package net.eclearing.ui;

import net.eclearing.util.*;

public class App {
    public static void main(String[] args) {

	String link = "http://localhost:8181/v1.0/chats/19:devteam-2025@thread.v2/messages";

	UIMain.createUI();

	CustomError error = new CustomError(CustomErrorType.HTTP_ERROR, "bad request!");
	CustomErrorCollector.addError(error);

	CustomErrorCollector.displayErrors();
    }
}
