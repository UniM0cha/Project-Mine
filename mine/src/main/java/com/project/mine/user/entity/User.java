package com.project.mine.user.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
  @Id
  private UUID userId;

  private String kakaoId;
  private String email;
  private String profileImg;
  private String nickname;
}
