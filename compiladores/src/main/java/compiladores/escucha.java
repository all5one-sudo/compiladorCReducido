//?esta clase heredar de la de compiladorBaseListener.javapara poder utilizar sus 
//?interfaces, NO son clases SON interfaces, tiene metodos vacios

package compiladores;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

import compiladores.compiladorParser.AsignacionContext;
import compiladores.compiladorParser.BloqueContext;
import compiladores.compiladorParser.ExpContext;
import compiladores.compiladorParser.FContext;
import compiladores.compiladorParser.FactorContext;
import compiladores.compiladorParser.IncrementoContext;
import compiladores.compiladorParser.Inst_elseContext;
import compiladores.compiladorParser.Inst_forContext;
import compiladores.compiladorParser.Inst_ifContext;
import compiladores.compiladorParser.Inst_whileContext;
import compiladores.compiladorParser.OparContext;
import compiladores.compiladorParser.PrincipalContext;
import compiladores.compiladorParser.ProgramaContext;
import compiladores.compiladorParser.TContext;
import compiladores.compiladorParser.TermContext;

public class escucha extends compiladorBaseListener {
    private Integer contexto = 0;
    private String stringTACIF = "";
    private String stringTACELSE = "";
    private String stringTACWHILE = "";
    private String stringTACFOR = "";
    private ArrayList<String> OrdendeImpresion;
    ArrayList<String> ContestoInicial;
    

    public escucha() {

        this.ContestoInicial = new ArrayList<>();
        this.OrdendeImpresion = new ArrayList<>();
        this.OrdendeImpresion.add("");
      

    }

    // * Para agregar metodos, se hace click derecho y poner Acciones de Codigo
    // Fuente

    // ! los eventos del arbol son el enter y exit de una Regla gramatical

    @Override
    public void enterBloque(BloqueContext ctx) {

        // !cada vez que entro a una regla gramatical se produce el Enter
        // !Enter significa que es la primera vez que visito ese Nodo
        contexto++;
        // System.out.println("Nuevo contexto " + contexto);
        // System.out.println("_____________________________________________Entre a un
        // bloque ________________________________________________________"+ contexto);

        // Notifique = true;
    }

    @Override
    public void exitAsignacion(AsignacionContext ctx) {

        super.exitAsignacion(ctx);
    }

    @Override
    public void enterPrograma(ProgramaContext ctx) {
        System.out.println("________________________ Comienza el LISTENER___________________________");

        super.enterPrograma(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

        super.exitEveryRule(ctx);
    }


    @Override
    public void exitIncremento(IncrementoContext ctx) {

        super.exitIncremento(ctx);
    }

    @Override
    public void exitInst_else(Inst_elseContext ctx) {

        setStringTACELSE(ctx.getText());

        OrdenarArray("TACelse");

        super.exitInst_else(ctx);
    }

    @Override
    public void exitInst_for(Inst_forContext ctx) {

        setStringTACFOR(ctx.getText());

        OrdenarArray("TACfor");

        super.exitInst_for(ctx);
    }

    @Override
    public void exitInst_while(Inst_whileContext ctx) {

        setStringTACWHILE(ctx.getText());

        OrdenarArray("TACwhile");

        super.exitInst_while(ctx);
    }

    @Override
    public void exitPrincipal(PrincipalContext ctx) {

        super.exitPrincipal(ctx);
    }

    @Override
    public void exitBloque(BloqueContext ctx) {
        // !cada vez que salgo a una regla gramatical se produce el Exit
        // !Exit ocurre solamente cuando termino de recorrer todos los nodos hijos de
        // ese nodo
        System.out.println("Fin contexto " + contexto);
        contexto--;
    }

    @Override
    public void exitPrograma(ProgramaContext ctx) {
        contexto--;
        // System.out.println("Fin compilacion | " + ctx.getText() + "|");
        System.out.println("-----------------------TERMINA EL LISTENER--------------------------------");
    }

    @Override
    public void enterInst_if(Inst_ifContext ctx) {

        stringTACIF = "";

        super.enterInst_if(ctx);
    }

    @Override
    public void exitInst_if(Inst_ifContext ctx) {

        setStringTAC(ctx.getText());
        OrdenarArray("TACif");

        super.exitInst_if(ctx);
    }

    public void setStringTAC(String stringTAC) {
        this.stringTACIF = stringTAC;
    }

    public String getStringTAC() {
        return stringTACIF;
    }

    @Override
    public void visitErrorNode(ErrorNode node) {

        System.out.println(" ERROR -> " + node.getText());

        super.visitErrorNode(node);
    }

    @Override
    public void enterInst_else(Inst_elseContext ctx) {
        stringTACELSE = "";
        super.enterInst_else(ctx);
    }

    @Override
    public void enterInst_for(Inst_forContext ctx) {
        stringTACFOR = "";
        super.enterInst_for(ctx);
    }

    @Override
    public void enterInst_while(Inst_whileContext ctx) {
        stringTACWHILE = "";

        super.enterInst_while(ctx);
    }

    public void OrdenarArray(String index) {

        //System.out.println("AGREGUEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

        OrdendeImpresion.add(index);

    }

    public Integer getContexto() {
        return contexto;
    }

    public void setStringTACIF(String stringTACIF) {
        this.stringTACIF = stringTACIF;
    }

    public void setStringTACELSE(String stringTACELSE) {
        this.stringTACELSE = stringTACELSE;
    }

    public void setStringTACWHILE(String stringTACWHILE) {
        this.stringTACWHILE = stringTACWHILE;
    }

    public void setStringTACFOR(String stringTACFOR) {
        this.stringTACFOR = stringTACFOR;
    }

    public String getStringTACIF() {
        return stringTACIF;
    }

    public String getStringTACELSE() {
        return stringTACELSE;
    }

    public String getStringTACWHILE() {
        return stringTACWHILE;
    }

    public String getStringTACFOR() {
        return stringTACFOR;
    }

    public ArrayList<String> getOrdendeImpresion() {
        return OrdendeImpresion;
    }

    // ! en la interfaz hay otros metodos como el enterEveryRule el cual avisa
    // cuando salgamos de un nodo
    // ! sin importar de que regla sea , util para contar nodos y/o hacer pilas

    // ! visiterminal es un metodo recontra util, ya que te permite avisar cuando
    // llego a un token en especifico

    // ! visitError los nodos de tipo error son objetos especiales (se ven pintadas
    // de rojo en el arbol)

    // ! getCharPositionInLine() usar para saber en que linea esta el error desde
    // tantos caracteres en adelante
    // ! y getLine te devuelve que en que renglon esta el error
}
