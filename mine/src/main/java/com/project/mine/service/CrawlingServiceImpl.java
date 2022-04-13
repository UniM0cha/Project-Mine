package com.project.mine.service;

import java.net.MalformedURLException;
import java.net.URL;

import com.project.mine.dto.MallType;
import com.project.mine.dto.StockStatus;
import com.project.mine.mall.CoupangCrawling;
import com.project.mine.mall.MallCrawling;
import com.project.mine.mall.NaverCrawling;

import org.springframework.stereotype.Service;

@Service
public class CrawlingServiceImpl implements CrawlingService {

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