import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ21278 {
    static int[][] map;
    static int N,M;
    static boolean[] visited;
    static int tmp,a,b;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j]=(int)1e9;
            }
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken())-1;
            int b=Integer.parseInt(st.nextToken())-1;

            map[a][b]=1;
            map[b][a]=1;
        }


        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(i==j || j==k || k==i) continue;
                    if(map[i][j]>map[i][k]+map[k][j]){
                        map[i][j]=map[i][k]+map[k][j];
                    }
                }
            }
        }

        int result=Integer.MAX_VALUE;
        // 치킨 집 두 곳 지정
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                tmp=0;
                for(int k=0;k<N;k++){
                    if(i==k || j==k) continue;
                    int min=Math.min(map[i][k],map[j][k]);
                    tmp+=min;
                }

                if(tmp<result){
                    result=tmp;
                    a=i+1;
                    b=j+1;
                }
            }
        }

        System.out.println(a+" "+b+" "+result*2);
    }

}

