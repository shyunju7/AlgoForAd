package month11;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW2117_SafeService {
    static int homeCount;
    static int N,M;
    static int[][] moves={{0,1},{0,-1},{1,0},{-1,0}};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T=Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            homeCount=0;
            st=new StringTokenizer(br.readLine());
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());

            map=new int[N][N];

            for(int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            // 서비스 영역 탐색
            for(int k=1;k<=N+1;k++){
                // 서비스 영역 당 최대한 많은 집들을 포함하는 위치 선정
                //가운데 지점을 모든 칸 다 돌려보기
                for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                        //집 몇 채 있는지 확인
                        bfs(i,j,k);
                    }
                }
            }

            System.out.println("#"+t+" "+homeCount);

        }

    }

    private static void bfs(int i, int j, int k) {
        Queue<Point> queue=new ArrayDeque<>();
        boolean[][] visited=new boolean[N][N];
        queue.offer(new Point(i,j));

        // 집 개수
        int count=0;

        if(map[i][j]==1){
            count++;
            visited[i][j]=true;
        }

        while(!queue.isEmpty()){
            Point n=queue.poll();

            for(int d=0;d<4;d++){
                int nx=n.x+moves[d][0];
                int ny=n.y+moves[d][1];

                if(nx<0 || nx>=N || ny<0 || ny>=N) continue;

                if(Math.abs(nx-i)+Math.abs(ny-j)<k && !visited[nx][ny]){
                    if(map[nx][ny]==1){
                        count++;
                    }
                    queue.offer(new Point(nx,ny));
                    visited[nx][ny]=true;
                }

            }
        }

        if(homeCount<count){
            int cal=k*k+(k-1)*(k-1);
            if((M*count-cal)>=0){
                homeCount=count;
            }
        }
    }
}

