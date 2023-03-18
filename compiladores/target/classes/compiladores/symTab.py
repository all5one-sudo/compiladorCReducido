import pandas as pd
import dataframe_image as dfi

# Se abre el archivo de texto que incluye el String de la tabla de simbolos
fin = open("/Users/federicovillar/Downloads/proyecto_final_compiladores_Definitivo/Tabla_de_Simbolos.txt")
fout = open("/Users/federicovillar/Downloads/proyecto_final_compiladores_Definitivo/tablaSimbolos.txt", "wt")
# Se reemplazan los 'null' por 'Null', con el objetivo de interpretarlos correctamente en el dataFrame
for line in fin:
	fout.write(line.replace('null', 'Null'))
# Se cierran los archivos
fin.close()
fout.close()
# Se lee el nuevo archivo de texto y se crea un dataframe de Pandas
df = pd.read_table('tablaSimbolos.txt',sep='&')
#Se exporta el dataFrame como png
dfi.export(df, '/Users/federicovillar/Downloads/proyecto_final_compiladores_Definitivo/compiladores/src/main/java/compiladores/TablaSimbolos.png')