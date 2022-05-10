package eu.redset.tsl.editor.editors;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.redset.emf.model.tsl.ConditionSentence;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.ScenarioSentence;


public class TCScenarioSentencePanel extends Composite{

	private ScenarioSentence sentence;
	private Label sentenceLabel;
	private Label inObjLabel;
	private Label inObjGenLabel;
	private StyledText inObjGenText = null;
	private Label inObjInstLabel;
	private StyledText inObjInstText = null;
	
	private int num;
	
	private DomainObject directNotion, indirectNotion;
	Composite view;
	
	private SVOSentence svo;
	

	
	private TCScenarioDomainObjectPanel directNotionPanel;
	private TCScenarioDomainObjectPanel indirectNotionPanel;

	private TCRelatedTestsPanel svoRelatedTests;
	
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
	public TCScenarioSentencePanel(ScenarioSentence sentence, int num, Composite parent, int style, Composite view) {
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
						
			svoRelatedTests = new TCRelatedTestsPanel(this, svo, SWT.NONE);
			fd = new FormData();
			fd.top = new FormAttachment(sentenceLabel, 10);
			fd.left = new FormAttachment(0, 5);
			svoRelatedTests.setLayoutData(fd);
			
			directNotion = svo.getDirectObject();
			indirectNotion = svo.getIndirectObject();
			
			if (directNotion != null){
				directNotionPanel = new TCScenarioDomainObjectPanel(this, SWT.BORDER);
				directNotionPanel.setDomainObject(directNotion);
				directNotionPanel.createContent();
				
				fd = new FormData();
				fd.top = new FormAttachment(svoRelatedTests, 10);
				fd.left = new FormAttachment(0, 5);
				fd.right = new FormAttachment(100, -5);
				directNotionPanel.setLayoutData(fd);
				
			}
			if (indirectNotion != null){
				
				indirectNotionPanel = new TCScenarioDomainObjectPanel(this, SWT.BORDER);
				indirectNotionPanel.setDomainObject(indirectNotion);
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
			
		}
		
	}
}
