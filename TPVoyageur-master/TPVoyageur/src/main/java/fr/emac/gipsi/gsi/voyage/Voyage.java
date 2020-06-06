package fr.emac.gipsi.gsi.voyage;

import fr.emac.gipsi.gsi.voyageur.AbstractVoyageur;

import java.util.ArrayList;

public class Voyage extends AbstractVoyage {

	private ArrayList<Planete> ordrePlaneteVoyage = new ArrayList<Planete>();

    public Voyage(ArrayList<Planete> listPlanete, AbstractVoyageur simulatedVoyageur) {
        super(listPlanete, simulatedVoyageur);
        // ici appeler fonction algo calcul chemin moins énergivore qui va retourner list ordonnée des planètes pour la stocker dans ordrePlaneteVoyage
        // PAS A FAIRE
    }


    public Voyage(ArrayList<Planete> listPlanete, AbstractVoyageur simulatedVoyageur, AbstractVoyageur realVoyager) {
        super(listPlanete, simulatedVoyageur, realVoyager);
        // PAS A FAIRE
    }


    @Override
    public int showFromPlanete(Planete p) {
        // affichage animation sur une planète (tout, roche, sol, photo)
        
    	return 0;
    }


    @Override
    public int showAll() {
        // PAS A FAIRE
        return 0;
    }


    @Override
    public void lancement() {
        // PAS A FAIRE

    }


    @Override
    public void lancementSimuler() {
        /*
         * for(Planete planete: ordrePlaneteVoyage){
         * 
         * }
         */
    	getSimulatedvoyageur().getPosTete().setX(2);
        afficheEcran();
    }
}
