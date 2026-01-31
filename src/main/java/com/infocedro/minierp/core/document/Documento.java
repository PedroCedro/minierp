package com.infocedro.minierp.core.document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Documento {

    private final UUID id;
    private final TipoDocumento tipoDocumento;
    private StatusDocumento statusDocumento;
    private final LocalDateTime dataCriacao;
    private final Documento documentoOrigem;

    private final List<ItemDocumento> itensDocumento;

    public Documento(TipoDocumento tipoDocumento, Documento documentoOrigem) {
        if (tipoDocumento == null) {
            throw new IllegalArgumentException("Tipo do documento é obrigatório");
        }

        this.id = UUID.randomUUID();
        this.tipoDocumento = tipoDocumento;
        this.statusDocumento = StatusDocumento.RASCUNHO;
        this.dataCriacao = LocalDateTime.now();
        this.documentoOrigem = documentoOrigem;
        this.itensDocumento = new ArrayList<>();
    }

    public void adicionarItem(ItemDocumento item) {
        if (item == null) {
            throw new IllegalArgumentException("Item do documento não pode ser nulo");
        }

        if (this.statusDocumento != StatusDocumento.RASCUNHO) {
            throw new IllegalStateException(
                    "Não é permitido adicionar itens em documento que não esteja em RASCUNHO");
        }

        this.itensDocumento.add(item);
    }

    public void fechar() {
        if (this.statusDocumento != StatusDocumento.RASCUNHO) {
            throw new IllegalStateException(
                    "Documento só pode ser fechado se estiver em RASCUNHO");
        }

        validarParaFechamento();

        this.statusDocumento = StatusDocumento.FECHADO;
    }

    public void cancelar() {
        if (this.statusDocumento == StatusDocumento.CANCELADO) {
            throw new IllegalStateException("Documento já está cancelado");
        }
        if (this.statusDocumento == StatusDocumento.FECHADO) {
            throw new IllegalStateException(
                    "Documento fechado não pode ser cancelado");
        }
        this.statusDocumento = StatusDocumento.CANCELADO;
    }

    private void validarParaFechamento() {
        if (itensDocumento.isEmpty()) {
            throw new IllegalStateException(
                    "Documento não pode ser fechado sem itens");
        }
    }

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

    public List<ItemDocumento> getItens() {
        return Collections.unmodifiableList(itensDocumento);
    }

    public List<ItemDocumento> getItensDocumento() {
        return Collections.unmodifiableList(itensDocumento);
    }
}
