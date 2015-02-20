package net.sf.anathema.hero.equipment.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import net.sf.anathema.character.equipment.impl.character.model.stats.NullModifierFactory;
import net.sf.anathema.equipment.template.IEquipmentTemplate;
import net.sf.anathema.library.event.ChangeListener;

import org.junit.Test;

public class EquipmentItemTest {

  @Test
  public void notifiesListenersOfPersonalizationChange() throws Exception {
    IEquipmentTemplate template = mock(IEquipmentTemplate.class);
    EquipmentItem item = new EquipmentItem(template, new NullAttunementProvider(), new NullModifierFactory());
    ChangeListener listener = mock(ChangeListener.class);
    item.addChangeListener(listener);
    item.setPersonalization("Title", "Description");
    verify(listener).changeOccurred();
  }
}