package fr.emac.gipsi.gsi.voyage;

import fr.emac.gipsi.gsi.voyageur.AbstractVoyageur;

import java.util.ArrayList;

 class Voyage extends AbstractVoyage {


    public Voyage(ArrayList<Planete> listPlanete, AbstractVoyageur simulatedVoyageur) {
        super(listPlanete, simulatedVoyageur);
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
        // calcul chemin
    	getSimulatedvoyageur().getPosTete().setX(2);
        afficheEcran();
    }
}
