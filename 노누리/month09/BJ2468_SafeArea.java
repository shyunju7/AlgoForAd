package month09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2468_SafeArea {
    static int result;
    static int[][] moves={{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        int[][] map =new int[N][N];
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]<min) min=map[i][j];
                else if(map[i][j]>max) max=map[i][j];
            }
        }

        //잠기는 높이에 따라 안전영역이 달라짐
        for(int d=min-1;d<max;d++){
            //영역 잠기게하기
            int[][] copy=sink(d,map,N);

            //빗물에 잠기는 높이에 따른 영역 개수
            int count=0;

            //영역 개수 찾기
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(copy[i][j]!=0){
                        copy=dfs(copy,N,i,j);
                        count++;
                    }
                }
            }

            if(count>result) result=count;
        }

        System.out.println(result);
    }

    private static int[][] dfs(int[][] copy,int N, int i, int j) {

        for(int[] m: moves){
            int a=i+m[0];
            int b=j+m[1];
            if(a<0 || a>=N || b<0 || b>=N) continue;
            if(copy[a][b]!=0){
                copy[a][b]=0;
                dfs(copy,N,a,b);
            }
        }

        return copy;
    }

    private static int[][] sink(int d, int[][] map,int N) {
        int[][] copy=new int[N][N];

        //그래프 깊은 복사
        //잠긴 부분은 0으로 처리
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]<=d) continue;
                copy[i][j]=map[i][j];
            }
        }

        return copy;
    }

}
