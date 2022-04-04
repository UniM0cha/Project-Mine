package com.project.mine.crawling.repository;

import com.project.mine.crawling.entity.Crawl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlRepository extends JpaRepository<Crawl, Long> {

}
