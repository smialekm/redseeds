package eu.redseeds.sc.editor.rsl.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;


public class TermEditorControl extends Composite {

	private RemoteJGWNL remoteJGWNL;
	private TermEditor editor;
	
	private Label termsLabel;
	private Text filterText;
	private Label senseLabel;
	private Button searchButton;
	private Button addWordButton;
	private Button addSenseButton;
	private Button addSynonymButton;
	private Button delSenseButton;
	private Tree senseTree;
	private TreeViewer senseTreeViewer;
	
	
	public TermEditorControl(Composite parent, TermEditor editor) {
		super(parent, SWT.NONE);
		remoteJGWNL = RemoteJGWNL.getInstance();
		this.editor = editor;
		createContent();
	}

	private void createContent() {
		
		// Set layout
		
		FormLayout layout = new FormLayout();
		layout.marginWidth = 40;
		layout.marginHeight = 20;
		setLayout(layout);

		// Create controls
		
		termsLabel = new Label(this, SWT.NONE);
		termsLabel.setText("Word to search for:");
		FormData fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, 0);
		termsLabel.setLayoutData(fd);
		
		filterText = new Text(this, SWT.SINGLE | SWT.BORDER);
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd. left = new FormAttachment(termsLabel, 10);
		fd.right = new FormAttachment(50, 0);
		filterText.setLayoutData(fd);
		
		searchButton = new Button(this, SWT.NONE);
		searchButton.setText("Search");
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(filterText, 10);
		searchButton.setLayoutData(fd);
		
		senseLabel = new Label(this, SWT.NONE);
		senseLabel.setText("Word senses:");
		fd = new FormData();
		fd.top = new FormAttachment(termsLabel, 20);
		fd.left = new FormAttachment(0, 0);
		senseLabel.setLayoutData(fd);
				
