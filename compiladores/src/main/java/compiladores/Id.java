package compiladores;

import java.util.HashMap;

public abstract class Id {

    String nombre;
    TipoDato tipo;
    Boolean inicializado;
    Boolean usado;
    String valor;
    String expresion;

    public Id(String name, TipoDato tip, Boolean inic, Boolean use) {
        nombre = name;
        tipo = tip;
        inicializado = inic;
        usado = use;

    }

    public Id(String name, TipoDato tip, Boolean inic, Boolean use, String val) {
        nombre = name;
        tipo = tip;
        inicializado = inic;
        usado = use;
        valor = val;

    }

    public Id(String name, TipoDato tip, Boolean inic, Boolean use, String val, String exp) {
        nombre = name;
        tipo = tip;
        inicializado = inic;
        usado = use;
        valor = val;
        expresion = exp;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoDato getTipo() {
        return tipo;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public Boolean getInicializado() {
        return inicializado;
    }

    public Boolean getUsado() {
        return usado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(TipoDato tipo) {
        this.tipo = tipo;
    }

    public void setInicializado(Boolean inicializado) {
        this.inicializado = inicializado;
    }

    public void setUsado(Boolean usado) {
        this.usado = usado;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public String getExpresion() {
        return expresion;
    }

}
