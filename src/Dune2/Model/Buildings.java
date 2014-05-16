package Dune2.Model;

import becker.io.TextInput;

public class Buildings
{  private Building[] buildings;
   private int numBuildings;

   public Buildings()
   {  TextInput in = new TextInput(Constants.DEFAULT_FILES + "Buildings.txt");
      this.numBuildings = in.readInt();
      this.buildings = new Building[this.numBuildings];
      in.readLine();
      in.readLine();
      for (int i=0; i < this.numBuildings; i++)
      {  this.buildings[i] = new Building(in);
      }
      
   }

   public Building getBuilding(String name)
   {  for (int i=0; i < this.numBuildings; i++)
      {  if (this.buildings[i].getName().equals(name))
         {  return this.buildings[i];
         }
      }
      
      return null;
   }
} 