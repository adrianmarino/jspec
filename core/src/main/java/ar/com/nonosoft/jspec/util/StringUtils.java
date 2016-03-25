package ar.com.nonosoft.jspec.util;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

import static org.fusesource.jansi.Ansi.Attribute.INTENSITY_BOLD;
import static org.fusesource.jansi.Ansi.Attribute.INTENSITY_BOLD_OFF;

public class StringUtils {

	public static String withFgAndBgColor(String value, Color fg, Color bg) {
		return Ansi.ansi().fg(fg).bg(bg).a(value).reset().toString();
	}

	public static String withFgColor(String value, Color fg) {
		return Ansi.ansi().fg(fg).a(value).reset().toString();
	}

	public static String boldWithFbColor(String value, Color fg) {
		return Ansi.ansi().fg(fg).a(INTENSITY_BOLD).a(value).a(INTENSITY_BOLD_OFF).reset().toString();
	}

	public static String boldWithFgAndBgColor(String value, Color fg, Color bg) {
		return Ansi.ansi().fg(fg).bg(bg).a(INTENSITY_BOLD).a(value).a(INTENSITY_BOLD_OFF).reset().toString();
	}

	private StringUtils() { }
}
