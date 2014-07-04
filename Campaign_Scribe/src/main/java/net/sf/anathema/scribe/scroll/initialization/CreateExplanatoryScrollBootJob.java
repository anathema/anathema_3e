package net.sf.anathema.scribe.scroll.initialization;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.initialization.BootJob;
import net.sf.anathema.platform.initialization.RegisteredBootJob;
import net.sf.anathema.platform.item.ItemInitializer;

@RegisteredBootJob
@Weight(weight = 12)
public class CreateExplanatoryScrollBootJob implements BootJob {

  @Override
  public void run(Environment environment, ApplicationModel anathemaModel) {
    ScrollInitializationStrategy strategy = new ScrollInitializationStrategy(anathemaModel);
    new ItemInitializer(environment, strategy).initialize();
  }
}