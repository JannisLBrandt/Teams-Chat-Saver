package net.eclearing.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.eclearing.domain.Chat;
import net.eclearing.domain.Message;

import java.io.IOException;
import java.util.ArrayList;


//liefert die Message Objekte
public class ChatService {

    private String uri;// kann man auch zum typ URI machern, ich nutze das was ich kenne und wo ich weiß wie es sich verhält (da passiert eh nicht weiter mit)
    private ApiHttpRequest request;

    public ChatService(String pUri) throws IOException, InterruptedException {
        this.uri = pUri;
        this.request = new ApiHttpRequest(uri);
    }

    public Chat getChat() throws IOException, InterruptedException {
        JsonArray messages = request.getMessagesAsArray();
        ArrayList<Message> messageList = new ArrayList<Message>();

        for(JsonElement e: messages){// ich spciher erstmal die einzenden felder um diese zu einer nachricht zusammen zu setzten. (liber so als ewig lannger kontruktur)

            JsonObject eObject = e.getAsJsonObject();//übersichtlicher so
            String msgId = ApiChatParser.getMsgID(eObject);
            String msgDate = ApiChatParser.getCreatedDateTime(eObject);
            String msgSenderId = ApiChatParser.getSenderId(eObject);
            String msgSenderName = ApiChatParser.getSenderName(eObject);
            String msgContentType = ApiChatParser.getContentType(eObject);
            String msgContent = ApiChatParser.getContent(eObject);

            Message msg = new Message(msgId, msgDate, msgSenderId, msgSenderName, msgContentType, msgContent);
            messageList.add(msg);
        }

        return new Chat(messageList);
    }

}
