package fr.emac.gipsi.gsi.voyageur;


public class VoyageurSimuler extends AbstractVoyageur {


	
	
    public VoyageurSimuler() {
    }


    @Override
    
    
    protected void forward() {
    	String direction = getDirection();
    	int xBody = getPosBody().getX();
    	int yBody = getPosBody().getY();
    	int xTete = getPosTete().getX();
    	int yTete = getPosTete().getY();
    	if (direction == "N") {
    		getPosBody().setX(xBody - 1);
    		getPosTete().setX(xTete - 1);
    	}
    	else if (direction == "S") {
    		getPosBody().setX(xBody + 1);
    		getPosTete().setX(xTete + 1);
    	}
    	else if (direction == "E") {
    		getPosBody().setY(yBody + 1);
    		getPosTete().setY(yTete + 1);
		}
    	else if (direction == "W") {
    		getPosBody().setY(yBody - 1);
    		getPosTete().setY(yTete - 1);
		}
    }

    
    protected void backward() {
    	String direction = getDirection();
    	int xBody = getPosBody().getX();
    	int yBody = getPosBody().getY();
    	int xTete = getPosTete().getX();
    	int yTete = getPosTete().getY();
    	if (direction == "N") {
    		getPosBody().setX(xBody + 1);
    		getPosTete().setX(xTete + 1);
    	}
    	else if (direction == "S") {
    		getPosBody().setX(xBody - 1);
    		getPosTete().setX(xTete - 1);
    	}
    	else if (direction == "E") {
    		getPosBody().setY(yBody - 1);
    		getPosTete().setY(yTete - 1);
		}
    	else if (direction == "W") {
    		getPosBody().setY(yBody + 1);
    		getPosTete().setY(yTete + 1);
		}
    }
    
    
    protected void left() {
        String direction = getDirection();
        int xBody = getPosBody().getX();
    	int yBody = getPosBody().getY();
        if (direction == "N") {
			setDirection("W");
			getPosTete().setX(xBody);
			getPosTete().setY(yBody - 1);
		}
        else if (direction == "S") {
			setDirection("E");
			getPosTete().setX(xBody);
			getPosTete().setY(yBody + 1);
		}
        else if (direction == "E") {
        	setDirection("N");
			getPosTete().setX(xBody - 1);
			getPosTete().setY(yBody);
		}
        else if (direction == "W") {
        	setDirection("S");
			getPosTete().setX(xBody + 1);
			getPosTete().setY(yBody);
		}
    }
    
    
    protected void right() {
    	String direction = getDirection();
        int xBody = getPosBody().getX();
    	int yBody = getPosBody().getY();
        if (direction == "N") {
        	setDirection("E");
        	getPosTete().setX(xBody);
			getPosTete().setY(yBody + 1);
		}
        else if (direction == "S") {
			setDirection("W");
			getPosTete().setX(xBody);
			getPosTete().setY(yBody - 1);
		}
        else if (direction == "E") {
        	setDirection("S");
        	getPosTete().setX(xBody + 1);
			getPosTete().setY(yBody);
		}
        else if (direction == "W") {
        	setDirection("N");
        	getPosTete().setX(xBody - 1);
			getPosTete().setY(yBody);
		}
    }
}
