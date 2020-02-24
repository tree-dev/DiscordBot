package com.tree.discordbot;

import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tree.discordbot.config.ConfigurationLoader;
import com.tree.discordbot.config.InvalidConfigException;
import com.tree.discordbot.config.LoggingConfiguration;
import com.tree.discordbot.placeholders.JDAPlaceholderParser;
import com.tree.discordbot.config.BotConfiguration;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordBot {

	private static final Logger LOG = LoggerFactory.getLogger(DiscordBot.class);

	private static JDA bot;

	private static BotConfiguration botConfig;

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
		LOG.info("Starting Discord Bot v1.0");
		
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
		
		// Build discord bot object
		try {
			bot = jdaBuilder.build();
		} catch (LoginException ex) {
			LOG.error("Could not login! Please check your bot-token and try again!", ex);
			System.exit(1);
		}
		
		// Set bot properties from configuration such as status, etc.
		JDAPlaceholderParser parser = new JDAPlaceholderParser(bot);
		bot.getPresence().setActivity(Activity.of(botConfig.getActivityType(), parser.format(botConfig.getActivity())));
	}

	public static Logger getLog() {
		return LOG;
	}

	public static BotConfiguration getConfig() {
		return botConfig;
	}

	public static JDA getBot() {
		return bot;
	}

}
