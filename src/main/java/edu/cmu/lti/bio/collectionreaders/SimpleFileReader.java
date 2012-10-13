package edu.cmu.lti.bio.collectionreaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;

import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import edu.cmu.lti.bio.customtypes.SentenceInfo;
import org.apache.uima.jcas.tcas.DocumentAnnotation;

public class SimpleFileReader extends CollectionReader_ImplBase {

	String mEncoding=null;
	String mLanguage=null;
	int mCurrentSentenceNo=0;
	ArrayList<SentenceInfo> mSentences = null;

	@Override
	public void initialize() throws ResourceInitializationException {

		File file = new File("/host/Users/alkesh/Desktop/Semester1/F12-Software Engineering for Information Systems/Assignments/hw1-alkeshku/src/main/resources/data/sample.in");//(String) getConfigParameterValue(
				//"INPUT_FILE"));

		// get list of files (not subdirectories) in the specified directory
		mSentences = new ArrayList<SentenceInfo>();
		BufferedReader bfr = null;
		try {
			bfr = new BufferedReader(new FileReader(file));
			String str;
			while ((str = bfr.readLine()) != null) {
				String rec[]=str.trim().split("[ ]");
				if(rec.length<2){
					continue;
				}
				String id=rec[0];//Separating sentence id from line
				String text=Arrays.toString(Arrays.copyOfRange(rec, 1, rec.length));//Separating sentence text from line
							
				mSentences.add(new SentenceInfo(id,text));
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ResourceInitializationException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ResourceInitializationException(e);
		} finally {
			if (bfr != null) {
				try {
					bfr.close();
				} catch (IOException e) {					
					throw new ResourceInitializationException(e);
				}
				bfr = null;
			}
		}

	}

	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		JCas jcas;
	    try {
	      jcas = aCAS.getJCas();
	    } catch (CASException e) {
	      throw new CollectionException(e);
	    }

	    // open input sentence list iterator
	    SentenceInfo sentence = (SentenceInfo) mSentences.get(mCurrentSentenceNo++);
	    try {
	      byte[] contents = new byte[(int) sentence.getText().length()];
	      
	      String text;
	      if (mEncoding != null) {
	        text = new String(contents, mEncoding);
	      } else {
	        text = new String(contents);
	      }
	      // put document in CAS
	      jcas.setDocumentText(text);
	    }
	    finally{
	    	
	    }

	    // set language if it was explicitly specified 
	    //as a configuration parameter
	    if (mLanguage != null) {
	      ((DocumentAnnotation) jcas.getDocumentAnnotationFs()).setLanguage(mLanguage);
	    }

	    // Also store location of source document in CAS. 
	    // This information is critical if CAS Consumers will 
	    // need to know where the original document contents 
	    // are located.
	    // For example, the Semantic Search CAS Indexer 
	    // writes this information into the search index that 
	    // it creates, which allows applications that use the 
	    // search index to locate the documents that satisfy 
	    //their semantic queries.
	    SourceDocumentInformation srcDocInfo = 
	            new SourceDocumentInformation(jcas);
	    srcDocInfo.setUri(sentence.getId());	    
	    srcDocInfo.setOffsetInSource(0);
	    srcDocInfo.setDocumentSize((int) sentence.getText().length());
	    srcDocInfo.setLastSegment(
	            mCurrentSentenceNo == mSentences.size());
	    srcDocInfo.addToIndexes();

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Progress[] getProgress() {
		return new Progress[]{
			     new ProgressImpl(mCurrentSentenceNo,mSentences.size(),Progress.ENTITIES)};
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		 return mCurrentSentenceNo < mSentences.size();
	}

}
