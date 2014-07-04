package net.sf.anathema.hero.environment.herotype;

import net.sf.anathema.library.resources.RelativePath;

public interface PresentationProperties {

  String getNewActionResource();

  RelativePath getSmallCasteIconResource(String casteId);

  RelativePath getLargeCasteIconResource(String casteId);

  String getCasteLabelResource();
}