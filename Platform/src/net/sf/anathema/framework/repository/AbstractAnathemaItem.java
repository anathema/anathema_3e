package net.sf.anathema.framework.repository;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractAnathemaItem implements IItem {

  private String printName;
  private final IItemType itemType;
  private final RepositoryLocation repositoryLocation;
  private final IIdentificate identificate;
  private final GenericControl<IItemListener> repositoryItemListeners = new GenericControl<IItemListener>();

  public AbstractAnathemaItem(IItemType type) {
    Ensure.ensureArgumentTrue("Use second constructor for non-persisted items.", type.supportsRepository()); //$NON-NLS-1$
    this.itemType = type;
    this.repositoryLocation = new RepositoryLocation(this);
    this.identificate = new IIdentificate() {
      @Override
      public String getId() {
        return repositoryLocation.getId();
      }
    };
  }

  public AbstractAnathemaItem(IItemType type, IIdentificate identificate) {
    this.itemType = type;
    this.repositoryLocation = null;
    this.identificate = identificate;
  }

  @Override
  public void addItemListener(IItemListener listener) {
    repositoryItemListeners.addListener(listener);
  }

  @Override
  public void removeItemListener(IItemListener listener) {
    repositoryItemListeners.removeListener(listener);
  }

  private void firePrintNameChanged(final String name) {
    repositoryItemListeners.forAllDo(new IClosure<IItemListener>() {
      @Override
      public void execute(IItemListener input) {
        input.printNameChanged(name);
      }
    });
  }

  @Override
  public final IItemType getItemType() {
    return itemType;
  }

  @Override
  public final synchronized String getId() {
    return identificate.getId();
  }

  @Override
  public String getDisplayName() {
    return printName == null ? DEFAULT_PRINT_NAME : printName;
  }

  @Override
  public void setPrintName(String printName) {
    if (StringUtilities.isNullOrEmpty(printName)) {
      printName = null;
    }
    if (ObjectUtilities.equals(this.printName, printName)) {
      return;
    }
    this.printName = printName;
    firePrintNameChanged(getDisplayName());
  }

  @Override
  public IItemRepositoryLocation getRepositoryLocation() {
    return repositoryLocation;
  }

  @Override
  public String toString() {
    return getItemType() + ": " + getDisplayName(); //$NON-NLS-1$
  }
}