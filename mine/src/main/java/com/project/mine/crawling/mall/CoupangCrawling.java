package com.project.mine.crawling.mall;

import org.springframework.stereotype.Component;

@Component
public class CoupangCrawling implements MallCrawling {

  private String url;

  @Override
  public boolean isStock(String url) {
    // TODO : 쿠팡 크롤링 구현
    return false;
  }

}