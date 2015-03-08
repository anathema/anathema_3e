package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.hero.charms.sheet.content.mnemonic.AbstractMagicMnemonic;
import net.sf.anathema.hero.magic.sheet.content.IMagicStats;

import java.util.List;

public class CharmsOnlyMnemonic extends AbstractMagicMnemonic {

  public CharmsOnlyMnemonic(List<IMagicStats> printMagic) {
    super(printMagic);
  }
}
