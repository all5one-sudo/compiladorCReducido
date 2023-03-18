// Esta clase va a heredar de la de compiladorBaseVisitor.javapara poder utilizar sus interfaces

package compiladores;

import org.antlr.v4.runtime.tree.TerminalNode;

import compiladores.compiladorParser.*;

import java.util.HashMap;

public class caminante extends compiladorBaseVisitor<Object> {

    private TablaSimbolos TS;
    private String DeclaracionReciente;
    private HashMap<String, String> MapaEntradaTAC;

    // ! Es el visitor

    public caminante() {
        TS = new TablaSimbolos();
        DeclaracionReciente = "NUEVO";
    }

    public HashMap<String, String> getMapaEntradaTAC() {
        return MapaEntradaTAC;
    }
  

    @Override
    public Object visitBloque(BloqueContext ctx) {

        Object o = super.visitBloque(ctx);

        TS.addContexto();

        return o;

    }

    @Override
    public Object visitPrograma(ProgramaContext ctx) {

        System.out.println("Comienza visita del arbol");
        Object o = super.visitPrograma(ctx); // si no uso el visitPrograma, tomo el control de visitar lo que quiera
        System.out.println("Fin de la visita del arbol");
        return o;
    }

    @Override
    public Object visitTerminal(TerminalNode node) {

        System.out.println("\tHoja contiene |" + node.getText() + "|");
        return super.visitTerminal(node);

    }

    @Override
    public Object visitAsignacion(AsignacionContext ctx) {

        calculadoraPostFija calcu = new calculadoraPostFija();

        boolean isOperacion = false;
        String Postfija = "";

        String PostfijaSeparada = "";

        System.out.println("Se hizo la Asignacion " + ctx.getText());
        System.out.println("La declaracion reciente " + DeclaracionReciente);
        ////////////////////////////////////////////

        if (ctx.getChild(2).getText().contains("+") || ctx.getChild(2).getText().contains("*")) {
            isOperacion = true;

        } else {
            isOperacion = false;
        }

        if (TS.buscarSimboloLocal(ctx.getChild(0).getText()) != null) {

            System.out.println("Entre 1");

            TS.buscarSimboloLocal(ctx.getChild(0).getText()).setInicializado(true);

            if (!isOperacion) {
                TS.buscarSimboloLocal(ctx.getChild(0).getText()).setValor(ctx.getChild(2).getText());
            } else {
                Postfija = calcu.PostFija(ctx.getChild(2).getText());

                PostfijaSeparada = "";

                for (int i = 0; i < Postfija.length(); i++) {
                    PostfijaSeparada += Postfija.charAt(i) + " ";
                }

                TS.buscarSimboloLocal(ctx.getChild(0).getText())
                        .setValor(calcu.Calcular_POSTFIJA(getTS(), PostfijaSeparada.trim()));
                TS.buscarSimboloLocal(ctx.getChild(0).getText()).setExpresion(ctx.getChild(2).getText());

                isOperacion = false;
            }

        } else if (TS.buscarSimbolo(ctx.getChild(0).getText()) != null) {

            System.out.println("Entre 2");
            TS.buscarSimbolo(ctx.getChild(0).getText()).setInicializado(true);
            if (!isOperacion) {
                TS.buscarSimboloLocal(ctx.getChild(0).getText()).setValor(ctx.getChild(2).getText());
            } else {
                Postfija = calcu.PostFija(ctx.getChild(2).getText());

                PostfijaSeparada = "";

                for (int i = 0; i < Postfija.length(); i++) {
                    PostfijaSeparada += Postfija.charAt(i) + " ";
                }

                TS.buscarSimboloLocal(ctx.getChild(0).getText())
                        .setValor(calcu.Calcular_POSTFIJA(getTS(), PostfijaSeparada.trim()));
                TS.buscarSimboloLocal(ctx.getChild(0).getText()).setExpresion(ctx.getChild(2).getText());

                isOperacion = false;
            }
        }

        else if (TS.buscarSimboloLocal(ctx.getChild(0).getText()) == null
                && TS.buscarSimbolo(ctx.getChild(0).getText()) == null) {

            ///////////////////////////////////////////////////////////

            for (int i = 0; i < ctx.getChildCount(); i++) {
                System.out.println(" ==   Hijo " + i + "  -> " + ctx.getChild(i).getText());


                if (ctx.getChild(0).getText().contains("int")) {
                    System.out.println("Entre 3");
                    setDeclaracionReciente(ctx.getChild(0).getText());
                    if (!(ctx.getChild(1).getText().contains("="))) {

                        if (!isOperacion) {
                            Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.INT, false, false,
                                    ctx.getChild(2).getText());
                            TS.addSimbolo(idagregado);
                        } else {
                            Postfija = calcu.PostFija(ctx.getChild(2).getText());

                            PostfijaSeparada = "";

                            for (int k = 0; k < Postfija.length(); k++) {
                                PostfijaSeparada += Postfija.charAt(i) + " ";
                            }

                            isOperacion = false;

                            Variable idagregado = new Variable(ctx.getChild(1).getText(), TipoDato.INT, false, false,
                                    calcu.Calcular_POSTFIJA(getTS(), PostfijaSeparada.trim()),
                                    ctx.getChild(2).getText());

                            TS.addSimbolo(idagregado);
                            setDeclaracionReciente(ctx.getChild(0).getText());
                        }

                    }

                } else {
                }

                if (DeclaracionReciente.equals("int") && !ctx.getChild(0).getText().contains("int")) {
                    System.out.println("Entre 4");
                    if (!isOperacion) { // System.out.println("Entre 4 sjaddjsadjsajsadasd");
                        Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.INT, true, false,
                                ctx.getChild(2).getText());
                        TS.addSimbolo(idagregado);
                    } else {

                        Postfija = calcu.PostFija(ctx.getChild(2).getText());

                        PostfijaSeparada = "";

                        for (int k = 0; k < Postfija.length(); k++) {
                            PostfijaSeparada += Postfija.charAt(k) + " ";
                        }

                        isOperacion = false;

                        Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.INT, true, false,
                                calcu.Calcular_POSTFIJA(getTS(), PostfijaSeparada.trim()), ctx.getChild(2).getText());

                        TS.addSimbolo(idagregado);
                        setDeclaracionReciente(ctx.getChild(0).getText());

                    }

                }

