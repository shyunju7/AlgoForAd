import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14891_2 {
    static char[][] gear;
    static int[] rotation;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int result=0;

        gear=new char[4][8];

        for(int i=0;i<4;i++){
            gear[i]=br.readLine().toCharArray();
        }

        int K=Integer.parseInt(br.readLine());

        //톱니바퀴 회전
        for(int k=0;k<K;k++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken())-1;
            int b=Integer.parseInt(st.nextToken());
            rotation=new int[4];

            rotation[a]=b;

            //왼쪽
            for(int i=a;i>0;i--){
                if(rotation[i]==2){
                    rotation[i-1]=2;
                    continue;
                }

                //다른 극
                if(gear[i][6]!=gear[i-1][2]){
                    rotation[i-1]=-rotation[i];
                }else{
                    rotation[i-1]=2;
                }
            }

            //오른쪽
            for(int i=a;i<3;i++){
                if(rotation[i]==2){
                    rotation[i+1]=2;
                    continue;
                }

                //다른 극
                if(gear[i][2]!=gear[i+1][6]){
                    rotation[i+1]=-rotation[i];
                }else{
                    rotation[i+1]=2;
                }
            }

            rotate();

            //마지막 회전일때 점수 계산
            if(k==K-1){
                for(int i=0;i<4;i++){
                    if(gear[i][0]=='1'){
                        result+=Math.pow(2,i);
                    }
                }
            }
        }

        System.out.println(result);
    }

    //톱니바퀴 회전하기
    private static void rotate() {

        for(int r=0;r<4;r++) {
            //1이면 시계방향
            if (rotation[r]==1){
                char tmp = gear[r][7];
                for (int i = 7; i > 0; i--) {
                    gear[r][i] = gear[r][i - 1];
                }
                gear[r][0] = tmp;
            }

            //-1이면 반시계방향
            else if (rotation[r] == -1) {
                char tmp = gear[r][0];
                for (int i = 0; i < 7; i++) {
                    gear[r][i] = gear[r][i + 1];
                }
                gear[r][7] = tmp;

            }
        }

    }
}
