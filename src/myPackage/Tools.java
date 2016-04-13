package myPackage;

public class Tools {
	private int N = Constant.N;
	public Tools(){
		
	}
	public void copyMap1To2(int[][] map1,int[][] map2){
		for(int i=0;i<N;i++){
			 for(int j=0;j<N;j++){
				 map2[i][j]=map1[i][j];
			 }
		 }
	}
	public boolean AIsSameToB(int A[][],int B[][]){
		for(int i=1;i<N;i++){
			for(int j=1;j<N;j++){
				if(A[i][j]!=B[i][j])return false;
			}
		}	
		return true;
	}

	
}
