package fr.emac.gipsi.gsi.launch;

import fr.emac.gipsi.gsi.animation.AbstractAnimation;
import fr.emac.gipsi.gsi.animation.AnimationByColumn;
import fr.emac.gipsi.gsi.animation.AnimationFlash;
import fr.emac.gipsi.gsi.ecran.ListScreen;


public class LaunchAnimation {

	
	public static void main(String[] args) {
		
		AbstractAnimation aa = new AnimationFlash();
		aa.setEcranDeb(ListScreen.first());
		aa.setEcranFin(ListScreen.second());
		
		aa.runAnimation();

		aa.wait(1000);
		
		AbstractAnimation ab = new AnimationFlash();
		ab.setEcranDeb(ListScreen.second());
		ab.setEcranFin(ListScreen.first());
		
		ab.runAnimation();

	}

}
