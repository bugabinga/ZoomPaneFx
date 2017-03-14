/** © 2017 Oliver Jan Krylow */
package net.bugabinga.javafx.scene.layout.one;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.*;
import static org.testfx.matcher.base.NodeMatchers.*;

import java.util.Objects;
import java.util.stream.IntStream;

import org.eclipse.jdt.annotation.Nullable;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * @author oliver
 * @date 10.03.2017
 */
@SuppressWarnings({"javadoc", "static-method"})
public class ZoomPaneGuiTest extends ApplicationTest {

  @Test
  public void givenSomeNode_whenAddedAsGhostElement_thenItShouldBeInGhost() throws Exception {

    final ZoomPane pane = new ZoomPane();
    final Rectangle boundingBox = new Rectangle();
    pane.getGhosts().add(boundingBox);

    assertThat(pane.getGhosts().size(), is(1));
  }

  @Test
  public void givenALotOfChildren_whenAddedToZoomPane_thenZoomPaneShouldContainAll()
      throws Exception {
    final ZoomPane pane = new ZoomPane();
    final int amount = 100000;
    IntStream.range(0, amount).forEach(__ -> pane.getChildren().add(new Rectangle()));
    assertThat(pane.getChildrenUnmodifiable().size(), is(amount));
  }

  @Test
  public void givenNoChildren_whenConstructingAZoomPane_thenANewZoomPaneShouldAppear() {
    final ZoomPane pane = new ZoomPane();
    assertThat(pane, notNullValue());

    verifyThat(pane, isNotNull());
    verifyThat(pane, isVisible());
    verifyThat(pane, isEnabled());
    verifyThat(pane.getChildrenUnmodifiable(), empty());
  }

  @Test
  public void givenOneChild_whenAddingItToZoomPane_thenZoomPaneShouldContainIt() throws Exception {
    final Node child = new Circle();
    final ZoomPane pane = new ZoomPane(child);

    verifyThat(pane.getChildrenUnmodifiable(), not(empty()));
  }

  @Override
  public void start(final @Nullable Stage stage) throws Exception {
    Objects.requireNonNull(stage);
    final StackPane root = new StackPane();
    final Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
