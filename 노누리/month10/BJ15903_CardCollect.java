package month10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ15903_CardCollect {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        PriorityQueue<Long> cards=new PriorityQueue<>();

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            cards.add(Long.parseLong(st.nextToken()));
        }

        for(int i=0;i<m;i++){
            long a=cards.poll();
            long b=cards.poll();
            cards.add(a+b);
            cards.add(a+b);
        }

        long sum=0;
        while(!cards.isEmpty()){
            sum+=cards.poll();
        }

        System.out.println(sum);
    }
}
