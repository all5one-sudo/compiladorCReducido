package compiladores;

import java.util.HashMap;

public class codigoIntermedioOperacion {

    private String expresion;
    private HashMap<String, String> informacion;

    private static final char[][] operadoresPrecedencia = {
            { '/', '1' },
            { '*', '1' },
            { '+', '2' },
            { '-', '2' }
    };

    public codigoIntermedioOperacion(HashMap<String, String> informacion) {
        this.informacion = new HashMap<String, String>(informacion);
        for (String variable : this.informacion.keySet()) {
            if (variable.contains("int")) {
                variable = variable.replace("int", "");
            } else if (variable.contains("string")) {
                variable = variable.replace("string", "");
            } else if (variable.contains("bool")) {
                variable = variable.replace("bool", "");
            }
        }

    }

    private static int precedencia(String t) {
        char token = t.charAt(0);
        for (int i = 0; i < operadoresPrecedencia.length; i++) {
            if (token == operadoresPrecedencia[i][0]) {
                return Integer.parseInt(operadoresPrecedencia[i][1] + "");
            }
        }
        return -1;
    }

    public void printMapa() {
        for (String s : informacion.keySet()) {
            System.out.println(s + " " + informacion.get(s));
        }
    }

    public void runProcess() {
        if (!this.informacion.isEmpty()) {
            for (String key : informacion.keySet()) {
                expresion = informacion.get(key);
                if (key.contains("int")) {
                    key = key.replace("int", "");
                }
                else if (key.contains("bool")){
                    key = key.replace("bool", "");
                }
                else if (key.contains("string")) {
                    key = key.replace("string", "");
                }
                if (!expresion.contains("+") && !expresion.contains("-") && !expresion.contains("/")
                        && !expresion.contains("*")) {
                    System.out.println(key + " = " + expresion);
                } else {
                    String finalLine = "";
                    int i, j, o = 0;
                    char token;
                    boolean procesado[];
                    String[][] operadores = new String[10][2];
                    // System.out.println("Operación a realizar: " + key + " = " + expresion);
                    procesado = new boolean[expresion.length()];
                    for (i = 0; i < procesado.length; i++) {
                        procesado[i] = false;
                    }
                    for (i = 0; i < expresion.length(); i++) {
                        token = expresion.charAt(i);
                        for (j = 0; j < operadoresPrecedencia.length; j++) {
                            if (token == operadoresPrecedencia[j][0]) {
                                operadores[o][0] = (token + "");
                                operadores[o][1] = (i + "");
                                o++;
                                break;
                            }
                        }
                    }
                    for (i = (o - 1); i >= 0; i--) {
                        for (j = 0; j < i; j++) {
                            if (precedencia(operadores[j][0]) > precedencia(operadores[j + 1][0])) {
                                String t = operadores[j][0];
                                operadores[j][0] = operadores[j + 1][0];
                                operadores[j + 1][0] = t;
                                t = operadores[j][1];
                                operadores[j][1] = operadores[j + 1][1];
                                operadores[j + 1][1] = t;
                            }
                        }
                    }
                    for (i = 0; i < o; i++) {
                        j = Integer.parseInt(operadores[i][1] + "");
                        String op1 = "";
                        String op2 = "";
                        if (procesado[j - 1] == true) {
                            if (precedencia(operadores[i - 1][0]) == precedencia(operadores[i][0])) {
                                op1 = "t" + i;
                            } else {
                                for (int k = 0; k < o; k++) {
                                    if ((j - 2) == Integer.parseInt(operadores[k][1])) {
                                        op1 = "t" + (k + 1) + "";
                                    }
                                }
                            }
                        }

                        else {
                            op1 = expresion.charAt(j - 1) + "";
                        }
                        if (procesado[j + 1] == true) {
                            for (int k = 0; k < o; k++) {
                                if ((j + 2) == Integer.parseInt(operadores[k][1])) {
                                    op2 = "t" + (k + 1) + "";
                                }
                            }
                        } else {
                            op2 = expresion.charAt(j + 1) + "";
                        }
                        System.out.println("t" + (i + 1) + " = " + op1 + operadores[i][0] + op2);
                        procesado[j] = procesado[j - 1] = procesado[j + 1] = true;
                        finalLine = key + " = t" + (i + 1);
                    }
                    System.out.println(finalLine);
                }
            }
        }
    }
}

/*
 * HashMap<String, String> mapa = new HashMap<>();
 * mapa.put("a", "1*2-6/9+3*2");
 * mapa.put("b", "3-2-8*7");
 * CodigoIntermedioOperacion codigo = new codigoIntermedioOperacion(mapa);
 * System.out.println("Prueba Codigo Intermedio:");
 * System.out.println("----------------");
 * codigo.runProcess();
 */
