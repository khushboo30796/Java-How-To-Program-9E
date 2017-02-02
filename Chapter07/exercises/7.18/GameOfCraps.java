import java.util.Random;
public class GameOfCraps
{
	private static final Random randomNumbers=new Random();
	private enum Status {CONTINUE,WON,LOST};
	private static final int SNAKE_EYES=2;
	private static final int TREY=3;
	private static final int SEVEN=7;
	private static final int YO_LEVEN=11;
	private static final int BOX_CARS=12;
	public static int rollDice()
	{
		int die1=1+randomNumbers.nextInt(6);
		int die2=1+randomNumbers.nextInt(6);
		int sum=die1+die2;
		return sum;
	}
		public static int result()
	{
		int myPoint=0;
		Status gameStatus;
		int sumOfDice=rollDice();
		int no=1;
		switch(sumOfDice)
		{
			case SEVEN:
			case YO_LEVEN:
				gameStatus=Status.WON;
			break;
			case SNAKE_EYES:
			case TREY:
			case BOX_CARS:
				gameStatus=Status.LOST;
			break;
			default: 
				gameStatus=Status.CONTINUE;
				myPoint=sumOfDice;
				break;
		}
		while(gameStatus==Status.CONTINUE)
		{
			no++;
			sumOfDice=rollDice();
			if(sumOfDice==myPoint)
				gameStatus=Status.WON;
			else if(sumOfDice==SEVEN)
				gameStatus=Status.LOST;
		}
		if(gameStatus==Status.WON)
			return no;
		else
			return -no;
	}
	public static void main(String[] args)
	{
		int[] wonAt=new int[21];
		int[] lostAt=new int[21];
		int noOfWins=0;
		int sumLength=0;
		int no;
		for(int i=0;i<1000000;i++)
		{
			no=result();
			if(no>0)
			{
				noOfWins++;
				sumLength+=no;
				++wonAt[no>20?20:no-1];
			}
			else
			{
				sumLength-=no;
				++lostAt[-no>20?20:-no-1];
			}
		}
		System.out.printf("%s%10s%10s\n","Roll","Won","Lost");
		for(int i=0;i<21;i++)
		{
			if(i==20)
				System.out.printf(">20%10d%10d\n",wonAt[20],lostAt[20]);
			else
				System.out.printf("%3d%10d%10d\n",i+1,wonAt[i],lostAt[i]);
		}
		
		System.out.printf("Chances at winning: %.2f\n",(double)noOfWins/10000);
		System.out.printf("Average length:%.2f\n",(double)sumLength/1000000);
	}
}