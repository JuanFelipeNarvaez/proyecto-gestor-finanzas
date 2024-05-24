package com.corhuila.proyectogestorfinanzas.Document;

import java.util.List;

public class SumaResponse {
    private List<Finanza> finanzas;
    private Integer sumTotal;

    public SumaResponse(List<Finanza> finanzas, Integer sumTotal) {
        this.finanzas = finanzas;
        this.sumTotal = sumTotal;
    }

    public List<Finanza> getFinanzas() {
        return finanzas;
    }

    public void setFinanzas(List<Finanza> finanzas) {
        this.finanzas = finanzas;
    }

    public Integer getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(Integer sumTotal) {
        this.sumTotal = sumTotal;
    }
}
