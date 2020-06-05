package fr.emac.gipsi.gsi.voyage;

import fr.emac.gipsi.gsi.voyageur.AbstractVoyageur;

import java.util.ArrayList;

 class Voyage extends AbstractVoyage {


    public Voyage(ArrayList<Planete> listPlanete, AbstractVoyageur simulatedVoyageur) {
        super(listPlanete, simulatedVoyageur);
        // TODO Auto-generated constructor stub
    }


    public Voyage(ArrayList<Planete> listPlanete, AbstractVoyageur simulatedVoyageur, AbstractVoyageur realVoyager) {
        super(listPlanete, simulatedVoyageur, realVoyager);
        // TODO Auto-generated constructor stub
    }


    @Override
    public int showFromPlanete(Planete p) {
        // affichage animation sur une planète
        return 0;
    }


    @Override
    public int showAll() {
        // image en général?
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
