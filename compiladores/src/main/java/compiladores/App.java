package compiladores;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;

import java.util.ArrayList;

import java.io.*; // Para imprimir

public class App {

    public static void main(String[] args) throws Exception {
        // create a CharStream that reads from file
        // CharStream input = CharStreams.fromFileName("src/entrada.txt");
        CharStream input = CharStreams.fromFileName("input/miprograma.c");

        // create a lexer that feeds off of input CharStream
        compiladorLexer lexer = new compiladorLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        compiladorParser parser = new compiladorParser(tokens);

        // create Listener
        escucha escucha = new escucha();

        // Conecto el objeto con Listeners al parser
        parser.addParseListener(escucha); // !le digo que cuando hay eventos del arbol le diga a los listener
                                          // !correspondientes

        // ! Solicito al parser que comience indicando una regla gramatical
        // ! En este caso la regla es el simbolo inicial

        ParseTree tree = parser.programa(); // lo que retorna el metodo programa lo guardo en el arbol

        // Imprime el arbol obtenido
        System.out.println(tree.toStringTree(parser));

        // Conectamos el visitor
        caminante walker = new caminante();
        walker.visit(tree); // ! le decimos al visitor que visite el arbol

        System.out.println("\n\n" + walker + " <-- toString default de los  Objetos ");

        System.out.println(walker.getTS().ImprimirTS());

        File f = new File("Tabla_de_Simbolos.txt");

        // ! El siguiente Codigo es solo para generar "Tabla_de_Simbolos.txt"
        // !
        // ////////////////////////////////////////////////////////////////////////////////
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            String aux = walker.getTS().ImprimirTS();
            aux = aux.replace(";", "");
            wr.write(aux);
            wr.close();
            bw.close();
        } catch (IOException e) {
        }

        // ! El siguiente Codigo es solo para generar "Tabla de Simbolos.PNG"
        // !
        // ////////////////////////////////////////////////////////////////////////////////

        String Script_Path = "/Users/federicovillar/Downloads/proyecto_final_compiladores_Definitivo/compiladores/src/main/java/compiladores/symTab.py";
        ProcessBuilder Process_Builder = new ProcessBuilder("python", Script_Path).inheritIO();

        Process Demo_Process = Process_Builder.start();
        Demo_Process.waitFor();

        BufferedReader Buffered_Reader = new BufferedReader(
                new InputStreamReader(
                        Demo_Process.getInputStream()));
        String Output_line = "";

        while ((Output_line = Buffered_Reader.readLine()) != null) {
            System.out.println(Output_line);
        }
        // !
        // ////////////////////////////////////////////////////////////////////////////////

        HashMap<String, String> mapa = new HashMap<>(); // mapa inicial que obtendra lo necesario para generar codigo
                                                        // intermedio de Asignaciones

        walker.getTS().CopiarMapa(mapa);

        walker.getTS().ReemplazarVariableMAPA(mapa);

        ///// ESTA PARTE SOLO SIRVE PARA DESMENUZAR LA PARTES DE DECLARACION CON LA DE
        ///// LOS BLOQUES IF ELSE O WHILE

        ///////////////////////////////////////////////

        HashMap<String, String> mapadefinitivo = new HashMap<>();

        String palabraClave = "";

        if (tree.toStringTree(parser).contains("(instruccion (inst_if")) {

            palabraClave = "(instruccion (inst_if";

        }

        else if (tree.toStringTree(parser).contains("(instruccion (inst_else")) {
            palabraClave = "(instruccion (inst_else";
        } else if (tree.toStringTree(parser).contains("(instruccion (inst_while")) {
            palabraClave = "(instruccion (inst_while";
        } else if (tree.toStringTree(parser).contains("(instruccion (inst_for")) {
            palabraClave = "(instruccion (inst_for";
        }

        String NOIgnorarIF_ELSE_WHILE_FOR = "";

        NOIgnorarIF_ELSE_WHILE_FOR = tree.toStringTree(parser).substring(0,
                tree.toStringTree(parser).indexOf(palabraClave));

        for (String s : mapa.keySet()) {

            if (NOIgnorarIF_ELSE_WHILE_FOR.contains(" " + s + " ")) {
                mapadefinitivo.put(s, mapa.get(s));
            }
        }

        ///////////////////////////////////////////////

        System.out.println("Salida de Codigo Intermedio:\n");

        if (mapadefinitivo.size() == 0) {
            codigoIntermedioOperacion codigoOperacion = new codigoIntermedioOperacion(mapa);
            codigoOperacion.runProcess();
        } else {
            codigoIntermedioOperacion codigoOperacion = new codigoIntermedioOperacion(mapadefinitivo);
            codigoOperacion.runProcess();
        }

        // ? Codigo Intermedio de Operaciones Aritmetícas

        // codigoIntermedioOperacion codigo = new codigoIntermedioOperacion(mapa);
        // System.out.println("Prueba Codigo Intermedio:");
        // System.out.println("----------------");
        // codigo.runProcess();
        //
        // printMapa ! co ladores.caminante@57baeedf -> Entro y no se lo que hay
        // ! -> que hay

        // String pruebaWhile = "while (i <= n) {a = b + 1;i++;}";
        // codigoIntermedioWhile codigoWhile = new codigoIntermedioWhile(pruebaWhile);
        // codigoWhile.runProcess();

        // ? Codigo Intermedio de if

        String TACif = escucha.getStringTAC();
        String TACelse = escucha.getStringTACELSE();
        String TACwhile = escucha.getStringTACWHILE();
        String TACfor = escucha.getStringTACFOR();

        String Tacif = TACif + TACelse;

        // System.out.print("aaaaaaaaaaaa " + escucha.getOrdendeImpresion().size());

        ArrayList<String> instructions = escucha.getOrdendeImpresion();
        instructions.add("");
        // escucha.getOrdendeImpresion() //arraylist

        for (String instru : instructions) {
            switch (instru) {
                case "TACif":
                    codigoIntermedioIf codigoIf = new codigoIntermedioIf(Tacif);
                    codigoIf.runProcess();
                    codigoIf = null;
                    break;
                case "TACelse":
                    break;
                case "TACwhile":
                    codigoIntermedioWhile codigoWhile = new codigoIntermedioWhile(TACwhile);
                    codigoWhile.runProcess();
                    codigoWhile = null;
                    break;
                case "TACfor":
                    codigoIntermedioFor codigoFor = new codigoIntermedioFor(TACfor);
                    codigoFor.runProcess();
                    codigoFor = null;
                    break;
                default:
                    break;
            }

        }

        // String TACelse=

        // System.out.println("Ver ahora");
        // HashMap<String, String> mapaxd = new HashMap<>();
        // mapaxd.put("a", "3");
        // codigoIntermedioOperacion codi = new codigoIntermedioOperacion(mapaxd);
        // codi.runProcess();

        /*
         * System.out.println("Prueba if:");
         * codigoIntermedioIf codigoIf = new codigoIntermedioIf(TACif + TACelse);
         * codigoIf.runProcess();
         * 
         * System.out.println("Prueba while:");
         * codigoIntermedioWhile codigoWhile = new codigoIntermedioWhile(TACwhile);
         * codigoWhile.runProcess();
         * 
         * System.out.println("Prueba for:");
         * codigoIntermedioFor codigoFor = new codigoIntermedioFor(TACfor);
         * codigoFor.runProcess();
         */

    }

}