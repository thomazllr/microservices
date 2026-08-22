package br.com.thomazllr.controller.docs;

import br.com.thomazllr.dto.request.LoanRequest;
import br.com.thomazllr.dto.request.LoanUpdateRequest;
import br.com.thomazllr.dto.response.ErrorResponseDto;
import br.com.thomazllr.dto.response.LoanResponse;
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

@Tag(name = "Loans", description = "Operações para cadastro, consulta, atualização e remoção de empréstimos")
public interface LoanControllerDocs {

    @Operation(
            summary = "Cria um novo empréstimo",
            description = "Cadastra um empréstimo padrão a partir do número de celular informado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Empréstimo criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou empréstimo já existente",
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
                    description = "Dados para criação do empréstimo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoanRequest.class))
            )
            @Valid LoanRequest request
    );

    @Operation(
            summary = "Busca um empréstimo por número de celular",
            description = "Retorna os dados do empréstimo associado ao número de celular informado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Empréstimo encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = LoanResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Empréstimo não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<LoanResponse> getOne(
            @Parameter(description = "Número de celular do cliente", required = true, example = "11999998888")
            @RequestParam String mobileNumber
    );

    @Operation(
            summary = "Remove um empréstimo",
            description = "Exclui o empréstimo com base no identificador informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empréstimo removido com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Empréstimo não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "Identificador do empréstimo", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualiza um empréstimo",
            description = "Atualiza os dados do empréstimo a partir do identificador informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empréstimo atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Empréstimo não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<Void> update(
            @Parameter(description = "Identificador do empréstimo", required = true, example = "1")
            @PathVariable Long id,
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para atualização do empréstimo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoanUpdateRequest.class))
            )
            @Valid LoanUpdateRequest request
    );
}
