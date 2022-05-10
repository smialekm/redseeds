package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import eu.redseeds.sc.editor.rsl.actions.AssignSenseAction;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.TerminologyWidget;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

/**
 * Dialog used for selecting sense of notions created from hyperlinked description
 *
 */
public class SelectNotionSenseDialog extends Dialog {
	
	private String notionName;
	private TermDTO term;
	private PhraseDTO phrase;
	private Composite parent;
	
	private long initialTermUID = 0;
	
	private TerminologyWidget tw;

	/**
	 * @param parent  
	 * @param phrase has to contain a term (noun)
	 */
	public SelectNotionSenseDialog(Shell parent, NounPhraseDTO phrase) {
		super(parent);
		this.term = phrase.getNoun();
		this.phrase = phrase;
		initialTermUID = term.getSynonymUid();
	}
	
	protected Control createDialogArea(Composite parent) {
	    Composite comp = (Composite) super.createDialogArea(parent);
	    this.parent = comp;
	    getShell().setText("Assign a sense to the notion");
	    GridLayout layout = (GridLayout) comp.getLayout();
	    layout.numColumns = 2;
	   
	    GridData data = new GridData();
	    
	    Label label = new Label(comp, SWT.NONE);
	    label.setText("Notion: "+getNotionName());
	    data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
	    label.setLayoutData(data);
	    
    	data = new GridData();
    	tw = new TerminologyWidget(comp, null);
    	tw.setLayoutData(data);
		tw.setEnabled(true);
		tw.setTerm(getTerm());
		tw.setPhrase(getPhrase());
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		
	    data = new GridData();
    	Label emptyLabel = new Label(comp, SWT.NONE);
    	data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		if(getTerm().getSynonymUid() == 0) {
			emptyLabel.setText("");
		}
		else {
			emptyLabel.setText("This noun has sense assigned. Reassigning it to a new sense will reassign all occurences of this noun to the sense selected \nin this dialog.");
		}
	    emptyLabel.setLayoutData(data);
		
	    return comp;
	  }

//	public Shell getShell() {
//		return shell;
//	}

	public String getNotionName() {
		return notionName;
	}

	public void setNotionName(String notionName) {
		this.notionName = notionName;
	}

	public TermDTO getTerm() {
		return term;
	}

	public void setTerm(TermDTO term) {
		this.term = term;
	}
	
	@Override
	protected void cancelPressed() {
		super.cancelPressed();
		if(initialTermUID != getTerm().getSynonymUid()) {//revert to old synonym uid
			if(RemoteJGWNL.getInstance().isConnected() && parent != null) {
				AssignSenseAction action 
					= new AssignSenseAction(getTerm(), 
							RemoteJGWNL.getInstance().getTermSenseDTO(initialTermUID), 
							getPhrase(), parent);
				action.run();
			}
		}
	}

	public PhraseDTO getPhrase() {
		return phrase;
	}

	public long getInitialTermUID() {
		return initialTermUID;
	}
	
}
