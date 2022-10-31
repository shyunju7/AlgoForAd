package com.Week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2075_N번째큰수 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		//내림차순으로 정렬 
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
		}
		
		//N번째 수 출력 
		long answer = 0;
		for(int i = 0; i < N; i++) {
			answer = pq.poll();
		}
		
		System.out.println(answer);
	}

}
