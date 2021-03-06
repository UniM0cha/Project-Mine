package com.project.mine.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrawlingDTO {
  private String url;
  private MallType mallType;
  private StockStatus stockStatus;
}