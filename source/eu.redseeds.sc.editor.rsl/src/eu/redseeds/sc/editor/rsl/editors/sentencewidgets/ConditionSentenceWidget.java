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
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;

public class ConditionSentenceWidget extends Composite implements ScenarioSentenceWidget {

	@Override
	public void refreshWidget() {
		if (sent.getOwningScenarios().size()>1)
			setInnerBackground(grey);
		else
			setInnerBackground(white);
		disableFields();
	}

	private Label arrow;
	private Text condition;

	ConditionSentenceDTO sent;

	UseCaseScenarioView scenarioView;

	public ConditionSentenceWidget(Composite parent, int arg1,
			ConditionSentenceDTO sent, UseCaseScenarioView scenView) {
		super(parent, arg1);

		this.sent = sent;

		this.scenarioView = scenView;


		arrow = new Label(this,SWT.NONE);
		arrow.setText("=>cond: ");

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = false;

		arrow.setLayoutData(gridData);

		arrow.setBackground(new Color(null, 255, 255, 255));

		 condition = new Text(this,SWT.NONE);
		 condition.setText(sent.getSentenceText());
		 condition.setData(sent);
		 condition.addModifyListener(new ModifyListener(){
			 public void modifyText(ModifyEvent e){
				 scenarioView.getEditor().backupScenariosIfNotDirty();
				 ((ConditionSentenceDTO)(((Text)e.getSource()).getData())).setSentenceText(
				 ((Text)e.getSource()).getText());
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

		condition.setLayoutData(gridData1);

		addSelectionAwareMouseListener(condition);

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
					scenarioView.rmvSelectionFormAllOtherSentenceWidgets(ConditionSentenceWidget.this);
				}
			}

			@Override
			public void mouseUp(MouseEvent e) {
			}});
	}

	public void addKeyPressedListenener(KeyListener l){
		condition.addKeyListener(l);
	}


	public void removeKeyPressedListenener(KeyListener l){
		condition.removeKeyListener(l);
	}

	public void focus(){
		condition.forceFocus();
	}

	public void addModifyListener(ModifyListener l){
		condition.addModifyListener(l);
	}

	public void removeModifyListener(ModifyListener l){
		condition.removeModifyListener(l);
	}

	public void setInnerBackground(Color c){
		this.setBackground(c);
		condition.setBackground(c);
		this.setBackground(c);
		arrow.setBackground(c);
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
			arrow.setEnabled(false);
			condition.setEditable(false);
			condition.setEnabled(false);
		} else
		{
			arrow.setEnabled(true);
			condition.setEditable(true);
			condition.setEnabled(true);
		}
		return isClipboradMember;
	}

	@Override
	public void removeSelection() {
	}



}
