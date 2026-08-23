package br.com.exemplo.asic.pdk;

public record GeometriaCamada(String camada, int x1, int y1, int x2, int y2) {
	public String paraCif() {
		int largura = x2 - x1;
		int altura = y2 - y1;
		int cx = (x1 + x2) / 2;
		int cy = (y1 + y2) / 2;
		return "B %d %d %d %d;".formatted(largura, altura, cx, cy);
	}
}