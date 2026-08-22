package br.com.thomazllr.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CardUpdateRequest", description = "Payload para atualização dos dados de um cartão")
public class CardUpdateRequest {

    @Schema(description = "Número de celular do cliente", example = "11999998888")
    @NotBlank(message = "Mobile number can not be null or empty")
    @Pattern(
            regexp = "(^(55)?(?:([1-9]{2})?)(\\d{4,5})(\\d{4})$)",
            message = "Invalid mobile number"
    )
    private String mobileNumber;

    @Schema(description = "Tipo do cartão", example = "Credit Card")
    @NotBlank(message = "Card type can not be null or empty")
    private String cardType;

    @Schema(description = "Limite total do cartão", example = "100000")
    @NotNull(message = "Total limit can not be null")
    @Min(value = 0, message = "Total limit can not be negative")
    private Integer totalLimit;

    @Schema(description = "Valor já utilizado do limite", example = "15000")
    @NotNull(message = "Amount used can not be null")
    @Min(value = 0, message = "Amount used can not be negative")
    private Integer amountUsed;

    @Schema(description = "Valor ainda disponível", example = "85000")
    @NotNull(message = "Available amount can not be null")
    @Min(value = 0, message = "Available amount can not be negative")
    private Integer availableAmount;
}
