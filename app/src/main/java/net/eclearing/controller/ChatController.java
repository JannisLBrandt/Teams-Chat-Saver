package net.eclearing.controller;

import net.eclearing.domain.Chat;
import net.eclearing.domain.Message;

import net.eclearing.services.ChatService;
import net.eclearing.services.ExportService;
import net.eclearing.ui.UIMain;

import java.io.IOException;

public class ChatController {

    public ChatService chatService;

    public UIMain uiMain;

    public ExportService exportService;

    //public ChatController() {}

    //ich glaube man sollte hier einen link übergeben
    public ChatController(String pUri) throws IOException, InterruptedException {
	this.chatService = new ChatService(pUri);
    }

    //was soll das genau machen??
    public void placeHolder(String link) {

        try {
            Chat messages = chatService.getChat();
                for (Message m : messages.getMessages()) {
                    if (!(m instanceof  Message)){
                        System.out.println("keien Nachricht, irgendwas ist schiefgelaufen");
                        break;
                    }
                    String sender = m.getSenderName();
                    String content = m.getContent();
                    uiMain.updateUI("", sender, content);
                    System.out.println(sender + ": " + content);//oder fülle label oder so
                }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void saveToFile(){
        exportService = new ExportService();
        try {
            exportService.saveStringToFile( chatService.getChatJsonAsString(), chatService.getChatMessagesAsString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }











}


