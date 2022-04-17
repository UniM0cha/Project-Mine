package com.project.mine.controller;

import java.security.Principal;
import java.util.UUID;

import com.project.mine.domain.User;
import com.project.mine.dto.CrawlingDTO;
import com.project.mine.dto.MallType;
import com.project.mine.dto.StockStatus;
import com.project.mine.service.CrawlingService;
import com.project.mine.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CrawlingController {

  private final CrawlingService crawlingService;
  private final UserService userService;

  @PostMapping("/crawl")
  public String handleUrl(CrawlingDTO crawlingDTO, Model model) {
    String url = crawlingDTO.getUrl();
    log.info("========= 요청한 url ==========> " + url);

    MallType mallType = crawlingService.checkMall(url);
    /**
     * mallType
     * 1. 각 쇼핑몰 enum -> 등록된 쇼핑몰
     * 2. Unknown -> 등록된 쇼핑몰이 아님
     */

    if (mallType != MallType.Unknown) {

      crawlingDTO.setMallType(mallType);
      StockStatus stockStatus = crawlingService.checkStock(url, mallType);
      /**
       * stockStatus
       * 1. 재고 있음 상태
       * 2. 재고 없음 상태
       * 3. null -> 상품 페이지가 아님
       */

      if (stockStatus != StockStatus.Unknown) {

        crawlingDTO.setStockStatus(stockStatus);
      }
    }
    model.addAttribute("crawl", crawlingDTO);
    return "index";
  }
}