package com.project.mine.crawling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import com.project.mine.crawling.dto.CrawlingDTO;
import com.project.mine.crawling.dto.MallType;
import com.project.mine.crawling.mall.CoupangCrawling;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CrawlingServiceTest {

  @Nested
  @DisplayName("checkMall 테스트")
  class testCheckMall {

    @Nested
    @DisplayName("쿠팡")
    class Coupang {
      @Test
      void 모바일링크_재고있음() {
        CrawlingDTO crawlingDTO = new CrawlingDTO();
        crawlingDTO.setUrl(
            "https://m.coupang.com/vm/products/2228437830?itemId=3801622640&q=라스텔라%20성경&searchId=149552130c0f4b089514bd911e4b7fa8");

        CoupangCrawling coupangCrawling = new CoupangCrawling();
        CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
        int resultCode = crawlingService.checkMall(crawlingDTO);

        assertEquals(MallType.Coupang, crawlingDTO.getMallType());
        assertEquals(0, resultCode);
      }

      @Test
      void 모바일링크_재고없음() {
        CrawlingDTO crawlingDTO = new CrawlingDTO();
        crawlingDTO.setUrl(
            "https://m.coupang.com/vm/products/2228437830?itemId=3801622640&q=라스텔라%20성경&searchId=149552130c0f4b089514bd911e4b7fa8");

        CoupangCrawling coupangCrawling = new CoupangCrawling();
        CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
        int resultCode = crawlingService.checkMall(crawlingDTO);

        assertEquals(MallType.Coupang, crawlingDTO.getMallType());
        assertEquals(0, resultCode);
      }

      @Test
      void 모바일링크_홈페이지() {
        CrawlingDTO crawlingDTO = new CrawlingDTO();
        crawlingDTO.setUrl("https://m.coupang.com/nm/");

        CoupangCrawling coupangCrawling = new CoupangCrawling();
        CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
        int resultCode = crawlingService.checkMall(crawlingDTO);

        assertEquals(MallType.Coupang, crawlingDTO.getMallType());
        assertEquals(0, resultCode);
      }

      @Test
      void 데스크탑링크_재고있음() {
        CrawlingDTO crawlingDTO = new CrawlingDTO();
        crawlingDTO.setUrl(
            "https://www.coupang.com/vp/products/2228437830?itemId=3801622640&vendorItemId=71786595035&q=%EB%9D%BC%EC%8A%A4%ED%85%94%EB%9D%BC+%EC%84%B1%EA%B2%BD&itemsCount=36&searchId=8b1cf3f07789494fa356eef083c27445&rank=1&isAddedCart=");

        CoupangCrawling coupangCrawling = new CoupangCrawling();
        CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
        int resultCode = crawlingService.checkMall(crawlingDTO);

        assertEquals(MallType.Coupang, crawlingDTO.getMallType());
        assertEquals(0, resultCode);
      }

      @Test
      void 데스크탑링크_재고없음() {
        CrawlingDTO crawlingDTO = new CrawlingDTO();
        crawlingDTO.setUrl(
            "https://www.coupang.com/vp/products/2228437830?itemId=3801622640&vendorItemId=71786595062&q=%EB%9D%BC%EC%8A%A4%ED%85%94%EB%9D%BC+%EC%84%B1%EA%B2%BD&itemsCount=36&searchId=8b1cf3f07789494fa356eef083c27445&rank=1&isAddedCart=");

        CoupangCrawling coupangCrawling = new CoupangCrawling();
        CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
        int resultCode = crawlingService.checkMall(crawlingDTO);

        assertEquals(MallType.Coupang, crawlingDTO.getMallType());
        assertEquals(0, resultCode);
      }

      @Test
      void 데스크탑링크_홈페이지() {
        CrawlingDTO crawlingDTO = new CrawlingDTO();
        crawlingDTO.setUrl("https://www.coupang.com/");

        CoupangCrawling coupangCrawling = new CoupangCrawling();
        CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
        int resultCode = crawlingService.checkMall(crawlingDTO);

        assertEquals(MallType.Coupang, crawlingDTO.getMallType());
        assertEquals(0, resultCode);
      }

      @Test
      void 공유링크_재고있음() {
        CrawlingDTO crawlingDTO = new CrawlingDTO();
        crawlingDTO.setUrl(
            "https://link.coupang.com/re/NONPROFITSDP?lptag=CFM18555774&pageKey=2228437830&itemId=3801622640&vendorItemId=71786595035");

        CoupangCrawling coupangCrawling = new CoupangCrawling();
        CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
        int resultCode = crawlingService.checkMall(crawlingDTO);

        assertEquals(MallType.Coupang, crawlingDTO.getMallType());
        assertEquals(0, resultCode);
      }

      @Test
      void 공유링크_재고없음() {
        CrawlingDTO crawlingDTO = new CrawlingDTO();
        crawlingDTO.setUrl(
            "https://link.coupang.com/re/NONPROFITSDP?lptag=CFM18555774&pageKey=2228437830&itemId=3801622641&vendorItemId=71786595062");

        CoupangCrawling coupangCrawling = new CoupangCrawling();
        CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
        int resultCode = crawlingService.checkMall(crawlingDTO);

        assertEquals(MallType.Coupang, crawlingDTO.getMallType());
        assertEquals(0, resultCode);
      }

    }

    @Test
    void 이상한링크() {
      CrawlingDTO crawlingDTO = new CrawlingDTO();
      crawlingDTO.setUrl("https:/dfkj--sdw/m.coupang.com/sdfsdfnm/");

      CoupangCrawling coupangCrawling = new CoupangCrawling();
      CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
      int resultCode = crawlingService.checkMall(crawlingDTO);

      assertEquals(null, crawlingDTO.getMallType());
      assertEquals(2, resultCode);
    }
  }

  @Nested
  class testCheckStock {

    @Test
    void 재고있음() {
      CrawlingDTO crawlingDTO = new CrawlingDTO();
      crawlingDTO.setUrl(
          "https://www.coupang.com/vp/products/2228437830?itemId=3801622640&vendorItemId=71786595035&q=%EB%9D%BC%EC%8A%A4%ED%85%94%EB%9D%BC+%EC%84%B1%EA%B2%BD&itemsCount=36&searchId=8b1cf3f07789494fa356eef083c27445&rank=1&isAddedCart=");
      try {
        crawlingDTO.setDocument(Jsoup.connect(crawlingDTO.getUrl()).get());
        crawlingDTO.setMallType(MallType.Coupang);
      } catch (IOException e) {
        e.printStackTrace();
      }

      CoupangCrawling coupangCrawling = new CoupangCrawling();
      CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
      int result = crawlingService.checkStock(crawlingDTO);

      assertEquals(0, result);
    }

    @Test
    void 재고없음() {
      CrawlingDTO crawlingDTO = new CrawlingDTO();
      crawlingDTO.setUrl(
          "https://www.coupang.com/vp/products/2228437830?itemId=3801622640&vendorItemId=71786595062&q=%EB%9D%BC%EC%8A%A4%ED%85%94%EB%9D%BC+%EC%84%B1%EA%B2%BD&itemsCount=36&searchId=8b1cf3f07789494fa356eef083c27445&rank=1&isAddedCart=");
      try {
        crawlingDTO.setDocument(Jsoup.connect(crawlingDTO.getUrl()).get());
        crawlingDTO.setMallType(MallType.Coupang);
      } catch (IOException e) {
        e.printStackTrace();
      }

      CoupangCrawling coupangCrawling = new CoupangCrawling();
      CrawlingService crawlingService = new CrawlingServiceImpl(coupangCrawling);
      int result = crawlingService.checkStock(crawlingDTO);

      assertEquals(1, result);
    }
  }

}
