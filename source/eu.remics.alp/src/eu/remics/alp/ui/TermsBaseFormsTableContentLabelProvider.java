package eu.remics.alp.ui;

import java.rmi.RemoteException;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import de.uni_koblenz.jgwnl.exceptions.JGWNLException;
import de.uni_koblenz.jgwnl.info.SynonymInfo;
import de.uni_koblenz.jgwnl.info.WordInfo;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.remics.alp.Activator;

public class TermsBaseFormsTableContentLabelProvider implements IStructuredContentProvider, ITableLabelProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof String) {
			return ((String)element);
		}
		return null;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof String) {
			WordInfo wordInfo = null;
			try {
				wordInfo = RemoteJGWNL.getInstance().lookupWord((String)inputElement);
			} catch (RemoteException e) {
				Activator.log("Error while accesing terminology", IStatus.WARNING);
				e.printStackTrace();
			} catch (JGWNLException e) {
				Activator.log("Error while accesing terminology", IStatus.WARNING);
				e.printStackTrace();
			}
			if(wordInfo != null) {
				if(wordInfo.wasFound()) {
					//get rid of repetitions
					HashSet<String> baseFormsSet = new HashSet<String>();
					for(SynonymInfo si : wordInfo.getSynonymInfos()) {
						baseFormsSet.add(si.getLemma());
					}
					return baseFormsSet.toArray();
				}
			}
		}
		return null;
	}

}
