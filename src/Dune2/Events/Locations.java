package Dune2.Events;
import Dune2.Model.*;

import becker.io.TextInput;

public class Locations
{  private Location[] locations;
   private int numLocations;

   public Locations(TextInput in)
   {  this.numLocations = in.readInt();
      in.readLine();
      in.readLine();

      this.locations = new Location[this.numLocations];

      for (int i=0; i < this.numLocations; i++)
      {  this.locations[i] = new Location(in);  
      }
   }

   public Location getLocation(String name)
   {  for (int i=0; i < this.numLocations; i++)
      {  if (this.locations[i].getName().equals(name))
         {  return this.locations[i];
         }
      }

      return null;
   }
} 