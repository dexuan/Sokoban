package myPackage;

public class Sokoban {
	private int N = Constant.N;
	
	private int[][] map = new int[N][N];
	private int[][] mapAvatar = new int[N][N];
	private int line = -1;
	private int column = -1;
	public String sokobanInfo = "need update";
	public int direction4C = -1;
	

	public Sokoban(){
		
	}

	private int getMoverSLine(){
		for(int i=1;i<N;i++){
			for(int j=1;j<N;j++){
				if(map[i][j]==2)return i;
				if(map[i][j]==7)return i;
			}
		}
		return -1;
	}
	private int getMoverSColumn(){
		for(int i=1;i<N;i++){
			for(int j=1;j<N;j++){
				if(map[i][j]==2)return j;
				if(map[i][j]==7)return j;
			}
		}
		return -1;
	}

	private void launchAvatar(){
		do{
			boolean cycleSwitch = false;
			for(int i=1;i<N-1;i++){
				for(int j=1;j<N-1;j++){
					if(mapAvatar[i][j]==0){
						if(mapAvatar[i-1][j]==2||mapAvatar[i-1][j]==7){
							mapAvatar[i][j]=2;
							cycleSwitch = true;
							continue;
						}
						if(mapAvatar[i+1][j]==2||mapAvatar[i+1][j]==7){
							mapAvatar[i][j]=2;
							cycleSwitch = true;
							continue;
						}
						if(mapAvatar[i][j-1]==2||mapAvatar[i][j-1]==7){
							mapAvatar[i][j]=2;
							cycleSwitch = true;
							continue;
						}
						if(mapAvatar[i][j+1]==2||mapAvatar[i][j+1]==7){
							mapAvatar[i][j]=2;
							cycleSwitch = true;
							continue;
						}
					}
					
					if(mapAvatar[i][j]==3){
						if(mapAvatar[i-1][j]==2||mapAvatar[i-1][j]==7){
							mapAvatar[i][j]=7;
							cycleSwitch = true;
							continue;
						}
						if(mapAvatar[i+1][j]==2||mapAvatar[i+1][j]==7){
							mapAvatar[i][j]=7;
							cycleSwitch = true;
							continue;
						}
						if(mapAvatar[i][j-1]==2||mapAvatar[i][j-1]==7){
							mapAvatar[i][j]=7;
							cycleSwitch = true;
							continue;
						}
						if(mapAvatar[i][j+1]==2||mapAvatar[i][j+1]==7){
							mapAvatar[i][j]=7;
							cycleSwitch = true;
							continue;
						}
					}
				}
			}
			if(cycleSwitch==false)break;
		}
		while(1!=2);
	}
	public void setMapAndLaunchAvatar(int mapIn[][]){
		new Tools().copyMap1To2(mapIn, map);
		new Tools().copyMap1To2(mapIn, mapAvatar);
		line = getMoverSLine();
		column = getMoverSColumn();
		launchAvatar();
	}


