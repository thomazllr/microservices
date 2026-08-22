package br.com.thomazllr.mapper;

import br.com.thomazllr.domain.Loan;
import br.com.thomazllr.dto.request.LoanRequest;
import br.com.thomazllr.dto.response.LoanResponse;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public Loan toEntity(LoanRequest dto) {
        return Loan.builder()
                .mobileNumber(dto.getMobileNumber())
                .build();
    }

    public LoanResponse toResponse(Loan loan) {
        return LoanResponse.builder()
                .id(loan.getLoanId())
                .mobileNumber(loan.getMobileNumber())
                .loanNumber(loan.getLoanNumber())
                .loanType(loan.getLoanType())
                .totalLoan(loan.getTotalLoan())
                .amountPaid(loan.getAmountPaid())
                .outstandingAmount(loan.getOutstandingAmount())
                .build();
    }
}
