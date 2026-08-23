package br.com.exemplo.asic.pdk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("Inicializando Gerador PDK 10um");

        NandCell10um nandCell = new NandCell10um();

        String netlistSpice = nandCell.gerarNetlistCompleta();
        var layoutCamadas = nandCell.gerarLayoutFisico();

        System.out.println("\n--- [NETLIST SPICE GERADA] ---");
        System.out.println(netlistSpice);

        System.out.println("\n--- [GEOMETRIAS DE LAYOUT (CIF) GERADAS] ---");
        layoutCamadas.forEach(camada -> 
            System.out.printf("Camada: %-8s | Caixa: [%d, %d, %d, %d] | CIF: %s%n",
                camada.camada(), 
                camada.x1(), camada.y1(), camada.x2(), camada.y2(), 
                camada.paraCif()
            )
        );

        String diretorioDestino = "C:\\pdk";
        try {
            Path dirPath = Paths.get(diretorioDestino);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
                System.out.println("\nDiretório criado com sucesso: " + diretorioDestino);
            }

            Path arquivoSpice = dirPath.resolve("nand2_10um.cir");
            Files.writeString(arquivoSpice, netlistSpice);
            System.out.println("\nArquivo SPICE salvo em: " + arquivoSpice.toAbsolutePath());

            Path arquivoLayout = dirPath.resolve("nand2_10um.cif");
            StringBuilder sbLayout = new StringBuilder();
            layoutCamadas.forEach(c -> sbLayout.append(c.camada()).append(" ").append(c.paraCif()).append("\n"));
            Files.writeString(arquivoLayout, sbLayout.toString());
            System.out.println("Arquivo de Layout salvo em: " + arquivoLayout.toAbsolutePath());

            Path arquivoGds = dirPath.resolve("nand2_10um.gds");
            GdsExporter.exportarParaGds("nand2_10um", layoutCamadas, arquivoGds);
            System.out.println("Arquivo GDSII salvo em: " + arquivoGds.toAbsolutePath());

            System.out.println("\nProcesso concluído com sucesso! Pronto para simulação no NGSPICE.");

        } catch (IOException e) {
            System.err.println("Erro ao salvar os arquivos do PDK: " + e.getMessage());
        }
    }
}