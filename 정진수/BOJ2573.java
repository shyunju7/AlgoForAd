package com.day0906;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {

	static int N,M;
	static int[][] cur_sea;
	static int[][] modify_sea;
	static boolean[][] visited;
	static int cnt, lump;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cur_sea = new int[N][M];
		modify_sea = new int[N][M];
		visited = new boolean[N][M];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				cur_sea[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 완료
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				modify_sea[i][j] = cur_sea[i][j];
			}
		}
		
		while(true) {	//무한루프 시작
			int ice = 0;
			cnt++;
			
			for(int i=1; i<N-1; i++) {
				for(int j=1; j<M-1; j++) {
					if(cur_sea[i][j] != 0) {
						check(i, j);
					}
				}
			}
			
			//System.out.println(Arrays.deepToString(modify_sea));
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!visited[i][j] && modify_sea[i][j] != 0) {
						bfs(i, j);
					}
				}
			}
			
			//System.out.println(lump);
			
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					ice += modify_sea[i][j];
				}
			}
			
			//System.out.println(ice);
			if(lump >= 2) break;
			if(ice == 0) {
				cnt = 0;
				break;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					cur_sea[i][j] = modify_sea[i][j];
				}
			}
			
			visited = new boolean[N][M];
			lump=0;
			ice=0;
			
		}
		
		
		System.out.println(cnt);
	}

	private static void bfs(int r, int c) {
		
		Queue<Point> q = new ArrayDeque<>();
		
		q.offer(new Point(r, c));
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			
			for(int k =0 ; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && modify_sea[nx][ny] != 0) {
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
				}
			}
		}
		
		lump++;
		
	}

	private static void check(int r, int c) {
		int cnt = 0;
		
		for(int k=0; k<4; k++) {
			int nx = r+dx[k];
			int ny = c+dy[k];
			
			if(cur_sea[nx][ny] == 0) {
				cnt++;
			}
		}
		
		modify_sea[r][c] -= cnt;
		
		if(modify_sea[r][c] < 0) {
			modify_sea[r][c] = 0;
		}
	}

}
