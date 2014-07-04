package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.library.event.ObjectChangedListener;

public interface ITextView {

  void addTextChangedListener(ObjectChangedListener<String> listener);

  void setEnabled(boolean enabled);

  void setText(String text);

  @SuppressWarnings("UnusedDeclaration")
  void removeAllListeners();
}