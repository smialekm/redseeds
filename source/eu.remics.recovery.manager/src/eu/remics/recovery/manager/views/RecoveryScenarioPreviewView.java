package eu.remics.recovery.manager.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MRecoveredScenario;

public class RecoveryScenarioPreviewView extends ViewPart {

	ScrolledComposite scrolledPanel;
	Composite panel;
	Composite attributePanel;
	Composite namePanel;
	Composite invokedByPanel;
	Composite invokesPanel;
	Composite testScriptNamePanel;
	Composite testScriptFilePanel;
	Label name;
	Label invokedBy;
	Label invokes;
	Label testScriptName;
	Label testScriptFile;
	Composite sentencesPanel;
	List<StyledText> sentences = new ArrayList<StyledText>();
	
	public RecoveryScenarioPreviewView() {}

	@Override
	public void createPartControl(Composite parent) {
		parent.setBackground(new Color(null, 255, 255, 255));
		parent.setLayout(new FillLayout());
		
		scrolledPanel = new ScrolledComposite(parent,SWT.V_SCROLL | SWT.H_SCROLL);
		scrolledPanel.setBackground(new Color(null, 255, 255, 255));
		
		panel = new Composite(scrolledPanel,SWT.NONE);
		panel.setBackground(new Color(null, 255, 255, 255));
		GridLayout gl = new GridLayout();
		gl.numColumns=1;
		panel.setLayout(gl);
		
		attributePanel = new Composite(panel, SWT.NONE);
		attributePanel.setBackground(new Color(null, 255, 255, 255));
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace=true;
		attributePanel.setLayoutData(gridData);
		gl = new GridLayout();
		gl.numColumns=5;
		attributePanel.setLayout(gl);
		
		namePanel = new Composite(attributePanel, SWT.NONE);
		namePanel.setBackground(new Color(null, 255, 255, 255));
		gl = new GridLayout();
		gl.numColumns=2;
		namePanel.setLayout(gl);
		Label lb = new Label(namePanel, SWT.NONE);
		lb.setText("Name: ");
		FontData fd = JFaceResources.getDefaultFont().getFontData()[0];
		lb.setFont(new Font(panel.getDisplay(), fd.getName(), fd.getHeight(), SWT.BOLD));
		lb.setBackground(new Color(null, 255, 255, 255));
		name = new Label(namePanel, SWT.NONE);
		name.setBackground(new Color(null, 255, 255, 255));
		
		invokedByPanel = new Composite(attributePanel, SWT.NONE);
		invokedByPanel.setBackground(new Color(null, 255, 255, 255));
		gl = new GridLayout();
		gl.numColumns=2;
		invokedByPanel.setLayout(gl);
		lb = new Label(invokedByPanel, SWT.NONE);
		lb.setText("Invoked by: ");
		lb.setFont(new Font(panel.getDisplay(), fd.getName(), fd.getHeight(), SWT.BOLD));
		lb.setBackground(new Color(null, 255, 255, 255));
		invokedBy = new Label(invokedByPanel, SWT.NONE);
		invokedBy.setBackground(new Color(null, 255, 255, 255));
		
		invokesPanel = new Composite(attributePanel, SWT.NONE);
		invokesPanel.setBackground(new Color(null, 255, 255, 255));
		gl = new GridLayout();
		gl.numColumns=2;
		invokesPanel.setLayout(gl);
		lb = new Label(invokesPanel, SWT.NONE);
		lb.setText("Invokes: ");
		lb.setFont(new Font(panel.getDisplay(), fd.getName(), fd.getHeight(), SWT.BOLD));
		lb.setBackground(new Color(null, 255, 255, 255));
		invokes = new Label(invokesPanel, SWT.NONE);
		invokes.setBackground(new Color(null, 255, 255, 255));
		
		testScriptNamePanel = new Composite(attributePanel, SWT.NONE);
		testScriptNamePanel.setBackground(new Color(null, 255, 255, 255));
		gl = new GridLayout();
		gl.numColumns=2;
		testScriptNamePanel.setLayout(gl);
		lb = new Label(testScriptNamePanel, SWT.NONE);
		lb.setText("Test script name: ");
		lb.setFont(new Font(panel.getDisplay(), fd.getName(), fd.getHeight(), SWT.BOLD));
		lb.setBackground(new Color(null, 255, 255, 255));
		testScriptName = new Label(testScriptNamePanel, SWT.NONE);
		testScriptName.setBackground(new Color(null, 255, 255, 255));
		
		testScriptFilePanel = new Composite(attributePanel, SWT.NONE);
		testScriptFilePanel.setBackground(new Color(null, 255, 255, 255));
		gl = new GridLayout();
		gl.numColumns=2;
		testScriptFilePanel.setLayout(gl);
		lb = new Label(testScriptFilePanel, SWT.NONE);
		lb.setText("Test script file: ");
		lb.setFont(new Font(panel.getDisplay(), fd.getName(), fd.getHeight(), SWT.BOLD));
		lb.setBackground(new Color(null, 255, 255, 255));
		testScriptFile = new Label(testScriptFilePanel, SWT.NONE);
		testScriptFile.setBackground(new Color(null, 255, 255, 255));
		
		sentencesPanel = new Composite(panel, SWT.NONE);
		sentencesPanel.setBackground(new Color(null, 255, 255, 255));
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace=true;
		gridData.grabExcessVerticalSpace=true;
		sentencesPanel.setLayoutData(gridData);
		sentencesPanel.setLayout(new GridLayout());
		
		scrolledPanel.setContent(panel);
		scrolledPanel.setExpandVertical(true);
		scrolledPanel.setExpandHorizontal(true);
	}

