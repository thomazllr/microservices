package br.com.thomazllr.repository;

import br.com.thomazllr.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmailOrMobileNumber(String email, String mobileNumber);

    Optional<Customer> findByMobileNumber(String mobileNumber);
}
