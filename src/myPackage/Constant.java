package myPackage;

public class Constant {
	//��Щ������ᵼ���ڴ治����
	static int N = 9;//��Ϊ��ͼ��С+2
	static int Y = 20;//step���ֵ��Ҳ���жϳɻ�ʱ�����������ֵ
	static int C = -1;
	static int Q = 1000;//���������ֵ�������ҵ���һ����֮���ٿ��Ǳȵ�һ�����������Σ�����Q��ʵ�������õ�Сһ��
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
