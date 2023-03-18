// Generated from c:\tpFinalCompiladores\proyecto_final_compiladores_Definitivo\compiladores\src\main\java\compiladores\compilador.g4 by ANTLR 4.9.2

package compiladores;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link compiladorParser}.
 */
public interface compiladorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link compiladorParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(compiladorParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(compiladorParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#logica}.
	 * @param ctx the parse tree
	 */
	void enterLogica(compiladorParser.LogicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#logica}.
	 * @param ctx the parse tree
	 */
	void exitLogica(compiladorParser.LogicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#incremento}.
	 * @param ctx the parse tree
	 */
	void enterIncremento(compiladorParser.IncrementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#incremento}.
	 * @param ctx the parse tree
	 */
	void exitIncremento(compiladorParser.IncrementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(compiladorParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(compiladorParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#instrucciones}.
	 * @param ctx the parse tree
	 */
	void enterInstrucciones(compiladorParser.InstruccionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#instrucciones}.
	 * @param ctx the parse tree
	 */
	void exitInstrucciones(compiladorParser.InstruccionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(compiladorParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(compiladorParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#principal}.
	 * @param ctx the parse tree
	 */
	void enterPrincipal(compiladorParser.PrincipalContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#principal}.
	 * @param ctx the parse tree
	 */
	void exitPrincipal(compiladorParser.PrincipalContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(compiladorParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(compiladorParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(compiladorParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(compiladorParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(compiladorParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(compiladorParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#opar}.
	 * @param ctx the parse tree
	 */
	void enterOpar(compiladorParser.OparContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#opar}.
	 * @param ctx the parse tree
	 */
	void exitOpar(compiladorParser.OparContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(compiladorParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(compiladorParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(compiladorParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(compiladorParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#t}.
	 * @param ctx the parse tree
	 */
	void enterT(compiladorParser.TContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#t}.
	 * @param ctx the parse tree
	 */
	void exitT(compiladorParser.TContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(compiladorParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(compiladorParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#f}.
	 * @param ctx the parse tree
	 */
	void enterF(compiladorParser.FContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#f}.
	 * @param ctx the parse tree
	 */
	void exitF(compiladorParser.FContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#comparacion}.
	 * @param ctx the parse tree
	 */
	void enterComparacion(compiladorParser.ComparacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#comparacion}.
	 * @param ctx the parse tree
	 */
	void exitComparacion(compiladorParser.ComparacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#inst_if}.
	 * @param ctx the parse tree
	 */
	void enterInst_if(compiladorParser.Inst_ifContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#inst_if}.
	 * @param ctx the parse tree
	 */
	void exitInst_if(compiladorParser.Inst_ifContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#inst_else}.
	 * @param ctx the parse tree
	 */
	void enterInst_else(compiladorParser.Inst_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#inst_else}.
	 * @param ctx the parse tree
	 */
	void exitInst_else(compiladorParser.Inst_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#inst_while}.
	 * @param ctx the parse tree
	 */
	void enterInst_while(compiladorParser.Inst_whileContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#inst_while}.
	 * @param ctx the parse tree
	 */
	void exitInst_while(compiladorParser.Inst_whileContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#inst_for}.
	 * @param ctx the parse tree
	 */
	void enterInst_for(compiladorParser.Inst_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#inst_for}.
	 * @param ctx the parse tree
	 */
	void exitInst_for(compiladorParser.Inst_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#ireturn}.
	 * @param ctx the parse tree
	 */
	void enterIreturn(compiladorParser.IreturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#ireturn}.
	 * @param ctx the parse tree
	 */
	void exitIreturn(compiladorParser.IreturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#declarar_func}.
	 * @param ctx the parse tree
	 */
	void enterDeclarar_func(compiladorParser.Declarar_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#declarar_func}.
	 * @param ctx the parse tree
	 */
	void exitDeclarar_func(compiladorParser.Declarar_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladorParser#llamada_func}.
	 * @param ctx the parse tree
	 */
	void enterLlamada_func(compiladorParser.Llamada_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladorParser#llamada_func}.
	 * @param ctx the parse tree
	 */
	void exitLlamada_func(compiladorParser.Llamada_funcContext ctx);
}