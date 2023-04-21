
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CPUScheduler{
	public static void main(String[] args){
		String file=args[0];
		int timeSlice=Integer.parseInt(args[1]);
		String text="";
		Scanner inStream=null;
		
		//This array will have all the processes from the file.
		process[] processArray;
		
		int currentTime=0;
		
		//Variables where we will save the total times for FCFS.
		float ttTimeFCFS=0;
		float twTimeFCFS=0;
		float trTimeFCFS=0;
		
		//Variables where we will save the total times for RR.
		float ttTimeRR=0;
		float twTimeRR=0;
		float trTimeRR=0;
		
		
		//reading the file line by line and addidng the text to "text" string.
		try{
			File file2=new File(file);
			inStream=new Scanner(file2);
			while(inStream.hasNextLine()){
				//we adding a dash (-) in between two processes so we can split them later.
				text=text+inStream.nextLine()+"-";
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
		inStream.close();
		
		//splitting the text by - and putting it in an array. So each index will have a process.
		String[] processesFromText=text.split("-");
		
		processArray=new process[processesFromText.length];
		
		for(int i=0;i<processesFromText.length;i++){
			String[] processArguements=processesFromText[i].split(" ");
			String id=processArguements[0];
			int a=Integer.parseInt(processArguements[1]);
			int b=Integer.parseInt(processArguements[2]);
			processArray[i]=new process(id,a,b);
		}
		
		//At this point we have an array processArray where we have saved all the process fetched from file.
		//before anything we have to sort the processArray according to their arrival time first.
		sort(processArray);
		System.out.println("\n-------------------------------------------------");
		System.out.println("\t\tCPU Scheduling Simulator");
		System.out.println("-------------------------------------------------\n");
		System.out.println("-------------------------------------------------");
		System.out.println("\tFirst come first served scheduling");
		System.out.println("-------------------------------------------------\n");
		
		//We going to print the run time of each process and also calculate turnaround,wait and response times.
		for(int i=0;i<processArray.length;i++){
			String id=processArray[i].id;
			int a=processArray[i].aTime;
			int b=processArray[i].bTime;
			 if(a<=currentTime){
				 System.out.println("["+currentTime+"-"+(currentTime+b)+"]\t"+id+" running");
				 
			 }
			 else{
				 System.out.println("["+currentTime+"-"+a+"]\tNo process running");
				 currentTime=a;
				 System.out.println("["+currentTime+"-"+(currentTime+b)+"]\t"+id+" running");
			 }
			 processArray[i].tTime=currentTime+b-a;
			 processArray[i].wTime=currentTime-a;
			 processArray[i].rTime=currentTime-a;
			 ttTimeFCFS=ttTimeFCFS+processArray[i].tTime;
			 twTimeFCFS=twTimeFCFS+processArray[i].wTime;
			 trTimeFCFS=trTimeFCFS+processArray[i].rTime;
			 currentTime=currentTime+b;
			 	 
		}
		
		System.out.println("\nTurnaround times:");
		
		for(int i=0;i<processArray.length;i++){
			System.out.println("\t"+processArray[i].id+": "+processArray[i].tTime);
		}
		
		System.out.println("Wait times:");
		
		for(int i=0;i<processArray.length;i++){
			System.out.println("\t"+processArray[i].id+": "+processArray[i].wTime);
		}
		
		System.out.println("Response times:");
		
		for(int i=0;i<processArray.length;i++){
			System.out.println("\t"+processArray[i].id+": "+processArray[i].rTime);
		}
		System.out.println();
		System.out.println("Average Turn around time: "+ttTimeFCFS/processArray.length);
		System.out.println("Average Wait time: "+twTimeFCFS/processArray.length);
		System.out.println("Average Response time: "+trTimeFCFS/processArray.length);
		
		//We are setting current time to zero for RR.
		currentTime=0;
		
		//this variable tells how many processes still needed to be finished.
		int totalProcessInRR=processArray.length;
		

		System.out.println("\n-------------------------------------------------");
		System.out.println("\t\tRound Robin Scheduling");
		System.out.println("-------------------------------------------------\n");
		while(totalProcessInRR>0){
			for(int i=0;i<processArray.length;i++){
				
				//to check if the process has came before to execute or not.
				if(processArray[i].track==processArray[i].bTime){
					processArray[i].rTime2=currentTime-processArray[i].aTime;
					
					
				}
				if(processArray[i].track>=timeSlice && processArray[i].aTime<=currentTime){
					System.out.println("["+currentTime+"-"+(currentTime+timeSlice)+"]\t"+processArray[i].id+" running");
					processArray[i].track=processArray[i].track-timeSlice;
					currentTime=currentTime+timeSlice;
					
					//if process has finished calculate tTime2 & wTime2 for that process.
					if(processArray[i].track<=0){
						processArray[i].tTime2=currentTime-processArray[i].aTime;
						processArray[i].wTime2=processArray[i].tTime2-processArray[i].bTime;
						ttTimeRR=ttTimeRR+processArray[i].tTime2;
						twTimeRR=twTimeRR+processArray[i].wTime2;
						totalProcessInRR--;
					}
				}
				else if(processArray[i].track<timeSlice && processArray[i].track>0 && processArray[i].aTime<=currentTime){
					System.out.println("["+currentTime+"-"+(currentTime+processArray[i].track)+"]\t"+processArray[i].id+" running");
					currentTime=currentTime+processArray[i].track;
					processArray[i].track=0;
					
					//it is obvious here that this will be last time this process is running so cal tTime2 & wTime2.
					processArray[i].tTime2=currentTime-processArray[i].aTime;
					processArray[i].wTime2=processArray[i].tTime2-processArray[i].bTime;
					ttTimeRR=ttTimeRR+processArray[i].tTime2;
					twTimeRR=twTimeRR+processArray[i].wTime2;
					totalProcessInRR--;
				}
			}
		}
		
		System.out.println("\nTurnaround times:");
		
		for(int i=0;i<processArray.length;i++){
			System.out.println("\t"+processArray[i].id+": "+processArray[i].tTime2);
		}
		
		System.out.println("Wait times:");
		
		for(int i=0;i<processArray.length;i++){
			System.out.println("\t"+processArray[i].id+": "+processArray[i].wTime2);
		}
		
		System.out.println("Response times:");
		
		for(int i=0;i<processArray.length;i++){
			System.out.println("\t"+processArray[i].id+": "+processArray[i].rTime2);
			trTimeRR=trTimeRR+processArray[i].rTime2;
		}

		System.out.println("\nAverage Turn around time: "+ttTimeRR/processArray.length);
		System.out.println("Average Wait time: "+twTimeRR/processArray.length);
		System.out.println("Average Response time: "+trTimeRR/processArray.length);
		
		System.out.println("\n-------------------------------------------------");
		System.out.println("Project done by [Usama Ikram]");
		System.out.println("-------------------------------------------------\n");
		
		
	}
	
	//to sort processes array.
	static void sort(process[] arr){
		int l=arr.length;
		for(int i=1;i<l;i++){
			process temp=arr[i];
			int j=i-1;
			while((j>-1)&&(arr[j].aTime>temp.aTime)){
				arr[j+1]=arr[j];
				j--;
			}
			arr[j+1]=temp;
		}
	}
}

class process{
		String id;
		int aTime;
		int bTime;
		//for FCFS.
		int tTime;
		int wTime;
		int rTime;
		//for RR.
		int tTime2;
		int wTime2;
		int rTime2;
		
		//variable to track how much process time left to complete for RR.
		int track;
		
		public process(String id,int a,int b){
			this.id=id;
			aTime=a;
			bTime=b;
			track=bTime;
		}
	}