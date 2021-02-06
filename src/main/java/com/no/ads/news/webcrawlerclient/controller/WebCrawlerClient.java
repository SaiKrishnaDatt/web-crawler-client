package com.no.ads.news.webcrawlerclient.controller;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author : ssuddhapally
 * @since : 06/02/21, Sat
 **/
@Component
public class WebCrawlerClient {

  private final RestTemplate restTemplate;

  public WebCrawlerClient() {
    ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();
    this.restTemplate = new RestTemplate(requestFactory);
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
  }

  public void performSearchCall() {
    String searchToken = "India";

    String requestUrl = "http://localhost:8080/live-news";

    // set headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(searchToken, headers);

    // send request and parse result
    ResponseEntity<String> responseEntity = restTemplate
            .exchange(requestUrl, HttpMethod.POST, entity, String.class);
    System.out.println("response:" + responseEntity.getBody());

  }

  private ClientHttpRequestFactory getClientHttpRequestFactory() {
    int timeout = 60000;
    RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(timeout)
            .setConnectionRequestTimeout(timeout)
            .setSocketTimeout(timeout)
            .build();
    CloseableHttpClient client = HttpClientBuilder
            .create()
            .setDefaultRequestConfig(config)
            .build();
    return new HttpComponentsClientHttpRequestFactory(client);
  }
}