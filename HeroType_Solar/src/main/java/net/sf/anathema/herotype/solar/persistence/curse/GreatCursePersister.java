package net.sf.anathema.herotype.solar.persistence.curse;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveLimitBreak;
import net.sf.anathema.herotype.solar.model.curse.LimitBreakModel;
import net.sf.anathema.lib.util.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class GreatCursePersister extends AbstractModelJsonPersister<VirtueFlawPto, LimitBreakModel> {

  public GreatCursePersister() {
    super("greatCurse", VirtueFlawPto.class);
  }

  @Override
  protected void loadModelFromPto(Hero hero, LimitBreakModel model, VirtueFlawPto pto) {
    Trait limitTrait = model.getLimitBreak().getLimitTrait();
    limitTrait.setUncheckedCreationValue(pto.limit.creationValue);
    if (pto.limit.experienceValue != null) {
      limitTrait.setUncheckedExperiencedValue(pto.limit.experienceValue);
    }
    if (model.getLimitBreak() instanceof DescriptiveLimitBreak) {
      DescriptiveLimitBreak virtueFlaw = (DescriptiveLimitBreak) model.getLimitBreak();
      virtueFlaw.getLimitBreak().setText(pto.limitBreak);
      virtueFlaw.getDescription().setText(pto.description);
    }
    model.getLimitBreak().getName().setText(pto.name);
  }

  @Override
  protected VirtueFlawPto saveModelToPto(LimitBreakModel heroModel) {
    VirtueFlawPto pto = new VirtueFlawPto();
    pto.name = heroModel.getLimitBreak().getName().getText();
    pto.limit.creationValue = heroModel.getLimitBreak().getLimitTrait().getCreationValue();
    int experienceValue = heroModel.getLimitBreak().getLimitTrait().getExperiencedValue();
    if (experienceValue >= 0) {
      pto.limit.experienceValue = experienceValue;
    }
    if (heroModel.getLimitBreak() instanceof DescriptiveLimitBreak) {
      DescriptiveLimitBreak virtueFlaw = (DescriptiveLimitBreak) heroModel.getLimitBreak();
      pto.limitBreak = virtueFlaw.getLimitBreak().getText();
      pto.description = virtueFlaw.getDescription().getText();
    }
    return pto;
  }

  @Override
  public Identifier getModelId() {
    return LimitBreakModel.ID;
  }
}