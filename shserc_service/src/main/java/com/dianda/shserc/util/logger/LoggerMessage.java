package com.dianda.shserc.util.logger;

/**
 *  @title LoggerMessage
 *  @Description  abstract class for logger
 *  @author huachao
 *  @Date 2020/10/17 13:45
 *  @Copyright 2019-2020
 */

public abstract class LoggerMessage {

	private String where;
	private String when;
	private String what;
	private String who;

	public LoggerMessage() {
	}

	public LoggerMessage(String who, String when, String where, String what) {
		this.who = who;
		this.when = when;
		this.where = where;
		this.what = what;
	}

	public String toString() {
		return "LoggerMessage{" +
				"【where】'" + where + '\'' +
				",【when】'" + when + '\'' +
				", 【what】'" + what + '\'' +
				", 【who】'" + who + '\'' +
				'}';
	}

}

