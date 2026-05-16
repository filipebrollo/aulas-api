package br.com.serratec.aula5.enums;

public enum Combustivel {
    ETANOL(1,"ETANOL"), FLEX(2,"FLEX"), GASOLINA(3,"GASOLINA"), DIESEL(4,"DIESEL");

    private Integer codigo;
    private String tipo;

    private Combustivel(Integer codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
