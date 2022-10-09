package com.Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2589_보물섬 {
	
	private static int N, M, answer;
	private static int[] dr = {1, 0, -1, 0}, dc = {0, -1, 0, 1};
	private static char[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		
		answer = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'L') {	//모든 육지에서 bfs 탐색을 위해서 !visited 조건 뺌 
					int max = bfs(new Point(i, j, 0));	//bfs를 돌면서 가장 긴 거리 찾기 
					answer = Math.max(max, answer);
				}
			}
		}
		
		System.out.println(answer);
		
	}	//main 끝 
	
	private static int bfs(Point p) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(p);
		
		visited = new boolean[N][M];	//방문배열 초기화 
		visited[p.r][p.c] = true;
		
		int max = Integer.MIN_VALUE;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int ncnt = cnt + 1;
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 'L') {
					q.offer(new Point(nr, nc, ncnt));	//새로 탐색하는 곳의 거리도 확인 
					visited[nr][nc] = true;
					max = Math.max(max, ncnt);
				}
			}
		}
		return max;
	}
	
	private static boolean isIn(int r, int c) {
		if(r >= 0 && c >= 0 && r < N && c < M) return true;
		else return false;
	}

	private static class Point {
		int r, c, cnt;
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;	//여기까지 오는데에 몇번 왔는지 
		}
	}

}
