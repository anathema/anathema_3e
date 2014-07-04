package net.sf.anathema.hero.spells.parser;

import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.template.GenericTemplateLoader;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ExtensibleDataSetCompiler
public class SpellCompiler implements IExtensibleDataSetCompiler {
  private static final String Spell_File_Recognition_Pattern = ".+?\\.spells";
  private final ListSpellCache cache = new ListSpellCache();
  private final TemplateLoader<SpellListTemplate> loader = new GenericTemplateLoader<>(SpellListTemplate.class);
  private final SpellBuilder spellBuilder = new SpellBuilder();

  @SuppressWarnings("UnusedParameters")
  public SpellCompiler(ObjectFactory objectFactory) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "Spells";
  }

  @Override
  public String getRecognitionPattern() {
    return Spell_File_Recognition_Pattern;
  }

  @Override
  public ExtensibleDataSet build() {
    return cache;
  }

  @Override
  public void registerFile(ResourceFile resource) {
    SpellListTemplate template = loadTemplate(resource);
    List<Spell> spells = spellBuilder.buildSpells(template);
    spells.forEach(cache::addSpell);
  }

  private SpellListTemplate loadTemplate(ResourceFile resource) {
    try (InputStream inputStream = resource.getURL().openStream()) {
      return loader.load(inputStream);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }
}