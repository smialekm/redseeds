package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewPart;

import eu.redseeds.engine.navigator.providers.OpenAction;
import eu.redseeds.sc.query.engine.SimilarSCProject;
import eu.redseeds.sc.query.ui.view.ISimpleSortAbleView;
import eu.redseeds.sc.query.ui.view.SCSimilarityResultView;
import eu.redseeds.scl.model.rsl.IHierarchyAware;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

/**
 * Content for view with result of similarity query.
 *
 * @see SCSimilarityResultView
 */
public class SCSimilarityResultComposite extends Composite implements ISimpleSortAbleView {

	private final class SCSimilarityResultTreeViewerChangedListener implements ISelectionChangedListener {
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			TreeSelection treeSelection = (TreeSelection) event.getSelection();
			TreePath[] treePaths = treeSelection.getPaths();

			//enable 4TV action if all is ok
			checkForFourTreeViewAction(treePaths);
			// Enable diff scenarios if all is ok
			checkForSceariosDiffAction(treePaths);
		}

		private void checkForSceariosDiffAction(TreePath[] treePaths) {
			openDiffViewAction.setEnabled(false);
			if (treePaths!=null && treePaths.length == 2) {
				TreePath treePath1 = treePaths[0];
				TreePath treePath2 = treePaths[1];

				IElementMatch elementMatch = null;
				if( treePath1.getLastSegment() instanceof IElementMatch){
					elementMatch = (IElementMatch)  treePath1.getLastSegment();
				}
				if(treePath2.getLastSegment() instanceof IElementMatch){
					elementMatch = (IElementMatch) treePath2.getLastSegment();
				}

				int idx1 = treePath1.getSegmentCount() - 1;
				int idx2 = treePath2.getSegmentCount() - 1;
				if(treePath1.getSegmentCount()>idx1 && treePath2.getSegmentCount()>idx2){
					if ( (treePath1.getSegment(idx1) instanceof LeafHierarchyAware || treePath2.getSegment(idx2) instanceof LeafHierarchyAware)
							&& elementMatch!=null) {

						IHierarchyAware candidateCurrElement = elementMatch.getCurrentElement();
						IHierarchyAware candidatePastElement = elementMatch.getPastElement();
						if(candidateCurrElement instanceof UseCaseDTO && candidatePastElement instanceof UseCaseDTO ){
							UseCaseDTO currUseCaseDTO=(UseCaseDTO) candidateCurrElement;
							UseCaseDTO pastUseCaseDTO=(UseCaseDTO) candidatePastElement;
							openDiffViewAction.setInput(currUseCaseDTO,  pastUseCaseDTO,
									similarSCProjectPathParser.getCurentPathAsString(currUseCaseDTO),
									similarSCProjectPathParser.getPastPathAsString(pastUseCaseDTO));
							openDiffViewAction.setEnabled(true);
						}
					}
				}
			}
		}

		private void checkForFourTreeViewAction(TreePath[] treePaths) {
			fourViewAction.setEnabled(false);
			if (treePaths!=null && treePaths.length == 1) {
				TreePath modyfTreePath = treePaths[0];
				if (modyfTreePath.getLastSegment() instanceof LeafHierarchyAware) {
					LeafHierarchyAware leafHierarchyAware=(LeafHierarchyAware) modyfTreePath.getLastSegment();
					modyfTreePath = normalizeLeafTreePath(leafHierarchyAware);
				}
				TreeSelection modyfTreeSelection = new TreeSelection(modyfTreePath);
				fourViewAction.setTreeSelection(modyfTreeSelection);
				fourViewAction.setEnabled(true);
			}
		}

