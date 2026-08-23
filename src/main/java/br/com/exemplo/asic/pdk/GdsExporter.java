package br.com.exemplo.asic.pdk;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class GdsExporter {

    private GdsExporter() {}

    private static int obterNumeroCamada(String nomeCamada) {
        if (nomeCamada == null) return 0;
        return switch (nomeCamada.toUpperCase()) {
            case "N_WELL", "NWELL" -> 1;
            case "ACTIVE" -> 2;
            case "POLY"   -> 3;
            case "METAL1" -> 4;
            case "METAL2" -> 5;
            default       -> 0;
        };
    }

    public static void exportarParaGds(String nomeCelulas, List<GeometriaCamada> geometrias, Path caminhoArquivo) throws IOException {
        Objects.requireNonNull(nomeCelulas, "Nome da célula não pode ser nulo.");
        Objects.requireNonNull(geometrias, "Geometrias não podem ser nulas.");
        Objects.requireNonNull(caminhoArquivo, "Caminho do arquivo não pode ser nulo.");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream out = new DataOutputStream(baos)) {

            // 1. HEADER (Versão 3 do GDSII)
            escreverRegistro(out, (short) 0x0002, new byte[]{0, 3});

            // 2. BGNLIB (Datas de criação e modificação)
            LocalDateTime now = LocalDateTime.now();
            short ano = (short) (now.getYear() % 100);
            short mes = (short) now.getMonthValue();
            short dia = (short) now.getDayOfMonth();
            short hora = (short) now.getHour();
            short min = (short) now.getMinute();
            short seg = (short) now.getSecond();

            ByteArrayOutputStream bgnlibBos = new ByteArrayOutputStream();
            DataOutputStream bgnlibDos = new DataOutputStream(bgnlibBos);
            for (int i = 0; i < 2; i++) {
                bgnlibDos.writeShort(ano);
                bgnlibDos.writeShort(mes);
                bgnlibDos.writeShort(dia);
                bgnlibDos.writeShort(hora);
                bgnlibDos.writeShort(min);
                bgnlibDos.writeShort(seg);
            }
            escreverRegistro(out, (short) 0x0102, bgnlibBos.toByteArray());

            // 3. LIBNAME (string com número par de bytes)
            byte[] nomeLibBytes = preencherPar((nomeCelulas + "_lib").getBytes());
            escreverRegistro(out, (short) 0x0206, nomeLibBytes);

            // 4. UNITS (1 user unit = 1um, database unit = 1nm)
            byte[] unitsBytes = new byte[]{0, 0, 0, 1, 0, 0, (byte) 135, (byte) 193, 0, 0, 0, 0, 0, 1, (byte) 134, (byte) 160};
            escreverRegistro(out, (short) 0x0305, unitsBytes);

            // 5. BGNSTR
            ByteArrayOutputStream bgnstrBos = new ByteArrayOutputStream();
            DataOutputStream bgnstrDos = new DataOutputStream(bgnstrBos);
            for (int i = 0; i < 2; i++) {
                bgnstrDos.writeShort(ano);
                bgnstrDos.writeShort(mes);
                bgnstrDos.writeShort(dia);
                bgnstrDos.writeShort(hora);
                bgnstrDos.writeShort(min);
                bgnstrDos.writeShort(seg);
            }
            escreverRegistro(out, (short) 0x0502, bgnstrBos.toByteArray());

            // 6. STRNAME (Nome da célula rigorosamente par)
            byte[] nomeCelBytes = preencherPar(nomeCelulas.getBytes());
            escreverRegistro(out, (short) 0x0606, nomeCelBytes);

            // 7. BOUNDARY Elements para cada Geometria
            for (GeometriaCamada geom : geometrias) {
                adicionarBoundary(out, obterNumeroCamada(geom.camada()), geom.x1(), geom.y1(), geom.x2(), geom.y2());
            }

            // 8. ENDSTR
            escreverRegistro(out, (short) 0x0700, new byte[0]);

            // 9. ENDLIB
            escreverRegistro(out, (short) 0x0400, new byte[0]);
        }

        if (caminhoArquivo.getParent() != null) {
            Files.createDirectories(caminhoArquivo.getParent());
        }
        Files.write(caminhoArquivo, baos.toByteArray());
    }

    private static void adicionarBoundary(DataOutputStream out, int layer, int x1, int y1, int x2, int y2) throws IOException {
        // BOUNDARY
        escreverRegistro(out, (short) 0x0800, new byte[0]);

        // LAYER
        byte[] layerBytes = new byte[]{
            (byte) ((layer >> 8) & 0xFF), 
            (byte) (layer & 0xFF)
        };
        escreverRegistro(out, (short) 0x0D02, layerBytes);

        // DATATYPE (0)
        escreverRegistro(out, (short) 0x0E02, new byte[]{0, 0});

        // XY (Polígono fechado com 5 pontos em inteiros de 4 bytes)
        byte[] xyBytes = empacotarCoordenadasRetangulo(x1, y1, x2, y2);
        escreverRegistro(out, (short) 0x1003, xyBytes);

        // ENDEL
        escreverRegistro(out, (short) 0x1100, new byte[0]);
    }

    private static void escreverRegistro(DataOutputStream out, short tipoRegistro, byte[] dados) throws IOException {
        int tamanhoTotal = 4 + (dados != null ? dados.length : 0);
        out.writeShort(tamanhoTotal);
        out.writeShort(tipoRegistro);
        if (dados != null && dados.length > 0) {
            out.write(dados);
        }
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