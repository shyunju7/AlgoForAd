package month10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2589_island {
    static char[][] map;
    static boolean[][] visited;
    static int[][] mapNum;
    static int N,M;
    static int dist,tmp;
    static int[][] moves={{1,0},{0,1},{-1,0},{0,-1}};

    public static class Node{
        int x,y,depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new char[N][M];
        mapNum=new int[N][M];
        visited=new boolean[N][M];
        dist=Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            map[i]=br.readLine().toCharArray();
        }

        // 육지별 번호 매기기
        int cnt=1;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]=='L' && !visited[i][j]){
                    bfs(i,j,cnt);
                    cnt++;
                }
            }
        }

        //각 육지별로 for문 돌려서 시작점마다 끝 노드까지의 거리 구하기
        for(int c=1;c<=cnt;c++){
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    tmp=0;
                    if(mapNum[i][j]==c){
                        bfsNum(i,j,c);
                        if(tmp>dist){
                            dist=tmp;
                        }
                    }
                }
            }

        }

        System.out.println(dist);

    }

    private static void bfsNum(int i, int j, int cnt) {
        boolean[][] tmpV=new boolean[N][M];
        Queue<Node> queue=new ArrayDeque<>();
        queue.offer(new Node(i,j,0));
        tmpV[i][j]=true;

        while(!queue.isEmpty()){
            Node n=queue.poll();

            if(tmp<n.depth) tmp=n.depth;

            for(int d=0;d<4;d++){
                int a=n.x+moves[d][0];
                int b=n.y+moves[d][1];

                if(a<0 || a>=N || b<0 || b>=M) continue;

                if(mapNum[a][b]==cnt && !tmpV[a][b]){
                    queue.offer(new Node(a,b,n.depth+1));
                    tmpV[a][b]=true;
                }
            }
        }
    }

    private static void bfs(int i, int j,int cnt) {
        Queue<Point> queue=new ArrayDeque<>();
        queue.offer(new Point(i,j));
        visited[i][j]=true;
        mapNum[i][j]=cnt;

        while(!queue.isEmpty()){
            Point p=queue.poll();

            for(int d=0;d<4;d++){
                int a=p.x+moves[d][0];
                int b=p.y+moves[d][1];

                if(a<0 || a>=N || b<0 || b>=M) continue;

                if(map[a][b]=='L' && !visited[a][b]){
                    queue.offer(new Point(a,b));
                    visited[a][b]=true;
                    mapNum[a][b]=cnt;
                }
            }
        }
    }
}
