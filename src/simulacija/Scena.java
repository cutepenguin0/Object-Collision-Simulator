package simulacija;

import java.awt.Canvas;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Scena extends Canvas implements Runnable {
	private ArrayList<Figura> figure = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	private int pomerajpx = 3;
	private Thread thread = new Thread(this);
	private boolean radi=false;
	final Font font =  new Font("Comic Sans",Font.BOLD, 70);

	public Scena(Simulacija simulacija) {
		
		setBackground(Color.GRAY);
		thread.start();
		
		addKeyListener(new KeyAdapter() {
		
		@Override
		public void keyPressed(KeyEvent e) {
			Component source = (Component)e.getSource();
			source.getParent().dispatchEvent(e);
		}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!radi) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						Vektor vpolozajdiska = new Vektor(e.getX(), e.getY());
						Figura disk = new Disk(vpolozajdiska, vpolozajdiska.pravljenjeVektora());
						if (figure.size() == 0) {
							figure.add(disk);
						} else {
							boolean poklapase = false;
							for (int i = 0; i < figure.size(); i++) {
								if (figure.get(i).dalisepreklapa(disk)) {
									poklapase = true;
								}
							}
							if (!poklapase)
								figure.add(disk);
						}
					}
					repaint();
			}
			
			}
		});

	}

	
	public void pomeriFiguru() {
		for (Figura f : figure) {
			f.getVektorPolozaja()
					.setX(f.getVektorPolozaja().getX() + pomerajpx * f.getVektorPomeraja().ortvektora().getX());
			f.getVektorPolozaja()
					.setY(f.getVektorPolozaja().getY() + pomerajpx * f.getVektorPomeraja().ortvektora().getY());

		}
	}

	public void postaviSimulaciju(Simulacija simulacija) {
	}

	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		for (Figura f : figure) {
			f.crtaj(g);
		}
		if(!radi){
			g.setColor(Color.BLACK);
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics(font);
			g.drawString("Pauza", (getWidth()-fm.stringWidth("Pauza"))/2, fm.getAscent()+(getHeight()- (fm.getAscent() + fm.getDescent())) / 2);
		}
	}

	public void sudarFigura() {
		for (Figura f : figure) {
			if (f.getVektorPolozaja().getY() - f.getPoluprecnik() < 0.0) {
				f.getVektorPomeraja().setY(Math.abs(f.getVektorPomeraja().getY()));
			}
			if (f.getVektorPolozaja().getY() + f.getPoluprecnik() > getHeight()) {
				f.getVektorPomeraja().setY(-f.getVektorPomeraja().getY());
			}
			if (f.getVektorPolozaja().getX() - f.getPoluprecnik() < 0.0) {
				f.getVektorPomeraja().setX(Math.abs(f.getVektorPomeraja().getX()));
			}
			if (f.getVektorPolozaja().getX() + f.getPoluprecnik() > getWidth()) {
				f.getVektorPomeraja().setX(-f.getVektorPomeraja().getX());
			}
		}

		for (int i = 0; i < figure.size(); i++) {
			for (int j = i + 1; j < figure.size(); j++) {
				Figura f1 = figure.get(i);
				Figura f2 = figure.get(j);
				if (f1.dalisepreklapa(f2)) {
					Vektor n = new Vektor(f1.getVektorPolozaja().getX() - f2.getVektorPolozaja().getX(),
							f1.getVektorPolozaja().getY() - f2.getVektorPolozaja().getY());
					Vektor nOrt = n.ortvektora();
					double skalar1 = f1.getVektorPomeraja().getX() * nOrt.getX()
							+ f1.getVektorPomeraja().getY() * nOrt.getY();
					
					f1.getVektorPomeraja().setX(f1.getVektorPomeraja().getX() - 2 * skalar1 * nOrt.getX());
					f1.getVektorPomeraja().setY(f1.getVektorPomeraja().getY() - 2 * skalar1 * nOrt.getY());

					Vektor nOrt2 = new Vektor(-nOrt.getX(), -nOrt.getY());
					double skalar2 = f2.getVektorPomeraja().getX() * nOrt2.getX()
							+ f2.getVektorPomeraja().getY() * nOrt2.getY();

					f2.getVektorPomeraja().setX(f2.getVektorPomeraja().getX() - 2 * skalar2 * nOrt2.getX());
					f2.getVektorPomeraja().setY(f2.getVektorPomeraja().getY() - 2 * skalar2 * nOrt2.getY());
					
				}
			}
		}

	}

	@Override
	public void run() {
		try {
			while (!thread.isInterrupted()) {
				synchronized (this) {
					while(!radi) wait();
				}
				Thread.sleep(100);
				pomeriFiguru();
				sudarFigura();
				repaint();
			}
		} catch (InterruptedException e) {
		}

	}

	public synchronized void ugasi() {
		thread.interrupt();
	}


	public synchronized  boolean radi() {
		return radi;
	}
	public synchronized void pauziraj() {
		radi= false;
		
	}
	
	public synchronized void nastavi() {
		radi = true;
		notify();
	}

}
