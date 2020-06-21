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

	public void calculDistance(Planete p1, Planete p2) {
		if (p1.getListVisibilite().contains(p2)) {
			int xp1 = p1.getPos().getX();
			int yp1 = p1.getPos().getY();
			int xp2 = p2.getPos().getX();
			int yp2 = p2.getPos().getY();
			distance = Math.abs(xp1 - xp2) + Math.abs(yp1 - yp2);
		} else {
			distance = -1;
		}
	}

	public void calculduchemin() {
		// algorythme de odrePlaneteVoyage

		// Détermination de la planète de départ
		boolean again = true;
		int i = 0;
		int xRover = getSimulatedvoyageur().getPosBody().getX();
		int yRover = getSimulatedvoyageur().getPosBody().getY();
		Planete planeteDép = new Planete();
		while (again) {
			planeteDép = listPlanete.get(i);
			if (xRover == planeteDép.getPos().getX() && yRover == planeteDép.getPos().getY()) {
				ordrePlaneteVoyage.add(planeteDép);
				again = false;
			}
			i++;
		}

		// Création de la liste des planètes visitables :
		ArrayList<Planete> planetesVisitables = new ArrayList<Planete>();
		planetesVisitables.add(planeteDép);
		for (Planete pi : listPlanete) {
			if (pi.getEchantillonSol() != null && !planetesVisitables.contains(pi)) {
				planetesVisitables.add(pi);
			}
		}

		// Création de la matrice des distances entre les planètes visitables :
		ArrayList<ArrayList<Integer>> mDistance = new ArrayList<ArrayList<Integer>>();
		// mDistance.get(i).set(j, valeur);
		i = 0;
		for (Planete pLig : planetesVisitables) {
			for (Planete pCol : planetesVisitables) {
				calculDistance(pLig, pCol);
				mDistance.get(i).add(distance);
			}
			i++;
		}
		ArrayList<Planete> planetesVisitées = new ArrayList<Planete>();
		planetesVisitées.add(planeteDép);
		// Planete dernièrePlanete = planeteDép;
		ArrayList<Planete> culDeSac = new ArrayList<Planete>();
		for (Planete planete : listPlanete) {
			if (planete.getListAccessibilite().size() == 1) {
				culDeSac.add(planete);
			}
		}
		Planete pActuelle = planeteDép;
		ordrePlaneteVoyage.add(planeteDép);
		ArrayList<Integer> listDistancesActuelles = new ArrayList<>();
		while (planetesVisitables.size() != planetesVisitées.size()) {
			ArrayList<Planete> listAccesActuel = pActuelle.getListAccessibilite();
			for (Planete planete : listAccesActuel) {
				if (culDeSac.contains(planete)) {
					ordrePlaneteVoyage.add(planete);
					ordrePlaneteVoyage.add(pActuelle);
					planetesVisitées.add(planete);
				}
				calculDistance(pActuelle, planete);
				listDistancesActuelles.add(distance);
			}
			int minDist = listDistancesActuelles.get(0);
			int indice = 0;
			for (int j = 0; j < listAccesActuel.size(); j++) {
				if (!culDeSac.contains(listAccesActuel.get(j))) {
					indice = j;
				}
			}
			for (int i = 1; i < listDistancesActuelles.size(); i++) {
				int dist = listDistancesActuelles.get(i);
				if (dist < minDist && !planetesVisitées.contains(listAccesActuel.get(i))) {
					minDist = dist;
					indice = i;
				}
			}
			ordrePlaneteVoyage.add(listAccesActuel.get(indice));
			planetesVisitées.add(listAccesActuel.get(indice));
			pActuelle = listAccesActuel.get(indice);
		}

		/*
		 * ArrayList<ArrayList<Planete>> mPlanete = new ArrayList<ArrayList<Planete>>();
		 * i = 0; for (Planete p1 : planetesVisitables) { for (Planete p2 :
		 * planetesVisitables) { if (p1 != p2 && p1.getListAccessibilite().contains(p2))
		 * { mPlanete.get(i).add(p1); mPlanete.get(i).add(p2); } } i++; } int m =
		 * mPlanete.size(); ArrayList<ArrayList<Planete>> mChemins = new
		 * ArrayList<ArrayList<Planete>>(); for (int k = 0; k < m; k++) { if
		 * (mPlanete.get(i).get(0) == planeteDép) { while (planetesVisitables.size() !=
		 * planetesVisitées.size()) { for (int j = 0; j < m; j++) { if
		 * (planetesVisitées.get(-1) == mPlanete.get(j).get(0) && ) {
		 * 
		 * } } } } }
		 */

		/*
		 * ArrayList<ArrayList<Planete>> mChemins = new ArrayList<ArrayList<Planete>>();
		 * int i = 0;
		 *
		 * int j = 0; for (Planete p1 : dernièrePlanete.getListAccessibilite()) {
		 * 
		 * for (Planete p2 : p1.getListAccessibilite()) { while (planetesVisitées.size()
		 * != planetesVisitables.size()) { planetesVisitées.add(dernièrePlanete); if
		 * (culDeSac.contains(p2)) { planetesVisitées.add(p2);
		 * 
		 * } } j++; } dernièrePlanete = i++; }
		 */
	}

	@Override
	public void lancementSimuler() {
		afficheEcran();
		calculduchemin();
		ArrayList<Planete> planeteVisitée = new ArrayList<Planete>();
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
			if (!getSimulatedvoyageur().getListPhotographie().contains(planete.getImage())) {
				getSimulatedvoyageur().takePicture(planete);
			}
			if (planete.getEchantillonRoche() != null && !planeteVisitée.contains(planete)) {
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
			if (planete.getEchantillonSol() != null && !planeteVisitée.contains(planete)) {
				// /!\ Il faut rajouter le cas où on a déjà l'échantillon dans le if !!!!
				getSimulatedvoyageur().takeEchantillonSol(planete);
				AbstractAnimation sol = new AnimationFlash();
				sol.setEcranDeb(planete.getImage());
				sol.setEcranFin(planete.getEchantillonSol());
				wait(1000);
				AbstractAnimation retourPlaneteDeSol = new AnimationFlash();
				retourPlaneteDeSol.setEcranDeb(planete.getEchantillonSol());
				retourPlaneteDeSol.setEcranFin(planete.getImage());
			}
			if (!planeteVisitée.contains(planete)) {
				planeteVisitée.add(planete);
			}
		}
		// Ici on est sortie de la boucle "for", si tout va bien on a tous les
		// échantillons et toutes les photos.
	}
}
