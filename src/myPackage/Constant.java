package myPackage;

public class Constant {
	//这些数过大会导致内存不够用
	static int N = 9;//设为地图大小+2
	static int Y = 20;//step最大值，也是判断成环时拍照数量最大值
	static int C = -1;
	static int Q = 1000;//答案条数最大值，由于找到第一条答案之后不再考虑比第一条更长的情形，所以Q其实可以设置的小一点
	static int[][] initialMap = new int[N][N];
 
	
	public Constant(){
		String str=""	
				+ "44444445"
				+ "43000045"
				+ "46400045"
				+ "43101045"
				+ "43611045"
				+ "43420045"
				+ "44444445"
				+ "55555555";


		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				initialMap[i][j]=5;
			}
		}
		int num = 0;
		for(int i=1;i<N;i++){
			for(int j=1;j<N;j++){
				char temp = str.charAt(num);num++;
				Constant.initialMap[i][j]=temp-48;
			}
		}
		
		int caseNum = 0;
		for(int i=1;i<N;i++){
			for(int j=1;j<N;j++){
				if(initialMap[i][j]==1||initialMap[i][j]==6){
					caseNum++;
				}
			}
		}
		C=caseNum;
	}
}
