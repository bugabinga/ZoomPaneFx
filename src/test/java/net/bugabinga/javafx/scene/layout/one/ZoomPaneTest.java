/** © 2017 Oliver Jan Krylow */
package net.bugabinga.javafx.scene.layout.one;

import static java.util.Objects.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.*;
import static org.testfx.matcher.base.NodeMatchers.*;

import java.util.stream.IntStream;

import org.eclipse.jdt.annotation.Nullable;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * @author Oliver Jan Krylow
 * @date 10.03.2017
 */
@SuppressWarnings({"javadoc", "static-method"})
public class ZoomPaneTest extends ApplicationTest {

  //(oliver) what should happen on zoom 0?
  //basically nothing. the content will be effectivly invisible.

  @Test
  public void givenAMinAndMaxValue_whenSet_thenZoomValueShouldBeClamped() throws Exception {
    final double minVal = 0.005;
    final double maxVal = 16.0;

    final ZoomPane pane = new ZoomPane();
    pane.setMinimumZoom(minVal);
    pane.setMaximumZoom(maxVal);

    assertThat(pane.getMinimumZoom(), is(minVal));
    assertThat(pane.getMaximumZoom(), is(maxVal));

    assertThat(pane.minimumZoomProperty().get(), is(minVal));
    assertThat(pane.maximumZoomProperty().get(), is(maxVal));

    pane.setZoom(10321.3213);
    assertThat(pane.getZoom(), is(maxVal));

    pane.setZoom(0.0001);
    assertThat(pane.getZoom(), is(minVal));
  }

  @Test
  public void givenAZoomValue_whenSet_thenItShouldBeGettable() {
    final ZoomPane pane = new ZoomPane();

    final double value = 2.0;
    pane.setZoom(value);
    assertThat(pane.getZoom(), is(value));

    assertThat(pane.zoomProperty().get(), is(value));
  }

  @Test
  public void givenSomeNode_whenAddedAsGhostElement_thenItShouldBeInGhost() throws Exception {
    final ZoomPane pane = new ZoomPane();
    final Rectangle boundingBox = new Rectangle();
    pane.setGhost(boundingBox);

    assertThat(pane.getGhost(), is(boundingBox));
  }

  @Test
  public void givenALotOfChildren_whenAddedToZoomPane_thenZoomPaneShouldContainAll()
      throws Exception {
    final ZoomPane pane = new ZoomPane();
    final int amount = 100000;
    final Group parent = new Group();
    pane.setContent(parent);
    IntStream.range(0, amount).forEach(__ -> parent.getChildren().add(new Rectangle()));
    @Nullable final Node content = pane.getContent();
    requireNonNull(content);
    assertThat(((Parent) content).getChildrenUnmodifiable().size(), is(amount));
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

    verifyThat(pane.getContent(), is(child));
  }

  @Override
  public void start(final @Nullable Stage stage) throws Exception {
    requireNonNull(stage);
    final StackPane root = new StackPane();
    final Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
