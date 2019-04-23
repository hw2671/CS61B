public class NBody {
    private static double radius = 0.0;
    private static Body[] bodies;
    public static void main(String args[]) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        radius = readRadius(filename);
        bodies = readBodies(filename);

        // Begin drawing
        StdDraw.setScale(-(radius), radius);
        StdDraw.picture(0,0, "./images/starfield.jpg");

        // Draw bodies
        for (int i = 0; i < bodies.length; i++){
            bodies[i].draw();
        }

        StdDraw.enableDoubleBuffering();

        double timeNow = 0;
        while(timeNow <= T){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int j = 0; j < bodies.length; j++) {
                xForces[j] = bodies[j].calcNetForceExertedByX(bodies);
                yForces[j] = bodies[j].calcNetForceExertedByY(bodies);
            }

            for (int k = 0; k < bodies.length; k++) {
                bodies[k].update(dt, xForces[k], yForces[k]);
            }

            StdDraw.picture(0,0, "./images/starfield.jpg");
            for (int l = 0; l < bodies.length; l++){
                bodies[l].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            timeNow += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }

    public static double readRadius(String filename) {
        In in  = new In(filename);
        int x = 0;
        double radius = 0.0;
        while (!in.isEmpty()) {
            if (x == 1) {
                radius = in.readDouble();
                break;
            }
            in.readLine();
            x += 1;
        }
        return radius;
    }

    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int numOfBodies = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[numOfBodies];

        for (int i = 0; i < numOfBodies; i++){
            bodies[i] = new Body
            (
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readString()
            );
        }

        return bodies;
    }
}