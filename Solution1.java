//first solution, correct results, need optimization for runtime
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static void matrixPrint(int[][] matrix, int maxCol,int maxRow){
        for(int row=0;row<=maxRow;row++){
                for(int col=0;col<=maxCol;col++){
                   System.out.print(matrix[row][col]);
                   if(col!=maxCol){System.out.print(' ');}
                }
                if(row!=maxRow){System.out.println();}
        }

    }
    static void matrixRotation(int[][] matrix, int maxcol,int maxrow,int[] rotations) {
        int maxCol=maxcol;
        int maxRow=maxrow;
        int min=0;
        int iterNum=Math.min(maxCol+1,maxRow+1)/2;
        int[][] temp=new int[maxRow+1][maxCol+1];
        for(int itr=0;itr<iterNum;itr++){
            if(rotations[itr]>=1){
            for(int row=min;row<=maxRow;row++){
                for(int col=min;col<=maxCol;col++){
                   if((row==min&&col==min)||(col==min&&row>min&&row<maxRow)){
                      temp[row+1][col]=matrix[row][col]; 
                   }else if((row==min&&col==maxCol)||(row==min&&col>min&&col<maxCol)){
                       temp[row][col-1]=matrix[row][col];
                   }else if((row==maxRow&&col==min)||(row==maxRow&&col>min&&col<maxCol)){
                       temp[row][col+1]=matrix[row][col];
                   }else if((row==maxRow&&col==maxCol)||(col==maxCol&&row>min&&row<maxRow)){
                      temp[row-1][col]=matrix[row][col]; 
                   }                
                }
            }
            }else{
                for(int row=min;row<=maxRow;row++){
                for(int col=min;col<=maxCol;col++){
                   if(((row==min&&col==min)||(col==min&&row>min&&row<maxRow))
                        ||((row==min&&col==maxCol)||(row==min&&col>min&&col<maxCol))
                        ||((row==maxRow&&col==min)||(row==maxRow&&col>min&&col<maxCol))
                        ||((row==maxRow&&col==maxCol)||(col==maxCol&&row>min&&row<maxRow))){
                      temp[row][col]=matrix[row][col]; 
                   }                
                }
            }
            }
            min++;
            maxCol--;
            maxRow--;
            } 
        
        int sumrotations=0;
        for(int itrr=0;itrr<iterNum;itrr++){
            if(rotations[itrr]>0){rotations[itrr]-=1;}
            sumrotations+=rotations[itrr];
        }
        if(sumrotations==0){
            matrixPrint(temp,maxcol,maxrow);
            return;
        }
        matrixRotation(temp,maxcol,maxrow,rotations);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int r = in.nextInt();
        int[][] matrix = new int[m][n];
        for(int matrix_i = 0; matrix_i < m; matrix_i++){
            for(int matrix_j = 0; matrix_j < n; matrix_j++){
                matrix[matrix_i][matrix_j] = in.nextInt();
            }
        }
        int layers=Math.min(m, n)/2;
        int[] rot=new int[layers];
        int prem=(m+n-2)*2;
        for(int i=0; i<layers;i++){
            rot[i]=r%(prem-(8*i));
        }
        matrixRotation(matrix,n-1,m-1,rot);
        in.close();
    }
}
