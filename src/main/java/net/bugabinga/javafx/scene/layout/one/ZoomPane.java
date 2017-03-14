/** © 2017 Oliver Jan Krylow */
package net.bugabinga.javafx.scene.layout.one;

import org.eclipse.jdt.annotation.NonNullByDefault;

import javafx.collections.*;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * @author oliver
 * @date Mar 12, 2017
 */
public class ZoomPane extends Region {
  private final ObservableList<Node> ghosts;

  /** @param children Initial Nodes to add to the ZoomPane. */
  public ZoomPane(final Node... children) {
    getChildren().addAll(children);
    ghosts = FXCollections.observableArrayList();
  }

  @Override
  @NonNullByDefault({
    /*
     * disable nullness api contract for legacy super type
     */
  })
  public ObservableList<Node> getChildren() {
    return super.getChildren();
  }

  /** @return the ghost nodes of the ZoomPane. Ghosts scale differently then normal children. */
  public ObservableList<Node> getGhosts() {
    return ghosts;
  }
}
