package Models;

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
