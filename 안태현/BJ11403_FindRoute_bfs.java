package com.day0830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class BJ11403_FindRoute_bfs {
	
	private static int N;
	private static int[][] adjMatrix;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N][N];	//유향그래프
		int[][] answer = new int[N][N];	//답 저장할 이차원배열
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}	//입력 끝
		
		//탐색
		for(int from = 0; from < N; from++) {
			for(int to = 0; to < N; to++) {
				visited = new boolean[N];	//방문 관리 초기화
				answer[from][to] = bfs(from, to);
			}
		}

		//출력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}

	}	//메인 끝
	
	private static int bfs(int from, int to) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		int cnt[] = new int[N];
		
//		visited[from] = true;	//자기자신을 방문처리해버리면 다시 안돌아옴!!!!
		queue.offer(from);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(cur == to && cnt[cur] > 0) return 1;	//현재 정점과 목적지가 같다면 1 반환.
													//자기 자신 그대로는 안되니까 다른 정점을 거쳤는가 확인(cnt)
			for(int i = 0; i < N; i++) {
				if(!visited[i] && adjMatrix[cur][i] != 0) {
					visited[i] = true;
					queue.offer(i);
					cnt[i] = cnt[cur] + 1;
				}
				
			}
		}
		
		return 0;
	}	//bfs 끝

}







