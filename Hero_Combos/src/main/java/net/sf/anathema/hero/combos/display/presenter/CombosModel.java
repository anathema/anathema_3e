package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface CombosModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Combos");

  void addCharmToCombo(Charm charm, boolean experienced);

  void addComboModelListener(ChangeListener listener);

  void removeCharmsFromCombo(Charm[] charms);

  void finalizeCombo();

  Combo getEditCombo();

  void addComboConfigurationListener(ComboConfigurationListener listener);

  boolean isComboLegal(Charm charm);

  void deleteCombo(Combo combo);

  void clearCombo();

  void beginComboEdit(Combo combo);

  Combo[] getAllCombos();

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}