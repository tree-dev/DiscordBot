package com.tree.discordbot.config;

import java.util.Properties;

import net.dv8tion.jda.api.entities.Activity.ActivityType;

public class BotConfiguration extends Configuration {

	private Properties properties;
	
	private static final String BOT_TOKEN_KEY = "botToken";
	private static final String ACTIVITY_TYPE_KEY = "activityType";
	private static final String ACTIVITY_KEY = "activity";
	private static final String PLACEHOLDER_UPDATE_INTERVAL_KEY = "placeholderUpdateInterval";
	private static final String PREFIX_KEY = "botPrefix";
	private static final String BOT_COLOR_KEY = "botColor";
	
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
		properties.setProperty(ACTIVITY_KEY, "Discord Bot | {totalUsers} users!");
		properties.setProperty(PLACEHOLDER_UPDATE_INTERVAL_KEY, String.valueOf(5000));
		properties.setProperty(PREFIX_KEY, "!");
		properties.setProperty(BOT_COLOR_KEY, "047DE5");
	}
	
	protected String getConfigPath() {
		return "config.properties";
	}
	
	public void validateConfig() throws InvalidConfigException {
		for (String key : new String[] {BOT_TOKEN_KEY, ACTIVITY_TYPE_KEY, ACTIVITY_KEY, PLACEHOLDER_UPDATE_INTERVAL_KEY, PREFIX_KEY, BOT_COLOR_KEY}) {
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
		
		try {
			Long.parseLong(properties.getProperty(PLACEHOLDER_UPDATE_INTERVAL_KEY));
		} catch (NumberFormatException ex) {
			throw new InvalidConfigException("The placeholder update interval must be a number!");
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
	
	public long getPlaceholderUpdateInterval() {
		return Long.parseLong(properties.getProperty(PLACEHOLDER_UPDATE_INTERVAL_KEY));
	}
	
	public String getPrefix() {
		return properties.getProperty(PREFIX_KEY);
	}
	
	public String getBotColor() {
		return properties.getProperty(BOT_COLOR_KEY);
	}
	
}
