package net.sf.anathema.magic.description.module;

import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.description.model.MagicDescription;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;

public class RepositoryMagicDescriptionProvider implements MagicDescriptionProvider {

  private MagicDescriptionDataBase dataBase;

  public RepositoryMagicDescriptionProvider(MagicDescriptionDataBase dataBase) {
    this.dataBase = dataBase;
  }

  @Override
  public MagicDescription getCharmDescription(Magic magic) {
    String description = dataBase.loadDescription(magic.getName().text);
    return new DirectMagicDescription(description);
  }
}
