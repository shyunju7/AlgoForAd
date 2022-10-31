package month10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501_Leave {
    static int[][] map;
    static int N;
    static int result=Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        map=new int[N][2];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            map[i][0]=Integer.parseInt(st.nextToken());
            map[i][1]=Integer.parseInt(st.nextToken());
        }

        work(0,0);

        System.out.println(result);
    }

    private static void work(int start,int sum) {
        if(start>=N){
            if(start==N) result=Math.max(result,sum);
            return;
        }

        //해당 날짜에 일을 한다.
        work(start+map[start][0],sum+map[start][1]);

        //일을 안할때
        work(start+1,sum);
    }
}
