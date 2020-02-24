package com.tree.discordbot.config;

import java.util.Properties;

public abstract class Configuration {

	protected abstract Properties getProperties();
	protected abstract void setProperties(Properties properties);
	protected abstract void setupDefaultProperties();
	protected abstract String getConfigPath();
	public abstract void validateConfig() throws InvalidConfigException;
	
}
