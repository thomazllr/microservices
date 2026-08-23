package br.com.thomazllr.service;

import br.com.thomazllr.constants.AccountsConstants;
import br.com.thomazllr.domain.Account;
import br.com.thomazllr.domain.Customer;
import br.com.thomazllr.exception.ResourceNotFound;
import br.com.thomazllr.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public void save(Customer customer) {
        var account = Account.builder()
                .customerId(customer.getCustomerId())
                .accountType(AccountsConstants.SAVINGS)
                .branchAddress(AccountsConstants.ADDRESS)
                .build();

        account.generateAccountNumber();

        repository.save(account);

    }

    public Account getAccountByCustomerId(Long customerId) {
        return repository
                .findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFound(
                        "Account for customer ID %d not found".formatted(customerId)
                ));
    }

    public void delete(Long customerId) {
        var account = getAccountByCustomerId(customerId);
        repository.delete(account);
    }
}
