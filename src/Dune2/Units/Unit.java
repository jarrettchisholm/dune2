package Dune2.Units;
import Dune2.Model.*;
import Dune2.Pathfinding.*;

import becker.io.TextInput;

public class Unit
{  protected String name;
   protected int totalHP;
   protected int currentHP;
   protected int previousHP;
   protected int attack;
   protected int defense;
   protected int lengthX;
   protected int widthY;
   protected int diagonalZ;
   protected int speed;
   protected String attackType;
   protected String defenseType;
   protected double rateOfFire;
   protected double lastFireTime;
   protected int bodyDegree;
   protected int turretDegree;
   protected int progress;
   protected String status;
   protected int cost;
   protected int buildTime;
   protected int holdingCredits = 0;
   protected boolean hasTurret;
   protected boolean docked;
   protected boolean isHarvesting;
   protected String projectileType;
   protected String explosionType;
   protected int ownerTag;
   protected int waveNumber;
   protected String builtFrom;
   protected String job;
   protected boolean canMove;

   protected World model;

   /*****************COMBAT VARIABLES*******************/
   protected int attackX;
   protected int attackY;
   protected Unit uTarget;
   protected Building bTarget;
   protected boolean isAttacking;
   protected boolean isMovingAndAttacking;
   protected double attackDistance;
   /***************************************************/


   /***************PATHFINDING VARIABLES***************/
   protected int currentX;
   protected int currentY;
   protected int gotoX;
   protected int gotoY;
   protected int beHereX;
   protected int beHereY;

   protected int[][] path;
   protected int pathIndex;

   protected int[][] blocked;
   protected int numBlocked;

   protected boolean inTransitChange;

   protected double findPathDelay = 0.00;
   protected int numTimesFindPath = 0;

   //protected CompiledList coordinateList;
   //protected OpenNodes openNodes;
   //protected ClosedNodes closedNodes;
   /***************************************************/

   public Unit(TextInput in)
   {  this.name = in.readLine();
      this.builtFrom = in.readLine();
      this.totalHP = in.readInt();
      in.readLine();
      this.attack = in.readInt();
      in.readLine();
      this.attackDistance = in.readDouble();
      in.readLine();
      this.defense = in.readInt();
      in.readLine();
      this.lengthX = in.readInt();
      in.readLine();
      this.widthY = in.readInt();
      in.readLine();
      this.diagonalZ = in.readInt();
      in.readLine();
      this.speed = in.readInt();
      in.readLine();
      this.attackType = in.readLine();
      this.defenseType = in.readLine();
      this.rateOfFire = in.readInt();
      in.readLine();
      this.lastFireTime = in.readDouble();
      in.readLine();
      this.cost = in.readInt();
      in.readLine();
      this.buildTime = in.readInt();
      in.readLine();
      this.hasTurret = in.readBoolean();
      in.readLine();
      this.projectileType = in.readLine();
      this.explosionType = in.readLine();
      this.canMove = in.readBoolean();
      in.readLine();
      in.readLine();
   }

   public Unit(Unit unit, int hp, int previousHP, int deg1, int deg2, int x, int y, int x2, int y2, int progress, double lastFireTime, String status, int beHereX, int beHereY, int attackX, int attackY, boolean attacking, boolean moveAndAttack, int holdingCredits, int ownerTag, boolean docked, boolean harvesting, String job, int waveNumber, World theModel)
   {  this.model = theModel;
      this.name = unit.getName();
      this.totalHP = unit.getTotalHP();
      this.builtFrom = unit.getBuiltFrom();
      this.currentHP = hp;
      this.previousHP = previousHP;
      this.attack = unit.getAttack();
      this.defense = unit.getDefense();
      this.lengthX = unit.getLengthX();
      this.widthY = unit.getWidthY();
      this.diagonalZ = unit.getDiagonalZ();
      this.speed = unit.getSpeed();
      this.attackType = unit.getAttackType();
      this.defenseType = unit.getDefenseType();
      this.rateOfFire = unit.getRateOfFire();
      this.buildTime = unit.getBuildTime();
      this.cost = unit.getCost();
      this.hasTurret = unit.hasTurret();
      this.attackDistance = unit.getAttackDistance();
      this.projectileType = unit.getProjectileType();
      this.explosionType = unit.getExplosionType();
      this.canMove = unit.getCanMove();
      this.lastFireTime = lastFireTime;
      this.bodyDegree = deg1;
      this.turretDegree = deg2;
      this.currentX = x;
      this.currentY = y;
      this.gotoX = x2;
      this.gotoY = y2;
      this.progress = progress;
      this.status = status;
      this.beHereX = beHereX;
      this.beHereY = beHereY;
      this.attackX = attackX;
      this.attackY = attackY;
      this.isAttacking = attacking;
      this.isMovingAndAttacking = moveAndAttack;
      this.holdingCredits = holdingCredits;
      this.docked = docked;
      this.isHarvesting = harvesting;
      this.ownerTag = ownerTag;
      this.job = job;
      this.waveNumber = waveNumber;
   }

   public void acquireTargetAfterFileLoad()
   {  if (this.isAttacking)
      {  this.uTarget = this.model.getUnitAt(this.attackX, this.attackY);
         this.bTarget = this.model.getBuildingAt(this.attackX, this.attackY);
      }
   }

   public void doRound()
   {  //changed so that enemy unit knows its under attack as SOON as projectile is created
      //getAttackedStatus() ONLY alerts the player that one of its units is under attack (visually)
      this.getAttackedStatus();

      /* If the unit has been given coordinates to attack to OR to move to, but
         failed to find a path to the coordinates, tell the unit to attempt again.
         Note:  The unit attempts to find another path every 4 seconds, up to
         a maximum of 10 times...(these values may change --> Constants class).
      */
      if (!this.hasValidPath() && this.beHereX >= 0 && this.beHereY >= 0 && this.numTimesFindPath < Constants.NUM_TIMES_FIND_PATH )
      {  if (this.findPathDelay >= Constants.FIND_PATH_DELAY_TIME)
         {  this.findPathDelay = 0.00;
            this.setBeHereCoordinates(this.beHereX, this.beHereY);
         } else
         {  this.findPathDelay = this.findPathDelay + this.model.getTimeIncrement();
         }
      } else if (!this.hasValidPath() && this.attackX >= 0 && this.attackY >= 0 && this.numTimesFindPath < Constants.NUM_TIMES_FIND_PATH)
      {  if (this.findPathDelay >= Constants.FIND_PATH_DELAY_TIME)
         {  this.findPathDelay = 0.00;
            this.setBeHereCoordinates(this.attackX, this.attackY);
         } else
         {  this.findPathDelay = this.findPathDelay + this.model.getTimeIncrement();
         }
      } else if (this.hasValidPath())
      {  //if a unit finally has found a path, reset numTimesFindPath;
         this.numTimesFindPath = 0;
      }

     //movement
      if (this.status.equals(Constants.MOVE))
      {  this.doMovement();
      }

      //turning
      if (this.status.equals(Constants.LEFT) || this.status.equals(Constants.RIGHT)|| this.status.equals(Constants.RIGHT_45)|| this.status.equals(Constants.LEFT_45))
      {  this.doTurning();
      }

      //turret turning
      if ((this.status.equals(Constants.MOVE) || this.status.equals(Constants.LEFT) || this.status.equals(Constants.RIGHT) || this.status.equals(Constants.LEFT_45) || this.status.equals(Constants.RIGHT_45)) && this.getHasTurret() && !this.getIsAttacking())
      {  this.doMovementTurretTurning();
      }

      //attacking
      if (this.getIsAttacking())
      {  this.doAttacking();
      }

      //DO NEXT ACTION IF APPLICABLE
      if (this.isAttacking())
      {  //this.determineNextCombatAction();
      } else if (this.isMovingAndStopped())
      {  this.determineNextMovementAction();
      }

      this.resetPreviousHP();
   }

   protected void doMovement()
   {  this.progress = this.progress + this.speed;

      //CHECK IF MOVEMENT IS DONE
      if (this.progress >= Constants.GRID_SIZE && (this.currentX == this.gotoX || this.currentY == this.gotoY))
      {  this.progress = -1;
         this.status = Constants.STOP;
         this.currentX = this.gotoX;
         this.gotoX = -1;
         this.currentY = this.gotoY;
         this.gotoY = -1;
         if (!this.inTransitChange)
         {  this.pathIndex++;
         } else
         {  this.inTransitChange = false;
         }
         this.model.doCheckOfAreaForUnit(this.currentX, this.currentY, this.ownerTag);

      } else if (this.progress >= Constants.DIAGONAL_MOVEMENT_SIZE && this.currentX != this.gotoX && this.currentY != this.gotoY)
      {  this.progress = -1;
         this.status = Constants.STOP;
         this.currentX = this.gotoX;
         this.gotoX = -1;
         this.currentY = this.gotoY;
         this.gotoY = -1;
         if (!this.inTransitChange)
         {  this.pathIndex++;
         } else
         {  this.inTransitChange = false;
         }
         this.model.doCheckOfAreaForUnit(this.currentX, this.currentY, this.ownerTag);

      }

      if (this.isUnitDoneMoving())
      {  this.resetBeHereCoordinates();
         this.resetPath();
         this.resetTemporarilyBlocked();
         this.resetIsMovingAndAttacking();
         /* I may need to remove this...but it will reset attackX and attackY
         */
         //this.resetAttackCoordinates();
      }
   }

