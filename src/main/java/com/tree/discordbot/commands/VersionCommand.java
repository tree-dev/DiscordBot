package com.tree.discordbot.commands;

import com.tree.discordbot.command.Command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class VersionCommand extends Command {

	public VersionCommand() {
		super("version", null);
	}

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		event.getChannel().sendMessage("Not implemented!").queue();
	}

}
