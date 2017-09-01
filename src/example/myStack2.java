
package example;
/**
 * @author Edmond
 * Comentarios a edmond.duke.developer@gmail.com
 * http://softcx.blogspot.com
 * Clase pila o stack
 */
public class myStack2 {
    private String[] pila;
    private int tope;

    public myStack2(int capacidad){
        pila = new String[capacidad];
        tope =  -1;
    }
    public boolean isEmpty(){
        return tope == -1;
    }
    public void push(String str){
        if(tope + 1 < pila.length)
            pila[++tope] = str;
    }
    public String pop(){
        if(isEmpty())
                return "";
        return pila[tope--];
    }
    public String StackTop(){
        return pila[tope];
    }
}
