package com.day0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14620 {
	static int N;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		System.out.println(min);
				
	}

	private static void dfs(int flower, int sum) {	//탐색
		if(flower == 3) {
			min = Math.min(min, sum);
			return;
		}
		
		else {
			for(int i=1; i<N-1; i++) {
				for(int j=1; j<N-1; j++) {
					if(!visited[i][j] && check(i, j)) {
						int cost = area_sum(i, j);
						dfs(flower+1, sum+cost);
						clear(i, j);	//현재 꽃이 피기전으로 되돌려 놓는다
					}
				}
			}
		}
	}
	
	private static boolean check(int r, int c) {	//동, 서, 남, 북 모두 꽃잎 들어갈 수 있는지 판단
		
		for(int k=0; k<4; k++) {
			int nx = r+dx[k];
			int ny = c+dy[k];
			
			if(visited[nx][ny]) return false;
		}
		
		return true;
		
	}
	
	private static int area_sum(int r, int c) {	//땅 비용 더하기
		int tmp = board[r][c];

		for(int k=0; k<4; k++) {
			int nx = r+dx[k];
			int ny = c+dy[k];
			
			visited[nx][ny] = true;				// 꽃잎핀거 처리
			tmp += board[nx][ny];
		}
		
		return tmp;
	}

	private static void clear(int r, int c) {	// 씨앗자리 포함해서 꽃잎 모두 없앰
		visited[r][c] = false;
		for(int k=0; k<4; k++) {
			int nx = r+dx[k];
			int ny = c+dy[k];
			
			visited[nx][ny] = false;

		}
	}

	

	

}
