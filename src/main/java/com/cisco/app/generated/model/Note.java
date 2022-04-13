package com.cisco.app.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Note object
 */

@Schema(name = "Note", description = "Note object")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Note   {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("details")
  private String details;

  public Note id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * note id
   * @return id
  */
  
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, description = "note id", required = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Note details(String details) {
    this.details = details;
    return this;
  }

  /**
   * Get details
   * @return details
  */
  
  @Schema(name = "details", required = false)
  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Note note = (Note) o;
    return Objects.equals(this.id, note.id) &&
        Objects.equals(this.details, note.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Note {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

