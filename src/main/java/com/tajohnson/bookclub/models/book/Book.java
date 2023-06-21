package com.tajohnson.bookclub.models.book;

import com.tajohnson.bookclub.models.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Book title is required")
  @Size(min = 2, message = "Minimum of 2 characters required")
  private String title;

  @NotBlank(message = "Author name is required")
  @Size(min = 2, message = "Minimum of 2 characters required")
  private String author;

  @NotBlank(message = "You read it - what did you think?")
  @Size(min = 2, message = "Minimum of 2 characters required")
  private String comments;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(updatable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;

  public Book() {
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = new Date();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}