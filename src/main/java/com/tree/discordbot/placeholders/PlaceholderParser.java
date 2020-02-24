package com.tree.discordbot.placeholders;

import java.util.Map;

public abstract class PlaceholderParser {

	protected abstract Map<String, String> getPlaceholders();
	
	public String format(String text) {
		getPlaceholders().entrySet().forEach(entry -> text.replace(String.format("{%s}", entry.getKey()), entry.getValue()));
		return text;
	}
	
}
