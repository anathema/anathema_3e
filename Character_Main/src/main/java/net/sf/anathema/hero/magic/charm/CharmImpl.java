package net.sf.anathema.hero.magic.charm;

import com.google.common.base.Preconditions;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.charm.old.cost.CostList;
import net.sf.anathema.magic.source.SourceBook;
import net.sf.anathema.magic.AbstractMagic;
import net.sf.anathema.hero.magic.charm.duration.Duration;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.magic.parser.charms.CharmPrerequisiteList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CharmImpl extends AbstractMagic implements Charm, CharmParent {

  private final Duration duration;
  private final SourceBook[] sources;
  private final CostList temporaryCost;
  private final List<CharmImpl> children = new ArrayList<>();
  private final CharmType charmType;
  private TreeReference treeReference;
  private CharmName name;
  private PrerequisiteList prerequisiteList;

  public CharmImpl(TreeReference treeReference, CharmName name, CharmPrerequisiteList prerequisiteList,
                   CostList temporaryCost, Duration duration, CharmType charmType,
                   SourceBook[] sources) {
    Preconditions.checkNotNull(prerequisiteList);
    Preconditions.checkNotNull(treeReference);
    Preconditions.checkNotNull(name);
    Preconditions.checkNotNull(temporaryCost);
    Preconditions.checkNotNull(duration);
    Preconditions.checkNotNull(charmType);
    Preconditions.checkNotNull(sources);
    this.name = name;
    this.treeReference = treeReference;
    this.temporaryCost = temporaryCost;
    this.duration = duration;
    this.charmType = charmType;
    this.sources = sources;
    this.prerequisiteList = new PrerequisiteListImpl(prerequisiteList);
  }

  @Override
  public CharmName getName() {
    return name;
  }

  @Override
  public CharmType getCharmType() {
    return charmType;
  }

  @Override
  public TreeReference getTreeReference() {
    return treeReference;
  }

  @Override
  public Duration getDuration() {
    return duration;
  }

  @Override
  public SourceBook[] getSources() {
    return sources.length > 0 ? sources : null;
  }

  @Override
  public CostList getTemporaryCost() {
    return temporaryCost;
  }

  @Override
  public void addChild(CharmImpl child) {
    children.add(child);
  }

  @Override
  public void forEachChild(Consumer<Charm> consumer) {
    children.forEach(consumer);
  }

  @Override
  public PrerequisiteList getPrerequisites() {
    return prerequisiteList;
  }
}