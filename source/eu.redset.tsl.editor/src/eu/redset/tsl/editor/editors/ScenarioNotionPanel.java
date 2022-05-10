package eu.redset.tsl.editor.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;


import eu.redset.emf.model.tsl.Notion;
import eu.redset.tsl.editors.actions.TestDetailsEditorOpenAction;

public class ScenarioNotionPanel extends Composite{

	private Notion notion;
	private Link notionLink;
	private RelatedTestsPanel relatedTestPanel;
	

	public ScenarioNotionPanel(Composite parent, int style) {
		super(parent, style);
	}
	
	public void createContent(){
		this.setLayout(new FormLayout());
		FormData fd = new FormData();
		
		notionLink = new Link(this, SWT.BOLD);
		notionLink.setText("<A>"+notion.getNotionName()+"</A>");
		//notionLink.setBackground(new Color(null, 255, 255, 255));
		FontData[] fontData = notionLink.getFont().getFontData();
		fontData[0].setHeight(8);
		fontData[0].setStyle(SWT.BOLD);
		notionLink.setFont( new Font(this.getDisplay(),fontData[0]));
		fd = new FormData();
		fd.top = new FormAttachment(0, 10);
		fd.left = new FormAttachment(0, 0);
		//fd.right = new FormAttachment(100, 0);
		notionLink.setLayoutData(fd);
		
		relatedTestPanel = new RelatedTestsPanel(this, notion, SWT.NONE);
		fd = new FormData();
		fd.top = new FormAttachment(notionLink, 10);
		fd.left = new FormAttachment(0, 0);
		relatedTestPanel.setLayoutData(fd);
		
		notionLink.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				TestDetailsEditorOpenAction propertiesOpenAction = new TestDetailsEditorOpenAction();
				propertiesOpenAction.setTest(notion);
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
	

	public Notion getNotion() {
		return notion;
	}

	public void setNotion(Notion notion) {
		this.notion = notion;
	}

}
