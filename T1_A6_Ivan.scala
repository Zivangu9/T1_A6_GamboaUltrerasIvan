//Call by name and call by value
/*
Ejercicio

1. Llenar archivo de forma aleatoria con lineas de 1 millon de caracteres y 1 millon de lineas, los caracteres son 0 y 1
2. Leer el archivo y buscar en cada linea patrones de 100 1's juntos
3. Implementar funciones CBV y CBN para medir los tiempos de analisis

*/
import java.io._
import scala.io.StdIn._
import scala.collection.mutable.ArrayBuffer
object Prueba{	
	def main(args: Array[String]): Unit ={
        val size = 1000000
        val patron = 100
        var flag = true;
        while(flag){
            print("¿Deseas llenar el archivo con nuevos datos?(0=No,1=Si)")
            var opcion = readInt()
            if(opcion==1){
                println("Llenando archivo de "+size+"x"+size)
                llenarArchivo(size,size)
                println("Archivo llenado")
                flag = false
            }
            else if(opcion==0)
                flag = false  
        }
        var time = System.currentTimeMillis()
        println("Patrones: "+iniciarAnalisisValue(leerDatos,patron))
        time = System.currentTimeMillis()-time
        println("Tiempo: "+time+" milisegundos")
        time = System.currentTimeMillis()
        println("Patrones: "+iniciarAnalisisName(leerDatos,patron))
        time = System.currentTimeMillis()-time
        println("Tiempo: "+time+" milisegundos")
        
	}
	/*
    	"var: => Int" Por Nombre
    */
    def llenarArchivo(columnas:Int,filas:Int): Unit = {
            val data = ArrayBuffer[String]()
            for(y<-0 until filas)
                data += generarLinea(columnas)
            printToFile(new File("imagen.txt")) { p =>
            data.foreach(p.println)
        }
    }
    def generarLinea(len:Int): String = {
        val r = scala.util.Random
        var salida = ""
        for(x<-0 until len){
            salida += r.nextInt(2)
        }
        salida
    }
    def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit): Unit = {
      val p = new java.io.PrintWriter(f)
      try { op(p) } finally { p.close() }
    }
    def leerDatos(): Iterator[String] = {
        scala.io.Source.fromFile("imagen.txt").getLines
    }
    def iniciarAnalisisValue(datos : Iterator[String],patron:Int): Int = {
        println("Iniciando análisis Value")
        var cont = 0;
        var res = 0;
        for(linea<-datos){
            for(dato<-linea){
                if(cont==patron)
                    res+=1
                if(dato=='1'){
                    cont+=1
                }
                else{
                    cont = 0
                }
            }
        }
        res
    }
    def iniciarAnalisisName(datos : => Iterator[String],patron:Int): Int = {
        println("Iniciando análisis Name")
        var cont = 0;
        var res = 0;
        for(linea<-datos){
            for(dato<-linea){
                if(cont==patron)
                    res+=1
                if(dato=='1'){
                    cont+=1
                }
                else{
                    cont = 0
                }
            }
        }
        res
    }
}