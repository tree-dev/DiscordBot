package com.tree.discordbot.placeholders;

import java.util.HashMap;
import java.util.Map;

import com.tree.discordbot.DiscordBot;

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
