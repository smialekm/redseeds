package eu.redset.tsl.editor.editors;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;

import eu.redseeds.common.SCProjectHelper;
import eu.redset.emf.model.tsl.ConditionSentence;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.DirectObject;
import eu.redset.emf.model.tsl.IndirectObject;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.ScenarioSentence;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestRelationship;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;
import eu.redset.emf.model.tsl.impl.TestRelationshipImpl;
import eu.redset.tsl.editors.actions.TestDetailsEditorOpenAction;

public class SentenceUCTPanel extends Composite{

	private ScenarioSentence sentence;
	private Label sentenceLabel;

	private Label svoRelTestsLabel;
	

	
	private Label inObjLabel;
	private Label inObjGenLabel;
	private StyledText inObjGenText = null;
	private Label inObjInstLabel;
	private StyledText inObjInstText = null;
	
	private int num;
	
	
	//private IndirectObject inObj;
	//private DirectObject diObj;
	private Notion directNotion, indirectNotion;
	Composite view;
	
	private SVOSentence svo;
	

	
	private ScenarioNotionPanel directNotionPanel;
	private ScenarioNotionPanel indirectNotionPanel;

	private RelatedTestsPanel svoRelatedTests;
	
	/*
	private ModifyListener modListener = new ModifyListener() {
		private EObject lisObj;
		
		public void setObject(EObject listenerObj){
			this.lisObj = listenerObj;
		}
		
		@Override
		public void modifyText(ModifyEvent e) {
			if (view instanceof UseCaseTestScenarioView)
				((UseCaseTestScenarioView)view).getEditor().setDirty(false);
			if (view instanceof TestCaseView)
				((TestCaseView)view).getEditor().setDirty(true);
			
			if (lisObj instanceof DirectObject){
				if (view instanceof UseCaseTestScenarioView);
					//((UseCaseTestScenarioView)view).getModifiedElementsMap();
				if (view instanceof TestCaseView)
					((TestCaseView)view).getModifiedElementsMap().put(diObj, diObjInstText.getText());

			} else if (lisObj instanceof IndirectObject){
				if (view instanceof UseCaseTestScenarioView);
				//((UseCaseTestScenarioView)view).getModifiedElementsMap();
				if (view instanceof TestCaseView)
				((TestCaseView)view).getModifiedElementsMap().put(inObj, inObjInstText.getText());
			}
		}
	};
	
	*/
	public SentenceUCTPanel(ScenarioSentence sentence, int num, Composite parent, int style, Composite view) {
		super(parent, SWT.BORDER);
		this.sentence = sentence;
		this.num = num;
		this.view = view;
		createContent();
	}

	
	
