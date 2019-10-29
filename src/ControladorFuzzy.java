import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import org.antlr.runtime.RecognitionException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Scanner;

public class ControladorFuzzy {

    public static void main(String[] args) throws URISyntaxException, RecognitionException, IOException {

        Scanner scan = new Scanner(System.in);

        do {
            Entradas entradas = leitura();

            File arquivoFis = new File(ControladorFuzzy.class.getResource("ControladorFuzzyConfig.flc").toURI());

            String conteudoArquivoFis = new String(Files.readAllBytes(arquivoFis.toPath()));
            FIS fis = FIS.createFromString(conteudoArquivoFis, true);

            fis.setVariable("velocidadeDoVento", entradas.velocidadeDoVento);
            fis.setVariable("umidadeDoAr", entradas.umidadeDoAr);
            fis.setVariable("periodoDoAno", entradas.periodoDoAno);
            fis.setVariable("pressaoAtmosferica", entradas.pressaoAtmosferica);

            fis.evaluate();

            Variable resultado = fis.getVariable("indicePluviometrico");

            System.out.println(resultado);
        } while (true);
    }

    public static Entradas leitura() {

        Scanner scan = new Scanner(System.in);

        Entradas entradas = new Entradas();

        System.out.println("Informe a velocidade do vento (m/s):");
        entradas.velocidadeDoVento = scan.nextDouble();

        System.out.println("Informe a umidade do ar (%):");
        entradas.umidadeDoAr = scan.nextDouble();

        System.out.println("Informe o mes (1 à 12):");
        entradas.periodoDoAno = scan.nextInt();

        System.out.println("Informe a pressao atmosferica(miliBar):");
        entradas.pressaoAtmosferica = scan.nextDouble();

//        entradas.velocidadeDoVento = 0;
//        entradas.umidadeDoAr = 0;
//        entradas.periodoDoAno = 9;
//        entradas.pressaoAtmosferica = 0;

        return entradas;
    }
}

class Entradas {
    double velocidadeDoVento = 0;
    double umidadeDoAr = 0;
    int periodoDoAno = 0;
    double pressaoAtmosferica = 0;
}
