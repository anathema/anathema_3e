package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.library.presenter.RGBColor;

public interface CharmBorderColorer {
	boolean handlesCharm(Charm charm);
	
	RGBColor getBorderColorForCharm(Charm charm);
}
