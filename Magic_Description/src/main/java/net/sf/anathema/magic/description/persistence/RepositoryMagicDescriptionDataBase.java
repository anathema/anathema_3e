package net.sf.anathema.magic.description.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.magic.description.module.MagicDescriptionItemType;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.Repository;
import net.sf.anathema.platform.repository.RepositoryStringAccess;

public class RepositoryMagicDescriptionDataBase implements MagicDescriptionDataBase {

  public static RepositoryMagicDescriptionDataBase CreateFrom(ApplicationModel anathemaModel) {
    Repository repository = anathemaModel.getRepository();
    IItemType itemType = new MagicDescriptionItemType().getItemType();
    return new RepositoryMagicDescriptionDataBase(repository, itemType);
  }

  private Repository repository;
  private IItemType itemType;
  private final Gson gson;

  public RepositoryMagicDescriptionDataBase(Repository repository, IItemType itemType) {
    this.repository = repository;
    this.itemType = itemType;
    this.gson = new GsonBuilder().setPrettyPrinting().create();
  }

  @Override
  public void saveDescription(String charmId, String description) {
    String jsonRepresentation = createJSonRepresentation(charmId, description);
    new RepositoryStringAccess(repository, itemType).write(charmId, jsonRepresentation);
  }

  private String createJSonRepresentation(String charmId, String description) {
    MagicDescriptionPO persistenceObject = MagicDescriptionPO.ForIdAndDescription(charmId, description);
    return gson.toJson(persistenceObject);
  }

  @Override
  public String loadDescription(String charmId) {
    if (!repository.knowsItem(itemType, charmId)) {
      return null;
    }
    String jsonRepresentation = new RepositoryStringAccess(repository, itemType).read(charmId);
    MagicDescriptionPO persistenceObject = gson.fromJson(jsonRepresentation, MagicDescriptionPO.class);
    return persistenceObject.description;
  }
}