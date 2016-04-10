package net.sf.anathema.points.display.experience;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;
import net.sf.anathema.points.model.xp.ExperiencePointType;
import net.sf.anathema.points.model.xp.ExperiencePoints;
import net.sf.anathema.points.model.xp.ExperienceSelectionListener;

import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FxExperienceView implements ExperienceView, NodeHolder {
  private final MigPane content = new MigPane(new LC().wrapAfter(1).fill());
  private final TableView<ExperiencePointEntry> table = new TableView<>();
  private final Announcer<ExperienceUpdateListener> updateAnnouncer = new Announcer<>(ExperienceUpdateListener.class);
  private final Announcer<ExperienceSelectionListener> entryAnnouncer = new Announcer<>(ExperienceSelectionListener.class);
  private final MigPane buttonPanel = new MigPane();
  private final Label totalLabel = new Label();
  private Resources resources;

  @Override
  public void initGui(ExperienceViewProperties properties, Resources resources, ExperiencePoints points) {
  	TableColumn<ExperiencePointEntry, String> descriptionColumn = createDescriptionColumn(properties);
    Collection<TableColumn<ExperiencePointEntry, String>> pointsColumns = createPointsColumns(properties, points);
    
    table.setEditable(true);
    table.getColumns().add(descriptionColumn);
    table.getColumns().addAll(pointsColumns);
    table.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> entryAnnouncer.announce().selectionChanged(newValue));
    MigPane totalPanel = createTotalPanel(properties);
    content.add(buttonPanel);
    content.add(table, new CC().push().grow().span());
    content.add(totalPanel, new CC().pushX().growX().spanX());
    this.resources = resources;
  }

  @Override
  public Tool addTool() {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    buttonPanel.add(tool.getNode());
    return tool;
  }

  private MigPane createTotalPanel(ExperienceViewProperties properties) {
    MigPane migPane = new MigPane(LayoutUtils.withoutInsets().fill());
    migPane.add(new Label(properties.getTotalString()));
    migPane.add(totalLabel, new CC().alignX("right"));
    return migPane;
  }

  @Override
  public void addSelectionListener(ExperienceSelectionListener listener) {
    entryAnnouncer.addListener(listener);
  }

  @Override
  public void setTotalValueLabel(int overallExperiencePoints) {
    totalLabel.setText(String.valueOf(overallExperiencePoints));
  }

  @Override
  public void addUpdateListener(ExperienceUpdateListener experienceUpdateListener) {
    updateAnnouncer.addListener(experienceUpdateListener);
  }

  @Override
  public void setSelection(ExperiencePointEntry entry) {
    table.getSelectionModel().select(entry);
  }

  @Override
  public void setEntries(Collection<ExperiencePointEntry> allEntries) {
    clearEntries();
    addEntries(allEntries);
    forceTableRefresh();
  }

  @Override
  public Node getNode() {
    return content;
  }
  
  private Collection<TableColumn<ExperiencePointEntry, String>> createPointsColumns(ExperienceViewProperties properties, ExperiencePoints points) {
  	List<TableColumn<ExperiencePointEntry, String>> columns = new ArrayList<>();
  	for (ExperiencePointType type : points.getSupportedExperiencePointTypes()) {
  		columns.add(createPointsColumn(properties, type));
  	}
  	return columns;
  }

  private TableColumn<ExperiencePointEntry, String> createPointsColumn(ExperienceViewProperties properties, ExperiencePointType type) {
    TableColumn<ExperiencePointEntry, String> pointColumn = new TableColumn<>(properties.getPointHeader());
    pointColumn.prefWidthProperty().bind(table.widthProperty().divide(4));
    Callback<TableColumn<ExperiencePointEntry, String>, TableCell<ExperiencePointEntry, String>> editableCell = TextFieldTableCell.forTableColumn();
    Callback<TableColumn<ExperiencePointEntry, String>, TableCell<ExperiencePointEntry, String>> styledEditableCell = new Callback<TableColumn<ExperiencePointEntry, String>, TableCell<ExperiencePointEntry, String>>() {
      @Override
      public TableCell<ExperiencePointEntry, String> call(TableColumn<ExperiencePointEntry, String> experiencePointEntryStringTableColumn) {
        TableCell<ExperiencePointEntry, String> cell = editableCell.call(experiencePointEntryStringTableColumn);
        cell.getStyleClass().add("right-aligned");
        return cell;
      }
    };
    pointColumn.setCellValueFactory(features -> new SimpleStringProperty(String.valueOf(features.getValue().getExperiencePointsCosted().get(type))));
    pointColumn.setCellFactory(styledEditableCell);
    pointColumn.setOnEditCommit(
            event -> {
              ExperiencePointEntry experienceEntry = event.getRowValue();
              Integer newPointValue = getChangedPointValue(event, experienceEntry, type);
              experienceEntry.getExperiencePointsCosted().put(type, newPointValue);
              String description = experienceEntry.getTextualDescription().getText();
              updateAnnouncer.announce().update(experienceEntry.getExperiencePointsCosted(), description);
            }
    );
    return pointColumn;
  }

  private Integer getChangedPointValue(TableColumn.CellEditEvent<ExperiencePointEntry, String> event, ExperiencePointEntry experienceEntry, ExperiencePointType type) {
    try {
      return Integer.valueOf(event.getNewValue());
    } catch (NumberFormatException e) {
      return experienceEntry.getExperiencePointsCosted().get(type);
    }
  }

  private TableColumn<ExperiencePointEntry, String> createDescriptionColumn(ExperienceViewProperties properties) {
    TableColumn<ExperiencePointEntry, String> descriptionColumn = new TableColumn<>(properties.getDescriptionHeader());
    descriptionColumn.prefWidthProperty().bind(table.widthProperty().multiply(3).divide(4).subtract(5));
    Callback<TableColumn<ExperiencePointEntry, String>, TableCell<ExperiencePointEntry, String>> cellCallback = TextFieldTableCell.forTableColumn();
    descriptionColumn.setCellValueFactory(features -> 
    {
    	features.getValue().initializePresentation(resources);
    	features.getTableView().setEditable(features.getValue().allowModification());
    	return new SimpleStringProperty(features.getValue().getTextualDescription().getText());
    });
    descriptionColumn.setCellFactory(cellCallback);
    descriptionColumn.setOnEditCommit(
            event -> {
              ExperiencePointEntry experienceEntry = event.getRowValue();
              Map<ExperiencePointType, Integer> experiencePoints = experienceEntry.getExperiencePointsCosted();
              String description = event.getNewValue();
              updateAnnouncer.announce().update(experiencePoints, description);
            }
    );
    return descriptionColumn;
  }

  private void clearEntries() {
    table.getItems().removeAll(table.getItems());
  }

  private void addEntries(Iterable<ExperiencePointEntry> allEntries) {
    for (ExperiencePointEntry entry : allEntries) {
      table.getItems().add(entry);
    }
  }

  private void forceTableRefresh() {
    table.getColumns().get(0).setVisible(false);
    table.getColumns().get(0).setVisible(true);
  }
}