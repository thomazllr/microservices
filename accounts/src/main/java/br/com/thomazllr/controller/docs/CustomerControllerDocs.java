package br.com.thomazllr.controller.docs;

import br.com.thomazllr.dto.request.CustomerAccountUpdateRequest;
import br.com.thomazllr.dto.request.CustomerRequest;
import br.com.thomazllr.dto.response.CustomerResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Customers", description = "Operações para cadastro, consulta, atualização e remoção de clientes")
public interface CustomerControllerDocs {

    @Operation(
            summary = "Cria um novo cliente",
            description = "Cadastra um cliente e sua conta inicial a partir dos dados enviados no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou cliente já existente",
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
                    description = "Dados para criação do cliente",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CustomerRequest.class))
            )
            @Valid CustomerRequest request
    );

    @Operation(
            summary = "Busca um cliente por número de celular",
            description = "Retorna os dados do cliente e da conta vinculada a partir do número de celular informado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<CustomerResponse> getOne(
            @Parameter(
                    description = "Número de celular do cliente",
                    required = true,
                    example = "11999998888"
            )
            @RequestParam String mobileNumber
    );

    @Operation(
            summary = "Remove um cliente",
            description = "Exclui o cliente e seus dados associados com base no identificador informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "Identificador do cliente", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualiza um cliente",
            description = "Atualiza os dados do cliente e da conta vinculada usando o identificador informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<Void> update(
            @Parameter(description = "Identificador do cliente", required = true, example = "1")
            @PathVariable Long id,
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para atualização do cliente e da conta",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CustomerAccountUpdateRequest.class))
            )
            @Valid CustomerAccountUpdateRequest request
    );

    @Operation(
            summary = "Consulta a informação de build",
            description = "Retorna a versão de build configurada para a aplicação."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informação de build retornada com sucesso",
                    content = @Content(schema = @Schema(type = "string", example = "1.0"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    ResponseEntity<String> getBuildInfo();
}
