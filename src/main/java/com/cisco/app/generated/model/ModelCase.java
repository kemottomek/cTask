package com.cisco.app.generated.model;

import java.net.URI;
import java.util.Objects;
import com.cisco.app.generated.model.Note;
import com.cisco.app.generated.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Case model
 */

@Schema(name = "Case", description = "Case model")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ModelCase   {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("severity")
  private Integer severity;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    OPEN("OPEN"),
    
    CLOSED("CLOSED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("status")
  private StatusEnum status;

  @JsonProperty("user")
  private User user;

  @JsonProperty("notes")
  @Valid
  private Set<Note> notes = null;

  public ModelCase id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ModelCase title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  
  @Schema(name = "title", required = false)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ModelCase description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ModelCase severity(Integer severity) {
    this.severity = severity;
    return this;
  }

  /**
   * Get severity
   * @return severity
  */
  
  @Schema(name = "severity", required = false)
  public Integer getSeverity() {
    return severity;
  }

  public void setSeverity(Integer severity) {
    this.severity = severity;
  }

  public ModelCase status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "status", required = false)
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ModelCase user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @Valid 
  @Schema(name = "user", required = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public ModelCase notes(Set<Note> notes) {
    this.notes = notes;
    return this;
  }

  public ModelCase addNotesItem(Note notesItem) {
    if (this.notes == null) {
      this.notes = new LinkedHashSet<>();
    }
    this.notes.add(notesItem);
    return this;
  }

  /**
   * Get notes
   * @return notes
  */
  @Valid 
  @Schema(name = "notes", required = false)
  public Set<Note> getNotes() {
    return notes;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setNotes(Set<Note> notes) {
    this.notes = notes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCase _case = (ModelCase) o;
    return Objects.equals(this.id, _case.id) &&
        Objects.equals(this.title, _case.title) &&
        Objects.equals(this.description, _case.description) &&
        Objects.equals(this.severity, _case.severity) &&
        Objects.equals(this.status, _case.status) &&
        Objects.equals(this.user, _case.user) &&
        Objects.equals(this.notes, _case.notes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, severity, status, user, notes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCase {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    severity: ").append(toIndentedString(severity)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
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

