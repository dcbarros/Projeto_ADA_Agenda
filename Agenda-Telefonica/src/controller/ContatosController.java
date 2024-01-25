package controller;

import models.DTO.ContatosDTO;
import service.ContatosService;

public class ContatosController {

    ContatosService _contatosService = new ContatosService();

    public void criarContato(ContatosDTO request) throws Exception{
        _contatosService.criarContato(request);
    }
    public void listarContatos() throws Exception{
        _contatosService.listarContatos();
    }

    public void atualizarContato(Long id, String update, int selecao, boolean append) throws Exception{
        _contatosService.atualizarContato(id, update,selecao, append);
    }

    public void removerContato(Long id) throws Exception{
        _contatosService.removerContato(id);
    }
}
