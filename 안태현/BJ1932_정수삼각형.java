package com.Week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ1932_정수삼각형 {

    private static int n;
    private static int[][] triangle, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        //일단 행만 정해줌
        triangle = new int[n][];
        dp = new int[n][];

        //입력받기!
        for(int i = 0; i < n; i++) {
            triangle[i] = new int[i+1]; //삼각형 배열을 만들기 위해서
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }

            dp[i] = new int[i+1];
        }

        //밑에서부터 올라가면서 최댓값을 구해서 DP 배열에 저장시키기
        //초기값 세팅: dp가장 마지막 줄에만 triangle 복사
        for(int i = 0; i < triangle[n-1].length; i++) {
            dp[n-1][i] = triangle[n-1][i];
        }

        for(int i = n-1; i > 0; i--) {

            for(int j = 0; j < triangle[i].length; j++) {
                int curNum = dp[i][j];

                //윗줄 어떤 거랑 더할지
                //1. 왼쪽 위로 올라가기
                int index = j-1;
                if(index >= 0) {    //위의 배열 인덱스 값을 벗어나면 안됨
                    dp[i-1][index] = Math.max(dp[i-1][index], curNum + triangle[i-1][index]);
                }
                //2. 오른쪽 위로 올라가기
                index = j;    //오른쪽 위(index가 같음에 주의)
                if(index < triangle[i-1].length) {
                    dp[i-1][index] = Math.max(dp[i-1][index], curNum + triangle[i-1][index]);
                }
            }

        }

        System.out.println(dp[0][0]);

    }   //main 끝
}
