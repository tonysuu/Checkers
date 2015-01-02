import hsa.*;
import java.awt.*;
public class CheckersTonySu
{
    public static void main (String[] args)
    {
	Console c = new Console (); //Console Declarlation
	Console d = new Console ();

	String board[] = new String [32];
	for (int i = 0 ; i < 16 ; i++)
	{
	    board [i] = Integer.toString (i + 1);
	}

	d.println ("Reference board"); //Console d reference board setup
	d.println ();
	for (int i = 0 ; i < 16 ; i++)
	{
	    d.print (board [i] + "\t");
	    if (Integer.parseInt (board [i]) % 4 == 0)
	    {
		d.println ();
		d.println ();
	    }
	}

	for (int i = 16 ; i < 32 ; i++)
	{
	    board [i] = "-1";
	}


	int count = 0;
	board [0] = "/"; // Console c board setup
	board [2] = "/";
	board [5] = "/";
	board [7] = "/";
	board [8] = "/";
	board [10] = "/";
	board [13] = "/";
	board [15] = "/";
	board [1] = "B";
	board [3] = "B";
	board [12] = "A";
	board [14] = "A";

	int option = -1; // Game type selection
	c.print ("Enter 1 for P1 v P2, 2 for P vs Easy Comp, 3 for P vs Hard Comp: ");
	while (true)
	{
	    option = c.readInt ();
	    if (option != 3 && option != 2 && option != 1)
	    {
		c.print ("Invalid option selection option, try again: ");
	    }
	    else
	    {
		break;
	    }
	}

	c.clear (); // Clears screen for board

	for (int i = 0 ; i < 16 ; i++) // Game board printing
	{
	    c.print (board [i] + "\t");
	    if ((i + 1) % 4 == 0)
	    {
		c.println ();
		c.println ();
	    }
	}

	int checka = 2; //# of checkers per player
	int checkb = 2;
	int choice = 0;


	if (option == 1) // Player vs Player
	{
	    while (checka > 0 && checkb > 0)
	    {
		if (choice % 2 == 0) // Determines whether it is the turn of player 1
		{
		    c.println ("Player A: select a checker(#):"); // Checker selection
		    int select = c.readInt ();
		    boolean select1 = true;
		    if (select < 1 || select > 16) // Checks if checker at location exists
		    {
			select1 = false;
		    }
		    else
		    {
			if (board [select - 1].equals ("A"))
			{
			    count = 1;
			}
			else if (board [select - 1].equals ("K"))
			{
			    count = 2;
			}
			else
			{
			    select1 = false;
			}
		    }
		    if (select1 == false)
		    {
			while (true) // Checker reselection if previous selection was incorrect
			{


			    c.println ("Checker A does not exist at position " + select);
			    c.print ("Select another Checker A: ");
			    select = c.readInt ();
			    if (select >= 1 && select <= 16)
			    {
				if (board [select - 1].equals ("A")) // If correct, game proceeds
				{
				    count = 1;
				    break;
				}
				else if (board [select - 1].equals ("K"))
				{
				    count = 2;
				    break;
				}
			    }
			}
		    }
		    board [select - 1] = Long.toString (select);


		    c.println ("Player A: enter the position you want to move it to:"); // Checker move to command entry
		    int move = c.readInt ();
		    boolean move1 = false;
		    // Board move limiter
		    if (count == 1)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("K") && !board [move - 1].equals ("Q") && ((select - move) == 3 || (select - move) == 5))
			{
			    move1 = true;
			}
		    }
		    if (count == 2)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("K") && ((Math.abs (move - select) == 3) || (Math.abs (move - select) == 5)))
			{
			    move1 = true;
			}
		    }

