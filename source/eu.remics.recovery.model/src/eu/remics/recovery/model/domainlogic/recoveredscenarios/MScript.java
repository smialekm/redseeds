package eu.remics.recovery.model.domainlogic.recoveredscenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.dto.TerminologyOperationFailureException;
import eu.remics.recovery.model.preferences.LanguageProfile;
import eu.remics.recovery.model.preferences.MConfiguration;
/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:21:08
 */
public class MScript {
	
	/**
	 * Parses the XML file and returns scenario class witch scenario sentences
	 * 
	 * @param script script path
	 * @param mapPath path to map file folder (null if default)
	 * @param emptyNotions empty notions flag
	 * @return created, but not saved notion
	 * @throws IOException
	 * @throws SAXException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws TerminologyOperationFailureException
	 */
    public static UseCaseDTO load(String script, String mapPath, boolean emptyNotions, RecoveryModelHelper rmh) throws IOException, SAXException, InstantiationException, IllegalAccessException, ClassNotFoundException, TerminologyOperationFailureException{ 	
    	UseCaseDTO uc;
    	DOMParser parser = new DOMParser();
        ArrayList<String> windname = new ArrayList<String>();
        int maxprevwind=-1;
        parser.parse(new InputSource(new InputStreamReader(new FileInputStream(script), "UTF-8")));
        ConstrainedLanguageScenarioDTO scenario=rmh.getBussinessLayerFacade().createConstrainedLanguageScenarioDTO();
        scenario.setName(MNameMaper.mapScriptNameToScenarioName(parser.getDocument().getDocumentElement().getAttributes().getNamedItem("name").getNodeValue()));
        boolean second = false;
        if (null==(uc=rmh.getBussinessLayerFacade().getUseCaseByName("~"+scenario.getName())) || (second=!uc.getConstrainedLanguageScenarioDTOList().isEmpty())){
        	uc = rmh.getBussinessLayerFacade().createUseCaseDTO();
        	int i=2;
        	if (second) while (null!=rmh.getBussinessLayerFacade().getUseCaseByName("~"+scenario.getName()+"_"+i)) i++;
            uc.setName("~"+scenario.getName()+(second?("_"+i):""));
        }
    	uc.addConstrainedLanguageScenario(scenario);
    	MRecoveredScenario.setTestScriptFile(uc,script.substring(script.lastIndexOf('\\')+1));
        MRecoveredScenario.setTestScriptName(uc,parser.getDocument().getDocumentElement().getAttributes().getNamedItem("name").getNodeValue());
        //finds and save top windows names
        for(int i = 0; i < parser.getDocument().getDocumentElement().getChildNodes().getLength(); i++) {
          if("topLevelWindows".equals(parser.getDocument().getDocumentElement().getChildNodes().item(i).getNodeName())){
              windname.add(parser.getDocument().getDocumentElement().getChildNodes().item(i).getAttributes().getNamedItem("name").getNodeValue());
          }
        }
        //calls function processTopLevelWindows for each top level windows group or other top level selector
        for (int i = 0; i < parser.getDocument().getDocumentElement().getChildNodes().getLength(); i++) if ("testElements".equals(parser.getDocument().getDocumentElement().getChildNodes().item(i).getNodeName())) maxprevwind = processTopLevelWindows(parser.getDocument().getDocumentElement().getChildNodes().item(i),scenario,uc,script,maxprevwind,windname,mapPath,emptyNotions);
        MRecoveredScenario.end(scenario);
        return uc;
    }
	
