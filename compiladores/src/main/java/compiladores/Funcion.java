package compiladores;


public class Funcion extends Id {


    public Funcion(String name, TipoDato tip, Boolean inic, Boolean use) {
        super(name, tip, inic, use);
    }

    public Funcion(String name, TipoDato tip, Boolean inic, Boolean use, String val) {
        super(name, tip, inic, use, val);
    }

    public Funcion(String name, TipoDato tip, Boolean inic, Boolean use, String val, String exp) {
        super(name, tip, inic, use, val, exp);
    }


}
