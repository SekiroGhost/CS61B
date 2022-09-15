

public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
        {
                xxPos = xP;
                yyPos = yP;
                xxVel = xV;
                yyVel = yV;
                mass = m;
                imgFileName = img;
        }
/** Create a new Planet */
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
/** Calculate the distance between two planet */
    public double calcDistance(Planet p){
        double dx, dy;
        dx = (this.xxPos - p.xxPos);
        dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx*dx + dy * dy);
    }
/** Calculate the force between two planets */
    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        
        return G*this.mass*p.mass/(r * r);
    }

/** Calculate the force in x-direction */
public double calcForceExertedByX(Planet p){
    double F, dx, r;
    F = this.calcForceExertedBy(p);
    dx = p.xxPos - this.xxPos;
    r = this.calcDistance(p);
    return F * dx/r;
}
/** Calculate the net force in y-direction */
    public double calcForceExertedByY(Planet p){
        double F, dy, r;
        F = this.calcForceExertedBy(p);
        dy = p.yyPos - this.yyPos;
        r = this.calcDistance(p);
        return F * dy /r ;
    }
    /** Calculate the net force on x or y direction */

    public double calcNetForceExertedByX(Planet[] p){
        double res = 0;
        for (Planet pp : p){
            if (this.equals(pp)){
                continue;
            }
            
            res += calcForceExertedByX(pp);
            
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double res = 0;
        for (Planet pp : p){
            if (this.equals(pp)){
                continue;
            }
            
            res += calcForceExertedByY(pp);
            
        }
        return res;
    }

/** Update the position by force */

    public void update(double dt,double fx,double fy){
        double ax, ay;
        ax = fx/this.mass;
        ay = fy/this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

/**Draw the planet on the screen */

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}