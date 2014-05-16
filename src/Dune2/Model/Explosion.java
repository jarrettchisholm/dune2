package Dune2.Model;

import becker.io.TextInput;

public class Explosion
{  private String name;
   private int x;
   private int y;
   private double progress;
   private int numberOfImages;

   public Explosion(TextInput in)
   {  this.name = in.readLine();
      this.numberOfImages = in.readInt();
      in.readLine();
      in.readLine();

   }

   public Explosion(Explosion exp, int x, int y, double progress)
   {  this.name = exp.getName();
      this.numberOfImages = exp.getNumberOfImages();
      this.x = x;
      this.y = y;
      this.progress = progress;
   }

   public void doRound()
   {  this.progress++;
   }

   public boolean isExplosionDone()
   {  return (this.progress > this.numberOfImages);
   }

   public int getNumberOfImages()
   {  return this.numberOfImages;
   }

   public double getProgress()
   {  return this.progress;
   }

   public int getX()
   {  return this.x;
   }

   public int getY()
   {  return this.y;
   }

   public String getName()
   {  return this.name;
   }
} 