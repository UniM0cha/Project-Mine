package com.project.mine.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

@Entity
@Table(name = "USERS")
@Getter
public class User {
  @Id
  @Column(name = "user_id", columnDefinition = "BINARY(16)")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID id;

  @Column(unique = true, nullable = false)
  private String providerId;

  private String email;
  private String profileImg;
  private String nickname;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role = Role.ROLE_USER;

  // 생성 메서드
  public static User createNewUser(String providerId, String email, String profileImg, String nickname) {
    User user = new User();
    user.providerId = providerId;
    user.email = email;
    user.profileImg = profileImg;
    user.nickname = nickname;
    return user;
  }

  public User updateEmail(String email) {
    this.email = email;
    return this;
  }

  public User updateNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

}