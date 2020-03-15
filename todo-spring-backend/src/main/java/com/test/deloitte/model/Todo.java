package com.test.deloitte.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TODO")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Todo implements Serializable {

  public Todo(@Size(min = 2, max = 100) String title, String status, LocalDate createdOn) {
    super();
    this.title = title;
    this.status = status;
    this.createdOn = createdOn;
  }
  

  public Todo(@Size(min = 2, max = 100) String title, String status, LocalDate createdOn, User user) {
    super();
    this.title = title;
    this.status = status;
    this.createdOn = createdOn;
    this.user = user;
  }

  @Column(name = "TODO_ID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Size(min = 2, max = 100)
  @Column(name = "ANOTHER_TITLE", nullable = false, unique = true, updatable = true)
  @JsonProperty(value = "title", required = true)
  @Getter
  @Setter
  private String title;

  @Column(name = "STATUS")
  @Getter
  @Setter
  private String status;

  @Column(name = "LAST_UPDATED")
  @Getter
  @Setter
  private LocalDate lastUpdatedOn;

  @Column(name = "CREATED_ON")
  @Getter
  @Setter
  private LocalDate createdOn;

  @Column(name = "IS_COMPLETED", columnDefinition = "boolean default false")
  @Getter
  @Setter
  private boolean isCompleted;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  @Setter
  @JsonIgnore
  private User user;

  @Override
  public String toString() {
    return "Todo [id=" + id + ", title=" + title + ", status=" + status + ", updatedDate=" + lastUpdatedOn + ", createdOn=" + createdOn
        + ", isCompleted=" + isCompleted + "]";
  }

}