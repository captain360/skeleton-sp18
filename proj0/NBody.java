public class NBody{

	public static double readRadius(String path){
		In input = new In(path);

		int planets = input.readInt();
		double radiusUniverse = input.readDouble();
		return radiusUniverse;
	}

	public static Planet[] readPlanets(String path){

		In input = new In(path);
		
		int NumberOfPlanets = input.readInt();
		double radius = input.readDouble();
		Planet[] planets = new Planet[NumberOfPlanets];

		int i = 0;
		while(i < NumberOfPlanets){
			
             double xxPos = input.readDouble(); 
             double yyPos = input.readDouble();
             double xxVel = input.readDouble();
             double yyVel = input.readDouble();
             double mass = input.readDouble();
             String ImgFileName = input.readString();

            planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,ImgFileName);
			
			i++;
		}        
        
        return planets;
	}


	public static void main(String[] args) {
		
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		//Drawing the Background.
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();

		String imageToDraw = "./images/starfield.jpg";
		StdDraw.picture(0,0,imageToDraw);

		//Drawing the planets from the planets array.
		for ( Planet p : planets) {
			p.draw();
		}

        StdDraw.show();
		
        //Enabling Double Buffering.
		StdDraw.enableDoubleBuffering();
		double timeVariable = 0;

		while(timeVariable < T){

			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
            
            //Calculating X and Y forces and storing them in array.
			for(int i = 0; i < planets.length; i++){

				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			//Calling update on each of the planets.
			for(int i = 0; i < planets.length; i++){

				planets[i].update(dt, xForces[i], yForces[i]);
			}

			//Drawing the background image.
			StdDraw.clear();
			StdDraw.setScale(-radius,radius);
			StdDraw.picture(0,0,imageToDraw);

			//Drawing the planets.
			for (Planet p : planets ) {
				p.draw();
			}

			//Showing off-screen Buffer.
			StdDraw.show();
			StdDraw.pause(10);

			timeVariable += dt;

		}

		StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);

        for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
            }

	}
}