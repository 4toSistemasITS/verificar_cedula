/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificar_cedula;


import java.util.Scanner;

/**
 *
 * @author Juan Neira
 */
public class Verificar_cedula {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String cedula;
        boolean sw=false;
        Scanner sc= new Scanner(System.in);
        System.out.printf("Ingrese su numero de cedula: ");
        cedula= sc.nextLine();
        while (sw == false) {
            if (cedula.length() <= 9 || cedula.length() > 10) {
                System.out.printf("Por favor ingrese su numero de cedula de 10 digitos: ");
                cedula=sc.nextLine();
            }  
            else{
                break;
            }
        }
        System.out.println(verificacion_cedula(cedula));
    }

    public static boolean verificacion_cedula(String cedula) {
        Scanner sc= new Scanner(System.in);
        int aux=0;
        int dec=0;
        int n=0;
        int rest;
        int mult;
        int sum_total=0;
        int sum_par = 0;
        int sum_impar = 0;
        for (int i = 0; i < cedula.length()-1; i++) {
            n=Integer.parseInt(String.valueOf(cedula.charAt(i)));
            if (i % 2 == 0) {
                mult = n * 2;
                if (mult > 9) {
                    mult = mult - 9;
                    
                }
                sum_impar = sum_impar + mult;
                mult=0;
            } else {
                sum_par = sum_par + n;
            }
        }
        sum_total=sum_par + sum_impar;
        aux = sum_total  / 10;
        dec = (aux + 1) * 10;
        rest= dec - sum_total;
        n=Integer.parseInt(String.valueOf(cedula.charAt(9)));
        if (n == rest) {
            return true;
        } else {
            return false;
        }
    }
}

