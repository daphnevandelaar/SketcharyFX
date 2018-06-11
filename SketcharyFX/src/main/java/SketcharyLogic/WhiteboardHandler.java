package SketcharyLogic;

import DrawWebSocket.DrawSocketClient.Drawer;

public class WhiteboardHandler {

    private Drawer communicator = null;
    private Drawer drawer = null;
    //private Gson gson = new Gson();


//    public void broadcastDrawedLine(Gson gson, String property, double X, double Y){
//        if(drawer != null){
//            DrawEvent drawEvent = new DrawEvent(X, Y);
//            String content = gson.toJson(drawEvent);
//            DrawMessage message = new DrawMessage();
//            message.setProperty(property);
//            message.setContent(content);
//            drawer.update(message);
//        }
//    }
}
