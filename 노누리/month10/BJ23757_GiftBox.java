import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ23757 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> gift=new PriorityQueue<>(Collections.reverseOrder());
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            gift.add(Integer.parseInt(st.nextToken()));
        }

        int[] want=new int[M];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            want[i]=Integer.parseInt(st.nextToken());
        }

        boolean flag=false;
        for(int i=0;i<M;i++){
            int g=gift.poll();
            int w=want[i];

            if(g>w){
                gift.add(g-w);
            }else if(g<w){
                flag=true;
                break;
            }
        }

        if(!flag){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
}
