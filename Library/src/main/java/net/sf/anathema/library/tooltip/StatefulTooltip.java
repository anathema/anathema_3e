package net.sf.anathema.library.tooltip;

public interface StatefulTooltip extends ConfigurableTooltip {

  void reset();

  void apply();
}