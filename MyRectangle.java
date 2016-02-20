
import java.awt.*;
import java.awt.geom.AffineTransform;

public class MyRectangle extends MyDrawing
{

	public MyRectangle (int xpt, int ypt) {
		super(xpt, ypt);
	}

	public MyRectangle (int xpt, int ypt, int wpt, int hpt) {
 		super(xpt, ypt, wpt, hpt);
	}

	public void draw( Graphics g ) {
		shape = RECTANGLE;
	 	super.draw(g);

        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();

        if ( w < 0 ) {
            x += w;
            w *= -1;
        }
        if ( h < 0 ) {
            y += h;
            h *= -1;
        }

        Graphics2D g2 = (Graphics2D) g;
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.PI / 180 * theta, getX() + getW() / 2, getY() + getH() / 2);
        g2.setTransform(at);
        BasicStroke widthStroke = new BasicStroke(lineWidth);
        g2.setStroke(widthStroke);
        if(shadow) {
        	g2.setColor(Color.black);
        	g2.fillRect(x + 10, y + 10, w, h);
        	g2.setColor(Color.white);
        	g2.fillRect(x, y, w, h);
        }
        g2.setColor(getLineColor());
        if (getFillColor() == Color.white) {
        	g2.drawRect(x, y, w, h);
        } else {
        	g2.setColor(getFillColor());
        	g2.fillRect(x, y, w, h);
        	g2.setColor(getLineColor());
        	g2.drawRect(x, y, w, h);
        }

	}
}
