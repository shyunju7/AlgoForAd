package com.day0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14891_Gear {
	
	private static int[][] qList;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] q1 = new int[8];
		int[] q2 = new int[8];
		int[] q3 = new int[8];
		int[] q4 = new int[8];
				
		//톱니바퀴 입력 저장 
		String temp = br.readLine();
		for(int i = 0; i < 8; i++) {
			q1[i] = (Character.getNumericValue(temp.charAt(i)));
		}
		temp = br.readLine();
		for(int i = 0; i < 8; i++) {
			q2[i] = (Character.getNumericValue(temp.charAt(i)));
		}
		temp = br.readLine();
		for(int i = 0; i < 8; i++) {
			q3[i] = (Character.getNumericValue(temp.charAt(i)));
		}
		temp = br.readLine();
		for(int i = 0; i < 8; i++) {
			q4[i] = (Character.getNumericValue(temp.charAt(i)));
		}
		
		//배열로 톱니들 관리 
		qList = new int[][] {q1, q2, q3, q4};
		
		// 만나는 톱니(index 기준): 2 와 6
		
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		//K번 수행 
		for(int kk = 0; kk < K; kk++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;	//회전시킨 톱니바퀴의 번호 (index 기준)
			int dir = Integer.parseInt(st.nextToken());	//방향 
			
			//같은 극이라면 true, 아니라면 false 
			boolean[] isSame = {false, false, false};
			for(int i = 0; i < 3; i++) {
				if(qList[i][2] == qList[i+1][6]) {
					isSame[i] = true;
				}
			}
			
			//먼저 골라진 톱니바퀴 회전 
			rotate(qList[num], dir);
			
			//현재의 왼쪽 탐색. true가 있으면 break 
			int tmpNum = num;
			int tmpDir = dir;
			while(tmpNum > 0) {
				if(isSame[tmpNum-1]) break;	// 같은 극이라면 더 볼 필요없음.
				
				if(tmpDir == 1) {
					tmpDir = -1;
				} else if(tmpDir == -1) {
					tmpDir = 1;
				}
				rotate(qList[tmpNum-1], tmpDir);
				
				tmpNum--;
			}
			
			//현재의 오른쪽 탐색. true가 있으면 break 
			tmpNum = num;
			tmpDir = dir;
			while(tmpNum < 3) {
				if(isSame[tmpNum]) break;	// 같은 극이라면 더 볼 필요없음.
				
				if(tmpDir == 1) {
					tmpDir = -1;
				} else if(tmpDir == -1) {
					tmpDir = 1;
				}
				rotate(qList[tmpNum+1], tmpDir);
				
				tmpNum++;
			}
			
		}
		
		//점수 구하기 
		int answer = 0;
		int score = 1;
		for(int i = 0; i < 4; i++) {
			if(qList[i][0] == 1) {	//S극이면 점수 얻음 
				answer += score;
			}
			score *= 2;
		}
		
		//답 출력 
		System.out.println(answer);

	}	//main 끝 
	
	//톱니바퀴 회전시키기 
	private static void rotate(int[] q, int dir) {
		if(dir == -1) {	//반시계 방향 
			int tmp = q[0];
			
			for(int i = 1; i < 8; i++) {
				q[i-1] = q[i];
			}
			
			q[7] = tmp;
		} else if(dir == 1) {	//시계 방향 
			int tmp = q[7];
			
			for(int i = 7; i > 0; i--) {
				q[i] = q[i-1];
			}
			
			q[0] = tmp;
		} else {
			System.out.println("err");
		}
	}

}
