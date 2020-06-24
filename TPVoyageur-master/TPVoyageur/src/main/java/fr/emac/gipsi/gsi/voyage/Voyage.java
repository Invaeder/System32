package fr.emac.gipsi.gsi.voyage;

import fr.emac.gipsi.gsi.animation.AbstractAnimation;
import fr.emac.gipsi.gsi.animation.AnimationFlash;
import fr.emac.gipsi.gsi.ecran.ListScreen;
import fr.emac.gipsi.gsi.voyageur.AbstractVoyageur;
import fr.emac.gipsi.gsi.voyageur.VoyageurSimuler;

import java.awt.List;
import java.lang.reflect.Array;
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

	private int distance = -1;

	public void calculDistance(Planete planete1, Planete planete2) {
		if (planete1.getListVisibilite().contains(planete2)) {
			int xp1 = planete1.getPos().getX();
			int yp1 = planete1.getPos().getY();
			int xp2 = planete2.getPos().getX();
			int yp2 = planete2.getPos().getY();
			distance = Math.abs(xp1 - xp2) + Math.abs(yp1 - yp2);
		} else {
			distance = -1;
		}
	}

	/**
	 * 
	 */
	public void calculduchemin() {
		// algorythme de odrePlaneteVoyage

		// Détermination de la planète de départ
		boolean again = true;
		int i = 0;
		int xRover = getSimulatedvoyageur().getPosBody().getX();
		int yRover = getSimulatedvoyageur().getPosBody().getY();
		Planete planeteDép = new Planete();
		ArrayList<Planete> listePlanetePropre = new ArrayList<Planete>();

		// J'enlève les null de listPlanete parce qu'il y en a et ça gène
		for (Planete pPropre : listPlanete) {
			if (!pPropre.equals(null)) {
				listePlanetePropre.add(pPropre);
			}
		}
		while (again) {
			planeteDép = listePlanetePropre.get(i);
			if (xRover == planeteDép.getPos().getX() && yRover == planeteDép.getPos().getY()) {
				ordrePlaneteVoyage.add(planeteDép);
				again = false;
			}
			i++;
		}

		// Création de la liste des planètes visitables :
		ArrayList<Planete> planetesVisitables = new ArrayList<Planete>();
		planetesVisitables.add(planeteDép);
		for (Planete pi : listePlanetePropre) {
			if (pi.getEchantillonSol() != null && !planetesVisitables.contains(pi)) {
				planetesVisitables.add(pi);
			}
		}
		ArrayList<Planete> planetesVisitees = new ArrayList<Planete>();
		planetesVisitees.add(planeteDép);
		ArrayList<Planete> culDeSac = new ArrayList<Planete>();
		for (Planete planete : listePlanetePropre) {
			if (planete.getListAccessibilite().size() == 1) {
				culDeSac.add(planete);
			}
		}
		Planete pActuelle = planeteDép;
		ordrePlaneteVoyage.add(planeteDép);
		ArrayList<Integer> listDistancesActuelles = new ArrayList<>();
		while (planetesVisitables.size() < planetesVisitees.size()) {
			ArrayList<Planete> listAccesActuel = pActuelle.getListAccessibilite();
			for (Planete planete : listAccesActuel) {
				if (culDeSac.contains(planete)) {
					ordrePlaneteVoyage.add(planete);
					ordrePlaneteVoyage.add(pActuelle);
					planetesVisitees.add(planete);
				}
				calculDistance(pActuelle, planete);
				listDistancesActuelles.add(distance);
			}
			int minDist = listDistancesActuelles.get(0);
			int indice = 0;
			for (int k = 1; k < listAccesActuel.size(); k++) {
				int dist = listDistancesActuelles.get(k);
				if (dist <= minDist && !planetesVisitees.contains(listAccesActuel.get(k))) {
					minDist = dist;
					indice = k;
				}
			}
			ordrePlaneteVoyage.add(listAccesActuel.get(indice));
			planetesVisitees.add(listAccesActuel.get(indice));
			pActuelle = listAccesActuel.get(indice);
		}
		// On enlève les potentiels doublons
		for (int j = 1; j < ordrePlaneteVoyage.size(); j++) {
			if (ordrePlaneteVoyage.get(j) == ordrePlaneteVoyage.get(j-1)) {
				ordrePlaneteVoyage.remove(j);
			}
		}
	}

	@Override
	public void lancementSimuler() {
		afficheEcran();
		calculduchemin();
		ArrayList<Planete> planeteVisitee = new ArrayList<Planete>();
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
					direction = getSimulatedvoyageur().getDirection();
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
					direction = getSimulatedvoyageur().getDirection();
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
					direction = getSimulatedvoyageur().getDirection();
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
					direction = getSimulatedvoyageur().getDirection();
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
			if (!getSimulatedvoyageur().getListPhotographie().contains(planete.getImage())) {
				getSimulatedvoyageur().takePicture(planete);
			}
			if (!planete.getEchantillonRoche().equals(null) && !planeteVisitee.contains(planete)
					&& !getSimulatedvoyageur().getListEchantillonRoche().contains(planete.getEchantillonRoche())) {
				getSimulatedvoyageur().takeEchantillonRoche(planete);
				AbstractAnimation roche = new AnimationFlash();
				roche.setEcranDeb(planete.getImage());
				roche.setEcranFin(planete.getEchantillonRoche());
				roche.runAnimation();
				wait(1000);
				AbstractAnimation retourPlaneteDeRoche = new AnimationFlash();
				retourPlaneteDeRoche.setEcranDeb(planete.getEchantillonRoche());
				retourPlaneteDeRoche.setEcranFin(planete.getImage());
				retourPlaneteDeRoche.runAnimation();
			}
			if (!planete.getEchantillonSol().equals(null) && !planeteVisitee.contains(planete)
					&& !getSimulatedvoyageur().getListEchantillonSol().contains(planete.getEchantillonSol())) {
				getSimulatedvoyageur().takeEchantillonSol(planete);
				AbstractAnimation sol = new AnimationFlash();
				sol.setEcranDeb(planete.getImage());
				sol.setEcranFin(planete.getEchantillonSol());
				sol.runAnimation();
				wait(1000);
				AbstractAnimation retourPlaneteDeSol = new AnimationFlash();
				retourPlaneteDeSol.setEcranDeb(planete.getEchantillonSol());
				retourPlaneteDeSol.setEcranFin(planete.getImage());
				retourPlaneteDeSol.runAnimation();
			}
			if (!planeteVisitee.contains(planete)) {
				planeteVisitee.add(planete);
			}
			// Prise de photo des planètes accessible de nul part mais visible de là où on
			// est
			for (Planete planetegaz : planete.getListVisibilite()) {
				int xPlanetegaz = planetegaz.getPos().getX();
				int yPlanetegaz = planetegaz.getPos().getY();
				if (planetegaz.getEchantillonSol().equals(null)
						&& !getSimulatedvoyageur().getListPhotographie().contains(planetegaz.getImage())) {
					if (yPlanetegaz - yRover == 0) {
						if (xPlanetegaz - xRover > 0) {
							if (getSimulatedvoyageur().getDirection() == "E") {
								getSimulatedvoyageur().turnRight();
							} else {
								while (getSimulatedvoyageur().getDirection() != "S") {
									getSimulatedvoyageur().turnLeft();
								}
							}
						}
					} else if (yPlanetegaz - yRover > 0) {
						if (getSimulatedvoyageur().getDirection() == "N") {
							getSimulatedvoyageur().turnRight();
						} else {
							while (getSimulatedvoyageur().getDirection() != "E") {
								getSimulatedvoyageur().turnLeft();
							}
						}
					}
				}
			}
		}
		// Ici on est sortie de la boucle "for", si tout va bien on a tous les
		// échantillons et toutes les photos.
	}
}
