package Dune2.Model;

import becker.io.TextInput;

public class Projectile
{  private String name;
   private String hitName;
   private double speed;
   private double distance;
   private double progress;
   private int startX;
   private int startY;
   private int targetX;
   private int targetY;
   private double degree;
   private int damage;

   public Projectile(TextInput in)
   {  this.name = in.readLine();
      this.hitName = in.readLine();
      this.speed = in.readInt();
      in.readLine();
      in.readLine();

   }

   public Projectile(Projectile proj, int damage, double distance, double travelled, int startX, int startY, double degree, int targetX, int targetY)
   {  this.name = proj.getName();
      this.hitName = proj.getHitName();
      this.speed = proj.getSpeed();
      this.damage = damage;
      this.distance = distance;
      this.progress = travelled;
      this.startX = startX;
      this.startY = startY;
      this.targetX = targetX;
      this.targetY = targetY;
      this.degree = degree;
   }

   public void doRound()
   {  this.progress = this.progress + this.speed;
      //System.out.println("do round for : " + this.name);
   }

   public boolean isAtTarget()
   {  return (this.progress >= this.distance);
   }

   public double getDegree()
   {  return this.degree;
   }

   public int getTargetY()
   {  return this.targetY;
   }

   public int getTargetX()
   {  return this.targetX;
   }

   public int getStartY()
   {  return this.startY;
   }

   public int getStartX()
   {  return this.startX;
   }

   public double getProgress()
   {  return this.progress;
   }

   public String getName()
   {  return this.name;
   }

   public String getHitName()
   {  return this.hitName;
   }

   public double getSpeed()
   {  return this.speed;
   }

   public int getDamage()
   {  return this.damage;
   }

   public double getDistance()
   {  return this.distance;
   }
} 