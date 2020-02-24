package com.tree.discordbot.placeholders;

import java.util.Map;
import java.util.Map.Entry;

public abstract class PlaceholderParser {

	protected abstract Map<String, String> getPlaceholders();
	
	public String format(String text) {
		for (Entry<String, String> placeholder : getPlaceholders().entrySet()) {
			text = text.replace(String.format("{%s}", placeholder.getKey()), placeholder.getValue());
		}
		return text;
	}
	
}
