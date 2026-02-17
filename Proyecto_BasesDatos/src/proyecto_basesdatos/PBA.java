/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_basesdatos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import static Metodos.MetodosCliente.*;
import java.io.IOException;
import java.sql.SQLException;
/**
 *
 * @author Juan Pablo
 */
public class PBA {
    
    public static int MenuCliente(BufferedReader br, int n){
        System.out.println("¿Que accion desea realizar?");
        System.out.println("1. ");
        return n;
    }
    public static void main(String[] args) throws IOException, SQLException{
        //En esta clase se hará el menu
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int op = 0;
        do {
            
            System.out.println("--- MENU---");
            System.out.println("1. CLIENTE");
            System.out.println("2. PROVEEDOR");
            System.out.println("0. Salir");
            op = Integer.parseInt(br.readLine());
                    
            switch (op) {
                case 1:
                    int op1 = 0;
                    do{
                        System.out.println("");
                    System.out.println("MENU CLIENTE");
                    System.out.println("1. Registro");
                    System.out.println("2. Eliminar");
                    System.out.println("0. Salir");
                        System.out.println("");
                    op1 = Integer.parseInt(br.readLine());
                        switch (op1) {
                            case 1:
                                registrarUnCliente(br);
                                break;
                            case 2:
                                eliminarUnCliente(br);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("ERROR");
                        }
                    }while(op1 != 0);
                    break;
                case 2: 
                    
                    break;
                case 0:
                    break;
                default:
                    System.out.println("ERROR");
            }
        } while (op != 0);
    }
}
