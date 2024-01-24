package controller;

import models.DTO.ContatosDTO;
import service.ContatosService;

public class ContatosController {

    ContatosService _contatosService = new ContatosService();

    public void criarContato(ContatosDTO request){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        _contatosService.criarContato(request);
    }
}
