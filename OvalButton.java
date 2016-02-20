
import java.awt.event.*;

import javax.swing.*;

public class OvalButton extends JButton {
	private static final long serialVersionUID = 1L;
	StateManager stateManager;
	
	public OvalButton(StateManager stateManager) {
		super("Oval");
		addActionListener(new OvalListener());
		this.stateManager = stateManager;
	}
	
	class OvalListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new OvalState(stateManager));
		}
	}
}
