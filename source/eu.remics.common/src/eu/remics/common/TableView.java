package eu.remics.common;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchSite;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MRecoveredScenario;
import eu.remics.recovery.model.dto.XScenariosCommonPart;

public class TableView {
	private TableViewer viewer;
	public void createViewer(Composite parent, IWorkbenchSite site, String[] columnTitles) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer, columnTitles);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new TableContentProvider());
		site.setSelectionProvider(viewer);

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}
	
	public TableViewer getViewer() {
		return viewer;
	}
	
	// This will create the columns for the table
	private void createColumns(final Composite parent, final TableViewer viewer, String[] columnTitles) {
		String[] titles = columnTitles;

		TableColumnLayout layout = new TableColumnLayout();
		parent.setLayout(layout);
		parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		for(int i=0; i < columnTitles.length; i++){
			TableViewerColumn col = createTableViewerColumn(titles[i], i);
			final String title = titles[i];
			
			col.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					Object dto = element;
					if(title.equalsIgnoreCase("Scenarios")){
						return ((ConstrainedLanguageScenarioDTO)dto).getName();
					}
					else if(title.equalsIgnoreCase("Alternative scenario") || title.equalsIgnoreCase("Splitted scenario")){
						if(dto == null || dto.equals("")){
							return "";
						}
						if(((ConstrainedLanguageSentenceDTO)dto).toString().endsWith("InvocationSentence")){
							String name = ( ((InvocationSentenceDTO)dto).getInvokedUseCase() == null ? "" :  ((InvocationSentenceDTO)dto).getInvokedUseCase().getName());
							return "=>invoke: " + name;
						}
						return ((ConstrainedLanguageSentenceDTO)dto).toString();
					}
					else if(title.equalsIgnoreCase("Scenario") || title.equalsIgnoreCase("Similar scenario")){
						if(dto == null || dto.equals("")){
							return "";
						}
						if(((ConstrainedLanguageSentenceDTO)dto).toString().endsWith("ConditionSentence")){
							return "=>cond: " + ((ConditionSentenceDTO)dto).getSentenceText();
						}
						if(((ConstrainedLanguageSentenceDTO)dto).toString().endsWith("InvocationSentence")){
							String name = ( ((InvocationSentenceDTO)dto).getInvokedUseCase() == null ? "" :  ((InvocationSentenceDTO)dto).getInvokedUseCase().getName());
							return "=>invoke: " + name;
						}
						if(((ConstrainedLanguageSentenceDTO)dto).toString().endsWith("PreconditionSentence")){
							return "precondition: " + ((PreconditionSentenceDTO)dto).getSentenceText();
						}
						if(((ConstrainedLanguageSentenceDTO)dto).toString().endsWith("PostconditionSentence")){
							PostconditionSentenceDTO postcondSentence = (PostconditionSentenceDTO) dto;
							String text = postcondSentence.getSentenceText() != null ? postcondSentence.getSentenceText() : "";
							String res = postcondSentence.isSuccess() ? "[final: success] " + text:
																		"[final: failure] " + text;
							return res;
						}
						return ((ConstrainedLanguageSentenceDTO)dto).toString();
					}
					else if(title.equalsIgnoreCase("Sentence")){
						return ((ConstrainedLanguageSentenceDTO)dto).toString();
					}
					else if(title.equalsIgnoreCase("Name")){
						return ((UseCaseDTO)dto).getName().charAt(0)=='~'?((UseCaseDTO)dto).getName().substring(1):((UseCaseDTO)dto).getName();
					}
					else if(title.equalsIgnoreCase("Invoked by")){
						return MRecoveredScenario.getInvokedBy((UseCaseDTO)dto);
					}
					else if(title.equalsIgnoreCase("Invokes")){
						return MRecoveredScenario.getInvokes((UseCaseDTO)dto);
					}
					else if(title.equalsIgnoreCase("Test script name")){
						 return MRecoveredScenario.getTestScriptName((UseCaseDTO)dto);//((ConstrainedLanguageScenarioDTO)dto).getTestScriptName();
					}
					else if(title.equalsIgnoreCase("Test script file")){
						 return MRecoveredScenario.getTestScriptFile((UseCaseDTO)dto);//((ConstrainedLanguageScenarioDTO)dto).getTestScriptFile();
					}
					else if(title.equalsIgnoreCase("Scenario name")){
						if(dto instanceof XScenariosCommonPart){
							return ((XScenariosCommonPart)dto).getReferenceScenario().getName();
						}
					}
					else if(title.equalsIgnoreCase("Similar scenario name")){
						if(dto instanceof XScenariosCommonPart){
							return ((XScenariosCommonPart)dto).getScenario().getName();
						}
					}
					else if(title.equalsIgnoreCase("Longest common part")){
						if(dto instanceof XScenariosCommonPart){
							return Integer.toString(((XScenariosCommonPart)dto).getCommonSentenceNumber());
						}
					}
					return null;
				}
			});
			layout.setColumnData(col.getColumn(), new ColumnWeightData(30));
		}
	}

	private TableViewerColumn createTableViewerColumn(String title, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setResizable(false);
		column.setMoveable(true);
		return viewerColumn;
	}
}
