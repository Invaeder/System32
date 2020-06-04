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
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see fr.emac.gipsi.gsi.voyageur.AbstractVoyageur#forward()
     */
    @Override
    protected void forward() {
        // TODO Auto-generated method stub
    	String direction = getDirection();
    	if (direction == "N") {
    		getPosBody().setX(getPosBody().getX() + 1);
    		getPosTete().setX(getPosTete().getX() + 1);
    		else if (direction == "S") {
    			
    		}
    	goForward();

    }

    /* (non-Javadoc)
     * @see fr.emac.gipsi.gsi.voyageur.AbstractVoyageur#backward()
     */
    @Override
    protected void backward() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see fr.emac.gipsi.gsi.voyageur.AbstractVoyageur#left()
     */
    @Override
    protected void left() {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see fr.emac.gipsi.gsi.voyageur.AbstractVoyageur#right()
     */
    @Override
    protected void right() {
        // TODO Auto-generated method stub
    }
}
