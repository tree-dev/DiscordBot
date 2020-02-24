package com.treedev.discordbot.config;

import java.util.Properties;

public class LoggingConfiguration extends Configuration {

	protected Properties properties;

	public Properties getProperties() {
		return properties;
	}
	
	protected void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	protected void setupDefaultProperties() {
		properties = new Properties();
		properties.setProperty("log4j.rootLogger", "debug, stdout, file");
		properties.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
		properties.setProperty("log4j.appender.stdout.Target", "System.out");
		properties.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.stdout.layout.ConversionPattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
		properties.setProperty("log4j.appender.file", "org.apache.log4j.RollingFileAppender");
		properties.setProperty("log4j.appender.file.File", "application.log");
		properties.setProperty("log4j.appender.file.MaxFileSize", "8MB");
		properties.setProperty("log4j.appender.file.MaxBackupIndex", "10");
		properties.setProperty("log4j.appender.file.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.file.layout.ConversionPattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
	}
	
	protected String getConfigPath() {
		return "log4j.properties";
	}

	public void validateConfig() throws InvalidConfigException {
		// Let Log4J validate this configuration file
	}
	
}
