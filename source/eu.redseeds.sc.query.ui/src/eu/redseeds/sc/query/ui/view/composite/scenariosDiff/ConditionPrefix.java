package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum ConditionPrefix {
	CONDITION_PREFIX("[==>COND:] "), END_SUCCESS_PREFIX("[FINAL: success] "), END_FAIL_PREFIX("[FINAL: failure] "),PRE_COND_PREFIX(
			"[PRECONDITION:] ");

	private final String code;
	private static final Map<String, ConditionPrefix> stringToEnum = new HashMap<String, ConditionPrefix>();
	static {
		for (ConditionPrefix op : values())
			stringToEnum.put(op.toString(), op);
	}

	private ConditionPrefix(String code) {
		this.code = code;
	}

	// Returns ConditionPrefix for string, or null if string is invalid
	public static ConditionPrefix fromString(String symbol) {
		return stringToEnum.get(symbol);
	}

	@Override
	public String toString() {
		return code;
	}

	public static boolean isConditionPrefix(String codeOfPrefix) {
		return stringToEnum.containsKey(codeOfPrefix);
	}

	public static List<String> getAllCodesAsList() {
		List<String> result = new ArrayList<String>();
		for (ConditionPrefix conditionPrefix : values()) {
			result.add(conditionPrefix.getCode());
		}
		return result;
	}

	public static String[] getAllCodesAsArray() {
		return getAllCodesAsList().toArray(new String[0]);
	}

	public String getCode() {
		return code;
	}

}
