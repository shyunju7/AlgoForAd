package com.No3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1581_RockStar {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int FF = Integer.parseInt(st.nextToken());
		int FS = Integer.parseInt(st.nextToken());
		int SF = Integer.parseInt(st.nextToken());
		int SS = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		
		//1. 빠르게 시작하는 노래가 없음 
		if(FF == 0 && FS == 0) {
			//[SF] - 끝 or [SS] - SS - ... - SS ( - SF)
			//SF가 없으면 그냥 SS갯수 다 넣을 수 있음. 있다면 맨뒤에 "한번"붙일 수 있음
			answer = (SF == 0) ? SS : SS + 1;
		}
		//2. 빠르게 시작하는 노래가 있음 -> 반드시 빠르게 시작하는 노래로 시작 
		//[FF] - FS - SF - ... or [FS] - SF - FS - ...
		//[FF] - FF - FS - ... or [FS] - SS - SF - ...
		else {
			//2-1. FS가 없으면 느리게 시작하는 노래는 불가 
			if(FS == 0) answer = FF;
			//2-2. FS와 SF가 최소 한곡씩은 있음 
			//FF랑 SS는 그냥 뒤에다가 쭉 붙여주면 되므로 더하기만 해주면 됨 
			else {
				//짝지어서 생각하는걸 2번째랑 3번째랑 맺으니까 편함 
				//2-2-1. FS가 SF보다 많을경우 
				if(FS > SF) {	//FS - SF - FS - SF - FS - SF - ... - FS (FS로 끝날거임)
					answer = (FF + SS) + (SF*2) + 1;	//SF의 두배 개수만큼 더해주고 마지막 FS를 더해줌 
				}
				//2-2-2. (FS <= SF) FS가 SF의 이하일 경우 
				else {	//FS - SF - FS - SF (SF로 끝날거임)
					answer = (FF + SS) + (FS*2);
				}
			}
		}
		
		System.out.println(answer);
		
	}

}
