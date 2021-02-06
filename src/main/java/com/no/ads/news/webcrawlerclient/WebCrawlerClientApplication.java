package com.no.ads.news.webcrawlerclient;

import com.no.ads.news.webcrawlerclient.controller.WebCrawlerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author : ssuddhapally
 * @since : 06/02/21, Sat
 **/
@SpringBootApplication
public class WebCrawlerClientApplication implements CommandLineRunner {
  private final WebCrawlerClient webCrawlerClient;

  @Autowired
  public WebCrawlerClientApplication(WebCrawlerClient webCrawlerClient) {
    this.webCrawlerClient = webCrawlerClient;
  }

  public static void main(String[] args) {
    SpringApplication.run(WebCrawlerClientApplication.class, args);
  }

  @Override
  public void run(String... strings) throws Exception {
    webCrawlerClient.performSearchCall();
  }

}
