
import java.awt.Color;

public class SelectState extends State{
	StateManager stateManager;
	int dragX1,dragX2,dragY1,dragY2,x1,y1;
	MyDrawing selectRectangle;

	public SelectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void mouseDown(int x, int y) {
		if (stateManager.move == stateManager.PASTE)
			stateManager.canvas.mediator.paste(x,y);
		else {
			//移動ボタンが押されていない時
			if (!stateManager.moveLocation) {
				stateManager.canvas.mediator.nullSelect();
				selectRectangle = new MyRectangle(x,y,0,0);
				stateManager.addDrawing(selectRectangle);
				selectRectangle.setLineColor(Color.yellow);
				x1 = x;
				y1 = y;
			} else {
				for(MyDrawing d : stateManager.canvas.mediator.selectedDrawings) {
					d.dragX1 = x;
					d.dragY1 = y;
				}
			}
		}
	}

	public void mouseUp(int x, int y) {
		if (stateManager.move == stateManager.PASTE) {
		} else if (stateManager.moveLocation) {
			//移動ボタンが押されている時、何もしない
		} else {
			stateManager.removeDrawing(selectRectangle);
			for(MyDrawing d : stateManager.canvas.mediator.drawings) {
				if(selectRectangle.contains(d.getX(), d.getY(),d.getW(),d.getH())) {
					d.setSelected(true);
					stateManager.canvas.mediator.selectedDrawings.add(d);
				}
			}
			stateManager.canvas.repaint();
		}
	}

	public void mouseDrag(int x, int y) {
		if(stateManager.move == stateManager.PASTE) {}
		else if(stateManager.move == stateManager.CANCEL) {
			if(!stateManager.moveLocation) { //移動ボタンが押されていない時
				int w = x-x1;
				int h = y-y1;
				selectRectangle.setSize(w,h);
				selectRectangle.setRegion();
			} else {  //移動ボタンが押されている時
				for(MyDrawing d : stateManager.canvas.mediator.selectedDrawings) {
					d.dragX2 = x;
					d.dragY2 = y;
					stateManager.canvas.mediator.move(d.dragX1,d.dragY1,d.dragX2,d.dragY2);
					d.dragX1 = d.dragX2;
					d.dragY1 = d.dragY2;
				}
			}
		}
		stateManager.canvas.repaint();
	}

	public void mouseMove(int x, int y) {}
}
