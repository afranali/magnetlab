import objectdraw.*;

public class MagnetGame extends WindowController {
    private Magnet magnet1, magnet2;
    private Magnet movingMagnet, otherMagnet;// magnet being dragged and one at rest 
    private boolean drag;// determines whether or not magnet is being dragged
    private Location prePoint;// previous location of mouse
   
   public void begin(){
	 //creates magnets
   	 magnet1 = new Magnet(50,100, canvas);
   	 magnet2 = new Magnet(250,100, canvas);   
    }

	public void onMousePress(Location point) {
	// determining if a magnet is being pressed and setting drag to true if so
	// determining which magnet mouse is on
    	prePoint = point;
    	if(magnet1.contains(point)) {
   		 movingMagnet = magnet1;
   		 otherMagnet = magnet2;
   		 drag = true;       	
    	}
   	    else if (magnet2.contains(point)) {
        	movingMagnet = magnet2;
        	otherMagnet = magnet1;
        	drag = true;
    	}
    	else drag = false;
	}
	// move pressed-on magnet as far as mouse is dragged 
	public void onMouseDrag(Location point) {
    boolean drag = true;
		movingMagnet.moveAll(point.getX()-prePoint.getX(), point.getY()-prePoint.getY());//move by distance from previous point
		movingMagnet.interact(otherMagnet);//test interaction with other magnet
		prePoint = point;
	}
	
    //flip magnet if it is clicked on/ reverses the poles 
	public void onMouseClick(Location point) {
    	if(magnet1.contains(point)) {
        	magnet1.flip();
        	magnet1.interact(magnet2);//test interaction
    	}
    	
    	else if (magnet2.contains(point)) {
            	magnet2.flip();
            	magnet2.interact(magnet1);// test interaction
    	}
	}
    
	public static void main(String[] args) {
    	new MagnetGame().startController(500,500);
	}

}


