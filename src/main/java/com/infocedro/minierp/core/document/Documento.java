package com.infocedro.minierp.core.document;

import java.time.LocalDateTime;
import java.util.UUID;

public class Documento {

    private final UUID id;
    private final TipoDocumento tipoDocumento;
    private StatusDocumento statusDocumento;
    private final LocalDateTime dataCriacao;
    private final Documento documentoOrigem;

    public Documento(TipoDocumento tipoDocumento, Documento documentoOrigem) {
        if (tipoDocumento == null) {
            throw new IllegalArgumentException("Tipo do documento é obrigatório");
        }

        this.id = UUID.randomUUID();
        this.tipoDocumento = tipoDocumento;
        this.statusDocumento = StatusDocumento.RASCUNHO;
        this.dataCriacao = LocalDateTime.now();
        this.documentoOrigem = documentoOrigem;
    }

    public void fechar() {
        if (this.statusDocumento != StatusDocumento.RASCUNHO) {
            throw new IllegalStateException(
                    "Documento só pode ser fechado se estiver em RASCUNHO");
        }

        // validarParaFecharDocumento();

        this.statusDocumento = StatusDocumento.FECHADO;
    }

    public void cancelar() {
        if (this.statusDocumento == StatusDocumento.CANCELADO) {
            throw new IllegalStateException(
                    "Documento já está cancelado");
        }
        if (this.statusDocumento == StatusDocumento.FECHADO) {
            throw new IllegalStateException(
                    "Documento fechado não pode ser cancelado");
        }
        this.statusDocumento = StatusDocumento.CANCELADO;
    }

    /*
     * private void validarParaFechamento() {
     * // Aqui entrarão regras de negócio:
     * // - possuir itens
     * // - valores válidos
     * // - cliente informado (se aplicável)
     * // - etc
     * }
     */

    public UUID getId() {
        return id;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public StatusDocumento getStatusDocumento() {
        return statusDocumento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Documento getDocumentoOrigem() {
        return documentoOrigem;
    }
}
