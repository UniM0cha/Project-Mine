package com.project.mine.crawling.mall;

import java.io.IOException;

import com.project.mine.crawling.dto.StockStatus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CoupangCrawling implements MallCrawling {
  @Override
  public StockStatus isStock(String url) {
    try {
      Document document = Jsoup.connect(url).get();
      Elements element = document.select(".oos-label");

      String oosText = element.text();
      log.info("oos-label: " + oosText);

      if (oosText.equals("품절")) {
        return StockStatus.OutOfStock;
      } else {
        return StockStatus.Available;
      }

    } catch (IOException e) {
      return StockStatus.Unknown;
    }
  }
}