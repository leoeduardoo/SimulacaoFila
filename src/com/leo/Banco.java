package com.leo;

import com.itextpdf.text.DocumentException;
import com.leo.Util.ConversorPdf;
import com.leo.Util.ManipuladorArquivo;
import com.leo.Util.Simulador;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel pela regra de negocio do banco
 *
 * @author Leo Eduardo da Silva
 */
public class Banco {

    // **************************************************
    // Atributos
    // **************************************************

    /**
     * Controla tempo de abertura do banco (duracao de 30 minutos ou 1800 segundos)
     * Ao alcançar o tempo limite o banco fecha e abre, ou seja, uma nova fila eh iniciada
     */
    static double tempo = 0;

    /**
     * O banco nada mais é que uma lista de filas
     */
    public static List<Fila> banco = new ArrayList<>();

    // **************************************************
    // Start da aplicacao
    // **************************************************

    public static void main(String[] args) throws DocumentException, IOException, URISyntaxException {

        ManipuladorArquivo.criaArquivoTabelaResultado();
        Simulador.registraQuantSementes();

        //Controla a posicao do cliente na fila
        for (int posicaoFila = 0; posicaoFila < Simulador.quantSementes; posicaoFila++) {

            Simulador.geraSemente(10000, 99999);
            ManipuladorArquivo.escreveArquivoSemente();

            //Cria a fila
            Fila fila = new Fila();

            //Adiciona a fila na lista de filas do banco
            banco.add(fila);

            ManipuladorArquivo.escreveArquivoCabecalho();

            //Posicao do cliente na fila
            int posicaoCliente = 0;

            while (tempo < 18000) {

                Cliente cliente = new Cliente();
                cliente.setPosicao(posicaoCliente + 1);

                double TEC = Simulador.calculaTempoEntreChegada();
                cliente.setTEC(TEC);

                double tempoChegadaRelogioAnterior = Simulador.retornaTempoChegadaRelogioClienteAnterior(posicaoFila, posicaoCliente);
                double tempoChegadaRelogio = TEC + tempoChegadaRelogioAnterior;
                cliente.setTempoChegadaRelogio(tempoChegadaRelogio);

                tempo += tempoChegadaRelogio;

                double TS = Simulador.geraTempoServico();
                cliente.setTS(TS);

                double tempoFila = 0;
                double tempoInicioServico = tempoChegadaRelogio;
                double tempoFimServicoClienteAnterior = 0;

                //Se ja existir clientes, deve analisar os dados do anterior
                if (posicaoCliente > 0) {

                    double tempoSistemaClienteAnterior = Simulador.retornaTempoSistemaClienteAnterior(posicaoFila, posicaoCliente);
                    double tempoSistemaClienteAnteriorMenosTec = tempoSistemaClienteAnterior - TEC;
                    tempoFila = (tempoSistemaClienteAnteriorMenosTec > 0 ? tempoSistemaClienteAnteriorMenosTec : 0);

                    tempoFimServicoClienteAnterior = Simulador.retornaTempoFimServicoClienteAnterior(posicaoFila, posicaoCliente);

                    //Se o cliente atual chegou na fila antes do anterior, o atual deve esperar o anterior ser atendido
                    if (tempoFimServicoClienteAnterior > tempoChegadaRelogio) {
                        tempoInicioServico = tempoFimServicoClienteAnterior;
                        tempoFila = tempoInicioServico - tempoChegadaRelogio;
                    }
                }

                cliente.setTempoFila(tempoFila);
                cliente.setTempoInicioServico(tempoInicioServico);

                double tempoSistema = Simulador.calculaTempoSistema(TS, tempoFila);
                cliente.setTempoSistema(tempoSistema);

                double tempoFimServico = tempoInicioServico + TS;
                cliente.setTempoFimServico(tempoFimServico);

                double tempoLivreCaixa = 0;
                if ((tempoChegadaRelogio - tempoFimServicoClienteAnterior) > 0) {
                    tempoLivreCaixa = tempoChegadaRelogio - tempoFimServicoClienteAnterior;
                }

                cliente.setTempoLivreCaixa(tempoLivreCaixa);

                //Adiciona o cliente na fila
                fila.getClientes().add(cliente);

                ManipuladorArquivo.escreveArquivoDadosCliente(posicaoCliente, TEC, tempoChegadaRelogio, TS, tempoFila, tempoInicioServico, tempoSistema, tempoFimServico, tempoLivreCaixa);

                //O cliente atual terminou de ser atendido e um proximo deve chegar
                posicaoCliente++;

            }

            //Calcula tempo medio da fila
            Simulador.calculaRegistraTemposMedios(posicaoFila, fila);

            //Banco fecha e uma nova fila se inicia
            tempo = 0;
        }

        ManipuladorArquivo.escreveArquivoIntervaloConfiancaMediaTempoMedioLivreCaixa();
        ManipuladorArquivo.escreveArquivoIntervaloConfiancaMediaTempoServico();
        ManipuladorArquivo.escreveArquivoIntervaloConfiancaMediaTempoFila();
        ManipuladorArquivo.escreveArquivoIntervaloConfiancaMediaTempoSistema();
        ManipuladorArquivo.escreveLegenda();

        //Finaliza a execucao da aplicacao
        ManipuladorArquivo.salvaArquivo();

        //Converte o .txt para .pdf
        ConversorPdf.conversor();

    }

}