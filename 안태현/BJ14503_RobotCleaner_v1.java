package com.day0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503_RobotCleaner_v1 {
	
	private static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};	//회전: 북동남서 - 좌하우상 
	private static int[] backDx = {1, 0, -1, 0}, backDy = {0, -1, 0, 1};	//뒤로: 북동남서 - 하좌상우 

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());	//0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽 
		
		//맵에 저장 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	//청소안함: 0, 벽: 1, 청소함: 5 
			}
		}
		
		//로봇청소기 첫 위치 반영 
		int answer = 1;
		map[r][c] = 5;
		
		while(true) {
			
			//확인 출력////////////////////////////////////////////////////////////
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("----------------------------------------");
			//////////////////////////////////////////////////////////////////////
						
			//현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 탐색을 진행한다.
			for(int i = 0; i < 4; i++) {
				//왼쪽 방향에 아직 청소하지 않은 공간(배열내부)이 존재한다면, 그 방향으로 회전한 다음 한칸을 전진.
				if(r+dx[d] >= 0 && r+dx[d] < N && c+dy[d] >= 0 && c+dy[d] < M && map[r+dx[d]][c+dy[d]] == 0) {
					r = r+dx[d];
					c = r+dy[d];
					map[r][c] = 5;
					answer++;
					d--;
					if(d < 0) d = 3;
					break;
				}
				d--;
				if(d < 0) d = 3;
			}
			
			//네방향 모두 청소가 이미 되있거나 벽인 경우에는, 바라보는 방향을 유지한채로 한칸 후진
			boolean isClean = true;
			for(int i = 0; i < 4; i++) {
				if(r+dx[d] >= 0 && r+dx[d] < N && c+dy[d] >= 0 && c+dy[d] < M && map[r+dx[d]][c+dy[d]] == 0) {
					isClean = false;
					break;
				}
			}
			
			if(isClean) {	//네방향이 모두 청소가 되어있다면 뒤로 한칸, 방향은 유지 
				//뒤가 벽이 아니라면 뒤로 간다!
				if(r+dx[d] >= 0 && r+dx[d] < N && c+dy[d] >= 0 && c+dy[d] < M && map[r+dx[d]][c+dy[d]] != 1) {
					r = r + backDx[d];
					c = c + backDy[d];
					continue;
				} else {
					break;	//뒤가 벽이므로 로봇청소기 중지 
				}
			}
			
			
		}
		
		System.out.println(answer);

	}	//main 끝 

}
