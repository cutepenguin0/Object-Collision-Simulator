package simulacija;

import java.awt.Graphics;

public class Figura {
	private Vektor vektorPolozaja;
	private Vektor vektorPomeraja;
	protected int poluprecnik;

	public Figura(Vektor vpol, Vektor vpom, int r) {
		vektorPolozaja = vpol;
		vektorPomeraja = vpom;
		poluprecnik = r;
	}

	public Figura(Vektor vpol, Vektor vpom) {
		vektorPolozaja = vpol;
		vektorPomeraja = vpom;
		poluprecnik = 20;
	}

	public Vektor getVektorPolozaja() {
		return vektorPolozaja;
	}

	public Vektor getVektorPomeraja() {
		return vektorPomeraja;
	}

	public int getPoluprecnik() {
		return poluprecnik;
	}

	public boolean daliupada(Figura f) {
		if (Math.pow(f.getVektorPolozaja().getX() - getVektorPolozaja().getX(), 2)
				+ Math.pow(f.getVektorPolozaja().getY() - getVektorPolozaja().getY(), 2)
				- poluprecnik * poluprecnik <= 0) {
			return true;
		}
		return false;
	}

	public boolean dalisepreklapa(Figura f) {
		double udaljenost = Math.sqrt(Math.pow(f.getVektorPolozaja().getX() - getVektorPolozaja().getX(), 2)
				+ Math.pow(f.getVektorPolozaja().getY() - getVektorPolozaja().getY(), 2));
		if (udaljenost <= f.getPoluprecnik() + getPoluprecnik())
			return true;
		return false;
	}

	public void crtaj(Graphics g) {
		// TODO Auto-generated method stub

	}

}
