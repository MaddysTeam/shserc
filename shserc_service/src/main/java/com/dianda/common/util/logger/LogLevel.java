package com.dianda.common.util.logger;

public enum LogLevel {
	FATAL("FATAL", 10005),
	ERROR("ERROR", 10004),
	NORMAL("NORMAL", 10003),
	SYSTEM("SYSTEM", 10002);

	private String name;
	private int value;

	private LogLevel(String name, int value) {
		this.name = name;
		this.value = value;
	}


	public String getName(){
		return name;
	}

	public void levelup() {
		if (value == 10005) return;
		value++;
		switch (value) {
			case 10005:
				name = LogLevel.FATAL.getName();
			case 10004:
				name = LogLevel.ERROR.getName();
			case 10003:
				name = LogLevel.NORMAL.getName();
		}
	}

	public void demote() {
		if (value == 10002) return;
		value++;
		switch (value) {
			case 10004:
				name = LogLevel.FATAL.getName();
			case 10003:
				name = LogLevel.ERROR.getName();
			case 10002:
				name = LogLevel.NORMAL.getName();
		}
	}


	@Override
	public String toString() {
		return String.format("log level:%s", this.name);
	}
}
