package fr.emac.gipsi.gsi.launch;

import fr.emac.gipsi.gsi.ecran.ListScreen;
import fr.emac.gipsi.gsi.screen.Screen;
import fr.emac.gipsi.gsi.voyage.Planete;
import fr.emac.gipsi.gsi.voyage.Voyage;
import fr.emac.gipsi.gsi.voyageur.AbstractVoyageur;
import fr.emac.gipsi.gsi.voyageur.VoyageurSimuler;

import java.util.ArrayList;

public class LaunchVoyage {

	public static void main(String[] args) {
		ArrayList<Planete> listPlanete = new ArrayList<>();

		Planete p1 = new Planete();
		p1.setColorName("HotPink");
		p1.getPos().setX(2);
		p1.getPos().setY(2);
		p1.setEchantillonRoche(ListScreen.roche1());
		p1.setEchantillonSol(ListScreen.landscapeP1());
		p1.setImage(ListScreen.first());
		
		Planete p2 = new Planete();
		p2.setColorName("HotPink");
		p2.getPos().setX(2);
		p2.getPos().setY(7);
		p2.setEchantillonRoche(ListScreen.roche2());
		p2.setEchantillonSol(ListScreen.landscapeP2());
		p2.setImage(ListScreen.second());
		
		Planete p3 = new Planete();
		p3.setColorName("HotPink");
		p3.getPos().setX(2);
		p3.getPos().setY(12);
		p3.setEchantillonRoche(ListScreen.roche3());
		p3.setEchantillonSol(ListScreen.landscapeP3());
		p3.setImage(ListScreen.PlaneteAnol());
		
		Planete p4 = new Planete();
		p4.setColorName("HotPink");
		p4.getPos().setX(7);
		p4.getPos().setY(2);
		p4.setEchantillonRoche(ListScreen.roche4());
		p4.setEchantillonSol(ListScreen.landscapeP4());
		p4.setImage(ListScreen.billard());
		
		Planete p5 = new Planete();
		p5.setColorName("HotPink");
		p5.getPos().setX(7);
		p5.getPos().setY(7);
		p5.setEchantillonRoche(ListScreen.roche5());
		p5.setEchantillonSol(ListScreen.solExplorer());
		p5.setImage(ListScreen.Explorer());

		listPlanete.add(p1);
		listPlanete.add(p2);
		listPlanete.add(p3);
		listPlanete.add(p4);
		listPlanete.add(p5);

		for (Planete p : listPlanete) {
			p.setRayon(0);
		}

		while (listPlanete.size() > 6) {
			listPlanete.remove(listPlanete.size() - 1);
		}
		// position
		int i = 0;
		listPlanete.get(i).getPos().setX(4);
		listPlanete.get(i).getPos().setY(1);
		listPlanete.get(i).setColorName("Magenta");
		i++;
		listPlanete.get(i).getPos().setX(6);
		listPlanete.get(i).getPos().setY(4);
		listPlanete.get(i).setColorName("PapayaWhip");
		i++;
		listPlanete.get(i).getPos().setX(4);
		listPlanete.get(i).getPos().setY(4);
		listPlanete.get(i).setColorName("White");
		i++;
		listPlanete.get(i).getPos().setX(4);
		listPlanete.get(i).getPos().setY(7);
		listPlanete.get(i).setColorName("Red");
		i++;
		listPlanete.get(i).getPos().setX(4);
		listPlanete.get(i).getPos().setY(10);
		listPlanete.get(i).setColorName("Green");

		for (Planete p : listPlanete) {
			for (Planete subP : listPlanete) {
				if (!p.equals(subP)) {
					p.getListAccessibilite().add(subP);
				}
			}
		}

		// on enleve afin de faire la constellation
		listPlanete.get(1).getListAccessibilite().remove(listPlanete.get(0));
		listPlanete.get(1).getListAccessibilite().remove(listPlanete.get(3));
		listPlanete.get(1).getListAccessibilite().remove(listPlanete.get(4));

		AbstractVoyageur simulatedVoyageur = new VoyageurSimuler(); // voyageur qui va faire le parcours

		simulatedVoyageur.getPosTete().setX(listPlanete.get(0).getPos().getX());
		simulatedVoyageur.getPosTete().setY(listPlanete.get(0).getPos().getY()+1);
		simulatedVoyageur.getPosBody().setX(listPlanete.get(0).getPos().getX());
		simulatedVoyageur.getPosBody().setY(listPlanete.get(0).getPos().getY());
		simulatedVoyageur.setDirection("E");

		Voyage voyage = new Voyage(listPlanete, simulatedVoyageur);

		voyage.lancementSimuler();
	}

}