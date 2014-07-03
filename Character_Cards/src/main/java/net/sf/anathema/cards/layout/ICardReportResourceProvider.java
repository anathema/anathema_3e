package net.sf.anathema.cards.layout;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.spells.data.CircleType;

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
