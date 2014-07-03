package net.sf.anathema.framework.repository.access;

import java.io.InputStream;

public interface RepositoryReadAccess extends RepositoryFileProvider {

  InputStream openMainInputStream();

  InputStream openSubInputStream(String streamID);
}