package Dune2.Model;

import becker.io.TextInput;

public class Land
{  private String name;
   private boolean enterable;
   private int amountOfSpice;
   private double speedModifier;
   private String symbol;

   public Land(TextInput in)
   {  this.name = in.readLine();
      this.symbol = in.readLine();
      this.enterable = in.readBoolean();
      in.readLine();
      this.amountOfSpice = in.readInt();
      in.readLine();
      this.speedModifier = in.readDouble();
      in.readLine();
      in.readLine();
   }

   public Land(Land land, int amountOfSpice)
   {  this.name = land.getName();
      this.symbol = land.getSymbol();
      this.enterable = land.getIsEnterable();
      this.speedModifier = land.getSpeedModifier();
      this.amountOfSpice = amountOfSpice;
   }

   public String getName()
   {  return this.name;
   }

   public boolean getIsEnterable()
   {  return this.enterable;
   }

   public double getSpeedModifier()
   {  return this.speedModifier;
   }

   public int getAmountOfSpice()
   {  return this.amountOfSpice;
   }

   public String getSymbol()
   {  return this.symbol;
   }
}

