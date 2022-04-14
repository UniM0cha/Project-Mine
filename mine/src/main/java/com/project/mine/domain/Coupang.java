package com.project.mine.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
public class Coupang {
  private String pageId;
  private String itemId;
}
