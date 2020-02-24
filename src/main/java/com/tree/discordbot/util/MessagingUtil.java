package com.tree.discordbot.util;

import java.awt.Color;

import com.tree.discordbot.DiscordBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

public class MessagingUtil {

	private MessagingUtil() {
		throw new IllegalStateException("This is a utility class!");
	}
	
	public static void sendEmbeddedMessage(MessageChannel channel, String title, String description) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		
		embedBuilder.setTitle(title, null);
		embedBuilder.setColor(new Color(Integer.parseInt(DiscordBot.getConfig().getBotColor(), 16)));
		embedBuilder.setDescription(description);
		
		channel.sendMessage(embedBuilder.build()).queue();
	}
	
}
