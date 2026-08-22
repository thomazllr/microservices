package br.com.thomazllr.repository;

import br.com.thomazllr.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    boolean existsByMobileNumber(String mobileNumber);

    Optional<Card> findByMobileNumber(String mobileNumber);
}
