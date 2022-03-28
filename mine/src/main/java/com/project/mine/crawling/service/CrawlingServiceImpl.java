package com.project.mine.crawling.service;

import java.io.IOException;
import java.net.URL;

import com.project.mine.crawling.dto.CrawlingDTO;
import com.project.mine.crawling.dto.MallType;
import com.project.mine.crawling.mall.CoupangCrawling;
import com.project.mine.crawling.mall.MallCrawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CrawlingServiceImpl implements CrawlingService {

  CoupangCrawling coupangCrawling;

  public CrawlingServiceImpl(CoupangCrawling coupangCrawling) {
    this.coupangCrawling = coupangCrawling;
  }

  @Override
  public int checkMall(CrawlingDTO crawlingDTO) {
    Document document;
    try {
      document = Jsoup.connect(crawlingDTO.getUrl()).get();
      String baseUri = document.baseUri();

      URL aURL = new URL(baseUri);
      String host = aURL.getHost();

      if (host.equals("www.coupang.com") || host.equals("m.coupang.com")) {
        crawlingDTO.setMallType(MallType.Coupang);
        crawlingDTO.setDocument(document);
        return 0;
      } else {
        return 1;
      }

    } catch (Exception e) {
      // e.printStackTrace();
      return 2;
    }

    // System.out.println("protocol = " + aURL.getProtocol()); // http
    // System.out.println("authority = " + aURL.getAuthority()); // example.com:80
    // System.out.println("host = " + aURL.getHost()); // example.com
    // System.out.println("port = " + aURL.getPort()); // 80
    // System.out.println("path = " + aURL.getPath()); //
    // /docs/books/tutorial/index.html
    // System.out.println("query = " + aURL.getQuery()); // name=networking
    // System.out.println("filename = " + aURL.getFile()); ///
    // docs/books/tutorial/index.html?name=networking
    // System.out.println("ref = " + aURL.getRef()); // DOWNLOADING

  }

  @Override
  public int checkStock(CrawlingDTO crawlingDTO) {
    int resultCode = 3;
    // 쿠팡일 경우
    if (crawlingDTO.getMallType() == MallType.Coupang) {
      resultCode = coupangCrawling.isStock(crawlingDTO.getDocument());
    }

    return resultCode;
  }

}