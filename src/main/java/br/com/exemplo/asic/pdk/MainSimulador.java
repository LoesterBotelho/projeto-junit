package br.com.exemplo.asic.pdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainSimulador {

    public static void main(String[] args) {
        System.out.println("   INICIALIZANDO SIMULADOR NGSPICE PARA JAVA");

        // Lista de células do PDK utilizando a interface comum
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
            }

            for (CelulaAsic celula : celulas) {
                String nome = celula.getNome();
                String netlistSpice = celula.gerarNetlistCompleta();

                System.out.println("\n--- [PROCESSANDO CÉLULA: " + nome.toUpperCase() + "] ---");
                System.out.println(netlistSpice);

                // 2. Salva a netlist em um arquivo .cir específico para cada célula
                Path arquivoSpice = dirPath.resolve(nome + ".cir");
                Files.writeString(arquivoSpice, netlistSpice);
                System.out.println("\n[OK] Arquivo SPICE salvo em: " + arquivoSpice.toAbsolutePath());

                // 3. Executa o NGSPICE via ProcessBuilder em modo batch para a respectiva célula
                executarNgspice(arquivoSpice, nome);
            }

            System.out.println("\n=== [TODAS AS SIMULAÇÕES FORAM PROCESSADAS] ===");

        } catch (IOException e) {
            System.err.println("Erro ao manipular arquivos da simulação: " + e.getMessage());
        }
    }

    private static void executarNgspice(Path caminhoCir, String nomeCelulas) {
        try {
            System.out.println("\n--- [DISPARANDO NGSPICE PARA: " + nomeCelulas + "] ---");
            
            String arquivoRaw = nomeCelulas + ".raw";
            ProcessBuilder pb = new ProcessBuilder("ngspice", "-b", "-r", arquivoRaw, caminhoCir.getFileName().toString());
            pb.directory(caminhoCir.getParent().toFile());
            pb.redirectErrorStream(true);

            Process process = pb.start();

            // Captura e exibe a saída do simulador linha por linha
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    System.out.println("NGSPICE > " + linha);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("\n[SUCESSO] Simulação de " + nomeCelulas + " concluída pelo NGSPICE!");
            } else {
                System.err.println("\n[AVISO] NGSPICE finalizou com código de saída: " + exitCode);
            }

        } catch (IOException e) {
            System.err.println("[ERRO] Falha ao iniciar o processo do NGSPICE. Verifique se ele está instalado e configurado no PATH: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("[ERRO] A execução da simulação foi interrompida.");
            Thread.currentThread().interrupt();
        }
    }
}