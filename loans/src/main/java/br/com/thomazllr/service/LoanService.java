package br.com.thomazllr.service;

import br.com.thomazllr.constants.LoansConstants;
import br.com.thomazllr.domain.Loan;
import br.com.thomazllr.dto.request.LoanRequest;
import br.com.thomazllr.dto.request.LoanUpdateRequest;
import br.com.thomazllr.dto.response.LoanResponse;
import br.com.thomazllr.exception.LoanAlreadyExistsException;
import br.com.thomazllr.exception.ResourceNotFoundException;
import br.com.thomazllr.mapper.LoanMapper;
import br.com.thomazllr.repository.LoanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository repository;
    private final LoanMapper mapper;

    @Transactional
    public void save(LoanRequest request) {
        if (repository.existsByMobileNumber(request.getMobileNumber())) {
            throw new LoanAlreadyExistsException("Loan already registered with the given mobile number");
        }

        Loan loan = mapper.toEntity(request);
        loan.generateLoanNumber();
        loan.setLoanType(LoansConstants.HOME_LOAN);
        loan.setTotalLoan(LoansConstants.NEW_LOAN_AMOUNT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoansConstants.NEW_LOAN_AMOUNT);

        repository.save(loan);
    }

    public LoanResponse findOneByMobileNumber(String mobileNumber) {
        Loan loan = repository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Loan with mobile number %s not found".formatted(mobileNumber)
                ));

        return mapper.toResponse(loan);
    }

    @Transactional
    public void update(Long id, LoanUpdateRequest request) {
        Loan loan = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Loan with ID %s not found".formatted(id)
                ));

        repository.findByMobileNumber(request.getMobileNumber())
                .filter(existingLoan -> !existingLoan.getLoanId().equals(id))
                .ifPresent(existingLoan -> {
                    throw new LoanAlreadyExistsException("Another loan is already registered with the given mobile number");
                });

        if (request.getAmountPaid() > request.getTotalLoan()) {
            throw new IllegalArgumentException("Amount paid can not be greater than total loan");
        }

        if (request.getOutstandingAmount() != request.getTotalLoan() - request.getAmountPaid()) {
            throw new IllegalArgumentException("Outstanding amount must be equal to total loan minus amount paid");
        }

        loan.setMobileNumber(request.getMobileNumber());
        loan.setLoanType(request.getLoanType());
        loan.setTotalLoan(request.getTotalLoan());
        loan.setAmountPaid(request.getAmountPaid());
        loan.setOutstandingAmount(request.getOutstandingAmount());

        repository.save(loan);
    }

    @Transactional
    public void delete(Long id) {
        Loan loan = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Loan with ID %s not found".formatted(id)
                ));

        repository.delete(loan);
    }
}
