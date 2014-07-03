package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.template.CharmTemplate;
import net.sf.anathema.hero.magic.charm.PrerequisiteList;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.traits.model.types.SimpleValuedTraitType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static net.sf.anathema.hero.traits.model.types.OtherTraitType.Essence;

public class DefaultPrerequisiteList implements PrerequisiteList {

  private final List<CharmPrerequisite> charmPrerequisites = new ArrayList<>();
  private final List<ValuedTraitType> traitPrerequisites = new ArrayList<>();

  public DefaultPrerequisiteList(CharmTemplate template) {
    template.minimums.forEach((type, value) -> {
      TraitType traitType = new TraitTypeUtils().getTraitTypeById(type);
      traitPrerequisites.add(new SimpleValuedTraitType(traitType, value));
    });
    if (!template.minimums.containsKey(Essence.getId())) {
      traitPrerequisites.add(new SimpleValuedTraitType(Essence, 1));
    }
  }

  @Override
  public TraitType getPrimaryTraitType() {
    return traitPrerequisites.get(0).getType();
  }

  @Override
  public List<ValuedTraitType> getTraitPrerequisites() {
    return new ArrayList<>(traitPrerequisites);
  }

  @Override
  public void forEachCharmPrerequisite(Consumer<CharmPrerequisite> consumer) {
    charmPrerequisites.forEach(consumer);
  }

  @Override
  public List<CharmPrerequisite> getCharmPrerequisites() {
    return new ArrayList<>(charmPrerequisites);
  }

  public void addCharmPrerequisite(CharmPrerequisite prerequisite) {
    charmPrerequisites.add(prerequisite);
  }
}
