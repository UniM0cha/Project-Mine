package com.project.mine.crawling.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.project.mine.crawling.dto.MallType;
import com.project.mine.crawling.dto.StockStatus;

@Entity
public class Crawl {
  @Id
  @GeneratedValue
  private Long crawlId;
  private String uri;
  private MallType mallType;
  private StockStatus stockStatus;

  @OneToOne
  private Coupang coupang;
}