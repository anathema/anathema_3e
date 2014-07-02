package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import org.dom4j.Document;

public interface CharmDocumentWorker {

  void work(CategoryReference reference, Document document);
}
