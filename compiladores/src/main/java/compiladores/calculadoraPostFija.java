
//? Esta clase solo sirve para resolver expresiones matematicas en el instruccion de "Asignacion"

package compiladores;

import java.util.Stack;

public class calculadoraPostFija {

  // ! Retorna True , en el caso que el caracter sea un "Operador"
  public boolean esOperador(char c) {
    if (c == '=' || c == '!' || c == '>' || c == '<' || c == '+' || c == '-' || c == '*' || c == '/' || c == '('
        || c == ')')
      return true;

    return false;
  }

  // ! Retorna un entero, el cual representa que se resuelve primero en una
  // ! operacion Aritmetica

  private int precedencia(char x) {
    int prec = 0;
    switch (x) {
      case '<':
        prec = 0;
        break;

      case '>':
        prec = 0;
        break;

      case '!':
        prec = 0;
        break;

      case '=':
        prec = 0;
        break;
      case '+':
        prec = 1;
        break;

      case '-':
        prec = 2;
        break;

      case '*':
        prec = 3;
        break;

      case '/':
        prec = 4;
    }

    return prec;
  }

  // ! Retorna un String, el cual representa la representacion en PostFija de una
  // ! entrada Infija
  // ! ej: Entrada: 2+(3*5) --> Salida: 35*2+

  public String PostFija(String infija) {

    char[] inf = new char[infija.length() + 1];
    char[] post = new char[infija.length() + 1];
    char[] pila = new char[infija.length() + 1];

    int ptr = 0;
    String POSTFIJA;

    int j = 0;

    if (infija.length() != 1) {
      for (int i = 0; i < infija.length(); i++) {
        inf[i] = infija.charAt(i);
      }

      for (int i = 0; inf[i] != '\0'; i++) {
        if (!esOperador(inf[i])) {
          post[j] = inf[i];
          j++;
        } else if (inf[i] == '(') {
          pila[++ptr] = '(';
        } else if (inf[i] == ')') {
          while (pila[ptr] != '(' && ptr > 0) {
            post[j] = pila[ptr--];
            j++;
          }
          if (pila[ptr] == '(')
            ptr--;
        } else {
          if (precedencia(inf[i]) != 0) {
            while (precedencia(inf[i]) < precedencia(pila[ptr]) && pila[ptr] != '(' && ptr > 0) {
              post[j] = pila[ptr--];
              j++;
            }
            pila[++ptr] = inf[i];
          } else {

          }

        }
      }

      while (ptr > 0) {
        post[j] = pila[ptr--];
        j++;
      }
      post[j] = '\0';

      j = 0;
      ptr = 0;

      POSTFIJA = String.valueOf(post);
      return POSTFIJA;
    } else {
      return infija;
    }
  }

  public String Calcular_POSTFIJA(TablaSimbolos tabla, String Postfija) {

    // ! Entrada (Expresión en Postfija)
    // ! Entrada "2 23 6 + * 1 -" --> Salida "2*(23+6)-1"
    String[] post = Postfija.split(" ");

    // Declaración de las pilas
    Stack<String> E = new Stack<String>(); // Pila entrada
    Stack<String> P = new Stack<String>(); // Pila de operandos

    // Añadir post (array) a la Pila de entrada (E)
    for (int i = post.length - 1; i >= 0; i--) {

      if (tabla.buscarSimboloLocal(post[i]) == null && tabla.buscarSimbolo(post[i]) == null) {
        E.push(post[i]);
        System.out.println(post[i]);

      } else if (tabla.buscarSimboloLocal(post[i]) != null) {
        E.push(tabla.buscarSimboloLocal(post[i]).getValor());
        tabla.buscarSimboloLocal(post[i]).setUsado(true);

      } else if (tabla.buscarSimbolo(post[i]) != null) {

        E.push(tabla.buscarSimbolo(post[i]).getValor());
        tabla.buscarSimboloLocal(post[i]).setUsado(true);

      }

    }

    // ! Algoritmo de Evaluación Postfija
    String operadores = "+-*/%";
    while (!E.isEmpty()) {
      if (operadores.contains("" + E.peek())) {
        P.push(evaluar(E.pop(), P.pop(), P.pop()) + "");
      } else {
        P.push(E.pop());
      }
    }

    // ! Mostrar resultados:
    // System.out.println("La expresion infija ingresada es:" + Postfija);
    // System.out.println("El resultado es: " + P.peek());

    return P.peek();

  }

  private static int evaluar(String op, String n2, String n1) {
    int num1 = Integer.parseInt(n1);
    int num2 = Integer.parseInt(n2);
    if (op.equals("+"))
      return (num1 + num2);
    if (op.equals("-"))
      return (num1 - num2);
    if (op.equals("*"))
      return (num1 * num2);
    if (op.equals("/"))
      return (num1 / num2);
    if (op.equals("%"))
      return (num1 % num2);
    return 0;
  }

}
