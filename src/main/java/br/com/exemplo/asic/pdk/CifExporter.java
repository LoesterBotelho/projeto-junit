package br.com.exemplo.asic.pdk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class CifExporter {

    private CifExporter() {}

    public static void exportarParaCif(String nomeCelulas, List<GeometriaCamada> geometrias, Path caminhoArquivo) throws IOException {
        Objects.requireNonNull(nomeCelulas, "Nome da célula não pode ser nulo.");
        Objects.requireNonNull(geometrias, "Geometrias não podem ser nulas.");
        Objects.requireNonNull(caminhoArquivo, "Caminho do arquivo não pode ser nulo.");

        StringBuilder sb = new StringBuilder();
        
        // Cabeçalho do arquivo CIF
        sb.append("(CIF Gerado via Java para PDK 10um);\n");
        
        // Início da Definição da Célula (DS: Define Symbol 0, escala 1 1)
        sb.append("DS 0 1 1;\n");
        
        String camadaAtual = null;
        
        for (GeometriaCamada geom : geometrias) {
            String camadaGeom = geom.camada() != null ? geom.camada().toUpperCase() : "";
            
            // Se mudou de camada, insere o comando L (LAYER) do CIF
            if (!camadaGeom.equals(camadaAtual)) {
                camadaAtual = camadaGeom;
                sb.append("L ").append(camadaAtual).append(";\n");
            }
            
            // Cada geometria gera uma BOX no formato oficial do CIF: B largura altura x_centro y_centro;
            sb.append(" ").append(geom.paraCif()).append("\n");
        }
        
        // Fim da Definição da Célula (DF)
        sb.append("DF;\n");
        
        // Fim do arquivo CIF
        sb.append("END;\n");

        if (caminhoArquivo.getParent() != null) {
            Files.createDirectories(caminhoArquivo.getParent());
        }
        Files.writeString(caminhoArquivo, sb.toString());
    }
}