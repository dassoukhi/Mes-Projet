package kakuro;

import java.awt.Dimension;

public class TailleFrame {

	int[] donneMoiLaBonneTaille(int n1, int n2)
	{
		 int k1; int k2;
		int dim[] = new int[2];
		if(n1 == n2 && n1 <= 10)
		{
			k1=40*n1+130+25; k2 =50*n2+90;
			dim[0]=k1;
			dim[1]=k2;
			
		}
		else if( n1 == n2 && n1<= 13)
		{
			k1=40*n1+130+70; k2 = 50*n2+90;
			dim[0]=k1;
			dim[1]=k2;
			
		}
		else if(n1 == n2 && n1 <=16)
		{
			k1=40*n1+130+110; k2 = 50*n2+90;
			dim[0]=k1;
			dim[1]=k2;
			
		}
		else {
			
			switch (n1) {
			case 7:
				if(n2 == 8)
				{
					k1=40*n1+100; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 9)
				{
					k1=40*n1+130+90; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 10)
				{
					k1=40*n1+130+140; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 11)
				{
					k1=40*n1+130+200; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 12)
				{
					k1=40*n1+130+260; k2 = 50*n2+90;
				}
				else if(n2 == 13)
				{
					k1=40*n1+130+320; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 14)
				{
					k1=40*n1+130+390; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 15)
				{
					k1=40*n1+130+440; k2 = 50*n2+90;
				}
				else if( n2 == 16)
				{
					k1=40*n1+130+490; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				break;
			case 8:
				if(n2 == 7)
				{
					k1=40*n1+130+50; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 9)
				{
					k1=40*n1+190; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 10)
				{
					k1=40*n1+240; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 11)
				{
					k1=40*n1+290; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 12)
				{
					k1=40*n1+340; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 13)
				{
					k1=40*n1+400; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 14)
				{
					k1=40*n1+450; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 15)
				{
					k1=40*n1+520; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if( n2 == 16)
				{
					k1=40*n1+580; k2 = 50*n2+90;
					dim[0]=k1;
					dim[1]=k2;
				}
				
				break;
			case 9:
				if(n2 == 7)
				{
					k1= 40*n1+50; k2= 50*n2+190;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 8)
				{
					k1=40*n1+90; k2=50*n2+190;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 10)
				{
					k1=40*n1+230; k2= 50*n2+150;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 11)
				{
					k1=40*n1+280; k2= 50*n2+100;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 12)
				{
					k1=40*n1+340; k2=50*n2+100;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 13)
				{
					k1=40*n1+390; k2=50*n2+50;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 14)
				{
				k1=40*n1+440; k2=50*n2+50;
				dim[0]=k1;
				dim[1]=k2;
				}
				else if(n2 == 15)
				{
					k1=40*n1+490; k2=50*n2;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if( n2 == 16)
				{
					k1=40*n1+540; k2=40*n2;
					dim[0]=k1;
					dim[1]=k2;
				}
				
				break;
			case 10:
				if(n2 == 7)
				{
					k1=40*n1; k2=50*n2+260;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 8)
				{
					k1=40*n1+90; k2=50*n2+260;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 9)
				{
					k1=40*n1+140; k2= 50*n2+260;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 11)
				{
					k1=40*n1+240; k2= 50*n2+160;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 12)
				{
					k1=40*n1+290; k2= 50*n2+100;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 13)
				{
					k1=40*n1+340; k2= 50*n2+50;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 14)
				{
					k1=40*n1+390; k2= 50*n2+0;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 15)
				{
				k1=40*n1+440; k2= 45*n2+0;
				dim[0]=k1;
				dim[1]=k2;
				}
				else if( n2 == 16)
				{
					k1=40*n1+490; k2= 45*n2+0;
					dim[0]=k1;
					dim[1]=k2;
				}
				break;
			case 11:
				if(n2 == 7)
				{
					k1=40*n1-10; k2= 45*n2+300;
					dim[0]=k1;
					dim[1]=k2;

				}
				else if(n2 == 8)
				{
					k1=40*n1; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;

				}
				else if(n2 == 9)
				{
					k1=40*n1+70; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;
		
				}
				else if(n2 == 10)
				{
					k1=40*n1+150; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;

				}
				else if(n2 == 12)
				{
					k1=40*n1+250; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 13)
				{
					k1=40*n1+320; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 14)
				{
						k1=40*n1+370; k2=  45*n2+300;
						dim[0]=k1;
						dim[1]=k2;

				}
				else if(n2 == 15)
				{
					k1=40*n1+420; k2=  45*n2+300;

				}
				else if( n2 == 16)
				{
					k1=40*n1+460; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;
		
				}
				
				break;
			case 12:
		      if(n2 == 7)
				{
		    	  k1=40*n1+50; k2= 45*n2+400;
		    	  dim[0]=k1;
					dim[1]=k2;

				}
				else if(n2 == 8)
				{
				k1=40*n1+50; k2=  45*n2+400;
				dim[0]=k1;
				dim[1]=k2;

				}
				else if(n2 == 9)
				{
					k1=40*n1+50; k2=  45*n2+400;
					dim[0]=k1;
					dim[1]=k2;

				}
				else if(n2 == 10)
				{
					k1=40*n1+100; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;
			
				}
				else if(n2 == 11)
				{
					k1=40*n1+150; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;
				}
				else if(n2 == 13)
				{	
					k1=40*n1+200; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;


				}
				else if(n2 == 14)
				{
					k1=40*n1+270; k2=  45*n2+300;
					dim[0]=k1;
					dim[1]=k2;

				}
				else if(n2 == 15)
				{
			k1=40*n1+360; k2=  45*n2+300;
			dim[0]=k1;
			dim[1]=k2;


				}
				else if( n2 == 16)
				{
				k1=40*n1+420; k2=  45*n2+300;
				dim[0]=k1;
				dim[1]=k2;


				}
				
				break;
			case 13:
			  if(n2 == 7)
				{
				k1=40*n1-90; k2=  45*n2+350;
				dim[0]=k1;
				dim[1]=k2;

				}
				else if(n2 == 8)
				{
				k1=40*n1-50; k2=  45*n2+350;
				dim[0]=k1;
				dim[1]=k2;

				}
				else if(n2 == 9)
				{
			k1=40*n1; k2=  45*n2+350;
			dim[0]=k1;
			dim[1]=k2;

				}
				else if(n2 == 10)
				{
			k1=40*n1+50; k2=  45*n2+500;
			dim[0]=k1;
			dim[1]=k2;

				}
				else if(n2 == 11)
				{
			k1=40*n1+100; k2=  45*n2+500;
			dim[0]=k1;
			dim[1]=k2;

				}
				else if(n2 == 12)
				{	
				k1=40*n1+150; k2=  45*n2+700;
				dim[0]=k1;
				dim[1]=k2;


				}
				else if(n2 == 14)
				{
				k1=40*n1+250; k2=  45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

				}
				else if(n2 == 15)
				{
				k1=40*n1+340; k2=  45*n2+700;
				dim[0]=k1;
				dim[1]=k2;
			
				}
				else if( n2 == 16)
				{
				k1=40*n1+390 ; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

				}
				
				break;
			case 14:

				
			  if(n2 == 7)
				{
				k1=40*n1-150; k2=  45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

				}
				else if(n2 == 8)
				{
			k1=40*n1-90; k2=  45*n2+700;
			dim[0]=k1;
			dim[1]=k2;

				}
				else if(n2 == 9)
				{
					k1=40*n1-30; k2=  45*n2+700;
					dim[0]=k1;
					dim[1]=k2;


				}
				else if(n2 == 10)
				{
				k1=40*n1+20; k2=  45*n2+700;
				dim[0]=k1;
				dim[1]=k2;
				}
				else if(n2 == 11)
				{
			k1=40*n1+70; k2=  45*n2+700;
			dim[0]=k1;
			dim[1]=k2;
				}
				else if(n2 == 12)
				{	
			k1=40*n1+120 ; k2= 5*n2+700;
			dim[0]=k1;
			dim[1]=k2;


				}
				else if(n2 == 13)
				{
				k1=40*n1+170; k2=  45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

				}
				else if(n2 == 15)
				{
				k1=40*n1+260; k2=  45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

			
				}
				else if( n2 == 16)
				{
				k1=40*n1+250; k2=  45*n2+700;


				}
				
				break;
			case 15:

				  if(n2 == 7)
				{
				k1=40*n1-180; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;
				}
				else if(n2 == 8)
				{
				k1=40*n1-130; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

				}
				else if(n2 == 9)
				{
				k1=40*n1-90; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;


				}
				else if(n2 == 10)
				{
				k1=40*n1-40; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;


				}
				else if(n2 == 11)
				{
			k1=40*n1+20; k2= 45*n2+700;
			dim[0]=k1;
			dim[1]=k2;

				}
				else if(n2 == 12)
				{	
				k1=40*n1+70; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;


				}
				else if(n2 == 13)
				{
				k1=40*n1+120; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

				}
				else if(n2 == 14)
				{
			k1=40*n1+180; k2= 45*n2+700;
			dim[0]=k1;
			dim[1]=k2;

			
				}
				else if( n2 == 16)
				{

				k1=40*n1+280; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

				}
						
			break;
			case 16:
				
			  if(n2 == 7)
			{
			k1=40*n1-230 ; k2=5*n2+700;
			dim[0]=k1;
			dim[1]=k2;
			}
			else if(n2 == 8)
			{
				
				k1=40*n1-180; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

			}	

			else if(n2 == 9)
			{

				k1=40*n1-130; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

			}
			else if(n2 == 10)
			{
				k1=40*n1-80; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;


			}
			else if(n2 == 11)
			{
			k1=40*n1-30; k2= 45*n2+700;
			dim[0]=k1;
			dim[1]=k2;

			}
			else if(n2 == 12)
			{	

				k1=40*n1+20; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

			}
			else if(n2 == 13)
			{
				k1=40*n1+80; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;
				

			}
			else if(n2 == 14)
			{
				k1=40*n1+140; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

			
			}
			else if( n2 == 15)
			{
				k1=40*n1+190; k2= 45*n2+700;
				dim[0]=k1;
				dim[1]=k2;

			}
					
			break;
						
					
			}
			
			
		}
		
		return dim;
		
		
		
		
		
		 
	}
	
}
