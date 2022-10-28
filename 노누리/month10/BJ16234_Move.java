import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16234 {
    static int N,L,R,cnt;
    static int[][] map;
    static int[][] visited;
    static int[][] moves={{0,1},{1,0},{0,-1},{-1,0}};
    static int day;
    static boolean flag;
    static int[][] copy;
    static int n,sum;
    static int pop_count;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());

        map=new int[N][N];
        copy=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            cnt=0;
            //연합 표시해주기
            visited = new int[N][N];

            flag=false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 0) {
                        cnt++;
                        bfs(i, j);
                        if(n>1){
                            move();
                            flag=true;
                        }
                    }
                }
            }

            if(!flag){
                System.out.println(day);
                System.exit(0);
            }

            day++;
        }

    }

    private static void move() {
        int pop=sum/n;
        //인구이동하기
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(visited[i][j]==cnt){
                    map[i][j]=pop;
                }
            }
        }

    }

    private static void bfs(int i,int j) {
        n=1;
        sum=map[i][j];
        Queue<Point> queue=new ArrayDeque<>();
        queue.offer(new Point(i,j));

        while(!queue.isEmpty()){
            Point p=queue.poll();
            visited[p.x][p.y]=cnt;

            for(int d=0;d<4;d++){
                int a=p.x+moves[d][0];
                int b=p.y+moves[d][1];

                if(a<0 || a>=N || b<0 || b>=N) continue;

                int tmp=Math.abs(map[a][b]-map[p.x][p.y]);
                if(visited[a][b]==0 && tmp>=L && tmp<=R){
                    queue.offer(new Point(a,b));
                    visited[a][b]=cnt;
                    n++;
                    sum+=map[a][b];
                }
            }
        }

    }
}
