package com.leo.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Classe responsavel por manipular o arquivo de saida e escritura dos dados
 *
 * @author Leo Eduardo da Silva
 */
public class ManipuladorArquivo {

    // **************************************************
    // Atributos
    // **************************************************

    /**
     * Flag que controla se deve escrever a semente no arquivo txt
     */
    private static boolean escreveSemente = true;

    /**
     * Flag que controla se deve printar no console
     */
    private static boolean printaConsole = false;

    /**
     * Responsavel por escrever no arquivo de saida
     */
    public static FileWriter escritor;
    /**
     * Responsavel por escrever com formatacao no arquivo de saida
     */
    public static PrintWriter printer;

    // **************************************************
    // Metodos
    // **************************************************

    /**
     * Indica que a escrita no arquivo de saida finalizou e salva os dados
     *
     * @return void
     */
    public static void salvaArquivo() {
        try {
            printer.close();
            escritor.close();
        } catch (IOException e) {
            if (printaConsole) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Escreve o valor da semente no arquivo de saida
     *
     * @return void
     */
    public static void escreveArquivoSemente() {
        if (escreveSemente) {
            printer.println("\nSemente = " + Simulador.semente);
        }
    }

    /**
     * Escreve o Intervalo de Confianca da Media do Tempo Medio Livre do Caixa no arquivo de saida
     *
     * @return void
     */
    public static void escreveArquivoIntervaloConfiancaMediaTempoMedioLivreCaixa() {

        printer.printf("\nIntervalo de confianca da media do tempo media livre no caixa = [" +
                (

                        Simulador.calculaMediaTempoMedioLivreCaixa() - (1.96) * (

                                Simulador.calculaDesvioPadraoTempoMedioLivreCaixa() / (Math.sqrt(Simulador.sementes.size()))))
                + ", " +

                Simulador.calculaMediaTempoMedioLivreCaixa() + (1.96) * (

                Simulador.calculaDesvioPadraoTempoMedioLivreCaixa() / (Math.sqrt(Simulador.sementes.size()))) + "]");
    }

    /**
     * Escreve o Intervalo de Confianca da Media do Tempo de Servico no arquivo de saida
     *
     * @return void
     */
    public static void escreveArquivoIntervaloConfiancaMediaTempoServico() {

        printer.printf("\nIntervalo de confianca da media do tempo de servico = [" +
                (

                        Simulador.calculaMediaTempoServico() - (1.96) * (

                                Simulador.calculaDesvioPadraoTempoServico() / (Math.sqrt(Simulador.sementes.size()))))
                + ", " +

                Simulador.calculaMediaTempoServico() + (1.96) * (

                Simulador.calculaDesvioPadraoTempoServico() / (Math.sqrt(Simulador.sementes.size()))) + "]");
    }

    /**
     * Escreve o Intervalo de Confianca da Media do Tempo na Fila no arquivo de saida
     *
     * @return void
     */
    public static void escreveArquivoIntervaloConfiancaMediaTempoFila() {

        printer.printf("\nIntervalo de confianca da media do tempo na fila = [" +
                (

                        Simulador.calculaMediaTempoFila() - (1.96) * (

                                Simulador.calculaDesvioPadraoTempoFila() / (Math.sqrt(Simulador.sementes.size()))))
                + ", " +

                Simulador.calculaMediaTempoFila() + (1.96) * (

                Simulador.calculaDesvioPadraoTempoFila() / (Math.sqrt(Simulador.sementes.size()))) + "]");
    }

    /**
     * Escreve o Intervalo de Confianca da Media do Tempo na Fila no arquivo de saida
     *
     * @return void
     */
    public static void escreveArquivoIntervaloConfiancaMediaTempoSistema() {

        printer.printf("\nIntervalo de confianca da media do tempo no sistema = [" +
                (

                        Simulador.calculaMediaTempoSistema() - (1.96) * (

                                Simulador.calculaDesvioPadraoTempoSistema() / (Math.sqrt(Simulador.sementes.size()))))
                + ", " +

                Simulador.calculaMediaTempoSistema() + (1.96) * (

                Simulador.calculaDesvioPadraoTempoSistema() / (Math.sqrt(Simulador.sementes.size()))) + "]");
    }

    /**
     * Cria o arquivo de saida (tabela de resultados)
     *
     * @return void
     */
    public static void criaArquivoTabelaResultado() {

        File resultadoTXT = new File("Tabela.txt");

        deletaArquivoExistente(resultadoTXT);

        try {
            resultadoTXT = new File("Tabela.txt");
            if (printaConsole) {
                if (resultadoTXT.createNewFile()) {
                    System.out.println("Arquivo " + resultadoTXT.getName() + " criado.");
                } else {
                    System.out.println("Arquivo " + resultadoTXT.getName() + " ja existe.");
                }
            }
        } catch (IOException e) {
            if (printaConsole) {
                System.out.println("Erro ao criar o arquivo.");
                e.printStackTrace();
            }
        }

        criaEscritorPrinter(resultadoTXT);

    }

    /**
     * Verifica se ja existe um arquivo .txt e deleta
     *
     * @return void
     */
    public static void deletaArquivoExistente(File resultadoTXT) {
        if (resultadoTXT.exists() && !resultadoTXT.isDirectory()) {
            resultadoTXT.delete();
        }
    }

    /**
     * Cria o escritor e printer responsaveis por manipular o arquivo de saida
     *
     * @return void
     */
    private static void criaEscritorPrinter(File resultado) {
        try {
            escritor = new FileWriter(resultado.getName());
            printer = new PrintWriter(escritor);
        } catch (IOException e) {
            if (printaConsole) {
                System.out.println("Erro na criacao do escritor/printer.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Escreve uma linha com os dados do cliente na tabela do arquivo de saida
     *
     * @return void
     */
    public static void escreveArquivoDadosCliente(int i, double TEC, double tempoChegadaRelogio, double TS, double tempoFila, double tempoInicioServico, double tempoSistema, double tempoFimServico, double tempoLivreCaixa) {
        printer.printf("%-10d%-20.0f%-20.4f%-20.4f%-20.4f%-20.4f%-20.4f%-20.4f%-20.4f\n", i + 1, TEC, TS, tempoChegadaRelogio, tempoInicioServico, tempoFimServico, tempoFila, tempoSistema, tempoLivreCaixa);
    }

    /**
     * Escreve o cabeçalho da tabela do arquivo de saida
     *
     * @return void
     */
    public static void escreveArquivoCabecalho() {
        printer.printf("\n%-10s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Cliente", "T.E.C.", "T.S.", "T.C.R.", "T.I.S.", "T.F.S.", "T.F.", "T.L.S.", "T.L.C.");
    }

    /**
     * Escreve a legenda do cabecalho no arquivo de saida
     *
     * @return void
     */
    public static void escreveLegenda() {
        printer.printf("\nT.E.C. = Tempo Entre Chegada\n");
        printer.printf("\nT.S. = Tempo de Servico\n");
        printer.printf("\nT.C.R. = Tempo de Chegada no Relogio/Tempo Real\n");
        printer.printf("\nT.I.S. = Tempo de Inicio de Servico\n");
        printer.printf("\nT.F.S. = Tempo de Fim de Servico\n");
        printer.printf("\nT.F. = Tempo na Fila\n");
        printer.printf("\nT.L.S. = Tempo Livre do Sistema\n");
        printer.printf("\nT.L.C. = Tempo Livre do Caixa\n");
    }

}