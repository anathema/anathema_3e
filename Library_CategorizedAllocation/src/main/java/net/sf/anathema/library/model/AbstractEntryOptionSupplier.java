package net.sf.anathema.library.model;


public abstract class AbstractEntryOptionSupplier<O extends OptionalEntryOption> implements OptionalEntryOptionSupplier<O> {

  @Override
  public O getOptionByReference(OptionalEntryReference reference) {
    return getAllOptions().stream().filter(option -> option.toString().equals(reference.name)).findFirst().get();
  }

}
