package baekjoon.silver.exam14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14501. 퇴사
 * 송현주
 * 미해결
 */

public class Solution14501H {

    static int[][] plan;
    static int N, answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        plan = new int[N + 1][2];

        // 입력
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            plan[i][0] = Integer.parseInt(st.nextToken());
            plan[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            consult(i);
            System.out.println("================================================================");
        }

        System.out.println(answer);
    }

    // 탐색
    private static void consult(int start) {
        int pay = 0;
        for (int k = start; k <= N; ) {
            if (k + plan[k][0] > N + 2) {
                break;
            }
            pay += plan[k][1];
            k += plan[k][0];
            System.out.println("k = " + k + " pay = " + pay);
        }
        answer = Math.max(pay, answer);
    }
}
