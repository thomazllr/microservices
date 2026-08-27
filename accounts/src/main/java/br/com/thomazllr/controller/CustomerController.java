package br.com.thomazllr.controller;

import br.com.thomazllr.controller.docs.CustomerControllerDocs;
import br.com.thomazllr.dto.request.CustomerAccountUpdateRequest;
import br.com.thomazllr.dto.request.CustomerRequest;
import br.com.thomazllr.dto.response.AccountsContactInfo;
import br.com.thomazllr.dto.response.CustomerResponse;
import br.com.thomazllr.dto.response.ResponseDto;
import br.com.thomazllr.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.thomazllr.constants.AccountsConstants.MESSAGE_201;
import static br.com.thomazllr.constants.AccountsConstants.STATUS_201;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController implements CustomerControllerDocs {

    private final CustomerService customerService;

    @Value("${build.info}")
    private String buildVersion;

    private final AccountsContactInfo contactInfo;

    @Override
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody @Valid CustomerRequest request) {
        customerService.save(request);
        return ResponseEntity.status(CREATED).body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Override
    @GetMapping
    public ResponseEntity<CustomerResponse> getOne(@RequestParam String mobileNumber) {
        return ResponseEntity.ok(customerService.findOneByMobileNumber(mobileNumber));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CustomerAccountUpdateRequest request) {
        customerService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.ok(buildVersion);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfo> getContactInfo() {
        return ResponseEntity.ok(contactInfo);
    }
}
