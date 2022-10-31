package month09;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;

public class BJ2573 {
    static int[][] moves={{0,1},{0,-1},{1,0},{-1,0}};
    static int[][] ice;
    static ArrayList<Integer> melt;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        ice=new int[N][M];
        int time=0;

        melt=new ArrayList<>();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                ice[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            time++;
            //녹을 양 저장
            melting(N,M);

            //녹을 양 감소
            sink(N,M);

            //몇 덩이인지 확인
            visited=new boolean[N][M];
            int cnt=0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(ice[i][j]!=0 && !visited[i][j]){
                        visited[i][j]=true;
                        bfs(i,j,N,M,visited);
                        cnt++;
                    }
                }
            }

            //두 덩이 이상이라면 출력
            if(cnt>1) break;
            //빙산이 다 녹았는데 두 덩이 이상이 아니라면
            else{
                boolean flag=false;
                for(int i=0;i<N;i++){
                    for(int j=0;j<M;j++){
                        if(ice[i][j]!=0) {
                            flag=true;
                            break;
                        }
                    }
                    if(flag) break;
                }

                if(!flag){
                    time=0;
                    break;
                }
            }

        }

        System.out.print(time);

    }
    private static void bfs(int i,int j,int N,int M,boolean[][] visited) {
        Queue<Point> queue=new ArrayDeque<>();
        queue.add(new Point(i,j));
        visited[i][j]=true;

        while(!queue.isEmpty()){
            Point p=queue.poll();

            for(int[] m:moves){
                int a=p.x+m[0];
                int b=p.y+m[1];
                if(ice[a][b]!=0 && !visited[a][b]){
                    visited[a][b]=true;
                    queue.add(new Point(a,b));
                }
            }
        }
    }

    private static void sink(int N,int M) {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(ice[i][j]!=0){
                    ice[i][j]-=melt.get(0);
                    if(ice[i][j]<0) ice[i][j]=0;
                    melt.remove(0);
                }
            }
        }
    }

    private static void melting(int N,int M) {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(ice[i][j]!=0) {
                    int cnt = 0;
                    for (int[] m : moves) {
                        if (ice[i + m[0]][j + m[1]] == 0) cnt++;
                    }
                    melt.add(cnt);
                }
            }
        }
    }
}
