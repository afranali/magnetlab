//helped by Caroline Locke, Doug Altshuler, Maddie Culcasi

import objectdraw.*;
//import java.awt.*;


public class Magnet {
	//  set dimensions of magnets    
	private static final double MAGNET_WIDTH = 150;
	private static final double MAGNET_HEIGHT = 50;
	
	//calculates pole distance
	private static final double POLE_DISTANCE = MAGNET_HEIGHT/2;
	
	//  creates box representing perimeter of magnet
	private FramedRect box;
	
	//  creates poles of magnets
	private Pole northPole;
	private Pole southPole;
    
	// creates two magnets and poles at set location
	public Magnet(double magnet2x, double magnet2y, DrawingCanvas canvas) {
	   	  double x = 200;
	      double y = 200;
	      box = new FramedRect(x, y, MAGNET_WIDTH, MAGNET_HEIGHT, canvas);
	      northPole = new Pole ( this, x +15, y + POLE_DISTANCE, "N", canvas);
	      southPole = new Pole ( this, (x + MAGNET_WIDTH-15), y + POLE_DISTANCE, "S", canvas);
		}

		// returns the upper-left coordinates of the magnet
		public Location getLocation() {
	    	return box.getLocation();
		}


    // motion of magnet and poles to location point  
	public void moveAll(double xoff, double yoff) {
        	box.move(xoff, yoff);
        	northPole.move(xoff, yoff);
        	southPole.move(xoff, yoff);
	}
    // sets location where magnet and pole moving 
	public void moveTo(Location point) {
    	this.moveAll(point.getX()- box.getX(), point.getY()- box.getY());
	}

	// returns true if the given point is within the magnet
	public boolean contains(Location point) {
    	return box.contains(point);
	}

	// returns the width of the magnet
	public double getWidth() {
    	return MAGNET_WIDTH;
	}

	// returns the height of the magnet
	public double getHeight() {
    	return MAGNET_HEIGHT;
	}
    // flips the north and south poles of magnets
	public void flip() {
    	double north_x = northPole.getX();
    	double north_y = northPole.getY();  
    	double south_x = southPole.getX();
    	double south_y = southPole.getY();
         
    	northPole.move(south_x - north_x, south_y -north_y);
    	southPole.move(north_x - south_x, north_y - south_y);
	}
    // begins interaction between two magnets, moving if attracted or repelled
	public void interact(Magnet otherMagnet){
    	northPole.attract(otherMagnet.southPole);
    	northPole.repel(otherMagnet.northPole);
    	southPole.attract(otherMagnet.northPole);
    	southPole.repel(otherMagnet.southPole);
	}
   	 
}


