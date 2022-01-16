package com.patika;

import java.util.*;
public class MineSweeper {
    int row,col;
    boolean isGameOver = false;
    int [][] map;
    String [][] game;

    MineSweeper(int row,int col){
        this.row = row;
        this.col = col;
        this.map = new int [row][col];
        this.game = new String [row][col];
        createGame(row,col);
    }

    public void createGame(int row, int col){
        int numberOfMine = (row*col)/4;
        int count = 0;
        Random random = new Random();

        while(count<numberOfMine){
            int mineRow = random.nextInt(row);
            int mineCol = random.nextInt(col);

            if(this.map[mineRow][mineCol] != 1){
                this.map[mineRow][mineCol] = 1;
                count++;
            }
        }

        for (String[] strings : game) {
            Arrays.fill(strings,"-");
        }

    }

    public void run(){
        printMap();
        Scanner scanner = new Scanner(System.in);
        int inRow,inCol;
        while(!isGameOver){
            printGame();
            System.out.print("Satir giriniz: ");
            inRow = scanner.nextInt();
            System.out.print("Sutun giriniz: ");
            inCol = scanner.nextInt();

            if(inRow<0 || inRow>=this.row){
                System.out.println("Hatali satir");
                continue;
            }

            if(inCol<0 || inCol>=this.col){
                System.out.println("Hatali sutun");
                continue;
            }

            select(inRow,inCol);
            if(isFinished()){
                System.out.println("Tebrikler oyunu kazandiniz.");
                break;
            }
            if(isGameOver){
                System.out.println("Mayina bastınız oyun bitti");
                printMap();
            }
        }
    }

    private void printGame(){
        System.out.println("-------------");
        for(String[] i : this.game){
            for(String j: i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    private void printMap() {
        System.out.println("----- map -------");
        for(int[] i : this.map){
            for(int j: i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        System.out.println("----- map -------");
    }

    public void select(int row,int col){
        int count = 0;
        if(this.map[row][col] == 1) {
            this.isGameOver = true;
        }else {

            for(int i=row-1; i<=row+1; i++){
                for(int j=col-1; j<=col+1;j++){
                    if(i==row && j==col)
                        continue;
                    try{
                        count+= map[i][j];
                    }catch(Exception ignored){

                    }

                }
            }
            this.game[row][col] = String.valueOf(count);

        }

    }

    private boolean isFinished(){
        boolean isEmpty=true;
        for(int i= 0; i<game.length;i++){
            for(int j=0; j<game[i].length;j++){
                if (Objects.equals(game[i][j], "-") && map[i][j]==0 ) {
                    isEmpty = false;
                    break;
                }
            }
        }
        return isEmpty;
    }
}
