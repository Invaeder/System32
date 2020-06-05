/**
 *
 */
package fr.emac.gipsi.gsi.voyageur;

/**
 * @author Truptil Sebastien - sebastien.truptil@gmail.com
 *
 */
public class VoyageurSimuler extends AbstractVoyageur {

    /**
     *
     */
	
	
    public VoyageurSimuler() {
        // ICI CALCUL DU CHEMIN
    }

    /* (non-Javadoc)
     * @see fr.emac.gipsi.gsi.voyageur.AbstractVoyageur#forward()
     */
    @Override
    
    
    protected void forward() {
    	String direction = getDirection();
    	int XBody = getPosBody().getX();
    	int YBody = getPosBody().getY();
    	int XTete = getPosTete().getX();
    	int YTete = getPosTete().getY();
    	if (direction == "N") {
    		getPosBody().setX(XBody - 1);
    		getPosTete().setX(XTete - 1);
    	}
    	else if (direction == "S") {
    		getPosBody().setX(XBody + 1);
    		getPosTete().setX(XTete + 1);
    	}
    	else if (direction == "E") {
    		getPosBody().setY(YBody + 1);
    		getPosTete().setY(YTete + 1);
		}
    	else if (direction == "W") {
    		getPosBody().setY(YBody - 1);
    		getPosTete().setY(YTete - 1);
		}
    	goForward();

    }

    
    protected void backward() {
    	String direction = getDirection();
    	int XBody = getPosBody().getX();
    	int YBody = getPosBody().getY();
    	int XTete = getPosTete().getX();
    	int YTete = getPosTete().getY();
    	if (direction == "N") {
    		getPosBody().setX(XBody - 1);
    		getPosTete().setX(XTete - 1);
    	}
    	else if (direction == "S") {
    		getPosBody().setX(XBody + 1);
    		getPosTete().setX(XTete + 1);
    	}
    	else if (direction == "E") {
    		getPosBody().setY(YBody - 1);
    		getPosTete().setY(YTete - 1);
		}
    	else if (direction == "W") {
    		getPosBody().setY(YBody + 1);
    		getPosTete().setY(YTete + 1);
		}
    	goBackward();
    }
    
    
    protected void left() {
        String direction = getDirection();
        int XBody = getPosBody().getX();
    	int YBody = getPosBody().getY();
        if (direction == "N") {
			setDirection("W");
			getPosTete().setX(XBody - 1);
			getPosTete().setY(YBody);
		}
        else if (direction == "S") {
			getDirection().setDirection("E");
			getPosTete().setX(XBody + 1);
			getPosTete().setY(YBody);
		}
        else if (direction == "E") {
        	setDirection("N");
			getPosTete().setX(XBody);
			getPosTete().setY(YBody + 1);
		}
        else if (direction == "W") {
        	setDirection("S");
			getPosTete().setX(XBody);
			getPosTete().setY(YBody - 1);
		}
        turnLeft();
    }
    
    
    protected void right() {
    	String direction = getDirection();
        int XBody = getPosBody().getX();
    	int YBody = getPosBody().getY();
        if (direction == "N") {
        	setDirection("E");
			getPosTete().setX(XBody + 1);
			getPosTete().setY(YBody);
		}
        else if (direction == "S") {
			setDirection("W");
			getPosTete().setX(XBody - 1);
			getPosTete().setY(YBody);
		}
        else if (direction == "E") {
        	setDirection("S");
			getPosTete().setX(XBody);
			getPosTete().setY(YBody - 1);
		}
        else if (direction == "W") {
        	setDirection("N");
			getPosTete().setX(XBody);
			getPosTete().setY(YBody + 1);
		}
        turnRight();
    }
}
