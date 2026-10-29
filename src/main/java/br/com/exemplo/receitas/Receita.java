package br.com.exemplo.receitas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Receita {

    private final Long id;
    private final String numero;
    private final TipoReceita tipo;
    private final Paciente paciente;
    private final Doutor doutor;
    private final LocalDate dataEmissao;
    private final List<ItemReceita> itens;
    private final String hashDocumento;
    private final boolean assinaturaValida;
    private boolean utilizada;

    public Receita(
            Long id,
            String numero,
            TipoReceita tipo,
            Paciente paciente,
            Doutor doutor,
            LocalDate dataEmissao,
            String hashDocumento,
            boolean assinaturaValida) {

        this.id = Objects.requireNonNull(id);
        this.numero = Objects.requireNonNull(numero);
        this.tipo = Objects.requireNonNull(tipo);
        this.paciente = Objects.requireNonNull(paciente);
        this.doutor = Objects.requireNonNull(doutor);
        this.dataEmissao = Objects.requireNonNull(dataEmissao);
        this.hashDocumento = Objects.requireNonNull(hashDocumento);
        this.assinaturaValida = assinaturaValida;
        this.itens = new ArrayList<>();
        this.utilizada = false;
    }

    public void adicionarItem(ItemReceita item) {
        itens.add(Objects.requireNonNull(item));
    }

    public List<ItemReceita> getItens() {
        return List.copyOf(itens);
    }

    public void marcarComoUtilizada() {
        if (utilizada) {
            throw new IllegalStateException("Receita já utilizada.");
        }

        utilizada = true;
    }

    public boolean isUtilizada() {
        return utilizada;
    }

    public boolean isAssinaturaValida() {
        return assinaturaValida;
    }

    public String getHashDocumento() {
        return hashDocumento;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public TipoReceita getTipo() {
        return tipo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Doutor getDoutor() {
        return doutor;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }
    
}