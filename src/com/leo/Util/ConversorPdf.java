package com.leo.Util;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * Classe responsavel por converter a tabela .txt para .pdf
 *
 * @author Leo Eduardo da Silva
 */
public class ConversorPdf {

    // **************************************************
    // Atributos
    // **************************************************

    /**
     * Flag que controla se deve printar no console
     */
    private static boolean printaConsole = false;

    // **************************************************
    // Metodos
    // **************************************************

    /**
     * Realiza a conversao da tabela
     *
     * @return void
     */
    public static void conversor() {

        File resultadoCSV = new File("Tabela.pdf");

        deletaArquivoExistente(resultadoCSV);

        try {
            FileReader reader = new FileReader("Tabela.txt");
            BufferedReader leitor = new BufferedReader(reader);

            //Criacao do pdf
            Document document = new Document(PageSize.A2, 3, 3, 3, 3);
            PdfWriter.getInstance(document, new FileOutputStream("Tabela.pdf"));
            document.open();

            //Le o txt linha a linha
            while (leitor.ready()) {
                document.add(new Paragraph(leitor.readLine(), FontFactory.getFont(FontFactory.COURIER)));
            }

            leitor.close();
            document.close();
        } catch (Exception e) {
            if (printaConsole) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Verifica se ja existe um arquivo .pdf e deleta
     *
     * @return void
     */
    public static void deletaArquivoExistente(File resultadoPDF) {
        if (resultadoPDF.exists() && !resultadoPDF.isDirectory()) {
            resultadoPDF.delete();
        }
    }
}