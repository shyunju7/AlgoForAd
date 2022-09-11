package com.day0903;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ1057 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	//참가자 수
		int kim = sc.nextInt();	//김지민 번호
		int lim = sc.nextInt();	//임한수 번호
		
		
		ArrayList<Integer> cur = new ArrayList<>();
		ArrayList<Integer> next = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			cur.add(i);
		}
		
		int cnt = 0;
		boolean find = false;
		
		while(cur.size() != 1) {
			cnt++;
			
			for(int i=0; i<cur.size(); i+=2) {
				if(i == cur.size()-1) break;
				
				
				if((cur.get(i) == kim && cur.get(i+1) == lim) || (cur.get(i) == lim && cur.get(i+1) == kim)) {
					find = true;
					break;
				}
				
				else if (cur.get(i+1) == kim || cur.get(i+1) == lim) {
					next.add(cur.get(i+1));
				}
				else next.add(cur.get(i));
			}
			
			if(find) break;
			
			if(cur.size()%2==1) next.add(cur.get(cur.size()-1));
			
			// 다음 라운드에 진출할 사람들을 list에 추가하여 갱신
			migration(cur, next);
		}
		
		if(find) {
			System.out.println(cnt);
		}
		else {
			System.out.println(-1);
		}
	}

	private static void migration(ArrayList<Integer> cur, ArrayList<Integer> next) {
		cur.clear();
		for(int i=0; i<next.size(); i++) {
			cur.add(next.get(i));
		}
		next.clear();
	}

}