		private TreePath normalizeLeafTreePath(LeafHierarchyAware leafHierarchyAware) {
			List<Object> segmentsAsList=new ArrayList<Object>();
			if(similarSCProjectPathParser.contains(leafHierarchyAware.getElementMatch())){
				segmentsAsList=Arrays.asList(similarSCProjectPathParser.getSoftwareCaseDTOForPastRequirement(),
						leafHierarchyAware);
			}
			if(domainElementPathParser.contains(leafHierarchyAware.getElementMatch())){
				segmentsAsList=Arrays.asList(domainElementPathParser.getSoftwareCaseDTOForPastRequirement(),
						leafHierarchyAware);
			}
			TreePath newTreePath = new TreePath(segmentsAsList.toArray());
			return newTreePath;
		}
	}

	static final String EU_REDSEEDS_ENGINE = eu.redseeds.engine.Activator.PLUGIN_ID;

	private SimilarSCProject input;

	private TreeViewer viewer;

	private FourViewAction fourViewAction= new FourViewAction();

	private OpenDiffViewAction openDiffViewAction= new OpenDiffViewAction();

	private Composite parent;

	private IViewPart viewPart;

	private TreeViewerColumn mainTreeColumn;
	private TreeViewerColumn columnSimilarityValue;

	private ISimilarGenericElementPathParser similarSCProjectPathParser;
	private ISimilarGenericElementPathParser domainElementPathParser;

	private enum SortType{UP,DOWN;
	private static final Map<SortType,SortType> sortTypeToInverse=new EnumMap<SortType, SortType>(SortType.class);
		static{
			sortTypeToInverse.put(UP, DOWN);
			sortTypeToInverse.put(DOWN, UP);
		}

		public SortType getInverseSortType() {
			return sortTypeToInverse.get(this);
		}
	}
	private Map<TreeColumn,SortType> tableColumnSortType=new HashMap<TreeColumn, SortType>();

	public SCSimilarityResultComposite(Composite parent, IViewPart viewPart) {
		super(parent, SWT.NULL);
		populateControl();
		this.parent = parent;
		this.viewPart = viewPart;
	}

	/**
	 * add fourViewAction
	 */
	private void addContextMenuAction() {
		fourViewAction.setEnabled(false);
		openDiffViewAction.setEnabled(false);

		MenuManager popupMenu = new MenuManager();
		popupMenu.add(fourViewAction);
		popupMenu.add(openDiffViewAction);
		Menu menu = popupMenu.createContextMenu(viewer.getTree());
		viewer.getTree().setMenu(menu);
	}

	/**
	 * Double click to open use case editor
	 */
	private void addDoubleClickListener() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(final DoubleClickEvent event) {
				if (input != null) {
					TreeSelection treeSelection = (TreeSelection) viewer.getSelection();
					if(treeSelection.getFirstElement() instanceof IElementMatch || treeSelection.getFirstElement() instanceof LeafHierarchyAware){
						boolean isCurrentPrj = isCurrentProjectPath(treeSelection.getPaths()[0]);
						IProject project = isCurrentPrj ? input.getCurrentScProject().getEclipseProject() : input.getScProject().getEclipseProject();
						SCSimilarityTreeSelectionProviderAdapter similarityTreeSelectionProviderAdapter = new SCSimilarityTreeSelectionProviderAdapter(viewer);
						OpenAction openAction = OpenAction.newInstanceGetSelectionToEditFromSelectionProvider(viewPart, similarityTreeSelectionProviderAdapter,
								project);
						openAction.run();
					}
				}
			}
		});
	}

	private void addMainTreeColumn() {
		mainTreeColumn = new TreeViewerColumn(viewer, SWT.LEFT);
		mainTreeColumn.getColumn().setText("Name");
		similarSCProjectPathParser = new SimilarUseCasePathParser(input);
		domainElementPathParser=new SimilarDomainElementPathParser(input);
		mainTreeColumn.setLabelProvider(new SCSimilarityMainTreeColumnLabelProvider(similarSCProjectPathParser,domainElementPathParser));
		addSortListenerAlphabetic(viewer,mainTreeColumn.getColumn());
	}

	private void addSelectionChangedListener() {
		viewer.addSelectionChangedListener(new SCSimilarityResultTreeViewerChangedListener());
	}

	/**
	 * Column with similarity level
	 */
	private void addSimilarityValueTreeColumn() {
		columnSimilarityValue = new TreeViewerColumn(viewer, SWT.LEFT);
		columnSimilarityValue.getColumn().setText("Similarity value");
		columnSimilarityValue.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				String result = null;
				if (element instanceof LeafHierarchyAware) {
					LeafHierarchyAware leafHierarchyAware=(LeafHierarchyAware) element;
					IElementMatch elementMatch = leafHierarchyAware.getElementMatch();
					int percent = (int) (elementMatch.getSimilarityValue() * 100);
					result = percent + "%";
				}
				return result;
			}

		});
		// this is workaround, i don't know how to do this in getImage from
		// JFace (ColumnLabelProvider)
		viewer.getTree().addListener(SWT.PaintItem, new Listener() {
			public void handleEvent(Event event) {
				if (event.index == 1) {
					TreeItem item = (TreeItem) event.item;
					int percent=-1;
					if (item.getData() instanceof LeafHierarchyAware) {
						LeafHierarchyAware leafHierarchyAware=(LeafHierarchyAware) item.getData();
						IElementMatch elementMatch = leafHierarchyAware.getElementMatch();
						percent = (int) (elementMatch.getSimilarityValue() * 100);
					}
					if(percent!=-1){
						int width = (columnSimilarityValue.getColumn().getWidth() - 1) * percent / 100;
						GC gc = event.gc;
						Color foreground = gc.getForeground();
						Color background = gc.getBackground();
						Display display2 = parent.getDisplay();
						gc.setForeground(display2.getSystemColor(SWT.COLOR_GREEN));
						gc.fillGradientRectangle(event.x, event.y, width, event.height, true);
						Rectangle rect2 = new Rectangle(event.x, event.y, width - 1, event.height - 1);
						gc.drawRectangle(rect2);
						gc.setForeground(display2.getSystemColor(SWT.COLOR_LIST_FOREGROUND));
						String text = percent + "%";
						Point size = event.gc.textExtent(text);
						int offset = Math.max(0, (event.height - size.y) / 2);
						gc.drawText(text, event.x + 2, event.y + offset, true);
						gc.setForeground(background);
						gc.setBackground(foreground);
					}
				}
			}
		});

		addSortListenerValue(viewer,columnSimilarityValue.getColumn());
	}
	private interface IViewerSorterProvider{
		public ViewerSorter getViewerSorter(SortType sortType);
	}
	private void addSortListenerStrategy(final TreeViewer treeViewer,final TreeColumn forColumn,final IViewerSorterProvider viewerSorterProvider){
		Listener sortListener = new Listener() {
			public void handleEvent(Event e) {
					TreeColumn column = (TreeColumn)e.widget;
	            	if(!tableColumnSortType.containsKey(column)){
	            		tableColumnSortType.put(column, SortType.DOWN);
	            	}
		            SortType sortType = tableColumnSortType.get(column);
		            ViewerSorter sorter=viewerSorterProvider.getViewerSorter(sortType);
		            treeViewer.setSorter(sorter);
		            if(tableColumnSortType.containsKey(column)){
		            	tableColumnSortType.put(column, sortType.getInverseSortType());
		            }
	            }
	    };
	    forColumn.addListener(SWT.Selection, sortListener);
	}
	private void addSortListenerAlphabetic(final TreeViewer treeViewer,final TreeColumn forColumn){
		addSortListenerStrategy(treeViewer,forColumn,new IViewerSorterProvider(){
			@Override
			public ViewerSorter getViewerSorter(SortType sortType) {
				 ViewerSorter sorter=null;
				 if(SortType.DOWN==sortType){
		            	sorter= OrderSimilaritNameSorterFactory.newAscendingSorter();
		         }
		         if(SortType.UP==sortType){
		          	sorter= OrderSimilaritNameSorterFactory.newDescendingSorter();
		         }
		         return sorter;
			}
		});
	}
	private void addSortListenerValue(final TreeViewer treeViewer,final TreeColumn forColumn){
	    addSortListenerStrategy(treeViewer,forColumn,new IViewerSorterProvider(){
			@Override
			public ViewerSorter getViewerSorter(SortType sortType) {
				 ViewerSorter sorter=null;
				 if(SortType.DOWN==sortType){
		            	sorter= OrderSimilaritValueSorterFactory.newAscendingSorter();
		         }
		         if(SortType.UP==sortType){
		          	sorter= OrderSimilaritValueSorterFactory.newDescendingSorter();
		         }
		         return sorter;
			}
		});
	}

	/**
	 * Add columns to tree
	 */
	private void addTabeNatureToTree() {
		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(33, true));
		layout.addColumnData(new ColumnWeightData(33, true));
		viewer.getTree().setLayout(layout);
		viewer.getTree().setLinesVisible(true);
		viewer.getTree().setHeaderVisible(true);

		addMainTreeColumn();
		addSimilarityValueTreeColumn();
	}

	/**
	 * Tree for result presentation
	 *
	 * @param style
	 */
	private void createTreeViewer(int style) {
		viewer = new TreeViewer(this, style);
		// viewer.setContentProvider(new SimilarSCProjectTreeContentProvider());
		viewer.setContentProvider(new SimilarSCProjectTreeContentProvider());
	}

	public SimilarSCProject getInput() {
		return input;
	}

	/**
	 * Check if selected element belongs to currentProject or to pastProject.
	 *
	 * @param treePath
	 *            selectiopn path
	 * @return true if belongs to currentProject
	 */
	private boolean isCurrentProjectPath(TreePath treePath) {
		boolean result = true;
		//if in path IElementMatch is present then we had selected past project
		boolean isElementMatchPresent = false;
		if (treePath != null) {
			for (int i = 0; i < treePath.getSegmentCount(); i++) {
				if (treePath.getSegment(i) instanceof IElementMatch) {
					isElementMatchPresent=true;
				}
			}
			result = !isElementMatchPresent;
		}
		return result;
	}

	protected void populateControl() {
		FillLayout compositeLayout = new FillLayout();
		setLayout(compositeLayout);
		createTreeViewer(SWT.MULTI | SWT.NONE);
		addTabeNatureToTree();
		addContextMenuAction();
		addSelectionChangedListener();
		addDoubleClickListener();
		refresh();
	}

	/**
	 * call it when input data change
	 */
	public void refresh() {
		viewer.setInput(getInput());
		similarSCProjectPathParser=new SimilarUseCasePathParser(input);
		domainElementPathParser=new SimilarDomainElementPathParser(input);
		if (mainTreeColumn != null) {
			mainTreeColumn.setLabelProvider(new SCSimilarityMainTreeColumnLabelProvider(similarSCProjectPathParser,domainElementPathParser));
		}
	}

	/**
	 * set new input data
	 *
	 * @param input
	 */
	public void setInput(SimilarSCProject input) {
		this.input = input;
		refresh();
	}

	@Override
	public void setViewerSorter(ViewerSorter viewerSorter) {
		viewer.setComparator(viewerSorter);
	}
}
