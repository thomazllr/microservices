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
@Schema(name = "LoanUpdateRequest", description = "Payload para atualização dos dados de um empréstimo")
public class LoanUpdateRequest {

    @Schema(description = "Número de celular do cliente", example = "11999998888")
    @NotBlank(message = "Mobile number can not be null or empty")
    @Pattern(
            regexp = "(^(55)?(?:([1-9]{2})?)(\\d{4,5})(\\d{4})$)",
            message = "Invalid mobile number"
    )
    private String mobileNumber;

    @Schema(description = "Tipo do empréstimo", example = "Home Loan")
    @NotBlank(message = "Loan type can not be null or empty")
    private String loanType;

    @Schema(description = "Valor total do empréstimo", example = "100000")
    @NotNull(message = "Total loan can not be null")
    @Min(value = 0, message = "Total loan can not be negative")
    private Integer totalLoan;

    @Schema(description = "Valor já pago", example = "15000")
    @NotNull(message = "Amount paid can not be null")
    @Min(value = 0, message = "Amount paid can not be negative")
    private Integer amountPaid;

    @Schema(description = "Valor restante em aberto", example = "85000")
    @NotNull(message = "Outstanding amount can not be null")
    @Min(value = 0, message = "Outstanding amount can not be negative")
    private Integer outstandingAmount;
}
