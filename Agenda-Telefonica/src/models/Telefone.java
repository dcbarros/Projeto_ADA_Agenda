package models;

public class Telefone {
    private Long id;
    private String ddd;
    private String numero;
    private Long contatosId;
    
    public Telefone(String ddd, String numero, Long contatosId) {
        this.ddd = ddd;
        this.numero = numero;
        this.contatosId = contatosId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getContatosId() {
        return contatosId;
    }

    public void setContatosId(Long contatosId) {
        this.contatosId = contatosId;
    }

}
