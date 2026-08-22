package br.com.thomazllr.mapper;

import br.com.thomazllr.domain.Account;
import br.com.thomazllr.domain.Customer;
import br.com.thomazllr.dto.request.CustomerAccountUpdateRequest;
import br.com.thomazllr.dto.request.CustomerRequest;
import br.com.thomazllr.dto.response.AccountResponse;
import br.com.thomazllr.dto.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest dto) {
        return Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .mobileNumber(dto.getMobileNumber())
                .build();
    }

    public CustomerResponse toResponse(Customer customer, Account account) {
        return CustomerResponse.builder()
                .id(customer.getCustomerId())
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .account(AccountResponse.builder()
                        .accountNumber(account.getAccountNumber())
                        .accountType(account.getAccountType())
                        .branchAddress(account.getBranchAddress())
                        .build())
                .build();
    }

    public Customer toEntity(CustomerAccountUpdateRequest dto, long id) {
        return Customer.builder()
                .customerId(id)
                .name(dto.getName())
                .email(dto.getEmail())
                .mobileNumber(dto.getMobileNumber())
                .build();
    }
}
