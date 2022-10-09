package com.Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16243_인구이동 {
	
	private static int N, L, R;
	private static int[] dr = {1, 0, 0, -1}, dc = { 0, 1, -1, 0};	//하우좌상 
	private static int[][] popul;
	private static boolean[][] visited;
	private static boolean isPossible;
	private static ArrayList<Pos> countries;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());	//인구 차가 L 이상 
		R = Integer.parseInt(st.nextToken());	//인구 차가 R 이하 
		
		popul = new int[N][N];
		visited = new boolean[N][N];
		
		//입력 받기 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				popul[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;	//모두 완료 되는데에 걸리는 날짜 
		
		while(true) {
			isPossible = false;
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						bfs(new Pos(i, j));
					}
				}
			}
			
			if(!isPossible) break;
			answer++;
			
		}
		
		System.out.println(answer);
		
	}	//main 끝 

	private static void bfs(Pos p) {
		
		Queue<Pos> q = new LinkedList<>();
		countries = new ArrayList<Pos>();	//같은 영역에 있는 나라 좌표들 저장 
		countries.clear();
		countries.add(p);
		visited[p.r][p.c] = true;
		q.offer(p);
		int sum = popul[p.r][p.c];
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(isIn(nr, nc)) {
					int dif = Math.abs(popul[r][c] - popul[nr][nc]);	//나라 간의 인구 수 차이 
					if(!visited[nr][nc] && dif >= L && dif <= R) {
						visited[nr][nc] = true;
						q.offer(new Pos(nr, nc));
						countries.add(new Pos(nr, nc));	//지금 탐색중인 영역에 포함된 나라를 배열에 저장 
						sum += popul[nr][nc];	//현재 영역의 총 인구수 구하기 
					}
				}
			}
		}
		
		//평균으로 업데이트 
		int size = countries.size();
		if(size > 1) {
			int avg = sum / size;
			for(int i = 0; i < size; i++) {
				Pos country = countries.get(i);
				popul[country.r][country.c] = avg;
			}
			isPossible = true;
		}
	}
	
	private static boolean isIn(int r, int c) {
		if(r >= 0 && c >= 0 && r < N && c < N) return true;
		else return false;
	}
	
	private static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
