package Dune2.Model;

import becker.io.TextInput;

public class AIUnitConstruction
{  private String house;
   private int waveNumber;
   private String difficulty;
   private String attackType;
   private String[] units;
   private int numUnits;
   private String jobType;

   public AIUnitConstruction(TextInput in)
   {  this.house = in.readLine();
      this.waveNumber = in.readInt();
      in.readLine();
      this.difficulty = in.readLine();
      this.attackType = in.readLine();
      this.numUnits = in.readInt();
      in.readLine();
      this.jobType = in.readLine();
      this.units = new String[this.numUnits];
      for (int i=0; i < this.numUnits; i++)
      {  this.units[i] = in.readLine();
      }

      in.readLine();
   }

   public String getDifficulty()
   {  return this.difficulty;
   }

   public String getHouse()
   {   return this.house;
   }

   public int getWaveNumber()
   {  return this.waveNumber;
   }

   public int getNumUnits()
   {  return this.numUnits;
   }

   public String getUnit(int index)
   {  if (index >= this.numUnits)
      {  return null;
      } else
      {  return this.units[index];
      }
   }

   public String getAttackType()
   {  return this.attackType;
   }

   public String getJobType()
   {  return this.jobType;
   }
} 