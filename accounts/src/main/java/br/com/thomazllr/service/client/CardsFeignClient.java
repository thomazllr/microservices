package br.com.thomazllr.service.client;

import br.com.thomazllr.dto.response.CardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping("/cards")
    CardResponse getOne(@RequestParam String mobileNumber);

}
