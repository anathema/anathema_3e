package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.library.event.ObjectChangedListener;

public interface ITextualDescription {

  boolean isDirty();

  boolean isEmpty();

  void setText(String text);

  void addTextChangedListener(ObjectChangedListener<String> listener);

  @SuppressWarnings("UnusedDeclaration")
  void removeTextChangeListener(ObjectChangedListener<String> listener);

  String getText();

  void clear();
}