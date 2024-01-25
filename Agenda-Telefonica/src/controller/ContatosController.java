package controller;

import models.DTO.ContatosDTO;
import service.ContatosService;

public class ContatosController {

    ContatosService _contatosService = new ContatosService();

    public void criarContato(ContatosDTO request) throws Exception{
        _contatosService.criarContato(request);
    }
    public void listarContatos(){
        _contatosService.listarContatos();
    }

    public void atualizarContato(Long id, String update, int selecao, boolean append) throws Exception{
        _contatosService.atualizarContato(id, update,selecao, append);
    }

    public void removerContato(Long id){}
}
