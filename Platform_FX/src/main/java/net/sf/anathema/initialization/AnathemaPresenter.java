package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.messaging.MessageContainer;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.framework.view.messaging.StatusBar;

import java.util.Collection;

public class AnathemaPresenter {

  private final IApplicationModel model;
  private final ApplicationView view;
  private final Environment environment;

  public AnathemaPresenter(IApplicationModel model, ApplicationView view, Environment environment) {
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
  }

  private void runBootJobs() throws InitializationException {
    Collection<IBootJob> jobs = environment.instantiateOrdered(BootJob.class);
    for (IBootJob bootJob : jobs) {
      bootJob.run(environment, model);
    }
  }
}