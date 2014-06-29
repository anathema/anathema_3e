package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface DescriptiveLimitBreak extends LimitBreak {

  ITextualDescription getDescription();

  ITextualDescription getLimitBreak();
}