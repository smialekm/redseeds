package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;

import eu.redseeds.common.Constants;

/**
 * Verifies if entered text does not contain hyperlink markers
 *
 */
public class VerifyMarkedTextListener implements VerifyKeyListener {
	
	private StyledText descriptionText = null;
	
	public VerifyMarkedTextListener(StyledText descriptionText) {
		super();
		this.descriptionText = descriptionText;
	}

	@Override
	public void verifyKey(VerifyEvent event) {
		//prohibit inserting markers
		int markerLength = Constants.HYPERLINK_LEFT_MARKER.length();
		//left marker
		if(event.character == Constants.HYPERLINK_LEFT_MARKER.charAt(markerLength - 1)) {
			//get last entered text (significant for markers)
			int carretPos = descriptionText.getCaretOffset();
			int startIndex = carretPos - markerLength + 1;
			int endIndex = carretPos - (markerLength - 1) + 1;
			if(endIndex > startIndex && startIndex >= 0) {
				String significantTextBeforeMarker = descriptionText.getText().substring(startIndex, endIndex);
				if(significantTextBeforeMarker.equalsIgnoreCase(Constants.HYPERLINK_LEFT_MARKER.substring(0, markerLength - 1))) {
					event.doit = false;
				}
			}
		}
		//right marker
		if(event.character == Constants.HYPERLINK_RIGHT_MARKER.charAt(markerLength - 1)) {
			//get last entered text (significant for markers)
			int carretPos = descriptionText.getCaretOffset();
			int startIndex = carretPos - markerLength + 1;
			int endIndex = carretPos - (markerLength - 1) + 1;
			if(endIndex > startIndex && startIndex >= 0) {
				String significantTextBeforeMarker = descriptionText.getText().substring(startIndex, endIndex);
				if(significantTextBeforeMarker.equalsIgnoreCase(Constants.HYPERLINK_RIGHT_MARKER.substring(0, markerLength - 1))) {
					event.doit = false;
				}
			}
		}
	}

}
