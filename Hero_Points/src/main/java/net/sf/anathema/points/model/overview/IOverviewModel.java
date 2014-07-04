package net.sf.anathema.points.model.overview;

import net.sf.anathema.library.identifier.Identifier;

public interface IOverviewModel extends Identifier {

  void accept(IOverviewModelVisitor visitor);

  String getCategoryId();
}
