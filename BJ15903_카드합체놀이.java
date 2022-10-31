package com.Week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ15903_카드합체놀이 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> cards = new PriorityQueue<Long>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			cards.add(Long.parseLong(st.nextToken()));
		}
		
		//가장 작은 카드 두개 더하기 
		for(int i = 0; i < m; i++) {
			long x = cards.poll();
			long y = cards.poll();
			long xy = x + y;
			
			//카드 두개가 더한 값으로 변하는 것이니까 카드를 두번 추가 
			cards.add(xy);
			cards.add(xy);
		}
		
		//카드들 다 더하기 
		long sum = 0;
		while(!cards.isEmpty()) {
			sum += cards.poll();
		}
		
		System.out.println(sum);
		
	}

}
