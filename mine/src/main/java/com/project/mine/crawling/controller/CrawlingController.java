package com.project.mine.crawling.controller;

import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.project.mine.crawling.dto.CrawlingDTO;
import com.project.mine.crawling.dto.MallType;
import com.project.mine.crawling.dto.StockStatus;
import com.project.mine.crawling.service.CrawlingService;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CrawlingController {

  CrawlingService crawlingService;

  public CrawlingController(CrawlingService crawlingService) {
    this.crawlingService = crawlingService;
  }

  @GetMapping("/")
  public String home() {
    return "index";
  }

  @PostMapping("/crawl")
  public String handleUrl(CrawlingDTO crawlingDTO, Model model) {
    String url = crawlingDTO.getUrl();
    log.info("요청한 url: " + url);

    MallType mallType = crawlingService.checkMall(url);

    if (mallType != MallType.Unknown) {

      crawlingDTO.setMallType(mallType);
      StockStatus stockStatus = crawlingService.checkStock(url, mallType);

      if (stockStatus != StockStatus.Unknown) {

        crawlingDTO.setStockStatus(stockStatus);
      }
    }
    model.addAttribute("crawl", crawlingDTO);
    return "index";
    // throw new Error("유효하지 않은 URL");
  }
}