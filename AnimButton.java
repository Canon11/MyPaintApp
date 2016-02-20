import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class AnimButton extends JButton {
	StateManager stateManager;
	Mediator mediator;
	int runcount = 0;
	boolean incliment = true;
	int stopcount = 2;
	
	public AnimButton(StateManager stateManager) {
		super("Anim");
		this.stateManager = stateManager;
		this.mediator = stateManager.canvas.mediator;
		addActionListener(new AnimListener());
	}
	
	TimerTask task = new TimerTask() {
		public void run() {
			if (incliment) { 	
				for (MyDrawing d : mediator.selectedDrawings) {
					d.setSize(d.getW() + 1, d.getH() + 1);
					d.setTheta(d.getTheta() + 1.0);
					if (d.getTheta() >= 360.0) d.setTheta(d.getTheta() - 360.0);
				}
				stateManager.canvas.repaint();
				runcount++;
				if (runcount == 100) incliment = false;
			} else {
				for (MyDrawing d : mediator.selectedDrawings) {
					d.setSize(d.getW() - 1, d.getH() - 1); 
					d.setTheta(d.getTheta() + 1.0);
					if (d.getTheta() >= 360.0) d.setTheta(d.getTheta() - 360.0);
				}
				stateManager.canvas.repaint();
				runcount--;
				if (runcount == 0) {
					incliment = true;
					stopcount--;
					if (stopcount == 0) cancel(); 
				}
			}
		}
	};
		
	class AnimListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Timer timer = new Timer();
			timer.schedule(task, 500L, 20L);
		}
	}	
}
