package eu.remics.recovery.model;

import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.preferences.MConfiguration;

public class NotionKindHelper {
	
	public static void setDefaultNotionKindInScenario(NotionDTO notion, boolean force){
		if (!MConfiguration.isAutoAssignNotionKind() && !force) return;
		setDefaultNotionKind(notion);
	}
	
	public static void setDefaultNotionKind(NotionDTO notion){
		if (!MConfiguration.isAutoAssignNotionKind()) return;
		String name = notion.getName().toLowerCase().replace(" ", "");
		String type = null;
		for (String pat:MConfiguration.getTriggerKeywords())
			if (!pat.toLowerCase().replace(" ", "").isEmpty() && name.contains(pat.toLowerCase().replace(" ", ""))){
				type = NotionTypesEnum.Trigger.tag();
				break;
			}
		if (null==type){
			for (String pat:MConfiguration.getScreenKeywords())
				if (!pat.toLowerCase().replace(" ", "").isEmpty() && name.contains(pat.toLowerCase().replace(" ", ""))){
					type = NotionTypesEnum.Screen.tag();
					break;
				}
			if (null==type){
				for (String pat:MConfiguration.getMessageKeywords())
					if (!pat.toLowerCase().replace(" ", "").isEmpty() && name.contains(pat.toLowerCase().replace(" ", ""))){
						type = NotionTypesEnum.Message.tag();
						break;
					}
				if (null==type){
					for (String pat:MConfiguration.getConfirmationDialogKeywords())
						if (!pat.toLowerCase().replace(" ", "").isEmpty() && name.contains(pat.toLowerCase().replace(" ", ""))){
							type = NotionTypesEnum.Confirmation_Dialog.tag();
							break;
						}
						if (null==type){
						for (String pat:MConfiguration.getListViewKeywords())
							if (!pat.toLowerCase().replace(" ", "").isEmpty() && name.contains(pat.toLowerCase().replace(" ", ""))){
								type = NotionTypesEnum.List_View.tag();
								break;
							}
						if (null==type){
							for (String pat:MConfiguration.getTreeViewKeywords())
								if (!pat.toLowerCase().replace(" ", "").isEmpty() && name.contains(pat.toLowerCase().replace(" ", ""))){
									type = NotionTypesEnum.Tree_View.tag();
									break;
								}
							if (null==type){
								for (String pat:MConfiguration.getSimpleViewKeywords())
									if (!pat.toLowerCase().replace(" ", "").isEmpty() && name.contains(pat.toLowerCase().replace(" ", ""))){
										type = NotionTypesEnum.Simple_View.tag();
										break;
									}
							}
						}
					}
				}
			}
		}
		if (null!=type){
			Stereotype stereotype = ((BusinessLayerFacade) ((Notion) notion).getGraph()).createSclkernel$Stereotype();
			stereotype.setName(type);
			((Notion) notion).addStereotype(stereotype);
		}
	}
	
}
