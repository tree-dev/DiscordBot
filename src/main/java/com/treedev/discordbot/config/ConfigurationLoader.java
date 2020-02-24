package com.treedev.discordbot.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.treedev.discordbot.DiscordBot;

public class ConfigurationLoader {

	private ConfigurationLoader() {
		throw new IllegalStateException("This is a utility class!");
	}

	public static void loadConfig(Configuration config) {
		// Check if the config exists and create it if it cannot be found
		if (!new File(config.getConfigPath()).exists()) {
			saveDefaultConfig(config);
			return;
		}
		
		// Load properties from file
		try (InputStream inputStream = new FileInputStream(config.getConfigPath())) {
			Properties properties = new Properties();
			properties.load(inputStream);
			config.setProperties(properties);
		} catch (IOException ex) {
			DiscordBot.getLog().error(String.format("Could not load configuration file '%s'!", config.getConfigPath()), ex);
			System.exit(1);
		}
	}

	private static void saveDefaultConfig(Configuration config) {
		// Setup default properties
		config.setupDefaultProperties();

		// Store default properties into file
		try (OutputStream outputStream = new FileOutputStream(config.getConfigPath())) {
			config.getProperties().store(outputStream, null);
		} catch (IOException ex) {
			DiscordBot.getLog().error(String.format("Could not save configuration file '%s'!", config.getConfigPath()), ex);
			System.exit(1);
		}
	}

}
