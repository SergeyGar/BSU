import java.awt.*;
import java.util.Vector;

public class Line {
    private Vector<Integer> xCoordinates;
    private Vector<Integer> yCoordinates;
    private Color color;

    public Line(Color color){
        this.color = color;
        xCoordinates = new Vector<>();
        yCoordinates = new Vector<>();
    }

    public void addCoordinates(int x, int y){
        xCoordinates.add(x);
        yCoordinates.add(y);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        for (int i = 0; i < xCoordinates.size() - 1; i++) {
            g2.setColor(color);
            g2.drawLine(xCoordinates.get(i), yCoordinates.get(i), xCoordinates.get(i + 1), yCoordinates.get(i + 1));
        }
    }
}
