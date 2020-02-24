package com.treedev.discordbot.placeholders;

import java.util.HashMap;
import java.util.Map;

import com.treedev.discordbot.DiscordBot;

public class JDAPlaceholderParser extends PlaceholderParser {
	
	private static final Map<String, IPlaceholderValue> PLACEHOLDERS;
	
	static {
		PLACEHOLDERS = new HashMap<>();
		PLACEHOLDERS.put("totalUsers", () -> DiscordBot.getBot().getUsers().size());
	}

	protected Map<String, IPlaceholderValue> getPlaceholders() {
		return PLACEHOLDERS;
	}

}
