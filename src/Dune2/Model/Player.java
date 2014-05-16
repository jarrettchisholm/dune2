package Dune2.Model;
import Dune2.Units.*;

import becker.io.TextInput;

public class Player
{  protected String house;
   protected int numBuildings;
   protected int numUnits;
   protected int numCredits;
   protected Unit[] units;
   protected Building[] buildings;
   protected int techLevel; //Haven't done this yet...
   protected int waveNumber = 0;
   protected int tag;

   protected Units unitList;
   protected Buildings buildingList;
   protected Lands landList;

   protected World model;

   public Player(TextInput in, int tag, Units unitLists, Buildings buildingLists, Lands landList, World theModel)
   {  this.model = theModel;
      this.tag = tag;
      this.unitList = unitLists;
      this.buildingList = buildingLists;
      this.landList = landList;

      this.house = in.readLine();
      this.numBuildings = in.readInt();
      in.readLine();
      this.numUnits = in.readInt();
      in.readLine();
      this.numCredits = in.readInt();
      in.readLine();
      in.readLine();

      this.units = new Unit[this.numUnits];
      this.buildings = new Building[this.numBuildings];

      for (int i=0; i < this.numUnits; i++)
      {  String name = in.readLine();
         Unit unitToAdd = this.unitList.getUnit(name);
         int hp = in.readInt();
         in.readLine();
         int previousHP = in.readInt();
         in.readLine();
         int degree1 = in.readInt();
         in.readLine();
         int degree2 = in.readInt();
         in.readLine();
         int currentX = in.readInt();
         in.readLine();
         int currentY = in.readInt();
         in.readLine();
         int gotoX = in.readInt();
         in.readLine();
         int gotoY = in.readInt();
         in.readLine();
         int beHereX = in.readInt();
         in.readLine();
         int beHereY = in.readInt();
         in.readLine();
         int attackX = in.readInt();
         in.readLine();
         int attackY = in.readInt();
         in.readLine();
         boolean attacking = in.readBoolean();
         in.readLine();
         boolean moveAndAttack = in.readBoolean();
         in.readLine();
         int waveNumber = in.readInt();
         in.readLine();
         int progress = in.readInt();
         in.readLine();
         double LFT = in.readDouble();
         in.readLine();
         boolean docked = in.readBoolean();
         in.readLine();
         String status = in.readLine();
         String job = in.readLine();
         int holdingCredits = 0;
         boolean harvesting = false;
         if (name.equals(Constants.HARVESTOR))
         {  holdingCredits = in.readInt();
            in.readLine();
            harvesting = in.readBoolean();
            in.readLine();
         }
         in.readLine();

         if (unitToAdd.getCanMove())
         {  this.units[i] = new Unit(unitToAdd, hp, previousHP, degree1, degree2, currentX, currentY, gotoX, gotoY, progress, LFT, status, beHereX, beHereY, attackX, attackY, attacking, moveAndAttack, holdingCredits, this.tag, docked, harvesting, job, waveNumber, this.model);
         } else
         {  this.units[i] = new DefenseUnit(unitToAdd, hp, previousHP, degree1, degree2, currentX, currentY, gotoX, gotoY, progress, LFT, status, beHereX, beHereY, attackX, attackY, attacking, moveAndAttack, holdingCredits, this.tag, docked, harvesting, job, waveNumber, this.model);
         }
      }

      for (int i=0; i < this.numBuildings; i++)
      {  String name = in.readLine();
         Building buildingToAdd = this.buildingList.getBuilding(name);

         int hp = in.readInt();
         in.readLine();
         int previousHP = in.readInt();
         in.readLine();
         String unitJob = in.readLine();
         String currentConstruction = in.readLine();
         String previousC = in.readLine();
         int waveNumber = in.readInt();
         in.readLine();
         int progress = in.readInt();
         in.readLine();
         int x = in.readInt();
         in.readLine();
         int y = in.readInt();
         in.readLine();
         String status = in.readLine();
         double buildingL = in.readDouble();
         in.readLine();
         boolean docked = false;
         if (name.equals(Constants.REFINERY))
         {  docked = in.readBoolean();
            in.readLine();
         }
         in.readLine();
         int ownerTag = tag;

         this.buildings[i] = new Building(buildingToAdd, hp, currentConstruction, previousC, progress, x, y, status, buildingL, docked, unitJob, waveNumber, previousHP, ownerTag, this.model);
      }
   }

