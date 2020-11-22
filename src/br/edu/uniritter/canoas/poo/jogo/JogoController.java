package br.edu.uniritter.canoas.poo.jogo;

import br.edu.uniritter.canoas.poo.jogo.model.Jogador;
import br.edu.uniritter.canoas.poo.jogo.model.MuitosJogadoresException;
import br.edu.uniritter.canoas.poo.jogo.model.Tabuleiro;
import br.edu.uniritter.canoas.poo.jogo.view.JogoView;
import br.edu.uniritter.canoas.poo.jogo.view.TabuleiroView;

public class JogoController {
    private static int qtdJogadores;
    private static int qtdTurnos = 0;
    private static Tabuleiro tabuleiro;
    private static int jogadorAtual = 0;

    public static void iniciarJogo() {
        tabuleiro = new Tabuleiro(50,20,20);
        qtdJogadores = JogoView.intQtdJogadores(2, 6);
        registrarJogadores();

       while(!EhFimDoJogo() && qtdTurnos < 1000)
       {
           iniciarJogada(); //Mostra quem está jogando
           System.out.println("     [TURNO: " + ++qtdTurnos + "]");
           
           TabuleiroView.showSituacaoAtual(tabuleiro,jogadorAtual,""); 
           
           proximoJogador();
       }
       
       if(qtdTurnos < 1000)
       {
            System.out.println("TURNO: " + ++qtdTurnos );
            System.out.println("VENCEDOR: " +  tabuleiro.getJogadores().get(jogadorAtual).getNome() );
            System.out.println("FIM ======");
       }
       else
       {
            System.out.println("TURNO: " + ++qtdTurnos);
            System.out.println("VENCEDOR: NENHUM");
            System.out.println("FIM ======");
       }

    }
    private static void proximoJogador() {
        jogadorAtual++;
        if(jogadorAtual == qtdJogadores) {
            jogadorAtual = 0;
        }
    }
    public static void registrarJogadores() {
        for (int i = 1; i <= qtdJogadores; i++) {
            String n = JogoView.InformeJogador(i);
            try {
                tabuleiro.addJogador(new Jogador(n));
            } catch (MuitosJogadoresException e) {
                e.printStackTrace();
            }
        }
    }
    private static void iniciarJogada() {
        JogoView.mostraJogadorAtual(tabuleiro.getJogadores().get(jogadorAtual));
    }

    private static boolean EhFimDoJogo()
    {
        return tabuleiro.getJogadores().get(jogadorAtual).getPosicaoAtual() >= tabuleiro.getQtdCasas()-1 ;
    }
    
}
