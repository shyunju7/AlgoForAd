package com.day0902;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ1057_Tournament {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int jimin = sc.nextInt() - 1;	// 2로 표현 
		int hansoo = sc.nextInt() - 1;	//2로 표현, 듣보잡들은 1로 표현 
		int cnt = 0;	//지민이랑 한수가 만날때까지 라운드 횟수 
		
		ArrayList<Integer> list = new ArrayList<>();
		
		//리스트에 세팅 
		for(int i = 0; i < N; i++) {
			if(i == jimin || i == hansoo) list.add(2);
			else list.add(1);
		}
		
		boolean condition = true;
		
		while(condition) {
			//한 사람이 남았다면 종료 (지민이랑 현수가 만나지 않았을 경우)
			if(list.size() == 1) {
				condition = false;
				cnt = -1;
			}
			
			++cnt;
			
			ArrayList<Integer> deleteIndex = new ArrayList<>();
			
//			System.out.println("현재 리스트: " + list.toString());
			
			for(int i = 0; i < list.size(); i+=2) {
				if(i < list.size()-1) {
					//두개를 비교함
					//둘 다 듣보잡(1)이면 뒤에꺼 탈락 시킴
					if(list.get(i) == 1 && list.get(i+1) == 1) {
						deleteIndex.add(i+1);
					}
					//둘 다 지민, 현수면 종료
					else if(list.get(i) == 2 && list.get(i+1) == 2) {
						condition = false;
						break;	//지민이랑 한수랑 만났으므로 !종료!
					}
					//둘 중 하나가 지민,현수면 듣보잡을 탈락 시킴 
					else if(list.get(i) == 2) {
						deleteIndex.add(i+1);
					}
					else if(list.get(i+1) == 2) {
						deleteIndex.add(i);
					}
				}
			}
			
//			System.out.println("삭제할 인덱스들: " + deleteIndex.toString());
			
			//저장해놓은 삭제할 인덱스들의 값 삭제 
			for(int i = deleteIndex.size()-1; i >= 0; i--) {
//				System.out.println("현재 삭제할 인덱스: " + deleteIndex.get(i));
				int a = deleteIndex.get(i);
				list.remove(a);
//				System.out.println("삭제 후 리스트: " + list.toString());
			}
			
//			System.out.println("------------------------");
		
			deleteIndex.clear();
			
		}
		
		System.out.println(cnt);
		
	}	//main 끝 
	

}
