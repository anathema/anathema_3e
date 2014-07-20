package net.sf.anathema.hero.traits.sheet.content;

import java.util.List;

public class BulletList {

  public final String header;
  public final List<String> items;

  public BulletList(String header, List<String> items) {
    this.header = header;
    this.items = items;
  }
}