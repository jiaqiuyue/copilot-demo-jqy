package edu.wisc.my.messages.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * ActionButton
 */

public class ActionButton {

  @JsonProperty("label")
  private String label = null;

  @JsonProperty("url")
  private String url = null;

  public ActionButton label(String label) {
    this.label = label;
    return this;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public ActionButton url(String url) {
    this.url = url;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActionButton actionButton = (ActionButton) o;
    return Objects.equals(this.label, actionButton.label) &&
      Objects.equals(this.url, actionButton.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActionButton {\n");

    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
