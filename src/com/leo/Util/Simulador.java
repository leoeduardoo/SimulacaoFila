package com.leo.Util;

import com.leo.Banco;
import com.leo.Fila;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.floor;
import static java.lang.Math.pow;

/**
 * Classe responsavel por gerar valores,
 * realizar calculos e manipular arquivos
 *
 * @author Leo Eduardo da Silva
 */
public class Simulador {

    // **************************************************
    // Atributos
    // **************************************************

    /**
     * Modulo
     */
    public static double modulo = (pow(2.0, 31.0) - 1);

    /**
     * Multiplicador
     */
    public static double multiplicador = 16807.0;

    /**
     * Responsavel pelo input de dados
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Quantidade de sementes
     */
    public static int quantSementes = 0;

    /**
     * Lista de numeros semente
     */
    public static List<Double> sementes = new ArrayList<>();

    /**
     * Numero semente
     */
    public static double semente = 0;

    // **************************************************
    // Metodos
    // **************************************************

    /**
     * Registra a quantidade de sementes
     *
     * @return void
     */
    public static void registraQuantSementes() {
        System.out.println("Informe a quantidade de sementes: ");
        quantSementes = Simulador.scanner.nextInt();
    }

    /**
     * Gera uma semente de forma aleatoria entre min e max
     *
     * @param min Valor minimo
     * @param max Valor maximo
     * @return void
     */
    public static void geraSemente(int min, int max) {
        SecureRandom secureRandom = new SecureRandom();
        Simulador.semente = min + secureRandom.nextInt((max - min) + 1);
        Simulador.sementes.add(Simulador.semente);
    }

    /**
     * Calcula a Media do Tempo Medio Livre do Caixa (de todas as filas do banco)
     *
     * @return double
     */
    static double calculaMediaTempoMedioLivreCaixa() {

        Double somatorio = Double.valueOf(0);
        for (Fila fila : Banco.banco) {
            somatorio += fila.getTempoMedioLivreCaixa();
        }

        return (somatorio / quantSementes);
    }

    /**
     * Calcula a Media do Tempo Medio de Servico (de todas as filas do banco)
     *
     * @return double
     */
    static double calculaMediaTempoServico() {

        Double somatorio = Double.valueOf(0);
        for (Fila fila : Banco.banco) {
            somatorio += fila.getTempoMedioServico();
        }

        return (somatorio / quantSementes);
    }

    /**
     * Calcula a Media do Tempo Medio na Fila (de todas as filas do banco)
     *
     * @return double
     */
    static double calculaMediaTempoFila() {

        Double somatorio = Double.valueOf(0);
        for (Fila fila : Banco.banco) {
            somatorio += fila.getTempoMedioEsperaFila();
        }

        return (somatorio / quantSementes);
    }

    /**
     * Calcula a Media do Tempo Medio no Sistema (de todas as filas do banco)
     *
     * @return double
     */
    static double calculaMediaTempoSistema() {

        Double somatorio = Double.valueOf(0);
        for (Fila fila : Banco.banco) {
            somatorio += fila.getTempoMedioDespendidoSistema();
        }

        return (somatorio / quantSementes);
    }

    /**
     * Calcula o Desvio Padrao do Tempo Medio Livre do Caixa (de todas as filas do banco)
     *
     * @return double
     */
    static double calculaDesvioPadraoTempoMedioLivreCaixa() {

        double media = calculaMediaTempoMedioLivreCaixa();
        double variacoes = 0;

        for (Fila fila : Banco.banco) {
            double v = (fila.getTempoMedioLivreCaixa()) - media;
            variacoes += v * v;
        }


        return (Math.sqrt(variacoes / quantSementes));
    }

    /**
     * Calcula o Desvio Padrao do Tempo Medio de Servico (de todas as filas do banco)
     *
     * @return double
     */
    static double calculaDesvioPadraoTempoServico() {

        double media = calculaMediaTempoServico();
        double variacoes = 0;

        for (Fila fila : Banco.banco) {
            double v = (fila.getTempoMedioServico()) - media;
            variacoes += v * v;
        }


        return (Math.sqrt(variacoes / quantSementes));
    }

    /**
     * Calcula o Desvio Padrao do Tempo Medio na Fila (de todas as filas do banco)
     *
     * @return double
     */
    static double calculaDesvioPadraoTempoFila() {

        double media = calculaMediaTempoFila();
        double variacoes = 0;

        for (Fila fila : Banco.banco) {
            double v = (fila.getTempoMedioEsperaFila()) - media;
            variacoes += v * v;
        }


        return (Math.sqrt(variacoes / quantSementes));
    }

    /**
     * Calcula o Desvio Padrao do Tempo Medio no Sistema (de todas as filas do banco)
     *
     * @return double
     */
    static double calculaDesvioPadraoTempoSistema() {

        double media = calculaMediaTempoSistema();
        double variacoes = 0;

        for (Fila fila : Banco.banco) {
            double v = (fila.getTempoMedioDespendidoSistema()) - media;
            variacoes += v * v;
        }


        return (Math.sqrt(variacoes / quantSementes));
    }

