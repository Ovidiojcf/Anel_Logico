/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.anel_logico;

/**
 *
 * @author ojcfa
 */
public class Anel_Logico {
    //Especificaçãoes Definidas:
    // a cada 30's um novo processo deve ser criado
    // a cada 25's algum processo deve fazer uma requisição para o coordenador
    //a cada 100's o coordenador fica inativo
    // a cada 80's um processo da lista de processos fica inativo 
    // dois processos não podem ter o mesmo id
    // dois processos de eleiição não podem acontecer simultaneamente 

    public static void main(String[] args) {
        
        Execucao c = new Execucao();
        c.criarProcesso();
        c.fazerRequisicao();
        c.inativarCoordenador();
        c.inativarProcesso();
    }
}
