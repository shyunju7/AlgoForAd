import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14620 {
    static int[][] moves={{0,1},{0,-1},{1,0},{-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        int[][] garden=new int[N][N];
        int result=Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                garden[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        //3개의 꽃 위치 찾기
        //화단밖으로 나가면 안되기 때문에 (1,1)~(N-2,N-2) 까지 탐색
        for(int i=1;i<N-1;i++){
            for(int j=1;j<N-1;j++){
                for(int a=1;a<N-1;a++){
                    for(int b=1;b<N-1;b++){
                        for(int c=1;c<N-1;c++){
                            for(int d=1;d<N-1;d++){
                                if((i==a && j==b) || (a==c && b==d) || (i==c && j==d)) continue;
                                //꽃이 모두 닿이지 않는지 체크
                                if(canPlant(i,j,a,b,c,d)){
                                    //꽃 심는 비용 계산
                                    int tmp=planting(garden,i,j,a,b,c,d);
                                    result=Math.min(result,tmp);
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static boolean canPlant(int i, int j, int a, int b, int c, int d) {
        //대각선
        int[][] dir={{-1,-1},{-1,1},{1,-1},{1,1}};
        for(int[] di:dir){
            if(i+di[0]==a && j+di[1]==b) return false;
            if(a+di[0]==c && b+di[1]==d) return false;
            if(c+di[0]==i && d+di[1]==j) return false;
        }

        //위아래 2칸차이,좌우 2칸차이
        int[][] updown={{-1,0},{1,0},{0,-1},{0,1}};
        for(int[] ud:updown){
            for(int x=1;x<3;x++){
                if(i+ud[0]*x==a && j+ud[1]*x==b) return false;
                if(a+ud[0]*x==c && b+ud[1]*x==d) return false;
                if(c+ud[0]*x==i && d+ud[1]*x==j) return false;
            }
        }

        return true;
    }

    private static int planting(int[][] garden,int i, int j, int a, int b, int c, int d) {
        int money=garden[i][j]+garden[a][b]+garden[c][d];

        for(int[] m:moves){
            money+=garden[i+m[0]][j+m[1]];
            money+=garden[a+m[0]][b+m[1]];
            money+=garden[c+m[0]][d+m[1]];
        }

        return money;
    }
}
