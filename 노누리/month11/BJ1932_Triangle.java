package month11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1932_Triangle {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        int[][] tri=new int[N][N];
        int[][] dp=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<i+1;j++){
                tri[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0]=tri[0][0];
        for(int i=0;i<N-1;i++){
            for(int j=0;j<i+1;j++){
                //왼쪽 대각선 (본인과 열 값, j가 같음)
                dp[i+1][j]=Math.max(dp[i+1][j],dp[i][j]+tri[i+1][j]);

                //오른쪽 대각선
                dp[i+1][j+1]= Math.max(dp[i+1][j+1],dp[i][j]+tri[i+1][j+1]);
            }
        }

        int max=0;
        for(int i=0;i<N;i++){
            if(max<dp[N-1][i]) max=dp[N-1][i];
        }

        System.out.print(max);
    }
}
