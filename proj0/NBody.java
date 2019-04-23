public class NBody {
    static double radius = 0.0;
    static Body[] bodies;
    public static void main(String args[]) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        System.out.println(args[0] + " " + args[1] + " " + args[2]);

        radius = readRadius(filename);
        bodies = readBodies(filename);

        // Begin drawing
        StdDraw.setScale(-(radius), radius);
        StdDraw.picture(0,0, "./images/starfield.jpg");

        // Draw bodies
        for (int i = 0; i < bodies.length; i++){
            System.out.println(bodies[i].imgFileName);
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
                System.out.println(bodies[l].imgFileName);
                bodies[l].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            timeNow += dt;
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

        if(!(bodies.length <= 1)) {
            for (int k = 0; k < bodies.length; k++) {
                System.out.print(bodies[k].xxPos + " " + bodies[k].yyPos + " " + bodies[k].xxVel + " " + bodies[k].yyVel + " " + bodies[k].mass + " " + bodies[k].imgFileName);
                System.out.println("--*--");
            }
        }

        return bodies;
    }
}