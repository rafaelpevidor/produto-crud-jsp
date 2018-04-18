/**
 * 
 */
package br.com.psystems.crud.web.controller.util;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author developer
 *
 */
public class URLAction {
	private static final String PATTERN;
	private static final Pattern pattern;

	static {
		PATTERN = "^/?(create|read|update|delete|list)(?:/([^/]+))?/?$";
		pattern = Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE);
	}

	private final String name;
	private final String value;

	private URLAction(String name, String value) {
		this.name = name;
		this.value = value;
	}

	private static URLAction createCommand(Matcher matcher) {
		URLAction command = null;
		String name = matcher.group(1);
		if (name != null) {
			String value = matcher.group(2);
			command = new URLAction(name.toLowerCase(Locale.ENGLISH), value);
		}
		return command;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public long getValueAsLong() {
		long longValue = 0L;
		String value = this.value;
		if (value != null) {
			try {
				longValue = Long.parseLong(value, 10);
			} catch (NumberFormatException e) { /* Nothing to do... */ }
		}
		return longValue;
	}

	public static URLAction parse(String command) {
		URLAction result = null;
		if (command != null) {
			Matcher matcher = pattern.matcher(command);
			if (matcher.matches()) {
				result = createCommand(matcher);
			}
		}
		return result;
	}
}
