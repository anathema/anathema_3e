package net.sf.anathema.library.text;

public class TextualPresentation {

  public void initView(final ITextView textView, final ITextualDescription textualDescription) {
    textView.addTextChangedListener(textualDescription::setText);
    textView.setText(textualDescription.getText());
    textualDescription.addTextChangedListener(textView::setText);
  }
}