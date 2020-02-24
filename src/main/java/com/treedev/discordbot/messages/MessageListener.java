package com.treedev.discordbot.messages;

import java.util.Arrays;

import com.treedev.discordbot.DiscordBot;
import com.treedev.discordbot.command.Command;

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
				// Grabs the bot prefix
				String botPrefix = DiscordBot.getConfig().getPrefix();
				
				// Split the message into an array to grab its parts
				String[] messageArray = message.split(" ");
				
				// Grab the parts of the message from the message array
				String command = messageArray[0].toLowerCase().replace(botPrefix, "");
				
				// Grab the arguments part of the message
				String[] args = Arrays.copyOfRange(messageArray, 1, messageArray.length);
				
				// Checks if the command exists and executes it if it does
				if (DiscordBot.getCommandManager().commandExists(command)) {
					Command cmd = DiscordBot.getCommandManager().getCommandByName(command);
					if (cmd.getValidArgs() == null || args.length == cmd.getValidArgs().length) {
						cmd.execute(event, args);
					} else {
						String validArgsString = String.join(" ", cmd.getValidArgs());
						event.getChannel().sendMessage(String.format("Invalid Args! Try: %s %s %s", botPrefix, cmd.getName(), validArgsString));
					}
				} else {
					event.getChannel().sendMessage("Command not found!").queue();
				}
			}
		}
	}
	
}
