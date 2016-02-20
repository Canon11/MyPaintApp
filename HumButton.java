import java.awt.event.*;

import javax.swing.*;

public class HumButton extends JButton {
	private static final long serialVersionUID = 1L;
	StateManager stateManager;
	
	public HumButton(StateManager stateManager) {
		super("Hum");
		addActionListener(new HumListener());
		this.stateManager = stateManager;
	}
	
	class HumListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new HumState(stateManager));
		}
	}
}