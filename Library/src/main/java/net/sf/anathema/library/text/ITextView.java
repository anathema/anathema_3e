package net.sf.anathema.library.text;

import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.view.Clearable;

public interface ITextView extends Clearable{

  void addTextChangedListener(ObjectChangedListener<String> listener);

  void setEnabled(boolean enabled);

  void setText(String text);

  @SuppressWarnings("UnusedDeclaration")
  void removeAllListeners();
}