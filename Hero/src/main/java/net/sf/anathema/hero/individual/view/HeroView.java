package net.sf.anathema.hero.individual.view;

import net.sf.anathema.library.interaction.view.InteractionView;

public interface HeroView {

  SectionView addSection(String title);
  
  InteractionView getInteractionView();
}