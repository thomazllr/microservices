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
@Schema(name = "AccountResponse", description = "Dados da conta vinculada ao cliente")
public class AccountResponse {
    @Schema(description = "Número da conta", example = "1000001")
    private Long accountNumber;
    @Schema(description = "Tipo da conta", example = "SAVINGS")
    private String accountType;
    @Schema(description = "Endereço da agência", example = "Avenida Paulista, 1000")
    private String branchAddress;
}
