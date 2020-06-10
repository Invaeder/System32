package fr.emac.gipsi.gsi.voyage;

import fr.emac.gipsi.gsi.animation.AbstractAnimation;
import fr.emac.gipsi.gsi.animation.AnimationFlash;
import fr.emac.gipsi.gsi.ecran.ListScreen;
import fr.emac.gipsi.gsi.voyageur.AbstractVoyageur;

import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

public class Voyage extends AbstractVoyage {

	private ArrayList<Planete> ordrePlaneteVoyage = new ArrayList<Planete>();

	public Voyage(ArrayList<Planete> listPlanete, AbstractVoyageur simulatedVoyageur) {
		super(listPlanete, simulatedVoyageur);
		// Pas à faire
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

	public void calculduchemin() {
		// algorythme de odrePlaneteVoyage
	}

	@Override
	public void lancementSimuler() {
		afficheEcran();
		calculduchemin();
		for (Planete planete : ordrePlaneteVoyage) {
			int xRover = getSimulatedvoyageur().getPosBody().getX();
			int yRover = getSimulatedvoyageur().getPosBody().getY();
			int xPlanete = planete.getPos().getX();
			int yPlanete = planete.getPos().getY();
			wait(300);
			if (xRover != xPlanete || yRover != yPlanete) {
				int xDistance = xPlanete - xRover;
				int yDistance = yPlanete - yRover;
				String direction = getSimulatedvoyageur().getDirection();
				while (xDistance != 0 || yDistance != 0) {
					if (direction == "N") {
						if (xDistance > 0) {
							while (xDistance != 0) {
								getSimulatedvoyageur().goBackward();
								xDistance = xPlanete - getSimulatedvoyageur().getPosBody().getX();
								afficheEcran();
								wait(300);
							}
						} else {
							while (xDistance != 0) {
								getSimulatedvoyageur().goForward();
								xDistance = xPlanete - getSimulatedvoyageur().getPosBody().getX();
								afficheEcran();
								wait(300);
							}
						}
						if (yDistance != 0) {
							getSimulatedvoyageur().turnLeft();
							afficheEcran();
							wait(300);
						}
					}
					if (direction == "S") {
						if (xDistance > 0) {
							while (xDistance != 0) {
								getSimulatedvoyageur().goForward();
								xDistance = xPlanete - getSimulatedvoyageur().getPosBody().getX();
								afficheEcran();
								wait(300);
							}
						} else {
							while (xDistance != 0) {
								getSimulatedvoyageur().goBackward();
								xDistance = xPlanete - getSimulatedvoyageur().getPosBody().getX();
								afficheEcran();
								wait(300);
							}
						}
						if (yDistance != 0) {
							getSimulatedvoyageur().turnLeft();
							afficheEcran();
							wait(300);
						}
					}
					if (direction == "E") {
						if (yDistance > 0) {
							while (yDistance != 0) {
								getSimulatedvoyageur().goForward();
								yDistance = yPlanete - getSimulatedvoyageur().getPosBody().getY();
								afficheEcran();
								wait(300);
							}
						} else {
							while (yDistance != 0) {
								getSimulatedvoyageur().goBackward();
								yDistance = yPlanete - getSimulatedvoyageur().getPosBody().getY();
								afficheEcran();
								wait(300);
							}
						}
						if (yDistance != 0) {
							getSimulatedvoyageur().turnLeft();
							afficheEcran();
							wait(300);
						}
					}
					if (direction == "W") {
						if (yDistance > 0) {
							while (yDistance != 0) {
								getSimulatedvoyageur().goBackward();
								yDistance = yPlanete - getSimulatedvoyageur().getPosBody().getY();
								afficheEcran();
								wait(300);
							}
						} else {
							while (yDistance != 0) {
								getSimulatedvoyageur().goForward();
								yDistance = yPlanete - getSimulatedvoyageur().getPosBody().getY();
								afficheEcran();
								wait(300);
							}
						}
						if (yDistance != 0) {
							getSimulatedvoyageur().turnLeft();
							afficheEcran();
							wait(300);
						}
					}
				}

			}
			// Ici on est sorti du "if", on est donc sur une planète (inch'Allah)
			
			if (planete.getEchantillonRoche() != null) {
				// /!\ Il faut rajouter le cas où on a déjà l'échantillon dans le if !!!!
				getSimulatedvoyageur().takeEchantillonRoche(planete);
				AbstractAnimation roche = new AnimationFlash();
				roche.setEcranDeb(planete.getImage());
				roche.setEcranFin(planete.getEchantillonRoche());
				wait(1000);
				AbstractAnimation retourPlaneteDeRoche = new AnimationFlash();
				retourPlaneteDeRoche.setEcranDeb(planete.getEchantillonRoche());
				retourPlaneteDeRoche.setEcranFin(planete.getImage());
			}

			
		}
		// Ici on est sortie de la boucle "for", si tout va bien on a : tous les
		// échantillons et toutes les photos.

	}
}
