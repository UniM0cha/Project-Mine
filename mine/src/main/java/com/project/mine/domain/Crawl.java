package com.project.mine.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
  private MallType mallType;
  private StockStatus stockStatus;

  @Embedded
  private Coupang coupang;
}