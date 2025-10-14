package edu.wisc.my.messages.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Data
 */

public class Data {

  @JsonProperty("dataUrl")
  private String dataUrl = null;

  @JsonProperty("dataObject")
  private String dataObject = null;

  @JsonProperty("dataArrayFilter")
  private Object dataArrayFilter = null;

  @JsonProperty("dataMessageTitle")
  private String[] dataMessageTitle = null;

  @JsonProperty("dataMessageMoreInfoUrl")
  private String[] dataMessageMoreInfoUrl = null;

  public Data dataUrl(String dataUrl) {
    this.dataUrl = dataUrl;
    return this;
  }

  public String getDataUrl() {
    return dataUrl;
  }

  public void setDataUrl(String dataUrl) {
    this.dataUrl = dataUrl;
  }

  public Data dataObject(String dataObject) {
    this.dataObject = dataObject;
    return this;
  }

  public String getDataObject() {
    return dataObject;
  }

  public void setDataObject(String dataObject) {
    this.dataObject = dataObject;
  }

  public Data dataArrayFilter(Object dataArrayFilter) {
    this.dataArrayFilter = dataArrayFilter;
    return this;
  }

  public Object getDataArrayFilter() {
    return dataArrayFilter;
  }

  public void setDataArrayFilter(Object dataArrayFilter) {
    this.dataArrayFilter = dataArrayFilter;
  }

  public Data dataMessageTitle(String[] dataMessageTitle) {
    this.dataMessageTitle = dataMessageTitle;
    return this;
  }

  public String[] getDataMessageTitle() {
    return dataMessageTitle;
  }

  public void setDataMessageTitle(String[] dataMessageTitle) {
    this.dataMessageTitle = dataMessageTitle;
  }

  public Data dataMessageMoreInfoUrl(String[] dataMessageMoreInfoUrl) {
    this.dataMessageMoreInfoUrl = dataMessageMoreInfoUrl;
    return this;
  }

  public String[] getDataMessageMoreInfoUrl() {
    return dataMessageMoreInfoUrl;
  }

  public void setDataMessageMoreInfoUrl(String[] dataMessageMoreInfoUrl) {
    this.dataMessageMoreInfoUrl = dataMessageMoreInfoUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Data data = (Data) o;
    return Objects.equals(this.dataUrl, data.dataUrl) &&
      Objects.equals(this.dataObject, data.dataObject) &&
      Objects.equals(this.dataArrayFilter, data.dataArrayFilter) &&
      Objects.equals(this.dataMessageTitle, data.dataMessageTitle) &&
      Objects.equals(this.dataMessageMoreInfoUrl, data.dataMessageMoreInfoUrl);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(dataUrl, dataObject, dataArrayFilter, dataMessageTitle, dataMessageMoreInfoUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Data {\n");

    sb.append("    dataUrl: ").append(toIndentedString(dataUrl)).append("\n");
    sb.append("    dataObject: ").append(toIndentedString(dataObject)).append("\n");
    sb.append("    dataArrayFilter: ").append(toIndentedString(dataArrayFilter)).append("\n");
    sb.append("    dataMessageTitle: ").append(toIndentedString(dataMessageTitle)).append("\n");
    sb.append("    dataMessageMoreInfoUrl: ").append(toIndentedString(dataMessageMoreInfoUrl))
      .append("\n");
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
