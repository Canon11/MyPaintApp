
import java.awt.Color;
import java.awt.Robot;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Point;

public class SpoitState extends State {
	StateManager stateManager;
	boolean selected = false;
	Color color;
	Robot robot;
	
	public SpoitState(StateManager stateManager) {
		this.stateManager = stateManager;
		try {
			robot = new Robot();
		} catch (Exception e) {
		}
	}
	
	public void mouseDown(int x, int y) {
		if (selected) 
			stateManager.paintColor(x, y, color);
		else {
			PointerInfo info = MouseInfo.getPointerInfo();
			Point point = info.getLocation();
			color = robot.getPixelColor(point.x, point.y);
			selected = true;
			System.out.println(color.getRed() + " " + color.getBlue() + " " + color.getGreen());
		}
	}
	
	public void mouseUp(int x, int y) {}
	public void mouseDrag(int x, int y) {}

	public void mouseMove(int x, int y) {
		if (selected) {
			for (MyDrawing d : stateManager.canvas.mediator.drawings) {
				d.setRegion();
				if (d.contains(x,y))
					stateManager.setSpoitCursor();
				else 
					stateManager.setDefaultCursor();
			}
		}
	}
}
