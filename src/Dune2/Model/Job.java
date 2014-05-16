package Dune2.Model;
import Dune2.Units.*;

public class Job
{  private Unit unit;
   private Building building;
   private Building builtFrom;
   private int importance;
   private String unitJob;
   private int waveNumber;


   public Job(Building building, Unit unit, Building builtFrom, int importance, String unitJob, int waveNumber)
   {  this.building = building;
      this.unit = unit;
      this.builtFrom = builtFrom;
      this.importance = importance;
      this.unitJob = unitJob;
      this.waveNumber = waveNumber;
   }

   public Unit getUnitNeed()
   {  return this.unit;
   }

   public Building getBuildingNeed()
   {  return this.building;
   }

   public Building getBuiltFrom()
   {  return this.builtFrom;
   }

   public int getImportance()
   {  return this.importance;
   }

   public String getUnitJob()
   {  return this.unitJob;
   }

   public int getWaveNumber()
   {  return this.waveNumber;
   }
}
