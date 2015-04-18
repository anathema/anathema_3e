package net.sf.anathema.hero.martial;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CategoryReference;

public class MartialArtUtilities {
	public static boolean isMartialArts(Charm charm) {
		return charm.getTreeReference().category.equals(new CategoryReference("MartialArts"));
	}
}
