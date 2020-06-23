package fr.emac.gipsi.gsi.launch;

import java.util.ArrayList;

import fr.emac.gipsi.gsi.ecran.ListScreen;
import fr.emac.gipsi.gsi.voyage.Planete;
import fr.emac.gipsi.gsi.voyage.Voyage;
import fr.emac.gipsi.gsi.voyageur.AbstractVoyageur;
import fr.emac.gipsi.gsi.voyageur.VoyageurSimuler;

//COCO tu casses les couilles à ne pas savoir utiliser ton ordi.
public class LaunchVoyage {

	public static void main(String[] args) {

		ArrayList<Planete> listPlanete = new ArrayList<>();

		Planete p1 = new Planete();
		p1.setColorName("DarkSalmon");
		p1.setImage(ListScreen.first());
		p1.setEchantillonRoche(ListScreen.second());
		p1.setEchantillonSol(ListScreen.first());
		p1.setRayon(1);
		p1.getPos().setX(2);
		p1.getPos().setY(2);

		listPlanete.add(p1);

		Planete p2 = new Planete();
		p2.setColorName("DeepSkyBlue");
		p2.setImage(ListScreen.first());
		p2.setEchantillonRoche(ListScreen.second());
		p2.setEchantillonSol(ListScreen.first());
		p2.setRayon(1);
		p2.getPos().setX(5);
		p2.getPos().setY(5);
		p2.getListAccessibilite().add(p1);

		listPlanete.add(p2);

		Planete p3 = new Planete();
		p3.setColorName("HotPink");
		p3.getPos().setX(2);
		p3.getPos().setY(12);
		p3.setEchantillonRoche(ListScreen.second());
		p3.setEchantillonSol(ListScreen.first());

		Planete p4 = new Planete();
		p4.setColorName("HotPink");
		p4.getPos().setX(7);
		p4.getPos().setY(2);
		p4.setEchantillonRoche(ListScreen.second());
		p4.setEchantillonSol(ListScreen.first());

		Planete p5 = new Planete();
		p5.setColorName("HotPink");
		p5.getPos().setX(7);
		p5.getPos().setY(7);
		p5.setEchantillonRoche(ListScreen.second());
		p5.setEchantillonSol(ListScreen.first());

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
		i++;

		listPlanete.get(i).getPos().setX(2);
		listPlanete.get(i).getPos().setY(7);
		listPlanete.get(i).setColorName("MediumVioletRed");
		i++;

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
		listPlanete.get(1).getListAccessibilite().remove(listPlanete.get(5));

		listPlanete.get(5).getListAccessibilite().remove(listPlanete.get(0));
		listPlanete.get(5).getListAccessibilite().remove(listPlanete.get(1));
		listPlanete.get(5).getListAccessibilite().remove(listPlanete.get(4));
		listPlanete.get(5).getListAccessibilite().remove(listPlanete.get(2));

		AbstractVoyageur simulatedVoyageur = new VoyageurSimuler();

		simulatedVoyageur.getPosTete().setX(listPlanete.get(0).getPos().getX());
		simulatedVoyageur.getPosTete().setY(listPlanete.get(0).getPos().getY() + 1);
		simulatedVoyageur.getPosBody().setX(listPlanete.get(0).getPos().getX());
		simulatedVoyageur.getPosBody().setY(listPlanete.get(0).getPos().getY());
		simulatedVoyageur.setDirection("E");

		Voyage voyage = new Voyage(listPlanete, simulatedVoyageur);

		voyage.lancementSimuler();
	}

}
