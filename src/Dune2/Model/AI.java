package Dune2.Model;
import Dune2.Units.*;

import becker.io.TextInput;

public class AI extends Player
{  private AIQueueList queue;
   private Building selectedBuilding;
   private Unit selectedUnit;
   private AIUnitConstructionList ucList;
   private String difficulty;

   private boolean attackQueueSent;

   public AI(TextInput in, int tag, Units unitLists, Buildings buildingLists, Lands landList, String difficulty, int waveNumber, World theModel)
   {  super(in , tag, unitLists, buildingLists, landList, theModel);
      this.queue = new AIQueueList();
      this.ucList = new AIUnitConstructionList();
      this.difficulty = difficulty;
      this.waveNumber = waveNumber;
      //this.unitList = unitLists;
      //this.buildingList = buildingLists;
   }

   public void doRound()
   {  super.doRound();

      /* Economic AI: Do managing of the AI's resources (building units, buildings, getting new harvestors, etc */
      this.calculateAI();

      /* Manager AI: Tell buildings to build things, etc. */
      if (this.queue.getQueueLength() > 0)
      {  this.distributeJobs();
      }

      /* Strategic AI: Send units on attack runs, etc. */
      if (this.isReadyToAttack())
      {  this.sendAttackersOnAssault();
      }
   }

   private boolean isReadyToAttack()
   {  AIUnitConstruction list = this.ucList.getList(this.house, this.difficulty, this.waveNumber);
      int numNeed = list.getNumUnits();
      int numHave = 0;

      for (int i=0; i < this.numUnits; i++)
      {  if (this.units[i].getJob().equals(Constants.ATTACK) && this.units[i].getWaveNumber() == this.waveNumber)
         {  numHave++;
         }
      }
      //System.out.println("Checking if ready to attack: " + numHave + ":" + numNeed);

      return (numHave >= numNeed && this.attackQueueSent);
   }

   private void distributeJobs()
   {  Job job = this.queue.getMostImportantQueueJob();
      Building builtFromBuilding = job.getBuiltFrom();
      Unit needUnit = job.getUnitNeed();
      Building needBuilding = job.getBuildingNeed();
      if (this.hasBuilding(builtFromBuilding.getName()))
      {  this.setAISelectedBuilding(builtFromBuilding.getName(), 0);
         if (this.selectedBuilding != null && !this.selectedBuilding.getStatus().equals(Constants.MOVE))
         {  if (needUnit != null)
            {  if (this.numCredits >= needUnit.getCost())
               {  this.selectedBuilding.buildUnitOrBuilding(needUnit.getName(), job.getUnitJob(), job.getWaveNumber());
                  this.reduceCredits(needUnit.getCost());
                  this.queue.removeMostImportantQueueJob();
               }
            } else if (needBuilding != null)
            {  if (this.numCredits >= needBuilding.getCost())
               {  this.selectedBuilding.buildUnitOrBuilding(needBuilding.getName(), Constants.NONE, 0);
                  this.reduceCredits(needBuilding.getCost());
                  this.queue.removeMostImportantQueueJob();
               }
            }
         }
      }

      this.selectedBuilding = null;
      this.selectedUnit = null;
   }

   private void calculateAI()
   {  //MONEY MANAGER
      /*  Keep harvestor running smoothly; build harvestor if don't have one;
          build refinery if don't have one;
      */
      if (this.hasBuilding(Constants.REFINERY) && !this.hasUnit(Constants.HARVESTOR) && this.hasBuilding(Constants.CONSTRUCTION_YARD))
      {  if (this.hasBuilding(Constants.HEAVY_FACTORY) && this.numCredits >= 300)
         {  if (!this.queue.isOnQueue(Constants.HARVESTOR))
            {  //this.queue.addToNeedsQueue(Constants.HARVESTOR, 0);
               Job newJob = new Job(this.buildingList.getBuilding(Constants.HARVESTOR), this.unitList.getUnit(Constants.HARVESTOR), this.buildingList.getBuilding(this.unitList.getUnit(Constants.HARVESTOR).getBuiltFrom()), 0, Constants.NONE, 0);
               this.queue.addToNeedsQueue(newJob);
            }
         }
      }

      if (this.getNumUnit(Constants.COMBAT_TANK) < 4 && this.hasBuilding(Constants.HEAVY_FACTORY) && this.hasBuilding(Constants.CONSTRUCTION_YARD))
      {  if (this.numCredits >= 400)
         {  if (!this.attackQueueSent)
            {  this.attackQueueSent = true;
               AIUnitConstruction list = this.ucList.getList(this.house, this.difficulty, this.waveNumber);

               for (int i=0; i < list.getNumUnits(); i++)
               {  Job newJob = new Job(this.buildingList.getBuilding(list.getUnit(i)), this.unitList.getUnit(list.getUnit(i)), this.buildingList.getBuilding(this.unitList.getUnit(list.getUnit(i)).getBuiltFrom()), 1, list.getJobType(), list.getWaveNumber());
                  this.queue.addToNeedsQueue(newJob);
               }
            }
         }
      }
   }

   private void setAISelectedUnit(String name, int index)
   {  int num = 0;
      for (int i=0; i < this.numUnits; i++)
      {  if (this.units[i].getName().equals(name))
         {  if (num == index)
            {  this.selectedUnit = this.units[i];
            } else
            {  num++;
            }
         }
      }
   }

   private void setAISelectedBuilding(String name, int index)
   {  int num = 0;
      for (int i=0; i < this.numBuildings; i++)
      {  if (this.buildings[i].getName().equals(name))
         {  if (num == index)
            {  this.selectedBuilding = this.buildings[i];
            } else
            {  num++;
            }
         }
      }
   }

   private void sendAttackersOnAssault()
   {  Building building = null;
      double random = Math.random();

      if (random < 0.50)
      {  if (this.tag == 1)
         {  if (this.model.getNumAI() > 1)
            {  building = this.model.getFirstBuilding(2);
            } else
            {  building = this.model.getFirstBuilding(0);
            }
         } else if (this.tag == 2)
         {  if (this.model.getNumAI() > 1)
            {  building = this.model.getFirstBuilding(1);
            } else
            {  building = this.model.getFirstBuilding(0);
            }
         }
      } else
      {  building = this.model.getFirstBuilding(0);
      }

      if (building != null)
      {  for (int i=0; i < this.numUnits; i ++)
         {  if (this.units[i].getJob().equals(Constants.ATTACK))
            {  this.units[i].setIsNotHarvesting();
               this.units[i].resetAttack();
               this.units[i].setIsMovingAndAttacking();
               this.units[i].setBeHereCoordinates(building.getX(), building.getY());
            }
         }
      }

      this.attackQueueSent = false;
      this.waveNumber++;
   }

   protected void removeUnit(Unit unit)
   {  String job = unit.getJob();
      super.removeUnit(unit);
      if (job.equals(Constants.GUARD))
      {  Job newJob = new Job(this.buildingList.getBuilding(unit.getName()), this.unitList.getUnit(unit.getName()), this.buildingList.getBuilding(unit.getBuiltFrom()), 2, job, 0);
         this.queue.addToNeedsQueue(newJob);
      }
   }
}