	private int[] move(int[] In){
		int[] Out = new int[3];
		for(int i=0;i<3;i++){
			Out[i]=In[i];
		}
		
		/*
		 * 0：空气
		 * 1：箱子
		 * 2：人物
		 * 3：目标（目标位置）
		 * 4：石头（墙壁）
		 * 5：外部（地图外部）
		 * 6：星星（箱子在目标上）
		 * 7：超人（人物在目标上）
		 * */
		/*
		 人物 空气			20		02	可
		 人物 目标			23		07	可
		 人物 箱子 空气		210		021	可
		 人物 箱子 目标		213		026	可
		 人物 星星 空气		260		071	可
		 人物 星星 目标		263		076	可
		 
		 超人 空气			70		32	可
		 超人 目标			73		37	可
		 超人 箱子 空气		710		321	可
		 超人 箱子 目标		713		326	可
		 超人 星星 空气		760		371	可
		 超人 星星 目标		763		376	可
		 
		 其余都不可*/
		
		if(In[0]==2&In[1]==0){Out[0]=0;Out[1]=2;return Out;}
		if(In[0]==2&In[1]==3){Out[0]=0;Out[1]=7;return Out;}
		if(In[0]==2&In[1]==1&In[2]==0){Out[0]=0;Out[1]=2;Out[2]=1;return Out;}
		if(In[0]==2&In[1]==1&In[2]==3){Out[0]=0;Out[1]=2;Out[2]=6;return Out;}
		if(In[0]==2&In[1]==6&In[2]==0){Out[0]=0;Out[1]=7;Out[2]=1;return Out;}
		if(In[0]==2&In[1]==6&In[2]==3){Out[0]=0;Out[1]=7;Out[2]=6;return Out;}
		if(In[0]==7&In[1]==0){Out[0]=3;Out[1]=2;return Out;}
		if(In[0]==7&In[1]==3){Out[0]=3;Out[1]=7;return Out;}
		if(In[0]==7&In[1]==1&In[2]==0){Out[0]=3;Out[1]=2;Out[2]=1;return Out;}
		if(In[0]==7&In[1]==1&In[2]==3){Out[0]=3;Out[1]=2;Out[2]=6;return Out;}
		if(In[0]==7&In[1]==6&In[2]==0){Out[0]=3;Out[1]=7;Out[2]=1;return Out;}
		if(In[0]==7&In[1]==6&In[2]==3){Out[0]=3;Out[1]=7;Out[2]=6;return Out;}	
		return Out;
	}
	private boolean up(){
		int[] info={map[line][column],map[line-1][column],map[line-2][column]};
		int[] outCome = move(info);
		for(int i=0;i<3;i++){
			if(info[i]!=outCome[i]){
				map[line][column]=outCome[0];
				map[line-1][column]=outCome[1];
				map[line-2][column]=outCome[2];
				
				line--;
				return true;
			}
		}
		return false;
	}
	private boolean down(){
		int[] info={map[line][column],map[line+1][column],map[line+2][column]};
		int[] outCome = move(info);
		for(int i=0;i<3;i++){
			if(info[i]!=outCome[i]){
				map[line][column]=outCome[0];
				map[line+1][column]=outCome[1];
				map[line+2][column]=outCome[2];
				
				line++;
				return true;
			}
		}
		return false;
	}
	private boolean left(){
		int[] info={map[line][column],map[line][column-1],map[line][column-2]};
		int[] outCome = move(info);
		for(int i=0;i<3;i++){
			if(info[i]!=outCome[i]){
				map[line][column]=outCome[0];
				map[line][column-1]=outCome[1];
				map[line][column-2]=outCome[2];
				column--;
				return true;
			}
		}
		return false;
	}
	private boolean right(){
		int[] info={map[line][column],map[line][column+1],map[line][column+2]};
		int[] outCome = move(info);
		for(int i=0;i<3;i++){
			if(info[i]!=outCome[i]){
				map[line][column]=outCome[0];
				map[line][column+1]=outCome[1];
				map[line][column+2]=outCome[2];
				column++;
				return true;
			}
		}
		return false;
	}
	private boolean go(int direction){
		switch(direction){
		case 0:return down();
		case 1:return left();
		case 2:return up();
		case 3:return right();
		default:System.out.println("goSwitch语句报错");break;
		}
		
		return false;
	}
	public boolean goAndLaunchAvatar(int direction){
		boolean temp = false;
		temp = go(direction);
		new Tools().copyMap1To2(map, mapAvatar);
		launchAvatar();
		return temp;
	}
	public boolean avatarTryToPushTheCase(int lineOfCase,int columnOfCase,int direction){
		int lineA = lineOfCase;//A:avatar
		int columnA = columnOfCase;
		int lineT = lineOfCase;//T:temp
		int columnT = columnOfCase;
		
		if(map[lineOfCase][columnOfCase]!=1&&map[lineOfCase][columnOfCase]!=6){
			System.out.println("avatarCanPushTheCase方法报错");
			return false;
		}
		
		switch(direction){

		case 0:lineA--;lineT++;break;
		case 1:columnA++;columnT--;break;
		case 2:lineA++;lineT--;break;
		case 3:columnA--;columnT++;break;
		default:System.out.println("avatarCanPushTheCase报错");
		}
		
		if(mapAvatar[lineA][columnA]!=2&&mapAvatar[lineA][columnA]!=7)return false;
		if(mapAvatar[lineT][columnT]!=2&&mapAvatar[lineT][columnT]!=7&&mapAvatar[lineT][columnT]!=0&&mapAvatar[lineT][columnT]!=3)return false;
		//到这就说明能推
		
		if(map[lineA][columnA]==0||map[lineA][columnA]==3){
			if(map[line][column]==2){
				map[line][column]=0;
			}else if(map[line][column]==7){
				map[line][column]=3;
			}else{
				System.out.println("报错");
			}
			
			
			
			if(mapAvatar[lineA][columnA]==2){
				map[lineA][columnA]=2;
			}else if(mapAvatar[lineA][columnA]==7){
				map[lineA][columnA]=7;
			}else{
				System.out.println("报错");
			}
			
			line = lineA;
			column = columnA;

		}
		
		goAndLaunchAvatar(direction);
		upDateSokobanInfo();
		return true;
	}
	public boolean tryToPush4C(int direction4CIn){
		this.direction4C = direction4CIn;
		int caseNo=direction4C/4+1;
		int direction=direction4C%4;
		int temp=0;
		int i=0;
		int j=0;
		outer:
		for(i=0;i<N;i++){
			for(j=0;j<N;j++){
				if(map[i][j]==1||map[i][j]==6){
					temp++;
					if(temp==caseNo)break outer;
				}
			}
		}
		return avatarTryToPushTheCase(i,j,direction);
	}

