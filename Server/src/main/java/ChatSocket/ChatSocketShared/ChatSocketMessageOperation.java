package ChatSocket.ChatSocketShared;

public enum ChatSocketMessageOperation {
    SUBSCRIBEEVENT,     //Subscribes to an certain event with an eventID
    UNSUBSCRIBEEVENT,   //Unsubscribes from an certain event with an eventID
    UPDATE,             //Update property client + server
    REGISTER,           //Register to server
    UNREGISTER,         //Unregister from server
    USERLOGIN,
}
