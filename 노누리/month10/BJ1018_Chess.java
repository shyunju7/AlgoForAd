import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1018 {
    public static void main(String[] args) throws IOException {
        char[][] bw={{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'}};

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        char[][] board=new char[N][M];

        for(int i=0;i<N;i++){
            board[i]=br.readLine().toCharArray();
        }

        int cnt=Integer.MAX_VALUE;
        for(int i=0;i<N-7;i++){
            for(int j=0;j<M-7;j++){
                int tmp1=0;
                int tmp2=0;
                for (int k = 0; k < 8; k++) {
                    for (int m = 0; m < 8; m++) {
                        if(board[i+k][j+m]!=bw[(k+1)%2][m]) tmp1++;
                        if(board[i+k][j+m]!=bw[k%2][m]) tmp2++;
                    }
                }

                cnt=Math.min(cnt,tmp1);
                cnt=Math.min(cnt,tmp2);
            }
        }

        System.out.println(cnt);
    }
}
