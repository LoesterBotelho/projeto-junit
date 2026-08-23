package br.com.exemplo.asic.pdk;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class GdsExporter {

    private GdsExporter() {
        // Construtor privado para evitar instanciação de classe utilitária
    }

    private static int obterNumeroCamada(String nomeCamada) {
        if (nomeCamada == null) return 0;
        return switch (nomeCamada.toUpperCase()) {
            case "N_WELL" -> 1;
            case "ACTIVE" -> 2;
            case "POLY"   -> 3;
            case "METAL1" -> 4;
            case "METAL2" -> 5;
            default       -> 0;
        };
    }

    public static void exportarParaGds(String nomeCelulas, List<GeometriaCamada> geometrias, Path caminhoArquivo) throws IOException {
        Objects.requireNonNull(nomeCelulas, "O nome da célula não pode ser nulo.");
        Objects.requireNonNull(geometrias, "A lista de geometrias não pode ser nula.");
        Objects.requireNonNull(caminhoArquivo, "O caminho do arquivo de destino não pode ser nulo.");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream out = new DataOutputStream(baos)) {

            // 1. HEADER (Versão GDSII)
            escreverCabecalhoRecord(out, 2, (short) 0x0002, new byte[]{0, 3});

            // 2. BGNLIB (Início da Biblioteca)
            escreverCabecalhoRecord(out, 12, (short) 0x0102, new byte[]{0, 26, 0, 6, 0, 6, 0, 6, 0, 6});

            // 3. LIBNAME (Nome da Biblioteca)
            byte[] nomeLibBytes = "PDK_10UM_LIB".getBytes();
            escreverCabecalhoRecord(out, 4 + nomeLibBytes.length, (short) 0x0206, nomeLibBytes);

            // 4. UNITS (Correção: agora enviando efetivamente o array unitsBytes ajustado com casts)
            byte[] unitsBytes = new byte[]{0, 0, 0, 1, 0, 0, (byte) 135, (byte) 193, 0, 0, 0, 0, 0, 1, (byte) 134, (byte) 160};
            escreverCabecalhoRecord(out, 4 + unitsBytes.length, (short) 0x0305, unitsBytes);

            // 5. BGNSTR (Início da Célula)
            escreverCabecalhoRecord(out, 12, (short) 0x0502, new byte[]{0, 26, 0, 6, 0, 6, 0, 6, 0, 6});

            // 6. STRNAME (Nome da Célula)
            byte[] nomeCelBytes = preencherPar(nomeCelulas.getBytes());
            escreverCabecalhoRecord(out, 4 + nomeCelBytes.length, (short) 0x0606, nomeCelBytes);

            // 7. BOUNDARY elements (Iterando pelas geometrias do PDK 10um)
            for (GeometriaCamada geom : geometrias) {
                adicionarBoundary(out, obterNumeroCamada(geom.camada()), geom.x1(), geom.y1(), geom.x2(), geom.y2());
            }

            // 8. ENDSTR (Fim da Célula)
            escreverCabecalhoRecord(out, 4, (short) 0x0700, new byte[0]);

            // 9. ENDLIB (Fim da Biblioteca)
            escreverCabecalnoRecordSeguro(out, 4, (short) 0x0400, new byte[0]);
        }

        // Garante que o diretório pai exista antes de gravar
        if (caminhoArquivo.getParent() != null) {
            Files.createDirectories(caminhoArquivo.getParent());
        }
        Files.write(caminhoArquivo, baos.toByteArray());
    }

    private static void adicionarBoundary(DataOutputStream out, int layer, int x1, int y1, int x2, int y2) throws IOException {
        escreverCabecalhoRecord(out, 4, (short) 0x0800, new byte[0]);

        byte[] layerBytes = new byte[]{(byte) (layer >> 8), (byte) (layer & 0xFF)};
        escreverCabecalhoRecord(out, 6, (short) 0x0D02, layerBytes);

        escreverCabecalhoRecord(out, 6, (short) 0x0E02, new byte[]{0, 0});

        byte[] xyBytes = empacotarCoordenadasRetangulo(x1, y1, x2, y2);
        escreverCabecalhoRecord(out, 4 + xyBytes.length, (short) 0x1003, xyBytes);

        escreverCabecalhoRecord(out, 4, (short) 0x1100, new byte[0]);
    }

    private static void escreverCabecalhoRecord(DataOutputStream out, int tamanhoTotal, short tipoRegistro, byte[] dados) throws IOException {
        out.writeShort(tamanhoTotal);
        out.writeShort(tipoRegistro);
        if (dados != null && dados.length > 0) {
            out.write(dados);
        }
    }
    
    // Alias para compatibilidade semântica de fim de arquivo se necessário
    private static void escreverCabecalnoRecordSeguro(DataOutputStream out, int tamanhoTotal, short tipoRegistro, byte[] dados) throws IOException {
        escreverCabecalhoRecord(out, tamanhoTotal, tipoRegistro, dados);
    }

    private static byte[] empacotarCoordenadasRetangulo(int x1, int y1, int x2, int y2) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        
        int[] coordsX = {x1, x2, x2, x1, x1};
        int[] coordsY = {y1, y1, y2, y2, y1};

        for (int i = 0; i < 5; i++) {
            dos.writeInt(coordsX[i]);
            dos.writeInt(coordsY[i]);
        }
        return bos.toByteArray();
    }

    private static byte[] preencherPar(byte[] entrada) {
        if (entrada.length % 2 != 0) {
            byte[] ajustado = new byte[entrada.length + 1];
            System.arraycopy(entrada, 0, ajustado, 0, entrada.length);
            ajustado[entrada.length] = 0;
            return ajustado;
        }
        return entrada;
    }
}