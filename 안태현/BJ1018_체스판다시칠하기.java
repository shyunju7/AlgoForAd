package com.Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1018_체스판다시칠하기 {
	
	private static int N, M;
	private static boolean[][] map;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//행 
		M = Integer.parseInt(st.nextToken());	//열 
		
		//W(흰색)이면 True, B(검정)이면 false
		map = new boolean[N][M];
		
		//입력 
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				if(input.charAt(j) == 'W') map[i][j] = true;
				else if(input.charAt(j) == 'B') map[i][j] = false;
			}
		}
		
		int ans = Integer.MAX_VALUE;
		
		//탐색 
		for(int i = 0; i < N-7; i++) {
			for(int j = 0; j < M-7; j++) {
				ans = Math.min(getCntWithWhite(i, j), ans);
				ans = Math.min(getCntWithBlack(i, j), ans);
			}
		}
		
		System.out.println(ans);
		
	}	//main 끝 

	private static int getCntWithWhite(int i, int j) {
		
		int cnt = 0;
		boolean haveTo = false;	//지금 들어온거(하양) 다음에 검정이 와야한다고 가정 
		
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 8; c++) {
				
				int nr = i + r;
				int nc = j + c;
				
				if(map[nr][nc] != haveTo) cnt++;
				
				haveTo = !haveTo;
				
			}
			haveTo = !haveTo;	//행 넘어갈때는 전줄 마지막 열 == 다음 줄 첫번째 열 이어야 하니까 다시 한번 !처리
		}
		
		return cnt;
	}
	
	private static int getCntWithBlack(int i, int j) {
		
		int cnt = 0;
		boolean haveTo = true;	//지금 들어온거(검정) 다음에 하양이 와야한다고 가정 
		
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 8; c++) {
				
				int nr = i + r;
				int nc = j + c;
				
				if(map[nr][nc] != haveTo) cnt++;
				
				haveTo = !haveTo;
				
			}
			haveTo = !haveTo;
		}
		
		return cnt;
	}
	
}
