
public class HumState extends State {
	StateManager stateManager;
	MyHum Hum;
	int x1,y1;
		
	public HumState(StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		x1 = x; y1 = y;
		Hum = new MyHum(x1,y1,0,0);
		stateManager.addDrawing(Hum);
	}
		
	public void mouseUp(int x, int y) {}
	public void mouseDrag(int x, int y){
		int w = x - x1;
		int h = y - y1;
		Hum.setSize(w, h);
	    stateManager.canvas.repaint();
	}
	
	public void mouseMove(int x, int y) {}
}

