package stubs;

import java.util.ArrayList;

public class MessagingStub {

    private ArrayList<Object> messages = new ArrayList<>();

    public void addMessage(Object obj)
    {
        this.messages.add(obj);
    }

    public ArrayList<Object>  getMessages()
    {
        return messages;
    }
}
