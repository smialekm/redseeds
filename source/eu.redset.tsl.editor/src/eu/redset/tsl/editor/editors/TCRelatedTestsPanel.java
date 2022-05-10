package eu.redset.tsl.editor.editors;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.TestInstance;
import eu.redset.emf.model.tsl.TestRelationship;
import eu.redset.tsl.editors.actions.TestDetailsEditorOpenAction;

public class TCRelatedTestsPanel extends Composite{

	private Composite parentComposite;
	private TestInstance parentTest;
	private Link testNameLink;
	private Label relatedTestsLabel;
	
	public TCRelatedTestsPanel(Composite parent, TestInstance parentTest, int style) {
		super(parent, style);
		this.parentComposite = parent;
		this.parentTest = parentTest;
		createContent();
	}

	public void refresh(){
		this.parentComposite.layout();
	}
	
	public void createContent(){
		RowLayout layout = new RowLayout();
		layout.wrap = true;
		layout.type = SWT.HORIZONTAL;
		layout.center = true;
		setLayout(layout);
		if (parentTest instanceof SVOSentence)
			this.setBackground(new Color(null, 255, 255, 255));
		
		relatedTestsLabel = new Label(this, SWT.NONE);
		relatedTestsLabel.setText("Related tests: ");
		if (parentTest instanceof SVOSentence)
			relatedTestsLabel.setBackground(new Color(null, 255, 255, 255));

		List relTests = parentTest.getAttachedTests();
		for (int i = 0; i<relTests.size(); i++){
			final TestInstance ti = (TestInstance)relTests.get(i);
			
			testNameLink = new Link(this, SWT.NONE);
			//testNameLink.setText(ti.getName());
			if (i==0)
				testNameLink.setText("<A>"+ti.getName()+"</A>");
			else 
				testNameLink.setText(", <A>"+ti.getName()+"</A>");
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
					propertiesOpenAction.setTest(ti);
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
}
