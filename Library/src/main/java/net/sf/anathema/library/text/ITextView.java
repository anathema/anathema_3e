package net.sf.anathema.library.text;

import net.sf.anathema.library.event.ObjectChangedListener;

public interface ITextView {

  void addTextChangedListener(ObjectChangedListener<String> listener);

  void setEnabled(boolean enabled);

  void setText(String text);

  void clear();

  @SuppressWarnings("UnusedDeclaration")
  void removeAllListeners();
}