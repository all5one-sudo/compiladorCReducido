package compiladores;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class codigoIntermedioWhile {

    private String whileStatement;
    private String condition;
    private HashMap<String, String> instructionMap;
    private codigoIntermedioOperacion whileActions;

    public codigoIntermedioWhile(String whileStatement) {
        this.whileStatement = whileStatement;
        this.condition = "";
        this.instructionMap = new HashMap<>();
    }

    public void runProcess() {
        // whileStatement = "while (i <= n) {a = b + 1;i++;}";
        if (!this.whileStatement.equals("")) {
            whileStatement = whileStatement.replaceAll("\\s", "");
            System.out.println("Se recibe: " + whileStatement);
            condition = whileStatement.substring(whileStatement.indexOf("(") + 1, whileStatement.indexOf(")"));
            System.out.println("label l0");
            System.out.println("t0 = " + condition);
            System.out.println("ifjmp t0, l1");
            String instructions = whileStatement.substring(whileStatement.indexOf("{") + 1,
                    whileStatement.indexOf("}"));
            String[] statements = instructions.split(";");
            List<String> statementsVar = Arrays.asList(statements);
            /*
             * int i;
             * for (i = 0; i < (statements.length - 1); i++) {
             * System.out.println("t" + (i + 1) + " = "
             * + statementsVar.get(i).substring(statementsVar.get(i).indexOf("=") + 1,
             * statementsVar.get(i).length()));
             * }
             */
            generateMap(statementsVar);
            whileActions = new codigoIntermedioOperacion(instructionMap);
            whileActions.runProcess();
            // System.out.println(instructions.charAt(0) + " = " + "t" + i);
            if (statements[statements.length - 1].contains("+")) {
                System.out.println(statements[statements.length - 1].charAt(0) + " = "
                        + statements[statements.length - 1].charAt(0) + " + " + 1);
            } else {
                System.out.println(statements[statements.length - 1].charAt(0) + " = "
                        + statements[statements.length - 1].charAt(0) + " - " + 1);
            }
            System.out.println("jmp l0");
            System.out.println("label l1");
        }
    }

    private void generateMap(List<String> actionsToParse) {
        for (int i = 0; i < (actionsToParse.size() - 1); i++) {
            String[] aux = actionsToParse.get(i).split("=");
            instructionMap.put(aux[0], aux[1]);
        }
    }
}
