package com.project.mine.crawling.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import antlr.collections.List;

@Entity
public class Coupang {
  @Id
  @GeneratedValue
  private Long copangId;
  private String pageId;
  private String itemId;

  @OneToOne
  private Crawl crawl;
}
