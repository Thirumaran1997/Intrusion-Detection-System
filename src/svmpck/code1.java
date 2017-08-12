package svmpck;


import java.util.Scanner;

import libsvm.svm;
import libsvm.svm_model;

import java.util.Random;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Math;
import java.math.BigInteger;

public class code1 
{	int num;
    String bin[] = new String[num+1]; 
    static double distance(int a, int b,int bits) 
		{
		int t,c,d,y; double e,dsum=0;
		
		for(int j=0;j<bits;j++)
		{
		 t = (int)Math.pow(2,j);
		 c = a&t;
		 d = b&t;
		 y = c-d;
		 e = Math.pow(y,2);
		 dsum = dsum+e;
		
		}
		
		double dist =(double)Math.sqrt(dsum);
		System.out.println("distance : "+dist);
		
		return dist;
		}
		
		
	static double randvalue()
		{
		Random rand = new Random(); 
		double value =1.0f -  rand.nextFloat();
		
		return value;
		}
		
	
	public static void main(String[] args)
		{
		 double ran;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of particles ");
		int num=sc.nextInt();
		
		System.out.println("Enter the number of bits ");
		int bits=sc.nextInt();
		String  bin[] = new String[num+1]; 
		double fit[] = new double[num]; 
		double f[] = new double[50]; 
		double v[]=new double[num+1];
		double acc[]=new double[num+1]; 
		int bin1[]=new int[num+1];
		int dec1[]= new int[num+1];
		double s[]=new double[num+1];
		double force[] = new double[num+1]; force[0]=0;
		double q[] = new double[num+1];
		double mass[] = new double[num+1]; v[0]=0;
		String dec[] = new String[num+1];
		int decimal = 0; int i,j,t,c,d,y,e,dsum; double sum=0; dsum=0; double dist=0;int temp=0,k=0;
		//int power = 0;double x=0;y=0;
		//bin[0][0]=1;
		svmtrain[] o=new svmtrain[num]; 
		svmpredict[] h=new svmpredict[num];
		
		
		for(i=0;i<num;i++)
		{
			BigInteger b = new BigInteger(bits,0,new Random());
			bin[i] = b.toString(2);
			dec[i] = b.toString(10);			
			bin1[i]=Integer.parseInt(bin[i]);
			dec1[i]=Integer.parseInt(dec[i]);
			
		}
	    
		for(i=0;i<num;i++)
		{
			o[i]=new svmtrain();
			String st=bin[i];
			System.out.println(st);
			o[i].run(st,num,bits);
		}
		
		for(i=0;i<num;i++)
		{	
			//f[i]=h[i].getacc();
			
			int predict_probability=0;
			final String FILENAME = "a1a.train";
			//InputStream br=new FileInputStream("C:/Users/Thirumaran.Thiru/Documents/workspace-sts-3.8.4.RELEASE/svm/src/svmpck/a1a.txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(FILENAME));
				DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("output.txt")));
			svm_model model = svm.svm_load_model("a1a.train.model");
			System.out.println("sds");
				f[i]=h[i].predict(br,output,model,predict_probability);
				System.out.println("sds");
				br.close();
				output.close();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(f[i]);
		catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	    	
		for(i=0;i<num;i++)
		{
	     		
		System.out.println("fitness values :"+fit[i]);
		}
		
		//finding the worst value
		double worst = fit[0];
		for(i=1;i<num;i++)
		{
		if(fit[i]>worst)
		worst = fit[i];
		
		}
	  System.out.println("worst : "+worst);
	   // finding the best value
		double best = fit[0];
		for(i=1;i<num;i++)
		{
		if(fit[i]<best)
		best = fit[i];
	
		}
	    System.out.println("best : "+best);
		
		for(i=0;i<num;i++)
		{	
		
		q[i] = (fit[i]-worst)/(best-worst);
	//	double tina = (double)(fit[i]-worst)/(best-worst);
		
		sum = sum+q[i];
		
		
		
		}
		//System.out.println("sum"+sum);
		
		
		for(i=0;i<num;i++)
		{
			
		mass[i]= q[i]/sum;
		//System.out.println("i"+q[i]+sum+mass[i]);
		
		}
		double value;
		for(i=0;i<num;i++)
		{
			for(j=0;j<num;j++)
			{
				if(i!=j)
				{
					double x1=(double)(1000.0*mass[i]*mass[j]);
					
					//System.out.println("massi"+mass[i]);
					//System.out.println("massj"+mass[j]);
					
					double y1=(double)(distance(dec1[i],dec1[j],bits)+0.3);
					
					double z1=distance(dec1[i],dec1[j],bits);
					
					
				//System.out.println("x:"+x1+"y:"+y1+"z:"+z1);
				f[k] = (x1/y1)*z1;
				//System.out.println("x1"+x1);
				//System.out.println("y1"+y1);
				//System.out.println("z1 "+z1);
				//System.out.println("(x1/y1)*z1"+(x1/y1)*z1);
				//System.out.println("f[k]"+f[k]);
				
				ran=(double)randvalue();
				
				
				//System.out.println("force "+i+" "+force[i]);
				//System.out.println("f[k] "+f[k]);
				//System.out.println("ran "+ran);
				///System.out.println("ran*f[k] "+ran*f[k]);
				force[i]=ran*f[k];
				System.out.println("force "+i+" "+force[i]);
				
				k++;
				}
			}
		}
				
		for(i=0;i<num;i++)
		{
		if(mass[i]==0)
			mass[i]=1;
			
		force[i]=Math.abs(force[i]);
		mass[i]=Math.abs(mass[i]);
		System.out.println(force[i]+" "+mass[i]);
	    acc[i]= force[i]/mass[i];
		
		System.out.println("acceleration"+acc[i]);
				
		}
		
		for(i=0;i<num;i++)
		{
		value=randvalue();
		v[i+1]=(value*v[i]) + acc[i];		
		//System.out.println("val"+value*v[i]+acc[i]);
		s[i]= (double)Math.tanh(v[i+1]);
		//System.out.println(v[i+1]+":"+(double)Math.tanh(v[i+1]));
	
		if(value>s[i])
		dec1[i+1]=~dec1[i];
		else
		dec1[i+1]=dec1[i];
		}
		for(i=0;i<num+1;i++)
			System.out.println(s[i]+" ");
		
	}
}