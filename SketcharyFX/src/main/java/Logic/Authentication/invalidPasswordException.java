package Logic.Authentication;

public class invalidPasswordException extends Exception {
    public invalidPasswordException(String errorMessage){
        this.message = errorMessage;
    }
    private String message;

    @Override
    public String toString() {
        return message;
    }
}
