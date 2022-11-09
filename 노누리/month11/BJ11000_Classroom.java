package month11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11000_Classroom {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        long ans=0;

        // 강의실
        PriorityQueue<Long> queue=new PriorityQueue<>();
        // 수업
        long[][] course=new long[N][2];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            course[i][0]=Long.parseLong(st.nextToken());
            course[i][1]=Long.parseLong(st.nextToken());
        }

        Arrays.sort(course, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[0]==o2[0]){
                    return (int) (o1[1]-o2[1]);
                }
                return (int) (o1[0]-o2[0]);
            }
        });

        for(int i=0;i<N;i++){
            if(queue.isEmpty()){
                ans++;
                queue.offer(course[i][1]);
                continue;
            }

            // 현재 사용중인 강의실이 끝나는 시간보다 다음 수업의 시작 시간이 빠르다면
            if(course[i][0]<queue.peek()){
                ans++;
                queue.offer(course[i][1]);
            }else{
                queue.poll();
                queue.offer(course[i][1]);
            }
        }

        System.out.println(ans);
    }
}

