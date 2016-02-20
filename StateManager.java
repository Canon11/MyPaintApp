
import java.awt.Color;
import java.awt.Cursor;

public class StateManager {
	MyCanvas canvas;
	State state;
	boolean select = false;
	int dragX1,dragY1,dragX2,dragY2;
	public final int COPY = 0, CUT = 1, PASTE = 2,CANCEL = 3;
	public int move = CANCEL;
	boolean moveLocation = false;
	
	public StateManager(MyCanvas canvas) {
		this.canvas = canvas;
	}
	
	public void setState(State state) {
		this.state = state;
		canvas.mediator.selectedDrawings.clear();
		moveLocation = false;
	}
	
	public void mouseDown(int x, int y) {	
		state.mouseDown(x, y);
	}
	
	public void addDrawing(MyDrawing drawing) {
		canvas.mediator.addDrawing(drawing);
		canvas.mediator.repaint();
	}
	
	public void removeDrawing(MyDrawing drawing) {
		canvas.mediator.removeDrawing(drawing);
	}
	
	public void mouseUp(int x, int y) {
		state.mouseUp(x, y);
	}
	
	public void mouseDrag(int x, int y) {
		state.mouseDrag(x, y);
	}
	
	public void mouseMove(int x, int y) {
		state.mouseMove(x, y);
	}
	
	public void moveSelect() {
		if(canvas.mediator.selectedDrawings != null)
			canvas.mediator.selectMove(move);
	}
	
	public void paintColor(int x, int y, Color color) {
		canvas.mediator.paintColor(x, y, color);
	}
	
	public void setSpoitCursor() {
		canvas.setCursor(Cursor.CROSSHAIR_CURSOR);
	}
	
	public void setDefaultCursor() {
		canvas.setCursor(Cursor.DEFAULT_CURSOR);
	}
}
