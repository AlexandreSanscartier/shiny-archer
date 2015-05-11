package test;

import gui.GameWindow;
import gui.GameWindowConfiguration;

public class TestGUI {

    //http://gamedevelopment.tutsplus.com/tutorials/finite-state-machines-theory-and-implementation--gamedev-11867
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameWindowConfiguration cfg = new GameWindowConfiguration();
		cfg.title = "Test GUI";
		
		ConcreteCanvas cc = new ConcreteCanvas();
		
		GameWindow gw = new GameWindow(cc, cfg);
	}

}
