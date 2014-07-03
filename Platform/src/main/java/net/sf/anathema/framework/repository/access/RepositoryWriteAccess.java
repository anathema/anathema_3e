package net.sf.anathema.framework.repository.access;

import java.io.OutputStream;

public interface RepositoryWriteAccess {

  OutputStream createMainOutputStream();

  OutputStream createSubOutputStream(String streamID);
}
