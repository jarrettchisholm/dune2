package Dune2.Units;
import Dune2.Model.*;


import becker.io.TextInput;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DefenseUnit extends Unit
{
   public DefenseUnit(TextInput in)
   {  super(in);
   }

   public DefenseUnit(Unit unit, int hp, int previousHP, int deg1, int deg2, int x, int y, int x2, int y2, int progress, double lastFireTime, String status, int beHereX, int beHereY, int attackX, int attackY, boolean attacking, boolean moveAndAttack, int holdingCredits, int ownerTag, boolean docked, boolean harvesting, String job, int waveNumber, World theModel)
   {  super(unit, hp, previousHP, deg1, deg2, x, y, x2, y2, progress, lastFireTime, status, beHereX, beHereY, attackX, attackY, attacking, moveAndAttack, holdingCredits, ownerTag, docked, harvesting, job, waveNumber, theModel);
   }

   public void doRound()
   {  //changed so that enemy unit knows its under attack as SOON as projectile is created
      //getAttackedStatus() ONLY alerts the player that one of its units is under attack (visually)
      this.getAttackedStatus();

      //attacking
      if (this.getIsAttacking())
      {  this.doAttacking();
      }

      this.resetPreviousHP();
   }

   protected void getAttackedStatus()
   {  if (this.previousHP != this.currentHP && this.ownerTag == 0 && !this.docked && !this.name.equals(Constants.HARVESTOR))
      {  this.model.alertPlayerBuildingUnderAttack(this.currentX, this.currentY);
      }
   }

   public void doAttacking()
   {  this.refreshTargetInformation();

      if (this.canAttackTarget())
      {  this.doAttack();
      } else if (this.getIsAttacking() && !this.isInAttackingDistance())
      {  this.resetAttack();
      }
      if (this.getIsAttacking() && !this.isTurretOrBodyAngleCorrectForAttack())
      {  this.correctTurretOrBodyAngleForAttack();
      }
   }

   protected boolean canAttackTarget()
   {  if (this.isAttackingUnit() && !this.uTarget.isUnitDead())
      {  boolean attackingDistance = this.isInAttackingDistance();
         boolean correctAngle = this.isTurretOrBodyAngleCorrectForAttack();
         return (attackingDistance && correctAngle);
      } else if (this.isAttackingUnit() && this.uTarget.isUnitDead())
      {  this.resetAttack();
      } else if (this.isAttackingBuilding() && !this.bTarget.isBuildingDead())
      {  boolean attackingDistance = this.isInAttackingDistance();
         boolean correctAngle = this.isTurretOrBodyAngleCorrectForAttack();
         return (attackingDistance && correctAngle);
      }  else if (this.isAttackingBuilding() && this.bTarget.isBuildingDead())
      {  this.resetAttack();
      }

      return false;
   }

   public void setAttackAt(int x, int y, Unit target)
   {  this.forceTarget(target);

   }

   public void setAttackAt(int x, int y, Building target)
   {  this.forceTarget(target);

   }

   public void setBeHereCoordinates(int x, int y)
   {  //Do nothing
   }

   public void setCurrentXY(int x, int y)
   {  //Do nothing
   }

   public void setDocked()
   {  //Do nothing
   }

   public void setNotDocked()
   {  //Do nothing
   }

   public void setIsHarvesting()
   {  //Do nothing
   }

   public void setIsNotHarvesting()
   {  //Do nothing
   }

   public void setIsMovingAndAttacking()
   {  //Do nothing
   }

   public void resetIsMovingAndAttacking()
   {  //Do nothing
   }

   public void setStatusHarvesting()
   {  //Do nothing
   }

   public void setStatusStop()
   {  //Do nothing
   }

   public void setStatusMove()
   {  //Do nothing
   }

   public void setStatusRight()
   {  //Do nothing
   }

   public void setStatusLeft()
   {  //Do nothing
   }

   public void setStatusRight45()
   {  //Do nothing
   }

   public void setStatusLeft45()
   {  //Do nothing
   }

   protected void determineNextMovementAction()
   {  System.out.println("Defensive Structure trying to find a path...");
      //Do nothing
   }


}