	public void upDateSokobanInfo(){
		sokobanInfo = sokobanInfo();
	}
	private boolean isComplete(){
		int temp = 0;//箱子计数
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]==1)temp++;
			}
		}
		if(temp==0){
			return true;
		}
		else{
			return false;
		}
	}
	public String sokobanInfo(){
		if(isComplete()){
			return "Complete";
		}
		//下面检查是否有2*2型死胡同
		for(int i=1;i<N-1;i++){
			for(int j=1;j<N-1;j++){
				int temp = 0;
				int t = 0;
				for(int l=0;l<2;l++){
					for(int k=0;k<2;k++){
						t=map[i+l][j+k];
						if(t==4||t==1||t==6)temp++;
					}
				}
				if(temp==4){
					for(int k=0;k<2;k++){
						for(int l=0;l<2;l++){
							if(map[i+k][j+l]==1)return "2*2GameOver";
						}
					}
				}
			}
		}
		//下面检查是否有3*3回字型死胡同
		for(int i=1;i<N-2;i++){
			for(int j=1;j<N-2;j++){
				int temp = 0;
				for(int l=0;l<3;l++){
					for(int k=0;k<3;k++){
						if(map[i+l][j+k]==4||map[i+l][j+k]==1)temp++;
					}
				}
				if(temp==8&&map[i+1][j+1]==0){
					for(int l=0;l<3;l++){
						for(int k=0;k<3;k++){
							if(map[i+l][j+k]==1)return "3*3GameOver";
						}
					}
				}
				
				if(temp==8&&map[i+1][j+1]==3){
					int numOf1=0;
					if(map[i+1][j]==1)numOf1++;
					if(map[i+2][j+1]==1)numOf1++;
					if(map[i+1][j+2]==1)numOf1++;

					if(numOf1>1)return "3*3GameOver";
				}
				
				
			}
		}
		
		
		
		
		//下面检测贴边型死胡同
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]==1){
					int temp = 0;
					
					//贴左壁
					temp=0;
					for(int k=0;k<N;k++){
						for(int l=0;l<j;l++){
							if(map[k][l]==0||map[k][l]==3||map[k][l]==2||map[k][l]==7)temp++;
						}
					}
					if(temp==0){
						int goal=0;
						for(int m=0;m<N;m++){
							if(map[m][j]==3||map[m][j]==7){
								goal++;
							}
						}
						if(goal==0){
							return "LeftWallGameOver";
						}
					}

					//贴右壁
					temp=0;
					for(int k=0;k<N;k++){
						for(int l=j+1;l<N;l++){
							if(map[k][l]==0||map[k][l]==3||map[k][l]==2||map[k][l]==7)temp++;
						}
					}
					if(temp==0){
						int goal=0;
						for(int m=0;m<N;m++){
							if(map[m][j]==3||map[m][j]==7){
								goal++;
							}
						}
						if(goal==0){
							return "RightWallGameOver"+i+j+goal;
						}
					}
					//贴上壁
					temp=0;
					for(int k=0;k<i;k++){
						for(int l=0;l<N;l++){
							if(map[k][l]==0||map[k][l]==3||map[k][l]==2||map[k][l]==7)temp++;
						}
					}
					if(temp==0){
						int goal=0;
						for(int m=0;m<N;m++){
							if(map[i][m]==3||map[i][m]==7){
								goal++;
							}
						}
						if(goal==0){
							return "UpWallGameOver";
						}
					}
					//贴下壁
					temp=0;
					for(int k=i+1;k<N;k++){
						for(int l=0;l<N;l++){
							if(map[k][l]==0||map[k][l]==3||map[k][l]==2||map[k][l]==7)temp++;
						}
					}
					if(temp==0){
						int goal=0;
						for(int m=0;m<N;m++){
							if(map[i][m]==3||map[i][m]==7){
								goal++;
							}
						}
						if(goal==0){
							return "DownWallGameOver";
						}
					}
					
				}
			}
		}

		return "AllIsWell";
	}

	public int getDirection4C(){
		return direction4C;
	}
	public int[][] getMap(){
		return map;
	}
	public int[][] getMapAvatar(){
		return mapAvatar;
	}
	public void printMap(){
		for(int i=1;i<N;i++){
			for(int j=1;j<N;j++){
				switch(map[i][j]){
				case 0:System.out.print("  ");break;
				case 1:System.out.print("□ ");break;
				case 2:System.out.print("♀ ");break;
				case 3:System.out.print("× ");break;
				case 4:System.out.print("▓▓");break;
				case 5:System.out.print("  ");break;
				case 6:System.out.print("@ ");break;
				case 7:System.out.print("♂ ");break;
				default:System.out.println("地图输出switch语句报错");break;
				}
			}
			System.out.print("\n");
		}
	}
	public void printMapAvastar(){
		for(int i=1;i<N;i++){
			for(int j=1;j<N;j++){
				switch(mapAvatar[i][j]){
				case 0:System.out.print("  ");break;
				case 1:System.out.print("□ ");break;
				case 2:System.out.print("♀ ");break;
				case 3:System.out.print("× ");break;
				case 4:System.out.print("▓▓");break;
				case 5:System.out.print("  ");break;
				case 6:System.out.print("@ ");break;
				case 7:System.out.print("♂ ");break;
				default:System.out.println("地图输出switch语句报错");break;
				}
			}
			System.out.print("\n");
		}
	}
	public void flash(String pathStr){
		int length = pathStr.length();
		int[] path = new int[length];
		for(int i=0;i<length;i++){
			char temp = pathStr.charAt(i);
			path[i]=temp-48;
		}
		for(int i=0;i<length;i++){
			go(path[i]);
			printMap();
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	
}
