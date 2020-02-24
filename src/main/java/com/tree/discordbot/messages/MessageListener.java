package com.tree.discordbot.messages;

import java.util.Arrays;

import com.tree.discordbot.DiscordBot;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String message = event.getMessage().getContentDisplay();
		
		// Check if the message is from a guild text channel
		if (event.isFromType(ChannelType.TEXT)) {
			// Check if message starts with the proper prefix
			if (message.startsWith(DiscordBot.getConfig().getPrefix())) {
				// Split the message into an array to grab its parts
				String[] messageArray = message.split(" ");
				
				// Grab the parts of the message from the message array
				String command = messageArray[0];
				
				// Grab the arguments part of the message
				String[] args = Arrays.copyOfRange(messageArray, 1, messageArray.length);
			}
		}
	}
	
}