		    if (move1 == false)
		    {
			while (true) // Incorrect move reselection
			{
			    if (count == 1)
			    {
				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					c.println ("ILLEGAL MOVE!");
					c.println ("Enter another position to move to: ");
					move = c.readInt ();
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				// Eat for A/B
				if (((move + 4) <= 16) && !board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && !board [(move - 1)].equals ("Q") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move + 2)].equals ("B") || board [Math.abs (move + 2)].equals ("Q"))
				    {
					board [move + 2] = Long.toString (move + 3);
					checkb--;
					break;
				    }
				    if (board [Math.abs (move + 4)].equals ("B") || board [Math.abs (move + 4)].equals ("Q"))
				    {
					board [move + 4] = Long.toString (move + 5);
					checkb--;
					break;
				    }
				}
			    }
			    else
			    {
				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					c.println ("ILLEGAL MOVE!!");
					c.println ("Enter another position to move to: ");
					move = c.readInt ();
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				// K or Q killing
				if (!board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && !board [(move - 1)].equals ("Q") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move + 2)].equals ("B") || board [Math.abs (move + 2)].equals ("Q"))
				    {
					board [move + 2] = Long.toString (move + 3);
					checkb--;
					break;
				    }
				    if (board [Math.abs (move - 4)].equals ("B") || board [Math.abs (move - 4)].equals ("Q"))
				    {
					board [move - 4] = Long.toString (move - 3);
					checkb--;
					break;
				    }

				    if (board [Math.abs (move - 6)].equals ("B") || board [Math.abs (move - 6)].equals ("Q"))
				    {
					board [move - 6] = Long.toString (move - 5);
					checkb--;
					break;
				    }
				    if (board [Math.abs (move + 4)].equals ("B") || board [Math.abs (move + 4)].equals ("Q"))
				    {
					board [move + 4] = Long.toString (move + 5);
					checkb--;
					break;
				    }


				}
			    }
			    if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("K")) //&& ((select - move) == 3) || ((select - move) == 5))
			    {
				if ((select - move == 3) || (select - move == 5))
				{
				    break;
				}
			    }
			    c.println ("ILLEGAL MOVE!!!");
			    c.println ("Enter another position to move to: ");
			    move = c.readInt ();

			}

		    }


		    if (count == 2)
		    {
			board [move - 1] = "K";
		    }
		    else
		    {
			board [move - 1] = "A"; // Moves checker to selected section of the board
		    }
		    if (board [1].equals ("A"))
		    {
			board [1] = "K";
		    }
		    else if (board [3].equals ("A"))
		    {
			board [3] = "K";
		    }
		    c.clear ();
		    for (int i = 0 ; i < 16 ; i++)
		    {
			c.print (board [i] + "\t");
			if ((i + 1) % 4 == 0)
			{
			    c.println ();
			    c.println ();
			}
		    }
		}

		if (checkb == 0)
		{
		    break;
		}
		if (checka == 0)
		{
		    break;
		}

		choice++;

		if (choice % 2 != 0) // Determines whether it is the turn of player 2
		{
		    c.println ("Player B: select a checker(#);"); // Checker selection
		    int select = c.readInt ();
		    boolean select1 = true;
		    if (select < 1 || select > 16) // Checker reselection if previous selection was incorrect
		    {
			select1 = false;
		    }
		    else
		    {
			if (board [select - 1].equals ("B"))
			{
			    count = 1;
			}
			else if (board [select - 1].equals ("Q"))
			{
			    count = 2;
			}
			else
			{
			    select1 = false;
			}
		    }
		    if (select1 == false)
		    {
			while (true) // Checker reselection if previous selection was incorrect
			{
			    c.println ("Checker B does not exist at position " + select);
			    c.print ("Select another Checker B: ");
			    select = c.readInt ();
			    if (select >= 1 && select <= 16)
			    {
				if (board [select - 1].equals ("B")) // If correct, game proceeds
				{
				    count = 1;
				    break;
				}
				else if (board [select - 1].equals ("Q"))
				{
				    count = 2;
				    break;
				}

			    }
			}
		    }
		    board [select - 1] = Long.toString (select);

		    c.println ("Player B: enter the position you want to move it to:"); // Checker move to command entry
		    int move = c.readInt ();
		    boolean move1 = false;
		    // Board move limiter
		    if (count == 1)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && board [move - 1].indexOf ("B") == -1 && !board [move - 1].equals ("Q") && board [move - 1].indexOf ("A") == -1 && board [move - 1].indexOf ("K") == -1 && ((move - select) == 3 || (move - select) == 5))
			{
			    move1 = true;
			}
		    }
		    else if (count == 2)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && board [move - 1].indexOf ("B") == -1 && !board [move - 1].equals ("Q") && board [move - 1].indexOf ("A") == -1 && board [move - 1].indexOf ("K") == -1 && ((move - select) == 3 || (move - select) == 5 || (move - select) == -3 || (move - select) == -5))
			{
			    move1 = true;
			}
		    }
		    if (move1 == false)
		    {
			while (true) // Incorrect move reselection
			{
			    if (count == 1)
			    {

				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					c.println ("ILLEGAL MOVE");
					c.println ("Enter another position to move to: ");
					move = c.readInt ();
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				if (!board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move - 6)].equals ("A") || board [Math.abs (move - 6)].equals ("K"))
				    {
					board [move - 6] = Long.toString (move - 5);
					checka--;
					break;
				    }
				    else if (board [Math.abs (move - 4)].equals ("A") || board [Math.abs (move - 4)].equals ("K"))
				    {
					board [move - 4] = Long.toString (move - 3);
					checka--;
					break;
				    }
				}
			    }
			    else if (count == 2)
			    {

				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					c.println ("ILLEGAL MOVE!");
					c.println ("Enter another position to move to: ");
					move = c.readInt ();
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				if (!board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move - 6)].equals ("A") || board [Math.abs (move - 6)].equals ("K"))
				    {
					board [move - 6] = Long.toString (move - 5);
					checka--;
					break;
				    }
				    if (board [Math.abs (move - 4)].equals ("A") || board [Math.abs (move - 4)].equals ("K"))
				    {
					board [move - 4] = Long.toString (move - 3);
					checka--;
					break;
				    }
				    if (board [Math.abs (move + 2)].equals ("A") || board [Math.abs (move + 2)].equals ("K"))
				    {
					board [move + 2] = Long.toString (move + 3);
					checka--;
					break;
				    }
				    if (board [Math.abs (move + 4)].equals ("A") || board [Math.abs (move + 4)].equals ("K"))
				    {
					board [move + 4] = Long.toString (move + 5);
					checka--;
					break;
				    }
				}
			    }

			    if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("K"))
			    {
				if (((move - select) == 3) || (move - select) == 5)
				{
				    break;
				}

			    }

			    c.println ("ILLEGAL MOVE");
			    c.println ("Enter another position to move to: ");
			    move = c.readInt ();
			    if (count == 1)
			    {
				if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("A") && !board [move - 1].equals ("K") && ((move - select) == 3 || (move - select) == 5))
				{
				    break;
				}
			    }
			    else if (count == 2)
			    {
				if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && board [move - 1].indexOf ("B") == -1 && !board [move - 1].equals ("Q") && board [move - 1].indexOf ("A") == -1 && ((move - select) == 3 || (move - select) == 5 || (move - select) == -3 || (move - select) == -5))
				{
				    break;
				}
			    }

			}
		    }
		    if (count == 2)
		    {
			board [move - 1] = "Q";
		    }
		    else
		    {
			board [move - 1] = "B"; // Moves checker to selected section of the board
		    }
		    if (board [12].equals ("B"))
		    {
			board [12] = "Q";
		    }
		    else if (board [14].equals ("B"))
		    {
			board [14] = "Q";
		    }

		    c.clear ();
		    for (int i = 0 ; i < 16 ; i++) // Moves checker to selected section of the board
		    {
			c.print (board [i] + "\t");
			if ((i + 1) % 4 == 0)
			{
			    c.println ();
			    c.println ();
			}
		    }
		}

		if (board [12].equals ("B"))
		{
		    board [12] = "Q";
		}
		else if (board [14].equals ("B"))
		{
		    board [14] = "Q";
		}
		if (checkb == 0)
		{
		    break;
		}
		if (checka == 0)
		{
		    break;
		}
	    }
	}













	else if (option == 2) // Player vs Easy (random) computer
	{
	    while (checka > 0 && checkb > 0)
	    {
		if (choice % 2 == 0) // Determines whether it is the turn of player 1
		{
		    c.println ("Player A: select a checker(#):"); // Checker selection
		    int select = c.readInt ();
		    boolean select1 = true;
		    if (select < 1 || select > 16) // Checks if checker at location exists
		    {
			select1 = false;
		    }
		    else
		    {
			if (board [select - 1].equals ("A"))
			{
			    count = 1;
			}
			else if (board [select - 1].equals ("K"))
			{
			    count = 2;
			}
			else
			{
			    select1 = false;
			}
		    }
		    if (select1 == false)
		    {
			while (true) // Checker reselection if previous selection was incorrect
			{


			    c.println ("Checker A does not exist at position " + select);
			    c.print ("Select another Checker A: ");
			    select = c.readInt ();
			    if (select >= 1 && select <= 16)
			    {
				if (board [select - 1].equals ("A")) // If correct, game proceeds
				{
				    count = 1;
				    break;
				}
				else if (board [select - 1].equals ("K"))
				{
				    count = 2;
				    break;
				}
			    }
			}
		    }
		    board [select - 1] = Long.toString (select);


		    c.println ("Player A: enter the position you want to move it to:"); // Checker move to command entry
		    int move = c.readInt ();
		    boolean move1 = false;
		    // Board move limiter
		    if (count == 1)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("K") && !board [move - 1].equals ("Q") && ((select - move) == 3 || (select - move) == 5))
			{
			    move1 = true;
			}
		    }
		    if (count == 2)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("K") && ((Math.abs (move - select) == 3) || (Math.abs (move - select) == 5)))
			{
			    move1 = true;
			}
		    }

		    if (move1 == false)
		    {
			while (true) // Incorrect move reselection
			{
			    if (count == 1)
			    {
				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					c.println ("ILLEGAL MOVE!");
					c.println ("Enter another position to move to: ");
					move = c.readInt ();
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				// Eat for A/B
				if (((move + 4) <= 16) && !board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && !board [(move - 1)].equals ("Q") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move + 2)].equals ("B") || board [Math.abs (move + 2)].equals ("Q"))
				    {
					board [move + 2] = Long.toString (move + 3);
					checkb--;
					break;
				    }
				    if (board [Math.abs (move + 4)].equals ("B") || board [Math.abs (move + 4)].equals ("Q"))
				    {
					board [move + 4] = Long.toString (move + 5);
					checkb--;
					break;
				    }
				}
			    }
			    else
			    {
				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					c.println ("ILLEGAL MOVE!!");
					c.println ("Enter another position to move to: ");
					move = c.readInt ();
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				// K or Q killing
				if (!board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && !board [(move - 1)].equals ("Q") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move + 2)].equals ("B") || board [Math.abs (move + 2)].equals ("Q"))
				    {
					board [move + 2] = Long.toString (move + 3);
					checkb--;
					break;
				    }
				    if (board [Math.abs (move - 4)].equals ("B") || board [Math.abs (move - 4)].equals ("Q"))
				    {
					board [move - 4] = Long.toString (move - 3);
					checkb--;
					break;
				    }

				    if (board [Math.abs (move - 6)].equals ("B") || board [Math.abs (move - 6)].equals ("Q"))
				    {
					board [move - 6] = Long.toString (move - 5);
					checkb--;
					break;
				    }
				    if (board [Math.abs (move + 4)].equals ("B") || board [Math.abs (move + 4)].equals ("Q"))
				    {
					board [move + 4] = Long.toString (move + 5);
					checkb--;
					break;
				    }


				}
			    }
			    if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("K")) //&& ((select - move) == 3) || ((select - move) == 5))
			    {
				if ((select - move == 3) || (select - move == 5))
				{
				    break;
				}
			    }
			    c.println ("ILLEGAL MOVE!!!");
			    c.println ("Enter another position to move to: ");
			    move = c.readInt ();

			}

		    }


		    if (count == 2)
		    {
			board [move - 1] = "K";
		    }
		    else
		    {
			board [move - 1] = "A"; // Moves checker to selected section of the board
		    }
		    if (board [1].equals ("A"))
		    {
			board [1] = "K";
		    }
		    else if (board [3].equals ("A"))
		    {
			board [3] = "K";
		    }
		    c.clear ();
		    for (int i = 0 ; i < 16 ; i++)
		    {
			c.print (board [i] + "\t");
			if ((i + 1) % 4 == 0)
			{
			    c.println ();
			    c.println ();
			}
		    }
		}

		if (checkb == 0)
		{
		    break;
		}
		if (checka == 0)
		{
		    break;
		}

		choice++;

		if (choice % 2 != 0) // Determines whether it is the turn of player 2
		{
		    //c.println ("Player B: select a checker(#);"); // Checker selection
		    int select = (int)(Math.random()*16);
		    boolean select1 = true;
		    if (select < 1 || select > 16) // Checker reselection if previous selection was incorrect
		    {
			select1 = false;
		    }
		    else
		    {
			if (board [select - 1].equals ("B"))
			{
			    count = 1;
			}
			else if (board [select - 1].equals ("Q"))
			{
			    count = 2;
			}
			else
			{
			    select1 = false;
			}
		    }
		    if (select1 == false)
		    {
			while (true) // Checker reselection if previous selection was incorrect
			{
			    //c.println ("Checker B does not exist at position " + select);
			    //c.print ("Select another Checker B: ");
			    select = (int)(Math.random()*16);
			    if (select >= 1 && select <= 16)
			    {
				if (board [select - 1].equals ("B")) // If correct, game proceeds
				{
				    count = 1;
				    break;
				}
				else if (board [select - 1].equals ("Q"))
				{
				    count = 2;
				    break;
				}

			    }
			}
		    }
		    board [select - 1] = Long.toString (select);

		    //c.println ("Player B: enter the position you want to move it to:"); // Checker move to command entry
		    int move = (int)(Math.random()*16);
		    boolean move1 = false;
		    // Board move limiter
		    if (count == 1)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && board [move - 1].indexOf ("B") == -1 && !board [move - 1].equals ("Q") && board [move - 1].indexOf ("A") == -1 && board [move - 1].indexOf ("K") == -1 && ((move - select) == 3 || (move - select) == 5))
			{
			    move1 = true;
			}
		    }
		    else if (count == 2)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && board [move - 1].indexOf ("B") == -1 && !board [move - 1].equals ("Q") && board [move - 1].indexOf ("A") == -1 && board [move - 1].indexOf ("K") == -1 && ((move - select) == 3 || (move - select) == 5 || (move - select) == -3 || (move - select) == -5))
			{
			    move1 = true;
			}
		    }
		    if (move1 == false)
		    {
			while (true) // Incorrect move reselection
			{
			    if (count == 1)
			    {

				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					//c.println ("ILLEGAL MOVE");
					//c.println ("Enter another position to move to: ");
					move = (int)(Math.random()*16);;
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				if (!board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move - 6)].equals ("A") || board [Math.abs (move - 6)].equals ("K"))
				    {
					board [move - 6] = Long.toString (move - 5);
					checka--;
					break;
				    }
				    else if (board [Math.abs (move - 4)].equals ("A") || board [Math.abs (move - 4)].equals ("K"))
				    {
					board [move - 4] = Long.toString (move - 3);
					checka--;
					break;
				    }
				}
			    }
			    else if (count == 2)
			    {

				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					//c.println ("ILLEGAL MOVE!");
					//c.println ("Enter another position to move to: ");
					move = (int)(Math.random()*16);
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				if (!board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move - 6)].equals ("A") || board [Math.abs (move - 6)].equals ("K"))
				    {
					board [move - 6] = Long.toString (move - 5);
					checka--;
					break;
				    }
				    if (board [Math.abs (move - 4)].equals ("A") || board [Math.abs (move - 4)].equals ("K"))
				    {
					board [move - 4] = Long.toString (move - 3);
					checka--;
					break;
				    }
				    if (board [Math.abs (move + 2)].equals ("A") || board [Math.abs (move + 2)].equals ("K"))
				    {
					board [move + 2] = Long.toString (move + 3);
					checka--;
					break;
				    }
				    if (board [Math.abs (move + 4)].equals ("A") || board [Math.abs (move + 4)].equals ("K"))
				    {
					board [move + 4] = Long.toString (move + 5);
					checka--;
					break;
				    }
				}
			    }

			    if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("K"))
			    {
				if (((move - select) == 3) || (move - select) == 5)
				{
				    break;
				}

			    }

			    //c.println ("ILLEGAL MOVE");
			    //c.println ("Enter another position to move to: ");
			    move = (int)(Math.random()*16);
			    if (count == 1)
			    {
				if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("A") && !board [move - 1].equals ("K") && ((move - select) == 3 || (move - select) == 5))
				{
				    break;
				}
			    }
			    else if (count == 2)
			    {
				if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && board [move - 1].indexOf ("B") == -1 && !board [move - 1].equals ("Q") && board [move - 1].indexOf ("A") == -1 && ((move - select) == 3 || (move - select) == 5 || (move - select) == -3 || (move - select) == -5))
				{
				    break;
				}
			    }

			}
		    }
		    if (count == 2)
		    {
			board [move - 1] = "Q";
		    }
		    else
		    {
			board [move - 1] = "B"; // Moves checker to selected section of the board
		    }
		    if (board [12].equals ("B"))
		    {
			board [12] = "Q";
		    }
		    else if (board [14].equals ("B"))
		    {
			board [14] = "Q";
		    }

		    c.clear ();
		    for (int i = 0 ; i < 16 ; i++) // Moves checker to selected section of the board
		    {
			c.print (board [i] + "\t");
			if ((i + 1) % 4 == 0)
			{
			    c.println ();
			    c.println ();
			}
		    }
		}

		if (board [12].equals ("B"))
		{
		    board [12] = "Q";
		}
		else if (board [14].equals ("B"))
		{
		    board [14] = "Q";
		}
		if (checkb == 0)
		{
		    break;
		}
		if (checka == 0)
		{
		    break;
		}
	    }
	}






	//
	//SComp vs P
	//
	//
	//
	//
	//




	else // Player vs Hard computer
	{
	    while (checka > 0 && checkb > 0)
	    {
		if (choice % 2 == 0) // Determines whether it is the turn of player 1
		{
		    c.println ("Player A: select a checker(#):"); // Checker selection
		    int select = c.readInt ();
		    boolean select1 = true;
		    if (select < 1 || select > 16) // Checks if checker at location exists
		    {
			select1 = false;
		    }
		    else
		    {
			if (board [select - 1].equals ("A"))
			{
			    count = 1;
			}
			else if (board [select - 1].equals ("K"))
			{
			    count = 2;
			}
			else
			{
			    select1 = false;
			}
		    }
		    if (select1 == false)
		    {
			while (true) // Checker reselection if previous selection was incorrect
			{


			    c.println ("Checker A does not exist at position " + select);
			    c.print ("Select another Checker A: ");
			    select = c.readInt ();
			    if (select >= 1 && select <= 16)
			    {
				if (board [select - 1].equals ("A")) // If correct, game proceeds
				{
				    count = 1;
				    break;
				}
				else if (board [select - 1].equals ("K"))
				{
				    count = 2;
				    break;
				}
			    }
			}
		    }
		    board [select - 1] = Long.toString (select);


		    c.println ("Player A: enter the position you want to move it to:"); // Checker move to command entry
		    int move = c.readInt ();
		    boolean move1 = false;
		    // Board move limiter
		    if (count == 1)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("K") && !board [move - 1].equals ("Q") && ((select - move) == 3 || (select - move) == 5))
			{
			    c.println ("1");
			    move1 = true;
			}
		    }
		    if (count == 2)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("K") && ((Math.abs (move - select) == 3) || (Math.abs (move - select) == 5)))
			{
			    c.println ("2");
			    move1 = true;
			}
		    }

		    if (move1 == false)
		    {
			while (true) // Incorrect move reselection
			{

			    if (count == 1)
			    {
				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					c.println ("ILLEGAL MOVE!");
					c.println ("Enter another position to move to: ");
					move = c.readInt ();
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				if ((move + 2) <= 16 && !board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && !board [(move - 1)].equals ("Q"))
				{
				    if (board [Math.abs (move + 2)].equals ("B") || board [Math.abs (move + 2)].equals ("Q"))
				    {
					board [move + 2] = Long.toString (move + 3);
					checkb--;
					break;
				    }
				    if (board [Math.abs (move + 4)].equals ("B") || board [Math.abs (move + 4)].equals ("Q"))
				    {
					board [move + 4] = Long.toString (move + 5);
					checkb--;
					break;
				    }
				}
			    }
			    else
			    {
				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					c.println ("ILLEGAL MOVE!!");
					c.println ("Enter another position to move to: ");
					move = c.readInt ();
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				if (!board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && !board [(move - 1)].equals ("Q") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move + 2)].equals ("B") || board [Math.abs (move + 2)].equals ("Q"))
				    {
					board [move + 2] = Long.toString (move + 3);
					checkb--;
					break;
				    }
				    if (board [Math.abs (move - 4)].equals ("B") || board [Math.abs (move - 4)].equals ("Q"))
				    {
					board [move - 4] = Long.toString (move - 3);
					checkb--;
					break;
				    }

				    if (board [Math.abs (move - 6)].equals ("B") || board [Math.abs (move - 6)].equals ("Q"))
				    {
					board [move - 6] = Long.toString (move - 5);
					checkb--;
					break;
				    }
				    if (board [Math.abs (move + 4)].equals ("B") || board [Math.abs (move + 4)].equals ("Q"))
				    {
					board [move + 4] = Long.toString (move + 5);
					checkb--;
					break;
				    }


				}
			    }
			    if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("K")) //&& ((select - move) == 3) || ((select - move) == 5))
			    {
				break;
			    }
			    c.println ("ILLEGAL MOVE!!!");
			    c.println ("Enter another position to move to: ");
			    move = c.readInt ();

			}
		    }

		    if (count == 2)
		    {
			board [move - 1] = "K";
		    }
		    else
		    {
			board [move - 1] = "A"; // Moves checker to selected section of the board
		    }
		    if (board [1].equals ("A"))
		    {
			board [1] = "K";
		    }
		    else if (board [3].equals ("A"))
		    {
			board [3] = "K";
		    }
		    c.clear ();
		    for (int i = 0 ; i < 16 ; i++)
		    {
			c.print (board [i] + "\t");
			if ((i + 1) % 4 == 0)
			{
			    c.println ();
			    c.println ();
			}
		    }
		}

		if (checkb == 0)
		{
		    break;
		}
		if (checka == 0)
		{
		    break;
		}

		choice++;



















		if (choice % 2 != 0) // Determines whether it is the turn of player 2
		{
		    int botselect = 0, bot1 = 0, bot2 = 0, lim = 0, temp1 = 0, temp2 = 0, temp3 = 0, temp4 = 0, botmove = 0;
		    boolean botmoved = false, queen1 = false, queen2 = false;


		    // Piece location finder
		    for (int i = 0 ; i < 16 ; i++)
		    {
			if (board [i].equals ("B") || board [i].equals ("Q"))
			{
			    while (lim != 1)
			    {
				bot1 = i;
				if (board [i].equals ("Q"))
				    queen1 = true;
				temp1 = bot1;
				lim = 1;
			    }
			    if (lim == 1 && checkb == 1 && (board [i].equals ("Q") || board [i].equals ("B")))
			    {
				bot2 = i;
				if (board [i].equals ("Q"))
				    queen2 = true;
				temp2 = bot2;
				lim = 0;
				break;
			    }
			}
		    }

		    temp1 = bot1 + 6;
		    temp2 = bot1 + 10;

		    //possible move limiter tester
		    while (botmoved == false)
		    {
			if (queen1 == false && !board [(temp1 - 1)].equals ("/") && !board [(temp1 - 1)].equals ("A") && !board [(temp1 - 1)].equals ("B") && !board [(temp1 - 1)].equals ("K"))
			{
			    if (board [Math.abs (temp1 - 6)].equals ("A") || board [Math.abs (temp1 - 6)].equals ("K"))
			    {
				botmove = temp1;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			    else if (board [Math.abs (temp1 - 4)].equals ("A") || board [Math.abs (temp1 - 4)].equals ("K"))
			    {
				botmove = temp1;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			}

			if (queen1 = true && !board [(temp1 - 1)].equals ("/") && !board [(temp1 - 1)].equals ("A") && !board [(temp1 - 1)].equals ("B") && !board [(temp1 - 1)].equals ("K"))
			{
			    if (board [Math.abs (temp1 - 6)].equals ("A") || board [Math.abs (temp1 - 6)].equals ("K"))
			    {
				botmove = temp1;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			    if (board [Math.abs (temp1 - 4)].equals ("A") || board [Math.abs (temp1 - 4)].equals ("K"))
			    {
				botmove = temp1;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			    if (board [Math.abs (temp1 + 2)].equals ("A") || board [Math.abs (temp1 + 2)].equals ("K"))
			    {
				botmove = temp1;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			    if (board [Math.abs (temp1 + 4)].equals ("A") || board [Math.abs (temp1 + 4)].equals ("K"))
			    {
				botmove = temp1;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			}
			if (queen1 == false && !board [(temp2 - 1)].equals ("/") && !board [(temp2 - 1)].equals ("A") && !board [(temp2 - 1)].equals ("B") && !board [(temp2 - 1)].equals ("K"))
			{
			    if (board [Math.abs (temp2 - 6)].equals ("A") || board [Math.abs (temp2 - 6)].equals ("K"))
			    {
				botmove = temp2;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			    else if (board [Math.abs (temp2 - 4)].equals ("A") || board [Math.abs (temp2 - 4)].equals ("K"))
			    {
				botmove = temp2;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			}

			if (queen1 = true && !board [(temp2 - 1)].equals ("/") && !board [(temp2 - 1)].equals ("A") && !board [(temp2 - 1)].equals ("B") && !board [(temp2 - 1)].equals ("K"))
			{
			    if (board [Math.abs (temp2 - 6)].equals ("A") || board [Math.abs (temp2 - 6)].equals ("K"))
			    {
				botmove = temp2;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			    if (board [Math.abs (temp2 - 4)].equals ("A") || board [Math.abs (temp2 - 4)].equals ("K"))
			    {
				botmove = temp2;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			    if (board [Math.abs (temp2 + 2)].equals ("A") || board [Math.abs (temp2 + 2)].equals ("K"))
			    {
				botmove = temp2;
				botmoved = true;
				botselect = bot1;
				break;
			    }
			    if (temp2 + 4 <= 16)
			    {
				if (board [Math.abs (temp2 + 4)].equals ("A") || board [Math.abs (temp2 + 4)].equals ("K"))
				{
				    botmove = temp2;
				    botmoved = true;
				    botselect = bot1;
				    break;
				}
			    }
			}
			if (lim == 0)
			{
			    temp3 = bot2 + 6;
			    temp4 = bot2 + 10;

			    while (botmoved == false)
			    {
				if (queen2 == false && !board [(temp3 - 1)].equals ("/") && !board [(temp3 - 1)].equals ("A") && !board [(temp3 - 1)].equals ("B") && !board [(temp3 - 1)].equals ("K"))
				{
				    if (board [Math.abs (temp3 - 6)].equals ("A") || board [Math.abs (temp3 - 6)].equals ("K"))
				    {
					botmove = temp3;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				    else if (board [Math.abs (temp3 - 4)].equals ("A") || board [Math.abs (temp3 - 4)].equals ("K"))
				    {
					botmove = temp3;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				}

				if (queen2 = true && !board [(temp3 - 1)].equals ("/") && !board [(temp3 - 1)].equals ("A") && !board [(temp3 - 1)].equals ("B") && !board [(temp3 - 1)].equals ("K"))
				{
				    if (board [Math.abs (temp3 - 6)].equals ("A") || board [Math.abs (temp3 - 6)].equals ("K"))
				    {
					botmove = temp3;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				    if (board [Math.abs (temp3 - 4)].equals ("A") || board [Math.abs (temp3 - 4)].equals ("K"))
				    {
					botmove = temp3;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				    if (board [Math.abs (temp3 + 2)].equals ("A") || board [Math.abs (temp3 + 2)].equals ("K"))
				    {
					botmove = temp3;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				    if (board [Math.abs (temp3 + 4)].equals ("A") || board [Math.abs (temp3 + 4)].equals ("K"))
				    {
					botmove = temp3;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				}
				if (queen2 == false && !board [(temp4 - 1)].equals ("/") && !board [(temp4 - 1)].equals ("A") && !board [(temp4 - 1)].equals ("B") && !board [(temp4 - 1)].equals ("K"))
				{
				    if (board [Math.abs (temp4 - 6)].equals ("A") || board [Math.abs (temp4 - 6)].equals ("K"))
				    {
					botmove = temp4;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				    else if (board [Math.abs (temp4 - 4)].equals ("A") || board [Math.abs (temp4 - 4)].equals ("K"))
				    {
					botmove = temp4;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				}

				if (queen2 = true && !board [(temp4 - 1)].equals ("/") && !board [(temp4 - 1)].equals ("A") && !board [(temp4 - 1)].equals ("B") && !board [(temp4 - 1)].equals ("K"))
				{
				    if (board [Math.abs (temp4 - 6)].equals ("A") || board [Math.abs (temp4 - 6)].equals ("K"))
				    {
					botmove = temp4;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				    if (board [Math.abs (temp4 - 4)].equals ("A") || board [Math.abs (temp4 - 4)].equals ("K"))
				    {
					botmove = temp4;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				    if (board [Math.abs (temp4 + 2)].equals ("A") || board [Math.abs (temp4 + 2)].equals ("K"))
				    {
					botmove = temp4;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				    if (board [Math.abs (temp4 + 4)].equals ("A") || board [Math.abs (temp4 + 4)].equals ("K"))
				    {
					botmove = temp4;
					botmoved = true;
					botselect = bot2;
					break;
				    }
				}
			    }
			}
			break;
		    }


		    int select = 0;
		    if (botmoved == false)
		    {
			select = (int) (Math.random () * 16);
		    }
		    else
		    {
			select = botselect;
		    }

		    boolean select1 = true;
		    if (select < 1 || select > 16) // Checker reselection if previous selection was incorrect
		    {
			select1 = false;
		    }
		    else
		    {
			if (board [select - 1].equals ("B"))
			{
			    count = 1;
			}
			else if (board [select - 1].equals ("Q"))
			{
			    count = 2;
			}
			else
			{
			    select1 = false;
			}
		    }
		    if (select1 == false)
		    {
			while (true) // Checker reselection if previous selection was incorrect
			{
			    select = (int) (Math.random () * 16);
			    if (select >= 1 && select <= 16)
			    {
				if (board [select - 1].equals ("B")) // If correct, game proceeds
				{
				    count = 1;
				    break;
				}
				else if (board [select - 1].equals ("Q"))
				{
				    count = 2;
				    break;
				}

			    }
			}
		    }
		    board [select - 1] = Long.toString (select);

		    int move = 0;
		    if (botmoved == false)
		    {
			move = (int) (Math.random () * 16);
		    }
		    else
		    {
			move = botmove;
		    }


		    boolean move1 = false;
		    // Board move limiter
		    if (count == 1)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && board [move - 1].indexOf ("B") == -1 && !board [move - 1].equals ("Q") && board [move - 1].indexOf ("A") == -1 && board [move - 1].indexOf ("K") == -1 && ((move - select) == 3 || (move - select) == 5))
			{
			    move1 = true;
			}
		    }
		    else if (count == 2)
		    {
			if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && board [move - 1].indexOf ("B") == -1 && !board [move - 1].equals ("Q") && board [move - 1].indexOf ("A") == -1 && board [move - 1].indexOf ("K") == -1 && ((move - select) == 3 || (move - select) == 5 || (move - select) == -3 || (move - select) == -5))
			{
			    move1 = true;
			}
		    }
		    if (move1 == false)
		    {
			while (true) // Incorrect move reselection
			{
			    if (count == 1)
			    {

				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					move = (int) (Math.random () * 16);
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				if (!board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move - 6)].equals ("A") || board [Math.abs (move - 6)].equals ("K"))
				    {
					board [move - 6] = Long.toString (move - 5);
					checka--;
					break;
				    }
				    else if (board [Math.abs (move - 4)].equals ("A") || board [Math.abs (move - 4)].equals ("K"))
				    {
					board [move - 4] = Long.toString (move - 3);
					checka--;
					break;
				    }
				}
			    }
			    else if (count == 2)
			    {

				while (true)
				{
				    if (move < 1 || move > 16)
				    {
					move = (int) (Math.random () * 16);
				    }
				    if (move >= 1 && move <= 16)
				    {
					break;
				    }
				}
				if (!board [(move - 1)].equals ("/") && !board [(move - 1)].equals ("A") && !board [(move - 1)].equals ("B") && !board [(move - 1)].equals ("K") && ((Math.abs (move - select) == 10) || (Math.abs (move - select) == 6)))
				{
				    if (board [Math.abs (move - 6)].equals ("A") || board [Math.abs (move - 6)].equals ("K"))
				    {
					board [move - 6] = Long.toString (move - 5);
					checka--;
					break;
				    }
				    if (board [Math.abs (move - 4)].equals ("A") || board [Math.abs (move - 4)].equals ("K"))
				    {
					board [move - 4] = Long.toString (move - 3);
					checka--;
					break;
				    }
				    if (board [Math.abs (move + 2)].equals ("A") || board [Math.abs (move + 2)].equals ("K"))
				    {
					board [move + 2] = Long.toString (move + 3);
					checka--;
					break;
				    }
				    if (board [Math.abs (move + 4)].equals ("A") || board [Math.abs (move + 4)].equals ("K"))
				    {
					board [move + 4] = Long.toString (move + 5);
					checka--;
					break;
				    }
				}
			    }

			    if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("A") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("K"))
			    {
				if (((move - select) == 3) || (move - select) == 5)
				{
				    c.println (board [move - 1]);
				    break;
				}

			    }

			    move = (int) (Math.random () * 16);
			    if (count == 1)
			    {
				if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && !board [move - 1].equals ("B") && !board [move - 1].equals ("Q") && !board [move - 1].equals ("A") && !board [move - 1].equals ("K") && ((move - select) == 3 || (move - select) == 5))
				{
				    break;
				}
			    }
			    else if (count == 2)
			    {
				if (move >= 1 && move <= 16 && !board [move - 1].equals ("/") && board [move - 1].indexOf ("B") == -1 && !board [move - 1].equals ("Q") && board [move - 1].indexOf ("A") == -1 && ((move - select) == 3 || (move - select) == 5 || (move - select) == -3 || (move - select) == -5))
				{
				    break;
				}
			    }

			}
		    }
		    if (count == 2)
		    {
			board [move - 1] = "Q";
		    }
		    else
		    {
			board [move - 1] = "B"; // Moves checker to selected section of the board
		    }
		    if (board [12].equals ("B"))
		    {
			board [12] = "Q";
		    }
		    else if (board [14].equals ("B"))
		    {
			board [14] = "Q";
		    }

		    c.clear ();
		    for (int i = 0 ; i < board.length ; i++) // Moves checker to selected section of the board
		    {
			c.print (board [i] + "\t");
			if ((i + 1) % 4 == 0)
			{
			    c.println ();
			    c.println ();
			}
		    }
		}

		if (board [12].equals ("B"))
		{
		    board [12] = "Q";
		}
		else if (board [14].equals ("B"))
		{
		    board [14] = "Q";
		}

		if (checkb == 0)
		{
		    break;
		}
		if (checka == 0)
		{
		    break;
		}
	    }
	}



	if (checka == 0)
	{
	    c.println ("Check B wins");
	}


	else
	{
	    c.println ("Check A wins");
	}
    }
}


