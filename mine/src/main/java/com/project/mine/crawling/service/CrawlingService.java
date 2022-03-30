package com.project.mine.crawling.service;

import com.project.mine.crawling.dto.MallType;
import com.project.mine.crawling.dto.StockStatus;

public interface CrawlingService {

  public MallType checkMall(String url);

  public StockStatus checkStock(String url, MallType mallType);
}