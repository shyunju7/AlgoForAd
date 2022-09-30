import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ2304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int[][] square=new int[N][2];
        int max=Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            square[i][0]=Integer.parseInt(st.nextToken());
            square[i][1]=Integer.parseInt(st.nextToken());

            if(max<square[i][1]) max=square[i][1];
        }

        Arrays.sort(square, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        //max의 높이인 기둥이 여러개일 수 있음
        ArrayList<Integer> list=new ArrayList<>();
        //idx 위치 찾기
        for(int i=0;i<N;i++){
            if(square[i][1]==max){
                list.add(i);
            }
        }

        //list 모두 사용
        int result=0;
        int maxArea=0;


        int x1=square[0][0];
        int y1=square[0][1];
        //max 높이의 좌
        for(int i=1;i<=list.get(0);i++){
            if(y1<square[i][1]){
                //높이가 더 높을때
                result+=y1*(square[i][0]-x1);

                x1=square[i][0];
                y1=square[i][1];
            }
        }

        int x2=square[N-1][0];
        int y2=square[N-1][1];
        //max 높이의 우
        for(int i=N-2;i>=list.get(list.size()-1);i--){
            if(y2<square[i][1]){
                result+=y2*(x2-square[i][0]);

                x2=square[i][0];
                y2=square[i][1];
            }
        }

        if(list.size()==1){
            maxArea=max;
        }else {
            int a = list.get(list.size() - 1);
            int b = list.get(0);
            maxArea = max * (square[a][0] - square[b][0] + 1);
        }

        System.out.println(result+maxArea);
    }
}
