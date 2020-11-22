package br.edu.uniritter.canoas.poo.jogo.view;
import br.edu.uniritter.canoas.poo.jogo.model.*;

public class TabuleiroView {
    public static void showSituacaoAtual(Tabuleiro tab, int jogadorAtual, String charRecursao){
         
        int avancar = tab.getJogadores().get(jogadorAtual).JogarDado(); // jogadaro atual faz uma jogada
        int pos = tab.getJogadores().get(jogadorAtual).getPosicaoAtual(); // obtém possição atual do jogador
            
        Casa casa = tab.getCasa(pos + avancar); // possição na casa que será feito o movimento
        
        // faz testes para saber o tipo de casa
        if(casa.getClass().equals(CasaNeutra.class) )
        {
            tab.getJogadores().get(jogadorAtual).avanca(avancar);
            System.out.println("Posição atual: " + tab.getJogadores().get(jogadorAtual).getPosicaoAtual());
            CasaView.desenhaCasa(tab.getCasa(tab.getJogadores().get(jogadorAtual).getPosicaoAtual()), tab.getJogadoresCasa(jogadorAtual)); // imprime jogada
        }
        else if(casa.getClass().equals(CasaAzar.class))
        {
            if(pos - avancar >= 0)
            {
                tab.getJogadores().get(jogadorAtual).avanca(-avancar);
                System.out.println("Posição atual: " + tab.getJogadores().get(jogadorAtual).getPosicaoAtual());
                CasaView.desenhaCasa(tab.getCasa(pos + avancar), tab.getJogadoresCasa(jogadorAtual)); // imprime jogada
            }
            else
            {
                System.out.println("Posição atual: " + tab.getJogadores().get(jogadorAtual).getPosicaoAtual());
                CasaView.desenhaCasa(tab.getCasa(pos + avancar), tab.getJogadoresCasa(jogadorAtual)); // imprime jogada
            }
        }
        else if(casa.getClass().equals(CasaSorte.class))
        {   
            tab.getJogadores().get(jogadorAtual).avanca(avancar);
            
            System.out.println("Posição atual: " + tab.getJogadores().get(jogadorAtual).getPosicaoAtual());
            
            CasaView.desenhaCasa(tab.getCasa(tab.getJogadores().get(jogadorAtual).getPosicaoAtual()), tab.getJogadoresCasa(jogadorAtual)); // imprime jogada
            
            if(tab.getJogadores().get(jogadorAtual).getPosicaoAtual() < tab.getQtdCasas()-1)
            {
                charRecursao += ">";
                System.out.println(charRecursao+" Jogar novamente ...");
                showSituacaoAtual(tab, jogadorAtual, charRecursao); //jogagar novamente
            }          
        }
        
        System.out.println("Fim jogada: " + tab.getJogadores().get(jogadorAtual).getNome());
    }
}
