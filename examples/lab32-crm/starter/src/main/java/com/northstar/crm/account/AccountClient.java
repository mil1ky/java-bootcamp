package com.northstar.crm.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AccountClient
{

  private final RestClient restClient;

  public AccountClient(@Value("${account.api.base-url}") String baseUrl)
  {
    this.restClient = RestClient.builder().baseUrl(baseUrl).build();
  }

  public AccountSummary fetch(String customerId)
  {

    // TODO: GET /accounts/{customerId}/summary — map 5xx to TemporaryAccountException
    try
    {
      // existing HTTP call here

    }
    catch (Exception ex)
    {
      throw new TemporaryAccountException(
              "Account profile unavailable", ex);

    }
    return null;
  }

}
