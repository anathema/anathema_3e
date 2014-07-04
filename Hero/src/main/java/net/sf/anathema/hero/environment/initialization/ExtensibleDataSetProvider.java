package net.sf.anathema.hero.environment.initialization;

public interface ExtensibleDataSetProvider {

	<T extends ExtensibleDataSet> T getDataSet(Class<T> set);
}