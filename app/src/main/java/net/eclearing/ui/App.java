package net.eclearing.ui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;

import net.eclearing.controller.ChatController;
import net.eclearing.services.ChatApi;

public class App {
    public static void main(String[] args) {
	System.out.println("Welcome to the main screen!");

	String link = "http://localhost:8181/v1.0/chats/19:devteam-2025@thread.v2/messages";
	ChatApi api = new ChatApi(link);
	ChatController controller = new ChatController(api);
	controller.placeHolder(link);
    }
}
