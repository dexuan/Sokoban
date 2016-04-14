package myPackage;

import java.util.Scanner;

public class Coastline {
	private int Y = Constant.Y;
	private int C = new Constant().C;//必须new
	public Sokoban[] mySokoban= new Sokoban[Y];

	int step = -1;
	int direction4C = -1;
	
	int[][] key = new int [Constant.Q][Y];
	int keyNum = 0;
	Scanner S = new Scanner(System.in);

	public Coastline(){
		
		for(int i=0;i<Constant.Q;i++){
			for(int j=0;j<Y;j++){
				key[i][j]=-10;
			}
		}
		

		for(int i=0;i<Y;i++){
			mySokoban[i] = new Sokoban();
		}
		mySokoban[0].setMapAndLaunchAvatar(new Constant().initialMap);
		mySokoban[0].printMap();
		System.out.println("正在寻找……");
		
		step = 1;
		direction4C = 0;

		long time = System.currentTimeMillis();
		long timeLength = -1;
		
		int i = 1;
		for(i=1;1!=0;i++){
			if(step==0)break;
			
			if(step>=Y-1){
				changeDirectionAndStep();
				continue;
			}
			if(key[0][0]!=-10){
				if(step>=key[0][0]){
					changeDirectionAndStep();
					continue;
				}
			}
			mySokoban[step].setMapAndLaunchAvatar(mySokoban[step-1].getMap());
	
			boolean tryValue = mySokoban[step].tryToPush4C(direction4C);
			if(tryValue){
				String info = mySokoban[step].sokobanInfo;
				boolean isACycle = thereIsACycle();
				
				//
//				mySokoban[step].printMapAvastar();
//				System.out.println("循环"+i);
//				System.out.println("步数为："+step+"方向为："+direction4C);
//				System.out.println("判定为"+info+"\n成环？："+isACycle);
				//S.nextLine();
				//
				
				if(isACycle){
					changeDirectionAndStep();
					continue;
				
				}else if(info.equals("AllIsWell")){
					step++;
					direction4C=0;
					continue;
					
				}else if(info.equals("Complete")){
					if(newKey()){
						timeLength = System.currentTimeMillis()-time;
						
						//System.out.println("已经找到一种答案，是否寻找更短的答案？\n（输入“y”继续寻找，输入别的终止寻找并演示答案）");
						//if(S.nextLine().equals("y")==false){
							break;
						//}
						
					}
					step++;
					direction4C=0;
					continue;
				}else{//表明推成了死局
					changeDirectionAndStep();
					continue;
				}
			}else{//表明没推动
				changeDirectionAndStep();
				continue;
			}
				
		}
		if(key[0][0]<0){
			System.out.println("未找到答案");
		}
		else{
			System.out.println("用时"+timeLength/1000+"秒找到第一条答案");
			System.out.println("最短答案的步数为："+key[0][0]+"步");
			System.out.println("2秒后开始演示");
			for(int j=0;j<20;j++){
				System.out.print("▌");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.print("\n\n");
			Sokoban showSokoban = new Sokoban();
			showSokoban.setMapAndLaunchAvatar(new Constant().initialMap);
			showSokoban.printMap();
			for(int j=1;j<=key[0][0];j++){
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				showSokoban.tryToPush4C(key[0][j]);
				showSokoban.printMap();
				
				//System.out.println(key[0][j]);
			}
			System.out.println("Complete!");
		}
		
	}
	
	private boolean newKey() {
		int i=1;
		for(i=1;i<=step;i++){
			key[keyNum][i]=mySokoban[i].getDirection4C();
		}
		key[keyNum][0]=i-1;//[0]用于记录该答案的长度
		if(keyNum!=0&&key[keyNum][0]<key[0][0]){
			for(int k=0;k<Y;k++){
				key[0][k]=key[keyNum][k];
			}
			keyNum++;
			return false;
		}
		else{
			keyNum++;
			return true;
		}
		
	}

	private boolean thereIsACycle() {
		for(int i=step-2;i>=0;i--){
			if(new Tools().AIsSameToB(mySokoban[step].getMapAvatar(), mySokoban[i].getMapAvatar())){
				return true;
			}
		}
		return false;
	}
	private void changeDirectionAndStep() {
		while(true){
			if(direction4C==4*C-1){
				step=step-1;
				direction4C = mySokoban[step].getDirection4C();
			}
			else{
				direction4C++;
				break;
			}
		}
		
		
	}
}
