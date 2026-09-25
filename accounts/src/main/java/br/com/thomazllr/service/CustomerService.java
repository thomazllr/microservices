package br.com.thomazllr.service;

import br.com.thomazllr.dto.request.CustomerAccountUpdateRequest;
import br.com.thomazllr.dto.request.CustomerRequest;
import br.com.thomazllr.dto.response.CustomerDetailsResponse;
import br.com.thomazllr.dto.response.CustomerResponse;
import br.com.thomazllr.exception.CustomerAlreadyExistsException;
import br.com.thomazllr.exception.ResourceNotFound;
import br.com.thomazllr.mapper.CustomerMapper;
import br.com.thomazllr.repository.CustomerRepository;
import br.com.thomazllr.service.client.CardsFeignClient;
import br.com.thomazllr.service.client.LoansFeignClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    private final AccountService accountService;

    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loanFeignClient;

    @Transactional
    public void save(CustomerRequest customerRequest) {
        var customer = mapper.toEntity(customerRequest);

        var exists = repository.existsByEmailOrMobileNumber(customer.getEmail(), customer.getMobileNumber());

        if (exists) throw new CustomerAlreadyExistsException("Customer already exists");

        var saved = repository.save(customer);
        accountService.save(saved);
    }

    public CustomerResponse findOneByMobileNumber(String mobileNumber) {
        var customer = repository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFound(
                        String.format("Customer with mobile number %s not found", mobileNumber)
                ));

        var account = accountService.getAccountByCustomerId(customer.getCustomerId());

        return mapper.toResponse(customer, account);
    }

    public CustomerDetailsResponse findOneWithDetailByMobileNumber(String mobileNumber) {
        var customer = repository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFound(
                        String.format("Customer with mobile number %s not found", mobileNumber)
                ));

        var account = accountService.getAccountByCustomerId(customer.getCustomerId());

        var card = cardsFeignClient.getOne(mobileNumber);

        var loan = loanFeignClient.getOne(mobileNumber);

       return mapper.toResponse(customer, account, loan, card);
    }

    @Transactional
    public void delete(Long id) {
        var customer = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound(
                        String.format("Customer with ID %s not found", id)
                ));

        accountService.delete(customer.getCustomerId());
        repository.delete(customer);
    }

    @Transactional
    public void update(Long id, CustomerAccountUpdateRequest customerRequest) {

        var account = accountService.getAccountByCustomerId(id);

        account.setBranchAddress(customerRequest.getAccount().getBranchAddress());
        account.setAccountType(customerRequest.getAccount().getAccountType());

        var customer = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound(
                        String.format("Customer with ID %s not found", id)
                ));


        customer = mapper.toEntity(customerRequest, id);
        repository.save(customer);
    }

}
