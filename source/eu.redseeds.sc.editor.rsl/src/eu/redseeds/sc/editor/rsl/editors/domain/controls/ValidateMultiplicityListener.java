package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class ValidateMultiplicityListener implements Listener {

	public static String isValid(String input) {
		if(input == null) {
			return null;
		}
		if(input.matches("^\\d+$")) {//ok, multiplicity = 1,5,10, etc.
			return null;
		}
		else if(input.matches("^\\d+(\\.\\.)\\d+$")) {//ok, multiplicity = e.g. 1..2
			int pos = input.indexOf(".");
			String first = input.substring(0, pos);
			String second = input.substring(pos+2);
			try {
				int firstInt = Integer.parseInt(first);
				int secondInt = Integer.parseInt(second);
				if(firstInt >= secondInt) {
					return "Not a valid range";
				}
				return null;
			} catch (NumberFormatException e) {
				return "Problem occured during parsing numbers";
			}
		}
		else if(input.matches("^\\d+(\\.\\.)\\*$")) {
			return null;
		}
		else if(input.equalsIgnoreCase("*")) {
			return null;
		}
		return "Multiplcity is not valid";
	}

	@Override
	public void handleEvent(Event event) {
		String result = isValid(((Text)event.widget).getText());
		if(result != null) {
			MessageDialog.openInformation(null, "Multiplicity is not valid", result);
			event.doit = false;
		}
		else {
			event.doit = true;
		}
	}

}
