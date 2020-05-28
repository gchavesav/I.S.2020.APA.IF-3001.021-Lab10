/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Profesor Lic. Gilberth Chaves Avila
 */
public class Utility {
    
    public static int random(){
        return 1+(int) Math.floor(Math.random()*999); 
    }
    
    public static int random(int bound){
        //return 1+random.nextInt(bound);
        return 1+(int) Math.floor(Math.random()*bound); 
    }
    
    public static String format(double value){
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }
    
    public static String $format(double value){
        return new DecimalFormat("$###,###,###,###.##")
                .format(value);
    }
     public static String perFormat(double value){
         //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }
     
     public static String dateFormat(Date date){
         return new SimpleDateFormat("dd/MM/yyyy").format(date);
     }
    
    public static String hhmmss(long start, long end){
        long milisegundos = end - start;
        long hora = milisegundos/3600000;
        long restohora = milisegundos%3600000;
        long minuto = restohora/60000;
        long restominuto = restohora%60000;
        long segundo = restominuto/1000;
        long restosegundo = restominuto%1000;
        
        return hora + ":" + minuto + ":" + segundo + "." + restosegundo;
    }
    
    public static String instanceOf(Object a, Object b){
        if(a instanceof Integer&&b instanceof Integer) return "integer";
        if(a instanceof String&&b instanceof String) return "string";
        if(a instanceof Character&&b instanceof Character) return "character";
        return "unknown"; //desconocido
    }
    
    public static String instanceOf(Object a){
        if(a instanceof Integer) return "integer";
        if(a instanceof String) return "string";
        return "unknown"; //desconocido
    }
    
    public static boolean equals(Object a, Object b){
        switch(instanceOf(a, b)){
            case "integer":
                Integer x=(Integer) a; Integer y=(Integer) b;
                return x==y;
            case "string":
                String v=(String) a; String w=(String) b;
                return v.compareToIgnoreCase(w)==0;
            case "character":
                Character c=(Character) a; Character d=(Character) b;
                return c.compareTo(d)==0;   
        }
        return false; //en cualquier otro caso
    }
    
    //less than (menorQ)
    public static boolean lessT(Object a, Object b){
        switch(instanceOf(a, b)){
            case "integer":
                Integer x=(Integer) a; Integer y=(Integer) b;
                return x<y;
            case "string":
                String v=(String) a; String w=(String) b;
                return v.compareToIgnoreCase(w)<0;
        }
        return false; //en cualquier otro caso
    }
    
    //greater than (mayorQ)
    public static boolean greaterT(Object a, Object b){
        switch(instanceOf(a, b)){
            case "integer":
                Integer x=(Integer) a; Integer y=(Integer) b;
                return x>y;
            case "string":
                String v=(String) a; String w=(String) b;
                return v.compareToIgnoreCase(w)>0;
        }
        return false; //en cualquier otro caso
    }
    
    public static char randAlphabet(){
        char alfabeto[] = new char[26] ;
        int cont=0;
        for(char i='a';i<='z';i++)
             alfabeto[cont++] = i; 
        return alfabeto[random(25)-1];
    }
    
    public static char randCharacter(){
        return (char)(random(91) + 65);
    }
    
     public static boolean isBetween(int value, int low, int high) {
        return low <= value && value <= high;
    }
     
    public static int getAge(Date date){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthday = LocalDate.parse(dateFormat(date), fmt);
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthday, now);
        return period.getYears();
    }
     
    public static String getFirstName(){
        String lista[] = {"Ana", "Pedro", "Maria", "Juan", "Marcela", "Carlos", "Laura", "Carmen", "Pablo", "Fernanda",
                          "Alexander", "Jaime", "Ariana", "Daniela", "Manuel", "Gabriel", "Valentina", "Esteban", "Karen",
                          "Arturo", "Jose", "Sergio", "Jason", "Samuel"};
        return lista[random(23)];
    }
    
    public static String getLastName(){
        String lista[] = {"Alvarado", "Gonzalez", "Perez", "Viquez", "Campos", "Chaves", "Vargas", "Garita", "Aguilera", "Mejia",
                          "Aguero", "Alpizar", "Castro", "Vega", "Ulloa", "Jimenez", "Hidalgo", "Leiva", "Navarro",
                          "Cantillo", "Sanchez", "Espinoza", "Trejos", "Rojas"};
        return lista[random(23)];
    }
    
    public static String getTitle(){
        String lista[] = {"Informática", "Administración", "Inglés", "Turismo", "Agronomía", 
                          "Diseño Publicitario", "Diseño Web", "Asesor", "Doctor", "Abogado"};     
        return lista[random(9)];
    }

    public static boolean isOperating(char value) {
        return Character.isLetter(value);
    }

    public static boolean isOperator(char value) {
        switch(value){
            case '+': case '-': case '*': case '/':
                return true;
        }
        return false;
    }

    public static boolean isNumber(char value) {
        return Character.isDigit(value);
    }

    public static int maxArray(int[] a) {
        int max = a[0]; //first element
        for (int i = 1; i < a.length; i++) {
            if(a[i]>max){
                max=a[i];
            }
        }
        return max;
    }
    
    
}
