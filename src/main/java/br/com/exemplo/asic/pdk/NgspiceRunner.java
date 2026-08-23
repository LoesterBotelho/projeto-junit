package br.com.exemplo.asic.pdk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class NgspiceRunner {

    public static void executarSimulacao(Path caminhoCir) {
        try {
            // Comando para rodar o NGSPICE em modo batch (-b) com o arquivo de netlist
            ProcessBuilder pb = new ProcessBuilder("ngspice", "-b", caminhoCir.toAbsolutePath().toString());
            pb.directory(caminhoCir.getParent().toFile());
            pb.redirectErrorStream(true);

            Process process = pb.start();

            // Lê o log de saída da simulação em tempo real
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String linha;
                System.out.println("\n--- [INÍCIO DA SIMULAÇÃO NGSPICE] ---");
                while ((linha = reader.readLine()) != null) {
                    System.out.println(linha);
                }
                System.out.println("--- [FIM DA SIMULAÇÃO NGSPICE] ---");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Simulação executada com sucesso pelo NGSPICE!");
            } else {
                System.err.println("NGSPICE encerrou com código de erro: " + exitCode);
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar o NGSPICE. Verifique se ele está instalado e no PATH do sistema: " + e.getMessage());
        }
    }
}