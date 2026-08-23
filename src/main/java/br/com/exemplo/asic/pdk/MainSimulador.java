package br.com.exemplo.asic.pdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainSimulador {

    public static void main(String[] args) {
        System.out.println("=== [INICIALIZANDO SIMULADOR NGSPICE PARA JAVA] ===");

        // 1. Instancia a célula do PDK e gera a Netlist SPICE completa
        NandCell10um nandCell = new NandCell10um();
        String netlistSpice = nandCell.gerarNetlistCompleta();

        System.out.println("\n--- [NETLIST SPICE GERADA] ---");
        System.out.println(netlistSpice);

        String diretorioDestino = "C:\\pdk";
        try {
            Path dirPath = Paths.get(diretorioDestino);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // 2. Salva a netlist em um arquivo .cir
            Path arquivoSpice = dirPath.resolve("nand2_10um.cir");
            Files.writeString(arquivoSpice, netlistSpice);
            System.out.println("\n[OK] Arquivo SPICE salvo em: " + arquivoSpice.toAbsolutePath());

            // 3. Executa o NGSPICE via ProcessBuilder em modo batch (-b)
            executarNgspice(arquivoSpice);

        } catch (IOException e) {
            System.err.println("Erro ao manipular arquivos da simulação: " + e.getMessage());
        }
    }

    private static void executarNgspice(Path caminhoCir) {
        try {
            System.out.println("\n--- [DISPARANDO NGSPICE EM MODO BATCH] ---");
            
            // Certifique-se de que o executável 'ngspice' está no PATH do Windows ou aponte o caminho completo (ex: "C:\\Spice64\\bin\\ngspice.exe")
            //ProcessBuilder pb = new ProcessBuilder("ngspice", "-b", caminhoCir.getFileName().toString());
            ProcessBuilder pb = new ProcessBuilder("ngspice", "-b", "-r", "nand2_10um.raw", caminhoCir.getFileName().toString());
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
                System.out.println("\n[SUCESSO] Simulação concluída pelo NGSPICE!");
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