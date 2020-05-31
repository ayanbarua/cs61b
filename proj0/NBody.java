public class NBody {
	public static void main(String[] args){
		double T  = Double.parseDouble(args[0]); 
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] allPlanets = readPlanets(filename);
		StdDraw.setScale(-1 * radius, radius);
		StdDraw.enableDoubleBuffering();

		for (double time = 0; time < T; time += dt) {
			animateUniverse(dt, allPlanets);
		} 
    	printUniverse(radius, allPlanets);
	}

	private static void animateUniverse(double dt, Planet[] allPlanets) {
		int planetCount = allPlanets.length;
		double[] xForces = new double[planetCount];
		double[] yForces = new double[planetCount];
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (Planet planet:allPlanets) {
      		planet.draw();
    	}

    	for (int i = 0; i < planetCount; i++) {
    		xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
      		yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
    	} // end for loop gathering net forces on each planet

    	for (int i = 0; i < planetCount; i++) {
      		allPlanets[i].update(dt, xForces[i], yForces[i]);
    	} // end for loop updating each planet
    	StdDraw.show();
    	StdDraw.pause(10);
    }


	
	private static void printUniverse(double radius, Planet[] allPlanets) {
		StdOut.printf("%d\n", allPlanets.length);
    	StdOut.printf("%.2e\n", radius);

    	for (int i = 0; i < allPlanets.length; i++) {
      		StdOut.printf(
        		"%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        		allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
        		allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
		}
	}
		

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int planetCount = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int planetCount = in.readInt();
		double radius = in.readDouble();
		Planet[] allPlanets = new Planet[planetCount];

		for (int i = 0; i < planetCount; i++) {
			double xP  = in.readDouble();
			double yP  = in.readDouble();
			double xV  = in.readDouble();
			double yV  = in.readDouble();
			double m   = in.readDouble();
			String img = in.readString();
			allPlanets[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return allPlanets;
	} 
}