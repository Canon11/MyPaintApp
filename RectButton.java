
import java.awt.event.*;

import javax.swing.*;

public class RectButton extends JButton {
	StateManager stateManager;
	
	public RectButton(StateManager stateManager){
		super("Rectangle");
		addActionListener(new RectListener());
		this.stateManager = stateManager;
	}

	class RectListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new RectState(stateManager));
		}
	}
}
