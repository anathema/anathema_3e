package net.sf.anathema.library.text;

import net.sf.anathema.library.event.ObjectValueListener;

public interface ITextView {

  void addTextChangedListener(ObjectValueListener<String> listener);

  void setEnabled(boolean enabled);

  void setText(String text);

  void removeAllListeners();
}