package net.sf.anathema.magic.description.swing;

import net.sf.anathema.magic.description.swing.widgets.RevalidatingScrollPane;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AreaTextView extends TextView {

  private JScrollPane scrollPane;

  public AreaTextView(int rows, int columns) {
    super(new JTextArea(rows, columns));
    this.scrollPane = new RevalidatingScrollPane(super.getComponent());
    JTextArea textArea = (JTextArea) getTextComponent();
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
  }

  @Override
  public JComponent getComponent() {
    return scrollPane;
  }
}