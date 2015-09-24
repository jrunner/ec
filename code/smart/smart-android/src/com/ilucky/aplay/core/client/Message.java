package com.ilucky.aplay.core.client;

/**
 * @author IluckySi
 * @since 20150728
 */
public class Message {
	private String type;
	private String from;
	private String to;
	private String result;
	private String message;
	private long time;

	public Message() {
	}

	public Message(String type, String from, String to, String result, String message, long time) {
		this.type = type;
		this.from = from;
		this.to = to;
		this.result = result;
		this.message = message;
		this.time = time;
	}

	public String getType() {
		return this.type;
	}
	public Message setType(String type) {
		this.type = type;
		return this;
	}
	public String getFrom() {
		return this.from;
	}
	public Message setFrom(String from) {
		this.from = from;
		return this;
	}
	public String getTo() {
		return this.to;
	}
	public Message setTo(String to) {
		this.to = to;
		return this;
	}
	public String getResult() {
		return this.result;
	}
	public Message setResult(String result) {
		this.result = result;
		return this;
	}
	public String getMessage() {
		return this.message;
	}
	public Message setMessage(String message) {
		this.message = message;
		return this;
	}
	public long getTime() {
		return time;
	}
	public Message setTime(long time) {
		this.time = time;
		return this;
	}
}