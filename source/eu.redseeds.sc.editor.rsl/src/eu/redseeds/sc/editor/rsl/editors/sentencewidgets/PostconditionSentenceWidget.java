package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.UseCaseScenarioView;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;

public class PostconditionSentenceWidget extends Composite implements ScenarioSentenceWidget {

	private Label label;
	PostconditionSentenceDTO sent;

	private Label postconditionLabel;
	private Text postconditionText;
	private Composite postcondition;

	private PostconditionSentenceWidget control =this;
	private Composite comp;

	UseCaseScenarioView scenarioView;

	@Override
	public void refreshWidget() {
		if (sent.getOwningScenarios().size()>1)
			setInnerBackground(grey);
		else
			setInnerBackground(white);
		disableFields();
	}


	public PostconditionSentenceWidget(Composite parent, int arg1,
			PostconditionSentenceDTO sent, UseCaseScenarioView scenView) {
		super(parent, arg1);

		this.sent = sent;
		this.comp=parent;

		this.scenarioView = scenView;

		label = new Label(this,SWT.NONE);
		label.setText("final: "+ (sent.isSuccess()?"success":"failure"));

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = false;

		label.setLayoutData(gridData);

		label.setBackground(new Color(null, 255, 255, 255));

		GridLayout gridLayout = new GridLayout();

		gridLayout.numColumns = 1;
		gridLayout.marginLeft = 5;
		gridLayout.marginRight = 5;
		gridLayout.marginBottom=0;
		gridLayout.marginTop=0;
		gridLayout.marginHeight=0;
		gridLayout.verticalSpacing=0;
		//gridLayout.makeColumnsEqualWidth=true;

		setLayout(gridLayout);
		setBackground(new Color(null, 255, 255, 255));



		GridLayout gridLayout1 = new GridLayout();

		gridLayout1.numColumns = 2;
		gridLayout1.marginLeft = 0;
		gridLayout1.marginRight = 0;
		gridLayout1.marginBottom=0;
		gridLayout1.marginTop=0;
		gridLayout1.marginHeight=0;
		gridLayout1.verticalSpacing=0;
		gridLayout1.horizontalSpacing=0;
		gridLayout1.marginWidth=0;


		GridData gridData1 = new GridData();

		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.verticalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;

		GridData gridData2 = new GridData();

		gridData2.horizontalAlignment = GridData.BEGINNING;
		gridData2.verticalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = false;

		GridData gridData3 = new GridData();

		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.verticalAlignment = GridData.FILL;
		gridData3.grabExcessHorizontalSpace = true;

		postcondition = new Composite(this,SWT.NONE);
		postcondition.setLayout(gridLayout1);
		postcondition.setLayoutData(gridData1);
		postconditionLabel = new Label(postcondition,SWT.NONE);
		postconditionLabel.setText("postcondition: ");
		postconditionLabel.setLayoutData(gridData2);

		 postconditionText = new Text(postcondition,SWT.MULTI | SWT.WRAP);
		 if(sent.getSentenceText()==null)
			 postconditionText.setText("");//prevent from nullPointer exception
		 else
			 postconditionText.setText(sent.getSentenceText());
		 postconditionText.setData(sent);
		 postconditionText.setLayoutData(gridData3);
		 postconditionText.addModifyListener(new ModifyListener(){
			 public void modifyText(ModifyEvent e){
				 scenarioView.getEditor().backupScenariosIfNotDirty();
				 ((PostconditionSentenceDTO)(((Text)e.getSource()).getData())).setSentenceText(
				 ((Text)e.getSource()).getText());
				 postcondition.layout();
				 control.layout();
				 comp.layout();
				 scenarioView.getEditor().setDirty(true);
			 }
		 });
		 postconditionLabel.setBackground(new Color(null, 255, 255, 255));

		 postcondition.layout();

		 addSelectionAwareMouseListener(postconditionText);
		this.layout();

	}

	private void addSelectionAwareMouseListener(Control composite){
		composite.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
			@Override
			public void mouseDown(MouseEvent e) {
				if(scenarioView!=null){
					scenarioView.rmvSelectionFormAllOtherSentenceWidgets(PostconditionSentenceWidget.this);
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
			}});
	}


	public void addKeyPressedListenener(KeyListener l){
		label.addKeyListener(l);
	}


	public void removeKeyPressedListenener(KeyListener l){
		label.removeKeyListener(l);
	}

	public void focus(){
		postconditionText.forceFocus();
	}

	public void setInnerBackground(Color c){
		this.setBackground(c);
		label.setBackground(c);
		postconditionLabel.setBackground(c);
		postconditionText.setBackground(c);
	}

	@Override
	public ConstrainedLanguageSentenceDTO getSentence() {
		return sent;
	}

	@Override
	public boolean hasFocusIncChildren(){
		for (Control c: this.getChildren())
			if(c.isFocusControl())
				return true;
		return false;
	}


	public boolean disableFields(){
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(sent).getBusinessLayerFacade();
		boolean isClipboradMember = facade.isAnyClipboardMember(getSentence());
		if (isClipboradMember) {
			label.setEnabled(false);
			postconditionLabel.setEnabled(false);
			postconditionText.setEditable(false);
			postconditionText.setEnabled(false);
		} else
		{
			label.setEnabled(true);
			postconditionLabel.setEnabled(true);
			postconditionText.setEditable(true);
			postconditionText.setEnabled(true);
		}
		return isClipboradMember;
	}

	@Override
	public void removeSelection() {
		if(postconditionText!=null && !postconditionText.isDisposed()){
			postconditionText.setSelection(0, 0);
		}
	}

}
