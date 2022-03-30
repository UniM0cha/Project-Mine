package com.project.mine.crawling.service;

import java.net.MalformedURLException;
import java.net.URL;

import com.project.mine.crawling.dto.MallType;
import com.project.mine.crawling.dto.StockStatus;
import com.project.mine.crawling.mall.CoupangCrawling;
import com.project.mine.crawling.mall.MallCrawling;
import com.project.mine.crawling.mall.NaverCrawling;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CrawlingServiceImpl implements CrawlingService {

  // @Override
  // public Document getDocument(String url) {
  // try {
  // return Jsoup.connect(url).get();
  // } catch (IOException e) {
  // return null;
  // }
  // }

  @Override
  public MallType checkMall(String url) {
    try {
      URL aURL = new URL(url);
      String host = aURL.getHost();
      if (host.equals("www.coupang.com") || host.equals("m.coupang.com") || host.equals("link.coupang.com")) {
        return MallType.Coupang;
      } else {
        return MallType.Unknown;
      }

    } catch (MalformedURLException e) {
      return MallType.Unknown;
    }
  }

  @Override
  public StockStatus checkStock(String url, MallType mallType) {
    MallCrawling mallCrawling;

    if (mallType == MallType.Coupang) {
      mallCrawling = new CoupangCrawling();
    } else if (mallType == MallType.Naver) {
      mallCrawling = new NaverCrawling();
    } else {
      throw new Error("등록되지 않은 쇼핑몰");
    }

    return mallCrawling.isStock(url);
  }

}