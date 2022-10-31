package month09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2615_Omok {
    static int[][] result=new int[5][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] board=new int[19][19];
        int a=-1;
        int b=-1;

        for(int i=0;i<19;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<19;j++){
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
                //오목 확인
                //오른쪽, 오른쪽 아래 대각선, 아래, 왼쪽 아래 대각선
                if(board[i][j]==0) continue;

                if(j+1<19 && board[i][j+1]==board[i][j]){
                    if(omok(board,i,j,0)){
                        a=i;
                        b=j;
                        break;
                    }
                }
                if(i+1<19 && j+1<19 && board[i+1][j+1]==board[i][j]){
                    if(omok(board,i,j,1)){
                        a=i;
                        b=j;
                        break;
                    }
                }
                if(i+1<19 && board[i+1][j]==board[i][j]){
                    if(omok(board,i,j,2)){
                        a=i;
                        b=j;
                        break;
                    }
                }
                if(i+1<19 && j-1>=0 && board[i+1][j-1]==board[i][j]){
                    if(omok(board,i,j,3)){
                        a=i;
                        b=j;
                        break;
                    }
                }
            }

            if(a!=-1 && b!=-1) break;
        }

        if(a==-1 && b==-1){
            System.out.println(0);
        }else {
            System.out.println(board[a][b]);

            //왼쪽, 상위
            if(result[0][0]+1==result[1][0] && result[0][1]==result[1][1]){
                System.out.println((result[0][0]+1)+" "+(result[0][1]+1));
            }else{
                int idx=0;

                for(int i=0;i<5;i++){
                    if(result[i][1]<result[idx][1]) idx=i;
                }

                System.out.print((result[idx][0]+1)+" "+(result[idx][1]+1));
            }

        }

    }

    //오른쪽, 오른쪽 아래 대각선, 아래, 왼쪽 아래 대각선
    private static boolean omok(int[][] board,int i,int j,int dir) {
        int cnt=0;
        int tmp_dir=dir;

        if(dir==0){
            for(int n=0;n<6;n++){
                if(j+n>=19) break;
                if(board[i][j+n]!=board[i][j]) break;
                cnt++;

                if(n==5) break;
                result[n][0]=i;
                result[n][1]=j+n;
            }
        }else if(dir==1){
            for(int n=0;n<6;n++){
                if(i+n>=19 || j+n>=19) break;
                if(board[i+n][j+n]!=board[i][j]) break;
                cnt++;

                if(n==5) break;
                result[n][0]=i+n;
                result[n][1]=j+n;
            }
        }else if(dir==2){
            for(int n=0;n<6;n++){
                if(i+n>=19) break;
                if(board[i+n][j]!=board[i][j]) break;
                cnt++;

                if(n==5) break;
                result[n][0]=i+n;
                result[n][1]=j;
            }
        }else if(dir==3){
            for(int n=0;n<6;n++){
                if(i+n>=19 || j-n<0) break;
                if(board[i+n][j-n]!=board[i][j]) break;
                cnt++;

                if(n==5) break;
                result[n][0]=i+n;
                result[n][1]=j-n;
            }
        }

        if(cnt==5) {
            if(tmp_dir==0){
                if(j-1>=0 && board[i][j-1]==board[i][j]) {
                    result=new int[5][2];
                    return false;
                }
            }else if (tmp_dir==1){
                if(i-1>=0 && j-1>=0 && board[i-1][j-1]==board[i][j]) {
                    result=new int[5][2];
                    return false;
                }
            }else if(tmp_dir==2){
                if(i-1>=0 && board[i-1][j]==board[i][j]) {
                    result=new int[5][2];
                    return false;
                }
            }else if(tmp_dir==3){
                if(i-1>=0 && j+1<19 && board[i-1][j+1]==board[i][j]) {
                    result=new int[5][2];
                    return false;
                }
            }
            return true;
        }

        result=new int[5][2];
        return false;
    }
}
