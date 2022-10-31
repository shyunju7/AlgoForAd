package month09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14712_Nemmo {
    static boolean[] visited;
    static int count;
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        //넴모의 개수
        visited=new boolean[N*M];

        subset(0,N*M);

        System.out.print(count);
    }

    private static void subset(int index,int R){
        if(index==R){
            //2x2가 아니라면 count++
            for (int i = 0; i < N * M; i++) {
                if (visited[i] && i % M != M - 1 && i+M+1<N*M) {
                    if (visited[i + 1] && visited[i + M] && visited[i + M + 1]) {
                        return;
                    }
                }
            }
            count++;
            return;
        }

        visited[index]=true;
        subset(index+1,R);

        visited[index]=false;
        subset(index+1,R);
    }
}

