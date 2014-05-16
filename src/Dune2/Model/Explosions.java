package Dune2.Model;

import becker.io.TextInput;

public class Explosions
{  private Explosion[] explosions;
   private int numExplosions;

   public Explosions()
   {  TextInput in = new TextInput(Constants.DEFAULT_FILES + "Explosions.txt");
      this.numExplosions = in.readInt();
      this.explosions = new Explosion[this.numExplosions];
      in.readLine();
      in.readLine();
      for (int i=0; i < this.numExplosions; i++)
      {  this.explosions[i] = new Explosion(in);
      }
   }

   public Explosion getExplosion(String name)
   {  for (int i=0; i < this.numExplosions; i++)
      {  if (this.explosions[i].getName().equals(name))
         {  return this.explosions[i];
         }
      }
      return null;
   }
} 