package com.northstar.crm.service;

import com.northstar.crm.account.Account;
import com.northstar.crm.account.AccountRepository;
import com.northstar.crm.account.TransactionLog;
import com.northstar.crm.account.TransactionLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {
  private final AccountRepository accountRepository;
  private final TransactionLogRepository transactionLogRepository;

  public TransferService(AccountRepository accountRepository,
                         TransactionLogRepository transactionLogRepository) {
    this.accountRepository = accountRepository;
    this.transactionLogRepository = transactionLogRepository;
  }

  // TODO: add @Transactional on this method (service-layer boundary)
  @Transactional
  public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
    Account from = accountRepository.findById(fromAccountId)
        .orElseThrow(() -> new IllegalArgumentException("Unknown from account"));
    // TODO: if toAccountId equals "ACC-FORCE-FAIL" → throw IllegalStateException to force rollback
    from.setBalance(from.getBalance().subtract(amount));
    accountRepository.save(from);

    if ("ACC-FORCE-FAIL".equals(toAccountId)) {
      throw new IllegalStateException(
              "Forced transfer failure for rollback demo");
    }
    Account to = accountRepository.findById(toAccountId)
        .orElseThrow(() -> new IllegalArgumentException("Unknown to account"));

    // TODO: debit from, credit to, save both
    to.setBalance(to.getBalance().add(amount));
    accountRepository.save(to);

    // TODO: write TransactionLog row

    TransactionLog log = new TransactionLog();
    log.setFromAccountId(fromAccountId);
    log.setToAccountId(toAccountId);
    log.setAmount(amount);

    transactionLogRepository.save(log);
   // throw new UnsupportedOperationException("TODO: implement transactional transfer");
  }
}
