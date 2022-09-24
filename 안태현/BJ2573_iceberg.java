package com.No3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2573_iceberg {
	
	private static int N, M, cntArea;
	private static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
	private static int[][] map, mapAfter;	//녹기 전, 녹은 후 (동시 처리를 위해서)
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		int year = 0;
		map = new int[N][M];
		mapAfter = new int[N][M];
		
		//입력 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mapAfter[i][j] = map[i][j];
			}
		}
		
		while(true) {
			year++;
			cntArea = 0;
			
			//빙하 녹이기 
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					
					int cnt0 = 0;
					
					for(int k = 0; k < 4; k++) {
						int ni = i + dx[k];
						int nj = j + dy[k];
						
						if(isIn(ni, nj)) {
							if(map[ni][nj] == 0) cnt0++;
						}
					}
					
					mapAfter[i][j] -= cnt0;
					
					if(mapAfter[i][j] < 0) mapAfter[i][j] = 0;
					
				}
			}
			
			copyMap();	//녹은 후의 맵을 맵에 저장해줌 
			
			//dfs로 탐색해서 빙산구역 카운트 
			visited = new boolean[N][M];
			for(int  i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] != 0 && !visited[i][j]) {
						cntArea++;
						dfs(i, j);
					}
				}
			}
			//두 덩어리가 나오면 answer = year하고 Break
			if(cntArea >= 2) {
				answer = year;
				break;
			}
			
			//모든 빙하가 0이면 while문 탈출 
			if(isAll0()) break;
			
		}	//while문 끝 
		
		System.out.println(answer);
		
	}
	
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(isIn(nx, ny) && map[nx][ny] != 0 && !visited[nx][ny] ) {
				dfs(nx, ny);
			}
			
		}
	}
	
	private static boolean isIn(int i, int j) {
		if(i >= 0 && j >= 0 && i < N && j < M)
			return true;
		else
			return false;
	}
	
	//빙산이 모두 0인지 확인 
	private static boolean isAll0() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] > 0) return false;
			}
		}
		return true;
	}
	
	private static void copyMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = mapAfter[i][j];
			}
		}
	}

}
