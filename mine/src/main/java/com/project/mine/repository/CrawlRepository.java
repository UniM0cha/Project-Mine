package com.project.mine.repository;

import com.project.mine.domain.Crawl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlRepository extends JpaRepository<Crawl, Long> {

}
