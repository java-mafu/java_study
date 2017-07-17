package gui.ex21;

import java.awt.BorderLayout;

import javax.swing.*;

public class DigitalClock extends JFrame{

	public static void main(String[] args) {
		DigitalClock clock = new DigitalClock();
		clock.setVisible(true);
	}
	
	public DigitalClock(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 100);
		TimerPanel tp = new TimerPanel();
		setLayout(new BorderLayout());
		add(tp);
	}

}
