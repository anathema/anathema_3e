package net.sf.anathema.character.framework.xml.creation;

import net.sf.anathema.character.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.hero.template.points.AbilityCreationPoints;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class CreationPointTemplateParser extends AbstractXmlTemplateParser<GenericCreationPoints> {

  private static final String ATTRIB_COUNT = "count";
  private static final String ATTRIB_FAVORED = "favored";
  private static final String ATTRIB_GENERAL = "general";
  private static final String TAG_ABILITY_DOTS = "abilityDots";
  private static final String TAG_CHARM_PICKS = "charmPicks";
  private static final String TAG_BONUS_POINTS = "bonusPoints";

  public CreationPointTemplateParser(IXmlTemplateRegistry<GenericCreationPoints> templateRegistry) {
    super(templateRegistry);
  }

  @Override
  public GenericCreationPoints parseTemplate(Element element) throws PersistenceException {
    GenericCreationPoints creationPoints = new GenericCreationPoints();
    parseAbilityCreationPoints(element.element(TAG_ABILITY_DOTS), creationPoints);
    parseCharmCreationPoints(element.element(TAG_CHARM_PICKS), creationPoints);
    Element bonusPointsElement = element.element(TAG_BONUS_POINTS);
    if (bonusPointsElement != null) {
      creationPoints.setBonusPointCount(getCountAttribute(bonusPointsElement));
    }
    return creationPoints;
  }

  private void parseAbilityCreationPoints(Element element, GenericCreationPoints creationPoints) throws PersistenceException {
    if (element == null) {
      return;
    }
    int generalDots = ElementUtilities.getIntAttrib(element, ATTRIB_GENERAL, 0);
    int favoredDots = ElementUtilities.getIntAttrib(element, ATTRIB_FAVORED, 0);
    creationPoints.setAbilityCreationPoints(new AbilityCreationPoints(favoredDots, generalDots));
  }

  private int getCountAttribute(Element element) throws PersistenceException {
    return ElementUtilities.getIntAttrib(element, ATTRIB_COUNT, 0);
  }

  private void parseCharmCreationPoints(Element element, GenericCreationPoints creationPoints) throws PersistenceException {
    if (element == null) {
      return;
    }
    int generalPicks = ElementUtilities.getIntAttrib(element, ATTRIB_GENERAL, 0);
    int favoredPicks = ElementUtilities.getIntAttrib(element, ATTRIB_FAVORED, 0);
    creationPoints.setGeneralCreationCharmCount(generalPicks);
    creationPoints.setFavoredCreationCharmCount(favoredPicks);
  }

  @Override
  protected GenericCreationPoints createNewBasicTemplate() {
    return new GenericCreationPoints();
  }
}