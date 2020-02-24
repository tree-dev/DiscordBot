package com.treedev.discordbot.placeholders;

import java.util.Map;
import java.util.Map.Entry;

public abstract class PlaceholderParser {

	protected abstract Map<String, IPlaceholderValue> getPlaceholders();
	
	public String format(String text) {
		for (Entry<String, IPlaceholderValue> placeholder : getPlaceholders().entrySet()) {
			String formattedPlaceholder = String.format("{%s}", String.valueOf(placeholder.getKey()));
			
			if (text.contains(formattedPlaceholder)) {
				text = text.replace(formattedPlaceholder, String.valueOf(placeholder.getValue().getPlaceholder()));
			}
		}
		return text;
	}
	
}
