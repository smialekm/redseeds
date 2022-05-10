package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TextChangeListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.ISentenceProvider;

public abstract class GenericSentenceWidgetAbstract extends Composite implements IGenericSentenceMarkerProvider{
	public static void log(String msg, int status) {
		System.out.println("["+status+"]"+msg);
	}
	private GenericSentenceContent marker;

	private Menu popupMenu;

	public GenericSentenceWidgetAbstract(Composite parent, int style,ISentenceProvider contentProvider) {
		super(parent, style);
		this.setBackground(new Color(null, 255, 255, 255));
		marker = new GenericSentenceContent(contentProvider);

		FillLayout fillLayout = new FillLayout();

		fillLayout.type = SWT.VERTICAL;
		fillLayout.marginHeight = 2;

		setLayout(fillLayout);
		addTextChangeListenerToMarker();

		//init text view compoments
		initialize();

		initPopupMenu();

		resetStyleRanges();
	}

	public void addKeyPressedListenener(KeyListener l) {
		getStyledText().addKeyListener(l);
	}
	public void addModifyListener(ModifyListener l) {
		getStyledText().addModifyListener(l);
	}

	@Override
	public void addMouseListener(MouseListener l) {
		getStyledText().addMouseListener(l);
	}

	private void addTextChangeListenerToMarker(){
		getMarker().addTextChangeListener(new TextChangeListener() {
			public void textChanged(
					org.eclipse.swt.custom.TextChangedEvent event) {
				resetStyleRanges();
			}

			public void textChanging(
					org.eclipse.swt.custom.TextChangingEvent event) {
			}

			public void textSet(org.eclipse.swt.custom.TextChangedEvent event) {

			}

		});
	}
	public void focus() {
		getStyledText().forceFocus();
	}

	public GenericSentenceContent getMarker() {
		return marker;
	}

	public int getOffsetAtLocation(Point p ){
		try{
		return getStyledText().getOffsetAtLocation(p);
		} catch (IllegalArgumentException e){

		}
		return -1;
	}

	protected int getSelectionEnd() {
		int position = getStyledText().getText().indexOf(' ',
				getStyledText().getCaretOffset());
		return position != -1 ? position : getStyledText().getText().length();
	}

	protected abstract StyledText getStyledText();

	public boolean hasFocusIncChildren() {
		for (Control c : this.getChildren())
			if (c.isFocusControl())
				return true;
		return false;
	}

	protected abstract void initialize();

	private void initPopupMenu(){
		popupMenu = new Menu(getStyledText());
		getStyledText().setMenu(popupMenu);
		popupMenu.addListener(SWT.Show, new Listener() {
			public void handleEvent(Event event) {
				// Get rid of existing menu items
				MenuItem[] items = popupMenu.getItems();
				for (int i = 0; i < items.length; i++) {
					items[i].dispose();
				}
				getStyledText().setSelection(getMarker().getLastMarkPosition(),
						getSelectionEnd());
				// Add menu items for current selection
				for (Map.Entry<Object, String> stateEntry : getMarker()
						.getNextSates().entrySet())
					if (stateEntry.getValue() != null)
						prepareMenuItem(stateEntry.getKey(), stateEntry
								.getValue());
			}
		});
	}

	private MenuItem prepareMenuItem(final Object state, String stateName) {
		MenuItem newItem = new MenuItem(popupMenu, SWT.NONE);
		newItem.setText(stateName);
		newItem.setData(state);
		newItem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (getStyledText().getSelectionCount() > 0
						&& getStyledText().getCaretOffset() > getMarker()
								.getLastMarkPosition()) {
					getMarker().mark(state, getStyledText().getSelectionCount());
					resetStyleRanges();
					getStyledText()
							.setSelectionRange(getMarker().getLastMarkPosition(), 0);
				}
			}
		});
		return newItem;
	}

	public void removeKeyPressedListenener(KeyListener l) {
		getStyledText().removeKeyListener(l);
	}

	public void removeModifyListener(ModifyListener l) {
		getStyledText().removeModifyListener(l);
	}

	protected void resetStyleRanges() {
		int start = 0;
		for (Map.Entry<Integer, StyleRange> styleOffset : getMarker()
				.getStyleRangesOffsets().entrySet()) {
			StyleRange style = styleOffset.getValue();
			style.start = start;
			style.length = styleOffset.getKey().intValue() - start;
			setStyleRange(style);
			start = styleOffset.getKey().intValue();
		}

	}

	protected void setStyleRange(StyleRange style){
		getStyledText().setStyleRange(style);
	}

	public void setEditable(boolean flag){
		this.getStyledText().setEditable(flag);
		if(flag)
			getStyledText().setMenu(popupMenu);
		else
			getStyledText().setMenu(null);
	}
	public void setInnerBackground(Color c) {
		getStyledText().setBackground(c);
		this.setBackground(c);
	}

	public  void disableFields(boolean flag){
		this.setEditable(!flag);
	}

	public abstract void removeSelection();
}
