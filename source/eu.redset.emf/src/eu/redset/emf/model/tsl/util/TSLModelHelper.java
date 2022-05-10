package eu.redset.emf.model.tsl.util;

import org.eclipse.emf.ecore.EObject;

import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.UseCaseTest;



public class TSLModelHelper{

	private static TSLModelHelper tslModelHelper;
	
	private TSLModelHelper(){
		tslModelHelper = null;
	}
	
	public static TSLModelHelper instance() {
		if (tslModelHelper == null) {
			tslModelHelper = new TSLModelHelper();
		}
		
		return tslModelHelper;
	}
	
	public String getName(EObject element){
		if (element instanceof TestScenario)
			return ((TestScenario)element).getName();
		else if (element instanceof UseCaseTest)
			return ((UseCaseTest)element).getName();
		else if (element instanceof TestPackage)
			return ((TestPackage)element).getName();
		else if (element instanceof TestSpecification)
			return ((TestSpecification)element).getName();
		else return "";
	}
	
	public String getName(Object element){
		if (element instanceof TestScenario)
			return ((TestScenario)element).getName();
		else if (element instanceof UseCaseTest)
			return ((UseCaseTest)element).getName();
		else if (element instanceof TestPackage)
			return ((TestPackage)element).getName();
		else return "";
	}
	
	public String getFullPath(EObject obj, boolean includeObject){
		String path = "";
		if (includeObject)
			path = TSLModelHelper.instance().getName(obj);
		EObject parent = obj.eContainer();
		while (!(parent instanceof TestSpecification)){
			path = TSLModelHelper.instance().getName(parent)+"\\"+path;
			parent = parent.eContainer(); 
		}
		path = TSLModelHelper.instance().getName(parent)+"\\"+path;
		return path;
	}
	
}
