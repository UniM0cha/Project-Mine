package com.project.mine.service;

import com.project.mine.dto.MallType;
import com.project.mine.dto.StockStatus;

public interface CrawlingService {

  public MallType checkMall(String url);

  public StockStatus checkStock(String url, MallType mallType);
}