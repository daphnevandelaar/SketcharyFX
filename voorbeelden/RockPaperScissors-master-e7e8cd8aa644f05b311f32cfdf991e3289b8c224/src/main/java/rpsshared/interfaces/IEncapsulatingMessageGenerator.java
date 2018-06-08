package rpsshared.interfaces;

import rpsshared.messages.EncapsulatingMessage;

public interface IEncapsulatingMessageGenerator {
    EncapsulatingMessage generateMessage(Object content);

    String generateMessageString(Object content);
}
