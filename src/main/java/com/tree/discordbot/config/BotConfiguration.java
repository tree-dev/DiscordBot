package com.tree.discordbot.config;

import java.util.Properties;

import net.dv8tion.jda.api.entities.Activity.ActivityType;

public class BotConfiguration extends Configuration {

	private Properties properties;
	
	private static final String BOT_TOKEN_KEY = "botToken";
	private static final String ACTIVITY_TYPE_KEY = "activityType";
	private static final String ACTIVITY_KEY = "activity";
	
	public Properties getProperties() {
		return properties;
	}
	
	protected void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	protected void setupDefaultProperties() {
		properties = new Properties();
		properties.setProperty(BOT_TOKEN_KEY, "default-token");
		properties.setProperty(ACTIVITY_TYPE_KEY, "DEFAULT");
		properties.setProperty(ACTIVITY_KEY, "Thanks for using Discord bot!");
	}
	
	protected String getConfigPath() {
		return "config.properties";
	}
	
	public void validateConfig() throws InvalidConfigException {
		for (String key : new String[] {BOT_TOKEN_KEY, ACTIVITY_TYPE_KEY, ACTIVITY_KEY}) {
			if (!properties.containsKey(key)) {
				throw new InvalidConfigException(String.format("The configuration key '%s' is missing!", key));
			}
		}
		
		if (getToken().equals("default-token")) {
			throw new InvalidConfigException("You must set a bot-token for this bot to work properly!");
		}
		
		if (getActivityType() == null) {
			throw new InvalidConfigException("You must specify a valid activity for the bot to be set to!");
		}
	}
	
	public String getToken() {
		return properties.getProperty(BOT_TOKEN_KEY);
	}
	
	public ActivityType getActivityType() {
		return ActivityType.valueOf(properties.getProperty(ACTIVITY_TYPE_KEY));
	}
	
	public String getActivity() {
		return properties.getProperty(ACTIVITY_KEY);
	}
	
}
