package com.project.mine.mall;

import com.project.mine.dto.StockStatus;

public interface MallCrawling {
  public StockStatus isStock(String url);
}