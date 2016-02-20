
public class StarState extends State {
	StateManager stateManager;
	MyStar Star;
	int x1,y1;
	
	public StarState(StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		x1 = x; y1 = y;
		Star = new MyStar(x1,y1,0,0);
		stateManager.addDrawing(Star);
	}
	
	public void mouseUp(int x, int y) {}
	public void mouseDrag(int x, int y){
		int w = x - x1;
		int h = y - y1;
	    stateManager.removeDrawing(Star);
	    Star.setSize(w, h);
	    stateManager.addDrawing(Star);		
	}
}

