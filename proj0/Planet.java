public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67 * Math.pow(10, -11);

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos       = xP;
		yyPos       = yP;
		xxVel       = xV;
		yyVel       = yV;
		mass        = m;
		imgFileName = img;

	}
	
	public Planet(Planet p) {
		xxPos       = p.xxPos;
		yyPos       = p.yyPos;
		xxVel       = p.xxVel;
		yyVel       = p.yyVel;
		mass        = p.mass;
		imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet rocinate) {
		double sqdis = (this.xxPos - rocinate.xxPos) * (this.xxPos - rocinate.xxPos) + (this.yyPos - rocinate.yyPos) * (this.yyPos - rocinate.yyPos);
		return Math.sqrt(sqdis);
	}

	public double calcForceExertedBy(Planet rocinate) {
		double distance = this.calcDistance(rocinate);
		return (G * this.mass * rocinate.mass) / (distance * distance); 
	}

	public double calcForceExertedByX(Planet rocinate) {
		double r = this.calcDistance(rocinate);
		double force = this.calcForceExertedBy(rocinate);
		double dx = rocinate.xxPos - this.xxPos;
		return (force * dx) / r;
	}

	public double calcForceExertedByY(Planet rocinate) {
		double r = this.calcDistance(rocinate);
		double force = this.calcForceExertedBy(rocinate);
		double dy = rocinate.yyPos - this.yyPos;
		return (force * dy) / r;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		int i = 0;
		double force_x = 0;
		while (i < allPlanets.length) {
			if (!this.equals(allPlanets[i])) {
				force_x += this.calcForceExertedByX(allPlanets[i]);
			}
			i++;
		}
		return force_x;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		int i = 0;
		double force_y = 0;
		while (i < allPlanets.length) {
			if (!this.equals(allPlanets[i])) {
				force_y += this.calcForceExertedByY(allPlanets[i]);
			}
			i++;
		}
		return force_y;
	}

	public void update(double dt, double fX, double fY) {
		double xxAcc = fX / this.mass;
		double yyAcc = fY / this.mass;

		this.xxVel = this.xxVel + (xxAcc * dt);
		this.yyVel = this.yyVel + (yyAcc * dt);

		this.xxPos = this.xxPos + (this.xxVel * dt);  
		this.yyPos = this.yyPos + (this.yyVel * dt);
	}

	public void draw() {
		String image_to_draw = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, image_to_draw); 
	}

}