package Model;

public class NonexistentGameSimbolExceptions extends Exception{

   NonexistentGameSimbolExceptions() {
       super("Выбран несуществующий символ для этой игры! Разрешено играть только X или O.");
   }

}
