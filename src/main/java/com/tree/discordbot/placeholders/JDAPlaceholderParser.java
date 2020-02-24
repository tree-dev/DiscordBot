package com.tree.discordbot.placeholders;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.JDA;

public class JDAPlaceholderParser extends PlaceholderParser {
	
	private Map<String, String> placeholders;
	
	public JDAPlaceholderParser(JDA bot) {
		placeholders = new HashMap<>();
		placeholders.put("totalUsers", String.valueOf(bot.getUsers()));
	}

	protected Map<String, String> getPlaceholders() {
		return placeholders;
	}

}
