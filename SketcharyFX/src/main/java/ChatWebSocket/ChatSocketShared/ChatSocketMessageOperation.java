package ChatWebSocket.ChatSocketShared;

public enum ChatSocketMessageOperation {
    SUBSCRIBEEVENT,     //Subscribes to an certain event with an eventID
    UNSUBSCRIBEEVENT,   //Unsubscribes from an certain event with an eventID
    UPDATE,             //Update property client + server
    REGISTER,           //Register to server
    UNREGISTER,         //Unregister from server
    //REFRESHCHAT,        //Gets all the messages in a different chat
}
