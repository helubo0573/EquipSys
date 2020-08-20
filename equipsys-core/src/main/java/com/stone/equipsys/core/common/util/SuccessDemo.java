package com.stone.equipsys.core.common.util;


public class SuccessDemo {

	final static int[] level= {25,25,25,50,50,50,75,75,85};
	static {
		
	}
	public static void main(String[] args) {
		run();

	}

	public static int redom() {
		double r2=Math.random()*100;
		return (int) r2;
	}
	
	public static void run() {
		boolean state=false;
		for(int i=1;i-1<=8;i++) {
			state=false;
			int success=level[i-1];
			int r=100-success;
			System.out.println("开始升级第"+i+"段");
			int n=0;
			while(state==false) {
				n++;
				int s=redom();
				state=s>=success;
				String str=state?"成功":"失败";
				System.out.println("第"+n+"手：成功率:"+r+"---随机数:"+s+"---"+i+"段升级"+str);
			}
		}
	}
}
