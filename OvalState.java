
public class OvalState extends State {
	StateManager stateManager;
	MyOval Oval;
	int x1,y1;
	
	public OvalState(StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		x1 = x; y1 = y;
		Oval = new MyOval(x1,y1,0,0);
		stateManager.addDrawing(Oval);
	}
	
	public void mouseUp(int x, int y) {}
	public void mouseDrag(int x, int y){
		int w = x - x1;
		int h = y - y1;
	    Oval.setSize(w, h);
	    stateManager.canvas.repaint();
	}
	
	public void mouseMove(int x, int y) {}
}
