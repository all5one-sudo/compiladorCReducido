// Generated from c:\tpFinalCompiladores\proyecto_final_compiladores_Definitivo\compiladores\src\main\java\compiladores\compilador.g4 by ANTLR 4.9.2

package compiladores;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link compiladorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface compiladorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link compiladorParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(compiladorParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogica(compiladorParser.LogicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#incremento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncremento(compiladorParser.IncrementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(compiladorParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#instrucciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstrucciones(compiladorParser.InstruccionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(compiladorParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#principal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrincipal(compiladorParser.PrincipalContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(compiladorParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(compiladorParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(compiladorParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#opar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpar(compiladorParser.OparContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(compiladorParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(compiladorParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#t}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitT(compiladorParser.TContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(compiladorParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF(compiladorParser.FContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#comparacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparacion(compiladorParser.ComparacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#inst_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInst_if(compiladorParser.Inst_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#inst_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInst_else(compiladorParser.Inst_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#inst_while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInst_while(compiladorParser.Inst_whileContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#inst_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInst_for(compiladorParser.Inst_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#ireturn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIreturn(compiladorParser.IreturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#declarar_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarar_func(compiladorParser.Declarar_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladorParser#llamada_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamada_func(compiladorParser.Llamada_funcContext ctx);
}