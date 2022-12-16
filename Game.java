import java.util.*;
public class Game {
    private static void player_location(int row , int col , int a[][] ,Scanner in , Player p){
        System.out.println("Player "+p.id+" Location: ");
        row = in.nextInt();
        col = in.nextInt();
        p.set(row , col , a);
    }
    private static void fill_locations(int row , int col , int a[][], Scanner in){
        System.out.println("Health Location 1: ");
        row = in.nextInt();
        col = in.nextInt();
        a[row][col] = 6;
        System.out.println("Health Location 2: ");
        row = in.nextInt();
        col = in.nextInt();
        a[row][col] = 6;
        System.out.println("Gun Location: ");
        row = in.nextInt();
        col = in.nextInt();
        a[row][col] = 5;
    }
   //        ******MAIN METHOD*******
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        int row= 9 , col =9;
        int a[][] = new int [row][col];
        fill_locations(row,col,a,in);
        Player p1 = new Player();
        player_location(row, col, a, in , p1);
        Player p2 = new Player();
        player_location(row , col , a , in , p2);
        StringBuilder gunholder = new StringBuilder();
        print(gunholder,a,p1,p2);
        int current_player = 1;
        int direction , moves = 0;
        while(true){
            if(current_player > 2) current_player = 1;
            System.out.print("Player"+current_player+" (Shoot / Choose Direction) & Moves:");
            direction = in.nextInt();
            if(direction != 5) moves = in.nextInt();
            if(direction > 5 || direction < 1 || moves > 3){
                System.out.println("Invalid Input");
                break;
            }
            if(direction == 5 && p1.holding_gun)p1.shoot(a,p2);
            else if(direction == 5 && p2.holding_gun) p2.shoot(a,p1);
            else if(current_player == 1) p1.move(direction,moves,a);
            else if(current_player == 2) p2.move(direction,moves,a);
            else{
                System.out.println("Player"+(current_player++)+" dont have gun..!");
                continue;
            }
            print(gunholder,a,p1,p2);
            current_player++;
        }
    }

    public static void print(StringBuilder gun_holder,int a[][],Player p1 , Player p2){
        gun_holder.setLength(0);
        if(p1.holding_gun) gun_holder.append("Player1");
        else if(p2.holding_gun)gun_holder.append("Player2");
        System.out.println("Gun Holder-> "+gun_holder);
        System.out.println("Player1 Health-> "+p1.health);
        System.out.println("Player2 Health-> "+p2.health);
        for(int r[] : a){
            for(int num : r)System.out.print(num+" ");
            System.out.println();
        }
    }
    public static void print(int a[][]){
        for(int r[] : a){
            for(int num : r)System.out.print(num+" ");
            System.out.println();
        }
    }
}