	@Override
	public void setFocus() {}

	public void setInput(UseCaseDTO scen) {
		for (StyledText st:sentences) st.dispose();
		sentences.clear();
		name.setText(scen.getName().substring(1));
		invokedBy.setText(null!=MRecoveredScenario.getInvokedBy(scen)?MRecoveredScenario.getInvokedBy(scen):"");
		invokes.setText(MRecoveredScenario.getInvokes(scen));
		testScriptName.setText(null!=MRecoveredScenario.getTestScriptName(scen)?MRecoveredScenario.getTestScriptName(scen):"");
		testScriptFile.setText(null!=MRecoveredScenario.getTestScriptFile(scen)?MRecoveredScenario.getTestScriptFile(scen):"");
		attributePanel.layout();
		for (ConstrainedLanguageSentenceDTO sent : scen.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList()){
			StyledText sentence = new StyledText(sentencesPanel,SWT.SINGLE | SWT.READ_ONLY);
			sentence.setBackground(new Color(null, 255, 255, 255));
			setText(sentence,sent);
			sentences.add(sentence);
		}
		sentencesPanel.layout();
		scrolledPanel.setMinHeight(scrolledPanel.getBounds().height+(sentences.isEmpty()?((GridLayout) sentencesPanel.getLayout()).marginHeight:((sentences.get(0).getBounds().height+((GridLayout) sentencesPanel.getLayout()).marginHeight)*sentences.size()))+((GridLayout) sentencesPanel.getLayout()).marginHeight-sentencesPanel.getBounds().height);
		int max = 2*((GridLayout) panel.getLayout()).marginWidth+namePanel.getBounds().width+invokedByPanel.getBounds().width+invokesPanel.getBounds().width+testScriptNamePanel.getBounds().width+testScriptFilePanel.getBounds().width+6*((GridLayout) attributePanel.getLayout()).marginWidth;
		for (StyledText sent: sentences)
			if (sent.getBounds().width+2*((GridLayout) panel.getLayout()).marginWidth>max)
				max = sent.getBounds().width+2*((GridLayout) panel.getLayout()).marginWidth;
		scrolledPanel.setMinWidth(max);
	}

	private void setText(StyledText sentence, ConstrainedLanguageSentenceDTO sent) {
		StyleRange sr;
		int m,l;
		if (sent instanceof SVOSentenceDTO){
			sentence.setText(sent.toString());
			SVOSentenceDTO svos = (SVOSentenceDTO) sent;
			sr= new StyleRange();
			sr.fontStyle = SWT.BOLD;
			sr.foreground = new Color(null, 0, 0, 255);
			sr.start= 0;
			m=svos.getSubject().getNounText().length();
			sr.length=m;
			sentence.setStyleRange(sr);
			SimpleVerbPhraseDTO svp = (SimpleVerbPhraseDTO) (svos.getPredicate() instanceof SimpleVerbPhraseDTO?svos.getPredicate():((ComplexVerbPhraseDTO) svos.getPredicate()).getSimpleVerbPhrase());
			sr= new StyleRange();
			sr.fontStyle = SWT.ITALIC;
			sr.foreground = new Color(null, 255, 0, 0);
			m++;
			sr.start= m;
			l=svp.getVerb().getName().length();
			sr.length=l;
			sentence.setStyleRange(sr);
			m+=l;
			sr= new StyleRange();
			sr.fontStyle = SWT.BOLD;
			sr.foreground = new Color(null, 0, 0, 255);
			m++;
			sr.start=m;
			l=svp.getObject().getNounText().length();
			sr.length=l;
			sentence.setStyleRange(sr);
			m+=l;
			if (svos.getPredicate() instanceof ComplexVerbPhraseDTO){
				ComplexVerbPhraseDTO cvp = (ComplexVerbPhraseDTO) svos.getPredicate();
				sr= new StyleRange();
				sr.foreground = new Color(null, 0, 255, 0);
				m++;
				sr.start=m;
				l=cvp.getPreposition().getName().length();
				sr.length=l;
				sentence.setStyleRange(sr);
				m+=l;
				sr= new StyleRange();
				sr.fontStyle = SWT.BOLD;
				sr.foreground = new Color(null, 0, 0, 255);
				m++;
				sr.start=m;
				l=cvp.getObject().getNounText().length();
				sr.length=l;
				sentence.setStyleRange(sr);
			}
		} else if (sent instanceof InvocationSentenceDTO){
			InvocationSentenceDTO is = (InvocationSentenceDTO) sent;
			sentence.setText("=>invoke/INSERT "+is.getInvokedUseCase().getName().substring(1));
			sr= new StyleRange();
			sr.fontStyle = SWT.BOLD;
			sr.start = 16;
			sr.length = is.getInvokedUseCase().getName().length()-1;
			sentence.setStyleRange(sr);
		}
	}

}
