package com.project.mine.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.project.mine.dto.MallType;
import com.project.mine.dto.StockStatus;

import lombok.Getter;

@Entity
@Getter
public class Crawl {
  @Id
  @GeneratedValue
  @Column(name = "crawl_id")
  private Long id;

  private String uri;

  @Enumerated(EnumType.STRING)
  private MallType mallType;

  @Enumerated(EnumType.STRING)
  private StockStatus stockStatus;

  @Embedded
  private Coupang coupang;
}