package com.day0907;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2468_SafeArea { 
	
	private static int N, max, water;
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};	//상우하좌 (시계방향)
	private static int[][] map;
	private static boolean[][] drown, visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		drown = new boolean[N][N];	//침수되면 True 
		visited = new boolean[N][N];
		max = 0;	//가장 높은 지역 
		water = 0;	//물의 높이 
		int answer = 1;	//안전한 영역의 최대 개수 
		
		//map에 저장 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}
		
		while(water < max) {	//수위가 높이가 가장 높은 지역보다 하나 낮으면 끝 (다 차면 안전영역 없음)
			water++;	//수위를 높인다.
			int cnt = 0;	//수위가 오를 떄마다 안전한 영역의 개수 초기화 
			
			//visited 배열 초기화 
			for(int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			
			
			//침수 지역 표시 하기 
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] <= water) {	//높이가 수위 이하면 
						drown[i][j] = true;	// 침수됐음 
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!drown[i][j] && !visited[i][j]) {	//침수되지 않은 곳이면서 방문도 하지 않은 곳일 경우 
						cnt++;
						dfs(i, j);
					}
				}
			}
			
			answer = Math.max(answer, cnt);	//최대 개수 업데이트 
		}
		
		//출력 
		System.out.println(answer);
		

	}	//main 끝 
	
	private static void dfs(int x, int y) {
		
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >= 0 && ny >= 0 && nx < N && ny < N && !drown[nx][ny] && !visited[nx][ny] ) {
				dfs(nx, ny);
			}
			
		}
	}

}
