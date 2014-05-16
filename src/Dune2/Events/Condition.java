package Dune2.Events;
import Dune2.Model.*;

import becker.io.TextInput;

public class Condition
{  private String identifier;
   private String modifier;
   private int numArguments;
   private int numNumberArguments;
   private int numStringArguments;
   private String[] arguments;
   private double[] numberArguments;

   public Condition(TextInput in)
   {  this.identifier = in.readLine();
      this.modifier = in.readLine();
      this.numArguments = in.readInt();
      in.readLine();
      this.numStringArguments = in.readInt();
      in.readLine();
      this.numNumberArguments = in.readInt();
      in.readLine();
      this.arguments = new String[this.numStringArguments];
      this.numberArguments = new double[this.numNumberArguments];
      
      for (int i=0; i < this.numStringArguments; i++)
      {  this.arguments[i] = in.readLine();
      }

      for (int i=0; i < this.numNumberArguments; i++)
      {  this.numberArguments[i] = in.readDouble();
         in.readLine();
      }

      in.readLine();
   }

   public String getIdentifier()
   {  return this.identifier;
   }

   public String getModifier()
   {  return this.modifier;
   }

   public int getNumArguments()
   {  return this.numArguments;
   }

   public int getNumStringArguments()
   {  return this.numStringArguments;
   }

   public int getNumNumberArguments()
   {  return this.numNumberArguments;
   }

   public String[] getArguments()
   {  return this.arguments;
   }

   public double[] getNumberArguments()
   {  return this.numberArguments;
   }
} 