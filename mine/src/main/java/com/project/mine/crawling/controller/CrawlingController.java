package com.project.mine.crawling.controller;

import java.io.IOException;

import com.project.mine.crawling.dto.CrawlingDTO;
import com.project.mine.crawling.service.CrawlingService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CrawlingController {

  CrawlingService crawlingService;

  public CrawlingController(CrawlingService crawlingService) {
    this.crawlingService = crawlingService;
  }

  @GetMapping("/")
  public String main() {
    return "index";
  }

  @PostMapping("/crawl")
  public String crawl(CrawlingDTO crawlingDTO, Model model) throws IOException {
    String url = crawlingDTO.getUrl();
    log.info("요청한 url: " + url);

    int mallResultCode = crawlingService.checkMall(crawlingDTO);
    log.info("checkMall 결과코드: " + mallResultCode + " / MallType: " + crawlingDTO.getMallType());

    if (mallResultCode == 0) {
      model.addAttribute("mall", crawlingDTO.getMallType());
      int stockResultCode = crawlingService.checkStock(crawlingDTO);
      if (stockResultCode == 0)
        model.addAttribute("stockCode", "재고 있음");
      else if (stockResultCode == 1)
        model.addAttribute("stockCode", "재고 없음");
    }
    return "index";
  }
}