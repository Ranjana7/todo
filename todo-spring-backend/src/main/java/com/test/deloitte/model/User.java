package com.test.deloitte.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USER")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class User implements Serializable {
  
  public User(String name) {
    this.name = name;
  }

  @Id
  @Column(name = "USER_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty(value = "userId")
  @Getter
  @Setter
  private Long id;

  @Column(name = "USER_NAME",unique = true)
  @JsonProperty(value = "userName", required = true)
  @Getter
  @Setter
  private String name;
  
  @OneToMany(mappedBy = "user", orphanRemoval = true)
  @Getter
  @Setter
  List<Todo> todos;

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", todos=" + todos + "]";
  }
  
}
