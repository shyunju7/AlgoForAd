package month09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BJ3085 {
    static int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
    static char[][] board;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());
        board=new char[N][N];

        for(int i=0;i<N;i++){
            board[i]=br.readLine().toCharArray();
        }

        int result=0;

        //가로
        for(int i=0;i<N;i++){
            int sum=0;
            for(int j=0;j<N-1;j++){
                if(board[i][j]==board[i][j+1]){
                    sum++;
                }else{
                    result=Math.max(result,sum);
                    sum=0;
                }
            }

            result=Math.max(result,sum);
        }

        //세로
        for(int i=0;i<N;i++){
            int sum=0;
            for(int j=0;j<N-1;j++){
                if(board[j][i]==board[j+1][i]){
                    sum++;
                }else{
                    result=Math.max(result,sum);
                    sum=0;
                }
            }

            result= Math.max(result,sum);
        }


        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                for(int k=0;k<4;k++) {
                    if(i+dir[k][0]<0 || i+dir[k][0]>=N || j+dir[k][1]<0 || j+dir[k][1]>=N) continue;;
                    if (board[i][j] != board[i+dir[k][0]][j+dir[k][1]]) {
                        //자리 교환 후 최대로 먹을 수 있는 사탕 개수 구하기
                        result=Math.max(result,change(i,j,i+dir[k][0],j+dir[k][1]));
                    }
                }
            }
        }

        System.out.println(result+1);

    }

    private static int change(int x1, int y1, int x2, int y2) {
        int tmp=0;

        char[][] copy= new char[N][N];

        for(int i=0;i<N;i++){
            copy[i]=Arrays.copyOf(board[i],N);
        }

        char swap=copy[x1][y1];
        copy[x1][y1]=copy[x2][y2];
        copy[x2][y2]=swap;

        //가로
        for(int i=0;i<N;i++){
            int sum=0;
            for(int j=0;j<N-1;j++){
                if(copy[i][j]==copy[i][j+1]){
                    sum++;
                }else{
                    tmp=Math.max(tmp,sum);
                    sum=0;
                }
            }

            tmp=Math.max(tmp,sum);
        }

        //세로
        for(int i=0;i<N;i++){
            int sum=0;
            for(int j=0;j<N-1;j++){
                if(copy[j][i]==copy[j+1][i]){
                    sum++;
                }else{
                    tmp=Math.max(tmp,sum);
                    sum=0;
                }
            }

            tmp= Math.max(tmp,sum);
        }

        return tmp;
    }
}
