package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.library.dummy.ConfigurableDummyObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class EncoderFactoryMapTest {
  private static final String TEST_ID = "testId";
  private ReportSession session = mock(ReportSession.class);
  private ConfigurableDummyObjectFactory objectFactory = new ConfigurableDummyObjectFactory();

  @Before
  public void setUp() throws Exception {
    //when(session.createContent(any(SubContent.class))).thenReturn(new BasicContent(null));
  }

  @Test
  public void discoversFactoryForSessionContent() throws Exception {
    EncoderFactory expectation = new DummyEncoderFactory(TEST_ID);
    objectFactory.add(EncoderFactory.class, expectation);
    EncoderFactoryMap map = new EncoderFactoryMap(objectFactory);
    assertThat(map.findFactory(session, TEST_ID), is(expectation));
  }
}