   public void doMovementTurretTurning()
   {
      if (this.beHereX >=0 && this.beHereY >= 0)
      {  double x = this.beHereX - this.currentX;
         double y = this.beHereY - this.currentY;

         if (this.currentX < this.beHereX && this.currentY > this.beHereY) //UP RIGHT
         {  if (this.turretDegree < 360 && this.turretDegree > 225)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree <=255 && this.turretDegree > 90)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  int beHereDegree = (int)Math.abs((Math.atan(x/(-y)) * 180 / Math.PI));
               int compareDegree = beHereDegree - this.turretDegree;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX > this.beHereX && this.currentY > this.beHereY) // UP LEFT
         {  if (this.turretDegree <= 270 && this.turretDegree > 135)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree <=135 && this.turretDegree >= 0)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  int beHereDegree = (int)Math.abs(90 - (Math.atan(x/y) * 180 / Math.PI));
               int compareDegree = beHereDegree - this.turretDegree + 270;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX > this.beHereX && this.currentY < this.beHereY) //DOWN LEFT
         {  if ((this.turretDegree < 360 && this.turretDegree >= 270) || this.turretDegree < 45)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else if (this.turretDegree <=180 && this.turretDegree >45)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else
            {  int beHereDegree = (int)Math.abs((Math.atan(x/y) * 180 / Math.PI));
               int compareDegree = beHereDegree - this.turretDegree + 180;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX < this.beHereX && this.currentY < this.beHereY) //DOWN RIGHT
         {  if ((this.turretDegree < 360 && this.turretDegree >= 315) || (this.turretDegree >= 0 && this.turretDegree <= 90))
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree < 315 && this.turretDegree >= 180)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  int beHereDegree = (int)Math.abs(90 - (Math.atan(x/y) * 180 / Math.PI));
               int compareDegree = beHereDegree - this.turretDegree + 90;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX > this.beHereX && this.currentY == this.beHereY) // LEFT
         {  if (this.turretDegree >= 0 && this.turretDegree < 90)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else if (this.turretDegree >= 90 && this.turretDegree <= 180)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else
            {  int compareDegree = 270 - this.turretDegree;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX < this.beHereX && this.currentY == this.beHereY) // RIGHT
         {  if (this.turretDegree < 360 && this.turretDegree >= 270)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree < 270 && this.turretDegree >= 180)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  int compareDegree = 90 - this.turretDegree;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX == this.beHereX && this.currentY > this.beHereY) // UP
         {  if (this.turretDegree <= 270 && this.turretDegree >= 180)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree < 180 && this.turretDegree >= 90)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  if (this.turretDegree > 270 && this.turretDegree < 360)
               {  if ((360 - this.turretDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + (360 - this.turretDegree);
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               } else if (this.turretDegree > 0)
               {  if ((360 - this.turretDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree - (360 - this.turretDegree);
                  } else
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX == this.beHereX && this.currentY < this.beHereY) // DOWN
         {
            if (this.turretDegree >= 270 && this.turretDegree < 360)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else if (this.turretDegree >= 0 && this.turretDegree <= 90)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else
            {  if (this.turretDegree > 180 && this.turretDegree < 270)
               {  if (Math.abs(180 - this.turretDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree - Math.abs(180 - this.turretDegree);
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else if (this.turretDegree > 90)
               {  if ((180 - this.turretDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + (180 - this.turretDegree);
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         }
      }

      this.fixTurretDegree();
      /*
      if (this.status.equals(Constants.STOP))
      {  this.determineNextAction();
      }
      */
   }

   public void doCombatTurretTurning()
   {
      if (this.attackX >=0 && this.attackY >= 0)
      {  double x = this.attackX - this.currentX;
         double y = this.attackY - this.currentY;

         if (this.currentX < this.attackX && this.currentY > this.attackY) //UP RIGHT
         {  if (this.turretDegree < 360 && this.turretDegree > 225)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree <=255 && this.turretDegree > 90)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  int beHereDegree = (int)Math.abs((Math.atan(x/(-y)) * 180 / Math.PI));
               int compareDegree = beHereDegree - this.turretDegree;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX > this.attackX && this.currentY > this.attackY) // UP LEFT
         {  if (this.turretDegree <= 270 && this.turretDegree > 135)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree <=135 && this.turretDegree >= 0)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  int beHereDegree = (int)Math.abs(90 - (Math.atan(x/y) * 180 / Math.PI));
               int compareDegree = beHereDegree - this.turretDegree + 270;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX > this.attackX && this.currentY < this.attackY) //DOWN LEFT
         {  if ((this.turretDegree < 360 && this.turretDegree >= 270) || this.turretDegree < 45)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else if (this.turretDegree <=180 && this.turretDegree >45)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else
            {  int beHereDegree = (int)Math.abs((Math.atan(x/y) * 180 / Math.PI));
               int compareDegree = beHereDegree - this.turretDegree + 180;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX < this.attackX && this.currentY < this.attackY) //DOWN RIGHT
         {  if ((this.turretDegree < 360 && this.turretDegree >= 315) || (this.turretDegree >= 0 && this.turretDegree <= 90))
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree < 315 && this.turretDegree >= 180)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  int beHereDegree = (int)Math.abs(90 - (Math.atan(x/y) * 180 / Math.PI));
               int compareDegree = beHereDegree - this.turretDegree + 90;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX > this.attackX && this.currentY == this.attackY) // LEFT
         {  if (this.turretDegree >= 0 && this.turretDegree < 90)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else if (this.turretDegree >= 90 && this.turretDegree <= 180)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else
            {  int compareDegree = 270 - this.turretDegree;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX < this.attackX && this.currentY == this.attackY) // RIGHT
         {  if (this.turretDegree < 360 && this.turretDegree >= 270)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree < 270 && this.turretDegree >= 180)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  int compareDegree = 90 - this.turretDegree;
               if (compareDegree < 0)
               {  if ( Math.abs(compareDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else
               {  if (compareDegree < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + compareDegree;
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX == this.attackX && this.currentY > this.attackY) // UP
         {  if (this.turretDegree <= 270 && this.turretDegree >= 180)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else if (this.turretDegree < 180 && this.turretDegree >= 90)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else
            {  if (this.turretDegree > 270 && this.turretDegree < 360)
               {  if ((360 - this.turretDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + (360 - this.turretDegree);
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               } else if (this.turretDegree > 0)
               {  if ((360 - this.turretDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree - (360 - this.turretDegree);
                  } else
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               }
            }
         } else if (this.currentX == this.attackX && this.currentY < this.attackY) // DOWN
         {
            if (this.turretDegree >= 270 && this.turretDegree < 360)
            {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
            } else if (this.turretDegree >= 0 && this.turretDegree <= 90)
            {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
            } else
            {  if (this.turretDegree > 180 && this.turretDegree < 270)
               {  if (Math.abs(180 - this.turretDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree - Math.abs(180 - this.turretDegree);
                  } else //Note:  compare degree is NEGATIVE...so its not adding, its subtracting (above)
                  {  this.turretDegree = this.turretDegree - Constants.TURRET_SPEED;
                  }
               } else if (this.turretDegree > 90)
               {  if ((180 - this.turretDegree) < Constants.TURRET_SPEED)
                  {  this.turretDegree = this.turretDegree + (180 - this.turretDegree);
                  } else
                  {  this.turretDegree = this.turretDegree + Constants.TURRET_SPEED;
                  }
               }
            }
         }
      }

      this.fixTurretDegree();
      /*
      if (this.status.equals(Constants.STOP))
      {  this.determineNextAction();
      }
      */
   }

   protected boolean upRightClear(int x, int y)
   {  boolean land = this.model.isLandEnterable(x + 1, y - 1);
      boolean building = this.model.isABuildingAt(x+1, y-1, 1, 1);
      boolean unit = this.model.isAUnitAt(x+1, y-1);

      return (land && !building && !unit);
   }

   protected boolean upRightClearForHarvestor(int x, int y)
   {  Building building = this.model.getBuildingAt(x+1, y-1);
      if (building != null)
      {  if (building.getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR))
         {  return true;
         }
      }

      return false;
   }

   protected boolean upClear(int x, int y)
   {  boolean land = this.model.isLandEnterable(x, y - 1);
      boolean building = this.model.isABuildingAt(x, y-1, 1, 1);
      boolean unit = this.model.isAUnitAt(x, y-1);

      return (land && !building && !unit);
   }

   protected boolean upClearForHarvestor(int x, int y)
   {  Building building = this.model.getBuildingAt(x, y-1);
      if (building != null)
      {  if (building.getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR))
         {  return true;
         }
      }

      return false;
   }

   protected boolean upLeftClear(int x, int y)
   {  boolean land = this.model.isLandEnterable(x - 1, y - 1);
      boolean building = this.model.isABuildingAt(x-1, y-1, 1, 1);
      boolean unit = this.model.isAUnitAt(x-1, y-1);
      return (land && !building && !unit);
   }

   protected boolean upLeftClearForHarvestor(int x, int y)
   {  Building building = this.model.getBuildingAt(x-1, y-1);
      if (building != null)
      {  if (building.getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR))
         {  return true;
         }
      }

      return false;
   }

   protected boolean leftClear(int x, int y)
   {  boolean land = this.model.isLandEnterable(x - 1,y);
      boolean building = this.model.isABuildingAt(x-1, y, 1, 1);
      boolean unit = this.model.isAUnitAt(x-1, y);

      return (land && !building && !unit);
   }

   protected boolean leftClearForHarvestor(int x, int y)
   {  Building building = this.model.getBuildingAt(x-1, y);
      if (building != null)
      {  if (building.getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR))
         {  return true;
         }
      }

      return false;
   }

   protected boolean downLeftClear(int x, int y)
   {  boolean land = this.model.isLandEnterable(x - 1, y + 1);
      boolean building = this.model.isABuildingAt(x-1, y+1, 1, 1);
      boolean unit = this.model.isAUnitAt(x-1, y+1);

      return (land && !building && !unit);
   }

   protected boolean downLeftClearForHarvestor(int x, int y)
   {  Building building = this.model.getBuildingAt(x-1, y+1);
      if (building != null)
      {  if (building.getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR))
         {  return true;
         }
      }

      return false;
   }

   protected boolean downClear(int x, int y)
   {  boolean land = this.model.isLandEnterable(x, y + 1);
      boolean building = this.model.isABuildingAt(x, y+1, 1, 1);
      boolean unit = this.model.isAUnitAt(x, y+1);

      return (land && !building && !unit);
   }

   protected boolean downClearForHarvestor(int x, int y)
   {  Building building = this.model.getBuildingAt(x, y+1);
      if (building != null)
      {  if (building.getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR))
         {  return true;
         }
      }

      return false;
   }

   protected boolean downRightClear(int x, int y)
   {  boolean land = this.model.isLandEnterable(x + 1, y + 1);
      boolean building = this.model.isABuildingAt(x+1, y+1, 1, 1);
      boolean unit = this.model.isAUnitAt(x+1, y+1);

      return (land && !building && !unit);
   }

   protected boolean downRightClearForHarvestor(int x, int y)
   {  Building building = this.model.getBuildingAt(x+1, y+1);
      if (building != null)
      {  if (building.getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR))
         {  return true;
         }
      }

      return false;
   }

   protected boolean rightClear(int x, int y)
   {  boolean land = this.model.isLandEnterable(x + 1, y);
      boolean building = this.model.isABuildingAt(x+1, y, 1, 1);
      boolean unit = this.model.isAUnitAt(x+1, y);

      return (land && !building && !unit);
   }

   protected boolean rightClearForHarvestor(int x, int y)
   {  Building building = this.model.getBuildingAt(x+1, y);
      if (building != null)
      {  if (building.getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR))
         {  return true;
         }
      }

      return false;
   }

   protected boolean coordinatesClear(int x, int y)
   {  boolean land = this.model.isLandEnterable(x, y);
      boolean building = this.isABuildingAt(x, y, 1, 1, -1, -1);
      boolean unit = this.model.isAUnitAt(x, y);

      return (land && !building && !unit);
   }

   protected void moveUpRight()
   {  if (this.bodyDegree == 45)
      {  this.status = Constants.MOVE;
         this.progress = 0;
         this.gotoX = this.currentX + 1;
         this.gotoY = this.currentY - 1;
      } else
      {  this.doBodyTurnTo(45);
      }
   }

   protected void moveUpLeft()
   {  if (this.bodyDegree == 315)
      {  this.status = Constants.MOVE;
         this.progress = 0;
         this.gotoX = this.currentX - 1;
         this.gotoY = this.currentY - 1;
      }  else
      {  this.doBodyTurnTo(315);
      }
   }

   protected void moveDownRight()
   {  if (this.bodyDegree == 135)
      {  this.status = Constants.MOVE;
         this.progress = 0;
         this.gotoX = this.currentX + 1;
         this.gotoY = this.currentY + 1;
      } else
      {  this.doBodyTurnTo(135);
      }
   }

   protected void moveDownLeft()
   {  if (this.bodyDegree == 225)
      {  this.status = Constants.MOVE;
         this.progress = 0;
         this.gotoX = this.currentX - 1;
         this.gotoY = this.currentY + 1;
      } else
      {  this.doBodyTurnTo(225);
      }
   }

   protected void moveRight()
   {  if (this.bodyDegree == 90)
      {  this.status = Constants.MOVE;
         this.progress = 0;
         this.gotoX = this.currentX + 1;
         this.gotoY = this.currentY;
      } else
      {  this.doBodyTurnTo(90);
      }
   }

   protected void moveLeft()
   {  if (this.bodyDegree == 270)
      {  this.status = Constants.MOVE;
         this.progress = 0;
         this.gotoX = this.currentX - 1;
         this.gotoY = this.currentY;
      } else
      {  this.doBodyTurnTo(270);
      }
   }

   protected void moveDown()
   {  if (this.bodyDegree == 180)
      {  this.status = Constants.MOVE;
         this.progress = 0;
         this.gotoX = this.currentX;
         this.gotoY = this.currentY + 1;
      } else
      {  this.doBodyTurnTo(180);
      }
   }

   protected void moveUp()
   {  if (this.bodyDegree == 0)
      {  this.status = Constants.MOVE;
         this.progress = 0;
         this.gotoX = this.currentX;
         this.gotoY = this.currentY - 1;
      } else
      {  this.doBodyTurnTo(0);
      }
   }

   protected void doBodyTurnTo(int degree)
   {  if (this.status.equals(Constants.STOP))
      {  if (degree == 45)
         {  if (this.bodyDegree < 45 && this.bodyDegree > 0)
            {  this.status = Constants.RIGHT;
               this.progress = 0;
            } else if (this.bodyDegree > 45 && this.bodyDegree <= 90)
            {  this.status = Constants.LEFT_45;
               this.progress = 0;
            }else if (this.bodyDegree >= 270)
            {  this.status = Constants.RIGHT;
               this.progress = 0;
            } else if (this.bodyDegree < 270 && this.bodyDegree > 90)
            {  this.status = Constants.LEFT;
               this.progress = 0;
            } else if (this.bodyDegree == 0)
            {  this.status = Constants.RIGHT_45;
               this.progress = 0;
            }
         } else if (degree == 315)
         {  if (this.bodyDegree < 315 && this.bodyDegree >= 270)
            {  this.status = Constants.RIGHT_45;
              this.progress = 0;
           } else if (this.bodyDegree > 315 || this.bodyDegree == 0)
           {  this.status = Constants.LEFT_45;
              this.progress = 0;
           } else if (this.bodyDegree > 0 && this.bodyDegree <= 135)
           {  this.status = Constants.LEFT;
              this.progress = 0;
           } else if (this.bodyDegree > 135)
           {  this.status = Constants.RIGHT;
              this.progress = 0;
           }
         }  else if (degree == 135)
         {  if (this.bodyDegree < 135 && this.bodyDegree >= 90)
            {  this.status = Constants.RIGHT_45;
               this.progress = 0;
            } else if (this.bodyDegree > 135 && this.bodyDegree <= 180)
            {  this.status = Constants.LEFT_45;
               this.progress = 0;
            } else if (this.bodyDegree > 180)
            {  this.status = Constants.LEFT;
               this.progress = 0;
            } else if (this.bodyDegree < 90)
            {  this.status = Constants.RIGHT;
               this.progress = 0;
            }
         } else if (degree == 225)
         {  if (this.bodyDegree < 225 && this.bodyDegree >= 180)
            {  this.status = Constants.RIGHT_45;
               this.progress = 0;
            } else if (this.bodyDegree > 225 && this.bodyDegree <= 270)
            {  this.status = Constants.LEFT_45;
               this.progress = 0;
            } else if (this.bodyDegree > 270)
            {  this.status = Constants.LEFT;
               this.progress = 0;
            } else if (this.bodyDegree < 180)
            {  this.status = Constants.RIGHT;
               this.progress = 0;
            }
         } else if (degree == 90)
         {  if (this.bodyDegree < 90 && this.bodyDegree >=45)
            {  this.status = Constants.RIGHT_45;
               this.progress = 0;
            } else if ((this.bodyDegree < 45 && this.bodyDegree >=0) ||  (this.bodyDegree > 270 && this.bodyDegree <= 360))
            {  this.status = Constants.RIGHT;
               this.progress = 0;
            } else if (this.bodyDegree < 270 && this.bodyDegree > 180)
            {  this.status = Constants.LEFT;
               this.progress = 0;
            } else
            {  this.status = Constants.LEFT_45;
               this.progress = 0;
            }
         } else if (degree == 270)
         {  if (this.bodyDegree < 270 && this.bodyDegree >= 225)
            {  this.status = Constants.RIGHT_45;
               this.progress = 0;
            } else if (this.bodyDegree < 225 && this.bodyDegree > 90)
            {  this.status = Constants.RIGHT;
               this.progress = 0;
            } else if (this.bodyDegree > 270 && this.bodyDegree >= 315)
            {  this.status = Constants.LEFT_45;
               this.progress = 0;
            } else
            {  this.status = Constants.LEFT;
               this.progress = 0;
            }
         } else if (degree == 180)
         {  if (this.bodyDegree < 180 && this.bodyDegree >= 135)
            {  this.status = Constants.RIGHT_45;
               this.progress = 0;
            } else if (this.bodyDegree < 135)
            {  this.status = Constants.RIGHT;
               this.progress = 0;
            } else if (this.bodyDegree > 225)
            {  this.status = Constants.LEFT;
               this.progress = 0;
            } else
            {  this.status = Constants.LEFT_45;
               this.progress = 0;
            }
         } else if (degree == 0)
         {  if (this.bodyDegree < 360 && this.bodyDegree >= 315)
            {  this.status = Constants.RIGHT_45;
               this.progress = 0;
            } else if (this.bodyDegree < 315 && this.bodyDegree > 180)
            {  this.status = Constants.RIGHT;
               this.progress = 0;
            } else if (this.bodyDegree > 45 && this.bodyDegree <=180)
            {  this.status = Constants.LEFT;
               this.progress = 0;
            } else
            {  this.status = Constants.LEFT_45;
               this.progress = 0;
            }
         }
      }
   }

   protected void moveToCurrentSquare()
   {  this.status = Constants.MOVE;
      this.progress = Constants.GRID_SIZE;
      this.gotoX = this.currentX;
      this.gotoY = this.currentY;
   }

   protected void createProjectile()
   {  //Calculate the distance to target
      double distance = this.getDistanceToTarget() * Constants.GRID_SIZE;

      double degree = (double)this.turretDegree;
      //Calculate the degree to the target
      if (!this.hasTurret)
      {  if (this.currentX < this.attackX && this.currentY == this.attackY)
         {  degree = 90.0;
         } else if (this.currentX == this.attackX && this.currentY < this.attackY)
         {  degree = 180.0;
         } else if (this.currentX > this.attackX && this.currentY == this.attackY)
         {  degree = 270.0;
         } else if (this.currentX == this.attackX && this.currentY > this.attackY)
         {  degree = 0.0;
         } else
         {  degree = this.getLocalizedDegreeToTarget();
            if (this.currentX < this.attackX && this.currentY < this.attackY)
            {  //DOWN and RIGHT
               degree = 180.0 - degree;
            } else if (this.currentX > this.attackX && this.currentY < this.attackY)
            {  //DOWN and LEFT
               degree = 180.0 + degree;
            } else if (this.currentX > this.attackX && this.currentY > this.attackY)
            {  degree = 360.0 - degree;
            }
         }
      }


      if (this.isAttackingUnit())
      {  this.model.createProjectileTargetUnit(this, this.uTarget, this.currentX, this.currentY, this.attackX, this.attackY, distance, 0.0, degree, this.attack, this.projectileType);
      } else if (this.isAttackingBuilding())
      {  this.model.createProjectileTargetBuilding(this.currentX, this.currentY, this.attackX, this.attackY, distance, 0.0, degree, this.attack, this.projectileType);
      }
   }

   protected boolean isAttackingUnit()
   {  return (this.isAttacking && this.uTarget != null);
   }

   protected boolean isAttackingBuilding()
   {  return (this.isAttacking && this.bTarget != null);
   }

   protected double getDistanceToTarget()
   {  double xF = this.attackX - this.currentX;
      double yF = this.attackY - this.currentY;
      return Math.sqrt((double)((xF*xF) + (yF*yF)));
   }

   protected int getLocalizedDegreeToTarget()
   {  double x = this.attackX - this.currentX;
      double y = this.attackY - this.currentY;
      return (int)Math.abs((Math.atan(x/y) * (180/Math.PI)));
   }

   protected boolean isInAttackingDistance()
   {  double distance = this.getDistanceToTarget();
      return (this.attackDistance >= distance);

   }

   //protected void getInAttackingDistance()
   //{  this.determineNextCombatAction();
   //}

   protected void correctTurretOrBodyAngleForAttack()
   {
      int targetX = this.attackX;
      int targetY = this.attackY;
      int smallDegree = this.getLocalizedDegreeToTarget();

      //UP and RIGHT
      if (this.currentX < targetX && this.currentY > targetY)
      {  if (this.hasTurret)
         {  if (!(this.turretDegree >= (smallDegree - 5) && this.turretDegree <= (smallDegree + 5)))
            {  this.doCombatTurretTurning();
            }
         } else if (this.bodyDegree != 45)
         {  this.doBodyTurnTo(45);
         }
      } else //DOWN and RIGHT
      if (this.currentX < targetX && this.currentY < targetY)
      {  //fix small degree for the down and right quadrant
         smallDegree = 180 - smallDegree;

         if (this.hasTurret)
         {  if (!(this.turretDegree >= (smallDegree - 5) && this.turretDegree <= (smallDegree + 5)))
            {  this.doCombatTurretTurning();
            }
         } else if (this.bodyDegree != 135)
         {  this.doBodyTurnTo(135);
         }
      } else //RIGHT
      if (this.currentX < targetX && this.currentY == targetY)
      {  if (this.hasTurret)
         {  if (!(this.turretDegree >= (90 - 5) && this.turretDegree <= (90 + 5)))
            {  this.doCombatTurretTurning();
            }
         } else if (this.bodyDegree != 90)
         {  this.doBodyTurnTo(90);
         }
      } else //DOWN
      if (this.currentX == targetX && this.currentY < targetY)
      {  if (this.hasTurret)
         {  if (!(this.turretDegree >= (180 - 5) && this.turretDegree <= (180 + 5)))
            {  this.doCombatTurretTurning();
            }
         } else if (this.bodyDegree != 180)
         {  this.doBodyTurnTo(180);
         }
      } else //UP
      if (this.currentX == targetX && this.currentY > targetY)
      {
         if (this.hasTurret)
         {  if (!(this.turretDegree >= (0 - 5) && this.turretDegree <= (0 + 5)))
            {
               this.doCombatTurretTurning();
            }
         } else if (this.bodyDegree != 0)
         {  this.doBodyTurnTo(0);
         }
      } else //LEFT and DOWN
      if (this.currentX > targetX && this.currentY < targetY)
      {  //fix small degree for the down and right quadrant
         smallDegree = 180 + smallDegree;

         if (this.hasTurret)
         {  if (!(this.turretDegree >= (smallDegree - 5) && this.turretDegree <= (smallDegree + 5)))
            {  this.doCombatTurretTurning();
            }
         } else if (this.bodyDegree != 225) //CHANGED THIS from 0 --> maybe errors???!!!
         {  this.doBodyTurnTo(225);
         }
      } else //LEFT and UP
      if (this.currentX > targetX && this.currentY > targetY)
      {  //fix small degree for the down and right quadrant
         smallDegree = 360 - smallDegree;

         if (this.hasTurret)
         {  if (!(this.turretDegree >= (smallDegree - 5) && this.turretDegree <= (smallDegree + 5)))
            {  this.doCombatTurretTurning();
            }
         } else if (this.bodyDegree != 315)
         {  this.doBodyTurnTo(315);
         }
      } else //LEFT
      {  if (this.currentX > targetX && this.currentY == targetY)
         {  if (this.hasTurret)
            {  if (!(this.turretDegree >= (270 - 5) && this.turretDegree <= (270 + 5)))
               {  this.doCombatTurretTurning();
               }
            } else if (this.bodyDegree != 270)
            {  this.doBodyTurnTo(270);
            }
         }
      }
   }

   protected boolean isTurretOrBodyAngleCorrectForAttack()
   {  int targetX = this.attackX;
      int targetY = this.attackY;
      int smallDegree = this.getLocalizedDegreeToTarget();

      //UP and RIGHT
      if (this.currentX < targetX && this.currentY > targetY)
      {  if (this.hasTurret)
         {  if (this.turretDegree >= (smallDegree - 5) && this.turretDegree <= (smallDegree + 5))
            {  return true;
            } else
            {  return false;
            }
         } else if (this.bodyDegree == 45)
         {  return true;
         } else
         {  return false;
         }
      } else //DOWN and RIGHT
      if (this.currentX < targetX && this.currentY < targetY)
      {  //fix small degree for the down and right quadrant
         smallDegree = 180 - smallDegree;

         if (this.hasTurret)
         {  if (this.turretDegree >= (smallDegree - 5) && this.turretDegree <= (smallDegree + 5))
            {  return true;
            } else
            {  return false;
            }
         } else if (this.bodyDegree == 135)
         {  return true;
         } else
         {  return false;
         }
      } else //RIGHT
      if (this.currentX < targetX && this.currentY == targetY)
      {  if (this.hasTurret)
         {  if (this.turretDegree >= (90 - 5) && this.turretDegree <= (90 + 5))
            {  return true;
            } else
            {  return false;
            }
         } else if (this.bodyDegree == 90)
         {  return true;
         } else
         {  return false;
         }
      } else //DOWN
      if (this.currentX == targetX && this.currentY < targetY)
      {  if (this.hasTurret)
         {  if (this.turretDegree >= (180 - 5) && this.turretDegree <= (180 + 5))
            {  return true;
            } else
            {  return false;
            }
         } else if (this.bodyDegree == 180)
         {  return true;
         } else
         {  return false;
         }
      } else //UP
      if (this.currentX == targetX && this.currentY > targetY)
      {  if (this.hasTurret)
         {  if (this.turretDegree >= (0 - 5) && this.turretDegree <= (0 + 5))
            {  return true;
            } else
            {  return false;
            }
         } else if (this.bodyDegree == 0)
         {  return true;
         } else
         {  return false;
         }
      } else //LEFT and DOWN
      if (this.currentX > targetX && this.currentY < targetY)
      {  //fix small degree for the down and right quadrant
         smallDegree = 180 + smallDegree;

         if (this.hasTurret)
         {  if (this.turretDegree >= (smallDegree - 5) && this.turretDegree <= (smallDegree + 5))
            {  return true;
            } else
            {  return false;
            }
         } else if (this.bodyDegree == 0)
         {  return true;
         } else
         {  return false;
         }
      } else //LEFT and UP
      if (this.currentX > targetX && this.currentY > targetY)
      {  //fix small degree for the down and right quadrant
         smallDegree = 360 - smallDegree;

         if (this.hasTurret)
         {  if (this.turretDegree >= (smallDegree - 5) && this.turretDegree <= (smallDegree + 5))
            {  return true;
            } else
            {  return false;
            }
         } else if (this.bodyDegree == 315)
         {  return true;
         } else
         {  return false;
         }
      } else //LEFT
      {  if (this.currentX > targetX && this.currentY == targetY)
         {  if (this.hasTurret)
            {  if (this.turretDegree >= (270 - 5) && this.turretDegree <= (270 + 5))
               {  return true;
               } else
               {  return false;
               }
            } else if (this.bodyDegree == 270)
            {  return true;
            } else
            {  return false;
            }
         }
      }

      return false;
   }

   public boolean getIsAttacking()
   {  return (this.isAttacking && (this.uTarget != null || this.bTarget != null));
   }

   public boolean isUnitDead()
   {  return (this.currentHP <= 0);
   }

   protected boolean hasValidPath()
   {  return (this.path != null);
   }

   public void takeDamage(int amount)
   {  this.currentHP = this.currentHP - (amount - this.defense);
   }

   public void doDeathExplosion()
   {  this.model.createExplosion(this.explosionType, this.currentX, this.currentY, 1.0);
   }

   protected boolean canAttackTarget()
   {  if (this.isAttackingUnit() && !this.uTarget.isUnitDead())
      {  boolean attackingDistance = this.isInAttackingDistance();
         boolean correctAngle = this.isTurretOrBodyAngleCorrectForAttack();
         return (attackingDistance && correctAngle);
      } else if (this.isAttackingUnit() && this.uTarget.isUnitDead())
      {  this.resetAttack();
         this.setBeHereCoordinates(this.beHereX, this.beHereY);
      } else if (this.isAttackingBuilding() && !this.bTarget.isBuildingDead())
      {  boolean attackingDistance = this.isInAttackingDistance();
         boolean correctAngle = this.isTurretOrBodyAngleCorrectForAttack();
         return (attackingDistance && correctAngle);
      }  else if (this.isAttackingBuilding() && this.bTarget.isBuildingDead())
      {  this.resetAttack();
         this.setBeHereCoordinates(this.beHereX, this.beHereY);
      }

      return false;
   }

   protected void doAttack()
   {  if (this.model.getTime() - this.lastFireTime >= this.rateOfFire)
      {  this.createProjectile();
         this.resetFireTime();
         this.resetTemporarilyBlocked();
      }
   }

   protected void getAttackedStatus()
   {  if (this.previousHP != this.currentHP && this.ownerTag == 0 && !this.docked && !this.name.equals(Constants.HARVESTOR))
      {  this.model.alertPlayerUnitUnderAttack(this.currentX, this.currentY);
      }
   }

   protected void resetFireTime()
   {  this.lastFireTime = this.model.getTime();
   }

   public void doAttacking()
   {  this.refreshTargetInformation();

      if (this.canAttackTarget())
      {  this.doAttack();
      } else if (this.getIsAttacking() && !this.isInAttackingDistance() && this.status.equals(Constants.STOP))
      {  this.determineNextCombatAction(); //get in combat range
      }
      if (this.getIsAttacking() && !this.isTurretOrBodyAngleCorrectForAttack())
      {  this.correctTurretOrBodyAngleForAttack();
      }
   }

   protected boolean isMovingAndStopped()
   {  return (this.status.equals(Constants.STOP) && this.isMoving());
   }

   public boolean isMoving()
   {  return (this.beHereX >= 0 && this.beHereY >= 0);
   }

   protected boolean isAttacking()
   {  return (this.getIsAttacking() && this.status.equals(Constants.STOP) && this.attackX >= 0 && this.attackY >= 0);
   }

   public boolean isMovingAndAttacking()
   {  return (this.isMovingAndAttacking);
   }

   public void setIsMovingAndAttacking()
   {  this.isMovingAndAttacking = true;
   }

   public void resetIsMovingAndAttacking()
   {  this.isMovingAndAttacking = false;
   }

   protected void determineNextMovementAction()
   {
      //if (!this.canAttackTarget() && this.getIsAttacking())
      //{  this.reestablishTargetAttackAt(this.target.getCurrentX(), this.target.getCurrentY());
      //}
      if (this.beHereX >= 0 && this.beHereY >= 0 && this.hasValidPath())//&& !this.canAttackTarget())
      {  int moveX = this.path[0][this.pathIndex] - this.currentX;
         int moveY = this.path[1][this.pathIndex] - this.currentY;
         if (moveX < 0 && moveY < 0)
         {  if (this.upLeftClear(this.currentX, this.currentY))
            {  this.moveUpLeft();
            } else if (this.upLeftClearForHarvestor(this.currentX, this.currentY))
            {  this.moveUpLeft();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX-1, this.currentY-1);
               this.findPath(this.beHereX, this.beHereY);
            }
         } else if (moveX == 0 && moveY < 0)
         {  if (this.upClear(this.currentX, this.currentY))
            {  this.moveUp();
            } else if (this.upClearForHarvestor(this.currentX, this.currentY))
            {  this.moveUp();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX, this.currentY-1);
               this.findPath(this.beHereX, this.beHereY);
            }
         }  else if (moveX > 0 && moveY < 0)
         {  if (this.upRightClear(this.currentX, this.currentY))
            {  this.moveUpRight();
            } else if (this.upRightClearForHarvestor(this.currentX, this.currentY))
            {  this.moveUpRight();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX+1, this.currentY-1);
               this.findPath(this.beHereX, this.beHereY);
            }
         }  else if (moveX > 0 && moveY == 0)
         {  if (this.rightClear(this.currentX, this.currentY))
            {  this.moveRight();
            } else if (this.rightClearForHarvestor(this.currentX, this.currentY))
            {  this.moveRight();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX+1, this.currentY);
               this.findPath(this.beHereX, this.beHereY);
            }
         }  else if (moveX > 0 && moveY > 0)
         {  if (this.downRightClear(this.currentX, this.currentY))
            {  this.moveDownRight();
            } else if (this.downRightClearForHarvestor(this.currentX, this.currentY))
            {  this.moveDownRight();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX+1, this.currentY+1);
               this.findPath(this.beHereX, this.beHereY);
            }
         }  else if (moveX == 0 && moveY > 0)
         {  if (this.downClear(this.currentX, this.currentY))
            {  this.moveDown();
            } else if (this.downClearForHarvestor(this.currentX, this.currentY))
            {  this.moveDown();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX, this.currentY+1);
               this.findPath(this.beHereX, this.beHereY);
            }
         }  else if (moveX < 0 && moveY > 0)
         {  if (this.downLeftClear(this.currentX, this.currentY))
            {  this.moveDownLeft();
            } else if (this.downLeftClearForHarvestor(this.currentX, this.currentY))
            {  this.moveDownLeft();
            }  else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX-1, this.currentY+1);
               this.findPath(this.beHereX, this.beHereY);
            }
         }  else if (moveX < 0 && moveY == 0)
         {  if (this.leftClear(this.currentX, this.currentY))
            {  this.moveLeft();
            } else if (this.leftClearForHarvestor(this.currentX, this.currentY))
            {  this.moveLeft();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX-1, this.currentY);
               this.findPath(this.beHereX, this.beHereY);
            }
         }
      }
   }

   protected boolean pathIsBlocked()
   {  if (this.hasValidPath())
      {  if (this.pathIndex == this.path[0].length)
         {  System.out.println("---BEGIN ERROR REPORT---");
            System.out.println("Error Type: Unit, " + this.name + ", has already reached its destination, (" + this.gotoX + ", " + this.gotoY + ").");
            System.out.println("Name: " + this.name);
            System.out.println("Current XY: " + this.currentX + ", " + this.currentY);
            System.out.println("pathIndex: " + this.pathIndex);
            System.out.println("---END ERROR REPORT---");
            //this.model.pauseGame();
            return true;

         }
         return (!this.coordinatesClear(this.path[0][this.pathIndex], this.path[1][this.pathIndex]));
      } else
      {  return true;
      }
   }

   protected void determineNextCombatAction()
   {
      if (!this.canAttackTarget() && this.getIsAttacking() && this.pathIsBlocked())
      {  this.reestablishTargetAttack();
      }  else if (this.isAttackingUnit() && this.uTarget.getCurrentX() != this.attackX && this.uTarget.getCurrentY() != this.attackY)
      {  this.reestablishTargetAttack();
      }  else if (this.isAttackingBuilding() && this.bTarget.getX() != this.attackX && this.bTarget.getY() != this.attackY)
      {  this.reestablishTargetAttack();
      }

      if (this.attackX >= 0 && this.attackY >= 0 && this.hasValidPath() && !this.canAttackTarget())
      {  int moveX = this.path[0][this.pathIndex] - this.currentX;
         int moveY = this.path[1][this.pathIndex] - this.currentY;
         if (moveX < 0 && moveY < 0)
         {  if (this.upLeftClear(this.currentX, this.currentY))
            {  this.moveUpLeft();
            } else if (this.upLeftClearForHarvestor(this.currentX, this.currentY))
            {  this.moveUpLeft();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX-1, this.currentY-1);
               this.findPath(this.attackX, this.attackY);
            }
         } else if (moveX == 0 && moveY < 0)
         {  if (this.upClear(this.currentX, this.currentY))
            {  this.moveUp();
            } else if (this.upClearForHarvestor(this.currentX, this.currentY))
            {  this.moveUp();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX, this.currentY-1);
               this.findPath(this.attackX, this.attackY);
            }
         }  else if (moveX > 0 && moveY < 0)
         {  if (this.upRightClear(this.currentX, this.currentY))
            {  this.moveUpRight();
            } else if (this.upRightClearForHarvestor(this.currentX, this.currentY))
            {  this.moveUpRight();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX+1, this.currentY-1);
               this.findPath(this.attackX, this.attackY);
            }
         }  else if (moveX > 0 && moveY == 0)
         {  if (this.rightClear(this.currentX, this.currentY))
            {  this.moveRight();
            } else if (this.rightClearForHarvestor(this.currentX, this.currentY))
            {  this.moveRight();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX+1, this.currentY);
               this.findPath(this.attackX, this.attackY);
            }
         }  else if (moveX > 0 && moveY > 0)
         {  if (this.downRightClear(this.currentX, this.currentY))
            {  this.moveDownRight();
            } else if (this.downRightClearForHarvestor(this.currentX, this.currentY))
            {  this.moveDownRight();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX+1, this.currentY+1);
               this.findPath(this.attackX, this.attackY);
            }
         }  else if (moveX == 0 && moveY > 0)
         {  if (this.downClear(this.currentX, this.currentY))
            {  this.moveDown();
            } else if (this.downClearForHarvestor(this.currentX, this.currentY))
            {  this.moveDown();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX, this.currentY+1);
               this.findPath(this.attackX, this.attackY);
            }
         }  else if (moveX < 0 && moveY > 0)
         {  if (this.downLeftClear(this.currentX, this.currentY))
            {  this.moveDownLeft();
            } else if (this.downLeftClearForHarvestor(this.currentX, this.currentY))
            {  this.moveDownLeft();
            }  else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX-1, this.currentY+1);
               this.findPath(this.attackX, this.attackY);
            }
         }  else if (moveX < 0 && moveY == 0)
         {  if (this.leftClear(this.currentX, this.currentY))
            {  this.moveLeft();
            } else if (this.leftClearForHarvestor(this.currentX, this.currentY))
            {  this.moveLeft();
            } else
            {  this.resetPath();
               this.addTemporarilyBlockedCoordinates(this.currentX-1, this.currentY);
               this.findPath(this.attackX, this.attackY);
            }
         }
      }
   }

   protected boolean isTemporarilyBlocked(int x, int y)
   {  for (int i=0; i < this.numBlocked; i++)
      {  if (this.blocked[0][i] == x && this.blocked[1][i] == y)
         {
            return true;
         }
      }

      return false;
   }

   protected void addTemporarilyBlockedCoordinates(int x, int y)
   {  if (this.blocked == null)
      {  this.increaseTempBlockedSize();
      } else if (this.blocked[0].length == this.numBlocked)
      {  this.increaseTempBlockedSize();
      }
      this.blocked[0][this.numBlocked] = x;
      this.blocked[1][this.numBlocked] = y;
      this.numBlocked++;
   }

   protected void increaseTempBlockedSize()
   {  int[][] temp = null;
      if (this.numBlocked != 0)
      {  temp = new int[2][this.numBlocked * 2];
      } else
      {  temp = new int[2][1];
      }
      for (int i=0; i < this.numBlocked; i++)
      {  temp[0][i] = this.blocked[0][i];
         temp[1][i] = this.blocked[1][i];
      }

      this.blocked = temp;
   }

   protected void resetTemporarilyBlocked()
   {  this.blocked = null;
      this.numBlocked = 0;
   }

   protected void findPath(int finishX, int finishY)
   {  //System.out.println(this.name + ": findPath to: " + finishX + ", " + finishY);
      int currentX = this.currentX;
      int currentY = this.currentY;
      if (this.inTransitChange)
      {  currentX = this.gotoX;
         currentY = this.gotoY;
      }
      Node startNode = new Node(currentX, currentY, this.findGoalDistance(finishX, finishY, currentX, currentY));
      OpenNodes open = new OpenNodes();
      ClosedNodes closed = new ClosedNodes();

      open.addNode(startNode);
      while(open.getNumOfNodes() != 0)
      {
         Node node = open.getNextNode();
         open.removeNode(node);
         closed.addNode(node);
         this.generateSuccessorNodes(open, closed, node, finishX, finishY);
         //closed.addNode(node);
      }

      open.resetNodes();
      this.createPath(closed.getNodes(), closed.getNumOfNodes());
      closed.resetNodes();
   }

   protected void generateSuccessorNodes(OpenNodes open, ClosedNodes closed, Node node, int finishX, int finishY)
   {  int x = node.getX();
      int y = node.getY();
      //Once the finishing node has been found, set this to true
      boolean foundFinish = false;

      //CHECK FOR ALL THE SUCCESSOR NODES OF THE CURRENT NODE

      //check LEFT
      if(x > 0 && !foundFinish && !this.isTemporarilyBlocked(x-1, y) && !this.isABuildingAt(x-1, y, 1, 1, finishX, finishY) && this.model.isLandEnterable(x-1, y))
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x-1, y);
         Node addNode = new Node(x-1, y, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x-1, y, addNode.getF()) && !closed.checkForNode(x-1, y))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x-1, y, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }    */
      } else //CHECK IF BUILDING IS REFINERY SO THAT HARVESTORS CAN RETURN
      if(x > 0 && !foundFinish && !this.isTemporarilyBlocked(x-1, y) && this.isABuildingAt(x-1, y, 1, 1, finishX, finishY) && this.model.getBuildingAt(x-1, y) != null && this.model.getBuildingAt(x-1, y).getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR) && this.holdingCredits > 0)
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x-1, y);
         Node addNode = new Node(x-1, y, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x-1, y, addNode.getF()) && !closed.checkForNode(x-1, y))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         }/* else if (closed.checkForNode(x-1, y, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }  */
      }

      //check RIGHT
      if(x < this.model.getMapX()-1 && !foundFinish && !this.isTemporarilyBlocked(x+1, y) && !this.isABuildingAt(x+1, y, 1, 1, finishX, finishY) && this.model.isLandEnterable(x+1, y))
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x+1, y);
         Node addNode = new Node(x+1, y, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x+1, y, addNode.getF()) && !closed.checkForNode(x+1, y))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x+1, y, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      } else //REFINERY CHECK FOR HARVESTORS
      if(x < this.model.getMapX()-1 && !foundFinish && !this.isTemporarilyBlocked(x+1, y) && this.isABuildingAt(x+1, y, 1, 1, finishX, finishY) && this.model.getBuildingAt(x+1, y) != null && this.model.getBuildingAt(x+1, y).getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR) && this.holdingCredits > 0)
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x+1, y);
         Node addNode = new Node(x+1, y, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x+1, y, addNode.getF()) && !closed.checkForNode(x+1, y))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x+1, y, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      }

      //check UP
      if(y > 0 && !foundFinish && !this.isTemporarilyBlocked(x, y-1) && !this.isABuildingAt(x, y-1, 1, 1, finishX, finishY) && this.model.isLandEnterable(x, y-1))
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x, y-1);

         Node addNode = new Node(x, y-1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x, y-1, addNode.getF()) && !closed.checkForNode(x, y-1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         }/* else if (closed.checkForNode(x, y-1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }  */
      } else //REFINERY CHECK FOR HARVESTORS
      if(y > 0 && !foundFinish && !this.isTemporarilyBlocked(x, y-1) && this.isABuildingAt(x, y-1, 1, 1, finishX, finishY) && this.model.getBuildingAt(x, y-1) != null && this.model.getBuildingAt(x, y-1).getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR) && this.holdingCredits > 0)
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x, y-1);

         Node addNode = new Node(x, y-1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x, y-1, addNode.getF()) && !closed.checkForNode(x, y-1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x, y-1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      }

      //check DOWN
      if(y < this.model.getMapY()-1 && !foundFinish && !this.isTemporarilyBlocked(x, y+1) && !this.isABuildingAt(x, y+1, 1, 1, finishX, finishY) && this.model.isLandEnterable(x, y+1))
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x, y+1);
         Node addNode = new Node(x, y+1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x, y+1, addNode.getF()) && !closed.checkForNode(x, y+1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         }/* else if (closed.checkForNode(x, y+1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }  */
      } else //REFINERY CHECK FOR HARVESTORS
      if(y < this.model.getMapY()-1 && !foundFinish && !this.isTemporarilyBlocked(x, y+1) && this.isABuildingAt(x, y+1, 1, 1, finishX, finishY) && this.model.getBuildingAt(x, y+1) != null && this.model.getBuildingAt(x, y+1).getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR) && this.holdingCredits > 0)
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x, y+1);
         Node addNode = new Node(x, y+1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x, y+1, addNode.getF()) && !closed.checkForNode(x, y+1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         }/* else if (closed.checkForNode(x, y+1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }  */
      }

      //check UP LEFT
      if(x > 0 && y > 0 && !foundFinish && !this.isTemporarilyBlocked(x-1, y-1) && !this.isABuildingAt(x-1, y-1, 1, 1, finishX, finishY) && this.model.isLandEnterable(x-1, y-1))
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x-1, y-1);
         Node addNode = new Node(x-1, y-1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x-1, y-1, addNode.getF()) && !closed.checkForNode(x-1, y-1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x-1, y-1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      } else
      if(x > 0 && y > 0 && !foundFinish && !this.isTemporarilyBlocked(x-1, y-1) && this.isABuildingAt(x-1, y-1, 1, 1, finishX, finishY) && this.model.getBuildingAt(x-1, y-1) != null && this.model.getBuildingAt(x-1, y-1).getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR) && this.holdingCredits > 0)
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x-1, y-1);
         Node addNode = new Node(x-1, y-1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x-1, y-1, addNode.getF()) && !closed.checkForNode(x-1, y-1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x-1, y-1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      }

      //check UP RIGHT
      if(x < this.model.getMapX()-1 && y > 0 && !foundFinish && !this.isTemporarilyBlocked(x+1, y-1) && !this.isABuildingAt(x+1, y-1, 1, 1, finishX, finishY) && this.model.isLandEnterable(x+1, y-1))
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x+1, y-1);
         Node addNode = new Node(x+1, y-1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x+1, y-1, addNode.getF()) && !closed.checkForNode(x+1, y-1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         }/* else if (closed.checkForNode(x+1, y-1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }  */
      } else  //REFINERY CHECK FOR HARVESTORS
      if(x < this.model.getMapX()-1 && y > 0 && !foundFinish && !this.isTemporarilyBlocked(x+1, y-1) && this.isABuildingAt(x+1, y-1, 1, 1, finishX, finishY) && this.model.getBuildingAt(x+1, y-1) != null && this.model.getBuildingAt(x+1, y-1).getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR) && this.holdingCredits > 0)
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x+1, y-1);
         Node addNode = new Node(x+1, y-1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x+1, y-1, addNode.getF()) && !closed.checkForNode(x+1, y-1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x+1, y-1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      }

      //check DOWN LEFT
      if(x > 0 && y < this.model.getMapY()-1 && !foundFinish && !this.isTemporarilyBlocked(x-1, y+1) && !this.isABuildingAt(x-1, y+1, 1, 1, finishX, finishY) && this.model.isLandEnterable(x-1, y+1))
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x-1, y+1);
         Node addNode = new Node(x-1, y+1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x-1, y+1, addNode.getF()) && !closed.checkForNode(x-1, y+1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x-1, y+1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      } else //REFINERY CHECK FOR HARVESTORS
      if(x > 0 && y < this.model.getMapY()-1 && !foundFinish && !this.isTemporarilyBlocked(x-1, y+1) && this.isABuildingAt(x-1, y+1, 1, 1, finishX, finishY) && this.model.getBuildingAt(x-1, y+1) != null && this.model.getBuildingAt(x-1, y+1).getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR) && this.holdingCredits > 0)
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x-1, y+1);
         Node addNode = new Node(x-1, y+1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x-1, y+1, addNode.getF()) && !closed.checkForNode(x-1, y+1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x-1, y+1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      }

      //check DOWN RIGHT
      if(x < this.model.getMapX()-1 && y < this.model.getMapY()-1 && !foundFinish && !this.isTemporarilyBlocked(x+1, y+1) && !this.isABuildingAt(x+1, y+1, 1, 1, finishX, finishY) && this.model.isLandEnterable(x+1, y+1))
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x+1, y+1);
         Node addNode = new Node(x+1, y+1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x+1, y+1, addNode.getF()) && !closed.checkForNode(x+1, y+1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x+1, y+1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      } else //REFINERY CHECK FOR HARVESTORS
      if(x < this.model.getMapX()-1 && y < this.model.getMapY()-1 && !foundFinish && !this.isTemporarilyBlocked(x-1, y) && this.isABuildingAt(x+1, y+1, 1, 1, finishX, finishY) && this.model.getBuildingAt(x+1, y+1) != null && this.model.getBuildingAt(x+1, y+1).getName().equals(Constants.REFINERY) && this.name.equals(Constants.HARVESTOR) && this.holdingCredits > 0)
      {  int goalDistance = this.findGoalDistance(finishX, finishY, x+1, y+1);
         Node addNode = new Node(x+1, y+1, goalDistance, node);
         if (addNode.getParentNode() == node && node.getParentNode() == addNode)
         {  System.err.println("A Infinite Loop Has Been Created.  Coordinates Are: " + addNode.getX() + ", " + addNode.getY());
            this.model.pauseGame();
         }

         if(!open.checkForNode(x+1, y+1, addNode.getF()) && !closed.checkForNode(x+1, y+1))
         {  open.addNode(addNode);

            if (goalDistance == 0)
            {
               closed.addNodes(open.getAllNodes());
               open.resetNodes();
               foundFinish = true;
            }
         } /*else if (closed.checkForNode(x+1, y+1, addNode.getF()))
         {
            closed.removeNode(addNode);
            open.addNode(addNode);
         }   */
      }
   }

   protected boolean isABuildingAt(int x, int y, int lengthX, int lengthY, int finishX, int finishY)
   {  if (this.isAttackingBuilding())
      {  if (this.bTarget.getX() == x && this.bTarget.getY() == y)
         {  return false;
         }
      } else if (this.isMovingAndAttacking())
      {  if (finishX == x && finishY == y)
         {  return false;
         }
      }

      return this.model.isABuildingAt(x, y, lengthX, lengthY);
   }

   protected int findGoalDistance(int finishX, int finishY, int startX, int startY)
   {  int distance = 0;
      while (startX != finishX || startY != finishY)
      {  if (finishX > startX)
         {  distance = distance + Constants.STRAIGHT_DISTANCE;
            startX = startX + 1;
         } else if (finishX < startX)
         {  distance = distance + Constants.STRAIGHT_DISTANCE;
            startX = startX - 1;
         } else if (finishY > startY)
         {  distance = distance + Constants.STRAIGHT_DISTANCE;
            startY = startY + 1;
         } else if (finishY < startY)
         {  distance = distance + Constants.STRAIGHT_DISTANCE;
            startY = startY - 1;
         }
      }

      return distance;
   }

   protected void createPath(Node[] nodes, int numNodes)
   {  CompiledList coordinateList = new CompiledList(nodes, numNodes);
      this.path = coordinateList.getCoordinates();
      int endCoordinate = coordinateList.getNumCoordinates()-1;

      //If path is created Successfully
      if (this.hasValidPath())
      {  if (this.getIsAttacking())
         {  this.attackX = this.path[0][endCoordinate];
            this.attackY = this.path[1][endCoordinate];
         } else
         {  this.beHereX = this.path[0][endCoordinate];
            this.beHereY = this.path[1][endCoordinate];
         }
         //this.numTimesFindPath = 0;
         //System.out.println(this.name + "reseting numTimes");
      } else //Path not created successfully
      {  this.numTimesFindPath++;
         //this.resetBeHereCoordinates();
      }

   }

   protected void resetPath()
   {  this.pathIndex = 1;
      this.path = null;
      //this.resetTemporarilyBlocked();
   }

   public void resetBeHereCoordinates()
   {  this.beHereX = -1;
      this.beHereY = -1;
   }

   public void doStop()
   {  this.resetBeHereCoordinates();
      this.resetAttack();
      this.resetIsMovingAndAttacking();
      this.resetPath();
      this.resetTemporarilyBlocked();
   }

   public void doTurning()
   {  this.progress = this.progress + this.speed;

      //DO LEFT_45 TURN
      if (this.status.equals(Constants.LEFT_45))
      {  this.bodyDegree = this.bodyDegree - this.speed;
         //IF DONE TURNING, FIX BODY ANGLE
         if (this.progress >= 45)
         {  this.progress = this.progress - 45;
            this.bodyDegree = this.bodyDegree + this.progress;
            this.progress = -1;
            this.status = Constants.STOP;
         }
      } else
      //DO RIGHT_45 TURN
      if (this.status.equals(Constants.RIGHT_45))
      {  this.bodyDegree = this.bodyDegree + this.speed;
         //IF DONE TURNING, FIX BODY ANGLE
         if (this.progress >= 45)
         {  this.progress = this.progress - 45;
            this.bodyDegree = this.bodyDegree - this.progress;
            this.progress = -1;
            this.status = Constants.STOP;
         }
      } else
      //DO LEFT TURN
      if (this.status.equals(Constants.LEFT))
      {  this.bodyDegree = this.bodyDegree - this.speed;
         //IF DONE TURNING, FIX BODY ANGLE
         if (this.progress >= 90)
         {  this.progress = this.progress - 90;
            this.bodyDegree = this.bodyDegree + this.progress;
            this.progress = -1;
            this.status = Constants.STOP;
         }
      } else
      //DO RIGHT TURN
      {  this.bodyDegree = this.bodyDegree + this.speed;
         //IF DONE TURNING, FIX BODY ANGLE
         if (this.progress >= 90)
         {  this.progress = this.progress - 90;
            this.bodyDegree = this.bodyDegree - this.progress;
            this.progress = -1;
            this.status = Constants.STOP;
         }
      }

      //MAKE SURE DEGREE IS A POSITION NUMBER
      this.fixBodyDegree();

      /*
      //DO NEXT ACTION IF APPLICABLE
      if (this.status.equals(Constants.STOP))
      {  this.determineNextAction();
      }
      */

   }

   protected void fixBodyDegree()
   { //MAKE SURE DEGREE IS A POSITION NUMBER
      if (this.bodyDegree < 0)
      {  this.bodyDegree = this.bodyDegree + 360;
      } else if (this.bodyDegree >= 360)
      {  this.bodyDegree = this.bodyDegree - 360;
      }
   }

   protected void fixTurretDegree()
   {  //MAKE SURE DEGREE IS A POSITION NUMBER
      if (this.turretDegree < 0)
      {  this.turretDegree = this.turretDegree + 360;
      } else if (this.turretDegree >= 360)
      {  this.turretDegree = this.turretDegree - 360;
      }
   }

   public void setAttackAt(int x, int y, Unit target)
   {  this.forceTarget(target);
      //this.setBeHereCoordinates(x, y);


      //reset the already established A* path
      this.resetPath();
      this.resetTemporarilyBlocked();
      this.resetBeHereCoordinates();
      if (this.status.equals(Constants.MOVE))
      {  this.inTransitChange = true;
      }

      //Use A* pathfinding to find a route to destination
      this.findPath(this.attackX, this.attackY);

   }

   public void setAttackAt(int x, int y, Building target)
   {  this.forceTarget(target);
      //this.setBeHereCoordinates(x, y);


      //reset the already established A* path
      this.resetPath();
      this.resetTemporarilyBlocked();
      this.resetBeHereCoordinates();
      if (this.status.equals(Constants.MOVE))
      {  this.inTransitChange = true;
      }

      //Use A* pathfinding to find a route to destination
      this.findPath(this.attackX, this.attackY);

   }

   protected void reestablishTargetAttack()
   {  this.refreshTargetInformation();

      //reset the already established A* path
      this.resetPath();
      if (this.status.equals(Constants.MOVE))
      {  this.inTransitChange = true;
      }

      //Use A* pathfinding to find a route to destination
      this.findPath(this.attackX, this.attackY);
   }

   protected void refreshTargetInformation()
   {  if (this.isAttackingBuilding())
      {  this.attackX = this.bTarget.getX();
         this.attackY = this.bTarget.getY();
      } else if (this.isAttackingUnit())
      {  this.attackX = this.uTarget.getCurrentX();
         this.attackY = this.uTarget.getCurrentY();
      }
   }

   protected void forceTarget(Unit target)
   {  this.uTarget = target;
      this.isAttacking = true;
      this.attackX = target.getCurrentX();
      this.attackY = target.getCurrentY();
   }

   protected void forceTarget(Building target)
   {  this.bTarget = target;
      this.isAttacking = true;
      this.attackX = target.getX();
      this.attackY = target.getY();
   }

   public void provideTarget(Unit target)
   {  if (this.uTarget == null && this.bTarget == null && !this.isAttacking)
      {  this.forceTarget(target);
         //System.out.println(this.name + " has been provided a target: " + target.getName());
         //this.determineNextAction();
      } else if (!this.isAttacking)
      {  //System.out.println(this.name + " has been provided a target: null");
      }
   }

   public void provideTarget(Building target)
   {  if (this.bTarget == null && this.uTarget == null && !this.isAttacking)
      {  this.forceTarget(target);
         //System.out.println(this.name + " has been provided a target: " + target.getName());
      } else if (!this.isAttacking)
      {  //System.out.println(this.name + " has been provided a target: null");
      }
   }

   public void resetAttack()
   {  this.attackX = -1;
      this.attackY = -1;
      this.isAttacking = false;
      this.uTarget = null;
      this.bTarget = null;
   }

   protected void resetAttackCoordinates()
   {  this.attackX = -1;
      this.attackY = -1;
   }

   public void setBeHereCoordinates(int x, int y)
   {  if (x >=0 && y >= 0)
      {  if (this.status == Constants.HARVESTING)
         {  this.status = Constants.STOP;
         }

         //reset the already established A* path
         this.resetPath();
         this.resetTemporarilyBlocked();
         if (this.status.equals(Constants.MOVE))
         {  this.inTransitChange = true;
         }

         //Use A* pathfinding to find a route to destination
         this.findPath(x, y);

         if (this.status.equals(Constants.STOP))
         {  this.determineNextMovementAction();
         }
      }
   }

   protected boolean isUnitDoneMoving()
   {  return (this.currentX == this.beHereX && this.currentY == this.beHereY);
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

   public int getAttack()
   {  return this.attack;
   }

   public int getDefense()
   {  return this.defense;
   }

   public int getLengthX()
   {  return this.lengthX;
   }

   public int getWidthY()
   {  return this.widthY;
   }

   public int getDiagonalZ()
   {  return this.diagonalZ;
   }

   public int getSpeed()
   {  return this.speed;
   }

   public boolean getHasTurret()
   {  return this.hasTurret;
   }

   public String getAttackType()
   {  return this.attackType;
   }

   public String getDefenseType()
   {  return this.defenseType;
   }

   public double getRateOfFire()
   {  return this.rateOfFire;
   }

   public int getBodyDegree()
   {  return this.bodyDegree;
   }

   public int getTurretDegree()
   {  return this.turretDegree;
   }

   public int getCurrentX()
   {  return this.currentX;
   }

   public int getCurrentY()
   {  return this.currentY;
   }

   public int getGotoX()
   {  return this.gotoX;
   }

   public int getGotoY()
   {  return this.gotoY;
   }

   public int getBeHereX()
   {  return this.beHereX;
   }

   public int getBeHereY()
   {  return this.beHereY;
   }

   public int getProgress()
   {  return this.progress;
   }

   public int getAttackX()
   {  return this.attackX;
   }

   public int getAttackY()
   {  return this.attackY;
   }

   public String getStatus()
   {  return this.status;
   }

   public String getProjectileType()
   {  return this.projectileType;
   }

   public String getExplosionType()
   {  return this.explosionType;
   }

   public int getCost()
   {  return this.cost;
   }

   public int getBuildTime()
   {  return this.buildTime;
   }

   public boolean hasTurret()
   {  return this.hasTurret;
   }

   public int getHoldingCredits()
   {  return this.holdingCredits;
   }

   public String getJob()
   {  return this.job;
   }

   public void setDocked()
   {  this.docked = true;
   }

   public void setNotDocked()
   {  this.docked = false;
   }

   public boolean getDocked()
   {  return this.docked;
   }

   public void setIsHarvesting()
   {  this.isHarvesting = true;
   }

   public void setIsNotHarvesting()
   {  this.isHarvesting = false;
   }

   public boolean getIsHarvesting()
   {  return this.isHarvesting;
   }

   public void changeHoldingCredits(int amount)
   {  this.holdingCredits = this.holdingCredits + amount;
      if (this.holdingCredits < 0)
      {  this.holdingCredits = 0;
      }
   }

   public void setCurrentXY(int x, int y)
   {
      this.currentX = x;
      this.currentY = y;
      this.resetUnit();
   }

   protected void resetUnit()
   {  this.gotoX = -1;
      this.gotoY = -1;
      this.resetBeHereCoordinates();
      this.bodyDegree = 0;
      this.resetProgress();
      this.status = Constants.STOP;
      if (this.turretDegree != -1)
      {  this.turretDegree = 0;
      }
   }

   private void setBodyDegree(int a)
   {  this.bodyDegree = a;
      if (this.bodyDegree >= 360)
      {  this.bodyDegree = 0;
      }
   }

   public void resetProgress()
   {  this.progress = 0;
   }

   private void setGotoX(int x)
   {  this.gotoX = x;
   }

   private void setGotoY(int y)
   {  this.gotoY = y;
   }

   public void setStatusHarvesting()
   {  this.status = Constants.HARVESTING;
   }

   public void setStatusStop()
   {  this.status = Constants.STOP;
   }

   public void setStatusMove()
   {  this.status = Constants.MOVE;
   }

   public void setStatusRight()
   {  this.status = Constants.RIGHT;
   }

   public void setStatusLeft()
   {  this.status = Constants.LEFT;
   }

   public void setStatusRight45()
   {  this.status = Constants.RIGHT_45;
   }

   public void setStatusLeft45()
   {  this.status = Constants.LEFT_45;
   }

   public double getAttackDistance()
   {  return this.attackDistance;
   }

   public int getHPFillSpace(int fillSpace)
   {  double temp = (double)this.currentHP / (double)this.totalHP;
      return (int)(temp * fillSpace);
   }

   public Unit getUnitTarget()
   {  return this.uTarget;
   }

   public Building getBuildingTarget()
   {  return this.bTarget;
   }

   public String getBuiltFrom()
   {  return this.builtFrom;
   }

   public int getWaveNumber()
   {  return this.waveNumber;
   }

   public int getPreviousHP()
   {  return this.previousHP;
   }

   protected void resetPreviousHP()
   {  this.previousHP = this.currentHP;
   }

   public double getLastFireTime()
   {  return this.lastFireTime;
   }

   public boolean getCanMove()
   {  return this.canMove;
   }
}
