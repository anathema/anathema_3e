package net.sf.anathema.platform.fx.preferences;

import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.fx.text.FxTextView;
import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.platform.environment.DesktopEnvironment;
import net.sf.anathema.platform.preferences.PreferenceView;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@SuppressWarnings("UnusedDeclaration")
public class FxRepositoryPreferenceView implements PreferenceView, NodeHolder, RepositoryPreferenceView {

  private final MigPane pane = new MigPane(LayoutUtils.fillWithoutInsets());
  private final Announcer<ObjectChangedListener> announcer = Announcer.to(ObjectChangedListener.class);

  @Override
  public ITextView addRepositoryDisplay(String label) {
    FxTextView view = FxTextView.SingleLine(label);
    pane.add(view.getNode(), new CC().alignX("right").alignY("top"));
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }

  @Override
  public Tool addTool() {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    pane.add(tool.getNode(), new CC().alignX("right").alignY("top"));
    return tool;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void selectNewRepository(String prompt) {
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle(prompt);
    File file = chooser.showDialog(null);
    if (file != null) {
      announcer.announce().valueChanged(file.toPath());
    }
  }

  @Override
  public void whenRepositoryChangeIsRequested(ObjectChangedListener<Path> objectChangedListener) {
    announcer.addListener(objectChangedListener);
  }

  @Override
  public void showInExplorer(Path repositoryPath) {
    try {
      DesktopEnvironment.openOnDesktop(repositoryPath);
    } catch (IOException e) {
      throw new RuntimeException("Please check via 'canOpenInExplorer()' before calling this method.", e);
    }
  }

  @Override
  public boolean canOpenInExplorer() {
    return DesktopEnvironment.isOpenSupported();
  }
}