package com.treedev.discordbot.commands;

import com.treedev.discordbot.DiscordBot;
import com.treedev.discordbot.command.Command;
import com.treedev.discordbot.util.MessagingUtil;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class VersionCommand extends Command {

	public VersionCommand() {
		super("version", null);
	}

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		MessagingUtil.sendEmbeddedMessage(event.getChannel(), "Discord Bot Version", "Discord bot " + DiscordBot.VERSION + ". Developed by Tree");
	}

}
