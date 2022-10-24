package com.Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2503_숫자야구 {
	
	private static int N;
	private static Ask[] asks;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//질문 횟수 
		asks = new Ask[N];
		int cnt = 0;	//생각하고 있을 가능성이 있는 답  
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			asks[i] = new Ask(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		int num = 123;	//숫자야구로서 가능한 숫자부터 시작 
		while(num < 1000) {
			
			int hun = getHun(num);	//백의 자리 
			int ten = getTen(num);	//십의 자리 
			int one = getOne(num);	//일의 자리 
			
			if(canNum(hun, ten, one)) {
				if(check(hun, ten, one)) cnt++;
			}
			
			num++;
		}
		
		System.out.println(cnt);
		
	}	//Main 끝 
	
	//물어본 조건들에 알맞은 숫자인지 체크 
	private static boolean check(int hun, int ten, int one) {
		
		//질문한 숫자들과 현재 숫자를 비교 
		for(int i = 0; i < N; i++) {
			
			//질문한 숫자랑 현재 숫자랑 비교했을 때의 strike 수와 Ball 수 
			int s = 0;
			int b = 0;
			
			Ask curAsk = asks[i];
			int askNum = curAsk.askNum;
			int strike = curAsk.s;	//숫자 물어봤을때 strike 몇이었는지 
			int ball = curAsk.b;	//숫자 물어봤을때 Ball 몇이었는지 
			int askHun = getHun(askNum);
			int askTen = getTen(askNum);
			int askOne = getOne(askNum);
			
			//#1. 스트라이크 체크 
			if(hun == askHun) s++;
			if(ten == askTen) s++;
			if(one == askOne) s++;
			
			//#2. 볼 체크 
			if(hun == askTen || hun == askOne) b++;
			if(ten == askHun || ten == askOne) b++;
			if(one == askHun || one == askTen) b++;
			
			//영수가 답변해준 답과 현재 스트라이크, 볼 수가 맞지 않다면 현재 숫자는 답이 아님.
			if(s != strike || b != ball) return false;
		}
		return true;
	}
	
	//숫자야구로서 가능한 숫자인지 확인 
	private static boolean canNum(int hun, int ten, int one) {
		
		//숫자들 중에 0이 포함되어 있는가?
		if(hun == 0 || ten == 0 || one == 0)
			return false;
		
		//겹치는 숫자가 있는가? 
		if(hun == ten || ten == one || hun == one)
			return false;
		
		return true;
	}
	
	//백의 자리 숫자 
	private static int getHun(int num) {
		return num/100;
	}
	
	//십의 자리 숫자 
	private static int getTen(int num) {
		return (num%100)/10;
	}
	
	//일의 자리 숫자 
	private static int getOne(int num) {
		return num%10;
	}
	
	private static class Ask {
		int askNum, s, b;
		public Ask(int askNum, int s, int b) {
			this.askNum = askNum;
			this.s = s;
			this.b = b;
		}
	}

}
