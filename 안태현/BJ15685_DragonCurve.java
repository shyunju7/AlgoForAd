package com.No2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ15685_DragonCurve {
	
	private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
	private static boolean[][] map = new boolean[101][101];
	private static int answer;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		Deque<int[]> q = new ArrayDeque<>();	//용 저장 
		Deque<int[]> oq = new ArrayDeque<>();	//용 회전한거 저장 
		
		for(int cnt = 0; cnt < N; cnt++) {
		
			q.clear();	//용 저장하는 덱 초기화 
			
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());	//행 (중앙에 오게끔)
			int y = Integer.parseInt(st.nextToken());	//열 (중앙에 오게끔)
			int d = Integer.parseInt(st.nextToken());	//증가하는 방향 
			int g = Integer.parseInt(st.nextToken());	//세대 
			
			//x, y좌표를 이차원 배열에 맞게 수정하면,
			//시계반대방향으로 90도 회전.
			//d 0: 밑으로(x++), 1: 왼쪽으로(y--), 2: 위로(x--), 3: 오른쪽으로(y++)
			
			//첫번째 세팅 
			q.addLast(new int[]{x, y});
			q.addLast(new int[]{x+dx[d], y+dy[d]});
			
			//주어진 세대 동안 반복 
			for(int i = 0; i < g; i++) {
				oq.clear();
				int length = q.size();
				int[] base = q.peekLast();
				
				//90도 돌린 것을 oq에 저장 
				for(int j = 0; j < length; j++) {
					oq.addLast(rotate(q.peekLast(), base));
					q.addFirst(q.pollLast());
				}
				
				//q뒤에다가 oq를 붙임 -> 드래곤 완성
				for(int j = 0; j < length; j++) {
					q.addLast(oq.pollFirst());
				}
				
			}
			
			//q에 저장된 좌표을 map내에서 True로 바꿔줌 
			int length = q.size();
			for(int i = 0; i < length; i++) {
				int[] tmp = q.pollFirst();
				map[tmp[0]][tmp[1]] = true;
			}
			
		}
		
		checkRect();
		System.out.println(answer);
		
	}	//main 끝 
	
	private static void checkRect() {
		for(int i = 0; i < map.length-1; i++) {
			for(int j = 0; j < map[i].length-1; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
					answer++;
				}
			}
		}
	}
	
	private static int[] rotate(int[] a, int[] base) {
		int[] after = new int[2];
		
		//세타 = 90
		//x'=cos세타(x-xbase) - sin세타(y-ybase) + xbase
		//y'=sin세타(x-xbase) + cos세타(y-ybase) + ybase
		after[0] = -(a[1]-base[1]) + base[0];
		after[1] = (a[0]-base[0] + base[1]);
		
		return after;
	}

}
