public class Player {
    int id = 1;
    static int player_id = 1;
    int health ;
    boolean holding_gun;
    int chances;
    int row;
    int col;
  
    Player(){
        id = player_id++;
        health = 0;
        holding_gun = false;
        chances = 2;
        row = -1;
        col = -1;
    }
    public void set(int r , int c , int a[][]){
        row = r;
        col = c;
        if(a[row][col] == 5 || a[row][col] == 6){
            System.out.println("Invalid..! ");
            System.exit(0);
        }
        a[row][col] = id;
    }
    public  void shoot(int a[][], Player opponent){
        for(int i=0;i<=8;i++){
            search_opponent(row,i,a,opponent);
            search_opponent(i,col,a,opponent);
        }
        chances--;
    }
    private  void move_left(int moves,int a[][]){
        if(col-moves < 0){
            System.out.println("Invalid move..!");
            System.exit(0);
        }
        else if(a[row][col-moves] == 5){
            System.out.println("Gun taken by Player"+id+"..!");
            holding_gun =true;
        }
        else if(a[row][col-moves] == 6) health++;
        col-=moves;
        a[row][col] = id;

    }
    private void move_right(int moves,int a[][]){
        if(col+moves > 8){
            System.out.println("Invalid move..!");
            System.exit(0);
        }
        else if(a[row][col+moves] == 5){
            System.out.println("Gun taken by Player"+id+"..!");
            holding_gun =true;
        }
        else if(a[row][col+moves] == 6) health++;
        col+=moves;
        a[row][col] = id;
    }
    private void move_up(int moves,int a[][]){
        if(row-moves < 0){
            System.out.println("Invalid move..!");
            System.exit(0);
        }
        else if(a[row-moves][col] == 5){
            System.out.println("Gun taken by Player "+id+"..!");
            holding_gun =true;
        }
        else if(a[row-moves][col] == 6) health++;
        row-=moves;
        a[row][col] = id;
    }
    private void move_down(int moves,int a[][]){
        if(row+moves > 8){
            System.out.println("Invalid move..!");
            System.exit(0);
        }
        if(a[row+moves][col] == 5){
            System.out.println("Gun taken by Player "+id+"..!");
            holding_gun =true;
        }
        else if(a[row+moves][col] == 6) health++;
        row+=moves;
        a[row][col] = id;
    }
    private void gun_freed(int row ,int col , int a[][]){
        if(     row-2 >= 0 && a[row-2][col] == 0) a[row-2][col] = 5;
        else if(col-2 >= 0 && a[row][col-2] == 0) a[row][col-2] = 5;
        else if(row+2 <= 8 && a[row+2][col] == 0) a[row+2][col] = 5;
        else if(col+2 <= 8 && a[row][col+2] == 0) a[row][col+2] = 5;
        holding_gun = false;
    }
    public void move(int direction , int moves , int a[][]){
        if(holding_gun && chances == 0){
            System.out.println("Gun freed from Player "+id+"..!");
            gun_freed(row,col,a);
            chances = 2;
            return;
        }
        if(holding_gun) chances-- ;
        a[row][col] = 0;
        if(direction == 1) move_left(moves,a);
        else if(direction == 2) move_right(moves,a);
        else if(direction == 3) move_up(moves,a);
        else move_down(moves,a);
    }
    private void search_opponent(int row , int col , int a[][] , Player opponent){
        if(a[row][col] == opponent.id && opponent.health == 0){
            a[row][col] = 0;
            Game.print(a);
            System.out.println("Player"+id+" Wins..!");
            System.exit(0);
        }else if(a[row][col] == opponent.id && opponent.health > 0){
            opponent.health--;
            gun_freed(row,col,a);
            holding_gun = false;
            System.out.println("Player"+opponent.id+" survived..!");
        }
    }
}
