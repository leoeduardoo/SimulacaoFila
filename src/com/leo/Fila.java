package com.leo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel por armazenar clientes e tempos medios da fila
 *
 * @author Leo Eduardo da Silva
 */
public class Fila {

    // **************************************************
    // Atributos
    // **************************************************

    /**
     * Lista de clientes
     */
    List<Cliente> clientes = new ArrayList<>();

    /**
     * Tempo Medio de Espera na Fila
     */
    double tempoMedioEsperaFila;

    /**
     * Tempo Medio de Servico
     */
    double tempoMedioServico;

    /**
     * Tempo Medio Despendido no Sistema
     */
    double tempoMedioDespendidoSistema;

    /**
     * Tempo Medio Livre do Caixa
     */
    double tempoMedioLivreCaixa;

    // **************************************************
    // Metodos
    // **************************************************

    public void setTempoMedioEsperaFila(double tempoMedioEsperaFila) {
        this.tempoMedioEsperaFila = tempoMedioEsperaFila;
    }

    public void setTempoMedioServico(double tempoMedioServico) {
        this.tempoMedioServico = tempoMedioServico;
    }

    public void setTempoMedioDespendidoSistema(double tempoMedioDespendidoSistema) {
        this.tempoMedioDespendidoSistema = tempoMedioDespendidoSistema;
    }

    public double getTempoMedioLivreCaixa() {
        return tempoMedioLivreCaixa;
    }

    public void setTempoMedioLivreCaixa(double tempoMediaLivreCaixa) {
        this.tempoMedioLivreCaixa = tempoMediaLivreCaixa;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public double getTempoMedioEsperaFila() {
        return tempoMedioEsperaFila;
    }

    public double getTempoMedioServico() {
        return tempoMedioServico;
    }

    public double getTempoMedioDespendidoSistema() {
        return tempoMedioDespendidoSistema;
    }
}
