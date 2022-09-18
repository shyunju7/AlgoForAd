package com.No2.day0917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2615_Omok_4way { 
	
	private static int cnt;
	private static int[] dx = {-1, 0, 1, 1}, dy = {1, 1, 1, 0};		//4방탐색(오위, 오, 오밑, 밑)
	private static int[] odx = {1, 0, -1, -1}, ody = {-1, -1, -1, 0};	//4방 반대쪽 (왼밑, 왼, 왼위, 위)
	private static int[][] map = new int[19][19];

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		//바둑판 입력 받기 
		for(int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//탐색 시작 
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(map[i][j] != 0) {	//0이 아닐 경우만 탐색 
					
					for(int d = 0; d < 4; d++) {
						if(isFirst(i, j, map[i][j], d)) {
							cnt = 0;
							
							Search(i, j, map[i][j], d);
							
							if(cnt == 5) {	//5개가 놓여져 있을 경우 (6개 이상은 안됨)
								System.out.println(map[i][j]);	//어떤 색이 이겼는가 
								System.out.println((i + 1) + " " + (j + 1));	//index번호를 "번째"형식으로 변경하여 출력  
								
								return;	//종료시켜버려 
							}
						}
					}	
				}
			}
		}
		
		System.out.println(0);	//승부가 안났음 
		
	}	//main 끝 
	
	//일렬로 되었는지 탐색 (재귀)
	private static void Search(int i, int j, int num, int dir) {
		
		if(map[i][j] == num) {
			cnt++;
			
			int nx = i + dx[dir];
			int ny = j + dy[dir];
			
			if(nx >= 0 && ny >= 0 && nx < 19 && ny < 19) {	//바둑판 범위 안일 경우 
					Search(nx, ny, num, dir);
			} else {
				return;
			}
		}
	}
	
	private static boolean isFirst(int i, int j, int num, int dir) {
		
		int nx = i + odx[dir];
		int ny = j + ody[dir];
		
		//탐색하려는 방향 반대쪽이 배열 안에 없거나 지금 찾는 돌(숫자)가 아니면 true)
		if(!(nx >= 0 && ny >= 0 && nx < 19 && ny < 19) || map[nx][ny] != num) {
			return true;
		} else {
			return false;
		}
		
	}

}
