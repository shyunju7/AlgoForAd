package com.Week07;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ14235_크리스마스선물 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();	//아이들과 거점지를 방문한 횟수 
		PriorityQueue<Integer> gifts = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < n; i++) {
//			System.out.println(gifts);
			int a = sc.nextInt();
			
			//아이들 만남. 선물 줘야함. 큐에서 빼기 
			if(a == 0) {
				if(gifts.isEmpty()) System.out.println(-1);
				else System.out.println(gifts.poll());
			}
			//선물 충전소 왔음. 선물 충전
			else {
				for(int j = 0; j < a; j++) {
					int gift = sc.nextInt();
					gifts.add(gift);
				}
			}
			
		}
		
		sc.close();
		
	}

}
