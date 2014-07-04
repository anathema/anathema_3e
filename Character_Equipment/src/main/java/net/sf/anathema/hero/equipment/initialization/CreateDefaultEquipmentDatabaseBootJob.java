package net.sf.anathema.hero.equipment.initialization;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.initialization.BootJob;
import net.sf.anathema.platform.initialization.RegisteredBootJob;
import net.sf.anathema.platform.item.ItemInitializer;
import net.sf.anathema.platform.item.RepositoryItemInitializationStrategy;

@RegisteredBootJob
@Weight(weight = 11)
public class CreateDefaultEquipmentDatabaseBootJob implements BootJob {

  @Override
  public void run(Environment environment, ApplicationModel anathemaModel) {
    RepositoryItemInitializationStrategy strategy = new EquipmentInitializationStrategy(anathemaModel);
    new ItemInitializer(environment, strategy).initialize();
  }
}