package net.eclearing.domain;

public class Message {

    private final String messageId;
    private final String date;

    private final String senderId;
    private final String senderName;

    private final String contentType;
    private final String content;


    public Message(String messageId, String date, String senderId, String senderName, String contentType, String content) {
        this.messageId = messageId;
        this.date = date;
        this.senderId = senderId;
        this.senderName = senderName;
        this.contentType = contentType;
        this.content = content;
    }
    public String getSenderName() {
        return senderName;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getDate() {
        return date;
    }







    //man sollte doch eigentlich die nicht mehr änder können oder?

    /*
    public void setDate(String newDate) {
	this.date = newDate;
    }

    public void setSender(String newSender) {
	this.sender = newSender;
    }

    public void setContent (String newContent) {
	this.content = newContent;
    }

     */
}
