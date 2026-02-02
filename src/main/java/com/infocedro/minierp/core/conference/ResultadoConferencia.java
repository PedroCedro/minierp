package com.infocedro.minierp.core.conference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoConferencia {

    private final StatusConferencia status;
    private final List<String> divergencias;

    public ResultadoConferencia(StatusConferencia status, List<String> divergencias) {
        this.status = status;
        this.divergencias = new ArrayList<>(divergencias);
    }

    public StatusConferencia getStatus() {
        return status;
    }

    public List<String> getDivergencias() {
        return Collections.unmodifiableList(divergencias);
    }

    public boolean possuiDivergencias() {
        return !divergencias.isEmpty();
    }
}
