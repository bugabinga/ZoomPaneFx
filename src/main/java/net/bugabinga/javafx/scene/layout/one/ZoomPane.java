/** © 2017 Oliver Jan Krylow */
package net.bugabinga.javafx.scene.layout.one;

import static java.util.Objects.*;

import org.eclipse.jdt.annotation.Nullable;

import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * TODO(oliver): Check out useful util function in com.sun.javafx.util.Utils which are
 * unfurtunatelly in private packages.
 *
 * @author Oliver Jan Krylow
 * @date Mar 12, 2017
 */
@DefaultProperty("content")
public class ZoomPane extends Control {

  /** @param content Initial Node to add to the ZoomPane. */
  public ZoomPane(final Node content) {
    setContent(content);
  }

  /** Constructs a {@link ZoomPane} without content. */
  public ZoomPane() {}

  /** The node used as the content of this ZoomPane. */
  private @Nullable ObjectProperty<Node> content;

  /** @param value The node used as the content of this ZoomPane. */
  public final void setContent(final Node value) {
    contentProperty().set(value);
  }

  /** @return The node used as the content of this ZoomPane. */
  public final @Nullable Node getContent() {
    return content != null ? content.get() : null;
  }

  /** @return The node used as the content of this ZoomPane. */
  public final ObjectProperty<Node> contentProperty() {
    if (content == null) {
      content = new SimpleObjectProperty<>(this, "content");
    }
    requireNonNull(content);
    return content;
  }

  /**
   * The ghost is a special Node above the content, which follows different scaling rules.
   *
   * <p>Instead of being scaled uniformly in layout position and styling, a ghost gets only scaled
   * in layout position.
   *
   * <p>For example, if the ghost is a {@link Line}, the line would grow/shrink according to the
   * zoom, the thickness of the stroke however is constant.
   *
   * <p>If the ghost is a {@link Text} element for example, it would adjust its layout position
   * according to zoom, the font size however would never change.
   *
   * <p>The ghost is meant for auxilerry user interface elements that accompany the content, but
   * need to be always visible.
   */
  private @Nullable ObjectProperty<Node> ghost;

  /**
   * @param ghost The ghost is a special Node above the content, which follows different scaling
   *     rules.
   */
  public void setGhost(final Node ghost) {
    ghostProperty().set(ghost);
  }

  /**
   * @return The ghost is a special Node above the content, which follows different scaling rules.
   */
  public @Nullable Node getGhost() {
    return ghost != null ? ghost.get() : null;
  }

  /** @return the ghost node of the ZoomPane. A Ghost scales differently then normal content. */
  public ObjectProperty<Node> ghostProperty() {
    if (ghost == null) {
      ghost = new SimpleObjectProperty<>(this, "ghost");
    }
    requireNonNull(ghost);
    return ghost;
  }

  /** The current zooom value. Default is 1.0. */
  private @Nullable DoubleProperty zoom;

  /** @param zoom The new zoom value. */
  public void setZoom(final double zoom) {
    zoomProperty().set(zoom);
  }

  /** @return The current zoom value. */
  public double getZoom() {
    return zoom != null ? zoom.get() : 1.0;
  }

  /** @return The zoom property. */
  public DoubleProperty zoomProperty() {
    if (zoom == null) {
      zoom = new SimpleDoubleProperty(this, "zoom", 1.0);
    }
    requireNonNull(zoom);
    return zoom;
  }

  /** The current mininum allowed value for zoom. Default is 0.0. */
  private @Nullable DoubleProperty minimumZoom;

  /** @param minimum The minimum allowed zoom value. */
  public void setMinimumZoom(final double minimum) {
    minimumZoomProperty().set(minimum);
  }

  /** @return The current minimum allowed zoom value. */
  public double getMinimumZoom() {
    return minimumZoom != null ? minimumZoom.get() : 0.0;
  }

  /** @return The minimum zoom property. */
  public DoubleProperty minimumZoomProperty() {
    if (minimumZoom == null) {
      minimumZoom = new SimpleDoubleProperty(this, "minimumZoom", 0.0);
    }
    requireNonNull(minimumZoom);
    return minimumZoom;
  }

  /** The current maximum allowed value for zoom. Default is {@link Double#MAX_VALUE}. */
  private @Nullable DoubleProperty maximumZoom;

  /** @param maximum The maximum allowed zoom value. */
  public void setMaximumZoom(final double maximum) {
    maximumZoomProperty().set(maximum);
  }

  /** @return The current maximum allowed zoom value. */
  public double getMaximumZoom() {
    return maximumZoom != null ? maximumZoom.get() : Double.MAX_VALUE;
  }

  /** @return The maximum zoom property. */
  public DoubleProperty maximumZoomProperty() {
    if (maximumZoom == null) {
      maximumZoom = new SimpleDoubleProperty(this, "maximumZoom", Double.MAX_VALUE);
    }
    requireNonNull(maximumZoom);
    return maximumZoom;
  }
}
