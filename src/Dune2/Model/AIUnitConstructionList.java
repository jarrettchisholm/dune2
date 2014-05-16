package Dune2.Model;

import becker.io.TextInput;

public class AIUnitConstructionList
{  private AIUnitConstruction[] list;
   private int numUnitList;

   public AIUnitConstructionList()
   {  TextInput in = new TextInput(Constants.DEFAULT_FILES + "AI.txt");
      this.numUnitList = in.readInt();
      in.readLine();
      in.readLine();
      this.list = new AIUnitConstruction[this.numUnitList];

      for (int i=0; i < this.numUnitList; i++)
      {  this.list[i] = new AIUnitConstruction(in);
      }
   }

   public AIUnitConstruction getList(String house, String difficulty, int wave)
   {  for (int i=0; i < this.numUnitList; i++)
      {  if (this.list[i].getHouse().equals(house) && this.list[i].getDifficulty().equals(difficulty) && this.list[i].getWaveNumber() == wave)
         {  return this.list[i];
         }
      }

      return null;
   }
} 