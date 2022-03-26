package com.project.mine.crawling.service;

import java.io.IOException;

import com.project.mine.crawling.dto.CrawlingDTO;

public interface CrawlingService {
  public CrawlingDTO checkMall(CrawlingDTO crawlingDTO) throws IOException;

  public boolean checkStock(CrawlingDTO crawlingDTO);
}