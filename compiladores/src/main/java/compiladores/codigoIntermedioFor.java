package compiladores;

import java.util.HashMap;

public class codigoIntermedioFor {

    private String forStatement;
    private HashMap<String, String> instructionMap;
    private codigoIntermedioOperacion forActions;

    public codigoIntermedioFor(String forStatement) {
        this.forStatement = forStatement;
        instructionMap = new HashMap<>();
    }

    public void runProcess() {
        if (!this.forStatement.equals("")) {
            // forStatement = "for (k = 10; k >= 0; --k) {y = 4 - k;}";
            forStatement = forStatement.replaceAll("\\s", "");
            forStatement = forStatement.replaceAll("\n", "");
            // System.out.println("Se recibe: " + forStatement);
            System.out.println(forStatement.substring(forStatement.indexOf("(") + 1, forStatement.indexOf(";")));
            String first = forStatement.substring(forStatement.indexOf(";") + 1, forStatement.length());
            System.out.println("forStatement: " + forStatement);
            String increment = first.substring(first.indexOf(";"), first.indexOf(")"));
            // System.out.println("first: " + first);
            String condition = first.substring(0, first.indexOf(";"));
            String action = first.substring(first.indexOf("{") + 1, first.indexOf("}"));
            // System.out.println("Action: " + action);
            System.out.println("label l0 \nt0 = " + condition + "\nifjmp t0, l1");
            // String forAction = action.substring(action.indexOf("=") + 1,
            // action.indexOf(";"));
            // System.out.println("for: " + forAction);
            // String variable = first.substring(first.indexOf("{") + 1,
            // first.indexOf("="));
            // String variable = action.substring(0, action.indexOf("="));
            // System.out.println("variable " + variable);
            if (increment.contains("+")) {
                generateMap(action);
                this.forActions = new codigoIntermedioOperacion(instructionMap);
                this.forActions.runProcess();
                System.out.println(condition.charAt(0) + " = " + condition.charAt(0) + " + 1");
            } else {
                generateMap(action);
                this.forActions = new codigoIntermedioOperacion(instructionMap);
                this.forActions.runProcess();
                System.out.println(condition.charAt(0) + " = " + condition.charAt(0) + " - 1");
            }

            System.out.println("jmp l0 \nlabel l1");
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
