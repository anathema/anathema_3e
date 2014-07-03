package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.magic.attribute.MagicAttribute;
import net.sf.anathema.charm.old.cost.CostList;
import net.sf.anathema.magic.source.SourceBook;
import net.sf.anathema.charm.parser.cost.CostListBuilder;
import net.sf.anathema.charm.parser.cost.ICostListBuilder;
import net.sf.anathema.charm.parser.source.SourceBuilder;
import net.sf.anathema.charm.parser.util.ElementUtilities;
import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.charm.duration.Duration;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.magic.parser.charms.prerequisite.IAttributePrerequisiteBuilder;
import net.sf.anathema.hero.magic.parser.charms.prerequisite.ICharmPrerequisiteBuilder;
import net.sf.anathema.hero.magic.parser.charms.prerequisite.ITraitPrerequisitesBuilder;
import net.sf.anathema.hero.magic.parser.charms.prerequisite.PrerequisiteListBuilder;
import net.sf.anathema.hero.magic.parser.charms.special.ReflectionSpecialCharmParser;
import net.sf.anathema.hero.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import java.util.Arrays;
import java.util.List;

import static net.sf.anathema.charm.parser.ICharmXMLConstants.*;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmParserImpl implements CharmParser {

  private final CharmTypeParser charmTypeParser = new CharmTypeParser();
  private final ICostListBuilder costListBuilder = new CostListBuilder();
  private final DurationParser durationParser = new DurationParser();
  private final GroupStringParser groupBuilder = new GroupStringParser();
  private final SourceBuilder sourceBuilder = new SourceBuilder();
  private final CharmAttributeParser attributeBuilder = new CharmAttributeParser();
  private final ReflectionSpecialCharmParser specialCharmParser;
  private final IdStringParser idBuilder;
  private final ITraitPrerequisitesBuilder traitsBuilder;
  private final IAttributePrerequisiteBuilder attributeRequirementsBuilder;
  private final ICharmPrerequisiteBuilder charmPrerequisiteBuilder;

  public CharmParserImpl(IdStringParser idBuilder, ITraitPrerequisitesBuilder traitsBuilder,
                         IAttributePrerequisiteBuilder attributeRequirementsBuilder,
                         ICharmPrerequisiteBuilder charmPrerequisiteBuilder,
                         ReflectionSpecialCharmParser specialCharmParser) {
    this.idBuilder = idBuilder;
    this.traitsBuilder = traitsBuilder;
    this.attributeRequirementsBuilder = attributeRequirementsBuilder;
    this.charmPrerequisiteBuilder = charmPrerequisiteBuilder;
    this.specialCharmParser = specialCharmParser;
  }

  @Override
  public CharmImpl buildCharm(Element charmElement, List<SpecialCharmDto> specialCharms) throws PersistenceException {
    String id = idBuilder.build(charmElement);
    try {
      String characterType = getCharacterType(charmElement);
      CostList temporaryCost;
      try {
        temporaryCost = costListBuilder.buildCostList(charmElement.element(TAG_COST));
      } catch (PersistenceException e) {
        throw new CharmException("Error building costlist for charm " + id, e);
      }
      Duration duration = buildDuration(charmElement);
      CharmType charmType = charmTypeParser.build(charmElement);
      SourceBook[] sources = sourceBuilder.buildSourceList(charmElement);
      CharmPrerequisiteList prerequisiteList = getPrerequisites(charmElement);
      ValuedTraitType[] prerequisites = prerequisiteList.getTraitPrerequisites();
      ValuedTraitType primaryPrerequisite = prerequisites.length != 0 ? prerequisites[0] : null;
      String group = groupBuilder.build(charmElement, primaryPrerequisite);
      MagicAttribute[] magicAttributes = attributeBuilder.buildCharmAttributes(charmElement, primaryPrerequisite);
      TreeReference treeReference = createTreeReference(magicAttributes, characterType, group);
      CharmImpl charm = new CharmImpl(treeReference, new CharmName(id), prerequisiteList, temporaryCost, duration, charmType, sources);
      for (MagicAttribute attribute : magicAttributes) {
        charm.addMagicAttribute(attribute);
      }

      SpecialCharmDto dto = specialCharmParser.readCharmDto(charmElement, id);
      if (dto.isSpecial()) {
        specialCharms.add(dto);
      }
      return charm;
    } catch (PersistenceException e) {
      throw new PersistenceException("Parsing error for Charm " + id, e);
    }
  }

  private TreeReference createTreeReference(MagicAttribute[] magicAttribute, String characterType, String group) {
    boolean isMartialArts = Arrays.asList(magicAttribute).contains(MARTIAL_ARTS);
    String categoryText = isMartialArts ? MARTIAL_ARTS.getId() : characterType;
    return new TreeReference(new CategoryReference(categoryText), new TreeName(group));
  }

  private Duration buildDuration(Element charmElement) throws CharmException {
    Duration duration;
    try {
      duration = durationParser.buildDuration(charmElement.element(TAG_DURATION));
    } catch (PersistenceException e) {
      throw new CharmException("Error in Charm duration.", e);
    }
    return duration;
  }

  private CharmPrerequisiteList getPrerequisites(Element charmElement) throws CharmException {
    try {
      Element prerequisiteListElement = ElementUtilities.getRequiredElement(charmElement, TAG_PREREQUISITE_LIST);
      return new PrerequisiteListBuilder(traitsBuilder, attributeRequirementsBuilder, charmPrerequisiteBuilder)
              .buildPrerequisiteList(prerequisiteListElement);
    } catch (PersistenceException e) {
      throw new CharmException("Error in Charm prerequisites.", e);
    }
  }

  private String getCharacterType(Element charmElement) throws CharmException {
    return charmElement.attributeValue(ATTRIB_EXALT);
  }
}