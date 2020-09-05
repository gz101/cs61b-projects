public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet that) {
        double dx = this.xxPos - that.xxPos;
        double dy = this.yyPos - that.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet that) {
        return G * this.mass * that.mass / Math.pow(this.calcDistance(that), 2);
    }

    public double calcForceExertedByX(Planet that) {
        return this.calcForceExertedBy(that) * (that.xxPos - this.xxPos) / this.calcDistance(that);
    }

    public double calcForceExertedByY(Planet that) {
        return this.calcForceExertedBy(that) * (that.yyPos - this.yyPos) / this.calcDistance(that);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double sum = 0;
        for (Planet planet : planets) {
            if (!planet.equals(this)) {
                sum += this.calcForceExertedByX(planet);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double sum = 0;
        for (Planet planet : planets) {
            if (!planet.equals(this)) {
                sum += this.calcForceExertedByY(planet);
            }
        }
        return sum;
    }

    public void update(double time, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel += time * ax;
        this.yyVel += time * ay;
        this.xxPos += time * this.xxVel;
        this.yyPos += time * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}
