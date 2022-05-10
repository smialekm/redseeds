package eu.redset.tsl.editor.editors;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.util.TSLModelHelper;
import eu.redset.tsl.editor.Activator;



public class UseCaseTestScenarioChooseLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	
	public UseCaseTestScenarioChooseLabelProvider() {
		super();
	}
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
			return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		if(!(element instanceof UseCaseTestScenario)) {
			return result;
		}
		UseCaseTestScenario scenario = (UseCaseTestScenario)element;
		switch (columnIndex) {
			case 0 :
				if (scenario.eContainer() != null){
					if (scenario.eContainer() instanceof UseCaseTest){
						result = TSLModelHelper.instance().getName(scenario.eContainer())+" - "+scenario.getName();
						break;
					}
						
				}
				result = scenario.eContainer().toString();
				break;
			default:
				result = "--TODO--";
				break;
		}
		return result;
	}

}
