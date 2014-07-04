package net.sf.anathema.library.text;

import net.sf.anathema.library.event.ObjectValueListener;

public class TextualPresentation {

  public void initView(final ITextView textView, final ITextualDescription textualDescription) {
    textView.addTextChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        textualDescription.setText(newValue);
      }
    });
    textView.setText(textualDescription.getText());
    textualDescription.addTextChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        textView.setText(newValue);
      }
    });
  }
}