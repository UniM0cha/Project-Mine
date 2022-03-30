package com.project.mine.crawling.mall;

import com.project.mine.crawling.dto.StockStatus;

import org.jsoup.nodes.Document;

public interface MallCrawling {
  public StockStatus isStock(String url);
}