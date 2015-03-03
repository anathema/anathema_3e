package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.template.IEquipmentTemplate;
import net.sf.anathema.library.event.ChangeListener;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EquipmentItemTest {

  @Test
  public void notifiesListenersOfPersonalizationChange() throws Exception {
    IEquipmentTemplate template = mock(IEquipmentTemplate.class);
    EquipmentItem item = new EquipmentItem(template);
    ChangeListener listener = mock(ChangeListener.class);
    item.addChangeListener(listener);
    item.setPersonalization("Title", "Description");
    verify(listener).changeOccurred();
  }
}