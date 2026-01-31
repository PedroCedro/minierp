package com.infocedro.minierp.core.conferencia;

import com.infocedro.minierp.core.document.Documento;
import com.infocedro.minierp.core.document.ItemDocumento;

import java.util.ArrayList;
import java.util.List;

public class ConferenciaDocumento {

    private final Documento documento;
    private final Documento documentoOrigem;

    public ConferenciaDocumento(Documento documento, Documento documentoOrigem) {
        this.documento = documento;
        this.documentoOrigem = documentoOrigem;
    }

    public ResultadoConferencia executar() {

        List<String> divergencias = new ArrayList<>();

        for (ItemDocumento itemDoc : documento.getItensDocumento()) {

            ItemDocumento itemOrigem = documentoOrigem.getItensDocumento()
                    .stream()
                    .filter(i -> i.getDescricao().equals(itemDoc.getDescricao()))
                    .findFirst()
                    .orElse(null);

            if (itemOrigem == null) {
                divergencias.add(
                        "Item não encontrado na origem: " + itemDoc.getDescricao()
                );
                continue;
            }

            if (itemDoc.getQuantidade() != itemOrigem.getQuantidade()) {
                divergencias.add(
                        "Quantidade divergente para item: " + itemDoc.getDescricao()
                );
            }
        }

        StatusConferencia status =
                divergencias.isEmpty()
                        ? StatusConferencia.OK
                        : StatusConferencia.DIVERGENTE;

        return new ResultadoConferencia(status, divergencias);
    }
}
