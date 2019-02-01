public class Planet{
     
    public double xxPos; //Current x position
    public double yyPos; //Current y position
    public double xxVel; //Current vel in x direction
    public double yyVel; //Current vel in y direction
    public double mass;
    public String imgFileName;
    private static final double GCONSTANT = 6.67e-11;

    public Planet(double xxPos,double yyPos,double xxVel,double yyVel,double mass, String imgFileName){
     	this.xxPos = xxPos;
     	this.yyPos = yyPos;
     	this.xxVel = xxVel;
     	this.yyVel = yyVel;
     	this.mass = mass;
     	this.imgFileName = imgFileName;
    }

    public Planet(Planet p){
    	this.xxPos = p.xxPos;
    	this.yyPos = p.yyPos;
    	this.xxVel = p.xxVel;
    	this.yyVel = p.yyVel;
    	this.mass = p.mass;
    	this.imgFileName = p.imgFileName;
    }
    
    public double calcDistance(Planet p){
    	double distanceSquare = 0;
        double dxSquare = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos);
        double dySquare = (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
        distanceSquare = dxSquare + dySquare;
        return Math.sqrt(distanceSquare);
    }

    public double calcForceExertedBy(Planet p){
    	double force = 0;
    	double distance = this.calcDistance(p);
    	return GCONSTANT * this.mass * p.mass / (distance * distance);

    }

    public double calcForceExertedByX(Planet p){
    	double force = this.calcForceExertedBy(p);
    	double dx = p.xxPos - this.xxPos;
    	double r = this.calcDistance(p);

    	return force * dx / r ;
    } 

    public double calcForceExertedByY(Planet p){
    	double force = this.calcForceExertedBy(p);
    	double dx = p.yyPos - this.yyPos;
    	double r = this.calcDistance(p);

    	return force * dx / r ;
    }

    public double calcNetForceExertedByX(Planet[] planets){
    	double netForce = 0;

    	for (Planet p :planets ) {
    		if(! this.equals(p)){
    			netForce += this.calcForceExertedByX(p);
    		}
    	}
    	return netForce;
    } 

    public double calcNetForceExertedByY(Planet[] planets){
    	double netForce = 0;

    	for (Planet p :planets ) {
    		if(! this.equals(p)){
    			netForce += this.calcForceExertedByY(p);
    		}
    	}
    	return netForce;
    }

    public void update(double dt,double fX,double fY){
    	//Updates the pos and velocity when fx force is applied
    	//in x direction and similarily y. 
    	//for a small period of time dt.

    	//Steps calculate net acc,velocity then pos.

    	double netAccX = fX / this.mass;
    	double netAccY = fY / this.mass;

    	//Calculating new velocity
    	this.xxVel = this.xxVel + dt * netAccX;
    	this.yyVel = this.yyVel + dt * netAccY;

    	//Calculating new position
    	this.xxPos = this.xxPos + dt * xxVel;
    	this.yyPos = this.yyPos + dt * yyVel;

    } 

    //draws the planet using StdDraw API
    public void draw(){
        
        String img = "./images/" + this.imgFileName;
    	StdDraw.picture(this.xxPos,this.yyPos,img);

    }

}