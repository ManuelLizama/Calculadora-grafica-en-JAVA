

package example;

import java.util.Vector;
/**
 * @author Edmond
 * Comentarios a edmond.duke.developer@gmail.com
 * http://softcx.blogspot.com
 * Basado en el algoritmo shunting yard
 */
public class MakePostFijo2 {
    private Vector<String> rpn;
    private String[] cadena;


    public MakePostFijo2(String[] cadenaInfija){
        cadena = cadenaInfija;
        rpn = new Vector();
        creaPostFijo();
    }
    private void creaPostFijo(){ //La cadena infija debe ser correcta
        myStack2 pila = new myStack2(cadena.length);
        int i,sim;
        i = 0;
        while(i < cadena.length){
            String s= cadena[i];
            sim = getPriori(s);
            if(sim == 0){ //Es número
                rpn.add(s);
            }else{ //Es operador
                while(pila.isEmpty() == false && Precedencia(pila.StackTop() ,s) && sim > 0) // Falta √ y ^ de derecha a izquierda
                    rpn.add(pila.pop());
                if(sim == -2){ //Si encuentra signo agrupacion que cierra
                    while(getPriori(pila.StackTop()) != -1) //Hasta que encuentre signo agrupación que abre
                        rpn.add(pila.pop());
                    pila.pop(); // quita "(" de la pila
                }else
                    pila.push(s);
            }
            i++;
        }
        while(pila.isEmpty() == false)
            rpn.add(pila.pop());
    }
    public String get(int index){
        return rpn.get(index);
    }
    public int Size(){
        return rpn.size();
    }
    public boolean Precedencia(String strPila,String str){ //Corregir al trabajar con ^
        int p1 = getPriori(strPila);                          // es asociativo por la derecha
        int p2 = getPriori(str);
        return p1 >= p2;  
    }
    private static int getPriori(String str){
        int i=0;
        if(str.equals("-"))
            i = 1;
        if(str.equals("+"))
            i = 1;
        if(str.equals("*"))
            i = 2;
        if(str.equals("/"))
            i = 2;
        if(str.equals("^"))
            i = 3;
        if(str.equals("√"))
            i = 3;
        if(str.equals("!"))
            i = 4;
        if(str.equals("("))
            i = -1;
        if(str.equals("["))
            i = -1;
        if(str.equals("{"))
            i = -1;
        if(str.equals(")"))
            i = -2;
        if(str.equals("]"))
            i = -2;
        if(str.equals("}"))
            i = -2;
        return i;
    }
    public float getValor(){
        myStack2 pila = new myStack2(cadena.length);
        String s,valor;
        float op1,op2;
        int i,simbol;
        //simbol = 0;
        for(i = 0; i < rpn.size(); i++){
            s = rpn.get(i);
            simbol = getPriori(s);
            if(getPriori(s) == 0){ //es número
                pila.push(s);
            }else{
                //try{
                if(simbol == 4){  //Es factorial{
                    op1 = Float.valueOf(pila.pop());
                    //if(getDecimal(op1) == 0){
                        valor = String.valueOf(factorial((int)op1));
                        pila.push(valor);
                    //else
                        //error++;
                }else{
                    op2 = Float.valueOf(pila.pop());
                    op1 = Float.valueOf(pila.pop());
                    valor = operar(s,op1,op2);
                    pila.push(valor);
                }

            }
        }
        return Float.valueOf(pila.pop());
    }
    private static String operar(String str, float op1, float op2){
        float valor = 0;
        if(str.equals("+"))
            valor = op1 + op2;
        if(str.equals("-"))
            valor = op1 - op2;
        if(str.equals("*"))
            valor = op1*op2;
        if(str.equals("/")){
            //if(op2 != 0)
                valor = op1 / op2;
            //else
                //xerror++;
        }
        if(str.equals("^"))
            valor = (float) Math.pow(op1,op2);
        if(str.equals("√"))
            valor = (float) Math.pow(op2, 1/op1);
        return String.valueOf(valor);
    }

    private static float factorial(int num){
          float x = 1;
          int i;
          for (i = 1; i < num; i++)
            x = x * (i+1);
          return x;
    }
}
