package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PhraseBuilderTest {

  private final PhraseBuilder phraseBuilder = new PhraseBuilder();

  @Test
  public void startsWithEmptyPhrase() throws Exception {
    Phrase phrase = phraseBuilder.getPhrase();
    assertThat(phrase.getChunks(), hasSize(0));
  }

  @Test
  public void addsChunksToPhrase() throws Exception {
    phraseBuilder.addText("TEXT");
    Phrase phrase = phraseBuilder.getPhrase();
    assertThat(phrase.getChunks(), hasSize(1));
  }

  @Test
  public void chunksHaveTextContent() throws Exception {
    phraseBuilder.addText("TEXT");
    Phrase phrase = phraseBuilder.getPhrase();
    assertThat(phrase.getContent(), is("TEXT"));
  }

  @Test
  public void chunksUseFont() throws Exception {
    Font font = mock(Font.class);
    phraseBuilder.switchToFont(font);
    phraseBuilder.addText("TEXT");
    Phrase phrase = phraseBuilder.getPhrase();
    assertThat(phrase.getChunks().get(0).getFont(), is(font));
  }

  @Test
  public void separatesEntriesWithComma() throws Exception {
    phraseBuilder.addText("TEXT");
    phraseBuilder.addText("NEXT");
    Phrase phrase = phraseBuilder.getPhrase();
    assertThat(phrase.getContent(), is("TEXT, NEXT"));
  }

  @Test
  public void breaksLineAfterItemsIfInstructed() throws Exception {
    phraseBuilder.breakAfterEveryItem();
    phraseBuilder.addText("TEXT");
    phraseBuilder.addText("NEXT");
    Phrase phrase = phraseBuilder.getPhrase();
    assertThat(phrase.getContent(), is("TEXT\nNEXT\n"));
  }
}