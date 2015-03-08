package net.sf.anathema.hero.martial;

import net.sf.anathema.magic.data.Charm;

public class MartialArtUtilities {
	public static boolean isMartialArts(Charm charm) {
		return charm.getTreeReference().category.text.equals("MartialArts");
	}
}
