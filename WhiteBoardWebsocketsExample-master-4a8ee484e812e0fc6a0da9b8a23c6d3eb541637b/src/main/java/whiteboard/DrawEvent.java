/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whiteboard;

/**
 * Draw event for white board.
 * @author Nico Kuijpers
 */
public class DrawEvent {
    private final double xPos;
    private final double yPos;
    
    public DrawEvent(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public double getXpos() {
        return xPos;
    }
    
    public double getYpos() {
        return yPos;
    }
}
