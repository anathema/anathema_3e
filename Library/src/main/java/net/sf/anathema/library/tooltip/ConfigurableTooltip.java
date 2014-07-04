package net.sf.anathema.library.tooltip;

public interface ConfigurableTooltip {

  String CommaSpace = ", ";
  String Space = " ";

  void showNoTooltip();

  void appendLine(String text);

  void appendTitleLine(String title);

  void appendLine(String label, String value);

  void appendDescriptiveLine(String description);
}