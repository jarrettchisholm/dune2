package Dune2.Model;
import Dune2.Pathfinding.*;
import Dune2.Sound.*;
import Dune2.View.*;
import Dune2.Events.*;
import Dune2.TextDisplay.*;
import Dune2.Units.*;

import becker.io.TextInput;
import becker.io.TextOutput;
import becker.util.IView;
import becker.util.Utilities;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import java.applet.AudioClip;
import java.applet.Applet;
import java.net.URL;
import java.net.MalformedURLException;

public class World
{  private ImageInformation imageInfo;
   private boolean gamePaused = false;
   private double time;
   private double timeIncrement; //haven't really implemented this...basically, its how much time increases every round
   private JFrame gameFrame;//this holds the start JFrame
   private BuildingView buildingView;
   private MainMenuView mainMenuView;

   /***********/
   private int tempThreadNumber = 0;
   private Runnable[] runnable;
   private int numRunnable = 0;
   private Thread[] thread;
   private int numThread = 0;
   private boolean running = false;
   private boolean inBuilding = false;
   private boolean atMainMenu = true;
   private boolean placingBuilding = false;
   /***********/

   //RADAR variables
   private int viewHorizontal;
   private int viewHorizontalValue;
   private int viewVertical;
   private int viewVerticalValue;
   private int worldViewVertical;
   private int worldViewHorizontal;
   private boolean radarChanged = true;
   private boolean viewChanged = true;

   //SOUND info
   private SoundInformation soundInfo;
   private double unitUnderAttackTime;
   private double buildingUnderAttackTime;
   private double enemyApproachingTime;

   //unit/building under attack AND enemy approaching variables (used by the VIEW)
   private boolean unitUnderAttack;
   private int unitUnderAttackX;
   private int unitUnderAttackY;
   private int unitUnderAttackLength;
   private boolean buildingUnderAttack;
   private int buildingUnderAttackX;
   private int buildingUnderAttackY;
   private int buildingUnderAttackLength;
   private boolean enemyApproaching;
   private int enemyApproachingX;
   private int enemyApproachingY;
   private int enemyApproachingLength;

   //private String savedMapName;
   private String mapName;
   private String difficulty;

   //map info
   private int sizeX;
   private int sizeY;
   private Map map;
   //private String mapFileName;
   private boolean refreshMap = false;
   private int[][] changedMapCoordinates; //where map data has been changed
   private int numChangedMapCoordinates;

   //land database info
   private Lands lands = new Lands();
   private int numLand;

   //unit database info
   private Units units = new Units();

   //building database info
   private Buildings buildings = new Buildings();

   //projectile database info
   private Projectiles projectileInfo = new Projectiles();

   //Explosion database info
   private Explosions explosionInfo = new Explosions();

   //projectile info
   private Projectile[] projectiles;
   private int numProjectiles;

   //Explosion info
   private Explosion[] explosions;
   private int numExplosions;

   //player info
   private Player[] players;
   //private AI[] AI;
   private int numAI;

   //view info
   private IView[] views = new IView[Constants.MIN_VIEWS];
   private int numViews = 0;

   //EVENTS INFO
   private EventList eventList;
   private Locations locations;

   //TextDisplayer (accompanies EVENT INFO)
   private TextDisplayer textDisplayer = new TextDisplayer();

   //Selected Unit OR Building
   private Unit selectedUnit;
   private Unit[] selectedUnits;
   private int numSelectedUnits;
   private Building selectedBuilding;
   private boolean setUnitMoving;
   private boolean setUnitHarvest;
   private boolean setUnitAttack;

   private double highlighterTime;
   private int movementIndicatorTime;
   private int movementIndicatorX;
   private int movementIndicatorY;
   private int attackIndicatorTime;
   private int attackIndicatorX;
   private int attackIndicatorY;
   /*************************************************************************/

   public World(ImageInformation iInfo, SoundInformation sInfo, JFrame gameFrame)
   {  this.imageInfo = iInfo;
      this.soundInfo = sInfo;
      this.gameFrame = gameFrame;

      //FILENAME TEMPORARY
      //this.mapFileName = "Default.txt";
      //String fileName = Constants.SAVED_GAME_PATH + "Default.txt";
      //this.loadFile(fileName);

      this.runnable = new Runnable[1];
      this.thread = new Thread[1];
   }

   public void addView(IView view)
   {  if (this.views.length == this.numViews)
      {  this.increaseIViewSize();
      }

      this.views[this.numViews] = view;
      this.numViews++;
   }

   public void resetRunnableAndThread()
   {  this.numRunnable = 0;
      this.numThread = 0;
      this.runnable = new Runnable[1];
      this.thread = new Thread[1];
   }

   public void addRunnable(Runnable run)
   {  if (this.runnable.length == this.numRunnable)
      {  this.increaseRunnableSize();
      }

      this.runnable[this.numRunnable] = run;
      this.numRunnable++;
      this.thread[this.numThread] = new Thread(run);
      this.numThread++;
   }

   private void increaseRunnableSize()
   {  Runnable[] temp = new Runnable[this.numRunnable * 2];
      Thread[] tempT = new Thread[this.numThread * 2];
      for (int i=0; i < this.numRunnable; i++)
      {  temp[i] = this.runnable[i];
         tempT[i] = this.thread[i];
      }

      this.runnable = temp;
      this.thread = tempT;
   }

   private void increaseIViewSize()
   {  IView[] temp = new IView[this.numViews*2];
      for (int i=0; i < this.numViews; i++)
      {  temp[i] = this.views[i];
      }

      this.views = temp;
   }

   public void updateAllViews()
   {  for (int i=0; i < this.numViews; i++)
      {  this.views[i].updateView();
      }
   }

   public int getNumUnits(String house)
   {  if (house !=null)
      {  for (int i=0; i < this.numAI+1; i++)
         {  if (this.players[i].getHouse().equals(house))
            {  return this.players[i].getNumUnits();
            }
         }
      }

      return 0;
   }

   public int getNumBuildings(String house)
   {  if (house != null)
      {  for (int i=0; i < this.numAI+1; i++)
         {  if (this.players[i].getHouse().equals(house))
            {  return this.players[i].getNumBuildings();
            }
         }
      }

      return 0;
   }

   public Unit[] getUnits(String house)
   {  if (house !=null)
      {  for (int i=0; i < this.numAI+1; i++)
         {  if (this.players[i].getHouse().equals(house))
            {  return this.players[i].getUnits();
            }
         }
      }

      return null;
   }

   public Building[] getBuildings(String house)
   {  if (house != null)
      {  for (int i=0; i < this.numAI+1; i++)
         {  if (this.players[i].getHouse().equals(house))
            {  return this.players[i].getBuildings();
            }
         }
      }

      return null;
   }

   public synchronized void gameFlow()
   {  this.doRound();
      //this.setDoneRefreshingMap();
      this.updateAllViews();
      this.setDoneRefreshingMap();
      this.time = this.time + this.getTimeIncrement();
      this.highlighterTime = this.highlighterTime + 1000/Constants.GAME_SPEED;

      if (this.highlighterTime > 600.00)
      {  this.highlighterTime = this.highlighterTime - 600.00;
      }
   }

   public boolean isLandEnterable(int x, int y)
   {  return this.lands.isEnterable(this.map.getBaseLayerInfoAt(x, y));
   }

