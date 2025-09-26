package net.eclearing.domain;

public class Message {
    private String date;
    private String sender;
    private String content;

    Message() {}
    
    Message(String date, String sender, String content) {
	this.date = date;
	this.sender = sender;
	this.content = content;
    }

    public String getDate() {
	return this.date;
    }

    public String getSender() {
	return this.sender;
    }

    public String getContent() {
	return this.content;
    }

    public void setDate(String newDate) {
	this.date = newDate;
    }

    public void setSender(String newSender) {
	this.sender = newSender;
    }

    public void setContent (String newContent) {
	this.content = newContent;
    }
}
