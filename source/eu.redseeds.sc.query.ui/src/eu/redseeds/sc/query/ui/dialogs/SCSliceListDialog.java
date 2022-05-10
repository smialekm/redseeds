package eu.redseeds.sc.query.ui.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;

import eu.redseeds.sc.slicing.SliceType;

/**
 * list dialog with SCs available in workspace (projectNamesToShow list) and slicing method selection
 *
 */
public class SCSliceListDialog extends ListDialog {
	
	private static String SLICING_METHOD_LABEL = "Select slicing method";
	
	protected Combo slicing;
	protected List<String> projectNamesToShow;
	
	protected SliceType slicingMethod;
	
	/**
	 * Hide this constructor
	 * @param parent
	 */
	private SCSliceListDialog(Shell parent) {
		super(parent);
	}		
	
	public SCSliceListDialog(Shell parent, List<String> projectNamesToShow) {
		this(parent);
		if(projectNamesToShow == null) {
			projectNamesToShow = new ArrayList<String>();
		}
		this.projectNamesToShow = projectNamesToShow;
	}

	@Override
	protected Control createDialogArea(Composite container) {
		
		setMessage("Select target Software Case:");
		setHeightInChars(projectNamesToShow.size());
		
		Control control = super.createDialogArea(container);
		
		Label slicingLabel = new Label(container, SWT.NONE);
		slicingLabel.setText(SLICING_METHOD_LABEL);
		slicing = new Combo(container, SWT.READ_ONLY);
		for (SliceType currentType : SliceType.values()) {
			if (currentType != SliceType.TRACE_SLICE) {
				slicing.add(currentType.toString());
			}
		}
		slicing.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if(slicing != null) {
					if(slicing.getSelectionIndex() < SliceType.values().length-1) {
						slicingMethod = SliceType.values()[slicing.getSelectionIndex()];
						return;
					}
				}
				slicingMethod = null;
			}
			
		});
		slicing.select(0);
		GridData gd = new GridData(SWT.CENTER, SWT.FILL, true, true);
		gd.widthHint = convertWidthInCharsToPixels(getWidthInChars() - 1);
		slicingLabel.setLayoutData(gd);
		slicing.setLayoutData(gd);
		
		return control;
	}
	
	public SliceType getSlicingMethod() {
		return slicingMethod;
	}
}
