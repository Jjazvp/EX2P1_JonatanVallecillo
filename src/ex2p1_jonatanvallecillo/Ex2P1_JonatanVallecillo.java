package ex2p1_jonatanvallecillo;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex2P1_JonatanVallecillo {
    
    static Scanner leer = new Scanner(System.in);
    static ArrayList <String> instru = new ArrayList <String>();
    static Montacarga montacarga = new Montacarga();

    public static void main(String[] args) {
        char[][] mapa = {
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        {'>', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        {'#', '#', '#', '#', '#', '#', '#', '.', '.', '.', '.'},
        {'.', 'C', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '#', '#', '#', '#', '#', '#', '#'},
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', 'M', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        montacarga.setMapa(mapa);
        
        agregarInstruccion(instru);
        
    }
    
    public static void agregarInstruccion(ArrayList <String> instru){
        int opcion = 0;
        int continuar = 1;
        while(continuar == 1){
            imprimir_mapa();
            System.out.println();
            int menu = 1;
            while(menu == 1){
                System.out.println("1. Subir");
                System.out.println("2. Bajar");
                System.out.println("3. Derecha");
                System.out.println("4. Izquierda");
                System.out.println("5. Cargar");
                System.out.println("6. Descargar");
                System.out.print("-> Agregue la instruccion: ");
                opcion = leer.nextInt();
                if (opcion > 0 && opcion < 7){
                    menu = 0;
                }else{
                    System.out.println("ERROR. Intente de nuevo.");
                }
            }

            switch(opcion){
                case 1:
                    instru.add("Subir");
                    break;
                case 2:
                    instru.add("Bajar");
                    break;
                case 3:
                    instru.add("Derecha");
                    System.out.println("El montacarga va a la derecha");
                    break;
                case 4:
                    instru.add("Izquierda");
                    System.out.println("El montacarga va a la izquierda");
                    break;
                case 5:
                    instru.add("Cargar");
                    System.out.println("El montacarga descargara la carga");
                    break;
                case 6:
                    System.out.println("\n---Lista de Instrucciones---");
                    listar_instrucciones(0);
                    System.out.println(montacarga.getMapa());
                    System.out.println("Iniciando movimiento");
                    moverse();
                    break;
            }
            System.out.println();
        }
    }
    
    public static void moverse(){
        int x = 0;
        int y = 0;
        char [][] map = montacarga.getMapa();
        int posx = 0;
        int posy = 0;
        for(int i = 0; i < map.length; i++){
            y = map.length;
            for(int j = 0; j < map[i].length; j++){
                x = map[i].length;
                if(map[i][j] == 'M'){
                    posx = j;
                    posy = i;
                }
            }
        }
        
        for(int i = 0; i < instru.size(); i++){
            if(instru.get(i).equals("Izquierda")){
                posx -= 1;
                if(posx >= 0 && posx < x){
                    map[posy][posx] = 'M';
                    map[posy][posx+1] = '.';
                }else{
                    System.out.println("\nSe ha salido del mapa");
                    i = instru.size();
                }
            }else if(instru.get(i).equals("Derecha")){
                posx += 1;
                if(posx >= 0 && posx < x){
                    map[posy][posx] = 'M';
                    map[posy][posx-1] = '.';
                }else{
                    System.out.println("\nSe ha salido del mapa");
                    i = instru.size();
                }
            }else if(instru.get(i).equals("Subir")){
                posy -= 1;
            }else if(instru.get(i).equals("Cargar")){
                posy += 1;
            }
        }
        
        imprimir_mapa();
    }
    
    public static void listar_instrucciones(int i){
        if (i < instru.size()){
            System.out.println(instru.get(i));
            listar_instrucciones(i+1);
        }
    } 
    
    public static void imprimir_mapa(){
        System.out.println("---------Map---------");
        char [][] map = montacarga.getMapa();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