	private void createContent(){
		this.setLayout(new FormLayout());
		FormData fd = new FormData();

		
		sentenceLabel = new Label(this, SWT.None);
		sentenceLabel.setBackground(new Color(null,255,255,255));
		this.setBackground(new Color(null, 255, 255, 255));
		if (sentence instanceof SVOSentence){
			SVOSentence svo = (SVOSentence)sentence;
			sentenceLabel.setText(svo.getNumber()+". "+svo.getName());
		} else if (sentence instanceof ConditionSentence){
			ConditionSentence condition = (ConditionSentence)sentence;
			sentenceLabel.setText("condition: "+condition.getName());
		} else if (sentence instanceof ControlSentence){
			ControlSentence control = (ControlSentence)sentence;
			sentenceLabel.setText("invoke: "+control.getName());
		}

		FontData[] fontData = sentenceLabel.getFont().getFontData();
		fontData[0].setHeight(8);
		fontData[0].setStyle(SWT.BOLD);
		sentenceLabel.setFont( new Font(this.getDisplay(),fontData[0]));
		
		fd = new FormData();
		fd.top = new FormAttachment(5, 0);
		fd.left = new FormAttachment(0, 0);
		//fd.right = new FormAttachment(100, 0);
		sentenceLabel.setLayoutData(fd);
	
		if (sentence instanceof SVOSentence){
			svo = (SVOSentence)sentence;
			
			
			svoRelatedTests = new RelatedTestsPanel(this, svo, SWT.NONE);
			fd = new FormData();
			fd.top = new FormAttachment(sentenceLabel, 10);
			fd.left = new FormAttachment(0, 5);
			svoRelatedTests.setLayoutData(fd);
			
			directNotion = svo.getPredicate().getDirectNotion();
			indirectNotion = svo.getPredicate().getIndirectNotion();
			
			if (directNotion != null){
				directNotionPanel = new ScenarioNotionPanel(this, SWT.BORDER);
				directNotionPanel.setNotion(directNotion);
				directNotionPanel.createContent();
				
				fd = new FormData();
				fd.top = new FormAttachment(svoRelatedTests, 10);
				fd.left = new FormAttachment(0, 5);
				fd.right = new FormAttachment(100, -5);
				directNotionPanel.setLayoutData(fd);
				
				
				
				/*
				
				diObjGenLabel = new Label(this, SWT.NONE);
				diObjGenLabel.setText("Description");
				diObjGenLabel.setBackground(new Color(null, 255, 255, 255));
				fd = new FormData();
				fd.top = new FormAttachment(addNotionTestButton, 5);
				fd.left = new FormAttachment(0, 0);
				//fd.right = new FormAttachment(100, 0);
				diObjGenLabel.setLayoutData(fd);
				
				diObjGenText = new StyledText(this,  SWT.READ_ONLY | SWT.BORDER);
				if (diObj.getGeneralDomain() != null)
					diObjGenText.setText(diObj.getGeneralDomain());
				else 
					diObjGenText.setText("n\\a");
				diObjGenText.setLineJustify(0, 1, true);
				diObjGenText.setBackground(new Color(null, 240, 240, 240));
				diObjGenText.setEditable(false);
				fd = new FormData();
				fd.top = new FormAttachment(addNotionTestButton, 5);
				fd.left = new FormAttachment(diObjGenLabel, 5);
				fd.right = new FormAttachment(100, 0);
				diObjGenText.setLayoutData(fd);
				
				if (view instanceof TestCaseView){
					diObjInstLabel = new Label(this, SWT.NONE);
					diObjInstLabel.setText("Instance");
					diObjInstLabel.setBackground(new Color(null, 255, 255, 255));
					fd = new FormData();
					fd.top = new FormAttachment(diObjGenText, 5);
					fd.left = new FormAttachment(0, 0);
					//fd.right = new FormAttachment(100, 0);
					diObjInstLabel.setLayoutData(fd);
					
					diObjInstText = new StyledText(this, SWT.WRAP | SWT.BORDER);
					if (diObj.getInstanceDomain() != null)
						diObjInstText.setText(diObj.getInstanceDomain());
					else 
						diObjInstText.setText(" - ");
					diObjInstText.setLineJustify(0, 1, true);
					diObjInstText.setBackground(new Color(null, 255, 255, 255));
					fd = new FormData();
					fd.top = new FormAttachment(diObjGenText, 5);
					fd.left = new FormAttachment(diObjGenLabel, 5);
					fd.right = new FormAttachment(100, 0);
					diObjInstText.setLayoutData(fd);
					diObjInstText.addModifyListener(new ReDSeTModifyListener(diObj, view, diObjInstText));
					
				}
				*/
				
			}
			if (indirectNotion != null){
				
				indirectNotionPanel = new ScenarioNotionPanel(this, SWT.BORDER);
				indirectNotionPanel.setNotion(indirectNotion);
				indirectNotionPanel.createContent();
				
				fd = new FormData();
				fd.top = new FormAttachment(directNotionPanel, 10);
				fd.left = new FormAttachment(0, 5);
				fd.right = new FormAttachment(100, -5);
				fd.bottom = new FormAttachment(100, -5);
				indirectNotionPanel.setLayoutData(fd);
				
			} else {
				fd.bottom = new FormAttachment(100, -5);
				directNotionPanel.setLayoutData(fd);
			}
				
				/*inObjLabel = new Label(this, SWT.BOLD);
				inObjLabel.setText(inObj.getName());
				inObjLabel.setBackground(new Color(null, 255, 255, 255));
				FontData[] fontData = inObjLabel.getFont().getFontData();
				fontData[0].setHeight(8);
				fontData[0].setStyle(SWT.BOLD);
				inObjLabel.setFont( new Font(this.getDisplay(),fontData[0]));
				fd = new FormData();
				if (view instanceof TestCaseView)
					fd.top = new FormAttachment(notionPanel, 10);
				else 
					fd.top = new FormAttachment(notionPanel, 10);
				fd.left = new FormAttachment(0, 0);
				fd.right = new FormAttachment(100, 0);
				inObjLabel.setLayoutData(fd);
				
				inObjGenLabel = new Label(this, SWT.NONE);
				inObjGenLabel.setText("Description");
				inObjGenLabel.setBackground(new Color(null, 255, 255, 255));
				fd = new FormData();
				fd.top = new FormAttachment(inObjLabel, 5);
				fd.left = new FormAttachment(0, 0);
				//fd.right = new FormAttachment(100, 0);
				inObjGenLabel.setLayoutData(fd);
				
				inObjGenText = new StyledText(this,  SWT.READ_ONLY | SWT.BORDER);
				if (inObj.getGeneralDomain() != null)
					inObjGenText.setText(inObj.getGeneralDomain());
				else 
					inObjGenText.setText("n\\a");
				inObjGenText.setLineJustify(0, 1, true);
				inObjGenText.setBackground(new Color(null, 240, 240, 240));
				inObjGenText.setEditable(false);
				fd = new FormData();
				fd.top = new FormAttachment(inObjLabel, 5);
				fd.left = new FormAttachment(inObjGenLabel, 5);
				fd.right = new FormAttachment(100, 0);
				inObjGenText.setLayoutData(fd);
				
				if (view instanceof TestCaseView){
					inObjInstLabel = new Label(this, SWT.NONE);
					inObjInstLabel.setText("Instance");
					inObjInstLabel.setBackground(new Color(null, 255, 255, 255));
					fd = new FormData();
					fd.top = new FormAttachment(inObjGenText, 5);
					fd.left = new FormAttachment(0, 0);
					//fd.right = new FormAttachment(100, 0);
					inObjInstLabel.setLayoutData(fd);
					
					inObjInstText = new StyledText(this, SWT.WRAP | SWT.BORDER);
					if (inObj.getInstanceDomain() != null)
						inObjInstText.setText(inObj.getInstanceDomain());
					else 
						inObjInstText.setText(" - ");
					inObjInstText.setLineJustify(0, 1, true);
					inObjInstText.setBackground(new Color(null, 255, 255, 255));
					fd = new FormData();
					fd.top = new FormAttachment(inObjGenText, 5);
					fd.left = new FormAttachment(inObjGenLabel, 5);
					fd.right = new FormAttachment(100, 0);
					inObjInstText.setLayoutData(fd);
					inObjInstText.addModifyListener(new ReDSeTModifyListener(inObj, view, inObjInstText));
				}
				*/
			
		}
		
	}
}
