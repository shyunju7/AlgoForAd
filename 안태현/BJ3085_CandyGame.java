package com.day0901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//CallByValue와 CallByReference에 대해서 알아보기

public class BJ3085_CandyGame {
	
	private static int N, max;
	private static char[][] mapCopy;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
//		mapCopy = new char[N][N];
		max = Integer.MIN_VALUE;
		
		//입력
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		mapCopy = map;
		
//		//출력 확인
//		for(int m = 0; m < N; m++) {
//			for(int n = 0; n < N; n++) {
//				System.out.print(mapCopy[m][n] + " ");
//			}
//			System.out.println();
//		}
		
		//두칸을 가로로 바꾸기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N-1; j++) {
//				mapCopy = map;
				
				//스왑!
				char temp = mapCopy[i][j];
				mapCopy[i][j] = mapCopy[i][j+1];
				mapCopy[i][j+1] = temp;
				
				findMax();
				
				//원상복구!
				temp = mapCopy[i][j];
				mapCopy[i][j] = mapCopy[i][j+1];
				mapCopy[i][j+1] = temp;
			}
		}
		
		//두칸을 세로로 바꾸기
		for(int j = 0; j < N; j++) {
			for(int i = 0; i < N-1; i++) {
//				mapCopy = map;	//얕은 복사?
				
				//스왑!
				char temp = mapCopy[i][j];
				mapCopy[i][j] = mapCopy[i+1][j];
				mapCopy[i+1][j] = temp;
				
				findMax();

				//원상복구!
				temp = mapCopy[i][j];
				mapCopy[i][j] = mapCopy[i+1][j];
				mapCopy[i+1][j] = temp;
			}
		}
		
		System.out.println(max);
		
	}	//main 끝
	
	//최대로 먹을 수 있는 사탕 개수 찾
	private static void findMax() {
		int cnt = 1;
		//행으로 다 탐색
		for(int i = 0; i < N; i++) {
			cnt = 1;
			for(int j = 0; j < N-1; j++) {
				if(mapCopy[i][j] != mapCopy[i][j+1]) {
					cnt = 1;
					continue;
				}
				cnt++;
				max = Math.max(max, cnt);
			}
		}

		//열로 다 탐색
		for(int j = 0; j < N; j++) {
			cnt = 1;
			for(int i = 0; i < N-1; i++) {
				if(mapCopy[i][j] != mapCopy[i+1][j]) {
					cnt = 1;
					continue;
				}
				cnt++;
				max = Math.max(max, cnt);
			}
		}
		
	}

}
