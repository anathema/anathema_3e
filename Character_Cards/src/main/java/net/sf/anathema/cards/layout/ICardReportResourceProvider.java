package net.sf.anathema.cards.layout;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.hero.spells.data.CircleType;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;

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