   public void doRound()
   {  for (int i=0; i < this.numUnits; i++)
      {  //Do Unit Round
         this.units[i].doRound();

         //resolve attacking
         if (this.units[i].getIsAttacking())
         {  this.units[i].doAttacking();
         }
      }

      for (int i=0; i < this.numBuildings; i++)
      {  //resolve building level
         this.buildings[i].doRound();
         //this.buildings[i].addToBuildingLevel(1000/Constants.GAME_SPEED);
      }

      this.checkIfHarvestorIsEnteringRefinery();
      this.resolveBuildingHarvestorHolding();
      this.resolveBuildingConstruction();
      this.resolveUnitConstruction();
   }

   public void unitsAqcuireTargetsAfterFileLoad()
   {  for (int i=0; i < this.numUnits; i++)
      {  this.units[i].acquireTargetAfterFileLoad();
      }
   }

   public void unitsFindPathsAfterFileLoad()
   {  for (int i=0; i < this.numUnits; i++)
      {  if (this.units[i].isMovingAndAttacking())
         {  this.units[i].setBeHereCoordinates(this.units[i].getBeHereX(), this.units[i].getBeHereY());  //was attackX and attackY
         } else if (this.units[i].getIsAttacking())
         {  Unit targetUnit = this.units[i].getUnitTarget();
            if (targetUnit != null)
            {  this.units[i].setAttackAt(this.units[i].getAttackX(), this.units[i].getAttackY(), targetUnit);
            }  else
            {  Building targetBuilding = this.units[i].getBuildingTarget();
               if (targetBuilding != null)
               {  this.units[i].setAttackAt(this.units[i].getAttackX(), this.units[i].getAttackY(), targetBuilding);
               }
            }
         } else
         {  this.units[i].setBeHereCoordinates(this.units[i].getBeHereX(), this.units[i].getBeHereY());
         }

      }
   }

   private void resolveBuildingHarvestorHolding()
   {  for (int i=0; i < this.numBuildings; i++)
      {  if (this.buildings[i].getHarvestorDocked() && this.buildings[i].getName().equals(Constants.REFINERY))
         {  for (int j=0; j < this.numUnits; j++)
            {  if (this.units[j].getName().equals(Constants.HARVESTOR) && this.buildings[i].getX() == this.units[j].getCurrentX() && this.buildings[i].getY() == this.units[j].getCurrentY())
               {  if (this.units[j].getHoldingCredits() == 0.00)
                  {  if (this.buildings[i].getHarvestorHoldingTime() >= Constants.HARVESTOR_HOLDING_TIME)
                     {  this.buildings[i].resetHarvestorHoldingTime();

                     } else
                     {  this.buildings[i].increaseHarvestorHoldingTime();
                     }
                  } else if (this.units[j].getHoldingCredits() != 0.00)
                  {  this.units[j].changeHoldingCredits(-Constants.CREDIT_EXCHANGE_RATE);
                     this.numCredits = this.numCredits + Constants.CREDIT_EXCHANGE_RATE;
                     this.buildings[i].changeCreditsHeld(Constants.CREDIT_EXCHANGE_RATE);
                  }
               }
            }
         }
      }
   }

   private void checkIfHarvestorIsEnteringRefinery()
   {  for (int i=0; i < this.numBuildings; i++)
      {  for (int j=0; j < this.numUnits; j++)
         {  if (this.units[j].getName().equals(Constants.HARVESTOR) && !this.buildings[i].getHarvestorDocked() && this.buildings[i].getName().equals(Constants.REFINERY))
            {  if (this.units[j].getCurrentX() == this.buildings[i].getX() && this.units[j].getCurrentY() == this.buildings[i].getY())
               {  this.units[j].setCurrentXY(this.buildings[i].getX(), this.buildings[i].getY());
                  this.buildings[i].setHarvestorDocked();
                  this.units[j].setDocked();
               }
            }
         }
      }
   }

