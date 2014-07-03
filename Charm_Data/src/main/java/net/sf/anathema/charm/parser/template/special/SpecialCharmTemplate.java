package net.sf.anathema.charm.parser.template.special;

public class SpecialCharmTemplate {

  public String charmId;
  public Repurchase repurchase;
  public OxBodyTechnique oxBodyTechnique;
  public PainTolerance painTolerance;
  public TraitCapModifier traitCapModifier;
  public MultiEffect multiEffect;
  public Transcendence transcendence;
  public Upgradable upgradable;
  public SubEffect subEffect;

  public boolean isSpecial() {
    return repurchase != null || oxBodyTechnique != null || painTolerance != null || traitCapModifier != null || multiEffect != null ||
           transcendence != null || upgradable != null || subEffect != null;
  }
}
