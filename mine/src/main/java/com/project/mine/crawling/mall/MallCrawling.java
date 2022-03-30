package com.project.mine.crawling.mall;

import com.project.mine.crawling.dto.StockStatus;

public interface MallCrawling {
  public StockStatus isStock(String url);
}