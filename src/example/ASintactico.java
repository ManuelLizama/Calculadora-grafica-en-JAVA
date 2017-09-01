
package example;

public class ASintactico {
    private int tipo,error;
    private myStack2 pila;

    public ASintactico(int tamano){
        tipo = -1;
        error = 0;
        pila = new myStack2(tamano);
    }
    
    public void add(char c){ //Falta optimizar
        String s = String.valueOf(c);
        char pi;
        if(c == '(' || c == '[' || c == '{'){ //Se verifica los paréntesis
            pila.push(s);
        }else{
            if(c == ')' || c == ']' || c == '}'){
               if(pila.isEmpty())
                    error++;
               else{
                   pi = pila.pop().charAt(0);
                   if( ((pi == '(' && c == ')') || (pi == '[' && c == ']') || ( pi == '{' && c == '}')) == false )
                       error++;
               }
            }
        }
    }
    public boolean isEmpty(){
        return pila.isEmpty();
    }
    public void setType(int xtipo){
        if(tipo == 1){
            if(xtipo != 0)
                error++; //Si luego de "." no sigue un número
        }
         if(tipo == 4)   {
             if(xtipo == 4)
                 error++; //Si agrega dos operadores seguidos
         }
        tipo = xtipo;
    }
    public int getError(){
        return error;
    }
}
