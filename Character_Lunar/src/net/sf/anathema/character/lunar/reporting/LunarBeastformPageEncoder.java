package net.sf.anathema.character.lunar.reporting;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAbilitiesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAttributesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionPersonalInfoEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class LunarBeastformPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  private static final int ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final PdfEncodingRegistry registry;
  private final IPdfPartEncoder partEncoder;

  public LunarBeastformPageEncoder(
      IPdfPartEncoder partEncoder,
      PdfEncodingRegistry registry,
      IResources resources,
      int essenceMax,
      PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(
      Document document,
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description) throws DocumentException {
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(directContent, character, description, distanceFromTop, firstRowHeight);
    encodeEssence(directContent, character, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    encodeFirstColumn(directContent, character, distanceFromTop);
    encodeAnima(directContent, character, distanceFromTop, ANIMA_HEIGHT);
    float virtueHeight = 72;
    float freeSpace = ANIMA_HEIGHT - virtueHeight;
    distanceFromTop += freeSpace;
    encodeVirtues(directContent, distanceFromTop, virtueHeight, character.getTraitCollection());
    distanceFromTop += calculateBoxIncrement(virtueHeight);

    float animalFormHeight = encodeAnimalForms(directContent, character, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, character, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float faceHeight = encodeFace(directContent, character, distanceFromTop, animalFormHeight - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(faceHeight);
    float weaponryHeight = encodeWeaponry(directContent, character, distanceFromTop, partEncoder.getWeaponryHeight());
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, character, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = LunarBeastformPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, character, distanceFromTop, remainingHeight);
  }

  private float encodeEssence(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = partEncoder.getEssenceEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, essenceBounds);
    return height;
  }

  private String getHeaderLabel(String key) {
    return resources.getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      int distanceFromTop,
      final int firstRowHeight) {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    String name = description.getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(directContent, infoBounds, title);
    encodePersonalInfos(directContent, character, description, infoContentBounds);
  }

  private void encodeFirstColumn(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int attributeHeight = encodeAttributes(directContent, character, distanceFromTop);
    encodeAbilities(directContent, character, distanceFromTop + attributeHeight + PADDING);
  }

  private void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int abilitiesHeight = CONTENT_HEIGHT - distanceFromTop;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IPdfContentBoxEncoder encoder = new PdfAbilitiesEncoder(baseFont, resources, essenceMax);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
  }

  private int encodeAttributes(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    IPdfContentBoxEncoder encoder = new PdfAttributesEncoder(baseFont, resources, essenceMax, true);
    boxEncoder.encodeBox(directContent, encoder, character, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeAnima(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = partEncoder.getAnimaEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, animaBounds);
  }

  private float encodeArmourAndSoak(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder contentEncoder = registry.getArmourContentEncoder();
    boxEncoder.encodeBox(directContent, contentEncoder, character, bounds);
    return height;
  }

  private float encodeAnimalForms(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(1, "AnimalForms"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeCombatStats(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = partEncoder.getCombatStatsEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeMovementAndHealth(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = partEncoder.getHealthAndMovementEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private void encodePersonalInfos(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      Bounds infoBounds) {
    SecondEditionPersonalInfoEncoder encoder = new SecondEditionPersonalInfoEncoder(baseFont, resources);
    encoder.encodePersonalInfos(directContent, character, description, infoBounds);
  }

  private float encodeVirtues(
      PdfContentByte directContent,
      float distanceFromTop,
      float height,
      IGenericTraitCollection collection) {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    String header = resources.getString("Sheet.Header.Virtues"); //$NON-NLS-1$
    Bounds contentBounds = boxEncoder.encodeBox(directContent, bounds, header);
    new PdfVirtueEncoder(resources, baseFont).encodeVirtues(directContent, contentBounds, collection);
    return height;
  }

  private float encodeWeaponry(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder weaponryEncoder = registry.getWeaponContentEncoder();
    boxEncoder.encodeBox(directContent, weaponryEncoder, character, bounds);
    return height;
  }

  private float encodeWillpower(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(directContent, new PdfWillpowerEncoder(baseFont), character, willpowerBounds);
    return height;
  }

  private float encodeFace(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(1, "Face"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }
}