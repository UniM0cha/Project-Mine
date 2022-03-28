package com.project.mine.crawling.service;

import java.io.IOException;

import com.project.mine.crawling.dto.CrawlingDTO;

public interface CrawlingService {
  /**
   * 쇼핑몰 판별하는 알고리즘 : 도메인 네임으로 판별..
   * 0 : 쇼핑몰 판별함 (정상)
   * 1 : 등록된 쇼핑몰이 아님
   * 2 : 올바르지 않은 URL 형식
   */
  public int checkMall(CrawlingDTO crawlingDTO);

  /**
   * 도메인 확인은 위에서 했으니 여기서는 재고가 있는지 없는지만 확인
   * 0 : 재고 있음
   * 1 : 재고 없음
   * 2 : 상품 없음 (홈페이지일 경우)
   * 3 : 알 수 없는 오류
   */
  public int checkStock(CrawlingDTO crawlingDTO);
}