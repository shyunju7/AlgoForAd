import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2075_Nst {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue=new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                queue.add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=0;i<N-1;i++){
            queue.poll();
        }

        System.out.println(queue.peek());
    }
}

/*

// 시간초과 발생
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        Integer[] number=new Integer[N*N];

        int idx=0;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                number[idx++] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(number, Collections.reverseOrder());

        System.out.println(number[N-1]);
    }
}

* */

