package net.sf.anathema.platform.tree.display.transform;

public interface TransformOperationVisitor {
  void visitPreConcatenate(PreConcatenate preConcatenate);

  void visitTranslation(Translation translation);

  void visitCenterOn(CenterOn centerOn);

  void visitScale(Scale scale);

  void visitRotation(Rotation rotation);
}