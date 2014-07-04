package net.sf.anathema.platform.repository.access;

import java.io.File;

public interface RepositoryFileProvider {

  File[] getFiles();
}