package com.day0901;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3085 {
	static int N;
	static char[][] candy;
	static int max = 0;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		candy = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				candy[i][j] = s.charAt(j);
			}
		}//입력완료
		
		for(int x=0; x<N; x++) {
			for(int y=0; y<N-1; y++) {
				char tmp = candy[x][y];
				candy[x][y] = candy[x][y+1];
				candy[x][y+1] = tmp;
				
				search();
				
				tmp = candy[x][y+1];
				candy[x][y+1] = candy[x][y];
				candy[x][y] = tmp;
			}
		}
		
		for(int x=0; x<N; x++) {
			for(int y=0; y<N-1; y++) {
				
				char tmp = candy[y][x];
				candy[y][x] = candy[y+1][x];
				candy[y+1][x] = tmp;
				
				search();
				
				tmp = candy[y][x];
				candy[y][x] = candy[y+1][x];
				candy[y+1][x] = tmp;
			}
		}
		
		System.out.println(max);
		
	}
	
	// 행 기준으로 인접한 값 교환
	private static void search() {
		//행 기준
		for(int i=0; i<N; i++) {
			int cnt_r=1;
			for(int j=1; j<N; j++) {
				if(candy[i][j-1] == candy[i][j]) {
					cnt_r++;
				}
				else {
					cnt_r = 1;
				}
				max = Math.max(cnt_r, max);
			}
			
		}
		

		//열 기준
		for(int i=0; i<N; i++) {
			int cnt_c=1;
			for(int j=1; j<N; j++) {
				if(candy[j-1][i] == candy[j][i]) {
					cnt_c++;
				}
				else {
					cnt_c = 1;
				}
				
				max = Math.max(max, cnt_c);
			}
		}
		
	}
	

}
