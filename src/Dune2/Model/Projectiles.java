package Dune2.Model;

import becker.io.TextInput;

public class Projectiles
{  private Projectile[] projectiles;
   private int numProjectiles;

   public Projectiles()
   {  TextInput in = new TextInput(Constants.DEFAULT_FILES + "Projectiles.txt");
      this.numProjectiles = in.readInt();
      this.projectiles = new Projectile[this.numProjectiles];
      in.readLine();
      in.readLine();
      for (int i=0; i < this.numProjectiles; i++)
      {  this.projectiles[i] = new Projectile(in);
      }
   }

   public Projectile getProjectile(String name)
   {  for (int i=0; i < this.numProjectiles; i++)
      {  if (this.projectiles[i].getName().equals(name))
         {  return this.projectiles[i];
         }
      }
      return null;
   }
}
