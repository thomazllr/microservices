package br.com.thomazllr.controller.docs;

import br.com.thomazllr.dto.request.CardRequest;
import br.com.thomazllr.dto.request.CardUpdateRequest;
import br.com.thomazllr.dto.response.CardResponse;
import br.com.thomazllr.dto.response.ErrorResponseDto;
import br.com.thomazllr.dto.response.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Cards", description = "Operações para cadastro, consulta, atualização e remoção de cartões")
public interface CardControllerDocs {

    @Operation(
            summary = "Cria um novo cartão",
            description = "Cadastra um cartão padrão a partir do número de celular informado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cartão criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou cartão já existente",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<ResponseDto> create(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para criação do cartão",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CardRequest.class))
            )
            @Valid CardRequest request
    );

    @Operation(
            summary = "Busca um cartão por número de celular",
            description = "Retorna os dados do cartão associado ao número de celular informado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cartão encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = CardResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cartão não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<CardResponse> getOne(
            @Parameter(description = "Número de celular do cliente", required = true, example = "11999998888")
            @RequestParam String mobileNumber
    );

    @Operation(
            summary = "Remove um cartão",
            description = "Exclui o cartão com base no identificador informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cartão removido com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cartão não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "Identificador do cartão", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualiza um cartão",
            description = "Atualiza os dados do cartão a partir do identificador informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cartão atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cartão não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<Void> update(
            @Parameter(description = "Identificador do cartão", required = true, example = "1")
            @PathVariable Long id,
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para atualização do cartão",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CardUpdateRequest.class))
            )
            @Valid CardUpdateRequest request
    );
}
