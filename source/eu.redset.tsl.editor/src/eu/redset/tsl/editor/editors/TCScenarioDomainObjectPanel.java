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

import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.tsl.editors.actions.TestDetailsEditorOpenAction;

public class TCScenarioDomainObjectPanel extends Composite{

	private DomainObject domainObject;	
	private TCRelatedTestsPanel relatedTestPanel;
	private Link notionLink;
	

	public TCScenarioDomainObjectPanel(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}
	
	public void createContent(){
		this.setLayout(new FormLayout());
		FormData fd = new FormData();
		
		notionLink = new Link(this, SWT.BOLD);
		notionLink.setText("<A>"+domainObject.getName()+"</A>");
		//notionLabel = new Label(this, SWT.BOLD);
		//notionLabel.setText(domainObject.getName());
		//notionLabel.setBackground(new Color(null, 255, 255, 255));
		FontData[] fontData = notionLink.getFont().getFontData();
		fontData[0].setHeight(8);
		fontData[0].setStyle(SWT.BOLD);
		notionLink.setFont( new Font(this.getDisplay(),fontData[0]));
		fd = new FormData();
		fd.top = new FormAttachment(0, 10);
		fd.left = new FormAttachment(0, 0);
		//fd.right = new FormAttachment(100, 0);
		notionLink.setLayoutData(fd);
		
		relatedTestPanel = new TCRelatedTestsPanel(this, domainObject, SWT.NONE);
		fd = new FormData();
		fd.top = new FormAttachment(notionLink, 10);
		fd.left = new FormAttachment(0, 0);
		relatedTestPanel.setLayoutData(fd);
		
		notionLink.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				TestDetailsEditorOpenAction propertiesOpenAction = new TestDetailsEditorOpenAction();
				propertiesOpenAction.setTest(domainObject);
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

	public DomainObject getDomainObject() {
		return domainObject;
	}

	public void setDomainObject(DomainObject domainObject) {
		this.domainObject = domainObject;
	}
	



}
