package edu.cmu.lti.bio.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import edu.cmu.lti.bio.types.GeneTag;

public class GeneTagAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {

		String id = jCas.getSofaDataURI();
		String sentence = jCas.getDocumentText();
		GeneTag annotation = new GeneTag(jCas);
		annotation.setBegin(0);
		annotation.setEnd(sentence.length());
		annotation.setId(id);
		annotation.setText(sentence);
		annotation.addToIndexes();

	}

}
