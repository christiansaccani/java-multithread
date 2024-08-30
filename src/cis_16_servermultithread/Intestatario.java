package cis_16_servermultithread;
public class Intestatario {
    private String nome;
    private String cognome;

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return nome + " " + cognome;
    }
}