package net.sf.anathema.platform.repository.printname;

import java.util.Collection;

public interface ReferenceAccess<R> {

  Collection<R> collectAllItemReferences();
}
