
package compiladores;

import java.util.ArrayList;

import java.util.HashMap;

public class TablaSimbolos {

    // definicion
    private ArrayList<HashMap<String, Id>> TdS;

    // Constructor
    public TablaSimbolos() {

        TdS = new ArrayList<>();
        HashMap<String, Id> ContestoInicial = new HashMap<>();
        TdS.add(ContestoInicial);
        System.out.println("Funciono ---------------------------- CONSTRUCTOR");

    }

    public void addContexto() {

        HashMap<String, Id> ADDCONTEXT = new HashMap<>();

        TdS.add(ADDCONTEXT);

    }

    public void delContexto() {
        TdS.remove(0);
    }

    public void addSimbolo(Id id) {

        TdS.get(0).put(id.getNombre(), id);
    }

    public ArrayList<HashMap<String, Id>> getTdS() {
        return TdS;
    }

    public TablaSimbolos(ArrayList<HashMap<String, Id>> tdS) {

        // TdS = (ArrayList<HashMap<String, Id>>)tdS.clone();

    }

    // !AGREGAR ESTE

    public String ImprimirTS() {

        String InformeTS = "String&Nombre&TipoDato&Inicializado&Expresion&Valor&Usado\n";

        for (int i = 0; i < TdS.size(); i++) {

            for (HashMap.Entry<String, Id> entry : TdS.get(i).entrySet()) {

                InformeTS += entry.getKey() + "&" + entry.getValue().getNombre() + "&"
                        + entry.getValue().getTipo() + "&" + entry.getValue().getInicializado()
                        + "&" + entry.getValue().getExpresion() + "&"
                        + entry.getValue().getValor() + "&"
                        + entry.getValue().getUsado() + "\n";

            }
        }
        return InformeTS;
    }

    public Id buscarSimboloLocal(String iden) {

        // System.out.println(iden);

        // System.out.println (TdS.get(0).containsKey(iden));
        // System.out.println (TdS.get(0).size());

        if (TdS.get(0).containsKey(iden)) {
            return TdS.get(0).get(iden);
        } else {
            return null;
        }

    }

    public Id buscarSimbolo(String iden) {

        for (int i = 0; i < TdS.size(); i++) {

            for (HashMap.Entry<String, Id> entry : TdS.get(i).entrySet()) {

                if (entry.getKey().equals(iden)) {
                    return entry.getValue();

                }
            }
        }

        return null;

    }

    public void setTdS(ArrayList<HashMap<String, Id>> tdS) {
        TdS = tdS;
    }

    public void CopiarMapa(HashMap<String, String> copia) {

        for (int i = 0; i < getTdS().size(); i++) {

            //System.out.println("entreeeeeeeeeeee");

            for (HashMap.Entry<String, Id> entry : getTdS().get(i).entrySet()) {

                System.out.println(entry);

                if (entry.getValue().getExpresion() != null) {

                    copia.put(entry.getKey(), entry.getValue().getExpresion());
                }
            }
        }

    }

    public void ReemplazarVariableMAPA(HashMap<String, String> mapa) {

        for (String s : mapa.keySet()) {
            String modifiValue = "";
            String post = mapa.get(s);

            for (int i = 0; i < post.length(); i++) {
                if (buscarSimboloLocal(post.charAt(i) + "") == null && buscarSimbolo(post.charAt(i) + "") == null) {

                    modifiValue += post.charAt(i);

                } else if (buscarSimboloLocal(post.charAt(i) + "") != null) {

                    modifiValue += buscarSimboloLocal(post.charAt(i) + "").getValor();

                } else if (buscarSimbolo(post.charAt(i) + "") != null) {

                    modifiValue += buscarSimbolo(post.charAt(i) + "").getValor();

                }

            }
            mapa.put(s, modifiValue);

        }

    }
    /*
     * public String ReemplazarVariableSTRING(String entrada) {
     * 
     * String modifiValue = "";
     * 
     * for (int i = 0; i < entrada.length(); i++) {
     * if (buscarSimboloLocal(entrada.charAt(i) + "") == null &&
     * buscarSimbolo(entrada.charAt(i) + "") == null) {
     * 
     * modifiValue += entrada.charAt(i);
     * 
     * } else if (buscarSimboloLocal(entrada.charAt(i) + "") != null) {
     * 
     * modifiValue += buscarSimboloLocal(entrada.charAt(i) + "").getValor();
     * 
     * } else if (buscarSimbolo(entrada.charAt(i) + "") != null) {
     * 
     * modifiValue += buscarSimbolo(entrada.charAt(i) + "").getValor();
     * 
     * }
     * 
     * }
     * 
     * return modifiValue;
     * 
     * }
     */

}
