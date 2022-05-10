package eu.redset.tsl.editor.editors;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;


import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.util.TSLModelHelper;



public class UseCaseTestScenarioChooseDecoratingLabelProvider extends DecoratingLabelProvider implements
ITableLabelProvider{

	private EObject test;
	
	public UseCaseTestScenarioChooseDecoratingLabelProvider(ILabelProvider provider,
			ILabelDecorator decorator, EObject test) {
		super(provider, decorator);
		this.test = test;
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

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
}