                // ! ///////////////////////////////////////////////////////////

                if (ctx.getChild(0).getText().contains("double")) {

                    if (!(ctx.getChild(1).getText().contains("="))) {
                        Variable idagregado = new Variable(ctx.getChild(1).getText(), TipoDato.DOUBLE, false, false,
                                ctx.getChild(2).getText());

                        TS.addSimbolo(idagregado);
                        setDeclaracionReciente(ctx.getChild(0).getText());

                    }

                } else {
                }

                if (DeclaracionReciente.equals("double")) {

                    Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.DOUBLE, false, false,
                            ctx.getChild(2).getText());
                    TS.addSimbolo(idagregado);
                }

                // ! ///////////////////////////////////////////////////////////

                if (ctx.getChild(0).getText().contains("char")) {

                    if (!(ctx.getChild(1).getText().contains("="))) {
                        Variable idagregado = new Variable(ctx.getChild(1).getText(), TipoDato.CHAR, false, false,
                                ctx.getChild(2).getText());

                        TS.addSimbolo(idagregado);
                        setDeclaracionReciente(ctx.getChild(0).getText());

                    }

                } else {
                }

                if (DeclaracionReciente.equals("char")) {

                    Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.CHAR, false, false,
                            ctx.getChild(2).getText());
                    TS.addSimbolo(idagregado);
                }

                // ! ///////////////////////////////////////////////////////////

                if (ctx.getChild(0).getText().contains("bool")) {

                    if (!(ctx.getChild(1).getText().contains("="))) {
                        Variable idagregado = new Variable(ctx.getChild(1).getText(), TipoDato.BOOL, false, false,
                                ctx.getChild(2).getText());

                        TS.addSimbolo(idagregado);
                        setDeclaracionReciente(ctx.getChild(0).getText());

                    }

                } else {
                }

                if (DeclaracionReciente.equals("bool")) {

                    //
                    Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.BOOL, false, true,
                            ctx.getChild(2).getText());
                    TS.addSimbolo(idagregado);
                }

                // ! ///////////////////////////////////////////////////////////

            }

            //////////////////////////////////////////////////////////

        } else {
        }

        if (DeclaracionReciente.equals("string") && !ctx.getChild(0).getText().contains("string")) {
            System.out.println("Entre 4");
            // System.out.println("Entre 4 sjaddjsadjsajsadasd");
            Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.STRING, true, false,
                    ctx.getChild(2).getText());
            TS.addSimbolo(idagregado);

        }

        // ! ///////////////////////////////////////////////////////////

        ////////////////////////////////////////////

        return super.visitAsignacion(ctx);
    }

    @Override
    public Object visitDeclaracion(DeclaracionContext ctx) {

        System.out.println("Comienza la declaracion " + ctx.getText());

        for (int i = 0; i < ctx.getChildCount(); i++) {
            System.out.println(" ==   Hijo " + i + "  -> " + ctx.getChild(i).getText() + "  -> "
                    + ctx.getChild(i).getText().length());

            // ! CONSIDERAR FUNCIONES

            if (ctx.getChild(0).getText().contains("int")) {
                setDeclaracionReciente(ctx.getChild(0).getText());

                if (!(ctx.getChild(1).getText().contains("="))) {
                    Variable idagregado = new Variable(ctx.getChild(1).getText(), TipoDato.INT, false, false);

                    TS.addSimbolo(idagregado);
                    setDeclaracionReciente(ctx.getChild(0).getText());

                }

            } else {
            }

            if (DeclaracionReciente.equals("int") && !ctx.getChild(0).getText().contains("int")
                    && !ctx.getChild(0).getText().equals(",")) {

                //
                Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.INT, false, false);
                TS.addSimbolo(idagregado);
            }

            // ! ///////////////////////////////////////////////////////////

            if (ctx.getChild(0).getText().contains("double")) {
                setDeclaracionReciente(ctx.getChild(0).getText());

                if (!(ctx.getChild(1).getText().contains("="))) {
                    Variable idagregado = new Variable(ctx.getChild(1).getText(), TipoDato.DOUBLE, false, false);

                    TS.addSimbolo(idagregado);
                    setDeclaracionReciente(ctx.getChild(0).getText());

                }

            } else {
            }

            if (DeclaracionReciente.equals("double") && !ctx.getChild(0).getText().contains("double")
                    && !ctx.getChild(0).getText().equals(",")) {

                //
                Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.DOUBLE, false, false);
                TS.addSimbolo(idagregado);
            }

            // ! ///////////////////////////////////////////////////////////

            if (ctx.getChild(0).getText().contains("char")) {
                setDeclaracionReciente(ctx.getChild(0).getText());

                if (!(ctx.getChild(1).getText().contains("="))) {
                    Variable idagregado = new Variable(ctx.getChild(1).getText(), TipoDato.CHAR, false, false);

                    TS.addSimbolo(idagregado);
                    setDeclaracionReciente(ctx.getChild(0).getText());

                }

            } else {
            }

            if (DeclaracionReciente.equals("char")) {

                ////
                Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.CHAR, false, false);
                TS.addSimbolo(idagregado);
            }

            // ! ///////////////////////////////////////////////////////////

            if (ctx.getChild(0).getText().contains("bool")) {
                setDeclaracionReciente(ctx.getChild(0).getText());
                if (!(ctx.getChild(1).getText().contains("="))) {
                    Variable idagregado = new Variable(ctx.getChild(1).getText(), TipoDato.BOOL, false, false);

                    TS.addSimbolo(idagregado);
                    setDeclaracionReciente(ctx.getChild(0).getText());

                }

            } else {
            }

            if (DeclaracionReciente.equals("bool") && !ctx.getChild(0).getText().contains("bool")
                    && !ctx.getChild(0).getText().equals(",")) {

                ////
                Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.BOOL, false, false);
                TS.addSimbolo(idagregado);
            }

            // ! ///////////////////////////////////////////////////////////
            // ! ///////////////////////////////////////////////////////////

            if (ctx.getChild(0).getText().contains("string")) {
                setDeclaracionReciente(ctx.getChild(0).getText());
                if (!(ctx.getChild(1).getText().contains("="))) {
                    Variable idagregado = new Variable(ctx.getChild(1).getText(), TipoDato.STRING, false, false);

                    TS.addSimbolo(idagregado);
                    setDeclaracionReciente(ctx.getChild(0).getText());

                }

            } else {
            }

            if (DeclaracionReciente.equals("string") && !ctx.getChild(0).getText().contains("string")
                    && !ctx.getChild(0).getText().equals(",")) {

                ////
                Variable idagregado = new Variable(ctx.getChild(0).getText(), TipoDato.STRING, false, false);
                TS.addSimbolo(idagregado);
            }

            // ! ///////////////////////////////////////////////////////////

        }

        return super.visitDeclaracion(ctx);
    }

    @Override
    public Object visitInst_for(Inst_forContext ctx) {

        return super.visitInst_for(ctx);
    }

    @Override
    public Object visitInst_if(Inst_ifContext ctx) {

        System.out.println("Se ingresa al if" + ctx.getText());

        System.out.println("\tHoja contiene |" + ctx.getText() + "|");

        return super.visitInst_if(ctx);
    }

    public TablaSimbolos getTS() {
        return TS;
    }

    public void setDeclaracionReciente(String declaracionReciente) {
        DeclaracionReciente = declaracionReciente;
    }


}
