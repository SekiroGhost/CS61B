public class NBody {
 
    /** Read the universal radius in the file */
    public static double readRadius(String s){
        In in = new In(s);
        int n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** Read the planet in the file */
    public static Planet[] readPlanets(String s){
        Planet[] res;
        In in = new In(s);
        int n = in.readInt();
        res = new Planet[n];
        double r = in.readDouble();
        for (int i = 0; i < res.length; i ++){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            res[i] = new Planet(xp,yp,xv,yv,mass,name);
        }
        return res;

    }
/**Drawing part */

    private static void drawBackGround(double r) {
        /** Sets up the universe so it goes from 
         * radius */
        String backGround = "./images/starfield.jpg";
        StdDraw.setScale(-r, r);

        /* Clears the drawing window. */
        StdDraw.clear();

        /* Stamps three copies of advice.png in a triangular pattern. */
        StdDraw.picture(0, 0, backGround);

        /* Shows the drawing to the screen, and waits 2000 milliseconds. */
        StdDraw.show();
    }



    public static void main(String[] args){
        double T,dt;
        String filename = args[2];
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        Planet[] p = readPlanets(filename);
        double radius = readRadius(filename);
        drawBackGround(radius);
        //System.out.println(p[0].calcNetForceExertedByY(p));
        for (int i = 0; i < p.length; i++){
            p[i].draw();
        }
        int time = 0;

        while (time < T){
            double[] xForces, yForces;
            xForces = new double[p.length];
            yForces = new double[p.length];
            for (int i = 0; i < p.length; i++){
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
                p[i].update(dt, xForces[i], yForces[i]);
            }
            drawBackGround(radius);
            for (int i = 0; i < p.length; i++){
                p[i].draw();
            }
        StdDraw.show();
        StdDraw.pause(10);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        time += dt;
        }
        StdOut.printf("%d\n",p.length);
        StdOut.printf("%.2e\n",radius);
        for (int i = 0; i < p.length; i ++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                              p[i].xxPos,p[i].yyPos,p[i].xxVel,p[i].yyVel,p[i].mass,p[i].imgFileName);
        }

    }
}
