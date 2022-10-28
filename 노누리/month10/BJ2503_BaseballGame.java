import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2503 {

    public static class Baseball{
        int num,S,B;

        public Baseball(int num, int s, int b) {
            this.num = num;
            S = s;
            B = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        Baseball[] ball=new Baseball[N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            int num=Integer.parseInt(st.nextToken());
            int S=Integer.parseInt(st.nextToken());
            int B=Integer.parseInt(st.nextToken());

            ball[i]=new Baseball(num,S,B);
        }

        int answer=0;

        for(int i=1;i<10;i++){
            for(int j=1;j<10;j++){
                for(int k=1;k<10;k++){
                    if(i==j || j==k || k==i) continue;

                    int number=i*100+j*10+k;

                    //야구 게임 조건이 맞는지 확인
                    if(base(number,ball)) answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean base(int number,Baseball[] ball) {
        for(int d=0;d<ball.length;d++){
            boolean[] check=new boolean[3];
            //strike 개수 만큼 일치하는 숫자들이 있는지
            int S_cnt=0;
            for(int i=2;i>=0;i--){
                int a=ball[d].num/(int)Math.pow(10,i)%10;
                int b=number/(int)Math.pow(10,i)%10;

                if(a==b) {
                    S_cnt++;
                    check[2-i]=true;
                }
            }

            //strike 수가 같지 않으면 false 반환 후 다음 수 체크
            if(S_cnt!=ball[d].S) return false;

            //ball 개수 만큼 일치하는 숫자들이 있는지(strike한 숫자 제외)
            int B_cnt=0;
            for(int i=2;i>=0;i--){
                //strike 체크 안된 숫자 위치
                if(!check[2-i]) {
                    for (int j = 2; j >=0; j--) {
                        if(i!=j){
                            int a=ball[d].num/(int)Math.pow(10,i)%10;
                            int b=number/(int)Math.pow(10,j)%10;

                            if(a==b) B_cnt++;
                        }
                    }
                }
            }

            if(B_cnt!=ball[d].B) return false;
        }

        return true;
    }
}
