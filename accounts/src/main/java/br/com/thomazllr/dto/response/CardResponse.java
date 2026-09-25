package br.com.thomazllr.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CardResponse", description = "Resposta com os dados do cartão")
public class CardResponse {

    @Schema(description = "Identificador do cartão", example = "1")
    private Long id;

    @Schema(description = "Número de celular do cliente", example = "11999998888")
    private String mobileNumber;

    @Schema(description = "Número do cartão", example = "4567123412341234")
    private String cardNumber;

    @Schema(description = "Tipo do cartão", example = "Credit Card")
    private String cardType;

    @Schema(description = "Limite total do cartão", example = "100000")
    private Integer totalLimit;

    @Schema(description = "Valor já utilizado do limite", example = "15000")
    private Integer amountUsed;

    @Schema(description = "Valor ainda disponível", example = "85000")
    private Integer availableAmount;
}
