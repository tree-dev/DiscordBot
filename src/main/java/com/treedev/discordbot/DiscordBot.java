package com.treedev.discordbot;

import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.treedev.discordbot.command.CommandManager;
import com.treedev.discordbot.config.BotConfiguration;
import com.treedev.discordbot.config.ConfigurationLoader;
import com.treedev.discordbot.config.InvalidConfigException;
import com.treedev.discordbot.config.LoggingConfiguration;
import com.treedev.discordbot.messages.MessageListener;
import com.treedev.discordbot.placeholders.PlaceholderUpdater;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot {

	private static final Logger LOG = LoggerFactory.getLogger(DiscordBot.class);
	public static final String VERSION = "v1.0";
	
	private static JDA bot;

	private static BotConfiguration botConfig;
	private static CommandManager commandManager;

	public static void main(String[] args) {
		// Loads and uses the command line arguments
		if (args.length > 0) {
			// Convert command line arguments into an list so contains() can be used
			List<String> argsList = Arrays.asList(args);
			
			// Loads stored log4j.properties if desired
			if (argsList.contains("useDefaultLogPropertiesFile")) {
				ConfigurationLoader.loadConfig(new LoggingConfiguration());	
			}
		}
		
		// Setup logging library (Note: Causes error is default file is not used or user-implementation is not provided)
		PropertyConfigurator.configure("log4j.properties");
		
		// Log startup message
		LOG.info("Starting Discord Bot {}", VERSION);
		
		// Setup bot configuration
		botConfig = new BotConfiguration();
		ConfigurationLoader.loadConfig(botConfig);
		
		//Check the bot configuration for issues
		try {
			botConfig.validateConfig();
		} catch (InvalidConfigException ex) {
			LOG.error("Discord bot configuration is not valid!", ex);
			System.exit(1);
		}

		// Discord bot builder. Accepts bot token as argument
		JDABuilder jdaBuilder = new JDABuilder(botConfig.getToken());
		
		// Register bot listeners
		jdaBuilder.addEventListeners(new MessageListener());
		
		// Build discord bot object
		try {
			bot = jdaBuilder.build();
		} catch (LoginException ex) {
			LOG.error("Could not login! Please check your bot-token and try again!", ex);
			System.exit(1);
		}
		
		// Setup placeholder update thread
		new PlaceholderUpdater(botConfig.getPlaceholderUpdateInterval()).start();
		
		// Setup command manager
		commandManager = new CommandManager();
	}

	public static Logger getLog() {
		return LOG;
	}

	public static BotConfiguration getConfig() {
		return botConfig;
	}
	
	public static CommandManager getCommandManager() {
		return commandManager;
	}

	public static JDA getBot() {
		return bot;
	}

}
