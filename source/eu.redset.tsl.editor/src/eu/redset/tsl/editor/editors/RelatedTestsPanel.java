package eu.redset.tsl.editor.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

import eu.redseeds.common.SCProjectHelper;
import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestRelationship;
import eu.redset.tsl.editors.actions.TestDetailsEditorOpenAction;

public class RelatedTestsPanel extends Composite{

	private Composite parentComposite;
	private Test parentTest;
	private Link testNameLink;
	private Label relatedTestsLabel;
	private Button addNotionTestButton;
	private RelatedTestsPanel thisComposite;
	private List linkList;
	
	public RelatedTestsPanel(Composite parent, Test parentTest, int style) {
		super(parent, style);
		this.parentComposite = parent;
		this.parentTest = parentTest;
		this.thisComposite = this;
		linkList = new ArrayList();
		createContent();
	}

	public void refresh(){
		if (parentTest instanceof SVOSentence){
			SVOSentence svo = (SVOSentence)parentTest;
			for (int i=0; i<svo.getPredicate().getSource().size(); i++){
				Object o1 = ((TestRelationship)svo.getPredicate().getSource().get(i)).getTarget();
				boolean exist = false;
				for (Object o2 : linkList){
					if (o1.equals(o2))
						exist = true;
				}
				if (!exist)
					addTestLabel((Test)o1, i);
			}
			
		} else {
			for (int i=0; i<parentTest.getSource().size(); i++){
				Object o1 = ((TestRelationship)parentTest.getSource().get(i)).getTarget();
				boolean exist = false;
				for (Object o2 : linkList){
					if (o1.equals(o2))
						exist = true;
				}
				if (!exist)
					addTestLabel((Test)o1, i);
			}
		}
	}
	
	public void createContent(){
		RowLayout layout = new RowLayout();
		layout.wrap = true;
		layout.type = SWT.HORIZONTAL;
		layout.center = true;
		setLayout(layout);
		if (parentTest instanceof SVOSentence)
			this.setBackground(new Color(null, 255, 255, 255));
		
		addNotionTestButton = new Button(this, SWT.NONE);
		addNotionTestButton.setText("Add Test");
		
		SelectionListener addTestToNotionListener = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddTestDialog addTestToNotionDialog = new AddTestDialog(SCProjectHelper.getShell(),parentTest, thisComposite);
				addTestToNotionDialog.open();
				//addTestToNotionDialog.setOkButtonDisabled();
				layout();
				parentComposite.layout();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		addNotionTestButton.addSelectionListener(addTestToNotionListener);
		
		relatedTestsLabel = new Label(this, SWT.NONE);
		relatedTestsLabel.setText("Related tests: ");
		if (parentTest instanceof SVOSentence)
			relatedTestsLabel.setBackground(new Color(null, 255, 255, 255));

		if (parentTest instanceof SVOSentence){
			List relTests = ((SVOSentence) parentTest).getPredicate().getSource();
			for (int i = 0; i<relTests.size(); i++){
				final TestRelationship testRelation = (TestRelationship)relTests.get(i);
			
				addTestLabel(testRelation.getTarget(), i);
			}
		} else {
		List relTests = parentTest.getSource();
		for (int i = 0; i<relTests.size(); i++){
			final TestRelationship testRelation = (TestRelationship)relTests.get(i);
			
			addTestLabel(testRelation.getTarget(), i);
		}
		}
	}
	
	private void addTestLabel(final Test t, int position){
		testNameLink = new Link(this, SWT.NONE);
		linkList.add(t);
		//testNameLink.setText(t.getName());
		if (position==0)
			testNameLink.setText("<A>"+t.getName()+"</A>");
		else 
			testNameLink.setText(", <A>"+t.getName()+"</A>");
		if (parentTest instanceof SVOSentence)
			testNameLink.setBackground(new Color(null, 255, 255, 255));
		FontData[] fontData = testNameLink.getFont().getFontData();
		fontData[0].setHeight(8);
		fontData[0].setStyle(SWT.BOLD | SWT.UNDERLINE_LINK);
		testNameLink.setFont( new Font(this.getDisplay(),fontData[0]));
		testNameLink.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				TestDetailsEditorOpenAction propertiesOpenAction = new TestDetailsEditorOpenAction();
				propertiesOpenAction.setTest(t);
				propertiesOpenAction.run();
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