   private void resolveBuildingConstruction()
   {  for (int i=0; i < this.numBuildings; i++)
      {  if (this.buildings[i].getStatus().equals(Constants.MOVE) && this.buildings[i].getProgress() < 100)
         {  Building building = this.buildingList.getBuilding(this.buildings[i].getCurrentConstruction());
            if (building != null) //RESOLVE IF BUILDING UNDER CONSTRUCTION
            {  double timeToAdd = (double)Constants.GAME_SPEED / 100.0;
               double time = this.buildings[i].getProgress() / 100 * building.getBuildTime();
               double addProgress = ((time + timeToAdd) / building.getBuildTime() * 100);// - this.buildings[i].getProgress();
               this.buildings[i].addToProgress(addProgress);
            }
         } else if (this.buildings[i].getProgress() > 100)
         {  this.buildings[i].addToProgress(100 - this.buildings[i].getProgress());
         }
      }
   }

   public void resolveUnitConstruction()
   {  for (int i=0; i < this.numBuildings; i++)
      {  if (this.buildings[i].getStatus().equals(Constants.MOVE) && this.buildings[i].getProgress() < 100)
         {  Unit unit = this.unitList.getUnit(this.buildings[i].getCurrentConstruction());
            if (unit != null) //RESOLVE IF UNIT UNDER CONSTRUCTION
            {  double timeToAdd = (double)Constants.GAME_SPEED / 100.0;
               double time = this.buildings[i].getProgress() / 100 * unit.getBuildTime();
               double addProgress = ((time + timeToAdd) / unit.getBuildTime() * 100);// - this.buildings[i].getProgress();
               this.buildings[i].addToProgress(addProgress);
            }
         } else if (this.buildings[i].getProgress() > 100)
         {  this.buildings[i].addToProgress(100 - this.buildings[i].getProgress());
         }
      }
   }

   public void addBuilding(int x, int y, Building building)
   {
      Building[] temp = new Building[this.numBuildings + 1];
      for (int i=0; i < this.numBuildings; i++)
      {  temp[i] = this.buildings[i];
      }

      int hp = building.getTotalHP();
      String construction = Constants.NONE;
      String previousC = Constants.NONE;
      int progress = -1;
      String status = Constants.STOP;
      double buildingLevel = 0.00;
      boolean docked = false;
      String unitJob = Constants.NONE;
      int waveNumber = 0;
      int ownerTag = this.tag;
      int previousHP = hp;
      temp[this.numBuildings] = new Building(building, hp, construction, previousC, progress, x, y, status, buildingLevel, docked, unitJob, waveNumber, previousHP, ownerTag, this.model);
      this.numBuildings++;

      this.buildings = temp;

      if (!building.getName().equals(Constants.SINGLE_SLAB) && !building.getName().equals(Constants.SLAB_4))
      {  this.checkForSlab(x, y, building);
      }

      if (building.getName().equals(Constants.REFINERY))
      {  this.addUnit(x, y, Constants.NONE, 0, this.unitList.getUnit(Constants.HARVESTOR));
      }
   }

