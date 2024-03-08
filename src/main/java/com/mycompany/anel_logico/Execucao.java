package com.mycompany.anel_logico;
import java.util.ArrayList;
import java.util.Random;

public class Execucao extends Thread {
    
    public static ArrayList<Processo> processos;
    Object controle = new Object();
    
    public Execucao(){
        processos = new ArrayList<Processo>();
    }
    
    public void criarProcesso(){
        // a cada 30's cria um novo processo deve ser criado
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                while(true) {
                    try{
                        Thread.sleep(3000);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    
                    synchronized (controle){
                        Random r = new Random();
                        int id = r.nextInt(10000);
                        
                        if (processos.size() > 0){
                            boolean existeProcesso = false;
                            
                            for(Processo p: processos){
                                if(p.getProcessoId() == id){
                                    existeProcesso = true;
                                }
                            }
                            if (false == existeProcesso){
                                processos.add(new Processo(id, Estado.ATIVO, false));
                                System.out.println("Criado o processo de número: " + id + ".");
                            }else{
                                processos.add(new Processo(1, Estado.ATIVO, true));
                                System.out.println("Criado o processo de numero 1.");
                            }    
                        }
                    }
                }
            }
        }).start();
    }
    
    void fazerRequisicao(){
        // a cada 25's um processo inicia uma requisição para o coordenador
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                while(true){
                    try{
                        Thread.sleep(25000);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    
                    synchronized (controle){
                        if (processos.size() > 0){
                            
                            Processo processoRequisitor = processos.get(new Random().nextInt(processos.size()));
                            boolean existeProcesso = false;
                            
                            for (Processo p: processos){
                                if(p.getProcessoId() == processoRequisitor.getProcessoId() && p.isCoordenador == false && p.processoEstado == Estado.ATIVO){
                                    existeProcesso = true;
                                    break;
                                }
                            }
                            
                            if(existeProcesso == true){
                                System.out.println("O processo " + processoRequisitor.getProcessoId() + " iniciou uma requisição ao coordenador. ");
                                processoRequisitor.mandarRequisicao();
                            }
                            
                        }
                    }
                }
            }
        }).start();
    }
    
    void inativarCoordenador(){
        //a cada 100's o coordenador fica inativo
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                 
                while(true){
                    try{
                        Thread.sleep(100000);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    
                    synchronized (controle){
                        if(processos.size() > 0){
                            for(Processo p: processos){
                                
                                if(p.isCoordenador()  && p.getProcessoEstado() == Estado.ATIVO){
                                    p.setProcessoEstado(Estado.INATIVO);
                                    System.out.println("Inativado o coordenador de numero "+ p.getProcessoId());
                                    break;
                                }else if(p.isCoordenador() && p.getProcessoEstado() == Estado.INATIVO){
                                    System.out.println("O coordenador número " + p.processoId + " já está inativo");
                                    break;
                                }
                                
                            }
                        }
                    }
                    
                }
            }
        }).start();
    }
        
    void inativarProcesso(){
        //a cada 80's um processo da lista de processos fica inativo
        new Thread(new Runnable(){
                
                
            @Override
            public void run(){
                while(true){
                    try{
                        Thread.sleep(80000);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    
                    synchronized(controle){
                        if(processos.size()> 0){
                            Random r = new Random();
                            int processoInativado = r.nextInt(processos.size());
                            
                            if(processos.get(processoInativado).getProcessoEstado() == Estado.ATIVO){
                                processos.get(processoInativado).setProcessoEstado(Estado.INATIVO);
                                System.out.println("Inativado o processo de número " + processos.get(processoInativado).getProcessoId());
                            }else if(processos.get(processoInativado).getProcessoEstado() == Estado.INATIVO){
                                System.out.println("O processo de número " + processos.get(processoInativado).getProcessoId() + "já está inativo");
                            }
                            
                        }
                    }
                }
            }
        }).start();
    }
}
