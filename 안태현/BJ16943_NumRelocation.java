package com.Week04;

import java.util.Scanner;

public class BJ16943_NumRelocation {
	
	private static int A, B, Alength, answer;
	private static int[] Al, numbers;
	private static boolean[] isSelected;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		A = sc.nextInt();
		B = sc.nextInt();
		answer = -1;	//정답 초기화
		
		//A와 B의 자릿수 구하기 
		Alength = 0;
		for(int i = 0; i < 10; i++) {
			Alength++;
			if(A/(int)Math.pow(10, Alength) == 0) {
				break;
			}
		}
		int Blength = 0;
		for(int i = 0; i < 10; i++) {
			Blength++;
			if(B/(int)Math.pow(10, Blength) == 0) {
				break;
			}
		}
		
		//A의 자릿수가 B보다 크면 불가능 ------------종료조건
		if(Alength > Blength) {
			answer = -1;	
			System.out.println(answer);
			return;
		}
		
		//A와 B를 각각 배열로 저장(순열을 쓰기 위해서)
		Al = new int[Alength];
		int tmpA = A;
		for(int i = 0; i < Alength; i++) {
			Al[i] = tmpA%10;
			tmpA /= 10;
		}
		
		isSelected = new boolean[Alength];
		numbers = new int[Alength];
		
		perm(0);
		
		System.out.println(answer);
	}	//main 끝 
	
	private static void perm(int cnt) {
		if(cnt == Alength) {
			if(numbers[Alength-1] != 0) {	//맨 앞자리가 0이면 안됨 
				getMax();
			}
			return;
		}
		
		for(int i = 0; i < Alength; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				numbers[cnt] = Al[i];
				perm(cnt+1);
				isSelected[i] = false;
			}
		}
	}
	
	private static void getMax() {
		int num = 0;
		int ten = 1;
		for(int i = 0; i < Alength; i++) {
			num += numbers[i] * ten;
			ten *= 10;
		}
		if(num < B) {
			answer = Math.max(num, answer);
		}
		
	}

}
