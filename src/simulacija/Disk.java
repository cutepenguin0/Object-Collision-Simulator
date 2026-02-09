package simulacija;

import java.awt.Color;
import java.awt.Graphics;

public class Disk extends Figura {

	public Disk(Vektor vpol, Vektor vpom) {
		super(vpol, vpom);

	}

	public Disk(Vektor vpol, Vektor vpom, int r) {
		super(vpol, vpom, r);
	}

	@Override
	public void crtaj(Graphics g) {
		int[] xkoord = new int[8];
		int[] ykoord = new int[8];

		for (int i = 0; i < 8; i++) {
			double ugao = 2 * Math.PI * i / 8;
			xkoord[i] = (int) ((int) getVektorPolozaja().getX() + poluprecnik * Math.cos(ugao));
			ykoord[i] = (int) ((int) getVektorPolozaja().getY() + poluprecnik * Math.sin(ugao));
		}
		g.setColor(Color.BLUE);
		g.fillPolygon(xkoord, ykoord, 8);
	}
}