   public void addUnit(int x, int y, String unitJob, int waveNumber, Unit unit)
   {
      Unit[] temp = new Unit[this.numUnits + 1];
      for (int i=0; i < this.numUnits; i++)
      {  temp[i] = this.units[i];
      }
      int hp = unit.getTotalHP();
      boolean canMove = unit.getCanMove();
      int deg1 = 0;
      int deg2 = -1;
      if (unit.hasTurret())
      { deg2 = 0;
      }
      int x1 = x;
      int y1 = y;
      int x2 = -1;
      int y2 = -1;
      int progress = -1;
      String status = Constants.STOP;
      double lastFireTime = 0;
      int beHereX = -1;
      int beHereY = -1;
      int attackX = -1;
      int attackY = -1;
      boolean attacking = false;
      boolean moveAndAttack = false;
      int holdingCredits = 0;
      boolean docked = false;
      boolean harvesting = false;
      if(unit.getName().equals(Constants.HARVESTOR))
      {  docked = true;
      }
      String job = unitJob;
      int waveN = waveNumber;

      if (canMove)
      {  temp[this.numUnits] = new Unit(unit, hp, hp, deg1, deg2, x1, y1, x2, y2, progress, lastFireTime, status, beHereX, beHereY, attackX, attackY, attacking, moveAndAttack, holdingCredits, this.tag, docked, harvesting, job, waveN, this.model);
      } else
      {  temp[this.numUnits] = new DefenseUnit(unit, hp, hp, deg1, deg2, x1, y1, x2, y2, progress, lastFireTime, status, beHereX, beHereY, attackX, attackY, attacking, moveAndAttack, holdingCredits, this.tag, docked, harvesting, job, waveN, this.model);
      }
      this.numUnits++;

      this.units = temp;
   }

   private void checkForSlab(int x, int y, Building building)
   {  boolean flag;
      Building[] temp = new Building[this.numBuildings];
      for (int i=0; i < this.numBuildings; i++)
      {  flag = true;
         for (int j=0; j < building.getLengthX(); j++)
         {  for (int k=0; k < building.getWidthY(); k++)
            {  if (this.buildings[i].getX() == (x + j) && this.buildings[i].getY() == (y + k) && (this.buildings[i].getName().equals(Constants.SINGLE_SLAB) || this.buildings[i].getName().equals(Constants.SLAB_4)))
               {  flag = false;
               }
            }
         }
         if (!flag)
         {  temp[i] = this.buildings[i];
         }
      }

      this.removeBuildings(temp);
   }

   public void unitTakeDamage(Unit unit, int amount)
   {  unit.takeDamage(amount);
      if (unit.isUnitDead())
      {  unit.doDeathExplosion();
         this.removeUnit(unit);
      }
   }

   public void buildingTakeDamage(Building building, int amount)
   {  building.takeDamage(amount);
      if (building.isBuildingDead())
      {  building.doDeathExplosion();
         this.removeBuilding(building);
      }
   }

   private void removeBuilding(Building building)
   {  boolean found = false;
      for (int i=0; i < this.numBuildings-1; i++)
      {  if (this.buildings[i] == building)
         {  found = true;
         }

         if (found)
         {  this.buildings[i] = this.buildings[i+1];
         }
      }

      this.numBuildings--;
      this.buildings[this.numBuildings] = null;
   }

   protected void removeUnit(Unit unit)
   {  boolean found = false;
      for (int i=0; i < this.numUnits-1; i++)
      {  if (this.units[i] == unit)
         {  found = true;
         }

         if (found)
         {  this.units[i] = this.units[i+1];
         }
      }

      this.numUnits--;
      this.units[this.numUnits] = null;
   }

   private void removeBuildings(Building[] buildings)
   {  int numDeleted = 0;
      for (int i=0; i < this.numBuildings; i++)
      {  if (this.buildings[i] == buildings[i])
         {  this.buildings[i] = null;
            numDeleted++;
         }
      }

      Building[] temp = new Building[this.numBuildings - numDeleted];
      int tempIndex = 0;
      for (int i=0; i < this.numBuildings; i++)
      {  if (this.buildings[i] != null)
         {  temp[tempIndex] = this.buildings[i];
            tempIndex++;
         }
      }
      this.numBuildings = this.numBuildings - numDeleted;
      this.buildings = new Building[this.numBuildings];
      this.buildings = temp;
   }

   /* Returns all of the units within the given coordinates --> IMPORTANT:  Excludes
      harvestors!  They are a special case, dealt with by the model (World).
   */
   public Unit[] getUnitsFrom(int x1, int y1, int x2, int y2)
   {
      int loopX = x2 - x1 + 1;
      int loopY = y2 - y1 + 1;
      Unit[] units = null;
      int numUnits = 0;

      if (x1 == x2 && y1 == y2)
      {  if (this.getUnitAt(x1, y1) != null)
         {  units = new Unit[1];
            units[0] = this.getUnitAt(x1, y1);
            return units;
         } else
         {  return null;
         }
      }

      for (int i=0; i < loopX; i++)
      {  for (int j=0; j < loopY; j++)
         {  Unit add = this.getUnitAt(x1 + i, y1 + j);
            if (add != null && !add.getName().equals(Constants.HARVESTOR))
            {  Unit[] temp = new Unit[numUnits + 1];
               for (int k=0; k < numUnits; k++)
               {  temp[k] = units[k];
               }

               units = temp;
               units[numUnits] = add;
               numUnits++;
            }
         }
      }

      return units;
   }

