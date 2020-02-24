package com.tree.discordbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

	private String name;
	private String[] validArgs;
	
	public Command(String name, String[] validArgs) {
		this.name = name;
		this.validArgs = validArgs;
	}
	
	public abstract void execute(MessageReceivedEvent event, String[] args);
	
	public String getName() {
		return name;
	}
	
	public String[] getValidArgs() {
		return validArgs;
	}
	
}
