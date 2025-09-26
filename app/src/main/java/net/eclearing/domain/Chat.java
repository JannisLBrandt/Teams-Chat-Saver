package net.eclearing.domain;

import java.util.ArrayList;
import net.eclearing.domain.Message;

public class Chat {

    private ArrayList<Message> messages;

    public Chat(ArrayList<Message> pMessages){
        this.messages = pMessages;
    }

    public ArrayList<Message> getMessages() {
	return this.messages;
    }
}
