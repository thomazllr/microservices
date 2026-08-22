package br.com.thomazllr.service;

import br.com.thomazllr.constants.CardsConstants;
import br.com.thomazllr.domain.Card;
import br.com.thomazllr.dto.request.CardRequest;
import br.com.thomazllr.dto.request.CardUpdateRequest;
import br.com.thomazllr.dto.response.CardResponse;
import br.com.thomazllr.exception.CardAlreadyExistsException;
import br.com.thomazllr.exception.ResourceNotFoundException;
import br.com.thomazllr.mapper.CardMapper;
import br.com.thomazllr.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;
    private final CardMapper mapper;

    @Transactional
    public void save(CardRequest request) {
        if (repository.existsByMobileNumber(request.getMobileNumber())) {
            throw new CardAlreadyExistsException("Card already registered with the given mobile number");
        }

        Card card = mapper.toEntity(request);

        card.generateCardNumber();
        card.setCardType(CardsConstants.CREDIT_CARD);
        card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        repository.save(card);
    }

    public CardResponse findOneByMobileNumber(String mobileNumber) {
        Card card = repository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Card with mobile number %s not found".formatted(mobileNumber)
                ));

        return mapper.toResponse(card);
    }

    @Transactional
    public void update(Long id, CardUpdateRequest request) {
        Card card = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Card with ID %s not found".formatted(id)
                ));

        repository.findByMobileNumber(request.getMobileNumber())
                .filter(existingCard -> !existingCard.getCardId().equals(id))
                .ifPresent(existingCard -> {
                    throw new CardAlreadyExistsException("Another card is already registered with the given mobile number");
                });

        if (request.getAmountUsed() > request.getTotalLimit()) {
            throw new IllegalArgumentException("Amount used can not be greater than total limit");
        }

        if (request.getAvailableAmount() != request.getTotalLimit() - request.getAmountUsed()) {
            throw new IllegalArgumentException("Available amount must be equal to total limit minus amount used");
        }

        card.setMobileNumber(request.getMobileNumber());
        card.setCardType(request.getCardType());
        card.setTotalLimit(request.getTotalLimit());
        card.setAmountUsed(request.getAmountUsed());
        card.setAvailableAmount(request.getAvailableAmount());

        repository.save(card);
    }

    @Transactional
    public void delete(Long id) {
        Card card = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Card with ID %s not found".formatted(id)
                ));

        repository.delete(card);
    }

}