  /**
   * Parses a group of top level windows
   * 
   * @param node current process node
   * @param scenario current recovery scenario
   * @param uc temporary use case containing current recovery scenario
   * @param script script path
   * @param maxprevwind maximal number of occurring earlier window
   * @param windname windows names list
   * @param mapPath path to map file folder (null if default)
   * @param emptyNotions empty notions flag
   * @return id of the latest window
   * @throws IOException
   * @throws SAXException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   * @throws TerminologyOperationFailureException
   */
    private static int processTopLevelWindows(Node node, ConstrainedLanguageScenarioDTO scenario, UseCaseDTO uc, String script, int maxprevwind, ArrayList<String> windname, String mapPath, boolean emptyNotions) throws IOException, SAXException, InstantiationException, IllegalAccessException, ClassNotFoundException, TerminologyOperationFailureException{
    	int wind = -1;
        boolean action=false;
        Map<String,NotionDTO> windowsToNotionsMap = new HashMap<String,NotionDTO>();
        //finds call script statement outside of the windows
        if ("com.ibm.rational.test.ft.visualscript:ScriptMethod".equals(node.getAttributes().getNamedItem("xsi:type").getNodeValue())){
            String call;
            //bypass spaces between XML selectors
            if("argument".equals(node.getFirstChild().getNodeName())){
                if("testElements".equals(node.getFirstChild().getFirstChild().getNodeName())){
                    call=node.getFirstChild().getFirstChild().getAttributes().getNamedItem("value").getNodeValue().substring(1,node.getFirstChild().getFirstChild().getAttributes().getNamedItem("value").getNodeValue().length()-1);
                } else {
                    call=node.getFirstChild().getChildNodes().item(1).getAttributes().getNamedItem("value").getNodeValue().substring(1,node.getFirstChild().getChildNodes().item(1).getAttributes().getNamedItem("value").getNodeValue().length()-1);
                }
            } else {
                if("testElements".equals(node.getChildNodes().item(1).getFirstChild().getNodeName())){
                    call=node.getChildNodes().item(1).getFirstChild().getAttributes().getNamedItem("value").getNodeValue().substring(1,node.getChildNodes().item(1).getFirstChild().getAttributes().getNamedItem("value").getNodeValue().length()-1);
                } else {
                    call=node.getChildNodes().item(1).getChildNodes().item(1).getAttributes().getNamedItem("value").getNodeValue().substring(1,node.getChildNodes().item(1).getChildNodes().item(1).getAttributes().getNamedItem("value").getNodeValue().length()-1);
                }
            }
            //sets InvokedBy or adds invoked sentence to scenario depending on situation (if the call statement is not preceded by anything except call statement is set InvokedBy)
            if (maxprevwind==-1){
            	MRecoveredScenario.setInvokedBy(MNameMaper.mapScriptNameToScenarioName(call), uc);
            } else {
            	MRecoveredScenario.addInvokedSentence(scenario,MNameMaper.mapScriptNameToScenarioName(call));
            }
        //finds top level group
        } else if ("com.ibm.rational.test.ft.visualscript.common:TopLevelWindowGroup".equals(node.getAttributes().getNamedItem("xsi:type").getNodeValue())){
            wind=Integer.parseInt(node.getAttributes().getNamedItem("topLevelWindow").getNodeValue().substring(node.getAttributes().getNamedItem("topLevelWindow").getNodeValue().indexOf(".")+1));
            //adds sentence about display screens if encounter new window (only if it not occurs until now, if showsonlynewwindows flag is enabled)
            if (wind>maxprevwind || !MConfiguration.isShowsonlynewwindows()){
                //determines domain
                String dom=null;
                for(int i=0;i<node.getChildNodes().getLength();i++) if (!"#text".equals(node.getChildNodes().item(i).getNodeName()) && "com.ibm.rational.test.ft.visualscript:ProxyMethod".equals(node.getChildNodes().item(i).getAttributes().getNamedItem("xsi:type").getNodeValue())){
                    dom=node.getChildNodes().item(i).getAttributes().getNamedItem("domain").getNodeValue();
                    break;
                }
                String windowName = MConfiguration.getLanguageProfiles().get(MConfiguration.getLanguageProfiles().indexOf(new LanguageProfile(dom,null,null))).getNameConverterInstance().convertName(windname.get(wind));
                if (MConfiguration.isEnableDialogNaming() && null==getMapPath(script,windname.get(wind),mapPath)){
                	for (int i = 0; i < node.getChildNodes().getLength(); i++) if ("testElements".equals(node.getChildNodes().item(i).getNodeName()) && "com.ibm.rational.test.ft.visualscript:ProxyMethod".equals(node.getChildNodes().item(i).getAttributes().getNamedItem("xsi:type").getNodeValue()) && MConfiguration.getDialogroles().contains(node.getChildNodes().item(i).getAttributes().getNamedItem("role").getNodeValue())){
                		windowName = MConfiguration.getLanguageProfiles().get(MConfiguration.getLanguageProfiles().indexOf(new LanguageProfile(dom,null,null))).getNameConverterInstance().convertName(node.getChildNodes().item(i).getAttributes().getNamedItem("controlName").getNodeValue());
                		break;
                	}
                }
                //adds sentence
                NotionDTO not = MRecoveredScenario.addSystemSentence(scenario,windowName);
                if (!windowsToNotionsMap.containsKey(windowName)) windowsToNotionsMap.put(windowName, not);
                if (maxprevwind==-1 && !MConfiguration.isCreateSentenceAboutFirstWindowDisplay()){
                	MRecoveredNotion.clean((SVOSentenceDTO) scenario.getLastSentence());
                	((SVOSentenceDTO) scenario.getLastSentence()).delete();
                }
            }
            //finds call script statement inside of the windows
            for (int i = 0; i < node.getChildNodes().getLength(); i++) if ("testElements".equals(node.getChildNodes().item(i).getNodeName())){
                if ("com.ibm.rational.test.ft.visualscript:ScriptMethod".equals(node.getChildNodes().item(i).getAttributes().getNamedItem("xsi:type").getNodeValue())){
                    String call;
                    //bypass spaces between XML selectors
                    if("argument".equals(node.getChildNodes().item(i).getFirstChild().getNodeName())){
                        if("testElements".equals(node.getChildNodes().item(i).getFirstChild().getFirstChild().getNodeName())){
                            call=node.getChildNodes().item(i).getFirstChild().getFirstChild().getAttributes().getNamedItem("value").getNodeValue().substring(1,node.getChildNodes().item(i).getFirstChild().getFirstChild().getAttributes().getNamedItem("value").getNodeValue().length()-1);
                        } else {
                            call=node.getChildNodes().item(i).getFirstChild().getChildNodes().item(1).getAttributes().getNamedItem("value").getNodeValue().substring(1,node.getChildNodes().item(i).getFirstChild().getChildNodes().item(1).getAttributes().getNamedItem("value").getNodeValue().length()-1);
                        }
                    } else {
                        if("testElements".equals(node.getChildNodes().item(i).getChildNodes().item(1).getFirstChild().getNodeName())){
                            call=node.getChildNodes().item(i).getChildNodes().item(1).getFirstChild().getAttributes().getNamedItem("value").getNodeValue().substring(1,node.getChildNodes().item(i).getChildNodes().item(1).getFirstChild().getAttributes().getNamedItem("value").getNodeValue().length()-1);
                        } else {
                            call=node.getChildNodes().item(i).getChildNodes().item(1).getChildNodes().item(1).getAttributes().getNamedItem("value").getNodeValue().substring(1,node.getChildNodes().item(i).getChildNodes().item(1).getChildNodes().item(1).getAttributes().getNamedItem("value").getNodeValue().length()-1);
                        }
                    }
                    //sets InvokedBy or adds invoked sentence to scenario depending on situation (if the call statement is not preceded by anything except call statement or first window is set InvokedBy)
                    if (maxprevwind==-1 && action==false){
                    	MRecoveredScenario.setInvokedBy(MNameMaper.mapScriptNameToScenarioName(call), uc);
                    } else {
                    	MRecoveredScenario.addInvokedSentence(scenario,MNameMaper.mapScriptNameToScenarioName(call));
                    }
                } else if ("com.ibm.rational.test.ft.visualscript:ProxyMethod".equals(node.getChildNodes().item(i).getAttributes().getNamedItem("xsi:type").getNodeValue())) {
                    action=true;
                    //calls function processTestElements for each test element
                    processTestElements(node.getChildNodes().item(i),scenario, script, wind, windname, mapPath, emptyNotions,windowsToNotionsMap);
                }
            }
        }
        //returns id of the latest window
        return wind>maxprevwind?wind:maxprevwind;
    }
    
