package br.com.thomazllr.controller;

import br.com.thomazllr.constants.CardsConstants;
import br.com.thomazllr.controller.docs.CardControllerDocs;
import br.com.thomazllr.dto.request.CardRequest;
import br.com.thomazllr.dto.request.CardUpdateRequest;
import br.com.thomazllr.dto.response.CardResponse;
import br.com.thomazllr.dto.response.CardsContactInfo;
import br.com.thomazllr.dto.response.ResponseDto;
import br.com.thomazllr.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController implements CardControllerDocs {

    private final CardService cardService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private CardsContactInfo contactInfo;

    @Override
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody @Valid CardRequest request) {
        cardService.save(request);
        return ResponseEntity.status(CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Override
    @GetMapping
    public ResponseEntity<CardResponse> getOne(@RequestParam String mobileNumber) {
        return ResponseEntity.ok(cardService.findOneByMobileNumber(mobileNumber));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CardUpdateRequest request) {
        cardService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.ok(buildVersion);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfo> getContactInfo() {
        return ResponseEntity.ok(contactInfo);
    }
}
