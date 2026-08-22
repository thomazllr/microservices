package br.com.thomazllr.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "CustomerResponse", description = "Resposta com os dados do cliente e da conta")
public class CustomerResponse {

    @Schema(description = "Identificador do cliente", example = "1")
    private Long id;
    @Schema(description = "Nome completo do cliente", example = "Thomaz Lima")
    private String name;
    @Schema(description = "Email do cliente", example = "thomazllrdev@gmail.com")
    private String email;
    @Schema(description = "Número de celular do cliente", example = "11999998888")
    private String mobileNumber;
    @Schema(description = "Dados da conta vinculada")
    private AccountResponse account;

}
