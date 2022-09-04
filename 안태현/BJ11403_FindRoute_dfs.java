package com.day0830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11403_FindRoute_dfs {
	
	private static int N;
	private static int[][] adjMatrix;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N][N];	//유향그래프
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}	//입력 끝
		
		for(int from = 0; from < N; from++) {
			for(int to = 0; to < N; to++) {
				visited = new boolean[N];	//방문관리 초기화
				if(to == N-1) {
					sb.append(dfs(from, to, 0));					
				} else {
					sb.append(dfs(from, to, 0) + " ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}	//메인 끝
	
	private static int dfs(int cur, int to, int cnt) {
		
		if(cnt > 0 && cur == to) return 1;
		visited[cur] = true;
		
		for(int i = 0; i < N; i++) {
			if(!visited[i] && adjMatrix[cur][i] == 1) {
				dfs(i, to, cnt++);
			}
		}
		
		return 0;
	}

}
