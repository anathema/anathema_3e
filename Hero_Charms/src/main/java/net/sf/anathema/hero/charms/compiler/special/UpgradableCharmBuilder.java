package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.template.special.Upgradable;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.upgradable.UpgradableCharm;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("UnusedDeclaration")
public class UpgradableCharmBuilder implements SpecialCharmBuilder {

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(SpecialCharmTemplate overallDto) {
    Upgradable dto = overallDto.upgradable;
    return new UpgradableCharm(new CharmName(overallDto.charmId), createUpgrades(dto), dto.requiresBase, dto.bpCostsByName,
            dto.xpCostsByName, dto.essenceMinimumsByName, dto.traitMinimumsByName, createTraitsMap(dto));
  }

  private Map<String, TraitType> createTraitsMap(Upgradable dto) {
    Map<String, TraitType> traits = new HashMap<>();
    for (Map.Entry<String, String> entry : dto.traitsByName.entrySet()) {
      traits.put(entry.getKey(), traitTypeFinder.getTrait(entry.getValue()));
    }
    return traits;
  }

  private String[] createUpgrades(Upgradable dto) {
    return dto.upgrades.toArray(new String[dto.upgrades.size()]);
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.upgradable != null;
  }
}