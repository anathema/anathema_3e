package net.sf.anathema.hero.charms.model;

import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.magic.data.attribute.MagicAttributeImpl;

public class CommonMagicAttributes {
	public static MagicAttribute NO_MANUAL_CONTROL = new MagicAttributeImpl("NotManuallyLearnable", false);
	public static MagicAttribute NO_COST = new MagicAttributeImpl("DoesNotCountAsCharmPurchase", false);
}