    /**
     * Calcula o Tempo Medio Livre do Caixa na fila
     *
     * @param posicaoFila Posicao da fila na lista de filas no banco
     * @return double
     */
    public static double calculaTempoMedioLivreCaixa(int posicaoFila) {
        return Banco.banco.get(posicaoFila).getClientes()
                .stream()
                .mapToDouble(cliente -> cliente.getTempoLivreCaixa())
                .sum() / Banco.banco.get(posicaoFila).getClientes().size();
    }

    /**
     * Calcula o Tempo Medio de Espera na fila
     *
     * @param posicaoFila Posicao da fila na lista de filas no banco
     * @return double
     */
    public static double calculaTempoMedioEspera(int posicaoFila) {
        return Banco.banco.get(posicaoFila).getClientes()
                .stream()
                .mapToDouble(cliente -> cliente.getTempoFila())
                .sum() / Banco.banco.get(posicaoFila).getClientes().size();
    }

    /**
     * Calcula o Tempo Medio de Servico na fila
     *
     * @param posicaoFila Posicao da fila na lista de filas no banco
     * @return double
     */
    public static double calculaTempoMedioServico(int posicaoFila) {
        return Banco.banco.get(posicaoFila).getClientes()
                .stream()
                .mapToDouble(cliente -> cliente.getTS())
                .sum() / Banco.banco.get(posicaoFila).getClientes().size();
    }

    /**
     * Calcula o Tempo Medio Despendido do Sistema na fila
     *
     * @param posicaoFila Posicao da fila na lista de filas no banco
     * @return double
     */
    public static double calculaTempoMedioDespendidoSistema(int posicaoFila) {
        return Banco.banco.get(posicaoFila).getClientes()
                .stream()
                .mapToDouble(cliente -> cliente.getTempoSistema())
                .sum() / Banco.banco.get(posicaoFila).getClientes().size();
    }

    /**
     * Retorna o Tempo de Fim de Servico do cliente anterior anterior na fila
     *
     * @param posicaoFila    Posicao da fila na lista de filas no banco
     * @param posicaoCliente Posicao do cliente na fila
     * @return double
     */
    public static double retornaTempoFimServicoClienteAnterior(int posicaoFila, int posicaoCliente) {
        return Banco.banco.get(posicaoFila).getClientes().get(posicaoCliente - 1).getTempoFimServico();
    }

    /**
     * Retorna o Tempo no Sistema do cliente anterior na fila
     *
     * @param posicaoFila    Posicao da fila na lista de filas no banco
     * @param posicaoCliente Posicao do cliente na fila
     * @return double
     */
    public static double retornaTempoSistemaClienteAnterior(int posicaoFila, int posicaoCliente) {
        return Banco.banco.get(posicaoFila).getClientes().get(posicaoCliente - 1).getTempoSistema();
    }

    /**
     * Retorna o Tempo de Chegada no Relogio do cliente anterior na fila
     *
     * @param posicaoFila    Posicao da fila na lista de filas no banco
     * @param posicaoCliente Posicao do cliente na fila
     * @return double
     */
    public static double retornaTempoChegadaRelogioClienteAnterior(int posicaoFila, int posicaoCliente) {
        return posicaoCliente > 0 ? Banco.banco.get(posicaoFila).getClientes().get(posicaoCliente - 1).getTempoChegadaRelogio() : 0;
    }

    /**
     * Calcula o Tempo no Sistema
     *
     * @param tempoFila Tempo do cliente na fila
     * @param TS        Tempo de servico no cliente
     * @return double
     */
    public static double calculaTempoSistema(double tempoFila, double TS) {
        return tempoFila + TS;
    }

    /**
     * Gera numeros pseudo-aleatorios
     *
     * @return double
     */
    private static double geraNumeroPseudoAleatorio() {
        semente = multiplicador * semente;
        double f = floor(semente / modulo);
        semente = semente - (f * modulo);
        double pseudoAleatorio = semente / modulo;
        return pseudoAleatorio;
    }

    /**
     * Calcula o Tempo Entre Chegada (TEC)
     *
     * @return double
     */
    public static double calculaTempoEntreChegada() {
        Double x = geraNumeroPseudoAleatorio();
        return (-Math.log(x)) * 15;
    }

    /**
     * Calcula o Tempo de Servico (TS)
     *
     * @return double
     */
    public static double geraTempoServico() {
        Double x = geraNumeroPseudoAleatorio();

        if (x <= 0.6) {
            return (-Math.log(x) * 15) + 15;
        }

        if (x > 0.6 && x <= 0.9) {
            return (-Math.log(x) * 45) + 30;
        }

        return (-Math.log(x) * 180) + 60;
    }

    /**
     * Calcula e registra os Tempos Medios da fila
     *
     * @param posicaoFila Posicao da fila na lista de filas no banco
     * @param fila        Fila do banco
     * @return void
     */
    public static void calculaRegistraTemposMedios(int posicaoFila, Fila fila) {
        fila.setTempoMedioLivreCaixa(calculaTempoMedioLivreCaixa(posicaoFila));
        fila.setTempoMedioDespendidoSistema(calculaTempoMedioDespendidoSistema(posicaoFila));
        fila.setTempoMedioEsperaFila(calculaTempoMedioEspera(posicaoFila));
        fila.setTempoMedioServico(calculaTempoMedioServico(posicaoFila));
    }

}
