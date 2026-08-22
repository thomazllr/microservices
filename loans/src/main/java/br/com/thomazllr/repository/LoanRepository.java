package br.com.thomazllr.repository;

import br.com.thomazllr.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    boolean existsByMobileNumber(String mobileNumber);

    Optional<Loan> findByMobileNumber(String mobileNumber);
}
