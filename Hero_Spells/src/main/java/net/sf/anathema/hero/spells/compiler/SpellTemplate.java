package net.sf.anathema.hero.spells.compiler;

import net.sf.anathema.hero.spells.data.CircleType;

import java.util.ArrayList;
import java.util.List;

public class SpellTemplate {
  CircleType circle;
  String cost;
  String duration;
  List<String> keywords = new ArrayList<>();
  List<String> source = new ArrayList<>();
}
