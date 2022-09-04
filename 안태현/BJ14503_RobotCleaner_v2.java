package com.day0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503_RobotCleaner_v2 {
	
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};	//회전: 북동남서 - 좌하우상 
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
		
		loop:
		while(true) {
			
//			//확인 출력////////////////////////////////////////////////////////////
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < M; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("----------------------------------------");
//			//////////////////////////////////////////////////////////////////////
			
			//왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진 
			for(int i = 0; i < 4; i++) {
				//로봇청소기 먼저 회전 
				d--;
				if(d < 0) d = 3;
				if(r+dx[d] >= 0 && r+dx[d] < N && c+dy[d] >= 0 && c+dy[d] < M && map[r+dx[d]][c+dy[d]] == 0) {
					r += dx[d];
					c += dy[d];
					map[r][c] = 5;
					answer++;
					continue loop;
				}
			}
			
			//위의 for문을 모두 통과했으므로 청소할 공간이 없다고 판단.
			//뒤로 갈 수 있다면 뒤로 가고 다시 청소 
			if(r+backDx[d] >= 0 && r+backDx[d] < N && c+backDy[d] >= 0 && c+backDy[d] < M && map[r+backDx[d]][c+backDy[d]] == 5) {
				r += backDx[d];
				c += backDy[d];
			}
			//뒤로 갈 수 없다면 종료 
			else if(r+backDx[d] < 0 || r+backDx[d] >= N || c+backDy[d] < 0 || c+backDy[d] >= M || map[r+backDx[d]][c+backDy[d]] == 1) {
				break;
			}
			else System.out.println("err");
			
		}	//while 끝 
		
		System.out.println(answer);

	}	//main 끝 

}
