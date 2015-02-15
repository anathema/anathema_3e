package net.sf.anathema.hero.evocations.display;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.display.coloring.CharmBorderColorer;
import net.sf.anathema.hero.charms.evocations.utilities.EvocationUtilities;
import net.sf.anathema.library.presenter.RGBColor;

import static net.sf.anathema.charm.template.evocations.EvocationTier.None;


@SuppressWarnings("UnusedDeclaration") //Automatically instantiated in CharmBorderColorEvaluator 
public class EvocationBorderColorer implements CharmBorderColorer {

  public static final RGBColor Emerald = new RGBColor(80, 200, 120);
  public static final RGBColor Sapphire = new RGBColor(15, 82, 186);
  public static final RGBColor Adamant = new RGBColor(218,165,32);
	
	@Override
	public boolean handlesCharm(Charm charm) {
		return EvocationUtilities.getTier(charm) != None;
	}

	@Override
	public RGBColor getBorderColorForCharm(Charm charm) {
		switch (EvocationUtilities.getTier(charm)) {
		case Emerald: return Emerald;
		case Sapphire: return Sapphire;
		case Adamant: return Adamant;
		}
		return RGBColor.Black;
	}

}
