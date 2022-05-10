package eu.redseeds.sc.current.ui.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class ConceptDataViewTransformationWizardPage extends WizardPage {
	
	private static final String DEFAULT_ADD_LABEL = ">>";
	private static final String DEFAULT_REMOVE_LABEL = "<<";
	private Composite container;
	private org.eclipse.swt.widgets.List attributeList;
	private org.eclipse.swt.widgets.List targetAttributeList;
	private Button add;
	private Button remove;
	private List<NotionDTO> notionAttributesList;
	private List<NotionDTO> targetNotionAttributesList;
	
	protected ConceptDataViewTransformationWizardPage(List<NotionDTO> notionAttributesList, String conceptDataViewTransformation_windowTitle, String conceptDataViewTransformation_title, String conceptDataViewTransformation_description) {
		super(conceptDataViewTransformation_windowTitle);
		this.notionAttributesList=notionAttributesList;
		targetNotionAttributesList = new ArrayList<NotionDTO>();
		setTitle(conceptDataViewTransformation_title);
		setDescription(conceptDataViewTransformation_description);
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		
		attributeList = new org.eclipse.swt.widgets.List(container, SWT.BORDER | SWT.MULTI);
		GridData listData = new GridData(SWT.FILL, SWT.FILL, true, true);
		attributeList.setLayoutData(listData);
		
		Composite buttonGroup = new Composite(container, SWT.NONE);
		buttonGroup.setLayoutData(new GridData());
	
		GridLayout buttonLayout = new GridLayout();
		buttonLayout.marginHeight = 0;
		buttonLayout.marginWidth = 0;
		buttonGroup.setLayout(buttonLayout);
		
		add = new Button(buttonGroup, SWT.NONE);
		add.setText(DEFAULT_ADD_LABEL);
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {	
				add();
				if (0<targetAttributeList.getItemCount() && !remove.isEnabled()){
					remove.setEnabled(true);
					setPageComplete(true);
				}
				if (0==attributeList.getItemCount()) add.setEnabled(false);
			}	
		});
		GridData addData = new GridData(GridData.FILL_HORIZONTAL);
		add.setLayoutData(addData);	
		
		remove = new Button(buttonGroup, SWT.NONE);
		remove.setEnabled(false);
		remove.setText(DEFAULT_REMOVE_LABEL);
		remove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {	
				remove();
				if (0<attributeList.getItemCount() && !add.isEnabled()) add.setEnabled(true);
				if (0==targetAttributeList.getItemCount()){
					remove.setEnabled(false);
					setPageComplete(false);
				}
			}	
		});
		GridData removeData = new GridData(GridData.FILL_HORIZONTAL);
		remove.setLayoutData(removeData);	
		
		targetAttributeList = new org.eclipse.swt.widgets.List(container, SWT.BORDER | SWT.MULTI);
		GridData targetListData = new GridData(SWT.FILL, SWT.FILL, true, true);
		targetAttributeList.setLayoutData(targetListData);
		
		setControl(container);
		
		loadItems();
		
		setPageComplete(false);
	}
	
	private void add(){
		int[] selections = attributeList.getSelectionIndices();
		Arrays.sort(selections);
		for (int i = selections.length-1;i>=0;i--){
			String s = attributeList.getItem(selections[i]);
			NotionDTO not = notionAttributesList.get(selections[i]);
			attributeList.remove(selections[i]);
			notionAttributesList.remove(selections[i]);
			targetAttributeList.add(s);
			targetNotionAttributesList.add(not);
		}
		loadItems();
	}
	
	private void remove(){
		int[] selections = targetAttributeList.getSelectionIndices();
		Arrays.sort(selections);
		for (int i = selections.length-1;i>=0;i--){
			String s = targetAttributeList.getItem(selections[i]);
			NotionDTO not = targetNotionAttributesList.get(selections[i]);
			targetAttributeList.remove(selections[i]);
			targetNotionAttributesList.remove(selections[i]);
			attributeList.add(s);
			notionAttributesList.add(not);
		}
		loadItems();
	}
	
	private void loadItems(){
		Comparator<NotionDTO> comp =  new Comparator<NotionDTO>(){

			@Override
			public int compare(NotionDTO o1, NotionDTO o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		};
		Collections.sort(notionAttributesList,comp);
		String[] items = new String[notionAttributesList.size()];
		for (int i=0;i<notionAttributesList.size();i++)
			items[i] = notionAttributesList.get(i).getName()+":"+(!notionAttributesList.get(i).getExtendedDataType().isEmpty()?AttributeDataTypesEnum.getExtendedDataTypeToDisplay(notionAttributesList.get(i)).toLowerCase():"none");
		if (0!=items.length) attributeList.setItems(items);
		Collections.sort(targetNotionAttributesList,comp);
		String[] items2 = new String[targetNotionAttributesList.size()];
		for (int i=0;i<targetNotionAttributesList.size();i++)
			items2[i] = targetNotionAttributesList.get(i).getName()+":"+(!targetNotionAttributesList.get(i).getExtendedDataType().isEmpty()?AttributeDataTypesEnum.getExtendedDataTypeToDisplay(targetNotionAttributesList.get(i)).toLowerCase():"none");
		if (0!=items2.length) targetAttributeList.setItems(items2);
	}
	
	@Override
	public Control getControl() {
		return container;
	}
	
	public List<NotionDTO> getAttributes(){
		return targetNotionAttributesList;
	}

}