		senseTree = new Tree(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		senseTree.setHeaderVisible(true);
		//senseTree.setLinesVisible(true);
		
		fd = new FormData();
		fd.top = new FormAttachment(senseLabel, 5);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(85, 0);
		senseTree.setLayoutData(fd);
		
		TreeColumn column = new TreeColumn(senseTree, SWT.LEFT, 0);
		column.setWidth(150);
		column.setText("Basic form");
		column.setToolTipText("Basic Form");
		
		column = new TreeColumn(senseTree, SWT.LEFT, 1);
		column.setWidth(70);
		column.setText("Part of speech");
		column.setToolTipText("Part of speech");
		
		column = new TreeColumn(senseTree, SWT.LEFT, 2);
		column.setWidth(50);
		column.setText("Is WordNet entry");
		column.setToolTipText("Is WordNet entry");
		
		column = new TreeColumn(senseTree, SWT.LEFT, 3);
		column.setWidth(1000);
		column.setText("Sense");
		column.setToolTipText("Sense");
		
		senseTreeViewer = new TreeViewer(senseTree);
		senseTreeViewer.setUseHashlookup(true);
		senseTreeViewer.setContentProvider(new TermSensesContentProvider());
		senseTreeViewer.setLabelProvider(new TermSensesLabelProvider());
		
		addWordButton = new Button(this, SWT.NONE);
		addWordButton.setText("Add new word");
		addWordButton.setToolTipText("Adds a new word");
		fd = new FormData();
		fd.top = new FormAttachment(senseTree, 15);
		fd.left = new FormAttachment(0, 0);
		addWordButton.setLayoutData(fd);
		
		addSenseButton = new Button(this, SWT.NONE);
		addSenseButton.setText("Add new sense");
		addSenseButton.setToolTipText("Adds a new sense to the current word");
		fd = new FormData();
		fd.top = new FormAttachment(senseTree, 15);
		fd.left = new FormAttachment(addWordButton, 5);
		addSenseButton.setLayoutData(fd);
		addSenseButton.setEnabled(false);
		
		addSynonymButton = new Button(this, SWT.NONE);
		addSynonymButton.setText("Add synonym");
		addSynonymButton.setToolTipText("Adds a new synonym to the selected sense");
		fd = new FormData();
		fd.top = new FormAttachment(senseTree, 15);
		fd.left = new FormAttachment(addSenseButton, 5);
		addSynonymButton.setLayoutData(fd);
		addSynonymButton.setEnabled(false);
		
		delSenseButton = new Button(this, SWT.NONE);
		delSenseButton.setText("Delete");
		delSenseButton.setToolTipText("Deletes chosen sense or synonym");
		fd = new FormData();
		fd.top = new FormAttachment(senseTree, 15);
		fd.left = new FormAttachment(addSynonymButton, 5);
		delSenseButton.setLayoutData(fd);
		delSenseButton.setEnabled(false);
		
		
		// Event handling
		
		filterText.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == '\r') {
					senseTreeViewer.setInput(filterText.getText());
					setTableAppearance();
					enableButtons();
				}					
			}
		});
		
		
		searchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				senseTreeViewer.setInput(filterText.getText());
				setTableAppearance();
				enableButtons();
			}
		});
		
		
		addSenseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = senseTree.getItems();
				List<TermSenseDTO> senses = new ArrayList<TermSenseDTO>();
				for (TreeItem item : items) {
					if (item.getData() instanceof TermSenseDTO)
						senses.add((TermSenseDTO)item.getData());
				}
				AddSenseDialog addSenseDlg = new AddSenseDialog();
				addSenseDlg.setSenses(senses);
				if (addSenseDlg.open() == SWT.OK) {
					editor.setDirty(true);
					senseTreeViewer.setInput(filterText.getText());
					setTableAppearance();
					enableButtons();
				}
			}
		});
		
		
		addWordButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddWordDialog addWordDlg = new AddWordDialog();
				if (addWordDlg.open() == SWT.OK) {
					editor.setDirty(true);
					filterText.setText(addWordDlg.getWordAdded());
					senseTreeViewer.setInput(filterText.getText());
					setTableAppearance();
					enableButtons();
				}
			}
		});
		
		addSynonymButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = senseTree.getSelection()[0];
				if (item.getParentItem() != null )
					return;
				if (!(item.getData() instanceof TermSenseDTO))
					return;
				TermSenseDTO sense = (TermSenseDTO) item.getData();
				AddSynonymDialog addSynonymDlg = new AddSynonymDialog(sense);
				if (addSynonymDlg.open() == SWT.OK) {
					editor.setDirty(true);
					senseTreeViewer.setInput(filterText.getText());
					setTableAppearance();
					enableButtons();
				}
			}
		});
		
		delSenseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = senseTree.getSelection();
				for (Item i : items) {
					Object sense = i.getData();
					if (sense instanceof TermSenseDTO)
						remoteJGWNL.deleteSense((TermSenseDTO)sense);
				}
				senseTreeViewer.setInput(filterText.getText());
				setTableAppearance();
				enableButtons();
				editor.setDirty(true);
			}
		});
		
		senseTree.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				TreeItem item = senseTree.getSelection()[0];
				if (item.getParentItem() == null )
					return;
				if (!(item.getData() instanceof TermSenseDTO))
					return;
				TermSenseDTO sense = (TermSenseDTO) item.getData();
				filterText.setText(sense.getLemma());
				senseTreeViewer.setInput(filterText.getText());
				setTableAppearance();
				enableButtons();
			}
		});
		
		senseTree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = senseTree.getSelection()[0];
				if (item.getParentItem() != null )
					addSynonymButton.setEnabled(false);
				else
					addSynonymButton.setEnabled(true);
				if (senseTree.getSelectionCount() < 1) {
					delSenseButton.setEnabled(false);
					return;
				}	
				if (item.getData() instanceof TermSenseDTO) {
					TermSenseDTO sense = (TermSenseDTO)item.getData();
					if (sense.isWordNetEntry())
						delSenseButton.setEnabled(false);
					else
						delSenseButton.setEnabled(true);
				} else {
					delSenseButton.setEnabled(false);
				}
			}
		});
		
	}
	

	private void enableButtons() {
		if (senseTree.getItemCount() > 0)
			addSenseButton.setEnabled(true);
		else
			addSenseButton.setEnabled(false);
		if (senseTree.getSelectionCount() < 1) {
			delSenseButton.setEnabled(false);
			addSynonymButton.setEnabled(false);
		} else {
			delSenseButton.setEnabled(true);
			addSynonymButton.setEnabled(true);
		}
	}

	
	private void setTableAppearance() {
		senseTreeViewer.expandAll();
		for (TreeItem item : senseTree.getItems()) {
			if (senseTree.indexOf(item) % 2 == 0) {
				for (int i = 0; i < senseTree.getColumnCount(); i++) {
					item.setBackground(i, new Color(null, 235, 235, 235));
				}
				for (TreeItem child : item.getItems()) {
					for (int i = 0; i < senseTree.getColumnCount(); i++)
						child.setBackground(i, new Color(null, 235, 235, 235));
				}
			}
			for (TreeItem child : item.getItems())
				child.setText(senseTree.getColumnCount() - 1 , "");
		}
		senseTreeViewer.collapseAll();
	}
	
	
	public boolean save() {
		return remoteJGWNL.saveRemoteDictionary(true);
	}
	
	
	class TermSensesContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof TermSenseDTO) {
				List<TermSenseDTO> siList = ((TermSenseDTO)parentElement).getSynonyms();
				if (siList != null) {
					return siList.toArray();
				}
			}
			return null;
		}

		@Override
		public Object getParent(Object element) {
			if (element instanceof TermSenseDTO) {
				return ((TermSenseDTO)element).getParent();
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof TermSenseDTO) {
				if (((TermSenseDTO)element).getSynonyms() != null)
					return true;
			}				
			return false;
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (!(inputElement instanceof String)) {
				return null;
			}
			String inputString = (String) inputElement;
			TermSenseDTO[] senses = remoteJGWNL.getTermSenses(inputString, null);
			return senses;
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		


	}
	
	
	class TermSensesLabelProvider extends LabelProvider implements
	ITableLabelProvider {


		public TermSensesLabelProvider() {
			super();
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			String result = "";
			if(!(element instanceof TermSenseDTO)) {
				return result;
			}
			switch (columnIndex) {
			case 0 :
				result = ((TermSenseDTO)element).getLemma();
				break;
			case 1:
				result = ((TermSenseDTO)element).getType();
				break;
			case 2:
				if (((TermSenseDTO)element).isWordNetEntry())
					result = "yes";
				else
					result = "no";
				break;
			case 3:
				result = ((TermSenseDTO)element).getSense();
				break;
			default:
				result = "";
				break;
			}
			return result;
		}
	}


}
