package com.Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14889_스타트와링크 {
	
	public static int ans, N, R;
	public static int[] people, selected;
	public static int[][] ability;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		ability = new int[N][N];
		people = new int[N];
		R = N/2;
		selected = new int[R];
		
		//입력 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < people.length; i++) {
			people[i] = i;
		}
		
		ans = Integer.MAX_VALUE;
		
		comb(0, 0);

		System.out.println(ans);
		
	}
	
	private static void comb(int index, int start) {
		
		if(index == R) {
			ans = Math.min(getDiff(selected), ans);
			return;
		}
		
		for(int i = start; i < N; i++) {
			selected[index] = people[i];
			comb(index + 1, i + 1);
		}
		
	}

	private static int getDiff(int[] selected) {
		
		boolean[] isInTeam = new boolean[N];
		int teamA = 0;
		int teamB = 0;
		int diff = 0;
		
		//teamA 능력치 결정: selected(골라진 멤버)에 포함된 사람들 
		for(int from : selected) {
			isInTeam[from] = true;
			for(int to : selected) {
				teamA += ability[from][to];
			}
		}
		
		//teamB 능력치 결정: 안 골라진 사람들 
		for(int from : people) {
			if(!isInTeam[from]) {	//안골라졌음 
				for(int to : people) {
					if(!isInTeam[to]) {
						teamB += ability[from][to];
					}
				}
			}
		}
		
		diff = (int)Math.abs(teamA - teamB);
		
		return diff;
	}

}
