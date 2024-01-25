package models;

public class Telefone {

    private String ddd;
    private String numero;
    
    public Telefone(String ddd, String numero) throws Exception {
        if(ddd.length()!=2 || numero.length() < 8 || numero.length() > 9){
            throw new Exception("Número Inválido!");
        }
        this.ddd = ddd;
        this.numero = numero;
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
