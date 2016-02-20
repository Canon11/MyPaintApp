

import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class Mediator {
	Vector<MyDrawing> drawings, selectedDrawings;
	MyCanvas canvas;
	Vector<MyDrawing> buffer;
	Color lineColor = Color.black;
	Color fillColor = Color.white;
	int lineWidth = 2;
	//moveコマンドのフィールド
	public final int COPY = 0, CUT = 1, PASTE = 2,CANCEL = 3;
	boolean selectedDown = false;
	
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
		selectedDrawings = new Vector<MyDrawing>();
		buffer = new Vector<MyDrawing>();
	}

	public Enumeration<MyDrawing> drawingsElements() {
		return drawings.elements();
	}

	public void addDrawing(MyDrawing d) {
	    d.setLineColor(lineColor);
	    d.setFillColor(fillColor);
	    d.setLineWidth(lineWidth);
		drawings.add(d);
	}

	public void removeDrawing(MyDrawing d) {
		drawings.remove(d);
	}

	public Vector<MyDrawing> getSelectedDrawing() {
		return selectedDrawings;
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	public void move(int x1,int y1,int x2,int y2) {
		for(MyDrawing d : selectedDrawings) {
			d.move(x1,y1,x2,y2);
			d.setRegion();
		}
		repaint();
	}
	
	public void setSelected(int x, int y) {
		for(MyDrawing d : drawings) {
			d.setRegion();
			if(d.contains(x,y)) {
				selectedDrawings.add(d);
				d.isSelected = true;
			}
		}
		repaint();
	}
	
	public void setLineColor(Color color) {
		this.lineColor = color;
	}
	
	public void setFillColor(Color color) {
		this.fillColor = color;
	}
	
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	public void clearBuffer() {
		buffer = null;
	}
	
	public void copy() {
		buffer.clear();
		for(MyDrawing d : selectedDrawings) {
			buffer.add(d.clone());
		}
	}
	
	public void cut() {
		buffer.clear();
		for(MyDrawing d : selectedDrawings) {
			buffer.add(d.clone());
			removeDrawing(d);
		}
		repaint();
	}
	
	public void paste(int x, int y) {
		Vector<MyDrawing> clone = new Vector<MyDrawing>();
		MyDrawing cloned;
		int centerX = selectedDrawings.elementAt(0).getX();
		int centerY = selectedDrawings.elementAt(0).getY();
		if(selectedDrawings == null) {}
		else {
			for(MyDrawing d : buffer) {
				clone.add(d.clone());
				cloned = clone.lastElement();
				cloned.setLocation(cloned.x - centerX + x,cloned.y - centerY + y);
				addDrawing(clone.lastElement());
			}
			repaint();
		}
	}
	
	public void selectMove(int move) {
		if(move == COPY) copy();
		else if(move == CUT) cut();
	}
	
	public void changeShadow() {
		if(getSelectedDrawing() != null) {
			for(MyDrawing d : selectedDrawings)
				d.shadow = !d.shadow;
			repaint();
		}
	}
	
	public void nullSelect() {
		for(MyDrawing d : selectedDrawings)
			d.isSelected = false;
		selectedDrawings.clear();
	}
	
	public void delete() {
		for(MyDrawing d : selectedDrawings) 
			removeDrawing(d);
		repaint();
	}
	public void selectLineColor(Color color) {
		for(MyDrawing d : selectedDrawings)
			d.setLineColor(color);
	}
	public void selectFillColor(Color color) {
		for(MyDrawing d : selectedDrawings)
			d.setFillColor(color);
	}
	public void selectLineWidth(int width) {
		for(MyDrawing d : selectedDrawings)
			d.setLineWidth(width);
	}
	
	public void paintColor(int x, int y, Color color) {
		for(MyDrawing d : drawings) {
			d.setRegion();
			if (d.contains(x,y)) {
				d.setFillColor(color);
			}
		}
		repaint();
	}
} 
