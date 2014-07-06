package net.sf.anathema.platform.fx.initialization;

import net.sf.anathema.library.initialization.InitializationException;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.frame.ApplicationView;
import net.sf.anathema.platform.initialization.BootJob;
import net.sf.anathema.platform.initialization.RegisteredBootJob;
import net.sf.anathema.platform.messaging.MessageContainer;
import net.sf.anathema.platform.messaging.StatusBar;

import java.util.Collection;

public class AnathemaPresenter {

  private final ApplicationModel model;
  private final ApplicationView view;
  private final Environment environment;

  public AnathemaPresenter(ApplicationModel model, ApplicationView view, Environment environment) {
    this.model = model;
    this.view = view;
    this.environment = environment;
  }

  public void initPresentation() throws InitializationException {
    runBootJobs();
    initMessaging();
  }

  private void initMessaging() {
    StatusBar statusBar = view.getStatusBar();
    MessageContainer messageContainer = model.getMessageContainer();
    messageContainer.addChangeListener(() -> showLatestMessage(messageContainer));
    showLatestMessage(messageContainer);
    statusBar.whenAllMessagesAreRequested(() -> statusBar.showMessages(messageContainer.getPermanentMessages()));
  }

  private void showLatestMessage(MessageContainer messageContainer) {
    if (messageContainer.hasMessages()) {
      view.getStatusBar().setLatestMessage(messageContainer.getLatestMessage());
    }
    else{
      view.getStatusBar().clear();
    }
  }

  private void runBootJobs() throws InitializationException {
    Collection<BootJob> jobs = environment.getObjectFactory().instantiateOrdered(RegisteredBootJob.class);
    for (BootJob bootJob : jobs) {
      bootJob.run(environment, model);
    }
  }
}