   public void doCheckOfAreaForUnit(int x, int y, int index)
   {  Unit attacker = this.getUnitAt(x, y, index);
      if (attacker != null && index == 0 && !attacker.getName().equals(Constants.HARVESTOR))
      {  //Check UP
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x, y-1, i);
            Building targetBuilding = this.getBuildingAt(x, y-1, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check DOWN
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x, y+1, i);
            Building targetBuilding = this.getBuildingAt(x, y+1, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check LEFT
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x-1, y, i);
            Building targetBuilding = this.getBuildingAt(x-1, y, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check RIGHT
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x+1, y, i);
            Building targetBuilding = this.getBuildingAt(x+1, y, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check UP RIGHT
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x+1, y-1, i);
            Building targetBuilding = this.getBuildingAt(x+1, y-1, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check UP LEFT
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x-1, y-1, i);
            Building targetBuilding = this.getBuildingAt(x-1, y-1, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check DOWN RIGHT
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x+1, y+1, i);
            Building targetBuilding = this.getBuildingAt(x+1, y+1, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check DOWN LEFT
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x-1, y+1, i);
            Building targetBuilding = this.getBuildingAt(x-1, y+1, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check UP UP
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x, y-2, i);
            Building targetBuilding = this.getBuildingAt(x, y-2, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check DOWN DOWN
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x, y+2, i);
            Building targetBuilding = this.getBuildingAt(x, y+2, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check RIGHT RIGHT
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x+2, y, i);
            Building targetBuilding = this.getBuildingAt(x+2, y, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }

         //Check LEFT LEFT
         for (int i=1; i < this.numAI+1; i++)
         {  Unit targetUnit = this.getUnitAt(x-2, y, i);
            Building targetBuilding = this.getBuildingAt(x-2, y, i);
            if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetUnit);
               }
               targetUnit.provideTarget(attacker);
            } else if (targetBuilding != null)
            {  if (attacker.isMovingAndAttacking())
               {  attacker.provideTarget(targetBuilding);
               }
            }
         }
      } else if (attacker != null && index != 0 && !attacker.getName().equals(Constants.HARVESTOR))
      {  //Check UP
         Unit targetUnit = this.getUnitAt(x, y-1, 0);
         Building targetBuilding = this.getBuildingAt(x, y-1, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check DOWN
         targetUnit = this.getUnitAt(x, y+1, 0);
         targetBuilding = this.getBuildingAt(x, y+1, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check LEFT
         targetUnit = this.getUnitAt(x-1, y, 0);
         targetBuilding = this.getBuildingAt(x-1, y, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check RIGHT
         targetUnit = this.getUnitAt(x+1, y, 0);
         targetBuilding = this.getBuildingAt(x+1, y, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check UP RIGHT
         targetUnit = this.getUnitAt(x+1, y-1, 0);
         targetBuilding = this.getBuildingAt(x+1, y-1, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check UP LEFT
         targetUnit = this.getUnitAt(x-1, y-1, 0);
         targetBuilding = this.getBuildingAt(x-1, y-1, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check DOWN RIGHT
         targetUnit = this.getUnitAt(x+1, y+1, 0);
         targetBuilding = this.getBuildingAt(x+1, y+1, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check DOWN LEFT
         targetUnit = this.getUnitAt(x-1, y+1, 0);
         targetBuilding = this.getBuildingAt(x-1, y+1, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check UP UP
         targetUnit = this.getUnitAt(x, y-2, 0);
         targetBuilding = this.getBuildingAt(x, y-2, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check DOWN DOWN
         targetUnit = this.getUnitAt(x, y+2, 0);
         targetBuilding = this.getBuildingAt(x, y+2, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check RIGHT RIGHT
         targetUnit = this.getUnitAt(x+2, y, 0);
         targetBuilding = this.getBuildingAt(x+2, y, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }

         //Check LEFT LEFT
         targetUnit = this.getUnitAt(x-2, y, 0);
         targetBuilding = this.getBuildingAt(x-2, y, 0);
         if (targetUnit != null && !targetUnit.getName().equals(Constants.HARVESTOR))
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetUnit);
            }
            targetUnit.provideTarget(attacker);
         } else if (targetBuilding != null)
         {  if (attacker.isMovingAndAttacking())
            {  attacker.provideTarget(targetBuilding);
            }
         }
      }
   }

   private void removeProjectile(Projectile remove)
   {  boolean found = false;
      for (int i=0; i < this.numProjectiles-1; i++)
      {  if (this.projectiles[i] == remove)
         {  found = true;
         }

         if (found)
         {  this.projectiles[i] = projectiles[i+1];
         }
      }

      this.numProjectiles--;
      this.projectiles[this.numProjectiles] = null;
   }

   public void createProjectileTargetUnit(Unit attacker, Unit target, int startX, int startY, int targetX, int targetY, double distance, double travelled, double degree, int damage, String name)
   {  if (this.projectiles.length == this.numProjectiles)
      {  this.increaseProjectilesSize();
      }

      this.soundInfo.playSound(name);

      Projectile projectile = this.projectileInfo.getProjectile(name);
      if (projectile != null)
      {  //System.out.println("Projectiles length: " + this.projectiles.length + ", " + this.numProjectiles);
         this.projectiles[this.numProjectiles] = new Projectile(projectile, damage, distance, travelled, startX, startY, degree, targetX, targetY);
         this.numProjectiles++;
      }

      if (target.isMovingAndAttacking() || !target.isMoving() && !target.getName().equals(Constants.HARVESTOR))
      {  target.provideTarget(attacker);
      }
   }

   public void createProjectileTargetBuilding(int startX, int startY, int targetX, int targetY, double distance, double travelled, double degree, int damage, String name)
   {  if (this.projectiles.length == this.numProjectiles)
      {  this.increaseProjectilesSize();
      }

      this.soundInfo.playSound(name);

      Projectile projectile = this.projectileInfo.getProjectile(name);
      if (projectile != null)
      {  this.projectiles[this.numProjectiles] = new Projectile(projectile, damage, distance, travelled, startX, startY, degree, targetX, targetY);
         this.numProjectiles++;
      }
   }

   private void increaseProjectilesSize()
   {  Projectile[] temp = new Projectile[this.numProjectiles * 2];
      for (int i=0; i < this.numProjectiles; i++)
      {  temp[i] = this.projectiles[i];
      }

      this.projectiles = temp;
   }

   public Projectile[] getProjectiles()
   {  return this.projectiles;
   }

   public int getNumProjectiles()
   {  return this.numProjectiles;
   }

   private void removeExplosion(Explosion remove)
   {  boolean found = false;
      for (int i=0; i < this.numExplosions-1; i++)
      {  if (this.explosions[i] == remove)
         {  found = true;
         }

         if (found)
         {  this.explosions[i] = explosions[i+1];
         }
      }

      this.numExplosions--;
      this.explosions[this.numExplosions] = null;
   }

   public void createExplosion(String name, int x, int y, double progress)
   {  if (this.explosions.length == this.numExplosions)
      {  this.increaseExplosionsSize();
      }

      this.soundInfo.playSound(name);
      Explosion explosion = this.explosionInfo.getExplosion(name);
      //System.out.println("explosions length: " + this.explosions.length + ", " + this.numExplosions);
      this.explosions[this.numExplosions] = new Explosion(explosion, x, y, progress);
      this.numExplosions++;
   }

   private void increaseExplosionsSize()
   {  Explosion[] temp = new Explosion[this.numExplosions * 2];
      for (int i=0; i < this.numExplosions; i++)
      {  temp[i] = this.explosions[i];
      }

      this.explosions = temp;
   }

   public Explosion[] getExplosions()
   {  return this.explosions;
   }

   public int getNumExplosions()
   {  return this.numExplosions;
   }

   public double getTime()
   {  return this.time;
   }

   public double getTimeIncrement()
   {  return (double)Constants.GAME_SPEED / 100.0;
   }

   private void doRound()
   {  //DO EVENTS
      this.eventList.doEvents();

      //TEXT DISPLAYER
      this.textDisplayer.doRound(this.time);

      //ALL AI DO ROUNDS
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].doRound();
      }

      //EXPLOSIONS
      for (int i=0; i < this.numExplosions; i++)
      {  this.explosions[i].doRound();
      }

      for (int i=0; i < this.numExplosions; i++)
      {  if (this.explosions[i].isExplosionDone())
         {  this.removeExplosion(this.explosions[i]);
            if (i != 0)
            {  i--;
            }
         }
      }

      //PROJECTILES
      for (int i=0; i < this.numProjectiles; i++)
      {  this.projectiles[i].doRound();
      }

      for (int i=0; i < this.numProjectiles; i++)
      {  if (this.projectiles[i].isAtTarget())
         {  this.createExplosion(this.projectiles[i].getHitName(), this.projectiles[i].getTargetX(), this.projectiles[i].getTargetY(), 1.0);
            this.soundInfo.playSound(this.projectiles[i].getHitName());
            for (int a=0; a < this.numAI+1; a++)
            {  Unit unit = this.getUnitAt(this.projectiles[i].getTargetX(), this.projectiles[i].getTargetY(), a);
               if (unit != null)
               {  this.players[a].unitTakeDamage(unit, this.projectiles[i].getDamage());
                  if (unit.isUnitDead() && this.selectedUnit == unit)
                  {  this.selectedUnit = null;
                  }
               } else
               {  Building building = this.getBuildingAt(this.projectiles[i].getTargetX(), this.projectiles[i].getTargetY(), a);
                  if (building != null)
                  {  this.players[a].buildingTakeDamage(building, this.projectiles[i].getDamage());
                     if (building.isBuildingDead() && this.selectedBuilding == building)
                     {  this.selectedBuilding = null;
                     }
                  }
               }
            }


            this.removeProjectile(this.projectiles[i]);
            if (i != 0)
            {  i--;
            }
         }
      }

       /*
      //IS BEING ATTACKED
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].resolveAttacked();
      }

      //MOVEMENT
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].resolveMovement();
      }

      //TURNING
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].resolveTurning();
      }

      //TURRET TURNING
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].resolveTurretTurning();
      }

      //ATTACKING
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].resolveAttacking();
      }

      //BUILDING LEVEL
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].resolveBuildingLevel();
      }

      //CHECK IF HARVESTOR IS ENTERING REFINERY
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].checkIfHarvestorIsEnteringRefinery();
      }

      //BUILDING HARVESTOR HOLDING TIME
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].resolveBuildingHarvestorHolding();
      }

      //BUILDING CONSTRUCTION
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].resolveBuildingConstruction();
      }

      //UNIT CONSTRUCTION
      for (int i=0; i < this.numAI+1; i++)
      {  this.players[i].resolveUnitConstruction();
      }
      */


      /****************************FOR PLAYERS***************************************************/

      for (int a=0; a < this.numAI+1; a++)
      {  Unit[] units = this.players[a].getUnits();
         int numUnits = this.players[a].getNumUnits();
         Building[] buildings = this.players[a].getBuildings();
         int numBuildings = this.players[a].getNumBuildings();

         //PLACING NEW UNITS
         for (int i=0; i < numBuildings; i++)
         {  if (buildings[i].getStatus().equals(Constants.MOVE) && buildings[i].getProgress() >= 100)
            {  Unit unit = this.units.getUnit(buildings[i].getCurrentConstruction());
               if (unit != null && unit.getCanMove())
               {  boolean unitMade = false;
                  //System.out.println(buildings[i].getX() + " : " + this.sizeX + " - " + buildings[i].getY() + " : " + this.sizeY);
                  if (buildings[i].getX() >= this.sizeX && buildings[i].getY() >= this.sizeY)
                  {  for (int j=-1; j < buildings[i].getLengthX()+1; j++)
                     {  for (int k=-1; k < buildings[i].getWidthY()+1; k++)
                       { if (!unitMade && buildings[i].getX() + j >= 0 && buildings[i].getY() + k >= 0 && !this.isABuildingAt(buildings[i].getX() + j, buildings[i].getY() + k, 1, 1) && this.map.areaClearAt(buildings[i].getX() + j, buildings[i].getY() + k) && !this.isAUnitAt(buildings[i].getX() + j, buildings[i].getY() + k))
                          {  this.players[a].addUnit(buildings[i].getX() + j, buildings[i].getY() + k, buildings[i].getUnitJob(), buildings[i].getWaveNumber(), unit);
                             buildings[i].resetUnitOrBuilding();  //was setCurrentConstruction()!
                             unitMade = true;
                          }
                       }
                     }
                  }

                  if (!unitMade)
                  {  //this.refundCredits();
                     this.refundCredits(buildings[i], a);
                     buildings[i].resetUnitOrBuilding();
                  }
               }
            }
         }

         //PLACING HARVESTOR UNIT(S)
         for (int i=0; i < numBuildings; i++)
         {  if (buildings[i].getHarvestorDocked() && buildings[i].getName().equals(Constants.REFINERY))
            {  for (int j=0; j < numUnits; j++)
               {  if (units[j].getName().equals(Constants.HARVESTOR) && units[j].getDocked() && buildings[i].getX() == units[j].getCurrentX() && buildings[i].getY() == units[j].getCurrentY())
                  {  if (units[j].getHoldingCredits() == 0 && buildings[i].getHarvestorHoldingTime() >= Constants.HARVESTOR_HOLDING_TIME)
                     {  boolean harvestorPlaced = false;
                        if (buildings[i].getX() >= this.sizeX && buildings[i].getY() >= this.sizeY)
                        {  for (int k=-1; k < buildings[i].getLengthX()+1; k++)
                           {  for (int l=-1; l < buildings[i].getWidthY()+1; l++)
                              {  if (!harvestorPlaced && buildings[i].getX() + k >= 0 && buildings[i].getY() + l >= 0 && !this.isABuildingAt(buildings[i].getX() + k, buildings[i].getY() + l, 1, 1) && this.map.areaClearAt(buildings[i].getX() + k, buildings[i].getY() + l) && !this.isAUnitAt(buildings[i].getX() + k, buildings[i].getY() + k))
                                 {  units[j].setCurrentXY(buildings[i].getX() + k, buildings[i].getY() + l);
                                    buildings[i].setNoHarvestorDocked();
                                    units[j].setNotDocked();
                                    harvestorPlaced = true;
                                    //Start to harvest Spice Again
                                    int[] coordinates = this.getClosestSpiceXY(units[j]);
                                    if (coordinates[0] != -1 && coordinates[1] != -1)
                                    {  units[j].setIsHarvesting();
                                       units[j].setBeHereCoordinates(coordinates[0], coordinates[1]);
                                    }
                                 }
                              }
                           }
                        }
                     }
                  } else if (units[j].getName().equals(Constants.HARVESTOR) && !units[j].getDocked() && buildings[i].getX() == units[j].getCurrentX() && buildings[i].getY() == units[j].getCurrentY())
                  {  boolean harvestorPlaced = false;
                     if (buildings[i].getX() >= this.sizeX && buildings[i].getY() >= this.sizeY)
                     {  for (int k=-1; k < buildings[i].getLengthX()+1; k++)
                        {  for (int l=-1; l < buildings[i].getWidthY()+1; l++)
                           {  if (!harvestorPlaced && !this.isABuildingAt(buildings[i].getX() + k, buildings[i].getY() + l, 1, 1) && this.map.areaClearAt(buildings[i].getX() + k, buildings[i].getY() + l) && !this.isAUnitAt(buildings[i].getX() + k, buildings[i].getY() + k))
                              {  units[j].setCurrentXY(buildings[i].getX() + k, buildings[i].getY() + l);
                                 buildings[i].setNoHarvestorDocked();
                                 units[j].setNotDocked();
                                 harvestorPlaced = true;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         //CHECKING IF HARVESTOR IS HARVESTING (in an area with spice)
         for (int i=0; i < numUnits; i++)
         {
            if (units[i].getName().equals(Constants.HARVESTOR) && units[i].getIsHarvesting() && this.map.getBaseSpiceInfoAt(units[i].getCurrentX(), units[i].getCurrentY()) <= 0 && this.map.getBaseLayerInfoAt(units[i].getCurrentX(), units[i].getCurrentY()).equals(Constants.SPICE) && (units[i].getStatus().equals(Constants.STOP) || units[i].getStatus().equals(Constants.HARVESTING)))
            {  if (units[i].getHoldingCredits() >= Constants.MAX_HOLDING_CREDITS) //Return to refinery if full
               {  this.unitReturnToRefinery(units[i], a);
               } else //Else goto next available spice field
               {  int[] coordinates = this.getClosestSpiceXY(units[i]);
                  units[i].setBeHereCoordinates(coordinates[0], coordinates[1]);
               }
               this.map.setBaseLayerInfoAtXYToSand(units[i].getCurrentX(), units[i].getCurrentY());
               this.addToMapChangedCoordinates(units[i].getCurrentX(), units[i].getCurrentY());
               //this.refreshMap = true;
            } else if (units[i].getName().equals(Constants.HARVESTOR) && units[i].getIsHarvesting() && this.map.getBaseSpiceInfoAt(units[i].getCurrentX(), units[i].getCurrentY()) <= 0 && this.map.getBaseLayerInfoAt(units[i].getCurrentX(), units[i].getCurrentY()).equals(Constants.SAND) && (units[i].getStatus().equals(Constants.STOP) || units[i].getStatus().equals(Constants.HARVESTING)))
            {  units[i].setIsNotHarvesting(); //if told to harvest on sand, stop harvesting
            } else if (units[i].getName().equals(Constants.HARVESTOR) && units[i].getIsHarvesting() && this.map.getBaseSpiceInfoAt(units[i].getCurrentX(), units[i].getCurrentY()) > 0 && units[i].getStatus().equals(Constants.STOP))
            {  units[i].setStatusHarvesting(); //if on a spice spot, start harvesting (if is supposed to)
            } else if (units[i].getName().equals(Constants.HARVESTOR) && units[i].getIsHarvesting() && this.map.getBaseSpiceInfoAt(units[i].getCurrentX(), units[i].getCurrentY()) > 0 && units[i].getStatus().equals(Constants.HARVESTING))
            {  this.map.setBaseSpiceInfoAt(units[i].getCurrentX(), units[i].getCurrentY(), -Constants.SPICE_HARVEST_RATE);
               units[i].changeHoldingCredits(Constants.SPICE_HARVEST_RATE); //Exchange credits between land and harvestor
            }
         }
      }
   }

   private void addToMapChangedCoordinates(int x, int y)
   {  int[][] temp = new int[2][this.numChangedMapCoordinates+1];

      for (int i=0; i < this.numChangedMapCoordinates; i++)
      {  temp[0][i] = this.changedMapCoordinates[0][i];
         temp[1][i] = this.changedMapCoordinates[1][i];
      }

      this.changedMapCoordinates = temp;
      this.changedMapCoordinates[0][this.numChangedMapCoordinates] = x;
      this.changedMapCoordinates[1][this.numChangedMapCoordinates] = y;
      this.numChangedMapCoordinates++;
   }

   public int getNumChangedMapCoordinates()
   {  return this.numChangedMapCoordinates;
   }

   public int[][] getChangedMapCoordinates()
   {  return this.changedMapCoordinates;
   }

   public void resetChangedMapCoordinates()
   {  this.numChangedMapCoordinates = 0;
      this.changedMapCoordinates = new int[2][0];
   }

   private int[] getClosestSpiceXY(Unit unit)
   {  //GET UNITS' COORDINATES
      double x1 = (double)unit.getCurrentX();
      double y1 = (double)unit.getCurrentY();

      //COUNT NUMBER OF SPICE FIELDS
      int numSpice = 0;
      for (int i=0; i < this.map.getX(); i++)
      {  for (int j=0; j < this.map.getY(); j++)
         {  if (this.map.getBaseLayerInfoAt(i, j).equals(Constants.SPICE) && this.coordinatesClear(i, j))
            {  numSpice++;
            }
         }
      }

      if (numSpice > 0)
      {  //FIND DISTANCES TO ALL SPICE FIELDS
         double[][] distances = new double[numSpice][2];
         double[] lengths = new double[numSpice];
         numSpice = 0;
         for (int i=0; i < this.map.getX(); i++)
         {  for(int j=0; j < this.map.getY(); j++)
            {  if (this.map.getBaseLayerInfoAt(i, j).equals(Constants.SPICE) && unit.getCurrentX() != i && unit.getCurrentY() != j && this.coordinatesClear(i, j))
               {  distances[numSpice][0] = (double)i;
                  distances[numSpice][1] = (double)j;
                  double yTotal = (double)(distances[numSpice][1] - y1) * (distances[numSpice][1] - y1);
                  double xTotal = (double)(distances[numSpice][0] - x1) * (distances[numSpice][0] - x1);
                  lengths[numSpice] = Math.sqrt(xTotal + yTotal);
                  numSpice++;
               }
            }
         }

         //DETERMINE THE SHORTEST DISTANCE TO A SPICE FIELD
         double toTake = lengths[0];
         int index = 0;
         for (int i=0; i < numSpice-1; i++)
         {  if (toTake > lengths[i + 1])
            {  toTake = lengths[i + 1];
               index = i+1;
            }
         }

         int[] temp = new int[2];
         temp[0] = (int)distances[index][0];
         temp[1] = (int)distances[index][1];

         return temp;
      }

      int[] temp = new int[2];
      temp[0] = -1;
      temp[1] = -1;
      return temp;
   }

   public boolean getRefreshMap()
   {  return this.refreshMap;
   }

   public void setDoneRefreshingMap()
   {  this.refreshMap = false;
   }

   public boolean isABuildingAt(int x, int y, int lengthX, int lengthY)
   {  //boolean flag = false;

      //CHECK PLAYERS' BUILDINGS
      for (int a=0; a < this.numAI+1; a++)
      {  Building[] buildings = this.players[a].getBuildings();
         int numBuildings = this.players[a].getNumBuildings();
         for (int i=0; i < numBuildings; i++)
         {  for (int j=0; j < buildings[i].getLengthX(); j++)
            {  for (int k=0; k < buildings[i].getWidthY(); k++)
               {  if (buildings[i].getX() + j == x && buildings[i].getY() + k == y && !buildings[i].getName().equals(Constants.SINGLE_SLAB) && !buildings[i].getName().equals(Constants.SLAB_4))
                  {  //flag = true;
                     return true;
                  }

                  for (int l=0; l < lengthX; l++) //CHECK FOR OVERLAPPING BUILDINGS (LENGTH/WIDTH > 1)
                  {  for (int m=0; m < lengthY; m++)
                     {  if (buildings[i].getX() + j == x + l && buildings[i].getY() + k == y + m && !buildings[i].getName().equals(Constants.SINGLE_SLAB) && !buildings[i].getName().equals(Constants.SLAB_4))
                        {  //lag = true;
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }

      //return flag;
      return false;
   }

   private boolean whenPlacingBuildingIsAUnitAt(int x, int y)
   {  int lengthX = 1;
      int lengthY = 1;
      if (this.selectedBuilding != null) //CHECKS FOR UNITS ALONG THE ENTIRE LENGTH/WIDTH OF THE BUILDING
      {  Building building = this.buildings.getBuilding(this.selectedBuilding.getCurrentConstruction());
         if(building != null)
         {  lengthX = building.getLengthX();
            lengthY = building.getWidthY();
         }
      }
      for (int a =0; a < this.numAI+1; a++)
      {  boolean flag = this.players[a].isAUnitAt(x, y, lengthX, lengthY);
         if (flag)
         {  return true;
         }
      }

      return false;
   }

   public boolean isAUnitAt(int x, int y)
   {  int lengthX = 1;
      int lengthY = 1;

      for (int a=0; a < this.numAI+1; a++)
      {  boolean flag = this.players[a].isAUnitAt(x, y, lengthX, lengthY);
         if (flag)
         {  return true;
         }
      }

      return false;
   }

   public Unit getUnitAt(int x, int y)
   {  int lengthX = 1;
      int lengthY = 1;

      for (int a=0; a < this.numAI+1; a++)
      {  Unit unit = this.players[a].getUnitAt(x, y);
         if (unit != null)
         {  return unit;
         }
      }

      return null;
   }

   public Unit getUnitAt(int x, int y, int index)
   {  int lengthX = 1;
      int lengthY = 1;

      Unit unit = this.players[index].getUnitAt(x, y);

      return unit;
   }

   public Unit getUnitAt(int x, int y, String house)
   {  int lengthX = 1;
      int lengthY = 1;

      for (int i=0; i < this.numAI+1; i++)
      {  if (this.players[i].getHouse().equals(house))
         {  return this.players[i].getUnitAt(x, y);
         }
      }

      return null;
   }

   public void saveFile(String fileName)
   {  if (fileName != null)
      {  TextOutput out = new TextOutput(fileName + ".sav");

         String savedMapName = "";
         for (int i=0; i < fileName.length(); i++)
         {  if (fileName.charAt(i) == '\\')
            {  savedMapName = "";
            } else if (fileName.charAt(i) == '.')
            {  break;
            } else
            {  savedMapName = savedMapName + fileName.charAt(i);
            }
         }

         //map info
         out.println(this.mapName);
         out.println(savedMapName + "Map.dsav");
         if (this.selectedUnit != null)
         {  out.println(this.selectedUnit.getCurrentX());
            out.println(this.selectedUnit.getCurrentY());
         } else if (this.selectedBuilding != null)
         {  out.println(this.selectedBuilding.getX());
            out.println(this.selectedBuilding.getY());
         } else
         {  out.println("-1");
            out.println("-1");
         }
         out.println(this.unitUnderAttackTime);
         out.println(this.buildingUnderAttackTime);
         out.println(this.enemyApproachingTime);
         out.println(this.difficulty);
         out.println(this.time);
         out.println(this.numAI);

         //projectiles
         out.println(" ");
         out.println(this.numProjectiles);
         out.println(" ");
         if (this.numProjectiles > 0)
         {  for (int i=0; i < this.numProjectiles; i++)
            {  out.println(this.projectiles[i].getName());
               out.println(this.projectiles[i].getDamage());
               out.println(this.projectiles[i].getDistance());
               out.println(this.projectiles[i].getProgress());
               out.println(this.projectiles[i].getStartX());
               out.println(this.projectiles[i].getStartY());
               out.println(this.projectiles[i].getDegree());
               out.println(this.projectiles[i].getTargetX());
               out.println(this.projectiles[i].getTargetY());
               out.println(" ");
            }
         }

         //explosions
         out.println(this.numExplosions);
         out.println(" ");
         if (this.numExplosions > 0 )
         {  for (int i=0; i < this.numExplosions; i++)
            {  out.println(this.explosions[i].getName());
               out.println(this.explosions[i].getX());
               out.println(this.explosions[i].getY());
               out.println(this.explosions[i].getProgress());
               out.println(" ");
            }
         }

         //Saving player and AI and MAP info

         out.println(this.players[0].getHouse());
         out.println(this.players[0].getNumBuildings());
         out.println(this.players[0].getNumUnits());
         out.println(this.players[0].getCredits());
         out.println(" ");

         Unit[] units = this.players[0].getUnits();
         Building[] buildings = this.players[0].getBuildings();
         for(int i=0; i < this.players[0].getNumUnits(); i++)
         {  out.println(units[i].getName());
            out.println(units[i].getCurrentHP());
            out.println(units[i].getPreviousHP());
            out.println(units[i].getBodyDegree());
            out.println(units[i].getTurretDegree());
            out.println(units[i].getCurrentX());
            out.println(units[i].getCurrentY());
            out.println(units[i].getGotoX());
            out.println(units[i].getGotoY());
            out.println(units[i].getBeHereX());
            out.println(units[i].getBeHereY());
            out.println(units[i].getAttackX());
            out.println(units[i].getAttackY());
            out.println(units[i].getIsAttacking());
            out.println(units[i].isMovingAndAttacking());
            out.println(units[i].getWaveNumber());
            out.println(units[i].getProgress());
            out.println(units[i].getLastFireTime());
            out.println(units[i].getDocked());
            out.println(units[i].getStatus());
            out.println(units[i].getJob());
            if (units[i].getName().equals(Constants.HARVESTOR))
            {  out.println(units[i].getHoldingCredits());
               out.println(units[i].getIsHarvesting());
            }
            out.println(" ");
         }

         for (int i=0; i < this.players[0].getNumBuildings(); i++)
         {  out.println(buildings[i].getName());
            out.println(buildings[i].getCurrentHP());
            out.println(buildings[i].getPreviousHP());
            out.println(buildings[i].getUnitJob());
            out.println(buildings[i].getCurrentConstruction());
            out.println(buildings[i].getPreviousConstruction());
            out.println(buildings[i].getWaveNumber());
            out.println(buildings[i].getProgress());
            out.println(buildings[i].getX());
            out.println(buildings[i].getY());
            out.println(buildings[i].getStatus());
            out.println(buildings[i].getBuildingLevel());
            if (buildings[i].getName().equals(Constants.REFINERY))
            {  out.println(buildings[i].getHarvestorDocked());
            }
            out.println(" ");
         }

         for (int j=1; j < this.numAI+1; j++)
         {  out.println(this.players[j].getWaveNumber());
            out.println(this.players[j].getHouse());
            out.println(this.players[j].getNumBuildings());
            out.println(this.players[j].getNumUnits());
            out.println(this.players[j].getCredits());
            out.println(" ");

            units = this.players[j].getUnits();
            buildings = this.players[j].getBuildings();
            for(int i=0; i < this.players[j].getNumUnits(); i++)
            {  out.println(units[i].getName());
               out.println(units[i].getCurrentHP());
               out.println(units[i].getPreviousHP());
               out.println(units[i].getBodyDegree());
               out.println(units[i].getTurretDegree());
               out.println(units[i].getCurrentX());
               out.println(units[i].getCurrentY());
               out.println(units[i].getGotoX());
               out.println(units[i].getGotoY());
               out.println(units[i].getBeHereX());
               out.println(units[i].getBeHereY());
               out.println(units[i].getAttackX());
               out.println(units[i].getAttackY());
               out.println(units[i].getIsAttacking());
               out.println(units[i].isMovingAndAttacking());
               out.println(units[i].getWaveNumber());
               out.println(units[i].getProgress());
               out.println(units[i].getLastFireTime());
               out.println(units[i].getDocked());
               out.println(units[i].getStatus());
               out.println(units[i].getJob());
               if (units[i].getName().equals(Constants.HARVESTOR))
               {  out.println(units[i].getHoldingCredits());
                  out.println(units[i].getIsHarvesting());
               }
               out.println(" ");

            }

            for (int i=0; i < this.players[j].getNumBuildings(); i++)
            {  out.println(buildings[i].getName());
               out.println(buildings[i].getCurrentHP());
               out.println(buildings[i].getPreviousHP());
               out.println(buildings[i].getUnitJob());
               out.println(buildings[i].getCurrentConstruction());
               out.println(buildings[i].getPreviousConstruction());
               out.println(buildings[i].getWaveNumber());
               out.println(buildings[i].getProgress());
               out.println(buildings[i].getX());
               out.println(buildings[i].getY());
               out.println(buildings[i].getStatus());
               out.println(buildings[i].getBuildingLevel());
               if (buildings[i].getName().equals(Constants.REFINERY))
               {  out.println(buildings[i].getHarvestorDocked());
               }
               out.println(" ");
            }
            out.println(" ");
         }
         out.close();

         out = new TextOutput(Constants.SAVED_GAME_PATH + savedMapName + "Map.dsav");
         for (int j=0; j < this.getMapY(); j++)
         {  for (int i=0; i < this.getMapX(); i++)
            {  out.print(this.map.getBaseSpiceInfoAt(i, j) + " ");
            }
            out.println();
         }

         //Events
         int numEvents = this.eventList.getNumEvents();
         Event[] events = this.eventList.getEvents();

         out.println("");
         out.println(numEvents);
         out.println("");

         for (int i=0; i < numEvents; i++)
         {  out.println(events[i].getName());
            int numConditions = events[i].getNumCondition();
            out.println(numConditions);
            int numActions = events[i].getNumAction();
            out.println(numActions);
            out.println(events[i].getIsActive());
            out.println("");

            Condition[] conditions = events[i].getConditions();
            Action[] actions = events[i].getActions();
            //conditions
            for (int j=0; j < numConditions; j++)
            {  out.println(conditions[j].getIdentifier());
               out.println(conditions[j].getModifier());
               out.println(conditions[j].getNumArguments());
               out.println(conditions[j].getNumStringArguments());
               out.println(conditions[j].getNumNumberArguments());
               for (int k=0; k < conditions[j].getNumStringArguments(); k++)
               {  out.println(conditions[j].getArguments()[k]);
               }
               for (int k=0; k < conditions[j].getNumNumberArguments(); k++)
               {  out.println(conditions[j].getNumberArguments()[k]);
               }

               out.println("");
            }

            //actions
            for (int j=0; j < numActions; j++)
            {  out.println(actions[j].getIdentifier());
               out.println(actions[j].getModifier());
               out.println(actions[j].getNumArguments());
               out.println(actions[j].getNumStringArguments());
               out.println(actions[j].getNumNumberArguments());
               for (int k=0; k < actions[j].getNumStringArguments(); k++)
               {  out.println(actions[j].getArguments()[k]);
               }
               for (int k=0; k < actions[j].getNumNumberArguments(); k++)
               {  out.println(actions[j].getNumberArguments()[k]);
               }

               out.println("");
            }
         }

         out.println("");
      }
   }

   public void newGame(String fileName)
   {  if (fileName != null)
      {  TextInput mapIn = new TextInput(fileName);
         this.mapName = mapIn.readLine();
         int sizeX = mapIn.readInt();
         mapIn.readLine();
         int sizeY = mapIn.readInt();
         mapIn.readLine();
         String savedMapNameFromFile = "";
         String mapSpecificationsFile = "";
         for (int i=0; i < this.mapName.length(); i++)
         {  if (this.mapName.charAt(i) != '.')
            {  savedMapNameFromFile = savedMapNameFromFile + this.mapName.charAt(i);
               mapSpecificationsFile = mapSpecificationsFile + this.mapName.charAt(i);
            } else
            {  break;
            }
         }

         savedMapNameFromFile = savedMapNameFromFile + "Map.map";
         mapSpecificationsFile = mapSpecificationsFile + "Specifications.spec";
         TextInput mapSpecIn = new TextInput(Constants.MAPS_PATH + mapSpecificationsFile);

         //System.out.println(this.mapName +", " + savedMapNameFromFile + ", " + mapSpecificationsFile);

         TextOutput out = new TextOutput(Constants.TEMP_PATH + this.mapName);
         out.println(this.mapName);
         out.println(savedMapNameFromFile);
         out.println("-1");
         out.println("-1");
         out.println("0.0");
         out.println("0.0");
         out.println("0.0");
         out.println(Constants.EASY);
         out.println("0.0");
         int numAI = mapSpecIn.readInt();
         mapSpecIn.readLine();
         mapSpecIn.readLine();
         out.println(numAI);
         out.println("");
         out.println("0");
         out.println("");
         out.println("0");
         out.println("");

         /* when reading unit/building data, the number of lines is hard coded
            and the data is simply transferred...it isn't interpreted.
         */
         //Doing Player
         out.println(mapSpecIn.readLine()); //house
         int numBuildings = mapSpecIn.readInt();
         mapSpecIn.readLine();
         int numUnits = mapSpecIn.readInt();
         mapSpecIn.readLine();
         out.println(numBuildings);
         out.println(numUnits);
         out.println(mapSpecIn.readInt()); //num credits
         mapSpecIn.readLine();
         mapSpecIn.readLine();
         out.println("");

         //units
         for (int i=0; i < numUnits; i++)
         {  String name = mapSpecIn.readLine();
            int loop = 20;
            if (name.equals(Constants.HARVESTOR))
            {  loop = 22;
            }

            out.println(name);
            for (int j=0; j < loop; j++)
            {  out.println(mapSpecIn.readLine());
            }
            mapSpecIn.readLine();
            out.println("");
         }

         //buildings
         for (int i=0; i < numBuildings; i++)
         {  String name = mapSpecIn.readLine();
            int loop = 11;
            if (name.equals(Constants.REFINERY))
            {  loop = 12;
            }

            out.println(name);
            for (int j=0; j < loop; j++)
            {  out.println(mapSpecIn.readLine());
            }

            mapSpecIn.readLine();
            out.println("");
         }

         //doing AI
         for (int i=0; i < numAI; i++)
         {  out.println(mapSpecIn.readLine()); // wave number
            out.println(mapSpecIn.readLine()); //house
            numBuildings = mapSpecIn.readInt();
            mapSpecIn.readLine();
            numUnits = mapSpecIn.readInt();
            mapSpecIn.readLine();
            out.println(numBuildings);
            out.println(numUnits);
            out.println(mapSpecIn.readLine()); //credits
            out.println("");
            mapSpecIn.readLine();

            //units
            for (int j=0; j < numUnits; j++)
            {  String name = mapSpecIn.readLine();
               int loop = 20;
               if (name.equals(Constants.HARVESTOR))
               {  loop = 22;
               }

               out.println(name);
               for (int k=0; k < loop; k++)
               {  out.println(mapSpecIn.readLine());
               }
               mapSpecIn.readLine();
               out.println("");
            }

            //buildings
            for (int j=0; j < numBuildings; j++)
            {  String name = mapSpecIn.readLine();
               int loop = 11;
               if (name.equals(Constants.REFINERY))
               {  loop = 12;
               }

               out.println(name);
               for (int k=0; k < loop; k++)
               {  out.println(mapSpecIn.readLine());
               }

               mapSpecIn.readLine();
               out.println("");
            }
         }
         //out.println("");


         //NOW create the 'mapname'Map.txt file...spice concentrations and events
         out.close();
         out = new TextOutput(Constants.TEMP_PATH + savedMapNameFromFile);

         //copy spice concentrations
         for (int i=0; i < sizeX; i++)
         {  for (int j=0; j < sizeY; j++)
            {  String read = mapIn.readToken();
               if (read.equals(Constants.SPICE))
               {  out.print("100 ");
               } else
               {  out.print("0 ");
               }
            }
            out.println("");
            mapIn.readLine();
         }
         out.println("");
         mapIn.readLine();

         //copy events
         int numLocations = mapIn.readInt();
         mapIn.readLine();
         mapIn.readLine();
         for (int i=0; i < numLocations; i++)
         {  for (int j=0; j < 5; j++)
            {  mapIn.readLine();
            }
            mapIn.readLine();
         }

         while(!mapIn.eofIsAvailable())
         {  out.println(mapIn.readLine());
         }

         out.close();

         TextInput mainIn = new TextInput(Constants.TEMP_PATH + mapName);
         TextInput savedMapIn = new TextInput(Constants.TEMP_PATH + savedMapNameFromFile);
         TextInput mapIn2= new TextInput(fileName);

         this.loadFile(mainIn, savedMapIn, mapIn2);
      }
   }

   private void loadFile(TextInput in, TextInput savedMapIn, TextInput mapIn)
   {  if (in != null && savedMapIn != null && mapIn != null)
      {
         this.mapName = in.readLine();
         String savedMapNameFromFile = in.readLine();
         int selX = in.readInt();
         in.readLine();
         int selY = in.readInt();
         in.readLine();
         this.unitUnderAttackTime = in.readDouble();
         in.readLine();
         this.buildingUnderAttackTime = in.readDouble();
         in.readLine();
         this.enemyApproachingTime = in.readDouble();
         in.readLine();
         this.difficulty = in.readLine();
         this.time = in.readDouble();
         in.readLine();
         this.numAI = in.readInt();
         in.readLine();
         in.readLine();

         //projectiles
         this.numProjectiles = in.readInt();
         in.readLine();
         in.readLine();
         if (this.numProjectiles == 0)
         {  this.projectiles = new Projectile[1];
         } else
         {  this.projectiles = new Projectile[this.numProjectiles];
         }
         for (int i=0; i < this.numProjectiles; i++)
         {  Projectile projectile = this.projectileInfo.getProjectile(in.readLine());
            int damage = in.readInt();
            in.readLine();
            double distance = in.readDouble();
            in.readLine();
            double travelled = in.readDouble();
            in.readLine();
            int startX = in.readInt();
            in.readLine();
            int startY = in.readInt();
            in.readLine();
            int degree = in.readInt();
            in.readLine();
            int targetX = in.readInt();
            in.readLine();
            int targetY = in.readInt();
            in.readLine();
            in.readLine();
            this.projectiles[i] = new Projectile(projectile, damage, distance, travelled, startX, startY, degree, targetX, targetY);
         }

         //explosions
         this.numExplosions = in.readInt();
         in.readLine();
         in.readLine();
         if (this.numExplosions == 0)
         {  this.explosions = new Explosion[1];
         } else
         {  this.explosions = new Explosion[this.numExplosions];
         }
         for (int i=0; i < this.numExplosions; i++)
         {  Explosion explosion = this.explosionInfo.getExplosion(in.readLine());
            int x = in.readInt();
            in.readLine();
            int y = in.readInt();
            in.readLine();
            double progress = in.readDouble();
            in.readLine();
            in.readLine();
            this.explosions[i] = new Explosion(explosion, x, y, progress);
         }

         //Making the players and map
         this.players = new Player[this.numAI+1];
         this.players[0] = new Player(in, 0, this.units, this.buildings, this.lands, this);
         for (int a=1; a < this.numAI+1; a++)
         {  int waveNumber = in.readInt();
            in.readLine();
            this.players[a] = new AI(in, a, this.units, this.buildings, this.lands, this.difficulty, waveNumber, this);
         }

         //TextInput savedMapIn = new TextInput(Constants.SAVED_GAME_PATH + savedMapNameFromFile);
         //TextInput mapNameIn = new TextInput(Constants.MAPS_PATH + this.mapName);
         this.map = new Map(mapIn, savedMapIn, this.lands);
         this.locations = new Locations(mapIn);
         EventProfiles eProfiles = new EventProfiles(this.locations, this);
         this.eventList = new EventList(savedMapIn, eProfiles, this);

         /*
         TextInput savedMapIn = new TextInput(Constants.SAVED_GAME_PATH + savedMapNameFromFile);
         TextInput mapNameIn = new TextInput(Constants.MAPS_PATH + this.mapName);
         this.map = new Map(mapNameIn, savedMapIn, this.lands);
         this.locations = new Locations(mapNameIn);
         EventProfiles eProfiles = new EventProfiles(this.locations, this);
         this.eventList = new EventList(savedMapIn, eProfiles, this);
         */
         /* When I am making a 'new game' and not loading a game, I must make a
            new method for it.  I also must send the eventList the SAME TextInput
            as the map (ie. I must send the eventList mapNameIn instead of savedMapIn).
            This is because savedMapIn has the events (just like mapNameIn), but
            savedMapIn has modified events to represent an event that has already
            fired (ie. been executed) whereas no events in the map file have been
            set as executed...
         */

         //Create Unit paths to beHere coordinates
         for (int a=0; a < this.numAI+1; a++)
         {  this.players[a].unitsFindPathsAfterFileLoad();
         }
         //Allow units to acquire targets
         for (int a=0; a < this.numAI+1; a++)
         {  this.players[a].unitsAqcuireTargetsAfterFileLoad();
         }
         //Reestablish highlighter over selected unit/building (if applicable)
         if (selX > -1 && selY > -1)
         {  this.selectedUnit = this.players[0].getUnitAt(selX, selY);
            this.selectedBuilding = this.players[0].getBuildingAt(selX, selY);
         }

         this.setOutOfMainMenu();
         this.updateAllViews();
         this.textDisplayer.resetMessages();
         in.close();

         //start the game
         this.startNewGame();

      }
   }

   public void loadFile(String fileName)
   {  if (fileName != null)
      {  TextInput in = new TextInput(fileName);

         this.mapName = in.readLine();
         String savedMapNameFromFile = in.readLine();
         int selX = in.readInt();
         in.readLine();
         int selY = in.readInt();
         in.readLine();
         this.unitUnderAttackTime = in.readDouble();
         in.readLine();
         this.buildingUnderAttackTime = in.readDouble();
         in.readLine();
         this.enemyApproachingTime = in.readDouble();
         in.readLine();
         this.difficulty = in.readLine();
         this.time = in.readDouble();
         in.readLine();
         this.numAI = in.readInt();
         in.readLine();
         in.readLine();

         //projectiles
         this.numProjectiles = in.readInt();
         in.readLine();
         in.readLine();
         if (this.numProjectiles == 0)
         {  this.projectiles = new Projectile[1];
         } else
         {  this.projectiles = new Projectile[this.numProjectiles];
         }
         for (int i=0; i < this.numProjectiles; i++)
         {  Projectile projectile = this.projectileInfo.getProjectile(in.readLine());
            int damage = in.readInt();
            in.readLine();
            double distance = in.readDouble();
            in.readLine();
            double travelled = in.readDouble();
            in.readLine();
            int startX = in.readInt();
            in.readLine();
            int startY = in.readInt();
            in.readLine();
            int degree = in.readInt();
            in.readLine();
            int targetX = in.readInt();
            in.readLine();
            int targetY = in.readInt();
            in.readLine();
            in.readLine();
            this.projectiles[i] = new Projectile(projectile, damage, distance, travelled, startX, startY, degree, targetX, targetY);
         }

         //explosions
         this.numExplosions = in.readInt();
         in.readLine();
         in.readLine();
         if (this.numExplosions == 0)
         {  this.explosions = new Explosion[1];
         } else
         {  this.explosions = new Explosion[this.numExplosions];
         }
         for (int i=0; i < this.numExplosions; i++)
         {  Explosion explosion = this.explosionInfo.getExplosion(in.readLine());
            int x = in.readInt();
            in.readLine();
            int y = in.readInt();
            in.readLine();
            double progress = in.readDouble();
            in.readLine();
            in.readLine();
            this.explosions[i] = new Explosion(explosion, x, y, progress);
         }

         //Making the players and map
         this.players = new Player[this.numAI+1];
         this.players[0] = new Player(in, 0, this.units, this.buildings, this.lands, this);
         for (int a=1; a < this.numAI+1; a++)
         {  int waveNumber = in.readInt();
            in.readLine();
            this.players[a] = new AI(in, a, this.units, this.buildings, this.lands, this.difficulty, waveNumber, this);
         }

         TextInput savedMapIn = new TextInput(Constants.SAVED_GAME_PATH + savedMapNameFromFile);
         TextInput mapNameIn = new TextInput(Constants.MAPS_PATH + this.mapName);
         this.map = new Map(mapNameIn, savedMapIn, this.lands);
         this.locations = new Locations(mapNameIn);
         EventProfiles eProfiles = new EventProfiles(this.locations, this);
         this.eventList = new EventList(savedMapIn, eProfiles, this);
         /* When I am making a 'new game' and not loading a game, I must make a
            new method for it.  I also must send the eventList the SAME TextInput
            as the map (ie. I must send the eventList mapNameIn instead of savedMapIn).
            This is because savedMapIn has the events (just like mapNameIn), but
            savedMapIn has modified events to represent an event that has already
            fired (ie. been executed) whereas no events in the map file have been
            set as executed...
         */

         //Create Unit paths to beHere coordinates
         for (int a=0; a < this.numAI+1; a++)
         {  this.players[a].unitsFindPathsAfterFileLoad();
         }
         //Allow units to acquire targets
         for (int a=0; a < this.numAI+1; a++)
         {  this.players[a].unitsAqcuireTargetsAfterFileLoad();
         }
         //Reestablish highlighter over selected unit/building (if applicable)
         if (selX > -1 && selY > -1)
         {  this.selectedUnit = this.players[0].getUnitAt(selX, selY);
            this.selectedBuilding = this.players[0].getBuildingAt(selX, selY);
         }

         this.setOutOfMainMenu();

         this.updateAllViews();
         this.textDisplayer.resetMessages();
         in.close();

         //start the game
         this.startNewGame();

      }
   }


   public int getMovementAmountX(Unit unit)
   {  if (unit.getBodyDegree() == 0)
      {  return 0;
      }  else if (unit.getBodyDegree() == 45)
      {  return 0;
      }  else if (unit.getBodyDegree() == 90)
      {  return 0;
      }  else if (unit.getBodyDegree() == 135)
      {  return 0;
      }  else if (unit.getBodyDegree() == 180)
      {  return 0;
      }  else if (unit.getBodyDegree() == 225)
      {  return 0;
      }  else if (unit.getBodyDegree() == 270)
      {  return 0;
      }  else if (unit.getBodyDegree() == 315)
      {  return 0;
      }  else if (unit.getBodyDegree() == 360)
      {  return 0;
      }

      return 0;
   }

   public int getMovementAmountY(Unit unit)
   {
      if (unit.getBodyDegree() == 0)
      {  return -unit.getProgress();
      }  else if (unit.getBodyDegree() == 45)
      {  return -unit.getProgress();
      }  else if (unit.getBodyDegree() == 90)
      {  return -unit.getProgress();
      }  else if (unit.getBodyDegree() == 135)
      {  return -unit.getProgress();
      }  else if (unit.getBodyDegree() == 180)
      {  return -unit.getProgress();
      }  else if (unit.getBodyDegree() == 225)
      {  return -unit.getProgress();
      }  else if (unit.getBodyDegree() == 270)
      {  return -unit.getProgress();
      }  else if (unit.getBodyDegree() == 315)
      {  return -unit.getProgress();
      }  else if (unit.getBodyDegree() == 360)
      {  return -unit.getProgress();
      }

      return 0;
   }

   public int getMapX()
   {  return this.map.getX();
   }

   public int getMapY()
   {  return this.map.getY();
   }

   public String getMapInfoAt(int x, int y)
   {  return this.map.getBaseLayerInfoAt(x, y);
   }

   public Building getBuildingAt(int x, int y, int index)
   {  return this.players[index].getBuildingAt(x, y);
   }

   public Building getBuildingAt(int x, int y)
   {  for (int a=0; a < this.numAI+1; a++)
      {  Building building = this.players[a].getBuildingAt(x, y);
         if (building != null)
         {  return building;
         }
      }

      return null;
   }

   public Building getFirstBuilding(int player)
   {  return this.players[player].getFirstBuilding();
   }

   public Unit getUnitAttackingMe(Unit unit)
   {  for (int a=0; a < this.numAI+1; a++)
      {  if (this.players[a].getUnitAttackingMe(unit) != null)
         {  return this.players[a].getUnitAttackingMe(unit);
         }
      }

      return null;
   }

   /*
   public Unit returnUnitNearXY(int x, int y)
   {  int coordX = (int)(x/Constants.GRID_SIZE);
      int coordY = (int)(y/Constants.GRID_SIZE);

      this.player.returnUnitNearXY(coordX, coordY);

      return null;
   }
   */

   public int getPlayerFunds(int index)
   {  return this.players[index].getCredits();
   }

   public String getPlayerHouse(int index)
   {  return this.players[index].getHouse();
   }

   public double getHighlighterTime()
   {  return this.highlighterTime;
   }

   public void setAttackIndicatorXY(int x, int y)
   {  this.attackIndicatorX = x;
      this.attackIndicatorY = y;
   }

   public void setAttackIndicatorTime()
   {  this.attackIndicatorTime = 10;
   }

   public void decrementAttackIndicatorTime()
   {  this.attackIndicatorTime--;
   }

   public int getAttackIndicatorTime()
   {  return this.attackIndicatorTime;
   }

   public int getAttackIndicatorX()
   {  return this.attackIndicatorX;
   }

   public int getAttackIndicatorY()
   {  return this.attackIndicatorY;
   }

   public void setMovementIndicatorXY(int x, int y)
   {  this.movementIndicatorX = x;
      this.movementIndicatorY = y;
   }

   public void setMovementIndicatorTime()
   {  this.movementIndicatorTime = 10;
   }

   public void decrementMovementIndicatorTime()
   {  this.movementIndicatorTime--;
   }

   public int getMovementIndicatorTime()
   {  return this.movementIndicatorTime;
   }

   public int getMovementIndicatorX()
   {  return this.movementIndicatorX;
   }

   public int getMovementIndicatorY()
   {  return this.movementIndicatorY;
   }

   public boolean isUnitUnderAttack()
   {  return this.unitUnderAttack;
   }

   public boolean isBuildingUnderAttack()
   {  return this.buildingUnderAttack;
   }

   public boolean isEnemyApproaching()
   {  return this.enemyApproaching;
   }

   public int getUnitUnderAttackX()
   {  return this.unitUnderAttackX;
   }

   public int getUnitUnderAttackY()
   {  return this.unitUnderAttackY;
   }

   public int getBuildingUnderAttackX()
   {  return this.buildingUnderAttackX;
   }

   public int getBuildingUnderAttackY()
   {  return this.buildingUnderAttackY;
   }

   public int getEnemyApproachingX()
   {  return this.enemyApproachingX;
   }

   public int getEnemyApproachingY()
   {  return this.enemyApproachingY;
   }

   public void increaseUnitUnderAttackLength()
   {  this.unitUnderAttackLength++;
      if (this.unitUnderAttackLength >= 40)
      {  this.unitUnderAttack = false;
         this.unitUnderAttackLength = 0;
      }
   }

   public void increaseBuildingUnderAttackLength()
   {  this.buildingUnderAttackLength++;
      if (this.buildingUnderAttackLength >= 40)
      {  this.buildingUnderAttack = false;
         this.buildingUnderAttackLength = 0;
      }
   }

   public void enemyApproachingLength()
   {  this.enemyApproachingLength++;
      if (this.enemyApproachingLength >= 40)
      {  this.enemyApproaching = false;
         this.enemyApproachingLength = 0;
      }
   }

   public void resetUnitUnderAttack()
   {  this.unitUnderAttack = false;
   }

   public void resetBuildingUnderAttack()
   {  this.buildingUnderAttack = false;
   }

   public void resetEnemyApproaching()
   {  this.enemyApproaching = false;
   }

   public Unit[] getSelectedUnits()
   {  return this.selectedUnits;
   }

   public int getNumSelectedUnits()
   {  return this.numSelectedUnits;
   }

   private Unit getSelectedUnit()
   {  return this.selectedUnit;
   }

   public Building getSelectedBuilding()
   {  return this.selectedBuilding;
   }

   /* Assign the selected units OR building using coordinates from WorldView
   */
   public void setSelectedUnitsOrBuilding(int x1, int y1, int x2, int y2)
   {  int buildingX = x1;
      int buildingY = y1;

      /* x1 must always be smaller than x2 and y1 always smaller than y2
      */
      if (x1 > x2)
      {  int temp = x1;
         x1 = x2;
         x2 = temp;
      }
      if (y1 > y2)
      {  int temp = y1;
         y1 = y2;
         y2 = temp;
      }

      if (!this.setSelectedUnits(this.players[0].getUnitsFrom(x1, y1, x2, y2)))
      {  this.setSelectedBuilding(this.players[0].getBuildingAt(buildingX, buildingY));
      }  else
      {  this.setSelectedBuilding(null);
      }
   }

   /* Used by the method "setSelectedUnits(int,int,int,int)" ONLY
   */
   private boolean setSelectedUnits(Unit[] units)
   {  this.selectedUnits = units;
      if (units == null)
      {  this.setUnitMoving = false;
         this.numSelectedUnits = 0;
         return false;
      } else
      {  this.numSelectedUnits = units.length;
      }

      return true;
   }

   /* Do not use ANY of the 'selectedUnit' variables again
   */
   private void setSelectedUnit(Unit unit)
   {  this.selectedUnit = unit;
      if (unit == null)
      {  this.setUnitMoving = false;
      }
   }

   public void enterBuilding()
   {  this.inBuilding = true;
      this.gameFrame.setVisible(false);
      this.buildingView = new BuildingView(this, this.imageInfo);
      this.pauseGame();
      this.updateAllViews();
   }

   public void leaveBuilding()
   {  this.inBuilding = false;
      this.gameFrame.setVisible(true);
      this.buildingView.closeView();
      this.buildingView = null;
      this.updateAllViews();
      this.resumeGame();
   }

   public void alertPlayerUnitUnderAttack(int x, int y)
   {  if (this.time - this.unitUnderAttackTime > Constants.ALERT_COOLDOWN)
      {  this.soundInfo.playSound(Constants.UNIT_UNDER_ATTACK);
         this.unitUnderAttackTime = this.time;
         this.unitUnderAttack = true;
         this.unitUnderAttackX = x;
         this.unitUnderAttackY = y;
         this.addMessageToTextDisplayer("Unit Under Attack!", 10.0, Color.yellow);
      }
   }

   public void alertPlayerBuildingUnderAttack(int x, int y)
   {  if (this.time - this.buildingUnderAttackTime > Constants.ALERT_COOLDOWN)
      {  this.soundInfo.playSound(Constants.BUILDING_UNDER_ATTACK);
         this.buildingUnderAttackTime = this.time;
         this.buildingUnderAttack = true;
         this.buildingUnderAttackX = x;
         this.buildingUnderAttackY = y;
         this.addMessageToTextDisplayer("Building Under Attack!", 10.0, Color.yellow);
      }
   }

   /*
   public void UnitReturnToRefinery(Unit unit, int index)
   {  unit.setIsNotHarvesting();
      Building[] buildings = this.player.getBuildings();

      //RETURN TO CLOSEST REFINERY
      double x1 = (double)unit.getCurrentX();
      double y1 = (double)unit.getCurrentY();

      //COUNT NUMBER OF REFINERIES
      int numRefineries = 0;
      for (int i=0; i < buildings.length; i++)
      {  if (buildings[i].getName().equals(Constants.REFINERY))
         {  numRefineries++;
         }
      }

      if (numRefineries > 0)
      {  //FIND DISTANCES TO ALL REFINERIES
         double[][] distances = new double[numRefineries][2];
         double[] lengths = new double[numRefineries];
         numRefineries = 0;
         for (int i=0; i < buildings.length; i++)
         {  if (buildings[i].getName().equals(Constants.REFINERY))
            {  distances[numRefineries][0] = (double)buildings[i].getX();
               distances[numRefineries][1] = (double)buildings[i].getY();
               double yTotal = (double)(distances[numRefineries][1] - y1) * (distances[numRefineries][1] - y1);
               double xTotal = (double)(distances[numRefineries][0] - x1) * (distances[numRefineries][0] - x1);
               lengths[numRefineries] = Math.sqrt(xTotal + yTotal);
               numRefineries++;
            }
         }


         //DETERMINE THE SHORTEST DISTANCE TO A REFINERY
         double toTake = lengths[0];
         int index = 0;
         for (int i=0; i < numRefineries-1; i++)
         {  if (toTake > lengths[i + 1])
            {  toTake = lengths[i + 1];
               index = i+1;
            }
         }

         //GO TO REFINERY THAT IS CLOSEST
         int counter = 0;
         for (int i=0; i < buildings.length; i++)
         {  if (buildings[i].getName().equals(Constants.REFINERY))
            {  if (counter == index)
               {  unit.setBeHereCoordinates(buildings[i].getX(), buildings[i].getY());
               }
               counter++;
            }
         }
      }
   }
   */

   public void unitReturnToRefinery(Unit unit, int indexI)
   {  unit.setIsNotHarvesting();
      Building[] buildings = this.players[indexI].getBuildings();
      int numBuildings = this.players[indexI].getNumBuildings();

      //RETURN TO CLOSEST REFINERY
      double x1 = (double)unit.getCurrentX();
      double y1 = (double)unit.getCurrentY();

      //COUNT NUMBER OF REFINERIES
      int numRefineries = 0;
      for (int i=0; i < numBuildings; i++)
      {  if (buildings[i].getName().equals(Constants.REFINERY))
         {  numRefineries++;
         }
      }

      if (numRefineries > 0)
      {  //FIND DISTANCES TO ALL REFINERIES
         double[][] distances = new double[numRefineries][2];
         double[] lengths = new double[numRefineries];
         numRefineries = 0;
         for (int i=0; i < numBuildings; i++)
         {  if (buildings[i].getName().equals(Constants.REFINERY))
            {  distances[numRefineries][0] = (double)buildings[i].getX();
               distances[numRefineries][1] = (double)buildings[i].getY();
               double yTotal = (double)(distances[numRefineries][1] - y1) * (distances[numRefineries][1] - y1);
               double xTotal = (double)(distances[numRefineries][0] - x1) * (distances[numRefineries][0] - x1);
               lengths[numRefineries] = Math.sqrt(xTotal + yTotal);
               numRefineries++;
            }
         }

         //DETERMINE THE SHORTEST DISTANCE TO A REFINERY
         double toTake = lengths[0];
         int index = 0;
         for (int i=0; i < numRefineries-1; i++)
         {  if (toTake > lengths[i + 1])
            {  toTake = lengths[i + 1];
               index = i+1;
            }
         }

         //GO TO REFINERY THAT IS CLOSEST
         int counter = 0;
         for (int i=0; i < numBuildings; i++)
         {  if (buildings[i].getName().equals(Constants.REFINERY))
            {  if (counter == index)
               {
                  unit.setBeHereCoordinates(buildings[i].getX(), buildings[i].getY());
               }
               counter++;
            }
         }
      }
   }

   public Building[] getAvailableBuildings()
   {  if (this.selectedBuilding.getName().equals(Constants.CONSTRUCTION_YARD))
      {  Building[] temp = new Building[8];
         temp[0] = this.buildings.getBuilding(Constants.SINGLE_SLAB);
         temp[1] = this.buildings.getBuilding(Constants.WINDTRAP);
         temp[2] = this.buildings.getBuilding(Constants.REFINERY);
         temp[3] = this.buildings.getBuilding(Constants.RADAR);
         temp[4] = this.buildings.getBuilding(Constants.SILO);
         temp[5] = this.buildings.getBuilding(Constants.WOR);
         temp[6] = this.buildings.getBuilding(Constants.LIGHT_FACTORY);
         temp[7] = this.buildings.getBuilding(Constants.HEAVY_FACTORY);

         return temp;
      }

      return null;
   }

   public Unit[] getAvailableUnits()
   {  if (this.selectedBuilding.getName().equals(Constants.CONSTRUCTION_YARD))
      {  Unit[] temp = new Unit[1];
         temp[0] = this.units.getUnit(Constants.COMBAT_TURRET);

         return temp;
      }  else if (this.selectedBuilding.getName().equals(Constants.LIGHT_FACTORY))
      {  Unit[] temp = new Unit[1];
         temp[0] = this.units.getUnit(Constants.ASSAULT_BUGGY);

         return temp;
      } else if (this.selectedBuilding.getName().equals(Constants.HEAVY_FACTORY))
      {  Unit[] temp = new Unit[2];
         temp[0] = this.units.getUnit(Constants.COMBAT_TANK);
         temp[1] = this.units.getUnit(Constants.HARVESTOR);

         return temp;
      } else if (this.selectedBuilding.getName().equals(Constants.WOR))
      {  Unit[] temp = new Unit[1];
         temp[0] = this.units.getUnit(Constants.HEAVY_TROOPERS);

         return temp;
      }

      return null;
   }

   public boolean getIsInBuilding()
   {  return this.inBuilding;
   }

   public void setSelectedUnitsGoto(int x, int y)
   {  if (this.coordinatesClear(x, y))
      {  for (int i=0; i < this.numSelectedUnits; i++)
         {  this.selectedUnits[i].setIsNotHarvesting();
            this.selectedUnits[i].resetAttack();
            this.selectedUnits[i].resetIsMovingAndAttacking();
            this.selectedUnits[i].setBeHereCoordinates(x, y);
         }
      }
   }

   private void setSelectedUnitGoto(int x, int y)
   {  if(this.coordinatesClear(x, y))
      {  this.selectedUnit.setIsNotHarvesting();
         this.selectedUnit.resetAttack();
         this.selectedUnit.resetIsMovingAndAttacking();
         this.selectedUnit.setBeHereCoordinates(x, y);
         //this.findPath(x, y, this.selectedUnit.getCurrentX(), this.selectedUnit.getCurrentY());
      }
   }

   public void setSelectedUnitsAttackTo(int x, int y)
   {  if (this.coordinatesClear(x, y))
      {  for (int i=0; i < this.numSelectedUnits; i++)
         {  this.selectedUnits[i].setIsNotHarvesting();
            this.selectedUnits[i].resetAttack();
            this.selectedUnits[i].setIsMovingAndAttacking();
            this.selectedUnits[i].setBeHereCoordinates(x, y);
         }
      }
   }

   private void setSelectedUnitAttackto(int x, int y)
   {  if(this.coordinatesClear(x, y))
      {  this.selectedUnit.setIsNotHarvesting();
         this.selectedUnit.resetAttack();
         this.selectedUnit.setIsMovingAndAttacking();
         this.selectedUnit.setBeHereCoordinates(x, y);
         //this.findPath(x, y, this.selectedUnit.getCurrentX(), this.selectedUnit.getCurrentY());
      }
   }

   public void setSelectedUnitStop()
   {  this.setUnitMoving = false;
      this.setUnitHarvest = false;
      this.setUnitAttack = false;
      if (this.selectedUnit != null)
      {  this.selectedUnit.doStop();
      } else
      {  for (int i=0; i < this.numSelectedUnits; i++)
         {  this.selectedUnits[i].doStop();
         }
      }
   }

   /* This method applies to only the first unit in the selectedUnits array...which
      will only be a harvestor
   */
   public void setSelectedUnitsGotoHarvest(int x, int y)
   {  if (this.coordinatesClear(x, y))
      {  this.selectedUnits[0].resetAttack();
         this.selectedUnits[0].resetIsMovingAndAttacking();
         this.selectedUnits[0].setBeHereCoordinates(x, y);
         this.selectedUnits[0].setIsHarvesting();
      }
   }

   private void setSelectedUnitGotoHarvest(int x, int y)
   {  if(this.coordinatesClear(x, y))
      {  this.selectedUnit.resetAttack();
         this.selectedUnit.resetIsMovingAndAttacking();
         this.selectedUnit.setBeHereCoordinates(x, y);
         this.selectedUnit.setIsHarvesting();
      }
   }

   /* Use this
   */
   public void setSelectedUnitsAttackAt(int x, int y)
   {  Unit target1 = null;
      boolean flag = false;
      for (int i=1; i < this.numAI+1; i++)
      {  if (target1 == null)
         {  target1 = this.getUnitAt(x, y, i);
            if (target1 != null)
            {  for (int j=0; j < this.numSelectedUnits; j++)
               {  this.selectedUnits[j].setIsNotHarvesting();
                  this.selectedUnits[j].resetAttack();
                  this.selectedUnits[j].resetIsMovingAndAttacking();
                  this.selectedUnits[j].setAttackAt(x, y, target1);
               }
               flag = true;
            }
         }
      }

      Building target2 = null;
      flag = false;
      for (int i=1; i < this.numAI +1; i++)
      {  if (target2 == null)
         {  target2 = this.getBuildingAt(x, y, i);
            if (target2 != null)
            {  for (int j=0; j < this.numSelectedUnits; j++)
               {  this.selectedUnits[j].setIsNotHarvesting();
                  this.selectedUnits[j].resetAttack();
                  this.selectedUnits[j].resetIsMovingAndAttacking();
                  this.selectedUnits[j].setAttackAt(x, y, target2);
               }
               flag = true;
            }
         }
      }

      if (!flag)
      {  this.setSelectedUnitsAttackTo(x, y);
      }

   }

   /* Don't need to use this
   */
   private void setSelectedUnitAttackAt(int x, int y)
   {  Unit target1 = null;
      boolean flag = false;
      for (int i=1; i < this.numAI+1; i++)
      {  if (target1 == null)
         {  target1 = this.getUnitAt(x, y, i);
            if (target1 != null)
            {  this.selectedUnit.setIsNotHarvesting();
               this.selectedUnit.resetAttack();
               this.selectedUnit.resetIsMovingAndAttacking();
               this.selectedUnit.resetBeHereCoordinates();
               this.selectedUnit.setAttackAt(x, y, target1);
               flag = true;
            }
         }
      }

      Building target2 = null;
      flag = false;
      for (int i=1; i < this.numAI+1; i++)
      {  if (target2 == null)
         {  target2 = this.getBuildingAt(x, y, i);
            if (target2 != null)
            {  this.selectedUnit.setAttackAt(x, y, target2);
               flag = true;
            }
         }
      }

      if (!flag)
      {  this.setSelectedUnitAttackto(x, y);
      }
   }

   public boolean coordinatesClear(int x, int y)
   {  boolean land = this.lands.isEnterable(this.map.getBaseLayerInfoAt(x, y));
      boolean building = this.isABuildingAt(x, y, 1, 1);
      boolean unit = this.isAUnitAt(x, y);

      return (land && !building && !unit);
   }

   public void setSelectedUnitMoving()
   {  this.setUnitMoving = true;
      this.setUnitHarvest = false;
      this.setUnitAttack = false;
   }

   public void setSelectedUnitHarvest()
   {  this.setUnitHarvest = true;
      this.setUnitMoving = false;
      this.setUnitAttack = false;
   }

   public void setSelectedUnitNotMoving()
   {  this.setUnitMoving = false;
   }

   public void setSelectedUnitNotHarvest()
   {  this.setUnitHarvest = false;
   }

   public void setSelectedUnitAttack()
   {  this.setUnitAttack = true;
      this.setUnitHarvest = false;
      this.setUnitMoving = false;
   }

   public void setSelectedUnitNotAttack()
   {  this.setUnitAttack = false;
   }

   public boolean doesSelectedUnitNeedMovingCoordinates()
   {  return this.setUnitMoving;
   }

   public boolean doesSelectedUnitNeedHarvestCoordinates()
   {  return this.setUnitHarvest;
   }

   public boolean doesSelectedUnitNeedAttackCoordinates()
   {  return this.setUnitAttack;
   }

   public boolean getPlacingBuilding()
   {  return this.placingBuilding;
   }

   public void setPlacingBuilding(boolean tF)
   {  this.placingBuilding = tF;
   }

   public boolean checkPlaceBuildingAt(int x, int y)
   {  Building building = this.buildings.getBuilding(this.selectedBuilding.getCurrentConstruction());
      Unit unit = this.units.getUnit(this.selectedBuilding.getCurrentConstruction());

      boolean flag = true; //CHECK IF PLACING ON ROCK
      if (building != null)
      {  for (int i=0; i < building.getLengthX(); i++)
         {  for (int j=0; j < building.getWidthY(); j++)
            {  if (!this.map.getBaseLayerInfoAt(x+i, y+j).equals(Constants.ROCK))
               {  flag = false;
               }
            }
         }
      } else
      {  if (!this.map.getBaseLayerInfoAt(x, y).equals(Constants.ROCK))
         {  flag = false;
         }
      }

      boolean buildingCheck = false;
      if (building != null)
      {  buildingCheck = this.isABuildingAt(x, y, building.getLengthX(), building.getWidthY()); //CHECK FOR BUILDING
      } else
      {  buildingCheck = this.isABuildingAt(x, y, 1, 1);
      }

      boolean isUnit = this.whenPlacingBuildingIsAUnitAt(x, y); //CHECK FOR UNIT

      if(flag && !buildingCheck && !isUnit)
      {  this.soundInfo.playSound(Constants.BUILDING_PLACED);
         return true;
      }

      this.soundInfo.playSound(Constants.CANNOT_PLACE);
      return false;
   }

   public void placeBuildingAt(int x, int y)
   {  Building building = this.buildings.getBuilding(this.selectedBuilding.getCurrentConstruction());
      Unit unit = this.units.getUnit(this.selectedBuilding.getCurrentConstruction());

      if (building != null)
      {  this.players[0].addBuilding(x, y, building);
      } else
      {  this.players[0].addUnit(x, y, Constants.NONE, -1, unit);
      }
      this.selectedBuilding.resetUnitOrBuilding();
      this.placingBuilding = false;
   }

   public boolean canBuildBuilding(Building building)
   {  if (this.players[0].getCredits() >= building.getCost())
      {  this.refundCredits();
         this.selectedBuilding.buildUnitOrBuilding(building.getName(), Constants.NONE, 0);
         this.players[0].reduceCredits(building.getCost());
         return true;
      }

      return false;
   }

   public boolean canBuildBuilding(String building)
   {  Building toBuild = this.buildings.getBuilding(building);
      if (toBuild != null)
      {  this.canBuildBuilding(toBuild);
         return true;
      }

      Unit toUnit = this.units.getUnit(building);
      if (toUnit != null)
      {  this.canBuildUnit(toUnit);
         return true;
      }

      return false;
   }


   public boolean canBuildUnit(Unit unit)
   {  if (this.players[0].getCredits() >= unit.getCost())
      {  this.refundCredits();
         this.selectedBuilding.buildUnitOrBuilding(unit.getName(), Constants.NONE, 0);
         this.players[0].reduceCredits(unit.getCost());
         return true;
      }

      return false;
   }

   private void refundCredits()
   {  if (!this.selectedBuilding.getCurrentConstruction().equals(Constants.STOP))
      {  Building building = this.buildings.getBuilding(this.selectedBuilding.getCurrentConstruction());
         Unit unit = this.units.getUnit(this.selectedBuilding.getCurrentConstruction());
         if (building != null)
         {  this.players[0].addCredits(building.getCost());
         } else if (unit != null)
         {  this.players[0].addCredits(unit.getCost());
         }
      }
   }

   private void refundCredits(Building building, int index)
   {  if (building != null)
      {  Building refundBuilding = this.buildings.getBuilding(building.getCurrentConstruction());
         Unit refundUnit = this.units.getUnit(building.getCurrentConstruction());

         if (refundBuilding != null)
         {  this.players[index].addCredits(refundBuilding.getCost());
         } else if (refundUnit != null)
         {  this.players[index].addCredits(refundUnit.getCost());
         }
      }
   }

   private void refundCredits(int index)
   {
   }

   /* These methods below all have to do with EVENTS
   */

   public TextDisplayer getTextDisplayer()
   {  return this.textDisplayer;
   }

   public void addMessageToTextDisplayer(String message, double duration)
   {  this.textDisplayer.addMessage(message, duration);
   }

   public void addMessageToTextDisplayer(String message, double duration, Color color)
   {  this.textDisplayer.addMessage(message, duration, color);
   }

   public void orderUnitsToMoveFromEvent(String name, String house, int startX1, int startY1, int endX1, int endY1, int startX2, int startY2, int endX2, int endY2)
   {  int loopX1 = endX1 - startX1 + 1;
      int loopY1 = endY1 - startY1 +1;

      Unit[] units = new Unit[0];

      //get units
      for (int i=0; i < loopX1; i++)
      {  for (int j=0; j < loopY1; j++)
         {  Unit unit = this.getUnitAt(startX1 + i, startY1 + j, house);
            if (unit != null && (unit.getName().equals(name) || name.equals(Constants.ANY)))
            {  units = this.addUnitToArray(units, unit);
            }
         }
      }

      //send units to move
      /* Might have to change the number "1" --> units.length.  I'm trying out "1"
         to see what happens when many units are told to move and attack to a single
         point.  Also, notice the "true" sent.  This means coordinates might have
         units/buildings on them.
      */
      int[][] freeCoordinates = this.findFreeCoordinatesWithin(startX2, startY2, endX2, endY2, 1, true);

      if (freeCoordinates != null)
      {  for (int i=0; i < units.length; i++)
         {  this.setUnitGoto(freeCoordinates[0][0], freeCoordinates[1][0], units[i]);
         }
      } else
      {  this.addMessageToTextDisplayer("Warning: Could Not Send Units On 'Move' ORDER From Event.  REASON: Coordinates are not enterable.", 22.0);
      }

   }

   public void orderUnitsToAttackFromEvent(String name, String house, int startX1, int startY1, int endX1, int endY1, int startX2, int startY2, int endX2, int endY2)
   {  int loopX1 = endX1 - startX1 + 1;
      int loopY1 = endY1 - startY1 +1;

      Unit[] units = new Unit[0];

      //get units
      for (int i=0; i < loopX1; i++)
      {  for (int j=0; j < loopY1; j++)
         {  Unit unit = this.getUnitAt(startX1 + i, startY1 + j, house);
            if (unit != null && (unit.getName().equals(name) || name.equals(Constants.ANY)))
            {  units = this.addUnitToArray(units, unit);
            }
         }
      }

      //send units to attack
      /* Might have to change the number "1" --> units.length.  I'm trying out "1"
         to see what happens when many units are told to move and attack to a single
         point.  Also, notice the "true" sent.  This means coordinates might have
         units/buildings on them.
      */
      int[][] freeCoordinates = this.findFreeCoordinatesWithin(startX2, startY2, endX2, endY2, 1, true);

      if (freeCoordinates != null)
      {  for (int i=0; i < units.length; i++)
         {  this.setUnitAttackTo(freeCoordinates[0][0], freeCoordinates[1][0], units[i]);
         }
      } else
      {  this.addMessageToTextDisplayer("Warning: Could Not Send Units On 'Move And Attack' ORDER From Event.  REASON: Coordinates are not enterable.", 22.0);
      }

   }

   /* This method assumes that the coordinates have been checked and the terrain
      is ENTERABLE!
   */
   private void setUnitAttackTo(int x, int y, Unit unit)
   {  unit.setIsNotHarvesting();
      unit.resetAttack();
      unit.setIsMovingAndAttacking();
      unit.setBeHereCoordinates(x, y);
   }

   /* This method assumes that the coordinates have been checked and the terrain
      is ENTERABLE!
   */
   private void setUnitGoto(int x, int y, Unit unit)
   {  unit.setIsNotHarvesting();
      unit.resetAttack();
      unit.resetIsMovingAndAttacking();
      unit.setBeHereCoordinates(x, y);
   }

   private Unit[] addUnitToArray(Unit[] units, Unit addUnit)
   {  int num = units.length;
      Unit[] temp = new Unit[num+1];

      for (int i=0; i < num; i++)
      {  temp[i] = units[i];
      }

      units = temp;
      units[num] = addUnit;
      return units;
   }

   public void createUnitsFromEvent(String name, int number, String house, int x1, int y1, int x2, int y2)
   {  int[][] freeCoordinates = this.findFreeCoordinatesWithin(x1, y1, x2, y2, number, false);

      if (freeCoordinates != null)
      {  if (freeCoordinates[0].length < number)
         {  this.addMessageToTextDisplayer("Warning:  Could Not Place All Units From Event Action.", 15.0);
            number = freeCoordinates.length;
         }

         int pIndex = 0;
         for (int i=0; i < this.numAI+1; i++)
         {  if (this.players[i].getHouse().equals(house))
            {  pIndex = i;
            }
         }

         for (int i=0; i < number; i++)
         {  Unit unit = this.units.getUnit(name);
            this.players[pIndex].addUnit(freeCoordinates[0][i], freeCoordinates[1][i], Constants.NONE, 0, unit);
         }

      } else
      {  this.addMessageToTextDisplayer("Warning:  Could Not Place ANY Units From Event Action.", 15.0);
      }
   }

   private int[][] findFreeCoordinatesWithin(int x1, int y1, int x2, int y2, int target, boolean loose)
   {  int numFound = 0;
      int currentX = 0;
      int currentY = 0;
      //initialize the array of coordinates at 0
      int[][] free = new int[2][0];

      if (x2 != 0)
      {  currentX = ((x2 - x1) / 2) + x1;
      }
      if (y2 != 0)
      {  currentY = ((y2 - y1) / 2) + y1;
      }

      Node startNode = new Node(currentX, currentY);
      OpenNodes open = new OpenNodes();
      ClosedNodes closed = new ClosedNodes();

      open.addNode(startNode);
      while(open.getNumOfNodes() != 0 && numFound < target)
      {  Node node = open.getNextNode();
         open.removeNode(node);
         closed.addNode(node);


         /* BOOLEAN "loose" tells whether the caller of this method wants to find
            coordinates without any units/buildings and that the coordinates are
            enterable (loose = false), OR whether the caller just wants to know
            of coordinates where the land is enterable and it doesn't matter if
            there are units/buildings there already (loose = true).  Use loose=true
            when sending units to MOVE AND ATTACK.
         */
         if (!loose && this.coordinatesClear(node.getX(), node.getY()))
         {  free = this.addToFreeCoordinates(free, node.getX(), node.getY());
         } else if (loose && this.lands.isEnterable(this.map.getBaseLayerInfoAt(node.getX(), node.getY())))
         {  free = this.addToFreeCoordinates(free, node.getX(), node.getY());
         }
         this.generateSuccessorNodes(open, closed, node, x1, y1, x2, y2);
      }

      return free;
   }

   private void generateSuccessorNodes(OpenNodes open, ClosedNodes closed, Node node, int x1, int y1, int x2, int y2)
   {  //UP
      if (node.getY()-1 >= y1 && !closed.checkForNode(node.getX(), node.getY()-1))
      {  Node addNode = new Node(node.getX(), node.getY()-1);
         open.addNode(addNode);
      }

      //RIGHT
      if (node.getX()+1 <= x2 && !closed.checkForNode(node.getX()+1, node.getY()))
      {  Node addNode = new Node(node.getX()+1, node.getY());
         open.addNode(addNode);
      }

      //DOWN
      if (node.getY()+1 <= y2 && !closed.checkForNode(node.getX(), node.getY()+1))
      {  Node addNode = new Node(node.getX(), node.getY()+1);
         open.addNode(addNode);
      }

      //LEFT
      if (node.getX()-1 >= x1 && !closed.checkForNode(node.getX()-1, node.getY()))
      {  Node addNode = new Node(node.getX()-1, node.getY());
         open.addNode(addNode);
      }

      //UP RIGHT
      if (node.getY()-1 >= y1 && node.getX()+1 <= x2 && !closed.checkForNode(node.getX()+1, node.getY()-1))
      {  Node addNode = new Node(node.getX()+1, node.getY()-1);
         open.addNode(addNode);
      }

      //UP LEFT
      if (node.getY()-1 >= y1 && node.getX()-1 >= x1 && !closed.checkForNode(node.getX()-1, node.getY()-1))
      {  Node addNode = new Node(node.getX()-1, node.getY()-1);
         open.addNode(addNode);
      }

      //DOWN LEFT
      if (node.getY()+1 <= y2 && node.getX()-1 >= x1 && !closed.checkForNode(node.getX()-1, node.getY()+1))
      {  Node addNode = new Node(node.getX()-1, node.getY()+1);
         open.addNode(addNode);
      }

      //DOWN RIGHT
      if (node.getY()+1 <= y2 && node.getX()+1 <= x2 && !closed.checkForNode(node.getX()+1, node.getY()+1))
      {  Node addNode = new Node(node.getX()+1, node.getY()+1);
         open.addNode(addNode);
      }
   }

   private int[][] addToFreeCoordinates(int[][] free, int x, int y)
   {  int numCoord = free[0].length;
      int[][] temp = new int[2][numCoord+1];
      for(int i=0; i < numCoord; i++)
      {  temp[0][i] = free[0][i];
         temp[1][i] = free[1][i];
      }

      //free = temp;

      temp[0][numCoord] = x;
      temp[1][numCoord] = y;

      return temp;
   }

   /* END of EVENT methods
   */

   private boolean setSelectedBuilding(Building building)
   {  this.selectedBuilding = building;
      return (building != null);

   }

   public void setThreadNumber(int time)
   {  this.tempThreadNumber = time;
   }

   public int getThreadNumber()
   {  return this.tempThreadNumber;
   }

   private void startNewGame()
   {  this.endGame();
      this.startGame();
   }

   private void startGame()
   {  if (!this.running)
      {  //this.runnable.ruun();
         //Thread t = new Thread(this.runnable[0]);
         //t.start();
         GameThread gT = new GameThread(this);
         this.running = true;
         this.thread[0].start();

      }

   this.soundInfo.playBackgroundMusic();
   }

   private void endGame()
   {  this.running = false;
      if (this.thread[0] != null)
      {  this.thread[0].stop();
      }
      this.resetRunnableAndThread();

   }

   public boolean isGameRunning()
   {  return this.running;
   }

   public void pauseGame()
   {  if (this.running)
      {  this.thread[0].suspend();
         this.running = false;
      }
   }

   public void resumeGame()
   {  if (this.running)
      {  this.thread[0].resume();
         this.running = true;
      } else if (!this.running && this.thread[0] != null)
      {  this.thread[0].resume();
         this.running = true;
      }
   }

   public int getNumAI()
   {  return this.numAI;
   }

   public void updateViewHorizontal(int value, int size)
   {  this.viewHorizontalValue = value;
      this.viewHorizontal = size;
   }

   public void updateWorldViewHorizontal(int value)
   {  this.worldViewHorizontal = value;
   }

   public void updateViewVertical(int value, int size)
   {  this.viewVerticalValue = value;
      this.viewVertical = size;
   }

   public void updateWorldViewVertical(int value)
   {  this.worldViewVertical = value;
   }

   public int getViewHorizontal()
   {  return this.viewHorizontal;
   }

   public int getViewHorizontalValue()
   {  return this.viewHorizontalValue;
   }

   public int getWorldViewHorizontal()
   {  return this.worldViewHorizontal;
   }

   public int getViewVertical()
   {  return this.viewVertical;
   }

   public int getViewVerticalValue()
   {  return this.viewVerticalValue;
   }

   public int getWorldViewVertical()
   {  return this.worldViewVertical;
   }

   public boolean hasRadarChanged()
   {  return this.radarChanged;
   }

   public boolean hasViewChanged()
   {  return this.viewChanged;
   }

   public void resetRadarChanged()
   {  this.radarChanged = false;
   }

   public void resetViewChanged()
   {  this.viewChanged = false;
   }

   public void setRadarChanged()
   {  this.radarChanged = true;
   }

   public void setViewChanged()
   {  this.viewChanged = true;
   }

   public void setAtMainMenu()
   {  this.atMainMenu = true;
      this.gameFrame.setVisible(false);
      this.mainMenuView = new MainMenuView(this, this.imageInfo);
      this.pauseGame();
      this.soundInfo.stopBackgroundMusic();
      //this.updateAllViews();
   }

   public void setOutOfMainMenu()
   {  this.atMainMenu = false;
      this.gameFrame.setVisible(true);
      this.mainMenuView.closeView();
      this.refreshMap = true;
      this.updateAllViews();
      this.resumeGame();
   }

   public boolean getAtMainMenu()
   {  return this.atMainMenu;
   }
}
