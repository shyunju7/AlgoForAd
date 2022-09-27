package com.No3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ2304_Storage {
	
	private static int[] map = new int[1001];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int minIndex = Integer.MAX_VALUE;
		int maxIndex = Integer.MIN_VALUE;
		int maxHeight = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			
			map[index] = height;
			
			maxHeight = Math.max(maxHeight, height);
			minIndex = Math.min(minIndex, index);
			maxIndex = Math.max(maxIndex, index);
		}
		
		int maxHeightIndex = 0;
		for(int i = minIndex; i <= maxIndex; i++) {
			if(map[i] == maxHeight) {
				maxHeightIndex = i;
			}
		}
		
		int sum = 0;
		
		int frontHeight = 0;
		for(int i = minIndex; i <= maxHeightIndex; i++) {
			frontHeight = Math.max(frontHeight, map[i]);
			sum += frontHeight;
		}
		
		int backHeight = 0;
		for(int i = maxIndex; i > maxHeightIndex; i--) {
			backHeight = Math.max(backHeight, map[i]);
			sum += backHeight;
		}
		
		System.out.println(sum);
		
	}
	
}
