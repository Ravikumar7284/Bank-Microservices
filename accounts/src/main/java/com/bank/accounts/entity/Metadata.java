package com.bank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class Metadata {

  @Column(name="created_at",updatable = false)
  private LocalDateTime createdAt;

  @Column(name="created_by",updatable = false)
  private String createdBy;

  @Column(name="updated_at",insertable = false)
  private LocalDateTime updatedAt;

  @Column(name="updated_by",insertable = false)
  private String updatedBy;

}
