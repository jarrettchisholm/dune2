package Dune2.Units;
import Dune2.Model.*;

import becker.io.TextInput;

public class Units
{  private Unit[] units;
   private int numUnits;

   public Units()
   {  TextInput in = new TextInput(Constants.DEFAULT_FILES + "Units.txt");
      this.numUnits = in.readInt();
      this.units = new Unit[this.numUnits];
      in.readLine();
      in.readLine();
      for (int i=0; i < this.numUnits; i++)
      {  this.units[i] = new Unit(in);
      }
   }

   public Unit getUnit(String name)
   {  for (int i=0; i < this.numUnits; i++)
      {  if (this.units[i].getName().equals(name))
         {  return this.units[i];
         }
      }

      return null;
   }
}
