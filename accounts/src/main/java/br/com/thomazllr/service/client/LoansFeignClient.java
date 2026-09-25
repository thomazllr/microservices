package br.com.thomazllr.service.client;

import br.com.thomazllr.dto.response.LoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping("/loans")
    LoanResponse getOne(@RequestParam String mobileNumber);

}
