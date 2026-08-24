package br.com.exemplo.asic.pdk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Inicializando Gerador PDK 10um");

        // Utilizando a interface comum CelulaAsic
        List<CelulaAsic> celulas = List.of(
            new NandCell10um(),
            new NotCell10um(),
            new NorCell10um(),
            new AndCell10um()
        );

        String diretorioDestino = "C:\\pdk";
        try {
            Path dirPath = Paths.get(diretorioDestino);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
                System.out.println("\nDiretório criado com sucesso: " + diretorioDestino);
            }

            for (CelulaAsic celula : celulas) {
                String nome = celula.getNome();
                String netlistSpice = celula.gerarNetlistCompleta();
                List<GeometriaCamada> layoutCamadas = celula.gerarLayoutFisico();

                System.out.println("\n--- [PROCESSANDO CÉLULA: " + nome.toUpperCase() + "] ---");

                Path arquivoSpice = dirPath.resolve(nome + ".cir");
                Files.writeString(arquivoSpice, netlistSpice);
                System.out.println("Arquivo SPICE salvo em: " + arquivoSpice.toAbsolutePath());

                Path arquivoCif = dirPath.resolve(nome + ".cif");
                CifExporter.exportarParaCif(nome, layoutCamadas, arquivoCif);
                System.out.println("Arquivo de Layout CIF salvo em: " + arquivoCif.toAbsolutePath());

                Path arquivoGds = dirPath.resolve(nome + ".gds");
                GdsExporter.exportarParaGds(nome, layoutCamadas, arquivoGds);
                System.out.println("Arquivo GDSII salvo em: " + arquivoGds.toAbsolutePath());
            }

            System.out.println("\nProcesso concluído com sucesso para todas as portas! Pronto para simulação no NGSPICE e visualização no KLayout.");

        } catch (IOException e) {
            System.err.println("Erro ao salvar os arquivos do PDK: " + e.getMessage());
        }
    }
}