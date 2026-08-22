package br.com.thomazllr.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "LoanRequest", description = "Payload para criação de um empréstimo")
public class LoanRequest {

    @Schema(description = "Número de celular do cliente", example = "11999998888")
    @NotBlank(message = "Mobile number can not be null or empty")
    @Pattern(
            regexp = "(^(55)?(?:([1-9]{2})?)(\\d{4,5})(\\d{4})$)",
            message = "Invalid mobile number"
    )
    private String mobileNumber;
}
