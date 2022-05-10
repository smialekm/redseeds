package eu.redseeds.sc.query.ui.editors;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.MessageBox;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.query.engine.SimilarSCProject;

public class SimilarSCProjectTableContentProvider extends LabelProvider
		implements IStructuredContentProvider, ITableLabelProvider {

	private double simLevel = 0.5;

	@Override
	public Object[] getElements(Object inputElement) {
//		if (inputElement instanceof SCProject) {
//			SCQueryEngine queryEngine = new SCQueryEngineImpl();
//			Object[] result;
//			try {
//				result = queryEngine.findCases((SCProject) inputElement,
//						simLevel).toArray();
//			} catch (RuntimeException e) {
//				e.printStackTrace();
//				Activator.log(
//						"Problem during running query: " + e.getMessage(),
//						Status.ERROR);
//				errorMB("Problem during running query, see Error Log for details");
//				return new Object[0];
//			}
//			return result;
//		}
		if (inputElement instanceof Object[]){
			return (Object[])inputElement;
		}
		return new Object[0];
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		if (!(element instanceof SimilarSCProject)) {
			return result;
		}
		switch (columnIndex) {
		case 0:
			result = ((SimilarSCProject) element).getScProject().getMainCase()
					.getName();
			break;
		case 1:
			result = String.valueOf(((SimilarSCProject) element)
					.getSimilarityValue().getSimilarityValue());
			break;
		default:
			result = "";
			break;
		}
		return result;
	}

	public double getSimLevel() {
		return simLevel;
	}

	public void setSimLevel(double simLevel) {
		this.simLevel = simLevel;
	}

	/**
	 * displays an error message box
	 * 
	 * @param shell
	 */
	private void errorMB(String message) {
		MessageBox warnMB = new MessageBox(SCProjectHelper.getShell(),
				SWT.ICON_ERROR | SWT.OK);

		warnMB.setMessage(message);
		warnMB.setText("Query Engine problem problem");
		warnMB.open();
	}
}