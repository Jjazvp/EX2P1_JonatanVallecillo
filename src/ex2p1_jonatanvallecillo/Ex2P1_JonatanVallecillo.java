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
                    System.out.println("El montacarga va a cargar");
                    break;
                case 6:
                    if(instru.size() > 0){
                        System.out.println("\n---Lista de Instrucciones---");
                        listar_instrucciones(0);
                        System.out.println(montacarga.getMapa());
                        System.out.println("Iniciando movimiento");
                        moverse(0);
                    }else{
                        System.out.println("\nNo tiene instrucciones");
                    }
                    imprimir_mapa();
                    if(montacarga.getEntregar() == true){
                        System.out.println("Ha logrado descargar la carga correctamente!!");
                        continuar = 0;
                    }else{
                        System.out.println("Ha fracasado.");
                        continuar = 0;
                    }
                    
                    break;
            }
            System.out.println();
        }
    }
    
    public static void moverse(int a){ //Con Recursiva
        int x = 0;
        int y = 0;
        char [][] map = montacarga.getMapa();
        int posx = 0;
        int posy = 0;
        for(int i = 0; i < map.length; i++){
            y = map.length;
            montacarga.setFila(y);
            for(int j = 0; j < map[i].length; j++){
                x = map[i].length;
                montacarga.setColumna(x);
                if(map[i][j] == 'M'){
                    posx = j;
                    posy = i;
                }
            }
        }
        
        if(a < instru.size()){
            System.out.println();
            imprimir_mapa();
            System.out.println();
            
            if(instru.get(a).equals("Izquierda")){
                System.out.println("Intruccion: "+instru.get(a));
                posx -= 1;
                if(posx >= 0 && posx < montacarga.getColumna()){
                    if(map[posy][posx] == '#'){
                        System.out.println("Se ha topado con una pared");
                        a = instru.size();
                    }else if(map[posy][posx] == 'C'){
                        System.out.println("No puede sobreponerse a la carga. Utilice opcion 5.");
                    }else if(map[posy][posx] == '.'){
                        map[posy][posx] = 'M';
                        map[posy][posx+1] = '.';
                        moverse(a+1); //Recursiva
                    }else{
                        char [][] maps = montacarga.getMapa();
                        posx = 0;
                        posy = 0;

                        for(int j = 0; j < maps.length; j++){
                            for(int k = 0; k < maps[j].length; k++){
                                if(maps[j][k] == 'M'){
                                    posx = k;
                                    posy = j;
                                }
                            }
                        }
                        
                        if(maps[posy + 1][posx] == '>'){
                            maps[posy + 1][posx] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy - 1][posx] == '>'){
                            maps[posy - 1][posx] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy][posx + 1] == '>'){
                            maps[posy][posx + 1] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy][posx - 1] == '>'){
                            maps[posy][posx - 1] = '*';
                            montacarga.setEntregar(true);
                        }
                        montacarga.setMapa(maps);
                    }
                }else{
                    System.out.println("\nSe ha salido del mapa");
                    a = instru.size();
                }
            }else if(instru.get(a).equals("Derecha")){
                System.out.println("Intruccion: "+instru.get(a));
                posx += 1;
                if(posx >= 0 && posx < montacarga.getColumna()){
                    if(map[posy][posx] == '#'){
                        System.out.println("Se ha topado con una pared");
                        a = instru.size();
                    }else if(map[posy][posx] == 'C'){
                        System.out.println("No puede sobreponerse a la carga. Utilice opcion 5.");
                    }else if(map[posy][posx] == '.'){
                        map[posy][posx] = 'M';
                        map[posy][posx-1] = '.';
                        moverse(a+1); //Recursiva
                    }else{
                        
                        char [][] maps = montacarga.getMapa();
                        posx = 0;
                        posy = 0;

                        for(int j = 0; j < maps.length; j++){
                            for(int k = 0; k < maps[j].length; k++){
                                if(maps[j][k] == 'M'){
                                    posx = k;
                                    posy = j;
                                }
                            }
                        }
                        
                        if(maps[posy + 1][posx] == '>'){
                            maps[posy + 1][posx] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy - 1][posx] == '>'){
                            maps[posy - 1][posx] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy][posx + 1] == '>'){
                            maps[posy][posx + 1] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy][posx - 1] == '>'){
                            maps[posy][posx - 1] = '*';
                            montacarga.setEntregar(true);
                        }
                        montacarga.setMapa(maps);
                        
                    }
                }else{
                    System.out.println("\nSe ha salido del mapa");
                    a = instru.size();
                }
            }else if(instru.get(a).equals("Subir")){
                System.out.println("Intruccion: "+instru.get(a));
                posy -= 1;
                if(posy >= 0 && posy < montacarga.getFila()){
                    if(map[posy][posx] == '#'){
                        System.out.println("Se ha topado con una pared");
                        a = instru.size();
                    }else if(map[posy][posx] == 'C'){
                        System.out.println("No puede sobreponerse a la carga. Utilice opcion 5.");
                    }else if(map[posy][posx] == '.'){
                        map[posy][posx] = 'M';
                        map[posy+1][posx] = '.';
                        moverse(a+1); //Recursiva
                    }else{
                        
                        char [][] maps = montacarga.getMapa();
                        posx = 0;
                        posy = 0;

                        for(int j = 0; j < maps.length; j++){
                            for(int k = 0; k < maps[j].length; k++){
                                if(maps[j][k] == 'M'){
                                    posx = k;
                                    posy = j;
                                }
                            }
                        }
                        
                        if(maps[posy + 1][posx] == '>'){
                            maps[posy + 1][posx] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy - 1][posx] == '>'){
                            maps[posy - 1][posx] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy][posx + 1] == '>'){
                            maps[posy][posx + 1] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy][posx - 1] == '>'){
                            maps[posy][posx - 1] = '*';
                            montacarga.setEntregar(true);
                        }
                        montacarga.setMapa(maps);
                        
                    }
                }else{
                    System.out.println("\nSe ha salido del mapa");
                    a = instru.size();
                }
            }else if(instru.get(a).equals("Bajar")){
                System.out.println("Intruccion: "+instru.get(a));
                posy += 1;
                if(posy >= 0 && posy < montacarga.getFila()){
                    if(map[posy][posx] == '#'){
                        System.out.println("Se ha topado con una pared");
                        a = instru.size();
                    }else if(map[posy][posx] == 'C'){
                        System.out.println("No puede sobreponerse a la carga. Utilice opcion 5.");
                    }else if(map[posy][posx] == '.'){
                        map[posy][posx] = 'M';
                        map[posy - 1][posx] = '.';
                        moverse(a+1); //Recursiva
                    }else{
                        
                        char [][] maps = montacarga.getMapa();
                        posx = 0;
                        posy = 0;

                        for(int j = 0; j < maps.length; j++){
                            for(int k = 0; k < maps[j].length; k++){
                                if(maps[j][k] == 'M'){
                                    posx = k;
                                    posy = j;
                                }
                            }
                        }
                        
                        if(maps[posy + 1][posx] == '>'){
                            maps[posy + 1][posx] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy - 1][posx] == '>'){
                            maps[posy - 1][posx] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy][posx + 1] == '>'){
                            maps[posy][posx + 1] = '*';
                            montacarga.setEntregar(true);
                        }else if(maps[posy][posx - 1] == '>'){
                            maps[posy][posx - 1] = '*';
                            montacarga.setEntregar(true);
                        }
                        montacarga.setMapa(maps);
                        
                    }
                }else{
                    System.out.println("\nSe ha salido del mapa");
                    a = instru.size();
                }
            }else if(instru.get(a).equals("Cargar")){
                char [][] maps = montacarga.getMapa();
                    posx = 0;
                    posy = 0;
                    
                    for(int i = 0; i < maps.length; i++){
                        for(int j = 0; j < maps[i].length; j++){
                            if(maps[i][j] == 'M'){
                                posx = j;
                                posy = i;
                            }
                        }
                    }
                    
                    if(maps[posy + 1][posx] == 'C'){
                        maps[posy + 1][posx] = '.';
                        montacarga.setMontacarga(true);
                    }else if(maps[posy - 1][posx] == 'C'){
                        maps[posy - 1][posx] = '.';
                        montacarga.setMontacarga(true);
                    }else if(maps[posy][posx + 1] == 'C'){
                        maps[posy][posx + 1] = '.';
                        montacarga.setMontacarga(true);
                    }else if(maps[posy][posx - 1] == 'C'){
                        maps[posy][posx - 1] = '.';
                        montacarga.setMontacarga(true);
                    }
                    montacarga.setMapa(maps);
                    moverse(a+1); //Recursiva
            }
        }
    }
    
    public static void listar_instrucciones(int i){ //Con Recursiva
        if (i < instru.size()){
            System.out.println(instru.get(i));
            listar_instrucciones(i+1); //Recursiva
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
