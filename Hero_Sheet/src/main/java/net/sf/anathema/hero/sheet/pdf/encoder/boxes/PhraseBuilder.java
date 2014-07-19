package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;

public class PhraseBuilder {

  private final Phrase phrase = new Phrase();
  private Font font;
  private boolean breakAfterItem;

  public Phrase getPhrase() {
    return phrase;
  }

  public void addText(String text) {
    if (breakAfterItem) {
      text = text += "\n";
      addTextToPhrase(text);
    }
    else {
      if (!phrase.isEmpty()) {
        text = ", " + text;
      }
      addTextToPhrase(text);
    }
  }

  private void addTextToPhrase(String text) {
    phrase.add(new Chunk(text, font));
  }

  public void switchToFont(Font font) {
    this.font = font;
  }

  public void breakAfterEveryItem() {
    this.breakAfterItem = true;
  }
}