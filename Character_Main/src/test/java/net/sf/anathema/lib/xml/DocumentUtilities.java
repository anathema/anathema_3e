package net.sf.anathema.lib.xml;

import net.sf.anathema.lib.exception.AnathemaException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.StringReader;

public class DocumentUtilities {

  public static Document read(String source) throws AnathemaException {
    try {
      SAXReader saxReader = new SAXReader();
      return saxReader.read(new StringReader(source));
    } catch (DocumentException exception) {
      throw new AnathemaException(exception);
    }
  }
}