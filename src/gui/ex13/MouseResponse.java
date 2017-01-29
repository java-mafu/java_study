package gui.ex13;

import java.awt.Component;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseResponse implements MouseListener , MouseMotionListener{

	private Component clock;
	PopupMenu pop;
	public Point pointerLocation;
	public Point boundLocation;

	public MouseResponse(Component clock, PopupMenu pop){
		this.clock = clock;
		clock.addMouseListener(this);
		clock.addMouseMotionListener(this);
		this.pop = pop;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int btn = e.getButton();

		if(btn == MouseEvent.BUTTON1){
			
		}
		if(btn == MouseEvent.BUTTON2){

		}
		if(btn == MouseEvent.BUTTON3){
			pop.show(clock, e.getX(), e.getY());
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int btn = e.getButton();

		if(btn == MouseEvent.BUTTON1){
			pointerLocation = e.getLocationOnScreen();
			boundLocation = clock.getLocationOnScreen();
		}
		if(btn == MouseEvent.BUTTON2){

		}
		if(btn == MouseEvent.BUTTON3){

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		pointerLocation = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(pointerLocation != null){
			int x = e.getXOnScreen() - pointerLocation.x + boundLocation.x;
			int y = e.getYOnScreen() - pointerLocation.y + boundLocation.y;
			clock.setBounds(x, y, clock.getWidth(), clock.getHeight());
			clock.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}


}
