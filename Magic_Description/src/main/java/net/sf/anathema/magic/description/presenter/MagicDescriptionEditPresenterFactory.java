package net.sf.anathema.magic.description.presenter;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.description.display.MagicDetailPresenter;
import net.sf.anathema.magic.description.display.MagicDetailPresenterFactory;
import net.sf.anathema.magic.description.display.RegisteredMagicDetailPresenterFactory;
import net.sf.anathema.magic.description.model.AutoSaveMagicDescriptionEditModel;
import net.sf.anathema.magic.description.model.MagicDescriptionEditModel;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;
import net.sf.anathema.magic.description.persistence.RepositoryMagicDescriptionDataBase;
import net.sf.anathema.magic.description.view.MagicDescriptionEditView;
import net.sf.anathema.platform.frame.ApplicationModel;

@RegisteredMagicDetailPresenterFactory
public class MagicDescriptionEditPresenterFactory implements MagicDetailPresenterFactory {

  @Override
  public MagicDetailPresenter create(ApplicationModel anathemaModel, Resources resources) {
    MagicDescriptionEditView view = new MagicDescriptionEditView();
    MagicDescriptionDataBase dataBase = RepositoryMagicDescriptionDataBase.CreateFrom(anathemaModel);
    MagicDescriptionEditModel model = new AutoSaveMagicDescriptionEditModel(dataBase);
    return new MagicDescriptionEditPresenter(view, model, resources);
  }
}
