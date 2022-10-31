package month10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14889_StartLink {
    static int[][] map;
    static boolean[] selected;
    static int N,result=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        selected=new boolean[N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        //조합
        team(0,0);

        System.out.println(result);
    }

    private static void team(int idx,int cnt) {
        if(cnt==N/2){
            int[] st=new int[N/2];
            int[] link=new int[N/2];
            int s=0,l=0;

            for(int i=0;i<N;i++){
                if(selected[i]){
                    st[s]=i;
                    s++;
                }else{
                    link[l]=i;
                    l++;
                }
            }

            //능력 계산
            int a=cal(st);
            int b=cal(link);

            result=Math.min(result,Math.abs(a-b));

            return;
        }

        for(int i=idx;i<N;i++){
            selected[i]=true;
            team(i+1,cnt+1);
            selected[i]=false;
        }
    }

    private static int cal(int[] team) {
        int sum=0;

        for(int i=0;i<N/2;i++){
            int a=team[i];
            for(int j=i;j<N/2;j++){
                int b=team[j];
                sum+=map[a][b]+map[b][a];
            }
        }

        return sum;
    }
}
