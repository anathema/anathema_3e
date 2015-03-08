package net.sf.anathema.magic.template.evocations;

import java.util.ArrayList;
import java.util.List;


public class EvocationArtifactTemplate {
	public enum EvocationTier {  };
	
	public String category;
	public String tree;
	public int emeraldRequiredForSapphire = 0;
  public int sapphireRequiredForAdamant = 0;
  
  public List<String> innateOnSapphire = new ArrayList<>();
  public List<String> innateOnAdamant = new ArrayList<>();
  
  @Override
  public String toString() {
  	return category + "." + tree;
  }
}
