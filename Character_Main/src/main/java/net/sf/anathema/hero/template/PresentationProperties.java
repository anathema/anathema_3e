package net.sf.anathema.hero.template;

import net.sf.anathema.lib.file.RelativePath;

public interface PresentationProperties {

  String getNewActionResource();

  RelativePath getSmallCasteIconResource(String casteId);

  RelativePath getLargeCasteIconResource(String casteId);

  String getCasteLabelResource();
}