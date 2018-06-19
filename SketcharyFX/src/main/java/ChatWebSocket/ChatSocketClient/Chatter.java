package ChatWebSocket.ChatSocketClient;

import java.util.Observable;

public abstract class Chatter extends Observable implements IChatter {
    /*Dit is een lege klasse, ChatSocketClient extend deze klasse.
    * Deze klasse zorgt ervoor dat alle communicatie die gedaan moet worden,
    * ook geïmplementeerd wordt in de Client waarbij het tevens een observable is.
    * */
}