    /**
     * Parses test element
     * 
     * @param node current process node
  	 * @param scenario current recovery scenario
     * @param script script path
     * @param windname windows names list
     * @param mapPath path to map file folder (null if default)
     * @param emptyNotions empty notions flag
     * @throws IOException
     * @throws SAXException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws DOMException
     * @throws TerminologyOperationFailureException
     */
    private static void processTestElements(Node node, ConstrainedLanguageScenarioDTO scenario, String script, int wind, ArrayList<String> windname, String mapPath, boolean emptyNotions, Map<String,NotionDTO> windowsToNotionsMap) throws IOException, SAXException, InstantiationException, IllegalAccessException, ClassNotFoundException, DOMException, TerminologyOperationFailureException {
    	RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
    	Node processnode=null;
        //determines domain
        String dom=node.getAttributes().getNamedItem("domain").getNodeValue();
        String controlName=null;
        if (MConfiguration.getDialogroles().contains(node.getAttributes().getNamedItem("role").getNodeValue())) controlName=node.getAttributes().getNamedItem("controlName").getNodeValue();
        //bypass spaces between XML selectors
        for(int i=0;i<node.getChildNodes().getLength();i++) if ("action".equals(node.getChildNodes().item(i).getNodeName())){
        	processnode=node.getChildNodes().item(i);
        	break;
        }
        String name=MConfiguration.getLanguageProfiles().get(MConfiguration.getLanguageProfiles().indexOf(new LanguageProfile(dom,null,null))).getNameConverterInstance().convertName(windname.get(wind));
        //finds selectors associated witch users selections not related to data entry
        if (("click".equals(processnode.getAttributes().getNamedItem("name").getNodeValue()) || "doubleclick".equals(processnode.getAttributes().getNamedItem("name").getNodeValue())) && MConfiguration.getClickroles().contains(node.getAttributes().getNamedItem("role").getNodeValue())){
            if(!processnode.hasChildNodes()){
                //adds sentence about user selections based on controlName parameter in current selector, if its possible
            	MRecoveredScenario.addClickSentence(scenario,node.getAttributes().getNamedItem("controlName").getNodeValue(),node.getAttributes().getNamedItem("role").getNodeValue(),windowsToNotionsMap.get(name));
            } else {
                //adds sentence about user selections based on value parameter belongs to one of descendants
                if ("argument".equals(processnode.getFirstChild().getNodeName())) processnode=processnode.getFirstChild(); else processnode=processnode.getChildNodes().item(1);
                if ("testelement".equals(processnode.getFirstChild().getNodeName())) processnode=processnode.getFirstChild(); else processnode=processnode.getChildNodes().item(1);
                if ("atPoint".equals(processnode.getAttributes().getNamedItem("name").getNodeValue()))
                	MRecoveredScenario.addClickSentence(scenario,node.getAttributes().getNamedItem("controlName").getNodeValue(),node.getAttributes().getNamedItem("role").getNodeValue(),windowsToNotionsMap.get(name));
                else {
                	if ("argument".equals(processnode.getFirstChild().getNodeName())) processnode=processnode.getFirstChild(); else processnode=processnode.getChildNodes().item(1);
                	if ("testelement".equals(processnode.getFirstChild().getNodeName())) processnode=processnode.getFirstChild(); else processnode=processnode.getChildNodes().item(1);
                	MRecoveredScenario.addClickSentence(scenario,processnode.getAttributes().getNamedItem("value").getNodeValue().substring(1,processnode.getAttributes().getNamedItem("value").getNodeValue().length()-1),node.getAttributes().getNamedItem("role").getNodeValue(),windowsToNotionsMap.get(name));
                }
            }
        //finds selectors associated witch data entry
        } else if("inputChars".equals(processnode.getAttributes().getNamedItem("name").getNodeValue()) || "inputKeys".equals(processnode.getAttributes().getNamedItem("name").getNodeValue()) || ("click".equals(processnode.getAttributes().getNamedItem("name").getNodeValue()) && MConfiguration.getInputclickroles().contains(node.getAttributes().getNamedItem("role").getNodeValue()))){
            //creates notion and gets it id
        	String path=null;
        	if (!emptyNotions && null==(path=getMapPath(script,windname.get(wind),mapPath)) && null!=controlName){
        		path=getMapPath(script,controlName,mapPath);
        		if (MConfiguration.isEnableDialogNaming() && null!=path) name=MConfiguration.getLanguageProfiles().get(MConfiguration.getLanguageProfiles().indexOf(new LanguageProfile(dom,null,null))).getNameConverterInstance().convertName(controlName);
        	}
        	Set<NotionDTO> fields = processNotion(path, rmh);
            if(!processnode.hasChildNodes()){
                //adds sentence about user input based on controlName parameter in current selector, if its possible
            	MRecoveredScenario.addInputSentence(scenario,name,fields,node.getAttributes().getNamedItem("controlName").getNodeValue(),windowsToNotionsMap.get(name));
            } else {
                if ("argument".equals(processnode.getFirstChild().getNodeName())) processnode=processnode.getFirstChild(); else processnode=processnode.getChildNodes().item(1);
                if ("testelement".equals(processnode.getFirstChild().getNodeName())) processnode=processnode.getFirstChild(); else processnode=processnode.getChildNodes().item(1);
                //adds sentence about user input based on value parameter belongs to one of descendants
                if(!processnode.hasChildNodes()){
                	MRecoveredScenario.addInputSentence(scenario,name,fields,processnode.getAttributes().getNamedItem("value").getNodeValue().substring(1,processnode.getAttributes().getNamedItem("value").getNodeValue().length()-1),windowsToNotionsMap.get(name));
                } else {
                    if ("argument".equals(processnode.getFirstChild().getNodeName())) processnode=processnode.getFirstChild(); else processnode=processnode.getChildNodes().item(1);
                    if ("testelement".equals(processnode.getFirstChild().getNodeName())) processnode=processnode.getFirstChild(); else processnode=processnode.getChildNodes().item(1);
                    MRecoveredScenario.addInputSentence(scenario,name,fields,(processnode.getAttributes().getNamedItem("value").getNodeValue().startsWith("\"") && processnode.getAttributes().getNamedItem("value").getNodeValue().endsWith("\""))?processnode.getAttributes().getNamedItem("value").getNodeValue().substring(1,processnode.getAttributes().getNamedItem("value").getNodeValue().length()-1):processnode.getAttributes().getNamedItem("value").getNodeValue(),windowsToNotionsMap.get(name));
                }
            }
        } else if (("click".equals(processnode.getAttributes().getNamedItem("name").getNodeValue()) || "doubleclick".equals(processnode.getAttributes().getNamedItem("name").getNodeValue())) && MConfiguration.getListroles().contains(node.getAttributes().getNamedItem("role").getNodeValue())){
        	boolean row = false, nap=false; 
        	List<String> col = new ArrayList<String>();
        	List<String> coltype = new ArrayList<String>();
        	List<String> colvalue = new ArrayList<String>();
        	loop:
        	for (int i=0;i<processnode.getChildNodes().getLength();i++) if ("argument".equals(processnode.getChildNodes().item(i).getNodeName())) for (int j=0; j<processnode.getChildNodes().item(i).getChildNodes().getLength();j++) if ("testelement".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getNodeName()) && !"atPoint".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getAttributes().getNamedItem("name").getNodeValue())){
        		nap=true;
        		if ("atCell".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getAttributes().getNamedItem("name").getNodeValue())) for (int k=0;i<processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength();k++) if ("argument".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())) for (int l=0; l<processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().getLength();l++) if ("testelement".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName()) && "atRow".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getAttributes().getNamedItem("name").getNodeValue())) {
        			processnode=processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l);
        			row=true;
        			break loop;	
        		}
        	}
        	if (nap){
        		if (row){
        			boolean odd=true;
        			for (int i=0;i<processnode.getChildNodes().getLength();i++) if ("argument".equals(processnode.getChildNodes().item(i).getNodeName())) for (int j=0; j<processnode.getChildNodes().item(i).getChildNodes().getLength();j++) if ("testelement".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getNodeName()) && "Value".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getAttributes().getNamedItem("elementType").getNodeValue())){
        				if (odd){
        					col.add(processnode.getChildNodes().item(i).getChildNodes().item(j).getAttributes().getNamedItem("value").getNodeValue().substring(1,processnode.getChildNodes().item(i).getChildNodes().item(j).getAttributes().getNamedItem("value").getNodeValue().length()-1));
        					odd=false;
        				} else {
        					coltype.add(processnode.getChildNodes().item(i).getChildNodes().item(j).getAttributes().getNamedItem("valueType").getNodeValue());
        					colvalue.add(processnode.getChildNodes().item(i).getChildNodes().item(j).getAttributes().getNamedItem("value").getNodeValue().substring(1,processnode.getChildNodes().item(i).getChildNodes().item(j).getAttributes().getNamedItem("value").getNodeValue().length()-1));
        					odd=true;
        				}
        			}
        		}
        		String nm = node.getAttributes().getNamedItem("controlName").getNodeValue();
        		for (String s:MConfiguration.getListroles()) if (nm.matches(".*?"+s)){
        			if (!nm.equalsIgnoreCase(node.getAttributes().getNamedItem("name").getNodeValue())) nm = node.getAttributes().getNamedItem("name").getNodeValue();
        			break;
        		}
        		MRecoveredScenario.addSelectionSentence(scenario,nm,node.getAttributes().getNamedItem("role").getNodeValue(),windowsToNotionsMap.get(name),col,coltype,colvalue);
        	}
        }
    }
    
    /**
     * Parses maps in order to extract fields
     * 
     * @param path path to object map connected with current window
     * @return set of notions fields
     * @throws IOException
     * @throws SAXException
     * @throws TerminologyOperationFailureException
     */
    private static Set<NotionDTO> processNotion(String path, RecoveryModelHelper rmh) throws IOException, SAXException, TerminologyOperationFailureException{
    	DOMParser parser = new DOMParser();
        HashSet<NotionDTO> fields=new HashSet<NotionDTO>();
        Node processnode;
        Map<NotionDTO,String> attributeToIdMap = new HashMap<NotionDTO,String>();
        if (null==path) return fields;
        parser.parse(new InputSource(new InputStreamReader(new FileInputStream(path), "UTF-8")));
        processnode=parser.getDocument().getDocumentElement();
        //scrolls to the proper subtree
        int i=0;
        while (i < processnode.getChildNodes().getLength()) {
            if ("Attribute".equals(processnode.getChildNodes().item(i).getNodeName()) && (("Name".equals(processnode.getChildNodes().item(i).getFirstChild().getNodeName()) && ".MtoSet".equals(processnode.getChildNodes().item(i).getFirstChild().getTextContent())) || ("Name".equals(processnode.getChildNodes().item(i).getChildNodes().item(1).getNodeName()) && ".MtoSet".equals(processnode.getChildNodes().item(i).getChildNodes().item(1).getTextContent())))){
                    processnode=processnode.getChildNodes().item(i);
                    break;
            }
            i++;
        }
        i=0;
        while (i < processnode.getChildNodes().getLength()) {
            if ("Value".equals(processnode.getChildNodes().item(i).getNodeName())){
                    processnode=processnode.getChildNodes().item(i);
                    break;
            }
            i++;
        }
        //process map objects
        for (i=0;i<processnode.getChildNodes().getLength();i++){
            String name = null;
            String atid=null;
            if ("MTO".equals(processnode.getChildNodes().item(i).getNodeName())){
                elements:
                //browses the parameters of the object
                for (int j=0;j<processnode.getChildNodes().item(i).getChildNodes().getLength();j++){
                	//save Id parameter
                	if ("Id".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getNodeName())) atid=processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent();
                	//save SimpleName parameter
                    if ("SimpleName".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getNodeName())) name=processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent();
                    //finds class parameter
                    if ("Class".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getNodeName()) && null!=name){
                        //if finds object connected to data entry, adds it as a notion attribute
                    	AttributeDataTypesEnum type = null;
                    	for (String s : MConfiguration.getInputpasswclasses())
                            if (processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent().matches(s)){
                            	type=AttributeDataTypesEnum.Password;
                            	break;
                            }
                    	if (null==type)
                    		for (String s : MConfiguration.getInputdescclasses())
                    			if (processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent().matches(s)){
                    				type=AttributeDataTypesEnum.Description;
                    				break;
                    			}
                    	if (null==type)
                    		for (String s : MConfiguration.getInputtextclasses())
                    			if (processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent().matches(s)){
                    				type=AttributeDataTypesEnum.String;
                    				break;
                    			}
                    	if (null!=type){
                        	List<String> val = new ArrayList<String>();
                        	for (int k=0;k<processnode.getChildNodes().item(i).getChildNodes().getLength();k++) if ("Prop".equals(processnode.getChildNodes().item(i).getChildNodes().item(k).getNodeName())) for (int l=0;l<processnode.getChildNodes().item(i).getChildNodes().item(k).getChildNodes().getLength();l++){
                        		if ("Key".equals(processnode.getChildNodes().item(i).getChildNodes().item(k).getChildNodes().item(l).getNodeName()) && !".itemText".equals(processnode.getChildNodes().item(i).getChildNodes().item(k).getChildNodes().item(l).getTextContent())) break;
                        		if ("Val".equals(processnode.getChildNodes().item(i).getChildNodes().item(k).getChildNodes().item(l).getNodeName())) for (int m=0;m<processnode.getChildNodes().item(i).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().getLength();m++) if ("Name".equals(processnode.getChildNodes().item(i).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName())) val.add(RemoteJGWNL.getInstance().getNounBaseForm(processnode.getChildNodes().item(i).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getTextContent().trim()));
                        	}
                        	
                        	for (String p : MConfiguration.getInputdescclasses())
                                if (processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent().matches(p)){
                                	
                                	break;
                                }
                        	for (String p : MConfiguration.getInputpasswclasses())
                                if (processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent().matches(p)){
                                	
                                	break;
                                }
                        	NotionDTO notion=MRecoveredNotion.addAttribute(name, type, atid, attributeToIdMap,val,rmh);
                        	for(String str:val) MRecoveredNotion.addOptionToAttribute(str, notion,null,null);
                        	if (!attributeToIdMap.containsKey(atid)) attributeToIdMap.put(notion, atid);
                        	fields.add(notion);
                            break elements;
                        }
                        //if finds object connected to users bivalent selections, adds it as a notion attribute
                        for (String s : MConfiguration.getCheckclasses())
                        if (processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent().matches(s)){
                        	NotionDTO notion=MRecoveredNotion.addAttribute(name, AttributeDataTypesEnum.Boolean, atid, attributeToIdMap,null,rmh);
                        	if (!attributeToIdMap.containsKey(atid)) attributeToIdMap.put(notion, atid);
                        	fields.add(notion);
                            break elements;
                        }
                        //if finds object connected to users selections, adds its parent name as  attribute (with this object as attribute of that notion attribute)
                        for (String s : MConfiguration.getOptionclasses())
                        if (processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent().matches(s)){
                            String id = null;
                            //finds parent id 
                            for (int k=0;k<processnode.getChildNodes().item(i).getChildNodes().getLength();k++){
                                if ("Parent".equals(processnode.getChildNodes().item(i).getChildNodes().item(k).getNodeName())){
                                    id=processnode.getChildNodes().item(i).getChildNodes().item(k).getTextContent();
                                    break;
                                }
                            }
                            //finds parent name
                            for (int k=0;k<processnode.getChildNodes().getLength();k++){
                                Boolean parentid = false;
                                if ("MTO".equals(processnode.getChildNodes().item(k).getNodeName())){
                                    for (int l=0;l<processnode.getChildNodes().item(k).getChildNodes().getLength();l++){
                                        //checks Id parameter
                                        if ("Id".equals(processnode.getChildNodes().item(k).getChildNodes().item(l).getNodeName()) && processnode.getChildNodes().item(k).getChildNodes().item(l).getTextContent().equals(id)) parentid=true;
                                        //if object has proper id adds notion attribute based on its name
                                        if ("SimpleName".equals(processnode.getChildNodes().item(k).getChildNodes().item(l).getNodeName()) && parentid){
                                        	NotionDTO notion=MRecoveredNotion.addAttribute(processnode.getChildNodes().item(k).getChildNodes().item(l).getTextContent(), null, id, attributeToIdMap,null,rmh);
                                        	if (!attributeToIdMap.containsKey(id)) attributeToIdMap.put(notion, id);
                                        	NotionDTO opt = MRecoveredNotion.addOptionToAttribute(name, notion, atid, attributeToIdMap);
                                        	if (!attributeToIdMap.containsKey(atid)) attributeToIdMap.put(opt, atid);
                                        	if (!fields.contains(notion)) fields.add(notion);
                                            break elements;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        //returns notions attributes
        return fields;
    }
    
    /**
     * Returns the path to the map corresponding to specified window
     * 
     * @param script script path
     * @param windname current widow name
     * @param mapPath path to map file folder (null if default)
     * @return path to map file connected with current window
     * @throws SAXException
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    private static String getMapPath(String script, String windname, String mapPath) throws SAXException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        DOMParser parser = new DOMParser();
        Node processnode;
        //finds a file
        if (null==mapPath){
            String s=script.substring(0,script.lastIndexOf('\\'));
            File file = new File(s+"\\resources\\visuals\\rational_ft_topObjects.rftvmap");
            if (!file.exists()){
                while (!file.exists() && s.contains("\\")){
                    s=s.substring(0,s.lastIndexOf('\\'));
                    file = new File(s+"\\resources\\visuals\\rational_ft_topObjects.rftvmap");
                }
                if (file.exists()) mapPath=s+"\\resources\\visuals\\";
            }
        }
        //opens list of top objects occurring in maps
        parser.parse(new InputSource(new InputStreamReader(new FileInputStream((null==mapPath?script.substring(0,script.lastIndexOf('\\'))+"\\resources\\visuals\\rational_ft_topObjects.rftvmap":(mapPath.charAt(mapPath.length()-1)=='\\'?mapPath+"rational_ft_topObjects.rftvmap":mapPath+"\\rational_ft_topObjects.rftvmap"))), "UTF-8")));
        processnode=parser.getDocument().getDocumentElement();
        //scrolls to the proper subtree
        int i=0;
        while (i < processnode.getChildNodes().getLength()) {
            if ("Attribute".equals(processnode.getChildNodes().item(i).getNodeName()) && (("Name".equals(processnode.getChildNodes().item(i).getFirstChild().getNodeName()) && ".MtoSet".equals(processnode.getChildNodes().item(i).getFirstChild().getTextContent())) || ("Name".equals(processnode.getChildNodes().item(i).getChildNodes().item(1).getNodeName()) && ".MtoSet".equals(processnode.getChildNodes().item(i).getChildNodes().item(1).getTextContent())))){
                    processnode=processnode.getChildNodes().item(i);
                    break;
            }
            i++;
        }
        i=0;
        while (i < processnode.getChildNodes().getLength()) {
            if ("Value".equals(processnode.getChildNodes().item(i).getNodeName())){
                    processnode=processnode.getChildNodes().item(i);
                    break;
            }
            i++;
        }
        //process map objects
        for (i=0;i<processnode.getChildNodes().getLength();i++){
            if ("MTO".equals(processnode.getChildNodes().item(i).getNodeName())){
                boolean prop=false;
                String uniqueid=null,domain = null,simplename=null;
                mto:
                for (int j=0;j<processnode.getChildNodes().item(i).getChildNodes().getLength();j++){
                    //saves SimpleName parameter
                    if ("SimpleName".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getNodeName())) simplename=processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent();
                    //saves Dom parameter
                    if ("Dom".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getNodeName())){
                        domain=processnode.getChildNodes().item(i).getChildNodes().item(j).getTextContent();
                        //checks SimpleName parameter
                        if(!simplename.equals(MConfiguration.getLanguageProfiles().get(MConfiguration.getLanguageProfiles().indexOf(new LanguageProfile(domain,null,null))).getNameConverterInstance().getSimpleName(windname))) break;
                        prop=(null==MConfiguration.getLanguageProfiles().get(MConfiguration.getLanguageProfiles().indexOf(new LanguageProfile(domain,null,null))).getKeyword());
                    }
                    //process properties of object
                    if ("Prop".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getNodeName())){
                        boolean isuniqueid=false;
                        for (int k=0;k<processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength();k++){
                            //checks Key parameter
                            if ("Key".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())){
                                if ("#uniqueid".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getTextContent())){
                                    isuniqueid=true;
                                } else if (prop || !processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getTextContent().equals(MConfiguration.getLanguageProfiles().get(MConfiguration.getLanguageProfiles().indexOf(new LanguageProfile(domain,null,null))).getKeyword())) break;
                            //checks Val parameter
                            } else if ("Val".equals(processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())){
                                //saves file path
                                if (isuniqueid) uniqueid=(null==mapPath?script.substring(0,script.lastIndexOf('\\'))+"\\resources\\visuals\\":(mapPath.charAt(mapPath.length()-1)=='\\'?mapPath:mapPath+"\\"))+processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getTextContent()+".rftvmap";
                                //checks dependent on the language parameter if needed
                                else {
                                    Node tprocessnode=processnode.getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k);
                                    if (tprocessnode.hasChildNodes()){
                                        if (!"#text".equals(tprocessnode.getFirstChild().getNodeName())) tprocessnode=tprocessnode.getFirstChild(); else tprocessnode=tprocessnode.getChildNodes().item(1);
                                    
                                    }
                                    if(tprocessnode.getTextContent().equals(MConfiguration.getLanguageProfiles().get(MConfiguration.getLanguageProfiles().indexOf(new LanguageProfile(domain,null,null))).getNameConverterInstance().getValue(windname))) prop=true;
                                    else break mto;
                                }
                                //returns file path
                                if (null!=uniqueid && prop) return uniqueid;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

}