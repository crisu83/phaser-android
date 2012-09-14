package org.cniska.phaser.event;

public class Event {
	
	protected String action;
	protected Publisher source;

	public Event(String action, Publisher source) {
		this.action = action;
		this.source = source;
	}

	public String getAction() {
		return action;
	}

	public Publisher getSource() {
		return source;
	}
}
