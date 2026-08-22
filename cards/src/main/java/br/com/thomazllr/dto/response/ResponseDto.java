package br.com.thomazllr.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ResponseDto", description = "Resposta padrão para operações concluídas com sucesso")
public class ResponseDto {

    @Schema(description = "Código do status retornado", example = "201")
    private String statusCode;

    @Schema(description = "Mensagem de sucesso", example = "Card created successfully")
    private String message;
}
