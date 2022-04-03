package com.project.mine.crawling.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.project.mine.crawling.dto.MallType;

public class Crawl {
  @Id
  @GeneratedValue
  private Long crawlId;
  private String uri;
  private MallType mallType;
}