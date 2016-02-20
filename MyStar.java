
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class MyStar extends MyDrawing
{
	int[] xArray = new int[5];
    int[] yArray = new int[5];

    public MyStar(int xpt, int ypt) {
        super();
        setLocation(xpt,ypt);
        setRegion();
    }
    
    public MyStar(int xpt,int ypt, int wpt, int hpt) {
    	super(xpt,ypt,wpt,hpt);
    	setRegion();
    }
    
    public void draw(Graphics g) {
        super.draw(g);
        shape = STAR;
        
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();

        double Delta = Math.PI/2;
        //５角形を作る
        for (int i = 0; i < 5; i++) {
            xArray[i] = (int)(w*Math.cos(2.0 * i * Math.PI / 5 + Delta) + x);
            yArray[i] = -(int)(w * Math.sin(2.0 * i * Math.PI / 5 + Delta)) + y;
        }
        
        //作った５角形の頂点で星を作る。頂点の順番を定義
        int xStar[] = {xArray[0],xArray[2],xArray[4],xArray[1],xArray[3]};
        int yStar[] = {yArray[0],yArray[2],yArray[4],yArray[1],yArray[3]};
        int sxStar[] = new int[5];
        int syStar[] = new int[5];
        
        for(int i = 0; i < 5; i++) {
        	sxStar[i] = xStar[i] + 20;
        	syStar[i] = yStar[i] + 20;
        }
        

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
        	g2.fillPolygon(sxStar, syStar,5);
        	g2.setColor(Color.white);
        	g2.fillPolygon(xStar, yStar, 5);
        }
        g2.setColor(getLineColor());
        if (getFillColor() == Color.white) {
        	g2.drawPolygon(xStar, yStar,5);
        } else {
        	g2.setColor(getFillColor());
        	g2.fillPolygon(xStar, yStar,5);
        	g2.setColor(getLineColor());
        	g2.drawPolygon(xStar, yStar, 5);
        }
    }
}