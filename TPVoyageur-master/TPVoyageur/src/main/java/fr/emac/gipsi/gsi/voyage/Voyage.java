package fr.emac.gipsi.gsi.voyage;

import fr.emac.gipsi.gsi.voyageur.AbstractVoyageur;

import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

public class Voyage extends AbstractVoyage {

	private ArrayList<Planete> ordrePlaneteVoyage = new ArrayList<Planete>();

	public Voyage(ArrayList<Planete> listPlanete, AbstractVoyageur simulatedVoyageur) {
		super(listPlanete, simulatedVoyageur);
		// ici appeler fonction algo calcul chemin moins énergivore qui va retourner
		// list ordonnée des planètes pour la stocker dans ordrePlaneteVoyage
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

		for (Planete planete : ordrePlaneteVoyage) {
			int xRover = getSimulatedvoyageur().getPosBody().getX();
			int yRover = getSimulatedvoyageur().getPosBody().getY();
			int xPlanete = planete.getPos().getX();
			int yPlanete = planete.getPos().getY();
			if (xRover != xPlanete && yRover != yPlanete) {
				int xDistance = xPlanete - xRover;
				int yDistance = yPlanete - yRover;
				String direction = getSimulatedvoyageur().getDirection();
				while (xDistance != 0 && yDistance != 0) {
					if (direction == "N") {
						if (xDistance > 0) {
							while (xDistance != 0) {
								getSimulatedvoyageur().goBackward();
								xDistance = xPlanete - getSimulatedvoyageur().getPosBody().getX();
							}
						} else {
							while (xDistance != 0) {
								getSimulatedvoyageur().goForward();
								xDistance = xPlanete - getSimulatedvoyageur().getPosBody().getX();
							}
						}
						if (yDistance != 0) {
							getSimulatedvoyageur().turnLeft();
						}
					}
					if (direction == "S") {
						if (xDistance > 0) {
							while (xDistance != 0) {
								getSimulatedvoyageur().goForward();
								xDistance = xPlanete - getSimulatedvoyageur().getPosBody().getX();
							}
						} else {
							while (xDistance != 0) {
								getSimulatedvoyageur().goBackward();
								xDistance = xPlanete - getSimulatedvoyageur().getPosBody().getX();
							}
						}
						if (yDistance != 0) {
							getSimulatedvoyageur().turnLeft();
						}
					}
					if (direction == "E") {
						if (yDistance > 0) {
							while (yDistance != 0) {
								getSimulatedvoyageur().goForward();
								yDistance = yPlanete - getSimulatedvoyageur().getPosBody().getY();
							}
						} else {
							while (yDistance != 0) {
								getSimulatedvoyageur().goBackward();
								yDistance = yPlanete - getSimulatedvoyageur().getPosBody().getY();
							}
						}
						if (yDistance != 0) {
							getSimulatedvoyageur().turnLeft();
						}
					}
					if (direction == "W") {
						if (yDistance > 0) {
							while (yDistance != 0) {
								getSimulatedvoyageur().goBackward();
								yDistance = yPlanete - getSimulatedvoyageur().getPosBody().getY();
							}
						} else {
							while (yDistance != 0) {
								getSimulatedvoyageur().goForward();
								yDistance = yPlanete - getSimulatedvoyageur().getPosBody().getY();
							}
						}
						if (yDistance != 0) {
							getSimulatedvoyageur().turnLeft();
						}
					}
				}
			}
		}

		afficheEcran();
	}
}
