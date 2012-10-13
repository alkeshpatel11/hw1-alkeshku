package edu.cmu.lti.bio.casconsumers;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceProcessException;
import org.xml.sax.SAXException;

public class GeneCasConsumer extends CasConsumer_ImplBase {

	int mDocNum;
	File mOutputFile = null;
	BufferedWriter bfw = null;

	@Override
	public void initialize() {

		mDocNum = 0;
		try {
		mOutputFile = new File("/host/Users/alkesh/Desktop/Semester1/F12-Software Engineering for Information Systems/Assignments/hw1-alkeshku/src/main/resources/data/my.out");//new File((String) getConfigParameterValue("OUTPUT_FILE"));
		
			bfw = new BufferedWriter(new FileWriter(mOutputFile));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void processCas(CAS aCAS) throws ResourceProcessException {

		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		// retreive the filename of the input file from the CAS
		FSIterator it = jcas.getAnnotationIndex(SourceDocumentInformation.type)
				.iterator();

		String geneId = "";
		String geneTag = "";
		int start = -1;
		int end = -1;
		if (it.hasNext()) {
			SourceDocumentInformation sentenceLoc = (SourceDocumentInformation) it
					.next();

			geneId = sentenceLoc.getUri();
			geneTag = sentenceLoc.getCoveredText();
			start = sentenceLoc.getBegin();
			end = sentenceLoc.getEnd();

		}

		// serialize XCAS and write to output file
		try {
			writeIntoFile(geneId, geneTag, start, end);
		} catch (IOException e) {
			throw new ResourceProcessException(e);
		} catch (SAXException e) {
			throw new ResourceProcessException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeIntoFile(String geneId, String geneTag, int start, int end)
			throws Exception {
		bfw.write(geneId + "\t" + geneTag + "\t" + start + "\t" + end);
		bfw.newLine();
		bfw.flush();
	}

	@Override
	public void destroy() {

		try {
			if (bfw != null) {
				bfw.close();
				bfw = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
