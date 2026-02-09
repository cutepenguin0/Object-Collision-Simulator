package simulacija;

import java.util.Random;

public class Vektor {
	private double x;
	private double y;
	
	public Vektor( double x, double y) {
		this.x=x;
		this.y=y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
	public Vektor ortvektora() {
		double xx = x/Math.sqrt(x*x+y*y);
		double yy = y/Math.sqrt(x*x+y*y);
		return new Vektor(xx,yy);
	}
	
	public Vektor pravljenjeVektora() {
		Random rand = new Random();
		double a = 0.0;
		double b = 0.0;
		while (a == 0.0 && b == 0.0) {
			a = -1 + rand.nextDouble() * 2.0;
			b = -1 + rand.nextDouble() * 2.0;
		}
		return new Vektor(a, b);
	}
	
}
