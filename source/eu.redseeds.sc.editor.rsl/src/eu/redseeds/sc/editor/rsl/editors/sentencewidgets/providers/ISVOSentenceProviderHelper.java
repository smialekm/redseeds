package eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers;

import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

public interface ISVOSentenceProviderHelper {

	public abstract String getStateText(SVOSentenceDTO sentenceDTO, Object state);

	public abstract void setStateText(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, Object state, String text);
	
	public void setExternal (boolean flag);

}