package com.No2.day0917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2615_Omok_8way {
	
	private static int cnt, startI, startJ;
	private static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0}, dy = {-1, 0, 1, 1, 1, 0, -1, -1};		//팔방 탐색 (왼위부터 시계방향)
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
				System.out.println("다음칸 >>>>>>>>>>>>>>>>>>>");
				if(map[i][j] != 0) {	//0이 아닐 경우만 탐색 
					
					for(int d = 0; d < 4; d++) {
						System.out.println("새로운방향 ---------------------");
						cnt = 0;
						startI = i;
						startJ = j;
						
						int od = d + 4;	//반대쪽의 dx,dy 인덱스 
						Search(i, j, map[i][j], d);		//왼위, 위, 오위, 오 
						Search(i, j, map[i][j], od);	//오밑, 밑, 왼밑, 왼 
						cnt--; //Search 함수를 거치면서 자기자신 카운트를 두번 해버리므로 한번 삭제 
						
						if(cnt == 5) {	//5개가 놓여져 있을 경우 (6개 이상은 안됨)
							System.out.println(">찐출력: " + startI);
							System.out.println(map[i][j]);	//어떤 색이 이겼는가 
							System.out.println((startI + 1) + " " + (startJ + 1));
							
							return;	//종료시켜버려 
						}
					}	
				}
			}
		}
		
		System.out.println(0);	//승부가 안났음 
		
	}	//main 끝 
	
	//일렬로 되었는지 탐색 
	private static void Search(int i, int j, int num, int dir) {
		
		if(map[i][j] == num) {
			cnt++;
			
			//시작 바둑알 찾기 
			if(dir == 2) {	//세로로 탐색하는 경우 (가장 위에 있는 것)
				startI = Math.min(startI, i);
				System.out.println("dir2: " + dir + ", startI: " + startI);
			} else if(dir == 7 || dir == 3) {	//우상향 그래프처럼 탐색하는 경우 (j는 작고 i는 젤 큰걸로)
				startI = Math.max(startI, i);
				System.out.println("dir7: " + dir +  "!!!startI!!!: " + startI);
				startJ = Math.min(startJ, j);
			} else {	//나머지는 i도 작고 j도 작으면 됨 
				startI = Math.min(startI, i);
				System.out.println("dirElse: " + dir + ", startI: " + startI);
				startJ = Math.min(startJ, j);
			}
			
			int nx = i + dx[dir];
			int ny = j + dy[dir];
			
			if(nx >= 0 && ny >= 0 && nx < 19 && ny < 19) {	//바둑판 범위 안일 경우 
					System.out.println("NEW 탐색! ++++++++++++++");
					Search(nx, ny, num, dir);
			} else {	//바둑판 범위 안이 아니라면 
				return;
			}
		}
			
		
	}

}
