package net.eclearing.controller;

import net.eclearing.services.ChatApi;
import java.net.URI;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.eclearing.ui.UIMain;

import java.io.IOException;

public class ChatController {

    public ChatApi chatApi;

    public ChatController() {}

    public ChatController(ChatApi chatApi) {
	this.chatApi = chatApi;
    }

    public void placeHolder(String link) {

        try {
            JsonArray messages = this.chatApi.getMessagesAsArray();
                for (JsonElement e : messages) {
                    JsonObject msg = ChatApi.convertJsonElementToJsonObject(e);
                    String date = ChatApi.getCreatedDateTime(msg);
                    String sender = ChatApi.getSenderName(msg);
                    String content = ChatApi.getContent(msg);
                    UIMain.updateUI(date, sender, content);
                    System.out.println(sender + ": " + content);//oder fülle label oder so
                }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
