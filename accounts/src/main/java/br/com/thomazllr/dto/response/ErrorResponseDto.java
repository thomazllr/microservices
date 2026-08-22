package br.com.thomazllr.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponseDto", description = "Resposta padrão para erros da API")
public class ErrorResponseDto {

    @Schema(description = "Caminho da requisição que gerou o erro", example = "uri=/customers")
    private String apiPath;

    @Schema(description = "Código HTTP do erro", example = "BAD_REQUEST")
    private HttpStatus errorCode;

    @Schema(description = "Mensagem detalhando o erro", example = "Customer already exists with the given mobile number")
    private String errorMessage;

    @Schema(description = "Data e hora da ocorrência do erro", example = "2026-08-22T10:15:30")
    private LocalDateTime errorTime;

}
