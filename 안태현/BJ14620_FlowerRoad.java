package com.No3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14620_FlowerRoad {
	
	private static int N;
	private static int[][] price;
	private static boolean[][] map;
	private static int[] dx = {0, -1, 0, 1, 0}, dy = {0, 0, -1, 0, 1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		price = new int[N][N];
		
		
		//지도에 가격 정보 저장 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				price[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int minPrice = Integer.MAX_VALUE;
		
		for(int a = 1; a < N - 1; a++) {
			for(int b = 1; b < N - 1; b++) {
				
				for(int c = 1; c < N - 1; c++) {
					for(int d = 1; d < N - 1; d++) {
						
						if(a == c && b == d) continue;
						
						for(int e = 1; e < N - 1; e++) {
							loop:	//꽃피는 자리가 겹치면 여기로 돌아옴 
							for(int f = 1; f < N - 1; f++) {
								
								if(c == e && d == f) continue;
								
								map = new boolean[N][N];	//(초기화) 꽃이 핀 자리면 True, 아니면 false
//								for(int i = 0; i < N; i++) {
//									for(int j = 0; j < N; j++) {
//										System.out.print(map[i][j] + " ");
//									}
//									System.out.println();
//								}
//								System.out.println("------------------------------");
								
								//첫번째 꽃 피는 자리 처리 
								for(int i = 0; i < 5; i++) {
									int nx = a + dx[i];
									int ny = b + dy[i];
									
									if(map[nx][ny]) continue loop;	//꽃잎이 겹쳤으면 바꿈 
									map[nx][ny] = true;
								}
								//두번째 꽃 피는 자리 처리 
								for(int i = 0; i < 5; i++) {
									int nx = c + dx[i];
									int ny = d + dy[i];
									
									if(map[nx][ny]) continue loop;	//꽃잎이 겹쳤으면 바꿈 
									map[nx][ny] = true;
								}
								//세번째 꽃 피는 자리 처리 
								for(int i = 0; i < 5; i++) {
									int nx = e + dx[i];
									int ny = f + dy[i];
									
									if(map[nx][ny]) continue loop;	//꽃잎이 겹쳤으면 바꿈 
									map[nx][ny] = true;
								}
								//여기까지 왔으면 다 통과했으므로 비용 계산 
								int price = getPrice();
								minPrice = Math.min(price, minPrice);
								
							}
						}
						
					}
				}
				
			}
		}
		
		System.out.println(minPrice);
		
	}	//main 끝 
	
	private static int getPrice() {
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j]) sum += price[i][j];
			}
		}
		return sum;
	}
	
}
