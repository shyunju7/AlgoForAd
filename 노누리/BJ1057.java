package com.study.radish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int A=Integer.parseInt(st.nextToken());
        int B=Integer.parseInt(st.nextToken());

        int round=1;
        while(N>=2){
            if(Math.abs(A-B)==1 && Math.min(A,B)%2==1){
                break;
            }

            A=Math.round((float)A/2);
            B=Math.round((float)B/2);
            N=Math.round(N/2);
            round++;
        }

        if(Math.abs(A-B)!=1) round=-1;

        System.out.println(round);
    }
}
