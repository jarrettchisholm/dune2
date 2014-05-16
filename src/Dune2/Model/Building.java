package Dune2.Model;

import becker.io.TextInput;

public class Building
{  private int power;
   private String name;
   private int totalHP;
   private int currentHP;
   private int previousHP;
   private int defense;
   private int cost;
   private String defenseType;
   private String currentConstruction;
   private String previousConstruction;
   private double progress;
   private int x;
   private int y;
   private String status;
   private String buildingNeeded;
   private double buildingLevel;
   private int lengthX;
   private int widthY;
   private int buildTime;
   private boolean enterable;
   private boolean harvestorDocked;
   private double harvestorHoldingTime;
   private int creditsHeld;
   private String unitJob;
   private int waveNumber;
   private int ownerTag;

   private World model;

   public Building(TextInput in)
   {  this.name = in.readLine();
      this.totalHP = in.readInt();
      in.readLine();
      this.defense = in.readInt();
      in.readLine();
      this.defenseType = in.readLine();
      this.lengthX = in.readInt();
      in.readLine();
      this.widthY = in.readInt();
      in.readLine();
      this.power = in.readInt();
      in.readLine();
      this.cost = in.readInt();
      in.readLine();
      this.buildTime = in.readInt();
      in.readLine();
      this.enterable = in.readBoolean();
      in.readLine();
      this.buildingNeeded = in.readLine();
      in.readLine();

   }

   public Building(Building building, int hp, String construction, String previousC, int progress, int x, int y, String status, double buildingLevel, boolean docked, String unitJob, int waveNumber, int previousHP, int ownerTag, World theModel)
   {  this.model = theModel;

      this.name = building.getName();
      this.totalHP = building.getTotalHP();
      this.defense = building.getDefense();
      this.defenseType = building.getDefenseType();
      this.lengthX = building.getLengthX();
      this.widthY = building.getWidthY();
      this.power = building.getPower();
      this.cost = building.getCost();
      this.buildTime = building.getBuildTime();
      this.enterable = building.getEnterable();
      this.buildingNeeded = building.getBuildingNeeded();
      this.harvestorDocked = docked;
      if (this.name.equals(Constants.REFINERY))
      {  //this.harvestorDocked = true;
         this.harvestorHoldingTime = 0.00;
         this.creditsHeld = 0;
      }
      this.currentHP = hp;
      this.currentConstruction = construction;
      this.previousConstruction = previousC;
      this.progress = progress;
      this.x = x;
      this.y = y;
      this.status = status;
      this.buildingLevel = buildingLevel;
      this.unitJob = unitJob;
      this.waveNumber = waveNumber;
      this.previousHP = previousHP;
      this.ownerTag = ownerTag;
   }

   public void doRound()
   {  this.getAttackedStatus();

      this.addToBuildingLevel(1000/Constants.GAME_SPEED);

      this.resetPreviousHP();
   }

   private void resetPreviousHP()
   {  this.previousHP = this.currentHP;
   }

   private void getAttackedStatus()
   {   if (this.previousHP != this.currentHP && this.ownerTag == 0)
      {  this.model.alertPlayerBuildingUnderAttack(this.x, this.y);
      }
   }

   public void buildUnitOrBuilding(String name, String unitJob, int waveNumber)
   {  this.resetProgress();
      this.setStatus(Constants.MOVE);
      this.setCurrentConstruction(name);
      this.unitJob = unitJob;
      this.waveNumber = waveNumber;
   }

   public void resetUnitOrBuilding()
   {  this.resetProgress();
      this.resetPreviousConstruction();
      this.setStatus(Constants.STOP);
      this.setCurrentConstruction(Constants.NONE);
      this.unitJob = Constants.NONE;
      this.waveNumber = 0;
   }

   private void resetPreviousConstruction()
   {  this.previousConstruction = this.currentConstruction;
   }

   public int getCost()
   {  return this.cost;
   }

   public String getName()
   {  return this.name;
   }

   public int getTotalHP()
   {  return this.totalHP;
   }

   public int getCurrentHP()
   {  return this.currentHP;
   }

   public int getDefense()
   {  return this.defense;
   }

   public String getDefenseType()
   {  return this.defenseType;
   }

   public String getCurrentConstruction()
   {  return this.currentConstruction;
   }

   public int getProgress()
   {  return (int)this.progress;
   }

   public int getX()
   {  return this.x;
   }

   public int getY()
   {  return this.y;
   }

   public int getLengthX()
   {  return this.lengthX;
   }

   public int getWidthY()
   {  return this.widthY;
   }

   public String getStatus()
   {  return this.status;
   }

   public double getBuildingLevel()
   {  return this.buildingLevel;
   }

   public int getPower()
   {  return this.power;
   }

   public void addToBuildingLevel(double amount)
   {  this.buildingLevel = this.buildingLevel + amount;
      if (this.buildingLevel > 600.00)
      {  this.buildingLevel = this.buildingLevel - 600.00;
      }
   }

   public void takeDamage(int amount)
   {  this.currentHP = this.currentHP - amount;
   }

   public boolean isBuildingDead()
   {  return (this.currentHP <= 0);
   }

   public void doDeathExplosion()
   {  
   }

   public void setCurrentConstruction(String cc)
   {  this.currentConstruction = cc;
   }

   public void setStatus(String status)
   {  this.status = status;
   }

   public void addToProgress(double amount)
   {  this.progress = this.progress + amount;
   }

   public void resetProgress()
   {  this.progress = -1;
   }

   public int getBuildTime()
   {  return this.buildTime;
   }

   public boolean getEnterable()
   {  return this.enterable;
   }

   public boolean getHarvestorDocked()
   {  return this.harvestorDocked;
   }

   public void setNoHarvestorDocked()
   {  this.harvestorDocked = false;
   }

   public void setHarvestorDocked()
   {  this.harvestorDocked = true;
   }

   public void increaseHarvestorHoldingTime()
   {  double gameTime = (double)Constants.GAME_SPEED;
      this.harvestorHoldingTime = this.harvestorHoldingTime + 1000/(gameTime*1000);
   }

   public void resetHarvestorHoldingTime()
   {  this.harvestorHoldingTime = 0;
   }

   public double getHarvestorHoldingTime()
   {  return this.harvestorHoldingTime;
   }

   public void changeCreditsHeld(int amount)
   {  this.creditsHeld = this.creditsHeld + amount;
   }

   public int getCreditsHeld()
   {  return this.creditsHeld;
   }

   public String getBuildingNeeded()
   {  return this.buildingNeeded;
   }

   public String getUnitJob()
   {  return this.unitJob;
   }

   public int getWaveNumber()
   {  return this.waveNumber;
   }

   public int getPreviousHP()
   {  return this.previousHP;
   }

   public int getHPFillSpace(int fillSpace)
   {  double temp = (double)this.currentHP / (double)this.totalHP;
      return (int)(temp * fillSpace);
   }

   public String getPreviousConstruction()
   {  return this.previousConstruction;
   }
}