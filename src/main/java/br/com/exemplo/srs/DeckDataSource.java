package br.com.exemplo.srs;

import java.util.ArrayList;
import java.util.List;

public class DeckDataSource {
    
    public static List<Flashcard> carregarCardsIniciais() {
        List<Flashcard> deck = new ArrayList<>();
        
        String[][] data = {
            {"The early bird catches the worm.", "O madrugador pega a minhoca."},
            {"Actions speak louder than words.", "Ações falam mais alto do que palavras."},
            {"Better late than never.", "Antes tarde do que nunca."},
            {"A blessing in disguise.", "Um mal que vem para o bem."},
            {"Break a leg!", "Quebra a perna! (Boa sorte!)"},
            {"Call it a day.", "Por hoje é só. / Encerrar o expediente."},
            {"Cut corners.", "Fazer corpo mole / Atalhar o caminho."},
            {"Easy come, easy go.", "O que vem fácil, vai fácil."},
            {"Get out of hand.", "Sair do controle."},
            {"Hit the nail on the head.", "Acertar em cheio."},
            {"It's a piece of cake.", "É muito fácil (mamão com açúcar)."},
            {"Let the cat out of the bag.", "Deixar escapar um segredo."},
            {"Once in a blue moon.", "Raramente / Quase nunca."},
            {"Pitch in and help out.", "Colaborar e ajudar."},
            {"Spill the beans.", "Contar o segredo / Abrir o jogo."},
            {"Take it with a grain of salt.", "Não acredite 100% / Fique com um pé atrás."},
            {"The ball is in your court.", "A decisão é sua."},
            {"Under the weather.", "Indisposto / Meio doente."},
            {"You can't judge a book by its cover.", "Não julgue o livro pela capa."},
            {"When it rains, it pours.", "Desgraça pouca é bobagem / Tudo ao mesmo tempo."}
        };

        for (String[] pair : data) {
            deck.add(new Flashcard(pair[0], pair[1]));
        }

        return deck;
    }
}