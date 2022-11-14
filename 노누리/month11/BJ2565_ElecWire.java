package month11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ2565_ElecWire {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int[][] wire=new int[N][2];
        int[][] dp=new int[N][2];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            wire[i][0]=Integer.parseInt(st.nextToken());
            wire[i][1]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(wire, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        dp[0][0]=1;
        dp[0][1]=wire[0][1];
        int max=1;
        for(int i=1;i<N;i++){
            dp[i][0]=1;
            dp[i][1]=wire[i][1];

            for(int j=0;j<N;j++){
                if(wire[j][1]<wire[i][1]){
                    if(dp[i][0]<dp[j][0]+1){
                        dp[i][0]=dp[j][0]+1;
                        dp[i][1]=dp[j][1];
                    }
                }
            }
            max=Math.max(max,dp[i][0]);
        }

        System.out.println(Arrays.deepToString(dp));
        System.out.println(max);
    }
}
