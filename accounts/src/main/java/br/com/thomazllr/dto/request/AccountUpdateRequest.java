package br.com.thomazllr.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AccountUpdateRequest", description = "Payload com os dados da conta do cliente")
public class AccountUpdateRequest {
    @Schema(description = "Número da conta", example = "1000001")
    @NotBlank(message = "Account number can not be null")
    private Long accountNumber;

    @Schema(description = "Tipo da conta", example = "SAVINGS")
    @NotBlank(message = "Account type can not be null")
    private String accountType;

    @Schema(description = "Endereço da agência", example = "Avenida Paulista, 1000")
    @NotBlank(message = "Branch address can not be null")
    private String branchAddress;
}
