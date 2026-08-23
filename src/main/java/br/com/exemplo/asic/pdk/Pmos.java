package br.com.exemplo.asic.pdk;

import java.util.List;

public final class Pmos implements TransistorMOS {
    private final String id;
    private final int wUf;
    private final int lUf;
    private final int x;
    private final int y;
    private final String nodeDrain;
    private final String nodeGate;
    private final String nodeSource;
    private final String nodeBulk;

    public Pmos(String id, int wUf, int lUf, int x, int y, 
                String nodeDrain, String nodeGate, String nodeSource, String nodeBulk) {
        this.id = id;
        this.wUf = wUf;
        this.lUf = lUf;
        this.x = x;
        this.y = y;
        this.nodeDrain = nodeDrain;
        this.nodeGate = nodeGate;
        this.nodeSource = nodeSource;
        this.nodeBulk = nodeBulk;
    }

    @Override
    public String id() { return id; }

    @Override
    public int wUf() { return wUf; }

    @Override
    public int lUf() { return lUf; }

    @Override
    public List<GeometriaCamada> desenhar() {
        // PMOS fica dentro de um N-Well com margens de segurança de 10um
        return List.of(
            new GeometriaCamada("N_WELL", x - 100, y - 100, x + 300, y + (wUf * 20) + 100),
            new GeometriaCamada("ACTIVE", x, y, x + 200, y + (wUf * 20)),
            new GeometriaCamada("POLY", x + 50, y - 50, x + 150, y + (wUf * 20) + 50),
            new GeometriaCamada("METAL1", x - 20, y + 20, x + 40, y + 80),
            new GeometriaCamada("METAL1", x - 20, y + (wUf * 20) - 80, x + 40, y + (wUf * 20) - 20)
        );
    }

    @Override
    public String gerarSpice() {
        return "M%s %s %s %s %s pmos W=%dum L=%dum".formatted(
            id, nodeDrain, nodeGate, nodeSource, nodeBulk, wUf, lUf
        );
    }
}