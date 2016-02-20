

import java.awt.*;

public class MyDrawing implements Cloneable {
	int x,y,w,h;
	Color lineColor, fillColor;
	int lineWidth;
	boolean isSelected = false;
	Rectangle region;
	final int SIZE = 3;
	MyDrawing newClone;
	int shape;
	final int RECTANGLE = 1, OVAL = 2, STAR = 3, HUM = 4;
	boolean shadow = false;
	int dragX1,dragY1,dragX2,dragY2;
	double theta = 0;
	int colorAlpha = 255;
	
	public MyDrawing() {
		x = y = 0;
		w = h = 40;
		lineColor = Color.black;
		fillColor = Color.white;
		lineWidth = 1;
	}
	
	public MyDrawing(int xpt,int ypt) {
		x = xpt; y = ypt;
		w = h = 40;
		lineColor = Color.black;
		fillColor = Color.white;
		lineWidth = 1;
	}
	
	public MyDrawing(int xpt,int ypt,int wpt,int hpt) {
		x = xpt; y = ypt; w = wpt; h = hpt;
		lineColor = Color.black;
		fillColor = Color.white;
		lineWidth = 1;
	}
	
	public void draw(Graphics g) {
		if (isSelected) {
			int xx,yy,ww,hh;
			if(shape == STAR) {
				xx = x-2*w/2;
				yy = y - 2*h/2;
				ww = w*2;
				hh = h*2;
			} else {
				xx = x;
				yy = y;
				ww = w;
				hh = h;
			}
			g.setColor(Color.black);
			g.fillRect(xx+ww/2-SIZE, yy-SIZE, SIZE*2, SIZE*2);
			g.fillRect(xx-SIZE, yy+hh/2-SIZE, SIZE*2, SIZE*2);
			g.fillRect(xx+ww/2-SIZE, yy+hh-SIZE, SIZE*2, SIZE*2);
			g.fillRect(xx+ww-SIZE, yy+hh/2-SIZE, SIZE*2, SIZE*2);
			g.fillRect(xx-SIZE, yy-SIZE, SIZE*2, SIZE*2);
			g.fillRect(xx+ww-SIZE, yy-SIZE, SIZE*2, SIZE*2);
			g.fillRect(xx-SIZE, yy+hh-SIZE, SIZE*2, SIZE*2);
			g.fillRect(xx+ww-SIZE, yy+hh-SIZE, SIZE*2, SIZE*2);
		}
	}
	
	public boolean contains(int x, int y, int w, int h) {
		return region.contains(x,y,w,h);
	}
	
	public boolean contains(int x, int y) {
		return region.contains(x,y);
	}
	
	public boolean getSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public void setRegion() {
		if(shape == STAR) region = new Rectangle(x-2*w/2, y-2*h/2, w*2, h*2);
		else region = new Rectangle(x,y,w,h);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getW() {
		return this.w;
	}
	
	public int getH() {
		return this.h;
	}
	
	public void setLocation(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
	}
	
	public void move(int x1, int y1, int x2, int y2) {
		int x = x2 - x1;
		int y = y2 - y1;
		this.x = this.x + x;
		this.y = this.y + y;
	}
	
	public void setLineColor(Color color) {
		this.lineColor = color;
	}
	
	public void setFillColor(Color color) {
		this.fillColor = color;
	}
	
	public Color getLineColor() {
		return this.lineColor;
	}
	
	public Color getFillColor() {
		return this.fillColor;
	}
	
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	public MyDrawing clone() {
	    MyDrawing d;
	    try{
		if(shape == RECTANGLE) d = (MyRectangle) super.clone();
		else if(shape == HUM) d = (MyHum) super.clone();
		else if(shape == OVAL) d = (MyOval) super.clone();
		else d = (MyStar) super.clone();
		return d;
	    } catch (CloneNotSupportedException e) {
		return null;
	    }
	}
	
	public double getTheta() {
		return this.theta;
	}
	
	public void setTheta(double theta) {
		this.theta = theta;
	}
}	

