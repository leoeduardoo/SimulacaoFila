package com.leo;

/**
 * Classe responsavel por armazenar dados dos clientes
 *
 * @author Leo Eduardo da Silva
 */
public class Cliente {

    // **************************************************
    // Atributos
    // **************************************************

    /**
     * Posicao do cliente na fila
     */
    int posicao;

    /**
     * Tempo Entre Chegadas
     */
    double TEC;

    /**
     * Tempo de Servico
     */
    double TS;

    /**
     * Tempo Chegada no Relogio
     */
    double tempoChegadaRelogio;

    /**
     * Tempo na Fila
     */
    double tempoFila;

    /**
     * Tempo de Inicio de Servico
     */
    double tempoInicioServico;

    /**
     * Tempo no Sistema
     */
    double tempoSistema;

    /**
     * Tempo do Fim de Servico
     */
    double tempoFimServico;

    /**
     * Tempo Livre do Caixa
     */
    double tempoLivreCaixa;

    // **************************************************
    // Metodos
    // **************************************************

    public double getTempoLivreCaixa() {
        return tempoLivreCaixa;
    }

    public void setTempoLivreCaixa(double tempoLivreCaixa) {
        this.tempoLivreCaixa = tempoLivreCaixa;
    }

    public void setTEC(double TEC) {
        this.TEC = TEC;
    }

    public double getTS() {
        return TS;
    }

    public void setTS(double TS) {
        this.TS = TS;
    }

    public double getTempoFimServico() {
        return tempoFimServico;
    }

    public void setTempoFimServico(double tempoFimServico) {
        this.tempoFimServico = tempoFimServico;
    }

    public double getTempoSistema() {
        return tempoSistema;
    }

    public void setTempoSistema(double tempoSistema) {
        this.tempoSistema = tempoSistema;
    }

    public void setTempoInicioServico(double tempoInicioServico) {
        this.tempoInicioServico = tempoInicioServico;
    }

    public double getTempoFila() {
        return tempoFila;
    }

    public void setTempoFila(double tempoFila) {
        this.tempoFila = tempoFila;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public double getTempoChegadaRelogio() {
        return tempoChegadaRelogio;
    }

    public void setTempoChegadaRelogio(double tempoChegadaRelogio) {
        this.tempoChegadaRelogio = tempoChegadaRelogio;
    }

}
