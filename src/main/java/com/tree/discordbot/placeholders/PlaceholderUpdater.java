package com.tree.discordbot.placeholders;

import com.tree.discordbot.DiscordBot;
import com.tree.discordbot.config.BotConfiguration;

import net.dv8tion.jda.api.entities.Activity;

public class PlaceholderUpdater extends Thread {

	private long updateTime;
	private boolean isRunning;
	private long updateInterval;

	public PlaceholderUpdater(long updateInterval) {
		updateTime = 0;
		isRunning = true;
		this.updateInterval = updateInterval;
	}

	public void setRunning(boolean value) {
		isRunning = value;
	}

	@Override
	public void run() {
		while (isRunning) {
			long timeNow = System.currentTimeMillis();

			if (timeNow > updateTime) {
				updateTime = timeNow + updateInterval;
				updatePlaceholders();
			}
		}
	}

	private void updatePlaceholders() {
		// Set bot properties from configuration such as status, etc.
		JDAPlaceholderParser parser = new JDAPlaceholderParser();
		BotConfiguration config = DiscordBot.getConfig();
		DiscordBot.getBot().getPresence().setActivity(Activity.of(config.getActivityType(), parser.format(config.getActivity())));
	}

}
