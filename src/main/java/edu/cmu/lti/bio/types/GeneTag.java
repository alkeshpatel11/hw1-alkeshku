

/* First created by JCasGen Fri Oct 12 15:34:27 EDT 2012 */
package edu.cmu.lti.bio.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Oct 12 15:38:23 EDT 2012
 * XML source: /host/Users/alkesh/Desktop/Semester1/F12-Software Engineering for Information Systems/Assignments/hw1-alkeshku/src/main/resources/CpeDescriptor.xml
 * @generated */
public class GeneTag extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GeneTag.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected GeneTag() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public GeneTag(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public GeneTag(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public GeneTag(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated */
  public String getId() {
    if (GeneTag_Type.featOkTst && ((GeneTag_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "edu.cmu.lti.bio.types.GeneTag");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneTag_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated */
  public void setId(String v) {
    if (GeneTag_Type.featOkTst && ((GeneTag_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "edu.cmu.lti.bio.types.GeneTag");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneTag_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated */
  public String getText() {
    if (GeneTag_Type.featOkTst && ((GeneTag_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "edu.cmu.lti.bio.types.GeneTag");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneTag_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated */
  public void setText(String v) {
    if (GeneTag_Type.featOkTst && ((GeneTag_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "edu.cmu.lti.bio.types.GeneTag");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneTag_Type)jcasType).casFeatCode_text, v);}    
  }

    