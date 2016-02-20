
import java.awt.*;
import java.awt.geom.AffineTransform;

public class MyHum extends MyDrawing
{
	public MyHum(int xpt, int ypt) {
		super(xpt,ypt);
	}

	public MyHum(int xpt, int ypt, int wpt, int hpt) {
		super(xpt, ypt, wpt, hpt);
	}

	public void draw(Graphics g) {
		shape = HUM;
		super.draw(g);

		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();

		int bodyX = x + w/2;
		int hootY = y + h + h;
		if (w < 0) {
			x += w;
			w *= -1;
		}
		if (h < 0) {
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
        	g2.fillOval(x + 10, y + 10, w, h);
        	g2.drawLine(bodyX + 10, y+h + 10, bodyX+10, hootY+10);
    		g2.drawLine(bodyX + 10, hootY+ 10, bodyX -w/2+10, hootY + h/2+10);
    		g2.drawLine(bodyX + 10, hootY + 10, bodyX + w/2+10, hootY+h/2+10);
    		g2.drawLine(bodyX + 10, hootY -h/2 + 10, bodyX-w/2+10, hootY-h/3+10);
    		g2.drawLine(bodyX + 10, hootY -h/2 + 10, bodyX+w/2+10, hootY-h/3+10);
        	g2.setColor(Color.white);
        	g2.fillOval(x, y, w, h);
        	g2.setColor(getLineColor());
        	g2.drawOval(x,y,w, h);
        }

		if (getFillColor() == Color.white) {
			g2.drawOval(x,y,w,h);
		} else {
			g2.setColor(getFillColor());
			g2.fillOval(x,y,w,h);
			g2.setColor(getLineColor());
		}
		g2.drawOval(x, y, w,h);
		g2.drawLine(bodyX, y+h, bodyX, hootY);
		g2.drawLine(bodyX, hootY, bodyX -w/2, hootY + h/2);
		g2.drawLine(bodyX, hootY, bodyX + w/2, hootY+h/2);
		g2.drawLine(bodyX, hootY -h/2, bodyX-w/2, hootY-h/3);
		g2.drawLine(bodyX, hootY -h/2, bodyX+w/2, hootY-h/3);
	}
}
