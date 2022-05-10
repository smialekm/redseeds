package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.List;

import org.eclipse.compare.CompareViewerPane;
import org.eclipse.compare.CompareViewerSwitchingPane;
import org.eclipse.compare.Splitter;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.ui.IViewPart;

public class ScenariosDiffViewComposite extends Composite {

	private static final String NONE = "  ----------------  \n";

	private enum DiffColor {
		SAME(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE), "same"),
		OTHER(new Color(null, 254, 254, 145), "other"),
		MISSING(new Color(null, 217, 243, 139), "missing"),
		ADDED(new Color(null, 255,204, 204), "added");
		private final Color color;
		private final String description;

		private DiffColor(Color color, String description) {
			this.color = color;
			this.description = description;
		}

		public Color getColor() {
			return color;
		}

		public String getDescription() {
			return description;
		}
	}

	private Composite parent;
	private IViewPart viewPart;

	private TextViewer currentTextViewer;
	private TextViewer pastTextViewer;

	private TextPresentation currentTextPresentation;
	private TextPresentation pastTextPresentation;

	private IScenariosDiffContentProvider scenariosDiffContentProvider;
	private IScenariosDiffStrategy scenariosDiffStrategy = ScenariosDiffStrategyFactory
			.getDefaultStrategy();
	private int commonLineNumber = 1;

	private CompareViewerSwitchingPane fStructureLeft;
	private CompareViewerSwitchingPane fStructureRight;

	public ScenariosDiffViewComposite(Composite parent, IViewPart viewPart) {
		super(parent, SWT.NULL);
		this.parent = parent;
		this.viewPart = viewPart;
		populateControl();
	}

	private void addDiffMarkedTextToTextViewers(
			IDiffScenariosResult diffScenariosResult) {

		currentTextPresentation = new TextPresentation();
		pastTextPresentation = new TextPresentation();

		int currCurosorIdx = 0;
		int pastCurosorIdx = 0;

		StringBuilder currDocBuilder = new StringBuilder();
		StringBuilder pastDocBuilder = new StringBuilder();
		List<IConstrainedLanguageSentenceMatch> sentencesMatchs = diffScenariosResult
				.getConstrainedLanguageSentenceMatchs();
		for (IConstrainedLanguageSentenceMatch sentencesMatch : sentencesMatchs) {
			IDiffMarkedConstrainedLanguageSentence currentDiffMarkedSVOSentence = sentencesMatch
					.getCurrentDiffMarkedConstrainedLanguageSentence();
			IDiffMarkedConstrainedLanguageSentence pastDiffMarkedSVOSentence = sentencesMatch
					.getPastDiffMarkedConstrainedLanguageSentence();

			// add line number
			int lineNumerPrefixLenght = prefixTextWithCommonLineNumber(
					currDocBuilder, pastDocBuilder);
			currCurosorIdx += lineNumerPrefixLenght;
			pastCurosorIdx += lineNumerPrefixLenght;

			if (sentencesMatch.isMissing()) {

				String currWordsAsStrng = currentDiffMarkedSVOSentence
						.getWordsAsStrng()
						+ "\n";

				currDocBuilder.append(currWordsAsStrng);
				pastDocBuilder.append(NONE);

				currCurosorIdx = colorCurrentTextPresentation(currCurosorIdx,
						currentDiffMarkedSVOSentence, currWordsAsStrng);
				pastCurosorIdx += NONE.length();
			}
			if (sentencesMatch.isAdded()) {

				String pastWordsAsStrng = pastDiffMarkedSVOSentence
						.getWordsAsStrng()
						+ "\n";

				currDocBuilder.append(NONE);
				pastDocBuilder.append(pastWordsAsStrng);

				pastCurosorIdx = colorPastTextPresentation(pastCurosorIdx,
						pastDiffMarkedSVOSentence, pastWordsAsStrng);
				currCurosorIdx += NONE.length();
			}
			if (sentencesMatch.isSimilar()) {

				String currWordsAsStrng = currentDiffMarkedSVOSentence
						.getWordsAsStrng()
						+ "\n";
				String pastWordsAsStrng = pastDiffMarkedSVOSentence
						.getWordsAsStrng()
						+ "\n";

				currDocBuilder.append(currWordsAsStrng);
				pastDocBuilder.append(pastWordsAsStrng);

				currCurosorIdx = colorCurrentTextPresentation(currCurosorIdx,
						currentDiffMarkedSVOSentence, currWordsAsStrng);
				pastCurosorIdx = colorPastTextPresentation(pastCurosorIdx,
						pastDiffMarkedSVOSentence, pastWordsAsStrng);

			}

		}
		fStructureLeft.setInput(new Document(currDocBuilder.toString()));
		fStructureRight.setInput(new Document(pastDocBuilder.toString()));

		setTextPresentation(fStructureLeft, currentTextPresentation);
		setTextPresentation(fStructureRight, pastTextPresentation);

		synchScrollBars();

		fStructureLeft.setText(scenariosDiffContentProvider.getCurrElmPath());
		fStructureRight.setText(scenariosDiffContentProvider.getPastElmPath());
	}

	private void setTextPresentation(
			CompareViewerSwitchingPane compareViewerSwitchingPane,
			TextPresentation textPresentation) {
		Viewer viewer = compareViewerSwitchingPane.getViewer();
		if (viewer instanceof TextViewer) {
			TextViewer textViewer = (TextViewer) viewer;
			textViewer.changeTextPresentation(textPresentation, true);
		}
	}

	private int colorCurrentTextPresentation(int currCurosorIdx,
			IDiffMarkedConstrainedLanguageSentence currentDiffMarkedSentence,
			String currWordsAsStrng) {
		colorText(currCurosorIdx, currentTextPresentation,
				currentDiffMarkedSentence);
		int newCurrCurosorIdx = currCurosorIdx + currWordsAsStrng.length();
		return newCurrCurosorIdx;
	}

	private int colorPastTextPresentation(int pastCurosorIdx,
			IDiffMarkedConstrainedLanguageSentence pastDiffMarkedSentence,
			String pastWordsAsStrng) {
		colorText(pastCurosorIdx, pastTextPresentation, pastDiffMarkedSentence);
		int newPastCurosorIdx = pastCurosorIdx + pastWordsAsStrng.length();
		return newPastCurosorIdx;
	}

	private void colorText(int from, TextPresentation textPresentation,
			IDiffMarkedConstrainedLanguageSentence diffMarkedSentence) {
		int localFrom = from;
		List<IDiffMarkedWord> diffMarkedWords = diffMarkedSentence
				.getDiffMarkedWords();
		if (diffMarkedWords != null) {
			for (IDiffMarkedWord diffMarkedWord : diffMarkedWords) {
				String word = diffMarkedWord.getWord();
				int localLenght = word.length();
				// don't color conditions prefixes
				List<String> allCodesAsList = ConditionPrefix
						.getAllCodesAsList();
				for (String prefixCode : allCodesAsList) {
					if (word.startsWith(prefixCode)) {
						localFrom += prefixCode.length();
						localLenght -= prefixCode.length();
						// mark conditions prefixes
						textPresentation.addStyleRange(new StyleRange(from,
								prefixCode.length(), null, null, SWT.BOLD));
					}
				}
				DiffType diffType = diffMarkedWord.getDiffType();
				textPresentation.addStyleRange(new StyleRange(localFrom,
						localLenght, null, getColorForDiffType(diffType)));
				localFrom += word.length();
				// skip space between words
				localFrom += 1;
			}
		}
	}

	private Color getColorForDiffType(DiffType diffType) {
		Color color;
		switch (diffType) {
		case MISSING:
			color = DiffColor.MISSING.getColor();
			break;
		case ADDED:
			color = DiffColor.ADDED.getColor();
			break;
		case OTHER:
			color = DiffColor.OTHER.getColor();
			break;
		default:
			color = DiffColor.SAME.getColor();
		}
		return color;
	}

	public IScenariosDiffContentProvider getScenariosDiffContentProvider() {
		return scenariosDiffContentProvider;
	}

	private void populateControl() {
		FillLayout compositeLayout = new FillLayout();
		setLayout(compositeLayout);

		Splitter splitter = new Splitter(this, SWT.VERTICAL);
		addLegend(splitter);
		Control outline = createOutlineContents(splitter, SWT.HORIZONTAL);

		splitter.setVisible(outline, true);
		splitter.setWeights(new int[] { 1, 10 });
		splitter.layout();
	}

	private void addLegend(Composite localParent) {

		Composite legendComp = new Composite(localParent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.numColumns = DiffColor.values().length;
		legendComp.setLayout(layout);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		for (DiffColor diffColor : DiffColor.values()) {
			Label label = new Label(legendComp, SWT.CENTER);
			label.setText(diffColor.getDescription());
			label.setBackground(diffColor.getColor());
			label.setLayoutData(gridData);
		}
	}

	private void synchScrollBars() {
		final StyledText currTextWidget = currentTextViewer.getTextWidget();
		final StyledText pastTextWidget = pastTextViewer.getTextWidget();
		final ScrollBar currVlBar = currTextWidget.getVerticalBar();
		final ScrollBar pastVBar = pastTextWidget.getVerticalBar();

		final ScrollBar currHlBar = currTextWidget.getHorizontalBar();
		final ScrollBar pastHBar = pastTextWidget.getHorizontalBar();
		currVlBar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pastVBar.setSelection(currVlBar.getSelection());
				if (e.item != pastVBar) {
					Event event = new Event();
					event.widget = pastVBar;
					event.item = currVlBar;
					event.display = e.display;
					pastVBar.notifyListeners(SWT.Selection, event);
				}
			}
		});
		pastVBar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				currVlBar.setSelection(pastVBar.getSelection());
				if (e.item != currVlBar) {
					Event event = new Event();
					event.widget = currVlBar;
					event.item = pastVBar;
					event.display = e.display;
					currVlBar.notifyListeners(SWT.Selection, event);
				}
			}
		});
		currHlBar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pastHBar.setSelection(currHlBar.getSelection());
				if (e.item != pastHBar) {
					Event event = new Event();
					event.widget = pastHBar;
					event.item = currHlBar;
					event.display = e.display;
					pastHBar.notifyListeners(SWT.Selection, event);
				}
			}
		});
		pastHBar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				currHlBar.setSelection(pastHBar.getSelection());
				if (e.item != currHlBar) {
					Event event = new Event();
					event.widget = currHlBar;
					event.item = pastHBar;
					event.display = e.display;
					currHlBar.notifyListeners(SWT.Selection, event);
				}
			}
		});
	}

	private Control createOutlineContents(Composite localParent, int direction) {
		CompareViewerPane fStructurePane = new CompareViewerPane(localParent,
				SWT.BORDER | SWT.FLAT);
		fStructurePane.setText("Diff result");
		final Splitter h = new Splitter(fStructurePane, direction);
		fStructureLeft = new CompareViewerSwitchingPane(h, SWT.BORDER
				| SWT.FLAT, true) {
			@Override
			protected Viewer getViewer(Viewer oldViewer, Object input) {
				if (input instanceof Document) {
					currentTextViewer = new TextViewer(this, SWT.NONE
							| SWT.V_SCROLL | SWT.H_SCROLL);
					return currentTextViewer;
				}
				return null;
			}
		};
		h.setVisible(fStructureLeft, true);

		fStructureRight = new CompareViewerSwitchingPane(h, SWT.BORDER
				| SWT.FLAT, true) {
			@Override
			protected Viewer getViewer(Viewer oldViewer, Object input) {
				if (input instanceof Document) {
					pastTextViewer = new TextViewer(this, SWT.NONE
							| SWT.V_SCROLL | SWT.H_SCROLL);
					return pastTextViewer;
				}
				return null;
			}
		};
		h.setVisible(fStructureRight, true);

		fStructurePane.setContent(h);
		return h;
	}

	private int prefixTextWithCommonLineNumber(
			StringBuilder currentSentStrBuilder,
			StringBuilder pastSentStrBuilder) {

		String number = commonLineNumber + ": ";
		commonLineNumber++;
		currentSentStrBuilder.append(number);
		pastSentStrBuilder.append(number);
		return number.length();
	}

	private void refresh() {
		if (scenariosDiffContentProvider == null) {
			throw new IllegalStateException(
					"scenariosDiffContentProvider must be set!");
		}
		commonLineNumber = 1;
		IDiffScenariosResult diffResult = scenariosDiffStrategy.calculateDiffs(
				scenariosDiffContentProvider.getCurrentSentences(),
				scenariosDiffContentProvider.getPastSentences());

		addDiffMarkedTextToTextViewers(diffResult);
	}

	public void setScenariosDiffContentProvider(
			IScenariosDiffContentProvider scenariosDiffContentProvider) {
		this.scenariosDiffContentProvider = scenariosDiffContentProvider;
		refresh();
	}

}
