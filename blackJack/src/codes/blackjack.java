package codes;

import java.util.Scanner;

public class blackjack {
	
	public static Scanner input = new Scanner(System.in);//Scanner for input
	
	public static void populate(int[] cards, char[] suit)
	{
		for (int x = 0; x < cards.length; x++)//populating sequentially
		{
			cards[x] = (x)%13 + 1;
		}
		
		for (int x = 0; x < suit.length; x++)
		{
			if( ((x/13)+1) == 1 )
				suit[x] = 'H';//Hearts
			else if( ((x/13)+1) == 2)
				suit[x] = 'S';//Spades
			else if ( ((x/13)+1) == 3)
				suit[x] = 'D';//Diamonds
			else 
				suit[x] = 'C';//Clubs
		}
		
	}
	
	public static void display(int[] cards, char[] suit)
	{
			for (int x = 0; x < cards.length; x++)
			{
			
				System.out.print(cards[x]);//displaying card number
				
				System.out.print(suit[x] + " ");//displaying suit
				
				if ((x+1)%13 == 0)
					System.out.println();
			}
	}
	
	public static void shuffle (int[] cards, char[] suit)
	{
		int random = 0;
		for (int x = 0; x < cards.length; x++)
		{
			random = (int)(Math.random()*52);
			
			int temp = cards[x];
			cards[x] = cards[random];//shuffles card
			cards[random] = temp;
			
			char temp2 = suit[x];
			suit[x] = suit[random];//shuffles suit
			suit[random] = temp2;
		}
		
	}
	
	public static void deal (int[] cards, char[] suit, int []pCards, char []pSuit, int []cCards, char []cSuit)
	{
		
		for(int pDeck = 0; pDeck <= 6; pDeck++)
		{
			pCards[pDeck] = cards[pDeck];
			pSuit[pDeck] = suit[pDeck];
		}
	
		for (int cDeck = 1; cDeck <= 6; cDeck++)
		{
			cCards[cDeck] = cards[cDeck];
			cSuit[cDeck] = suit[cDeck];
		}
		
	}
	
	public static void displayPlayerDeck (int index1, int []pCards, char []pSuit)
	{
		System.out.print(pCards[index1]);
		System.out.print(pSuit[index1]);
		System.out.println();
				
	}
	
	public static void displayCompDeck (int index2, int []cCards, char []cSuit)
	{
		System.out.print(cCards[index2]);
		System.out.print(cSuit[index2]);
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		int playAgain = 0; 
		
		
		String username = "";
		System.out.println("Enter your username");
		username = input.next();
		
		do{
			
		boolean win = false;
		final int SIZE = 52;
		
		int []cards = new int [SIZE];//initial array for cards
		char []suit = new char[SIZE];//initial array for suit
		
		int []pCards = new int [SIZE];//cards for player
		char []pSuit = new char [SIZE];//suit for player
		
		int []cCards = new int[SIZE];//cards for computer
		char []cSuit = new char [SIZE];//suit for computer
		
		
		int play = 0; 
		int menuChoice = 0;
		int pSum = 0; //counts player sum of cards
		int cSum = 0; //counts computer sum of cards
		int pCounter = 2; //counts amount of cards of player
		int cCounter = 2; //counts amount of cards of dealer
			
		int index1 = 3; //starting position for player cards
		
		populate(cards,suit); //populate 1-13 4 times
			
		shuffle(cards,suit); //shuffle all 52 cards
			
		//display(cards,suit); //display all 52 cards
			
		System.out.println("------Welcome to Blackjack!------");
		System.out.println();
		
			
		deal(cards, suit, pCards, pSuit, cCards, cSuit); //deal 5 cards to each player
		
		System.out.println("Welcome: " + username);
		System.out.println();
		System.out.println("Your current hand is: ");
		displayPlayerDeck(0, pCards, pSuit); //displays card 1
		displayPlayerDeck(2, pCards, pSuit);//displays card 2
		pSum = pCards[0] + pCards[2]; //sums up initial cards of player
		System.out.println("The sum of your cards is: " + pSum);
		
		
		if (pSum > 21)
		{
			System.out.println("You lose!");
			continue;		
		}
		
		
		System.out.println();
		System.out.println("The Dealer's hand is: ");
			
		displayCompDeck(1, cCards, cSuit);//displays card 1
		System.out.println("##");//hides card 2
		cSum = cCards[1] + cCards[3]; //sums up initial cards of dealer
		
		
			do{
			
			System.out.println();
			System.out.println("1. Hit");
			System.out.println("2. Stand");
			
			menuChoice = input.nextInt();
			
			if(menuChoice == 1)
			{
				index1++;
				pCounter++;
				System.out.println("You got the card: ");
				displayPlayerDeck(index1, pCards, pSuit); //displays player card that was drawn
				pSum += pCards[index1]; //sum of all player cards after new one is drawn
				System.out.println("Your new sum is: " + pSum);
				
				
				if(pSum > 21)
				{
					System.out.println("You lost! Your sum is greater than 21!");
					win = false;
					break;
				}
				
				else if(pCounter == 5)
				{
					System.out.println("You win!");
					win = true;
					break;
				}	
			}
			
			
			if (menuChoice == 2)
			{
				System.out.println("The value of the Dealer's hand is: ");
				System.out.println(cSum);
				System.out.println();
				
				do{ //loops if dealer sum is < 17
				
					if(cSum < 17)
					{
						System.out.println("The value of the dealer's hand is less than 17, they take another card");
						System.out.println();
						index1++;
						cCounter++;
						cSum += cCards[index1]; //sum of all computer cards after new one is drawn
						System.out.println("The dealer drew a " + cCards[index1] + cSuit[index1]);
						System.out.println("The new value of the dealer's hand is ");
						System.out.println(cSum);
						System.out.println();
						
						if (cSum > 21)
						{
							System.out.println("You win! The dealer's sum is greater than 21!");
							win = true;
							play = 1;
							break;
						}
						
						if (cSum <= 21 && cCounter == 5) //if the dealer sum is 21 and has 5 cards, you lose
						{
							System.out.println("You lose!");
							win = true;
							play = 1;
							break;
						}
						
					}
			
				}while(cSum < 17);
				
				if (cSum >= 17 && win == false) //runs this only if you didn't win or lose above
					{
						if (cSum > 21)
						{
							System.out.println("You win!");
							break;
						}
						if(pSum > cSum)
						{
							System.out.println("You win!");
							break;
						}
						if (pSum < cSum)
						{
							System.out.println("You lose!");
							break;
						}
						
						else if (pSum == cSum)
						{
							System.out.println("Tie!");
							break;
						}
					}
			}
			
			}while(play == 0);
			
			System.out.println("Do you want to play again? 1 for yes, 2 for no");
			playAgain = input.nextInt();
			
			
	}while(playAgain == 1);

	}
}
