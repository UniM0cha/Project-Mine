package com.project.mine.crawling.mall;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CoupangCrawling implements MallCrawling {
  @Override
  public int isStock(Document document) {
    // TODO : 쿠팡 크롤링 구현
    // log.info("=====================> body: " + document.body().html());
    Elements element = document.select(".oos-label");
    String oosText = element.text();
    log.info("oos-label: " + oosText);
    if (oosText.equals("품절")) {
      return 1;
    } else {
      return 0;
    }
  }
}