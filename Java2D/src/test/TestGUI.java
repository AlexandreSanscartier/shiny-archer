package test;

import gui.GameWindow;
import gui.GameWindowConfiguration;

public class TestGUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameWindowConfiguration cfg = new GameWindowConfiguration();
		cfg.title = "Test GUI";
		
		ConcreteCanvas cc = new ConcreteCanvas();
		
		GameWindow gw = new GameWindow(cc, cfg);
	}

}
