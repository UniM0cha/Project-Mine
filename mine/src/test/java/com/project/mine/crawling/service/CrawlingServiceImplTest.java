package com.project.mine.crawling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.project.mine.dto.MallType;
import com.project.mine.dto.StockStatus;
import com.project.mine.service.CrawlingService;
import com.project.mine.service.CrawlingServiceImpl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.RequiredArgsConstructor;

@SpringBootTest
public class CrawlingServiceImplTest {

  @Autowired
  private CrawlingService crawlingService;

  @Nested
  class testCheckMall {
    @Test
    void 쿠팡_모바일() {
      String url = "https://m.coupang.com/vm/products/2228437830?itemId=3801622640&q=라스텔라%20성경&searchId=149552130c0f4b089514bd911e4b7fa8";
      // CrawlingService crawlingService = new CrawlingServiceImpl();
      MallType mallType = crawlingService.checkMall(url);
      assertEquals(MallType.Coupang, mallType);
    }

    @Test
    void 쿠팡_데스크탑() {
      String url = "https://www.coupang.com/vp/products/2228437830?itemId=3801622640&vendorItemId=71786595035&q=%EB%9D%BC%EC%8A%A4%ED%85%94%EB%9D%BC+%EC%84%B1%EA%B2%BD&itemsCount=36&searchId=8b1cf3f07789494fa356eef083c27445&rank=1&isAddedCart=";
      CrawlingService crawlingService = new CrawlingServiceImpl();
      MallType mallType = crawlingService.checkMall(url);
      assertEquals(MallType.Coupang, mallType);
    }

    @Test
    void 쿠팡_공유링크() {
      String url = "https://link.coupang.com/re/NONPROFITSDP?lptag=CFM18555774&pageKey=2228437830&itemId=3801622640&vendorItemId=71786595035";
      CrawlingService crawlingService = new CrawlingServiceImpl();
      MallType mallType = crawlingService.checkMall(url);
      assertEquals(MallType.Coupang, mallType);
    }

    @Test
    void 네이버() {
      String url = "https://smartstore.naver.com/storemjj/products/6194873260?NaPm=ct%3Dl1d28xuw%7Cci%3D7987b6a78628fc83f473a71e2e124667205e3709%7Ctr%3Dslc%7Csn%3D5219944%7Chk%3De301b4b43d993416023b1f44b2ce4b57d1db88b2";
      CrawlingService crawlingService = new CrawlingServiceImpl();
      MallType mallType = crawlingService.checkMall(url);
      assertEquals(MallType.Naver, mallType);
    }

    @Test
    void 옥션() {
      String url = "http://itempage3.auction.co.kr/DetailView.aspx?itemno=B866146767";
      CrawlingService crawlingService = new CrawlingServiceImpl();
      MallType mallType = crawlingService.checkMall(url);
      assertEquals(MallType.Auction, mallType);
    }

    @Test
    void 등록되지않은_쇼핑몰() {
      String url = "https://www.atomy.kr/v2/Home/Product/ProductView?GdsCode=004759&installYn=";
      CrawlingService crawlingService = new CrawlingServiceImpl();
      MallType mallType = crawlingService.checkMall(url);
      assertEquals(MallType.Unknown, mallType);
    }

    @Test
    void 이상한_링크() {
      String url = "httㄴㅁㄹㄷpㄷㄱdwdfw.atomy.kㅈㄷㅂr/v2/Hodf9&installYn=";
      CrawlingService crawlingService = new CrawlingServiceImpl();
      MallType mallType = crawlingService.checkMall(url);
      assertEquals(MallType.Unknown, mallType);
    }
  }

  @Nested
  class testCheckStock {
    @Test
    void 쿠팡_재고있음() {
      String url = "https://www.coupang.com/vp/products/2228437830?itemId=3801622640&vendorItemId=71786595035&q=%EB%9D%BC%EC%8A%A4%ED%85%94%EB%9D%BC+%EC%84%B1%EA%B2%BD&itemsCount=36&searchId=8b1cf3f07789494fa356eef083c27445&rank=1&isAddedCart=";
      CrawlingService crawlingService = new CrawlingServiceImpl();
      StockStatus stockStatus = crawlingService.checkStock(url, MallType.Coupang);
      assertEquals(StockStatus.Available, stockStatus);
    }

    @Test
    void 쿠팡_재고없음() {
      String url = "https://www.coupang.com/vp/products/2228437830?itemId=3801622640&vendorItemId=71786595062&q=%EB%9D%BC%EC%8A%A4%ED%85%94%EB%9D%BC+%EC%84%B1%EA%B2%BD&itemsCount=36&searchId=8b1cf3f07789494fa356eef083c27445&rank=1&isAddedCart=";
      CrawlingService crawlingService = new CrawlingServiceImpl();
      StockStatus stockStatus = crawlingService.checkStock(url, MallType.Coupang);
      assertEquals(StockStatus.OutOfStock, stockStatus);

    }
  }

}
