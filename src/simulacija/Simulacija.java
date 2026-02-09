package simulacija;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Simulacija extends Frame{
	private Scena scena= new Scena(this);
	public void populateScene(){
		add(scena, BorderLayout.CENTER);
	}
	public Simulacija() {
		populateScene();
		setTitle("Simulacija");
		setBounds(500,500,600,400);
		setVisible(true);
		setResizable(true);
		
	addFocusListener(new FocusAdapter() {
	
	@Override
	public void focusGained(FocusEvent e) {
		super.focusGained(e);
	
	}
	});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				System.out.println(e);
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					scena.ugasi();
					dispose();
				}

				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					if (scena.radi())
						scena.pauziraj();
					else
						scena.nastavi();
		}
		});
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				scena.ugasi();
				dispose();
			}
		});
	}
	public static void main(String[] args) {
		new Simulacija();
		
	}

}
