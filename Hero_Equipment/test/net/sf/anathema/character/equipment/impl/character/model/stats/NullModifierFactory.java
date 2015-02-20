package net.sf.anathema.character.equipment.impl.character.model.stats;

import static org.mockito.Mockito.mock;
import net.sf.anathema.equipment.stats.IWeaponModifiers;
import net.sf.anathema.hero.equipment.model.ModifierFactory;

public class NullModifierFactory implements ModifierFactory {
  @Override
  public IWeaponModifiers createModifiers() {
    return mock(IWeaponModifiers.class);
  }
}