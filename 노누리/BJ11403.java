import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11403 {
    static boolean[] visited;
    static int[][] graph;
    static int N;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());

        StringTokenizer st;
        graph=new int[N][N];
        result=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                graph[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        //dfs
        for(int i=0;i<N;i++){
            //i번째 노드에서 출발한다고 생각
            //새로운 노드를 잡으면 방문 배열도 초기화 해줘야함
            visited=new boolean[N];
            dfs(i);
            
            //dfs를 거쳐서 모든 이어진 노드를 방문했다면 visited[i]=true
            //즉 false라는 것은 해당 노드에서 출발했을 때 도달하지 못한다는 뜻임
            for(int j=0;j<N;j++){
                if(visited[j]) result[i][j]=1;
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void dfs(int node){
        for(int i=0;i<N;i++){
            if(graph[node][i]==1 && !visited[i]){
                visited[i]=true;
                dfs(i);
                //방문하고 길을 잘못들어서 빠져나와야할 이유는 없으니 백트래킹 하지 않음
            }
        }
    }
}
