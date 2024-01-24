package controller;

import models.DTO.ContatosDTO;
import service.ContatosService;

public class ContatosController {

    ContatosService _contatosService = new ContatosService();

    public void criarContato(ContatosDTO request){
        _contatosService.criarContato(request);
    }
    public void listarContatos(){
        _contatosService.listarContatos();
    }
}
