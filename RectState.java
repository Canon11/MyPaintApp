
public class RectState extends State {
	StateManager stateManager;
	MyRectangle Rectangle;
	int x1,y1;
	
	public RectState(StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		x1 = x; y1 = y;
		Rectangle = new MyRectangle(x1,y1,0,0);
		stateManager.addDrawing(Rectangle);
	}
	
	public void mouseUp(int x, int y){}
	public void mouseDrag(int x, int y){
		int w = x-x1;
		int h = y-y1;
	    Rectangle.setSize(w,h);
	    stateManager.canvas.repaint();	
	}
	
	public void mouseMove(int x, int y) {}
}
