package models;

public class Telefone {

    private String ddd;
    private String numero;
    
    public Telefone(String ddd, String numero) throws Exception {
        if(ddd.length()!=2 || numero.length() < 8 || numero.length() > 9){
            throw new Exception("Número Inválido, verifique as regras:\nDDD deve ter dois números e o telefone deve possuir 8 ou 9 números");
        }
        this.ddd = ddd;
        this.numero = numero;
    }

    public Telefone() {
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

}
