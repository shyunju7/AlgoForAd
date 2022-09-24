package com.day0906;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2468 {
	
	static int N;
	static int[][] area;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0 ,0};
	static int[] dy = {0, 0, -1, 1};
	static int compare = 0;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		area = new int[N][N];
		visited = new boolean[N][N];
		int tmp = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				
				if(area[i][j] > tmp) {
					tmp = area[i][j];
				}
			}
		} //입력완료
		
		
		int cnt = 1;
		while (cnt <= tmp) {
			
			for(int i=0; i<N; i++) {		//수면 높이보다 이하면 0으로 잠김표시
				for(int j=0; j<N; j++) {
					if (area[i][j] <= cnt)
						area[i][j] = 0;
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if (area[i][j] != 0 && !visited[i][j]) {	//현재 지역이 안잠겼으면 dfs 시작
						bfs(i, j);
						//compare += dfs(i, j);
					}
				}
			}
			
			max = Math.max(max, compare);
			compare = 0;
			cnt++;
			visited = new boolean[N][N];	//수면 높이 바뀔 때 마다 초기화
			
			
		}
		
		if (tmp == 1) {
			System.out.println(1);
		}
		else {
			System.out.println(max);
		}
		
		
	}

	
	/*
	private static int dfs(int i, int j) {
		
		for(int k=0; k<4; k++) {
			int nx = i+dx[k];
			int ny = j+dy[k];
			
			if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && area[nx][ny] != 0) {
					visited[nx][ny] = true;
					dfs(nx, ny);
			}
			
		}
		
		return 1;
		
	}
	*/
	
	
	private static void bfs(int i, int j) {
		Queue<Point> q = new ArrayDeque<>();
		
		q.offer(new Point(i, j));

		while (!q.isEmpty()) {
			
			Point cur = q.poll();
			
			int x = cur.x;
			int y = cur.y;
			
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				
				if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && area[nx][ny] != 0) {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny));
				}
				
			}
		}
		
		compare++;
		
	}
	
}
