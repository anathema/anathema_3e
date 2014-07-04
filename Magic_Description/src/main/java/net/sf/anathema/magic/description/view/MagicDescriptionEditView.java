package net.sf.anathema.magic.description.view;

import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.magic.description.swing.AreaTextView;
import net.sf.anathema.magic.description.swing.IView;
import net.sf.anathema.magic.description.swing.TextView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MagicDescriptionEditView implements IView {

  private final TextView descriptionView = new AreaTextView(10, 1);
  private final JPanel editorPanel = new JPanel(new BorderLayout());

  public MagicDescriptionEditView() {
    editorPanel.add(descriptionView.getComponent(), BorderLayout.CENTER);
  }

  @Override
  public JComponent getComponent() {
    return editorPanel;
  }

  public void setDescription(String description) {
    descriptionView.setText(description);
  }

  public void addDescriptionChangeListener(ObjectChangedListener<String> listener) {
    descriptionView.addTextChangedListener(listener);
  }
}