   public Unit getUnitAt(int x, int y)
   {  for (int i=0; i < this.numUnits; i++)
      {  if (this.units[i].getCurrentX() == x && this.units[i].getCurrentY() == y && !this.units[i].getDocked())
         {  return this.units[i];
         }
      }

      return null;
   }

   public Unit getUnitAttackingMe(Unit unit)
   {  for (int i=0; i < this.numUnits; i++)
      {  if (this.units[i].getUnitTarget() == unit)
         {  return this.units[i];
         }
      }
      return null;
   }

   public Building getBuildingAt(int x, int y)
   {  for (int i=0; i < this.numBuildings; i++)
      {  for (int j=0; j < this.buildings[i].getLengthX(); j++)
         {  for (int k=0; k < this.buildings[i].getWidthY(); k++)
            {  if (this.buildings[i].getX()+j == x && this.buildings[i].getY()+k == y)
               {  return this.buildings[i];
               }
            }
         }
      }

      return null;
   }

   public Building getFirstBuilding()
   {  //System.out.println(this.buildings[0].getName() + ": (" + this.buildings[0].getX() + ", " + this.buildings[0].getY() + ")");
      if (this.numBuildings > 0)
      {  return this.buildings[0];
      } else
      {  return null;
      }
   }

   public Unit returnUnitNearXY(int x, int y)
   {  //FINISH THIS LATER

      x = x + (Constants.GRID_SIZE/2);
      y = y + (Constants.GRID_SIZE/2);

      return null;
   }

   public boolean isAUnitAt(int x, int y, int lengthX, int lengthY)
   {  for (int i=0; i < this.numUnits; i++)
      {  for(int j=0; j < lengthX; j++)
         {  for(int k=0; k < lengthY; k++)
            {  if ((this.units[i].getCurrentX() == x+j && this.units[i].getCurrentY() == y+k) || (this.units[i].getGotoX() == x+j && this.units[i].getGotoY() == y+k))
               {  return true;
               }
            }
         }
      }

      return false;
   }

   public String getHouse()
   {  return this.house;
   }

   public int getNumUnits()
   {  return this.numUnits;
   }

   public int getNumBuildings()
   {  return this.numBuildings;
   }

   public Unit[] getUnits()
   {  return this.units;
   }

   public Building[] getBuildings()
   {  return this.buildings;
   }

   public int getCredits()
   {  return this.numCredits;
   }

   public void addCredits(int amount)
   {  this.numCredits = this.numCredits + amount;
   }

   public void reduceCredits(int amount)
   {  this.numCredits = this.numCredits - amount;
   }

   protected boolean hasBuilding(String name)
   {  for (int i=0; i < this.numBuildings; i++)
      {  if (this.buildings[i].getName().equals(name))
         {  return true;
         }
      }

      return false;
   }

   protected int getNumBuilding(String name)
   {  int num = 0;
      for (int i=0; i < this.numBuildings; i++)
      {  if (this.buildings[i].getName().equals(name))
         {  num++;
         }
      }

      return num;
   }

   protected boolean hasUnit(String name)
   {  for (int i=0; i < this.numUnits; i++)
      {  if (this.units[i].getName().equals(name))
         {  return true;
         }
      }

      return false;
   }

   protected int getNumUnit(String name)
   {  int num = 0;
      for (int i=0; i < this.numUnits; i++)
      {  if (this.units[i].getName().equals(name))
         {  num++;
         }
      }

      return num;
   }

   public int getWaveNumber()
   {  return this.waveNumber;
   }
}
