package net.sf.anathema.library.fx.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.identifier.Identifier;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.LinkedHashMap;
import java.util.Optional;

public class FxStack {

  private final LinkedHashMap<Identifier, Node> namedNodes = new LinkedHashMap<>();
  private MigPane parent;

  public FxStack(MigPane parent) {
    this.parent = parent;
  }

  public void add(Identifier name, Node node) {
    namedNodes.put(name, node);
  }

  public void show(Identifier name) {
    parent.getChildren().clear();
    Node selectedNode = namedNodes.get(name);
    parent.add(selectedNode, new CC().push().grow());
  }

  public void showFirst() {
    Identifier first = findFirstKey();
    show(first);
  }

  private Identifier findFirstKey() {
    Optional<Identifier> first = namedNodes.keySet().stream().findFirst();
    return first.orElseThrow(() -> new IllegalStateException("No perspectives found"));
  }
}