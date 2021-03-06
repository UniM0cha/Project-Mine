package com.project.mine.mall;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.project.mine.dto.StockStatus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CoupangCrawling implements MallCrawling {
  @Override
  public StockStatus isStock(String url) {
    /**
     * 1. URL을 받아옴
     * 2. 스크래핑을 통해서 쿠팡상품번호를 받아옴
     * 3. 받아온 상품 번호가 데이터베이스에 등록되어있는지 확인
     * 4. 없으면 추가, 있으면 알림 울려야 할 것에 등록
     */
    try {

      Document doc = Jsoup.connect(url).get();
      String baseUri = doc.baseUri();
      URL pharseUrl = new URL(baseUri);

      String path = pharseUrl.getPath();
      // log.info("============ path =======> " + path);

      // 상품 페이지인지 확인

      if (isProductPage(doc)) {
        // 페이지 아이디와 상품 아이디 가져오기
        Elements items = doc.select(".prod-attr-item");
        String itemInfo = items.get(items.size() - 1).text();
        log.info("=========== itemInfo =========> " + itemInfo);
        itemInfo = itemInfo.replace("쿠팡상품번호: ", "");
        String[] ids = itemInfo.split(" - ");
        String pageId = ids[0];
        String itemId = ids[1];

        if (isExistDB(pageId, itemId)) {
          // TODO: 사용자 알림에 추가하는 로직 추가
          addUserNotice();
          /**
           * 사용자 알림에 추가한다?
           * 페이지 id와 아이템 id를 가져와서
           */
        }
        // 없으면 데이터베이스 추가
        else {
          // TODO: 크롤링 데이터베이스에 추가하는 로직 추가
          addCrawlCoupang(pageId, itemId);
          // TODO: 사용자 알림에 추가하는 로직 추가
          addUserNotice();
        }

        // if (path.contains("/vp/products/")) {

        // String pageId = path.substring(path.lastIndexOf("/") + 1);
        // log.info("========== pageId ===========> " + pageId);

        // String[] queryStrings = pharseUrl.getQuery().split("&");
        // Map<String, String> queryMap = new HashMap<>();
        // for (String query : queryStrings) {
        // String[] p = query.split("=");
        // String name = p[0];
        // if (p.length > 1) {
        // String value = p[1];
        // queryMap.put(name, value);
        // }
        // }
        // log.info("========= itemId =======> " + queryMap.get("itemId"));
      }
      // 상품 페이지가 아님
      else {
        log.info("============= 상품 페이지가 아님 ============");
        return null;
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

      Elements element = doc.select(".oos-label");

      element.hasClass("className");

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

  private boolean isProductPage(Document doc) {
    try {
      doc.select(".prod-attr-item").get(0);
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    return true;
  }

  private boolean isExistDB(String pageId, String itemId) {
    // TODO: 데이터베이스에 있는지 없는지 확인하는 코드 삽입

    return true;
  }

  private void addCrawlCoupang(String pageId, String itemId) {
    /**
     * 크롤링 데이터베이스에 추가한다?
     * = 스케줄링을 통해서 크롤링 테이블에 있는 요소들을 싹 한번 훑을건데
     * 그때 어떻게 해야 잘 넣을 수 있을까
     * = 크롤링은 일단 쿠팡에서는 URI만 제공이 되면 된다.
     */

  }

  private void addUserNotice() {

  }
}