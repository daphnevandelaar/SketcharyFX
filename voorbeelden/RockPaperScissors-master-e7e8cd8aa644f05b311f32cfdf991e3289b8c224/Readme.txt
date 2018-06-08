MVC -> Separate Domain model
Game, Round, Player as model
BaseController, RpsController as controller
RpsGUI.fxml as view


Observer pattern
Game as Observer
Round as Observable
When the round has processed input for both players it changes hasEnded to true, the game subscribes to this change and sends messages to clients when the round ends.


Messaging -> Event bus
Messages are sent between clients and server.
For every action that can be performed in the GUI there is a message class, example: RegisterPlayerMessage.

This message is serialized to JSON, the JSON string and the original message Type are put into an encapsulating message with 2 properties, messageType and messageData.
This encapsulating message is then also serialized to JSON.
This way, the WebSocketServer receives the same type of message content every time it receives text.
Once the message is received, it is deserialized to the EncapsulatingMessage class.
Based on the contents of the messageType, the messageData is then deserialized to the matching object class. So in this example, back to RegisterPlayerMessage.
To separate the messaging logic from the game model logic, there are different message handlers defined. One message handler for each type of message.
Once the server has deserialized the message to the RegisterPlayerMessage, an instance of the matching message handler in created: RegisterPlayerMessageHandler.
This class then handles the message and executes the proper methods in the model.
In this example the registerPlayer method on the Game class.
Factory pattern
To create the instances for the message handlers, the factory pattern is used.
The ClientMessageHandlerFactory class creates the proper handler based on the messageType.


Unit testing
A few example unit tests are created to demonstrate the unit test principle.
Unit testing is done for every method the interface defines, per class.
Take into consideration, every method has both good and bad flows, both need to be tested.
Integration testing
With integration testing, the class / component coherence is tested.
For example, a unit test only tests ONE class.
The integration test is used to test the MessageProcessor, MessageHandlerFactory and the MessageHandler class all at the same time.


Mocking / Stubbing
To ensure only one class is tested in unit testing, all the class dependencies are replaced with Mock or Stub classes. These classes contain minimum to zero logic.
For example, WebSocket connections are hard to test because there are dependencies on a server container.
To still be able to test the MessageGenerator classes, which depend on the WebSocket classes,
the WebSocket classes are replaced by an empty class.


SOLID -> On class level AND application level
Solid can be implemented both on class and application level.
The separation of responsibility can also be applied to the Client / Server model.
The client is only responsible for capturing user input and displaying game logic results (round results for example)
The server has the responsibility to decide which client can actually perform an action (like the selection between Rock, Paper or Scissors)
This way, it is easy to create multiple front end user interfaces using the same backend game logic.


DRY
0% code duplication is a really important goal in software development.
Running SonarCube you will see 0% duplication in the example project.


Interfacing -> For testing purposes
To replace classes in unit testing, interfaces are used.
This way, it is possible to use Stub or Mock classes for testing purposes.


Dependecy Injection
In the RpsController class a property with annotation @FXML can be found:
@FXML
private TextField usernameField;
At runtime first the RpsController class is instantiated by the JavaFx framework. Next, the textfield defined in the FXML file is injected into this property.
This way, the textfield and it’s contents can be manipulated.


Communication through the app.
GUI (Controller) -> GameClient -> ClientMessageGenerator -> ClientWebSocket
ServerWebSocket -> ServerMessageProcessor -> Specific server MessageHandler (Distinction on message type) -> Game (Model) ->
ServerMessageGenerator -> ServerWebSocket
ClientWebSocket -> ClientMessageProcessor -> Specific client MessageHandler -> GameClient -> GUI (Controller)
