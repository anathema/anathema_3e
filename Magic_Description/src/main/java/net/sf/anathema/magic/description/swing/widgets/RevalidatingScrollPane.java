package net.sf.anathema.magic.description.swing.widgets;

import java.awt.Component;

import javax.swing.JScrollPane;

import static net.sf.anathema.magic.description.swing.GuiUtilities.revalidateTree;

public class RevalidatingScrollPane extends JScrollPane {

  public RevalidatingScrollPane(Component view) {
    super(view);
  }

  @Override
  public void revalidate() {
    revalidateTree(this);
  }
}