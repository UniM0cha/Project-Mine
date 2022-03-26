package com.project.mine.crawling.service;

import java.io.IOException;

import com.project.mine.crawling.dto.CrawlingDTO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CrawlingServiceImpl implements CrawlingService {

  @Override
  public CrawlingDTO checkMall(CrawlingDTO crawlingDTO) throws IOException {
    Document document = Jsoup.connect(crawlingDTO.getUrl()).get();

    Elements element = document.select(".oos-label");
    String oosText = element.text();
    log.info("oos-label: " + oosText);
    return null;
  }

  @Override
  public boolean checkStock(CrawlingDTO crawlingDTO) {
    // TODO Auto-generated method stub
    return false;
  }

}