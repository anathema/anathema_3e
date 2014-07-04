package net.sf.anathema.platform.updatecheck;

import net.sf.anathema.platform.Version;

import java.util.List;

public class TagVersionFinder {

  public static final Version Unidentified_Version = new Version(0, 0, 0);

  public Version findLatestTaggedVersion(List<Tag> tags) {
    Version latestVersion = Unidentified_Version;
    for (Tag tag : tags) {
      String versionString = tag.name.replace("v", "");
      if (Version.isParseable(versionString)) {
        Version version = new Version(versionString);
        if (version.isLargerThan(latestVersion)) {
          latestVersion = version;
        }
      }
    }
    return latestVersion;
  }
}