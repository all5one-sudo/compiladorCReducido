package compiladores;

public class Variable extends Id {

    public Variable(String name, TipoDato tip, Boolean inic, Boolean use) {
        super(name, tip, inic, use);
    }

    public Variable(String name, TipoDato tip, Boolean inic, Boolean use, String val) {
        super(name, tip, inic, use, val);
    }

    public Variable(String name, TipoDato tip, Boolean inic, Boolean use, String val, String exp) {
        super(name, tip, inic, use, val, exp);
    }

}
