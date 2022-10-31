package month10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ14235_Christmas {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue=new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());

            //아이들 만남 -> 선물 증정
            if(a==0){
                if(queue.isEmpty()) System.out.println(-1);
                else System.out.println(queue.poll());
            }

            //거점지 -> 선물 충전
            else{
                for(int j=0;j<a;j++) {
                    int gift=Integer.parseInt(st.nextToken());
                    queue.add(gift);
                }
            }
        }
    }
}
