package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import rsldl.diagram.DomainModelHelper;

import eu.redseeds.common.QueriesHelper;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.QueryTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;

public class QueryWidget extends Composite {
	
	Combo queryType;
	Combo querySubject;
	Combo queryDomain;
	
	VerbPhraseDTO phrase;
	ActionTypesEnum actionType;
	Map<Integer,String> codeLinks;
	String codeLink;
	
	public QueryWidget(Composite parent) {
		super(parent, SWT.NONE);
		createContent();
	}

	private void createContent() {
		queryType = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		queryType.setBounds(0, 0, 95, 13);
		querySubject = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		querySubject.setBounds(100, 0, 95, 13);
		Label textBasedOn = new Label(this, SWT.NONE);
		textBasedOn.setBounds(200, 5, 55, 13);
		textBasedOn.setText("based on");
		queryDomain = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		queryDomain.setBounds(255, 0, 95, 13);
	}
	
	public VerbPhraseDTO getPhrase() {
		return phrase;
	}

	public void setPhrase(VerbPhraseDTO phrase) {
		this.phrase = phrase;
	}
	
	public ActionTypesEnum getActionType() {
		return actionType;
	}

	public void setActionType(ActionTypesEnum actionType) {
		this.actionType = actionType;
	}

	public void initialize(String[] prm){
		refresh();
		if (null!=prm[0] && Arrays.asList(queryType.getItems()).contains(prm[0]))
			queryType.select(Arrays.asList(queryType.getItems()).indexOf(prm[0]));
		if (null!=prm[1] && Arrays.asList(querySubject.getItems()).contains(prm[1]))
			querySubject.select(Arrays.asList(querySubject.getItems()).indexOf(prm[1]));
		if (null!=prm[2] && Arrays.asList(queryDomain.getItems()).contains(prm[2]))
			queryDomain.select(Arrays.asList(queryDomain.getItems()).indexOf(prm[2]));
	}

	public void refresh(){
		if (null==phrase){
			queryType.removeAll();
			querySubject.removeAll();
			queryDomain.removeAll();
			return;
		}
		refreshCombo(queryType,QueryTypesEnum.names(actionType));
		refreshCombo(querySubject,getSubjects());
		refreshCombo(queryDomain,getDomains());
		if (queryDomain.getItemCount()>0 && -1==queryDomain.getSelectionIndex())
			queryDomain.select(0);
	}
	
	private static void refreshCombo(Combo combo, String[] values){
		if (combo.getItems().equals(values))
			return;
		String ps = -1!=combo.getSelectionIndex()?combo.getItem(combo.getSelectionIndex()):null;
		combo.removeAll();
		for (String s:values)
			combo.add(s);
		if (1==combo.getItemCount())
			combo.select(0);
		else if (null!=ps && Arrays.asList(combo.getItems()).contains(ps))
			combo.select(Arrays.asList(combo.getItems()).indexOf(ps));
	}
	
	private String[] getSubjects(){
		String s1 = phrase instanceof ComplexVerbPhraseDTO?((ComplexVerbPhraseDTO) phrase).getSimpleVerbPhrase().getObject().getNoun().getName():phrase.getObject().getNoun().getName();
		String s2 = phrase instanceof ComplexVerbPhraseDTO?((ComplexVerbPhraseDTO) phrase).getObject().getNoun().getName():null;
		return null!=s2?new String[]{s1,s2}:new String[]{s1};
	}
	
	public String[] getDomains(){
		String[] dmn = DomainModelHelper.getDomains(phrase);
		String[] res = new String[dmn.length+1];
		res[0] = ""+Character.toString('\u221E');
		System.arraycopy(dmn, 0, res, 1, dmn.length);
		return res;
	}
	
	public String getTextForDescription(){
		return QueriesHelper.getRSLDLDescription(-1!=queryType.getSelectionIndex()?queryType.getItem(queryType.getSelectionIndex()):null,-1!=querySubject.getSelectionIndex()?querySubject.getItem(querySubject.getSelectionIndex()):null,-1!=queryDomain.getSelectionIndex() && 0!=queryDomain.getSelectionIndex()?queryDomain.getItem(queryDomain.getSelectionIndex()):null, codeLinks, codeLink);
	}
	
	public QueryTypesEnum getQueryType(){
		if (-1!=queryType.getSelectionIndex())
			return QueryTypesEnum.getQueryTypeByName(queryType.getItem(queryType.getSelectionIndex()));
		return null;
	}

	public void setCodeLinks(Map<Integer,String> codeLinks) {
		this.codeLinks = codeLinks;
	}

	public void setCodeLink(String codeLink) {
		this.codeLink = codeLink;
	}

}
