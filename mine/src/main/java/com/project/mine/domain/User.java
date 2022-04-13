package com.project.mine.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "USERS")
@Getter
public class User {
  @Id
  @Column(name = "user_id")
  private UUID id;

  private String kakaoId;
  private String email;
  private String profileImg;
  private String nickname;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  public User updateEmail(String email) {
    this.email = email;
    return this;
  }

  public User updateNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

}