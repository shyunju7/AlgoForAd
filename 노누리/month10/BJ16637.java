package month10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ16637 {
    static int max=Integer.MIN_VALUE;
    static int N;
    static char[] com;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         N=Integer.parseInt(br.readLine());

         com=new char[N];
         com=br.readLine().toCharArray();

        //괄호가 있을때, 없을때
        dfs(0,0);

        System.out.println(max);
    }

    private static void dfs(int idx, int sum) {
        if(idx>=N){
            max=Math.max(max,sum);
            return;
        }

        //괄호 안 친 경우
        if(idx==0){
            dfs(idx + 2, com[idx]-'0');
        }else{
            dfs(idx + 2, cal(sum,com[idx-1],com[idx]-'0'));
        }

        //왼쪽에 괄호 친 경우
        if(idx+2<N){
            int tmp=cal(com[idx]-'0',com[idx+1],com[idx+2]-'0');
            if(idx!=0) {
                dfs(idx + 4, cal(sum, com[idx - 1],tmp));
            }else{
                dfs(idx+4, tmp);
            }
        }

    }

    private static int cal(int a, char b, int c) {
        if(b=='+'){
            return a+c;
        }else if(b=='-'){
            return a-c;
        }else{
            return a*c;
        }
    }
}

