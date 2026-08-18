package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.base.NivelLogico;
import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;
import br.com.exemplo.asic.registradores.Registrador8Bits;
import br.com.exemplo.asic.ula.Ula8Bits;

public class Cpu {
    private final MemoriaRom rom;
    private final MemoriaRam ram;
    private final ProgramCounter pc = new ProgramCounter();
    private final RegistradorInstrucao ir = new RegistradorInstrucao();
    private final Registrador8Bits acumulador = new Registrador8Bits();
    private final Ula8Bits ula = new Ula8Bits();

    private boolean executando = false;

    public Cpu(MemoriaRom rom, MemoriaRam ram) {
        this.rom = rom;
        this.ram = ram;
    }

    public void executarCiclo() {
        int enderecoAtual = pc.getValor();
        int opcode = rom.lerInstrucao(enderecoAtual);
        ir.carregar(opcode);
        pc.incrementar(); // Avança após ler o opcode

        DecodificadorInstrucao.Instrucao inst;
        try {
            inst = DecodificadorInstrucao.Instrucao.porOpcode(ir.getOpcode());
        } catch (IllegalArgumentException e) {
            executando = false;
            return;
        }

        switch (inst) {
            case LOAD_IMM -> {
                int valorImediato = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                acumulador.clock(NivelLogico.HIGH, valorImediato);
            }
            case LOAD -> {
                int enderecoRam = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                int dadoRam = ram.ler(enderecoRam);
                acumulador.clock(NivelLogico.HIGH, dadoRam);
            }
            case STORE -> {
                int enderecoRam = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                ram.escrever(enderecoRam, acumulador.getValor());
            }
            case ADD -> {
                int enderecoRam = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                int valorRam = ram.ler(enderecoRam);
                
                ula.executar(Ula8Bits.Operacao.ADD, acumulador.getValor(), valorRam);
                acumulador.clock(NivelLogico.HIGH, ula.getResultado());
            }
            case SUB -> {
                int enderecoRam = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                int valorRam = ram.ler(enderecoRam);
                ula.executar(Ula8Bits.Operacao.SUB, acumulador.getValor(), valorRam);
                acumulador.clock(NivelLogico.HIGH, ula.getResultado());
            }
            case AND -> {
                int enderecoRam = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                int valorRam = ram.ler(enderecoRam);
                ula.executar(Ula8Bits.Operacao.AND, acumulador.getValor(), valorRam);
                acumulador.clock(NivelLogico.HIGH, ula.getResultado());
            }
            case OR -> {
                int enderecoRam = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                int valorRam = ram.ler(enderecoRam);
                ula.executar(Ula8Bits.Operacao.OR, acumulador.getValor(), valorRam);
                acumulador.clock(NivelLogico.HIGH, ula.getResultado());
            }
            case INC -> {
                ula.executar(Ula8Bits.Operacao.ADD, acumulador.getValor(), 1);
                acumulador.clock(NivelLogico.HIGH, ula.getResultado());
            }
            case DEC -> {
                ula.executar(Ula8Bits.Operacao.SUB, acumulador.getValor(), 1);
                acumulador.clock(NivelLogico.HIGH, ula.getResultado());
            }
            case NOT -> {
                ula.executar(Ula8Bits.Operacao.NOT, acumulador.getValor(), 0);
                acumulador.clock(NivelLogico.HIGH, ula.getResultado());
            }
            case SHL -> {
                ula.executar(Ula8Bits.Operacao.SHL, acumulador.getValor(), 0);
                acumulador.clock(NivelLogico.HIGH, ula.getResultado());
            }
            case SHR -> {
                ula.executar(Ula8Bits.Operacao.SHR, acumulador.getValor(), 0);
                acumulador.clock(NivelLogico.HIGH, ula.getResultado());
            }
            case JMP -> {
                int novoEndereco = rom.lerInstrucao(pc.getValor());
                pc.saltar(novoEndereco);
            }
            case JZ -> {
                int novoEndereco = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                if (ula.getFlags().isZero()) {
                    pc.saltar(novoEndereco);
                }
            }
            case JNZ -> {
                int novoEndereco = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                if (!ula.getFlags().isZero()) {
                    pc.saltar(novoEndereco);
                }
            }
            case JN -> { // <-- ADICIONADO: Salta se o resultado da ULA for negativo (bit 7 ligado)
                int novoEndereco = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                if (ula.getFlags().isNegativo()) {
                    pc.saltar(novoEndereco);
                }
            }
            case CMP -> {
                int enderecoRam = rom.lerInstrucao(pc.getValor());
                pc.incrementar();
                int valorRam = ram.ler(enderecoRam);
                ula.executar(Ula8Bits.Operacao.SUB, acumulador.getValor(), valorRam);
            }
            case HALT -> {
                executando = false;
            }
        }
    }

    public void executarPrograma() {
        executando = true;
        int maxCiclos = 1000;
        int ciclos = 0;

        while (executando && ciclos < maxCiclos) {
            executarCiclo();
            ciclos++;
        }
    }

    public int getAcumuladorValor() {
        return acumulador.getValor();
    }

    public ProgramCounter getPc() {
        return pc;
    }

    public Ula8Bits getUla() {
        return ula;
    }
}