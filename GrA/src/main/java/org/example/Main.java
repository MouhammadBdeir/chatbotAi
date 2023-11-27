package org.example;


public class Main {

    public static void main(String[] args) {
       String [][]termin= new String[4][4];
       String [][]termin2={{"01","02","03"},{"11","12"},{"21","22","23"}};

       for(int z=0; z<termin2.length;z++){
           for(int s=0; s<termin2[z].length;s++){
                System.out.print(termin2[z][s]+",");
           }
           System.out.println();
       }
    }

}