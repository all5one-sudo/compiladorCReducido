package compiladores;

import java.util.HashMap;

public class codigoIntermedioIf {
    private String ifStatement;
    private String condition;
    private HashMap<String, String> instructionMap;
    private codigoIntermedioOperacion ifActions;

    public codigoIntermedioIf(String ifStatement) {
        this.ifStatement = ifStatement;
        this.condition = "";
        this.instructionMap = new HashMap<>();
    }

    public void runProcess() {
        if (!this.ifStatement.equals("")) {
            if (this.ifStatement.contains("else")) {
                // ifStatement = "if (y <= 0) {x = y;} else {x = -y;}";
                ifStatement = ifStatement.replaceAll("\\s", "");
                ifStatement = ifStatement.replaceAll("\n", "");
                // System.out.println("A analizar: " + ifStatement);
                // System.out.println("-------------------------");
                this.condition = ifStatement.substring(ifStatement.indexOf("(") + 1, ifStatement.indexOf(")"));
                System.out.println("t0" + " = " + condition);
                System.out.println("ifjmp t0, l0");
                int auxIndex = 0;
                for (int i = 0; i < ifStatement.length(); i++) {
                    if (ifStatement.charAt(i) == '}') {
                        auxIndex = i;
                        break;
                    }
                }
                // System.out.println(auxIndex);
                String first = ifStatement.substring(0, auxIndex + 1);
                String second = ifStatement.substring(auxIndex + 1, ifStatement.length());
                // System.out.println("Primera parte: " + first + ", Segunda parte: " + second);
                String ifAction = first.substring(first.indexOf("{") + 1, first.indexOf("}"));
                // System.out.println(ifAction);
                generateMap(ifAction);
                this.ifActions = new codigoIntermedioOperacion(instructionMap);
                this.ifActions.runProcess();
                System.out.println("jmp l1 \nlabel l0");
                // String aux = instructionMap.get(ifAction.charAt(0) + "");
                /*
                 * if (instructionMap.keySet().size() == 1) {
                 * System.out.println(ifAction + "\njmp l1\nlabel l0");
                 * } else {
                 * this.ifActions = new codigoIntermedioOperacion(instructionMap);
                 * this.ifActions.runProcess();
                 * }
                 */
                this.instructionMap.clear();
                String elseAction = second.substring(second.indexOf("{") + 1, second.indexOf("}"));
                generateMap(elseAction);
                this.ifActions = null;
                this.ifActions = new codigoIntermedioOperacion(instructionMap);
                this.ifActions.runProcess();
                System.out.println("label l1");
                /*
                 * if (instructionMap.keySet().size() == 1) {
                 * System.out.println(elseAction);
                 * } else {
                 * // this.ifActions = null;
                 * this.ifActions = new codigoIntermedioOperacion(instructionMap);
                 * this.ifActions.runProcess();
                 * System.out.println("jmp l1 \nlabel l0");
                 * }
                 */
                // System.out.println("jmp l1 \nlabel l0");
                // aca esta el problema
                // System.out.println("label l1");
                // System.out.println(ifAction + "\njmp l1\nlabel l0");
                // System.out.println(elseAction + "\nlabel l1");
            } else {
                // ifStatement = "if (y <= 0) {x = y;} else {x = -y;}";
                ifStatement = ifStatement.replaceAll("\\s", "");
                ifStatement = ifStatement.replaceAll("\n", "");
                // System.out.println("A analizar: " + ifStatement);
                // System.out.println("-------------------------");
                this.condition = ifStatement.substring(ifStatement.indexOf("(") + 1, ifStatement.indexOf(")"));
                System.out.println("t0" + " = " + condition);
                System.out.println("ifjmp t0, l0");
                int auxIndex = 0;
                for (int i = 0; i < ifStatement.length(); i++) {
                    if (ifStatement.charAt(i) == '}') {
                        auxIndex = i;
                        break;
                    }
                }
                // System.out.println(auxIndex);
                String first = ifStatement.substring(0, auxIndex + 1);
                String second = ifStatement.substring(auxIndex + 1, ifStatement.length());
                // System.out.println("Primera parte: " + first + ", Segunda parte: " + second);
                String ifAction = first.substring(first.indexOf("{") + 1, first.indexOf("}"));
                // System.out.println(ifAction);
                generateMap(ifAction);
                this.ifActions = new codigoIntermedioOperacion(instructionMap);
                this.ifActions.runProcess();
                System.out.println("label l0");
            }
        }

    }

    private void generateMap(String actionsToParse) {
        String[] aux = actionsToParse.split(";");
        for (int i = 0; i < aux.length; i++) {
            String[] instruction = aux[i].split("=");
            instructionMap.put(instruction[0], instruction[1]);
        }
    }
}
