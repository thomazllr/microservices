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
@Schema(name = "LoanResponse", description = "Resposta com os dados do empréstimo")
public class LoanResponse {

    @Schema(description = "Identificador do empréstimo", example = "1")
    private Long id;

    @Schema(description = "Número de celular do cliente", example = "11999998888")
    private String mobileNumber;

    @Schema(description = "Número do empréstimo", example = "123456789012")
    private String loanNumber;

    @Schema(description = "Tipo do empréstimo", example = "Home Loan")
    private String loanType;

    @Schema(description = "Valor total do empréstimo", example = "100000")
    private Integer totalLoan;

    @Schema(description = "Valor já pago", example = "15000")
    private Integer amountPaid;

    @Schema(description = "Valor restante em aberto", example = "85000")
    private Integer outstandingAmount;
}
