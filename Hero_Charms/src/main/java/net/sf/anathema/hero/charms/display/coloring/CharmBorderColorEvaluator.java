package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.presenter.RGBColor;

import java.util.Collection;

public class CharmBorderColorEvaluator {
	
	private final Collection<CharmBorderColorer> colorers;
	
	public CharmBorderColorEvaluator(ObjectFactory factory) {
		colorers = factory.instantiateAllImplementers(CharmBorderColorer.class);
	}
	
	public RGBColor getBorderColorForCharm(Charm charm) {
		for (CharmBorderColorer colorer : colorers) {
			if (colorer.handlesCharm(charm)) {
				return colorer.getBorderColorForCharm(charm);
			}
		}
		return RGBColor.Black;
	}
}
