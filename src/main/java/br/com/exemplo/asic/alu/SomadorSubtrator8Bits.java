package br.com.exemplo.asic.alu;

import br.com.exemplo.asic.base.NivelLogico;
import br.com.exemplo.asic.portaslogicas.Xor;

public class SomadorSubtrator8Bits {
    private final FullAdder[] fullAdders = new FullAdder[8];
    private final Xor[] xorsInversores = new Xor[8];

    private int resultadoFinal = 0;
    private boolean carryOutFinal = false;
    private boolean overflowFinal = false;

    public SomadorSubtrator8Bits() {
        for (int i = 0; i < 8; i++) {
            fullAdders[i] = new FullAdder();
            xorsInversores[i] = new Xor();
        }
    }

    public void calcular(int a, int b, boolean subtrair) {
        NivelLogico modoSubtracao = subtrair ? NivelLogico.HIGH : NivelLogico.LOW;
        NivelLogico carryAtual = modoSubtracao; // Se for subtração, o Cin inicial entra como 1 (+1 do complemento de 2)

        int somaOuSub = 0;
        NivelLogico[] bitsSoma = new NivelLogico[8];

        for (int i = 0; i < 8; i++) {
            boolean bitA = ((a >> i) & 1) == 1;
            boolean bitB = ((b >> i) & 1) == 1;

            NivelLogico nivelA = bitA ? NivelLogico.HIGH : NivelLogico.LOW;
            NivelLogico nivelB = bitB ? NivelLogico.HIGH : NivelLogico.LOW;


            NivelLogico bModificado = xorsInversores[i].processar(nivelB, modoSubtracao);


            fullAdders[i].somar(nivelA, bModificado, carryAtual);

            bitsSoma[i] = fullAdders[i].getSoma();
            carryAtual = fullAdders[i].getCarryOut();

            if (bitsSoma[i] == NivelLogico.HIGH) {
                somaOuSub |= (1 << i);
            }
        }

        this.resultadoFinal = somaOuSub & 0xFF;
        this.carryOutFinal = (carryAtual == NivelLogico.HIGH);


        boolean msbA = ((a >> 7) & 1) == 1;
        boolean msbB = ((b >> 7) & 1) == 1;
        if (subtrair) msbB = !msbB;
        boolean msbRes = ((somaOuSub >> 7) & 1) == 1;
        
        this.overflowFinal = (msbA == msbB) && (msbA != msbRes);
    }

    public int getResultado() {
        return resultadoFinal;
    }

    public boolean isCarryOut() {
        return carryOutFinal;
    }

    public boolean isOverflow() {
        return overflowFinal;
    }
}