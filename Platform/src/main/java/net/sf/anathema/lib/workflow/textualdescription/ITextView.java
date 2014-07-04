package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.library.event.ObjectValueListener;

public interface ITextView {

  void addTextChangedListener(ObjectValueListener<String> listener);

  void setEnabled(boolean enabled);

  void setText(String text);

  @SuppressWarnings("UnusedDeclaration")
  void removeAllListeners();
}