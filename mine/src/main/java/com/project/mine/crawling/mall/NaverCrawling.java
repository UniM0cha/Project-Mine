package com.project.mine.crawling.mall;

import com.project.mine.crawling.dto.StockStatus;

import org.jsoup.nodes.Document;

public class NaverCrawling implements MallCrawling {

  @Override
  public StockStatus isStock(String url) {
    // TODO : 네이버 크롤링 구현
    return StockStatus.OutOfStock;
  }

}