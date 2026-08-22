package br.com.thomazllr.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CustomerAccountUpdateRequest", description = "Payload para atualização de cliente e conta")
public class CustomerAccountUpdateRequest {

    @Schema(description = "Identificador do cliente", example = "1")
    private Long id;

    @Schema(description = "Nome completo do cliente", example = "Thomaz Lima")
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(description = "Email do cliente", example = "thomazllrdev@gmail.com")
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(description = "Número de celular do cliente", example = "11999998888")
    @Pattern(
            regexp = "(^(55)?(?:([1-9]{2})?)(\\d{4,5})(\\d{4})$)",
            message = "Invalid mobile number"
    )
    private String mobileNumber;

    @Schema(description = "Dados da conta vinculada ao cliente")
    @Valid
    private AccountUpdateRequest account;
}
