package Dune2.Model;

import becker.io.TextInput;

public class Lands
{  private Land[] lands;
   private int numLands;

  public Lands()
  {  TextInput in = new TextInput(Constants.DEFAULT_FILES + "Lands.txt");
     this.numLands = in.readInt();
     this.lands = new Land[this.numLands];
     in.readLine();
     in.readLine();
     for (int i=0; i < this.numLands; i++)
     {  this.lands[i] = new Land(in);
     }
  }

  public Land getLand(String name)
  {  for (int i=0; i < this.numLands; i++)
     {  if (this.lands[i].getName().equals(name))
        {  return this.lands[i];
        }
     }

     return null;
  }

  public boolean isEnterable(String symbol)
  {  for(int i=0; i < this.numLands; i++)
     {  if (this.lands[i].getSymbol().equals(symbol))
        {  return this.lands[i].getIsEnterable();
        }
     }

     return false;
  }
}
