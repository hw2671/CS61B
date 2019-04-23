public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = (6.67 * (Math.pow(10, -11)));

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP; yyPos = yP; xxVel = xV; yyVel = yV; mass = m; imgFileName = img;
    }

    public Body(Body x) {
        this(x.xxPos, x.yyPos, x.xxVel, x.yyVel, x.mass, x.imgFileName);
    }

    public double calcDistance(Body y) {
        double a = Math.pow(y.xxPos - this.xxPos, 2);
        double b = Math.pow(y.yyPos - this.yyPos, 2);
        return Math.sqrt(a + b);
    }

    public double calcForceExertedBy(Body y) {
        double r = calcDistance(y);
        return (G* (this.mass * y.mass)) / (r * r);
    }

    public double calcForceExertedByX(Body y) {
        double r = calcDistance(y);
        double F = calcForceExertedBy(y);
        double Fx = (F * (y.xxPos - this.xxPos)) / r;
        return Fx;
    }

    public double calcForceExertedByY(Body y) {
        double r = calcDistance(y);
        double F = calcForceExertedBy(y);
        double Fy = (F * (y.yyPos - this.yyPos)) / r;
        return Fy;
    }
    
    public double calcNetForceExertedByX(Body[] allBodies){
        double netForceX = 0.0;
        for (Body body : allBodies) {
            if (!body.equals(this)) {
                netForceX += calcForceExertedByX(body);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] allBodies){
        double netForceY = 0.0;
        for (Body body : allBodies) {
            if (!body.equals(this)) {
                netForceY += calcForceExertedByY(body);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        // Step 1
        double aNetx = fX / this.mass;
        double aNety = fY / this.mass;
        // Step 2
        this.xxVel += dt * aNetx;
        this.yyVel += dt * aNety;
        // Step 3
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel; 
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }

}