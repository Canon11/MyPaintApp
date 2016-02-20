
import java.awt.event.*;

import javax.swing.*;

public class SelectButton extends JButton {
	StateManager stateManager;
	MyDrawing selectFigure;	

	public SelectButton(StateManager stateManager){
		super("Select");
		addActionListener(new SelectListener());
		this.stateManager = stateManager;
	}

	class SelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.canvas.mediator.nullSelect();
			stateManager.moveLocation = false;
			stateManager.setState(new SelectState(stateManager));
		}
	}
}