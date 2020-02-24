package com.treedev.discordbot.command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

	private Map<String, Command> commands;
	
	public CommandManager() {
		commands = new HashMap<>();
		
		// Adds the version command to the bot
		VersionCommand versionCommand = new VersionCommand();
		commands.put(versionCommand.getName(), versionCommand);
	}
	
	public Command getCommandByName(String name) {
		return commands.get(name);
	}
	
	public boolean commandExists(String name) {
		return getCommandByName(name) != null;
	}
	
}
