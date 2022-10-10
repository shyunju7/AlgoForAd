package com.Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ21278_호석이두마리치킨 {
	
	private static int N, M;
	private static int[] distances;
	private static boolean[] visited;
	private static boolean[][] adjMatrix;

	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//건물의 개수
		M = Integer.parseInt(st.nextToken());	//도로의 개수 
		
		adjMatrix = new boolean[N+1][N+1];	//건물들간의 인접행렬 true: 도로있음, false: 도로 없음.
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = adjMatrix[to][from] = true;	//무향 그래프 
		}
		
		int shortest = Integer.MAX_VALUE;
		int first = 0;
		int second = 0;
		
		distances = new int[N+1];	//치킨 집에서 해당 빌딩까지의 최단 거리 
		
		//각 빌딩들에서 bfs 탐색 
		for(int i = 1; i < N+1; i++) {	//첫번째 치킨집이 있을 빌딩 번호 
			for(int j = i+1; j < N+1; j++) {	//두번째 치킨 집이 있을 빌딩 번호 (i랑 안 겹치게)
				
				
				Arrays.fill(distances, -1);
				visited = new boolean[N+1];
				bfs(new Building(i, 0));
				visited = new boolean[N+1];
				bfs(new Building(j, 0));
				
				int sum = sumDistances() * 2;	//왕복이므로 * 2 
				
				if(sum < shortest) {
					shortest = sum;
					first = i;
					second = j;
				}
				
			}
		}
		
		System.out.println(first + " " + second + " " + shortest);
		
	}	//main 끝 
	
	private static int sumDistances() {
		int sum = 0;
		for(int i = 1; i < distances.length; i++) {
			sum += distances[i];
		}
		return sum;
	}

	private static void bfs(Building b) {
		Queue<Building> q = new LinkedList<>();
		q.offer(b);
		visited[b.num] = true;
		distances[b.num] = 0;	//치킨 집이 있는 빌딩은 거리가 0 
		
		while(!q.isEmpty()) {
			Building cur = q.poll();
			int num = cur.num;
			int distance = cur.distance;
			
			for(int i = 0; i < N+1; i++) {
				if(!visited[i] && adjMatrix[num][i]) {	//방문하지 않았고 인접 빌딩으로 갈 수 있다면 
					visited[i] = true;
					q.offer(new Building(i, distance+1));
					
					//처음으로 계산하고 있는 중이거나 지금 거리가 원래 거리보다 짧다면 갱신 
					if(distances[i] == -1 || distances[i] > distance+1) {
						distances[i] = distance+1;
						
					}
				}
			}
		}
		
	}	//bfs 끝 
	
	private static class Building {
		int num, distance;
		public Building(int num, int distance) {
			this.num = num;
			this.distance = distance;
		}
	}

}
