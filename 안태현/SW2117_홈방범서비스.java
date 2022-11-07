package com.Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2117_홈방범서비스 {

    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   //도시의 크기
            int M = Integer.parseInt(st.nextToken());   //하나의 집이 지불할 수 있는 비용
            int houseCnt = 0;   //마을에 있는 집의 수

            //지도에 집 입력
            map = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) ++houseCnt;
                }
            }

            int canPay = houseCnt * M;  //마을 모든 집이 낼 수 있는 총 비용

            int K = 1;  //처음 서비스 영역
            int serviceCost = 1;    //처음 서비스 비용
            int answer = 0;


            while(serviceCost <= canPay) {

                int house = cntHouse(K); //K범위 안에 가장 많은 집의 수

                if(serviceCost <= house * M) {
                    answer = Math.max(answer, house);
                }
                //다음을 위해서 업데이트
                ++K;
                serviceCost = K * K + ((K - 1) * (K - 1));
            }

            sb.append("#" + tc + " " + answer + "\n");
        }   //테스트 케이스 1개 끝
        System.out.println(sb);

    }   //main 끝

    private static int cntHouse(int K) {

        int maxCnt = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                int cnt = 0;

                //1. 가운데 가장 긴 곳 세기(가로방향)
                for(int j2 = j - (K-1); j2 <= j + (K-1); j2++) {
                    if(isIn(i, j2) && map[i][j2] == 1) ++cnt;
                }

                //2. 가장 긴 가로줄을 기존으로 윗부분 세기
                int decrease = 0;
                for(int i2 = i-1; i2 >= i-(K-1); i2--) {
                    ++decrease;
                    for(int j2 = j-(K-1)+decrease; j2 <= j+(K-1)-decrease; j2++) {
                        if(isIn(i2, j2) && map[i2][j2] == 1) ++cnt;
                    }
                }

                //3. 가장 긴 가로줄을 기준으로 아랫부분 세기
                decrease = 0;
                for(int i2 = i+1; i2 <= i+(K-1); i2++) {
                    ++decrease;
                    for(int j2 = j-(K-1)+decrease; j2 <= j+(K-1)-decrease; j2++) {
                        if(isIn(i2, j2) && map[i2][j2] == 1) ++cnt;
                    }
                }

                maxCnt = Math.max(cnt, maxCnt);
            }
        }

        return maxCnt;
    }

    private static boolean isIn(int i, int j) {
        if(i >= 0 && i < N && j >= 0 && j < N) {
            return true;
        } else {
            return false;
        }
    }
}
