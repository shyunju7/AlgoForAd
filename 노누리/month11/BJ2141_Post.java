package month11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2141_Post {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N=Integer.parseInt(br.readLine());
        PriorityQueue<Long[]> queue=new PriorityQueue<>(Comparator.comparing(o1->o1[0]));

        long total=0;

        for(long i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            long a=Long.parseLong(st.nextToken());
            long b=Long.parseLong(st.nextToken());

            total+=b;
            Long[] l=new Long[2];
            l[0]=a;
            l[1]=b;
            queue.add(l);
        }

        //양팔 저울 생각하여 양 옆의 무게의 차이가 최소일 때 중심을 찾을 수 있음
        //이때, 총 인구수의 중간값쯤에서 무게차이가 최소일 것을 예상할 수 있기 때문에
        //총 인구수의 중간값을 지나면 break해줌
        long tmp=0;
        long ans=queue.peek()[0];
        for(long i=0;i<N;i++){
            Long[] l=queue.poll();
            tmp+=l[1];

            if(tmp>=(long) Math.ceil((double)total/(double)2)){
                ans=l[0];
                break;
            }
        }

        System.out.print(ans);
    }
}

//시간초과 발생
/*public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N=Integer.parseInt(br.readLine());
    long[][] loc=new long[N][2];

    for(int i=0;i<N;i++){
        st=new StringTokenizer(br.readLine());
        loc[i][0]=Long.parseLong(st.nextToken());
        loc[i][1]=Long.parseLong(st.nextToken());
    }

    //마을위치 정렬
    Arrays.stream(loc).sorted();
    long ans=Integer.MAX_VALUE;
    long idx=0;

    for(int i=0;i<N;i++){
        long tmp_l=0;
        long tmp_r=0;
        for(int j=0;j<i;j++){
            tmp_l+=loc[j][1];
        }

        for(int k=i+1;k<N;k++){
            tmp_r+=loc[k][1];
        }

        if(Math.abs(tmp_r-tmp_l)<ans){
            ans=Math.abs(tmp_r-tmp_l);
            idx=i;
        }
    }

    System.out.println(idx+1);

}*/
