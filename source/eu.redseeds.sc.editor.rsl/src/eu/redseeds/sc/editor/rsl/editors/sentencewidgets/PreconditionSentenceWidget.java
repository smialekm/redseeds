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
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;

public class PreconditionSentenceWidget extends Composite implements ScenarioSentenceWidget {

	private Label label;
	private Text precondition;
	private PreconditionSentenceWidget control =this;
	private Composite comp;

	UseCaseScenarioView scenarioView;

	PreconditionSentenceDTO sent;

	@Override
	public void refreshWidget() {
		if (sent.getOwningScenarios().size()>1)
			setInnerBackground(grey);
		else
			setInnerBackground(white);
		disableFields();
	}

	public PreconditionSentenceWidget(Composite parent, int arg1,
			PreconditionSentenceDTO sent, UseCaseScenarioView scenView) {
		super(parent, arg1);

		this.sent = sent;
		this.comp=parent;
		this.scenarioView = scenView;


		label = new Label(this,SWT.NONE);
		label.setText("precondition: ");

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = false;

		label.setLayoutData(gridData);

		label.setBackground(new Color(null, 255, 255, 255));

		 precondition = new Text(this,SWT.MULTI | SWT.WRAP);
		 precondition.setText(sent.getSentenceText());
		 precondition.setData(sent);
		 precondition.addModifyListener(new ModifyListener(){
			 public void modifyText(ModifyEvent e){
				 scenarioView.getEditor().backupScenariosIfNotDirty();
				 ((PreconditionSentenceDTO)(((Text)e.getSource()).getData())).setSentenceText(
				 ((Text)e.getSource()).getText());
				 control.layout();
				 comp.layout();
				 scenarioView.getEditor().setDirty(true);
			 }
		 });

		GridLayout gridLayout = new GridLayout();

		gridLayout.numColumns = 2;
		gridLayout.marginLeft = 5;
		gridLayout.marginRight = 5;
		gridLayout.marginBottom=0;
		gridLayout.marginTop=0;
		gridLayout.marginHeight=0;
		gridLayout.verticalSpacing=0;
		//gridLayout.makeColumnsEqualWidth=true;

		setLayout(gridLayout);
		setBackground(new Color(null, 255, 255, 255));

		GridData gridData1 = new GridData();

		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.verticalAlignment = GridData.FILL;

		gridData1.grabExcessHorizontalSpace = true;

		precondition.setLayoutData(gridData1);

		addSelectionAwareMouseListener(precondition);

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
					scenarioView.rmvSelectionFormAllOtherSentenceWidgets(PreconditionSentenceWidget.this);
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
			}});
	}

	public void addKeyPressedListenener(KeyListener l){
		precondition.addKeyListener(l);
	}


	public void removeKeyPressedListenener(KeyListener l){
		precondition.removeKeyListener(l);
	}

	public void focus(){
		precondition.forceFocus();
	}

	public void addModifyListener(ModifyListener l){
		precondition.addModifyListener(l);
	}

	public void removeModifyListener(ModifyListener l){
		precondition.removeModifyListener(l);
	}

	public void setInnerBackground(Color c){
		this.setBackground(c);
		precondition.setBackground(c);
		this.setBackground(c);
		label.setBackground(c);
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
			precondition.setEditable(false);
			precondition.setEnabled(false);
		} else
		{
			label.setEnabled(true);
			precondition.setEditable(true);
			precondition.setEnabled(true);
		}
		return isClipboradMember;
	}

	@Override
	public void removeSelection() {
		if(precondition!=null && !precondition.isDisposed()){
			precondition.setSelection(0, 0);
		}

	}
}
