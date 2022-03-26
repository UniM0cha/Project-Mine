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
  public String crawl(String url, Model model) throws IOException {
    log.info("=======================> crawling url...: " + url);

    CrawlingDTO crawlingDTO = new CrawlingDTO();
    crawlingDTO.setUrl(url);
    crawlingService.checkMall(crawlingDTO);
    return "index";
  }
}