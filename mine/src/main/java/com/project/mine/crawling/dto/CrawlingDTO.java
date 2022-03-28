package com.project.mine.crawling.dto;

import org.jsoup.nodes.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrawlingDTO {
  private String url;
  private MallType mallType;
  private StockStatus stockStatus;
  private Document document;
}