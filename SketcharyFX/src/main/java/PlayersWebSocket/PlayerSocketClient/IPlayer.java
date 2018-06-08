package PlayersWebSocket.PlayerSocketClient;

public interface IPlayer {
    void startConnection();

    void login(String property);
    void logout(String property);

    void stopConnection();

    void update(PlayerMessage playerMessage);
}
