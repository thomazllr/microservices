package br.com.thomazllr.controller;

import br.com.thomazllr.constants.LoansConstants;
import br.com.thomazllr.controller.docs.LoanControllerDocs;
import br.com.thomazllr.dto.request.LoanRequest;
import br.com.thomazllr.dto.request.LoanUpdateRequest;
import br.com.thomazllr.dto.response.LoanResponse;
import br.com.thomazllr.dto.response.ResponseDto;
import br.com.thomazllr.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController implements LoanControllerDocs {

    private final LoanService loanService;

    @Override
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody @Valid LoanRequest request) {
        loanService.save(request);
        return ResponseEntity.status(CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Override
    @GetMapping
    public ResponseEntity<LoanResponse> getOne(@RequestParam String mobileNumber) {
        return ResponseEntity.ok(loanService.findOneByMobileNumber(mobileNumber));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid LoanUpdateRequest request) {
        loanService.update(id, request);
        return ResponseEntity.noContent().build();
    }
}
