package eu.redseeds.sc.query.ui.editors;

import java.util.List;

import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

import eu.redseeds.scl.sclkernel.SCLElement;



class FourTreeViewDecoratingLabelProvider extends DecoratingLabelProvider {

	private List<Object> elements;
	private List<Object> preSelectedElements;
	private int treeType;
	
	public FourTreeViewDecoratingLabelProvider(ILabelProvider provider,
			ILabelDecorator decorator, List<Object> selectedElements, List<Object> preSelectedElements, int treeType) {
		super(provider, decorator);
		this.elements=selectedElements;
		this.preSelectedElements=preSelectedElements;
		this.treeType = treeType;
	}
	
	public Color getBackground(Object element) {
		if(element instanceof SCLElement) {
			for (int i=0; i<elements.size(); i++){
				if(elements.get(i) instanceof SCLElement) {
					if (((SCLElement)elements.get(i)).getId() == (((SCLElement)element).getId())){
						return new Color(null, 164, 211, 238);
					}
				}
			} 
		}
		return new Color(null, 255, 255, 255);
	}
	
	public Font getFont(Object element) {
		if(element instanceof SCLElement) {
			if (treeType == 0) {
				for (int i = 0; i < preSelectedElements.size(); i++) {
					if(preSelectedElements.get(i) instanceof SCLElement) {
						if (((SCLElement)preSelectedElements.get(i)).getId() == (((SCLElement)element).getId())) {
							return new Font(null, new FontData("Arial", 9, 1));
						}
					}
				}
			}
		}
		return null;
	}

}