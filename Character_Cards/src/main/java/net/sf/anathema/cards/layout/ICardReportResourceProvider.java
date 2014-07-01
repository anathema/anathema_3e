package net.sf.anathema.cards.layout;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.spells.CircleType;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;

public interface ICardReportResourceProvider {

  Image getCardBaseImage();

  Image getCardStatBlockImage();

  Image getCardIconShadowImage();

  Image getCardIconBlockImage();

  Image getCardBodyBlockImage();

  Image getCategoryIcon(Charm charm);

  String getCategoryLabel(Charm charm);

  Image getTreeIcon(Charm charm);

  String getTreeLabel(Charm charm);

  Image getSpellIcon(CircleType circle);

  Image getNullIcon();

  Font getTitleFont();

  Font getBoldFont();

  Font getSymbolFont();

  Font getNormalFont();

  Font getKeywordFont();

  Font getSourceFont();
}
