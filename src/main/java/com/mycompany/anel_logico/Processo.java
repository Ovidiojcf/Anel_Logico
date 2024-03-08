/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.anel_logico;
import java.util.ArrayList;
/**
 *
 * @author ojcfa
 */
public class Processo {
    
    public int processoId;
    public Estado processoEstado;
    public boolean isCoordenador;
    
    public Processo(int processoId, Estado estadoProcesso, Boolean isCoordenador){
        setProcessoId(processoId);
        setProcessoEstado(estadoProcesso);
        setisCoordenador(isCoordenador);
    }
    
    public int getProcessoId(){
        return processoId;
    }
    
    public void setProcessoId(int processoId){
        this.processoId = processoId;
    }
    
    public Estado getProcessoEstado(){
        return processoEstado;
    }
    
    public void setProcessoEstado(Estado processoEstado){
        this.processoEstado = processoEstado;
    }
    
    public boolean isCoordenador(){
        return isCoordenador;
    }
    
    public void setisCoordenador(boolean estadoCoordenador){
        this.isCoordenador = estadoCoordenador ;
    }
    
    void mandarRequisicao(){
        //Estado verificarEstado = Estado.ATIVO;
        
        for(Processo p : Execucao.processos){
            if(p.isCoordenador && this.processoId != p.getProcessoId()){
                if(p.receberRequisicao().equals(Estado.INATIVO)){
                    System.out.println("O processo de numero " + this.getProcessoId() + "Enviou a requisicao"+ "O coordenador de número"+p.getProcessoId()+ "recebeu uma requisição, ele esta " + p.getProcessoEstado() + ".");
                    p.setisCoordenador(false);
                    this.chamarEleicao();
                    break;
                }else{
                   System.out.println("Requisição enviada ao coordenador "+ p.getProcessoId() + "realizada com sucesso");
                   break;
                }
            }      
        }
    }
    
    Estado receberRequisicao(){
        return this.getProcessoEstado();  
    }
    
    private void chamarEleicao(){
        
        System.out.println("Inciada uma nova eleição.");
        ArrayList<Processo> processosVerificados = new ArrayList<Processo>();
        
        for(Processo p: Execucao.processos){ 
          if(p.getProcessoEstado() == Estado.ATIVO){ 
              processosVerificados.add(p);
          }
        }
        
        int idNovoCoordenador = this.getProcessoId();
        
        for(Processo p: processosVerificados){
            System.out.println("Comparando processos: "+ p.getProcessoId() + "e" + idNovoCoordenador);
            if(p.getProcessoId() > idNovoCoordenador){ 
                idNovoCoordenador = p.getProcessoId();
            }
        }
        atualizarCoordenador(idNovoCoordenador);
        System.out.println("Eleição realizada com sucesso.");
        
    }
    
    private void atualizarCoordenador(int idNovoCoordenador){ 
        for(Processo p: Execucao.processos){ 
            if(p.getProcessoId() == idNovoCoordenador){
                p.setisCoordenador(true);
                System.out.println("O novo coordenador é o processo de número " + p.getProcessoId() + "estado" + "coord" + p.isCoordenador());
               }
            }
        }
}
    
    
  
