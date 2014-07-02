package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import org.dom4j.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class CharmDocuments {

  private final Map<Document, CategoryReference> categoriesForDocuments = new HashMap<>();

  public void addDocument(CategoryReference category, Document document) {
    categoriesForDocuments.put(document, category);
  }

  public void forEach(BiConsumer<Document, CategoryReference> action) {
    categoriesForDocuments.forEach(action);
  }
 }