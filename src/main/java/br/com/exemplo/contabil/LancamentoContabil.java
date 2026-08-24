package br.com.exemplo.contabil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LancamentoContabil {
	private String id;
	private LocalDate data;
	private String historico;
	private List<ItemLancamento> itens;

	public LancamentoContabil(String id, LocalDate data, String historico) {
		this.id = Objects.requireNonNull(id, "ID nao pode ser nulo");
		this.data = Objects.requireNonNull(data, "Data nao pode ser nula");
		this.historico = Objects.requireNonNull(historico, "Historico nao pode ser nulo");
		this.itens = new ArrayList<>();
	}

	public void adicionarItem(ItemLancamento item) {
		Objects.requireNonNull(item, "Item nao pode ser nulo");
		this.itens.add(item);
	}

	public String getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public String getHistorico() {
		return historico;
	}

	public List<ItemLancamento> getItens() {
		return Collections.unmodifiableList(itens);
	}

	@Override
	public String toString() {
		return "LancamentoContabil [id=" + id + 
				", data=" + data + 
				", historico=" + historico + 
				", itens=" + itens
				+ "]";
	}

}