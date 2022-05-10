package eu.redset.tsl.editors.actions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.BLTestInstance;
import eu.redset.emf.model.tsl.ConditionSentence;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.GUITestInstance;
import eu.redset.emf.model.tsl.NFTestInstance;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.ScenarioSentence;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestInstance;
import eu.redset.emf.model.tsl.TestParameter;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestValue;

public class CreateCSVFileAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate{

	private static String ID_PROJECT_EXPLORER = "eu.redset.navigator.view";
	private IWorkbenchWindow window;
	private TestScenario ts;
	private BufferedWriter bw;
	//private DirFileSelection dirSelector;
	
	@Override
	public void run(IAction action) {

		
		 FileDialog dialog = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN);
		   dialog.setFilterExtensions(new String [] {"*.csv"});
		   //dialog.setFilterPath("c:\\temp");
		   String result = dialog.open();
		
		if (result == null)
				return;
		   
		if (result.equals(""))
			return;
		
		ts = getTestScenario();
		if (ts != null) {

			FileWriter fw = null;

			try {
				fw = new FileWriter(result);
			} catch (IOException e) {
				e.printStackTrace();
			}

			bw = new BufferedWriter(fw);
			try {
				bw.write("Test Type|Step|Test Name|Description|Test Data|Input Values|Characteristic to test|Expected result");
				bw.newLine();
				String line = "Test Scenario|N/A|"+ts.getName()+"|"+ts.getDescription()+"|N/A|N/A|N/A|N/A|N/A";
					bw.write(line);
					bw.newLine();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (TestCase tc : ts.getEReference1())
				documentTestCase(tc);

			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void documentTestCase(TestCase testCase){
		List testCaseList;
		List<TestCaseSentence> testCaseSentenceList;
		String preconditionText = "";
		String postconditionText = "";
		writeLine("Test Case", testCase.getOrderNumberGlobal(),testCase.getName(), testCase.getDescription(), "N/A", "N/A", "N/A", "N/A");
		if (testCase.getPrecondition() != null)
			writeLine("Precondition", testCase.getOrderNumberGlobal()+" pre",testCase.getPrecondition().getName(), "N/A", "N/A", "N/A", "N/A", "N/A");
		else
			writeLine("Precondition", testCase.getOrderNumberGlobal()+" pre","---", "N/A", "N/A", "N/A", "N/A", "N/A");
		
		testCaseSentenceList = testCase.getSentences();
		for (TestCaseSentence tcs : testCaseSentenceList){
			if (tcs.getScenarioSentence() instanceof SVOSentence)
				documentSVOSentence((SVOSentence)tcs.getScenarioSentence(), testCase.getOrderNumberGlobal());
			else if (tcs.getScenarioSentence() instanceof ControlSentence){
				if (!tcs.getScenarioSentence().getName().startsWith("NO INSERT"))
					documentControlSentence((ControlSentence)tcs.getScenarioSentence(), testCase.getOrderNumberGlobal());
			}
			else if (tcs.getScenarioSentence() instanceof ConditionSentence)
				documentConditionSentence((ConditionSentence)tcs.getScenarioSentence(), testCase.getOrderNumberGlobal());
		}
		documentAttachedTests(testCase, testCase.getOrderNumberGlobal());
		
		if (testCase.getPostcondition() != null)
			writeLine("Postcondition", testCase.getOrderNumberGlobal()+" post",testCase.getPostcondition().getName(), "N/A", "N/A","N/A","N/A","N/A");
		else
			writeLine("Postcondition", testCase.getOrderNumberGlobal()+" post","---", "N/A", "N/A","N/A","N/A","N/A");
	}
	
	private void documentAttachedTests(TestInstance testInstance, String stepNumber){
		List<TestInstance> attachedTests = testInstance.getAttachedTests();
		String chToTest = "";
		String expectedValue = "";
		

		
		for (TestInstance ti : attachedTests){
			
			List<TestParameter> testParamList = ti.getTestParameters();
			if (testParamList != null){
				if (testParamList.size() > 0){
					if (testParamList.get(0) != null){
						if (testParamList.get(0) instanceof TestParameter){
							chToTest = testParamList.get(0).getName();
							expectedValue = testParamList.get(0).getExpectedValue();
						}
					}
				}
			}
			
			if (ti instanceof BLTestInstance)
				writeLine("Business Logic Test",stepNumber +"BL Test", ((BLTest)ti).getName(), ti.getDescription(), "N/A","N/A",chToTest,expectedValue);
			else if (ti instanceof GUITestInstance){
				writeLine("GUI Test", stepNumber + "GUI Test", ti.getName(), ti.getDescription(),  "N/A","N/A",chToTest,expectedValue);
			}
			else if (ti instanceof NFTestInstance)
				writeLine("Non-Functional Test", stepNumber +"NF Test",ti.getName(), ti.getDescription(),  "N/A","N/A",chToTest,expectedValue);
			else 
				break;
			documentAttachedTests(ti, stepNumber);
		}
	}
	
	private void documentSVOSentence(SVOSentence svoSentence, String tcOrderNumber){
		writeLine("SVO Sentence", tcOrderNumber+" SVO "+svoSentence.getNumber(),svoSentence.getName(), svoSentence.getDescription(), "N/A","N/A", "N/A","N/A");
		documentAttachedTests(svoSentence, tcOrderNumber+" SVO "+svoSentence.getNumber());
		
		if (svoSentence.getDirectObject() != null){
			DomainObject directObject = svoSentence.getDirectObject();
			String testData = "";
			String testValue = "";
			List tvList = directObject.getInputData();
			if (tvList.size()>0){
				testData = ((TestValue)tvList.get(0)).getName();
				testValue = ((TestValue)tvList.get(0)).getValue();
			}
			writeLine("Domain Object", tcOrderNumber+" SVO "+svoSentence.getNumber()+" DirObj", directObject.getName(), directObject.getDescription(), testData, testValue, "N/A","N/A");
			documentAttachedTests(directObject, tcOrderNumber+" SVO "+svoSentence.getNumber()+" DirObj");
		}
		if (svoSentence.getIndirectObject() != null){
			DomainObject indirectObject = svoSentence.getIndirectObject(); 
			String testData = "";
			String testValue = "";
			List tvList = indirectObject.getInputData();
			if (tvList.size()>0){
				testData = ((TestValue)tvList.get(0)).getName();
				testValue = ((TestValue)tvList.get(0)).getValue();
			}
			writeLine("Domain Object", tcOrderNumber+" SVO "+svoSentence.getNumber()+" IndirObj", indirectObject.getName(), indirectObject.getDescription(), testData, testValue, "N/A","N/A");
			documentAttachedTests(indirectObject, tcOrderNumber+" SVO "+svoSentence.getNumber()+" IndirObj");
		}
		
	}
	
	private void documentControlSentence(ControlSentence controlSentence, String stepNumber){
		writeLine("Control Sentence", stepNumber+" Control", controlSentence.getName(), controlSentence.getDescription(),  "N/A","N/A", "N/A","N/A");
		List<TestInstance> attachedTests = controlSentence.getAttachedTests();
		
		for (TestInstance ti : attachedTests){
			if (ti instanceof TestCase)
				documentTestCase((TestCase)ti);
		}
		
		documentAttachedTests(controlSentence, stepNumber);
	}
	
	private void documentConditionSentence(ConditionSentence conditionSentence, String stepNumber){
		writeLine("Condition Sentence", stepNumber+" Cond", conditionSentence.getName(), conditionSentence.getDescription(),  "N/A","N/A", "N/A","N/A");
		
		
		documentAttachedTests(conditionSentence, stepNumber);
	}
	
/*
	private void documentTestInstances(Object parentTest, int colNum){
		List testInstanceList;
		if (parentTest instanceof TestSc){
			testInstanceList = ((TestScenario)parentTest).get
		}
	}
	*/
	
	private String getParentTestName(EObject test){
			Object parent = test.eContainer();
			if (parent instanceof TestScenario)
				return ((TestScenario)parent).getName();
			else if (parent instanceof TestCase)
				return ((TestCase)parent).getName();
			else if (parent instanceof SVOSentence)
				return ((SVOSentence)parent).getName();
			else if (parent instanceof Notion)
				return ((Notion)parent).getName();
			else return "";
	}
	
	private void writeLine(String testType, String stepNumber, String testName, String testDescription, /*String precondition, String postcondition,*/ String testData, String inputValue, String characteristicToTest, String expectedResult){
		try {
				String line = "";
				if (testName.equals(""))
					testName = "---";
				if (testDescription != null){
					if (testDescription.equals(""))
						testDescription = "---";
				} else
					testDescription = "---";
				/*
				if (precondition != null){
					if (precondition.equals(""))
						precondition = "---";
				} else
					precondition = "---";
				
				if (postcondition != null){
					if (postcondition.equals(""))
						postcondition = "---";
				} else
					postcondition = "---";
				*/
				if (testData != null){
					if (testData.equals(""))
						testData = "---";
				} else
					testData = "---";
				
				if (inputValue != null){
					if (inputValue.equals(""))
						inputValue = "---";
				} else
					inputValue = "---";
				
				if (characteristicToTest != null){
					if (characteristicToTest.equals(""))
						characteristicToTest = "---";
				} else
					characteristicToTest = "---";
				
				if (expectedResult != null){
					if (expectedResult.equals(""))
						expectedResult = "---";
				} else
					expectedResult = "---";
				
				line = testType+"|"+stepNumber+"|"+testName+"|"+testDescription+/*"|"+precondition+"|"+postcondition+*/"|"+testData+"|"+inputValue+"|"+characteristicToTest+"|"+expectedResult;
				bw.write(line);
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}
	
	private TestScenario getTestScenario(){
		TestScenario ts = null;
		
		try {
			
			/********* retrieving the selected item ***************/
			
			// Receiving references to opened editors in active page
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			
			// Receiving selected elements and their paths from Project Explorer
			IViewPart projectExplorer = ((IViewPart)activePage.findView(ID_PROJECT_EXPLORER));
			ITreeSelection select = (ITreeSelection)projectExplorer.getSite().getWorkbenchWindow().getSelectionService().getSelection();
			TreePath[] selectedPaths = select.getPaths();
			Object selectedItem = select.getFirstElement();
			
			// Tests generation can be performed if only one element is selected
			if (selectedPaths.length != 1){
				throw new PartInitException("Tests script generation can not be performed for current selection");
			}
			
			// Test generation can be performed if 2-level element is selected (sc or clipboard)
			if (!(selectedItem instanceof TestScenario)){
				throw new PartInitException("Tests generation can not be performed for current selection");
			}
			
			ts = (TestScenario)selectedItem;
		
		} catch (PartInitException e) {
			MessageDialog.openInformation(window.getShell(),
					"Current Software Case Browser", e.toString());
		}
		
		return ts;
	}
	
}
