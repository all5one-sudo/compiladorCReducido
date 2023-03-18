

grammar compilador;					//! indica que es un archivo de gramatica, o analisis lexico combinados




@header {
package compiladores;
}

//?%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// ? Comienza el analizador Lexico 
// ? REGLA LEXICA EN MAYUSCULA y entre corchetes la expresionregular que encuentra esa secuencia de caracteres


fragment LETRA: [A-Za-z];
fragment DIGITO:[0-9];

/* 		La palabra reservada "FRAGMENT" significa que no quiero que la expresion regular DIGITO sea un 
 		TOKEN sino que solo quiero que el conjungo de los DIGITOS sea un token (osea para formar tokens 
 		con su concatenacion) 
*/

PA: '(';
PC: ')';
LLA: '{';
LLC: '}';
PYC: ';';
SUMA: '+';
MULT: '*';
DIVI: '/';
RESTA: '-';

MAIN: 'main';
IF:	'if';
INT: 'int';
STRING: 'string';
BOOLEAN: 'bool';
CHAR: 'char';
FLOAT: 'float';
DOUBLE: 'double';
FALSE: 'false';
TRUE: 'true';

//! si dos reglas son aplicables para la misma secuancia de caracteres, "gana" la que primero se definio


//? Click derecho "Show Railroad Diagrama for Rule" para ver el arbol para reglas lexicas ? este 
//? arbol nos muestra como avanza nuestro automata dependiendo de las posibles entradas


ID: (LETRA | '_') (LETRA | DIGITO | '_')*; //! ID = IDentificador
NUMERO: DIGITO+;


TEXTO: '"' (LETRA | '_') (LETRA | DIGITO | '_')* '"';

CARACTER: '\'' (LETRA) '\'';

WS:
	[ \n\t\r] -> skip; //! si encuentra alguno de esos caracteres, hace salto "skip"

OTRO: .;

//?	 Finaliza el analizador Lexico 

//?%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

tipo: INT | STRING | BOOLEAN | CHAR | FLOAT | DOUBLE;
logica:
	'<'
	| '>'
	| '<='
	| '>='
	| '!='
	| '==';				 //! expresado como reg gramat

incremento:
	ID '+=' NUMERO
	| ID '++'
	| ID '--'
	| ID '-=' NUMERO
	| ID '+=' ID
	| ID '-=' ID;


//?%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

//? Esto a continuacion, es el analizador gramatical (o sintactico)

//?  								Se define la siguiente regla sintactica (gramatical) "Programa" 
//? 								basicamente indica que un programa es una sucesion de instrucciones
programa: instrucciones EOF; 									//?sucesion hasta que no haya mas instrucciones

//? EOF : End Of File (Final de Archivo)

instrucciones: instruccion* 
		|;

//! ahora defino las instrucciones NO PUEDO nombrar instrucciones de la misma 
//! forma que palabras reservadas de un lenguaje, ya que habria conflicto

instruccion:
	principal
	| inst_if
	| asignacion
	| bloque
	| incremento
	| ireturn
	| declaracion
	| inst_for
	| inst_while
	| inst_else
	; 

//! Recordar FACTOR -> Parte separada por mult o div ej 5*3 TERMINOS -> Parte separada por suma y/o resta ej 5+3

										//?Arbol Gramatical

										//?? Ascendete -> de las hojas a la raiz 
										//?? Descendente -> de la raiz a las hojas (es el que usa ANTLR )

principal: INT MAIN PA PC bloque;

bloque: LLA instrucciones LLC;

asignacion:
	 ID '=' exp PYC      
	| ID '=' exp ','
	| ID '=' ID PYC
	| ID '=' ID ','
	| ID '=' exp 
	| ID '=' exp ',' asignacion
	| ID '=' llamada_func
	;

declaracion:
	tipo ID PYC 
	| tipo asignacion declaracion 
	| tipo ID ',' declaracion 
	| ID PYC
	| ID ',' declaracion
	| tipo asignacion 
	| tipo declarar_func
	| tipo ID ',' asignacion
	;


opar:
	exp opar
	//!es una expresion seguida de otra expresion         
	//!es la que recorre TODAS las expresiones
	|; //! Puede no considir con nada

exp: term t; //!una expresion es la sucesion de terminos

term: factor f; //un termino es una sucesion de factores

//otro ejemplo de factor es lo que este entre parentesis

t:  SUMA term t //! t es la variable que nos sirve para iterar (o recurrir) 
   | RESTA term t
   |
   ; //! se que estoy sumando , y no restando

factor:
	NUMERO   
	| ID     
	| TEXTO  
	| CARACTER
	| PA exp PC; //!	controlo balance de parentesis

f: MULT factor f 
  |DIVI factor f
  | 
  ;

comparacion: ID logica exp | ID logica ID;

inst_if:
	IF PA comparacion PC instruccion inst_else
	| IF PA comparacion PC instruccion;

inst_else: 'else' instruccion | 'else' inst_if;

inst_while: 'while' PA exp PC instruccion;

inst_for:
	'for' PA ID '=' (NUMERO | ID)  ';' comparacion ';' (ID '=' ID ('+' | '-') NUMERO) PC bloque
	;

ireturn:
	'return' ID PYC
	| 'return' NUMERO PYC
	| 'return' TEXTO
	;

declarar_func:
	| ID  PA tipo declarar_func
	| ','  tipo  declarar_func
	| ','  tipo PC PYC
	;

llamada_func:
	ID PA (NUMERO | ID) PC PYC
	| ID PA (NUMERO | ID) (',' (NUMERO | ID)) PC PYC
	;
