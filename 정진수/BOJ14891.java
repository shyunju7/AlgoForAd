package com.day0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14891 {
	
	static ArrayList<Integer>[] gear;
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		gear = new ArrayList[4];
		
		for(int i=0; i<4; i++) {
			gear[i] = new ArrayList<Integer>();
		}
		
		
		for(int i=0; i<4; i++) {
			String s = br.readLine();
			for(int j=0; j<s.length(); j++) {
				gear[i].add(Integer.parseInt(s.substring(j, j+1))); 
			}
		}
		
		int k = Integer.parseInt(br.readLine());	//회전 횟수
		
		for(int n=0; n < k; n++) {
			st = new StringTokenizer(br.readLine());
			int gear_choice = Integer.parseInt(st.nextToken()) - 1;	// 회전시킬 톱니바퀴 선택
			int rot_dir = Integer.parseInt(st.nextToken()); 	//회전 방향-> 시계방향 : 1, 반시계방향 : -1
			boolean flag_1 = false;
			boolean flag_2 = false;
			boolean flag_3 = false;
			boolean flag_4 = false;
			
			if(rot_dir == 1) {	//회전방향이 시계방향일 때
				
				if (gear_choice == 1) {		//두번째 톱니바퀴 일 때
					
					flag_2 = true;
					
					if(gear[gear_choice].get(6) != gear[gear_choice-1].get(2)) {	//왼쪽 톱니바퀴와 극이 다르면
						flag_1 = true;
						
					}
					
					if (gear[gear_choice].get(2) != gear[gear_choice+1].get(6)) {	//오른쪽 톱니바퀴와 극이 다르면
						flag_3 = true;
						
						if (gear[gear_choice+1].get(2) != gear[gear_choice+2].get(6)) {	//오른쪽 톱니바퀴와 마지막 톱니바퀴 극 판별
							flag_4 = true;
						}
						
					}
					
					if (flag_1) {
						int tmp = gear[gear_choice-1].remove(0);	
						gear[gear_choice-1].add(tmp);
					}
					if (flag_2) {
						int tmp = gear[gear_choice].remove(7);	
						gear[gear_choice].add(0, tmp);
					}
					if (flag_3) {
						int tmp = gear[gear_choice+1].remove(0);	
						gear[gear_choice+1].add(tmp);
					}
					if (flag_4) {
						int tmp = gear[gear_choice+2].remove(7);	
						gear[gear_choice+2].add(0, tmp);
					}
					
				}
				
				else if (gear_choice == 2) {	//세번째 톱니바퀴가 선택된 경우
					flag_3 = true;
					
					if (gear[gear_choice].get(6) != gear[gear_choice-1].get(2)) {
						flag_2 = true;
						
						if (gear[gear_choice-1].get(6) != gear[gear_choice-2].get(2)) {
							flag_1 = true;
						}
					}
					
					if (gear[gear_choice].get(2) != gear[gear_choice+1].get(6)) {
						flag_4 = true;
					}
					
					if (flag_1) {
						int tmp = gear[gear_choice-2].remove(7);	//왼쪽 톱니바퀴는 반시계
						gear[gear_choice-2].add(0, tmp);
					}
					if (flag_2) {
						int tmp = gear[gear_choice-1].remove(0);	//왼쪽 톱니바퀴는 반시계
						gear[gear_choice-1].add(tmp);
					}
					if (flag_3) {
						int tmp = gear[gear_choice].remove(7);	//왼쪽 톱니바퀴는 반시계
						gear[gear_choice].add(0, tmp);
					}
					if (flag_4) {
						int tmp = gear[gear_choice+1].remove(0);	//왼쪽 톱니바퀴는 반시계
						gear[gear_choice+1].add(tmp);
					}
				}
				
				else if (gear_choice == 0) {	//첫번째 톱니바퀴 선택된경우
					flag_1 = true;
					
					if (gear[gear_choice].get(2) != gear[gear_choice+1].get(6)) {
						flag_2 = true;
						
						if (gear[gear_choice+1].get(2) != gear[gear_choice+2].get(6)) {
							flag_3 = true;
							
							if (gear[gear_choice+2].get(2) != gear[gear_choice+3].get(6)) {
								flag_4 = true;
							}
						}
					}
					
					if (flag_1) {
						int tmp = gear[gear_choice].remove(7);	
						gear[gear_choice].add(0, tmp);
					}
					if (flag_2) {
						int tmp = gear[gear_choice+1].remove(0);	
						gear[gear_choice+1].add(tmp);
					}
					if (flag_3) {
						int tmp = gear[gear_choice+2].remove(7);	
						gear[gear_choice+2].add(0, tmp);
					}
					if (flag_4) {
						int tmp = gear[gear_choice+3].remove(0);	
						gear[gear_choice+3].add(tmp);
					}
				}
				
				else {	//마지막 톱니바퀴 선택된 경우
					flag_4 = true;
					
					if(gear[gear_choice].get(6) != gear[gear_choice-1].get(2)) {
						flag_3 = true;
						
						if (gear[gear_choice-1].get(6) != gear[gear_choice-2].get(2)) {
							flag_2 = true;
							
							if (gear[gear_choice-2].get(6) != gear[gear_choice-3].get(2)) {
								flag_1 = true;
							}
						}
					}
					if (flag_1) {
						int tmp = gear[gear_choice-3].remove(0);	
						gear[gear_choice-3].add(tmp);
					}
					if (flag_2) {
						int tmp = gear[gear_choice-2].remove(7);	
						gear[gear_choice-2].add(0, tmp);
					}
					if (flag_3) {
						int tmp = gear[gear_choice-1].remove(0);	
						gear[gear_choice-1].add(tmp);
					}
					if (flag_4) {
						int tmp = gear[gear_choice].remove(7);	
						gear[gear_choice].add(0, tmp);
					}
				}
			} 
			
			else {
				if (gear_choice == 1) {		//두번째 톱니바퀴 일 때
					flag_2 = true;
					
					if(gear[gear_choice].get(6) != gear[gear_choice-1].get(2)) {	//왼쪽 톱니바퀴와 극이 다르면
						flag_1 = true;
					}
					
					if (gear[gear_choice].get(2) != gear[gear_choice+1].get(6)) {	//오른쪽 톱니바퀴와 극이 다르면
						flag_3 = true;
						
						if (gear[gear_choice+1].get(2) != gear[gear_choice+2].get(6)) {	//오른쪽 톱니바퀴와 마지막 톱니바퀴 극 판별
							flag_4 = true;
						}
					}
					
					if (flag_1) {
						int tmp = gear[gear_choice-1].remove(7);	
						gear[gear_choice-1].add(0, tmp);
					}
					if (flag_2) {
						int tmp = gear[gear_choice].remove(0);	
						gear[gear_choice].add(tmp);
					}
					if (flag_3) {
						int tmp = gear[gear_choice+1].remove(7);	
						gear[gear_choice+1].add(0, tmp);
					}
					if (flag_4) {
						int tmp = gear[gear_choice+2].remove(0);	
						gear[gear_choice+2].add(tmp);
					}
					
				}
				
				else if (gear_choice == 2) {	//세번째 톱니바퀴가 선택된 경우
					flag_3 = true;
					
					if (gear[gear_choice].get(6) != gear[gear_choice-1].get(2)) {
						flag_2 = true;
						
						if (gear[gear_choice-1].get(6) != gear[gear_choice-2].get(2)) {
							flag_1 = true;
						}
					}
					
					if (gear[gear_choice].get(2) != gear[gear_choice+1].get(6)) {
						flag_4 = true;
					}
					
					if (flag_1) {
						int tmp = gear[gear_choice-2].remove(0);	//왼쪽 톱니바퀴는 반시계
						gear[gear_choice-2].add(tmp);
					}
					if (flag_2) {
						int tmp = gear[gear_choice-1].remove(7);	//왼쪽 톱니바퀴는 반시계
						gear[gear_choice-1].add(0, tmp);
					}
					if (flag_3) {
						int tmp = gear[gear_choice].remove(0);	//왼쪽 톱니바퀴는 반시계
						gear[gear_choice].add(tmp);
					}
					if (flag_4) {
						int tmp = gear[gear_choice+1].remove(7);	//왼쪽 톱니바퀴는 반시계
						gear[gear_choice+1].add(0, tmp);
					}
				}
				
				else if (gear_choice == 0) {	//첫번째 톱니바퀴 선택된경우
					flag_1 = true;
					
					if (gear[gear_choice].get(2) != gear[gear_choice+1].get(6)) {
						flag_2 = true;
						
						if (gear[gear_choice+1].get(2) != gear[gear_choice+2].get(6)) {
							flag_3 = true;
							
							if (gear[gear_choice+2].get(2) != gear[gear_choice+3].get(6)) {
								flag_4 = true;
							}
						}
					}
					
					if (flag_1) {
						int tmp = gear[gear_choice].remove(0);	
						gear[gear_choice].add(tmp);
					}
					if (flag_2) {
						int tmp = gear[gear_choice+1].remove(7);	
						gear[gear_choice+1].add(0, tmp);
					}
					if (flag_3) {
						int tmp = gear[gear_choice+2].remove(0);	
						gear[gear_choice+2].add(tmp);
					}
					if (flag_4) {
						int tmp = gear[gear_choice+3].remove(7);	
						gear[gear_choice+3].add(0, tmp);
					}
				}
				
				
				else {	//마지막 톱니바퀴 선택된 경우
					flag_4 = true;
					
					if(gear[gear_choice].get(6) != gear[gear_choice-1].get(2)) {
						flag_3 = true;
						
						if (gear[gear_choice-1].get(6) != gear[gear_choice-2].get(2)) {
							flag_2 = true;
							
							if (gear[gear_choice-2].get(6) != gear[gear_choice-3].get(2)) {
								flag_1 = true;
							}
						}
					}
					if (flag_1) {
						int tmp = gear[gear_choice-3].remove(7);	
						gear[gear_choice-3].add(0, tmp);
					}
					if (flag_2) {
						int tmp = gear[gear_choice-2].remove(0);	
						gear[gear_choice-2].add(tmp);
					}
					if (flag_3) {
						int tmp = gear[gear_choice-1].remove(7);	
						gear[gear_choice-1].add(0, tmp);
					}
					if (flag_4) {
						int tmp = gear[gear_choice].remove(0);	
						gear[gear_choice].add(tmp);
					}
				}
			}
		}
		
		int cnt = 1;
		for(int i=0; i<4; i++) {
			if (gear[i].get(0) == 1) {
				result += gear[i].get(0)*cnt;
			}
			
			cnt *= 2;
		}
		
		System.out.println(result);
	}

}
