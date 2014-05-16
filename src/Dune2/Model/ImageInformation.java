package Dune2.Model;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;

public class ImageInformation
{  private static final Toolkit toolkit = Toolkit.getDefaultToolkit();

   private Image aAssaultBuggyImage;
   private Image oAssaultBuggyImage;
   private Image hAssaultBuggyImage;
   private Image aCombatTankImage;
   private Image oCombatTankImage;
   private Image hCombatTankImage;
   private Image combatTurretImage;
   private Image aHarvestorImage;
   private Image hHarvestorImage;
   private Image oHarvestorImage;
   private Image aHarvestorHarvestingImage;
   private Image hHarvestorHarvestingImage;
   private Image oHarvestorHarvestingImage;
   private Image aHeavyTroopersImage;
   private Image oHeavyTroopersImage;
   private Image hHeavyTroopersImage;

   private Image aConstructionYardImage;
   private Image aConstructionYardImage1;
   private Image aConstructionYardImage2;
   private Image oConstructionYardImage;
   private Image oConstructionYardImage1;
   private Image oConstructionYardImage2;
   private Image hConstructionYardImage;
   private Image hConstructionYardImage1;
   private Image hConstructionYardImage2;
   private Image aSingleSlabImage;
   private Image hSingleSlabImage;
   private Image oSingleSlabImage;
   private Image aSlab4Image;
   private Image hSlab4Image;
   private Image oSlab4Image;
   private Image aLightFactoryImage;
   private Image hLightFactoryImage;
   private Image oLightFactoryImage;
   private Image aHeavyFactoryImage;
   private Image hHeavyFactoryImage;
   private Image oHeavyFactoryImage;
   private Image aRadarImage;
   private Image hRadarImage;
   private Image oRadarImage;
   private Image aRefineryImage;
   private Image hRefineryImage;
   private Image oRefineryImage;
   private Image aHarvestorDockedImage;
   private Image hHarvestorDockedImage;
   private Image oHarvestorDockedImage;
   private Image aSiloImage;
   private Image hSiloImage;
   private Image oSiloImage;
   private Image aWallImage;
   private Image hWallImage;
   private Image oWallImage;
   private Image aWindtrapImage;
   private Image hWindtrapImage;
   private Image oWindtrapImage;
   private Image aWORImage;
   private Image hWORImage;
   private Image oWORImage;
   private Image aCombatTurretImage;
   private Image hCombatTurretImage;
   private Image oCombatTurretImage;
   private Image combatTurretTurretImage;

   private Image CYLargeImage; //buildings large
   private Image SSLargeImage;
   private Image S4LargeImage;
   private Image LFLargeImage;
   private Image HFLargeImage;
   private Image RALargeImage;
   private Image RELargeImage;
   private Image SLargeImage;
   private Image WALargeImage;
   private Image WILargeImage;
   private Image WOLargeImage;
   private Image CALargeImage; //units large
   private Image CTLargeImage;
   private Image HALargeImage;
   private Image HTSLargeImage;
   private Image LILargeImage;
   private Image MCLargeImage;
   private Image ORLargeImage;
   private Image ABLargeImage;
   private Image STLargeImage;
   private Image TRLargeImage;
   private Image SWLargeImage;
   private Image HTLargeImage;
   private Image CTTLargeImage;

   private Image CYSmallImage; //buildings small
   private Image SSSmallImage;
   private Image S4SmallImage;
   private Image LFSmallImage;
   private Image HFSmallImage;
   private Image RASmallImage;
   private Image RESmallImage;
   private Image SSmallImage;
   private Image WASmallImage;
   private Image WISmallImage;
   private Image WOSmallImage;
   private Image CASmallImage; //units small
   private Image CTSmallImage;
   private Image HASmallImage;
   private Image HTSSmallImage;
   private Image LISmallImage;
   private Image MCSmallImage;
   private Image ORSmallImage;
   private Image ABSmallImage;
   private Image STSmallImage;
   private Image TRSmallImage;
   private Image SWSmallImage;
   private Image HTSmallImage;
   private Image CTTSmallImage;

   private Image highlighterImage1;
   private Image highlighterImage2;
   private Image highlighterImage3;

   private Image rockImage;
   private Image stoneImage;
   private Image sandImage;
   private Image spiceImage;
   private Image mogulsImage;

   private Image errorImage;

   private Image cannonShellImage;
   private Image machineGunImage;
   private Image rocketSmallImage;

   private Image generalExplosion1Image;
   private Image generalExplosion2Image;
   private Image generalExplosion3Image;
   private Image generalExplosion4Image;
   private Image generalExplosion5Image;
   private Image generalExplosion6Image;
   private Image generalExplosion7Image;
   private Image generalExplosion8Image;
   private Image generalExplosion9Image;
   private Image generalExplosion10Image;
   private Image generalExplosion11Image;
   private Image generalExplosion12Image;
   private Image generalExplosion13Image;
   private Image machineGunHit1Image;
   private Image machineGunHit2Image;
   private Image machineGunHit3Image;
   private Image machineGunHit4Image;
   private Image machineGunHit5Image;
   private Image machineGunHit6Image;
   private Image machineGunHit7Image;
   private Image machineGunHit8Image;
   private Image smallExplosion1Image;
   private Image smallExplosion2Image;
   private Image smallExplosion3Image;
   private Image smallExplosion4Image;
   private Image smallExplosion5Image;
   private Image smallExplosion6Image;
   private Image smallExplosion7Image;
   private Image smallExplosion8Image;

   /*******************************************/

   private BufferedImage aAssaultBuggyBI;
   private BufferedImage oAssaultBuggyBI;
   private BufferedImage hAssaultBuggyBI;
   private BufferedImage aCombatTankBI;
   private BufferedImage oCombatTankBI;
   private BufferedImage hCombatTankBI;
   private BufferedImage combatTurretBI;
   private BufferedImage aHarvestorBI;
   private BufferedImage hHarvestorBI;
   private BufferedImage oHarvestorBI;
   private BufferedImage aHarvestorHarvestingBI;
   private BufferedImage hHarvestorHarvestingBI;
   private BufferedImage oHarvestorHarvestingBI;
   private BufferedImage oHeavyTroopersBI;
   private BufferedImage hHeavyTroopersBI;
   private BufferedImage aHeavyTroopersBI;

   private BufferedImage aConstructionYardBI;
   private BufferedImage aConstructionYardBI1;
   private BufferedImage aConstructionYardBI2;
   private BufferedImage oConstructionYardBI;
   private BufferedImage oConstructionYardBI1;
   private BufferedImage oConstructionYardBI2;
   private BufferedImage hConstructionYardBI;
   private BufferedImage hConstructionYardBI1;
   private BufferedImage hConstructionYardBI2;
   private BufferedImage aSingleSlabBI;
   private BufferedImage hSingleSlabBI;
   private BufferedImage oSingleSlabBI;
   private BufferedImage aSlab4BI;
   private BufferedImage hSlab4BI;
   private BufferedImage oSlab4BI;
   private BufferedImage aLightFactoryBI;
   private BufferedImage hLightFactoryBI;
   private BufferedImage oLightFactoryBI;
   private BufferedImage aHeavyFactoryBI;
   private BufferedImage hHeavyFactoryBI;
   private BufferedImage oHeavyFactoryBI;
   private BufferedImage aRadarBI;
   private BufferedImage hRadarBI;
   private BufferedImage oRadarBI;
   private BufferedImage aRefineryBI;
   private BufferedImage hRefineryBI;
   private BufferedImage oRefineryBI;
   private BufferedImage aHarvestorDockedBI;
   private BufferedImage hHarvestorDockedBI;
   private BufferedImage oHarvestorDockedBI;
   private BufferedImage aSiloBI;
   private BufferedImage hSiloBI;
   private BufferedImage oSiloBI;
   private BufferedImage aWallBI;
   private BufferedImage hWallBI;
   private BufferedImage oWallBI;
   private BufferedImage aWindtrapBI;
   private BufferedImage hWindtrapBI;
   private BufferedImage oWindtrapBI;
   private BufferedImage aWORBI;
   private BufferedImage hWORBI;
   private BufferedImage oWORBI;
   private BufferedImage aCombatTurretBI;
   private BufferedImage hCombatTurretBI;
   private BufferedImage oCombatTurretBI;
   private BufferedImage combatTurretTurretBI;


   private BufferedImage CYLargeBI;
   private BufferedImage SSLargeBI;
   private BufferedImage S4LargeBI;
   private BufferedImage LFLargeBI;
   private BufferedImage HFLargeBI;
   private BufferedImage RALargeBI;
   private BufferedImage RELargeBI;
   private BufferedImage SLargeBI;
   private BufferedImage WALargeBI;
   private BufferedImage WILargeBI;
   private BufferedImage WOLargeBI;
   private BufferedImage CALargeBI; //units large
   private BufferedImage CTLargeBI;
   private BufferedImage HALargeBI;
   private BufferedImage HTSLargeBI;
   private BufferedImage LILargeBI;
   private BufferedImage MCLargeBI;
   private BufferedImage ORLargeBI;
   private BufferedImage ABLargeBI;
   private BufferedImage STLargeBI;
   private BufferedImage TRLargeBI;
   private BufferedImage SWLargeBI;
   private BufferedImage HTLargeBI;
   private BufferedImage CTTLargeBI;

   private BufferedImage CYSmallBI;
   private BufferedImage SSSmallBI;
   private BufferedImage S4SmallBI;
   private BufferedImage LFSmallBI;
   private BufferedImage HFSmallBI;
   private BufferedImage RASmallBI;
   private BufferedImage RESmallBI;
   private BufferedImage SSmallBI;
   private BufferedImage WASmallBI;
   private BufferedImage WISmallBI;
   private BufferedImage WOSmallBI;
   private BufferedImage CASmallBI; //units small
   private BufferedImage CTSmallBI;
   private BufferedImage HASmallBI;
   private BufferedImage HTSSmallBI;
   private BufferedImage LISmallBI;
   private BufferedImage MCSmallBI;
   private BufferedImage ORSmallBI;
   private BufferedImage ABSmallBI;
   private BufferedImage STSmallBI;
   private BufferedImage TRSmallBI;
   private BufferedImage SWSmallBI;
   private BufferedImage HTSmallBI;
   private BufferedImage CTTSmallBI;

   private BufferedImage highlighterBI1;
   private BufferedImage highlighterBI2;
   private BufferedImage highlighterBI3;

   private BufferedImage rockBI;
   private BufferedImage stoneBI;
   private BufferedImage sandBI;
   private BufferedImage spiceBI;
   private BufferedImage mogulsBI;

   private BufferedImage errorBI;
   private BufferedImage bigBlankBI;

   private BufferedImage cannonShellBI;
   private BufferedImage machineGunBI;
   private BufferedImage rocketSmallBI;

   private BufferedImage generalExplosion1BI;
   private BufferedImage generalExplosion2BI;
   private BufferedImage generalExplosion3BI;
   private BufferedImage generalExplosion4BI;
   private BufferedImage generalExplosion5BI;
   private BufferedImage generalExplosion6BI;
   private BufferedImage generalExplosion7BI;
   private BufferedImage generalExplosion8BI;
   private BufferedImage generalExplosion9BI;
   private BufferedImage generalExplosion10BI;
   private BufferedImage generalExplosion11BI;
   private BufferedImage generalExplosion12BI;
   private BufferedImage generalExplosion13BI;
   private BufferedImage machineGunHit1BI;
   private BufferedImage machineGunHit2BI;
   private BufferedImage machineGunHit3BI;
   private BufferedImage machineGunHit4BI;
   private BufferedImage machineGunHit5BI;
   private BufferedImage machineGunHit6BI;
   private BufferedImage machineGunHit7BI;
   private BufferedImage machineGunHit8BI;
   private BufferedImage smallExplosion1BI;
   private BufferedImage smallExplosion2BI;
   private BufferedImage smallExplosion3BI;
   private BufferedImage smallExplosion4BI;
   private BufferedImage smallExplosion5BI;
   private BufferedImage smallExplosion6BI;
   private BufferedImage smallExplosion7BI;
   private BufferedImage smallExplosion8BI;

   /***********************************************/

  /** The constructor for ImageInformation
  */
  public ImageInformation()
  {  this.loadImages();
  }

  public BufferedImage getImage(String name, String house)
  {
     if (house != null)
     {   if (name.equals(Constants.ASSAULT_BUGGY) && house.equals(Constants.ATREIDES))
        {  return this.aAssaultBuggyBI;
        } else if (name.equals(Constants.ASSAULT_BUGGY) && house.equals(Constants.ORDOS))
        {  return this.oAssaultBuggyBI;
        } else if (name.equals(Constants.ASSAULT_BUGGY) && house.equals(Constants.HARKONNEN))
        {  return this.hAssaultBuggyBI;
        } else if (name.equals(Constants.COMBAT_TANK) && house.equals(Constants.ATREIDES))
        {  return this.aCombatTankBI;
        } else if (name.equals(Constants.COMBAT_TANK) && house.equals(Constants.ORDOS))
        {  return this.oCombatTankBI;
        } else if (name.equals(Constants.COMBAT_TANK) && house.equals(Constants.HARKONNEN))
        {  return this.hCombatTankBI;
        } else if (name.equals(Constants.HARVESTOR) && house.equals(Constants.HARKONNEN))
        {  return this.hHarvestorBI;
        } else if (name.equals(Constants.HARVESTOR) && house.equals(Constants.ORDOS))
        {  return this.oHarvestorBI;
        } else if (name.equals(Constants.HARVESTOR) && house.equals(Constants.ATREIDES))
        {  return this.aHarvestorBI;
        } else if (name.equals(Constants.HEAVY_TROOPERS) && house.equals(Constants.ATREIDES))
        {  return this.aHeavyTroopersBI;
        } else if (name.equals(Constants.HEAVY_TROOPERS) && house.equals(Constants.ORDOS))
        {  return this.oHeavyTroopersBI;
        } else if (name.equals(Constants.HEAVY_TROOPERS) && house.equals(Constants.HARKONNEN))
        {  return this.hHeavyTroopersBI;
        } else if (name.equals(Constants.HARVESTING) && house.equals(Constants.ATREIDES))
        {  return this.aHarvestorHarvestingBI;
        } else if (name.equals(Constants.HARVESTING) && house.equals(Constants.HARKONNEN))
        {  return this.aHarvestorHarvestingBI;
        } else if (name.equals(Constants.HARVESTING) && house.equals(Constants.ORDOS))
        {  return this.aHarvestorHarvestingBI;
        } else if (name.equals(Constants.CONSTRUCTION_YARD) && house.equals(Constants.ATREIDES))
        {  return this.aConstructionYardBI;
        } else if (name.equals(Constants.CONSTRUCTION_YARD) && house.equals(Constants.ORDOS))
        {  return this.oConstructionYardBI;
        } else if (name.equals(Constants.CONSTRUCTION_YARD) && house.equals(Constants.HARKONNEN))
        {  return this.hConstructionYardBI;
        } else if (name.equals(Constants.SINGLE_SLAB) && house.equals(Constants.ATREIDES))
        {  return this.aSingleSlabBI;
        } else if (name.equals(Constants.SINGLE_SLAB) && house.equals(Constants.ORDOS))
        {  return this.oSingleSlabBI;
        } else if (name.equals(Constants.SINGLE_SLAB) && house.equals(Constants.HARKONNEN))
        {  return this.hSingleSlabBI;
        } else if (name.equals(Constants.SLAB_4) && house.equals(Constants.ATREIDES))
        {  return this.aSlab4BI;
        } else if (name.equals(Constants.SLAB_4) && house.equals(Constants.ORDOS))
        {  return this.oSlab4BI;
        } else if (name.equals(Constants.SLAB_4) && house.equals(Constants.HARKONNEN))
        {  return this.hSlab4BI;
        } else if (name.equals(Constants.LIGHT_FACTORY) && house.equals(Constants.ATREIDES))
        {  return this.aLightFactoryBI;
        } else if (name.equals(Constants.LIGHT_FACTORY) && house.equals(Constants.ORDOS))
        {  return this.oLightFactoryBI;
        } else if (name.equals(Constants.LIGHT_FACTORY) && house.equals(Constants.HARKONNEN))
        {  return this.hLightFactoryBI;
        } else if (name.equals(Constants.HEAVY_FACTORY) && house.equals(Constants.ATREIDES))
        {  return this.aHeavyFactoryBI;
        } else if (name.equals(Constants.HEAVY_FACTORY) && house.equals(Constants.ORDOS))
        {  return this.oHeavyFactoryBI;
        } else if (name.equals(Constants.HEAVY_FACTORY) && house.equals(Constants.HARKONNEN))
        {  return this.hHeavyFactoryBI;
        } else if (name.equals(Constants.RADAR) && house.equals(Constants.ATREIDES))
        {  return this.aRadarBI;
        } else if (name.equals(Constants.RADAR) && house.equals(Constants.ORDOS))
        {  return this.oRadarBI;
        } else if (name.equals(Constants.RADAR) && house.equals(Constants.HARKONNEN))
        {  return this.hRadarBI;
        } else if (name.equals(Constants.REFINERY) && house.equals(Constants.ATREIDES))
        {  return this.aRefineryBI;
        } else if (name.equals(Constants.REFINERY) && house.equals(Constants.ORDOS))
        {  return this.oRefineryBI;
        } else if (name.equals(Constants.REFINERY) && house.equals(Constants.HARKONNEN))
        {  return this.hRefineryBI;
        } else if (name.equals(Constants.COMBAT_TURRET) && house.equals(Constants.ATREIDES))
        {  return this.aCombatTurretBI;
        } else if (name.equals(Constants.COMBAT_TURRET) && house.equals(Constants.ORDOS))
        {  return this.oCombatTurretBI;
        } else if (name.equals(Constants.COMBAT_TURRET) && house.equals(Constants.HARKONNEN))
        {  return this.hCombatTurretBI;
        } else if (name.equals(Constants.HARVESTOR_DOCKED) && house.equals(Constants.HARKONNEN))
        {  return this.hHarvestorDockedBI;
        } else if (name.equals(Constants.HARVESTOR_DOCKED) && house.equals(Constants.ATREIDES))
        {  return this.aHarvestorDockedBI;
        } else if (name.equals(Constants.HARVESTOR_DOCKED) && house.equals(Constants.ORDOS))
        {  return this.oHarvestorDockedBI;
        } else if (name.equals(Constants.SILO) && house.equals(Constants.ATREIDES))
        {  return this.aSiloBI;
        } else if (name.equals(Constants.SILO) && house.equals(Constants.ORDOS))
        {  return this.oSiloBI;
        } else if (name.equals(Constants.SILO) && house.equals(Constants.HARKONNEN))
        {  return this.hSiloBI;
        } else if (name.equals(Constants.WALL) && house.equals(Constants.ATREIDES))
        {  return this.aWallBI;
        } else if (name.equals(Constants.WALL) && house.equals(Constants.ORDOS))
        {  return this.oWallBI;
        } else if (name.equals(Constants.WALL) && house.equals(Constants.HARKONNEN))
        {  return this.hWallBI;
        } else if (name.equals(Constants.WINDTRAP) && house.equals(Constants.ATREIDES))
        {  return this.aWindtrapBI;
        } else if (name.equals(Constants.WINDTRAP) && house.equals(Constants.ORDOS))
        {  return this.oWindtrapBI;
        } else if (name.equals(Constants.WINDTRAP) && house.equals(Constants.HARKONNEN))
        {  return this.hWindtrapBI;
        } else if (name.equals(Constants.WOR) && house.equals(Constants.ATREIDES))
        {  return this.aWORBI;
        } else if (name.equals(Constants.WOR) && house.equals(Constants.ORDOS))
        {  return this.oWORBI;
        } else if (name.equals(Constants.WOR) && house.equals(Constants.HARKONNEN))
        {  return this.hWORBI;
        } else if (name.equals(Constants.COMBAT_TURRET) && house.equals(Constants.HARKONNEN))
        {  return this.hCombatTurretBI;
        } else if (name.equals(Constants.COMBAT_TURRET) && house.equals(Constants.ATREIDES))
        {  return this.aCombatTurretBI;
        } else if (name.equals(Constants.COMBAT_TURRET) && house.equals(Constants.ORDOS))
        {  return this.oCombatTurretBI;
        }
     } else
     {  if (name.equals(Constants.SAND))
        {  return this.sandBI;
        } else if (name.equals(Constants.SPICE))
        {  return this.spiceBI;
        } else if (name.equals(Constants.STONE))
        {  return this.stoneBI;
        } else if (name.equals(Constants.ROCK))
        {  return this.rockBI;
        } else if (name.equals(Constants.MOGULS))
        {  return this.mogulsBI;
        } else if (name.equals(Constants.COMBAT_TANK))
        {  return this.combatTurretBI;
        } else if (name.equals(Constants.COMBAT_TURRET))
        {  return this.combatTurretTurretBI;
        } else if (name.equals(Constants.CANNON_SHELL))
        {  return this.cannonShellBI;
        } else if (name.equals(Constants.MACHINE_GUN))
        {  return this.machineGunBI;
        } else if (name.equals(Constants.LIGHT_ROCKET))
        {  return this.rocketSmallBI;
        }
     }

     return this.errorBI;
  }

  public BufferedImage getImage(String name, String house, double level)
  {
     if (house != null)
     {  if (name.equals(Constants.CONSTRUCTION_YARD) && house.equals(Constants.ATREIDES))
        {  if (level >= 000.00 && level <= 300.00)
           {  return this.aConstructionYardBI1;
           } else if (level > 300.00)
           {  return this.aConstructionYardBI2;
           }
        } else if (name.equals(Constants.CONSTRUCTION_YARD) && house.equals(Constants.ORDOS))
        {  if (level >= 000.00 && level <= 300.00)
           {  return this.oConstructionYardBI1;
           } else if (level > 300.00)
           {  return this.oConstructionYardBI2;
           }
        } else if (name.equals(Constants.CONSTRUCTION_YARD) && house.equals(Constants.HARKONNEN))
        {  if (level >= 000.00 && level <= 300.00)
           {  return this.hConstructionYardBI1;
           } else if (level > 300.00)
           {  return this.hConstructionYardBI2;
           }
        }
     } else
     {  if (name.equals(Constants.HIGHLIGHTER_UNIT))
        {  if (level >= 000.00 && level < 200.00)
           {  return this.highlighterBI1;
           } else if (level >= 200.00 && level < 400.00)
           {  return this.highlighterBI2;
           } else if (level >= 400.00 && level <= 600.00)
           {  return this.highlighterBI3;
           }
        } else if (name.equals(Constants.GENERAL_EXPLOSION))
        {  if (level == 1)
           {  return this.generalExplosion1BI;
           } else if (level == 2)
           {  return this.generalExplosion2BI;
           } else if (level == 3)
           {  return this.generalExplosion3BI;
           } else if (level == 4)
           {  return this.generalExplosion4BI;
           } else if (level == 5)
           {  return this.generalExplosion5BI;
           } else if (level == 6)
           {  return this.generalExplosion6BI;
           } else if (level == 7)
           {  return this.generalExplosion7BI;
           } else if (level == 8)
           {  return this.generalExplosion8BI;
           } else if (level == 9)
           {  return this.generalExplosion9BI;
           } else if (level == 10)
           {  return this.generalExplosion10BI;
           } else if (level == 11)
           {  return this.generalExplosion11BI;
           } else if (level == 12)
           {  return this.generalExplosion12BI;
           } else //if (level >= 13)
           {  return this.generalExplosion13BI;
           }
        } else if (name.equals(Constants.MACHINE_GUN_HIT))
        {  if (level == 1)
           {  return this.machineGunHit1BI;
           } else if (level == 2)
           {  return this.machineGunHit2BI;
           } else if (level == 3)
           {  return this.machineGunHit3BI;
           } else if (level == 4)
           {  return this.machineGunHit4BI;
           } else if (level == 5)
           {  return this.machineGunHit5BI;
           } else if (level == 6)
           {  return this.machineGunHit6BI;
           } else if (level == 7)
           {  return this.machineGunHit7BI;
           } else //if (level >= 8)
           {  return this.machineGunHit8BI;
           }
        } else if (name.equals(Constants.SMALL_EXPLOSION))
        {  if (level == 1)
           {  return this.smallExplosion1BI;
           } else if (level == 2)
           {  return this.smallExplosion2BI;
           } else if (level == 3)
           {  return this.smallExplosion3BI;
           } else if (level == 4)
           {  return this.smallExplosion4BI;
           } else if (level == 5)
           {  return this.smallExplosion5BI;
           } else if (level == 6)
           {  return this.smallExplosion6BI;
           } else if (level == 7)
           {  return this.smallExplosion7BI;
           } else //if (level >= 8)
           {  return this.smallExplosion8BI;
           }
        }
     }

     if (name.equals(Constants.CONSTRUCTION_YARD) && level == 0)
       {  return this.CYLargeBI;
       }  else if (name.equals(Constants.SINGLE_SLAB) && level == 0)
       {  return this.SSLargeBI;
       }  else if (name.equals(Constants.SLAB_4) && level == 0)
       {  return this.S4LargeBI;
       }  else if (name.equals(Constants.LIGHT_FACTORY) && level == 0)
       {  return this.LFLargeBI;
       }  else if (name.equals(Constants.HEAVY_FACTORY) && level == 0)
       {  return this.HFLargeBI;
       }  else if (name.equals(Constants.RADAR) && level == 0)
       {  return this.RALargeBI;
       }  else if (name.equals(Constants.REFINERY) && level == 0)
       {  return this.RELargeBI;
       }  else if (name.equals(Constants.SILO) && level == 0)
       {  return this.SLargeBI;
       }  else if (name.equals(Constants.WALL) && level == 0)
       {  return this.WALargeBI;
       }  else if (name.equals(Constants.WINDTRAP) && level == 0)
       {  return this.WILargeBI;
       }  else if (name.equals(Constants.WOR) && level == 0)
       {  return this.WOLargeBI;
       }  else if (name.equals(Constants.CARRYALL) && level == 0)
       {  return this.CALargeBI;
       }  else if (name.equals(Constants.COMBAT_TANK) && level == 0)
       {  return this.CTLargeBI;
       }  else if (name.equals(Constants.HARVESTOR) && level == 0)
       {  return this.HALargeBI;
       }  else if (name.equals(Constants.HEAVY_TROOPERS) && level == 0)
       {  return this.HTSLargeBI;
       }  else if (name.equals(Constants.HEAVY_TROOPER) && level == 0)
       {  return this.HTLargeBI;
       }  else if (name.equals(Constants.LIGHT_INFANTRY) && level == 0)
       {  return this.LILargeBI;
       }  else if (name.equals(Constants.MCV) && level == 0)
       {  return this.MCLargeBI;
       }  else if (name.equals(Constants.ORDOS_RAIDER_TRIKE) && level == 0)
       {  return this.ORLargeBI;
       }  else if (name.equals(Constants.ASSAULT_BUGGY) && level == 0)
       {  return this.ABLargeBI;
       }  else if (name.equals(Constants.TRIKE) && level == 0)
       {  return this.TRLargeBI;
       }  else if (name.equals(Constants.COMBAT_TURRET) && level == 0)
       {  return this.CTTLargeBI;
       }  else if (name.equals(Constants.CONSTRUCTION_YARD) && level == 1)
       {  return this.CYSmallBI;
       }  else if (name.equals(Constants.SINGLE_SLAB) && level == 1)
       {  return this.SSSmallBI;
       }  else if (name.equals(Constants.SLAB_4) && level == 1)
       {  return this.S4SmallBI;
       }  else if (name.equals(Constants.LIGHT_FACTORY) && level == 1)
       {  return this.LFSmallBI;
       }  else if (name.equals(Constants.HEAVY_FACTORY) && level == 1)
       {  return this.HFSmallBI;
       }  else if (name.equals(Constants.RADAR) && level == 1)
       {  return this.RASmallBI;
       }  else if (name.equals(Constants.REFINERY) && level == 1)
       {  return this.RESmallBI;
       }  else if (name.equals(Constants.SILO) && level == 1)
       {  return this.SSmallBI;
       }  else if (name.equals(Constants.WALL) && level == 1)
       {  return this.WASmallBI;
       }  else if (name.equals(Constants.WINDTRAP) && level == 1)
       {  return this.WISmallBI;
       }  else if (name.equals(Constants.WOR) && level == 1)
       {  return this.WOSmallBI;
       }   else if (name.equals(Constants.CARRYALL) && level == 1)
       {  return this.CASmallBI;
       }  else if (name.equals(Constants.COMBAT_TANK) && level == 1)
       {  return this.CTSmallBI;
       }  else if (name.equals(Constants.HARVESTOR) && level == 1)
       {  return this.HASmallBI;
       }  else if (name.equals(Constants.HEAVY_TROOPERS) && level == 1)
       {  return this.HTSSmallBI;
       }  else if (name.equals(Constants.HEAVY_TROOPER) && level == 1)
       {  return this.HTSmallBI;
       }  else if (name.equals(Constants.LIGHT_INFANTRY) && level == 1)
       {  return this.LISmallBI;
       }  else if (name.equals(Constants.MCV) && level == 1)
       {  return this.MCSmallBI;
       }  else if (name.equals(Constants.ORDOS_RAIDER_TRIKE) && level == 1)
       {  return this.ORSmallBI;
       }  else if (name.equals(Constants.ASSAULT_BUGGY) && level == 1)
       {  return this.ABSmallBI;
       }  else if (name.equals(Constants.TRIKE) && level == 1)
       {  return this.TRSmallBI;
       }  else if (name.equals(Constants.COMBAT_TURRET) && level == 1)
       {  return this.CTTSmallBI;
       }
     return null;
     //return this.errorBI;
  }

  /** Loads all of the images from the harddrive into Image objects
  */
  public void loadImages()
  {  String fileName;

     fileName = Constants.IMAGE_PATH + "aAssaultBuggyy.GIF";
     this.aAssaultBuggyImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hAssaultBuggyy.GIF";
     this.hAssaultBuggyImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oAssaultBuggyy.GIF";
     this.oAssaultBuggyImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aCombatTank.GIF";
     this.aCombatTankImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hCombatTank.GIF";
     this.hCombatTankImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oCombatTank.GIF";
     this.oCombatTankImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "CombatTankTurret.GIF";
     this.combatTurretImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oHarvestor.GIF";
     this.oHarvestorImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hHarvestor.GIF";
     this.hHarvestorImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aHarvestor.GIF";
     this.aHarvestorImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oHarvestorHarvesting.GIF";
     this.oHarvestorHarvestingImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hHarvestorHarvesting.GIF";
     this.hHarvestorHarvestingImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aHarvestorHarvesting.GIF";
     this.aHarvestorHarvestingImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aHeavyTroopers.GIF";
     this.aHeavyTroopersImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oHeavyTroopers.GIF";
     this.oHeavyTroopersImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hHeavyTroopers.GIF";
     this.hHeavyTroopersImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aConstructionYard.GIF";
     this.aConstructionYardImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aConstructionYard1.GIF";
     this.aConstructionYardImage1 = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aConstructionYard2.GIF";
     this.aConstructionYardImage2 = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oConstructionYard.GIF";
     this.oConstructionYardImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oConstructionYard1.GIF";
     this.oConstructionYardImage1 = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oConstructionYard2.GIF";
     this.oConstructionYardImage2 = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hConstructionYard.GIF";
     this.hConstructionYardImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hConstructionYard1.GIF";
     this.hConstructionYardImage1 = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hConstructionYard2.GIF";
     this.hConstructionYardImage2 = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "AllSlab.GIF";
     this.aSingleSlabImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "AllSlab.GIF";
     this.oSingleSlabImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "AllSlab.GIF";
     this.hSingleSlabImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "All4Slab.GIF";
     this.aSlab4Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "All4Slab.GIF";
     this.oSlab4Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "All4Slab.GIF";
     this.hSlab4Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aLightFactory.GIF";
     this.aLightFactoryImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oLightFactory.GIF";
     this.oLightFactoryImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hLightFactory.GIF";
     this.hLightFactoryImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aHeavyFactory.GIF";
     this.aHeavyFactoryImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oHeavyFactory.GIF";
     this.oHeavyFactoryImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hHeavyFactory.GIF";
     this.hHeavyFactoryImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aRadar.GIF";
     this.aRadarImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oRadar.GIF";
     this.oRadarImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hRadar.GIF";
     this.hRadarImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aRefinery.GIF";
     this.aRefineryImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oRefinery.GIF";
     this.oRefineryImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hRefinery.GIF";
     this.hRefineryImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aHarvestorDocked.GIF";
     this.aHarvestorDockedImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oHarvestorDocked.GIF";
     this.oHarvestorDockedImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hHarvestorDocked.GIF";
     this.hHarvestorDockedImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aSilo.GIF";
     this.aSiloImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oSilo.GIF";
     this.oSiloImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hSilo.GIF";
     this.hSiloImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aWall.GIF";
     this.aWallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oWall.GIF";
     this.oWallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hWall.GIF";
     this.hWallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aWindtrap.GIF";
     this.aWindtrapImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oWindtrap.GIF";
     this.oWindtrapImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hWindtrap.GIF";
     this.hWindtrapImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aWOR.GIF";
     this.aWORImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oWOR.GIF";
     this.oWORImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hWOR.GIF";
     this.hWORImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "hCombatTurret.GIF";
     this.hCombatTurretImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "oCombatTurret.GIF";
     this.oCombatTurretImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "aCombatTurret.GIF";
     this.aCombatTurretImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "combatTurretTurret.GIF";
     this.combatTurretTurretImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "ERROR.GIF";
     this.CYLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "ERROR.GIF";
     this.CYSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SingleSlab.GIF";
     this.SSLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SingleSlab_Small.GIF";
     this.SSSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "4Slab.GIF";
     this.S4LargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "4Slab_Small.GIF";
     this.S4SmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "LightFactory.GIF";
     this.LFLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "LightFactory_Small.GIF";
     this.LFSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "HeavyFactory.GIF";
     this.HFLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "HeavyFactory_Small.GIF";
     this.HFSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Radar.GIF";
     this.RALargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Radar_Small.GIF";
     this.RASmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Refinery.GIF";
     this.RELargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Refinery_Small.GIF";
     this.RESmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Silo.GIF";
     this.SLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Silo_Small.GIF";
     this.SSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Wall.GIF";
     this.WALargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Wall_Small.GIF";
     this.WASmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Windtrap.GIF";
     this.WILargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Windtrap_Small.GIF";
     this.WISmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "WOR.GIF";
     this.WOLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "WOR_Small.GIF";
     this.WOSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "CombatTurret_Small.GIF";
     this.CTTSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "CombatTurret.GIF";
     this.CTTLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Carryall_Large.GIF";
     this.CALargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Carryall_Small.GIF";
     this.CASmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "CombatTank_Large.GIF";
     this.CTLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "CombatTank_Small.GIF";
     this.CTSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Harvestor_Large.GIF";
     this.HALargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Harvestor_Small.GIF";
     this.HASmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "HeavyTroopers_Large.GIF";
     this.HTSLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "HeavyTroopers_Small.GIF";
     this.HTSSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "LightInfantry_Large.GIF";
     this.LILargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "LightInfantry_Small.GIF";
     this.LISmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MCV_Large.GIF";
     this.MCLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MCV_Small.GIF";
     this.MCSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "AssaultBuggy_Large.GIF";
     this.ABLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "AssaultBuggy_Small.GIF";
     this.ABSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "HeavyTrooper_Large.GIF";
     this.HTLargeImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "HeavyTrooper_Small.GIF";
     this.HTSmallImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Sand.GIF";
     this.sandImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Spice.GIF";
     this.spiceImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Rock.GIF";
     this.rockImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Moguls.GIF";
     this.mogulsImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Stone.GIF";
     this.stoneImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "ERROR.GIF";
     this.errorImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Highlighter1.GIF";
     this.highlighterImage1 = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Highlighter2.GIF";
     this.highlighterImage2 = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "Highlighter3.GIF";
     this.highlighterImage3 = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "CannonShell.GIF";
     this.cannonShellImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MachineGun.GIF";
     this.machineGunImage = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "RocketSmall.GIF";
     this.rocketSmallImage = this.toolkit.getImage(fileName);

     /****explosions****/
     fileName = Constants.IMAGE_PATH + "GeneralExplosion1.GIF";
     this.generalExplosion1Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion2.GIF";
     this.generalExplosion2Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion3.GIF";
     this.generalExplosion3Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion4.GIF";
     this.generalExplosion4Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion5.GIF";
     this.generalExplosion5Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion6.GIF";
     this.generalExplosion6Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion7.GIF";
     this.generalExplosion7Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion8.GIF";
     this.generalExplosion8Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion9.GIF";
     this.generalExplosion9Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion10.GIF";
     this.generalExplosion10Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion11.GIF";
     this.generalExplosion11Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion12.GIF";
     this.generalExplosion12Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "GeneralExplosion13.GIF";
     this.generalExplosion13Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MachineGunHit1.GIF";
     this.machineGunHit1Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MachineGunHit2.GIF";
     this.machineGunHit2Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MachineGunHit3.GIF";
     this.machineGunHit3Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MachineGunHit4.GIF";
     this.machineGunHit4Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MachineGunHit5.GIF";
     this.machineGunHit5Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MachineGunHit6.GIF";
     this.machineGunHit6Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MachineGunHit7.GIF";
     this.machineGunHit7Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "MachineGunHit8.GIF";
     this.machineGunHit8Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SmallExplosion1.GIF";
     this.smallExplosion1Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SmallExplosion2.GIF";
     this.smallExplosion2Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SmallExplosion3.GIF";
     this.smallExplosion3Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SmallExplosion4.GIF";
     this.smallExplosion4Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SmallExplosion5.GIF";
     this.smallExplosion5Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SmallExplosion6.GIF";
     this.smallExplosion6Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SmallExplosion7.GIF";
     this.smallExplosion7Image = this.toolkit.getImage(fileName);

     fileName = Constants.IMAGE_PATH + "SmallExplosion8.GIF";
     this.smallExplosion8Image = this.toolkit.getImage(fileName);
  }

  public void bufferImages()
  {   Graphics2D biContext;

      //ERROR
      this.errorBI = new BufferedImage(Constants.GRID_SIZE, Constants.GRID_SIZE, BufferedImage.TYPE_INT_RGB);
      biContext = this.errorBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.errorImage, 0, 0, null);

      //Highlighter 1
      this.highlighterBI1 = new BufferedImage(Constants.GRID_SIZE, Constants.GRID_SIZE, Transparency.BITMASK);
      biContext = this.highlighterBI1.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.highlighterImage1, 0, 0, null);

      //Highlighter 2
      this.highlighterBI2 = new BufferedImage(Constants.GRID_SIZE, Constants.GRID_SIZE,Transparency.BITMASK);
      biContext = this.highlighterBI2.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.highlighterImage2, 0, 0, null);

      //Highlighter 3
      this.highlighterBI3 = new BufferedImage(Constants.GRID_SIZE, Constants.GRID_SIZE, Transparency.BITMASK);
      biContext = this.highlighterBI3.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.highlighterImage3, 0, 0, null);

      //Atreides Assault Buggy
      this.aAssaultBuggyBI = new BufferedImage(this.aAssaultBuggyImage.getWidth(null), this.aAssaultBuggyImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.aAssaultBuggyBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aAssaultBuggyImage, 0, 0, null);

      //Horkonnen Assault Buggy
      this.hAssaultBuggyBI = new BufferedImage(this.hAssaultBuggyImage.getWidth(null), this.hAssaultBuggyImage.getHeight(null), Transparency.BITMASK);
      biContext = this.hAssaultBuggyBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hAssaultBuggyImage, 0, 0, null);

      //Ordos Assault Buggy
      this.oAssaultBuggyBI = new BufferedImage(this.oAssaultBuggyImage.getWidth(null), this.oAssaultBuggyImage.getHeight(null), Transparency.BITMASK);
      biContext = this.oAssaultBuggyBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oAssaultBuggyImage, 0, 0, null);

      //Atreides Combat Tank
      this.aCombatTankBI = new BufferedImage(this.aCombatTankImage.getWidth(null), this.aCombatTankImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.aCombatTankBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aCombatTankImage, 0, 0, null);

      //Harkonnen Combat Tank
      this.hCombatTankBI = new BufferedImage(this.hCombatTankImage.getWidth(null), this.hCombatTankImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.hCombatTankBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hCombatTankImage, 0, 0, null);

      //Ordos Combat Tank
      this.oCombatTankBI = new BufferedImage(this.oCombatTankImage.getWidth(null), this.oCombatTankImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.oCombatTankBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oCombatTankImage, 0, 0, null);

      //Combat Tank Turret
      this.combatTurretBI = new BufferedImage(this.combatTurretImage.getWidth(null), this.combatTurretImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.combatTurretBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.combatTurretImage, 0, 0, null);

      //Atreides Harvestor
      this.aHarvestorBI = new BufferedImage(this.aHarvestorImage.getWidth(null), this.aHarvestorImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.aHarvestorBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aHarvestorImage, 0, 0, null);

      //Ordos Harvestor
      this.oHarvestorBI = new BufferedImage(this.oHarvestorImage.getWidth(null), this.oHarvestorImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.oHarvestorBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oHarvestorImage, 0, 0, null);

      //Harkonnen Harvestor
      this.hHarvestorBI = new BufferedImage(this.hHarvestorImage.getWidth(null), this.hHarvestorImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.hHarvestorBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hHarvestorImage, 0, 0, null);

      //Atreides Harvestor Harvesting
      this.aHarvestorHarvestingBI = new BufferedImage(this.aHarvestorHarvestingImage.getWidth(null), this.aHarvestorHarvestingImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.aHarvestorHarvestingBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aHarvestorHarvestingImage, 0, 0, null);

      //Ordos Harvestor Harvesting
      this.oHarvestorHarvestingBI = new BufferedImage(this.oHarvestorHarvestingImage.getWidth(null), this.oHarvestorHarvestingImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.oHarvestorHarvestingBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oHarvestorHarvestingImage, 0, 0, null);

      //Harkonnen Harvestor Harvesting
      this.hHarvestorHarvestingBI = new BufferedImage(this.hHarvestorHarvestingImage.getWidth(null), this.hHarvestorHarvestingImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.hHarvestorHarvestingBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hHarvestorHarvestingImage, 0, 0, null);

      //Harkonnen Heavy Troopers
      this.hHeavyTroopersBI = new BufferedImage(this.hHeavyTroopersImage.getWidth(null), this.hHeavyTroopersImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.hHeavyTroopersBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hHeavyTroopersImage, 0, 0, null);

      //Ordos Heavy Troopers
      this.oHeavyTroopersBI = new BufferedImage(this.oHeavyTroopersImage.getWidth(null), this.oHeavyTroopersImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.oHeavyTroopersBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oHeavyTroopersImage, 0, 0, null);

      //Atreides Heavy Troopers
      this.aHeavyTroopersBI = new BufferedImage(this.aHeavyTroopersImage.getWidth(null), this.aHeavyTroopersImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.aHeavyTroopersBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aHeavyTroopersImage, 0, 0, null);

      //Atreides Construction Yard
      this.aConstructionYardBI = new BufferedImage(this.aConstructionYardImage.getWidth(null), this.aConstructionYardImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aConstructionYardBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aConstructionYardImage, 0, 0, null);

      //Atreides Construction Yard 1
      this.aConstructionYardBI1 = new BufferedImage(this.aConstructionYardImage1.getWidth(null), this.aConstructionYardImage1.getHeight(null), Transparency.BITMASK);
      biContext = this.aConstructionYardBI1.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aConstructionYardImage1, 0, 0, null);

      //Atreides Construction Yard 2
      this.aConstructionYardBI2 = new BufferedImage(this.aConstructionYardImage2.getWidth(null), this.aConstructionYardImage2.getHeight(null), Transparency.BITMASK);
      biContext = this.aConstructionYardBI2.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aConstructionYardImage2, 0, 0, null);

      //Ordos Construction Yard
      this.oConstructionYardBI = new BufferedImage(this.oConstructionYardImage.getWidth(null), this.oConstructionYardImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oConstructionYardBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oConstructionYardImage, 0, 0, null);

      //Ordos Construction Yard 1
      this.oConstructionYardBI1 = new BufferedImage(this.oConstructionYardImage1.getWidth(null), this.oConstructionYardImage1.getHeight(null), Transparency.BITMASK);
      biContext = this.oConstructionYardBI1.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oConstructionYardImage1, 0, 0, null);

      //Ordos Construction Yard 2
      this.oConstructionYardBI2 = new BufferedImage(this.oConstructionYardImage2.getWidth(null), this.oConstructionYardImage2.getHeight(null), Transparency.BITMASK);
      biContext = this.oConstructionYardBI2.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oConstructionYardImage2, 0, 0, null);

      //Hardkonnen Construction Yard
      this.hConstructionYardBI = new BufferedImage(this.hConstructionYardImage.getWidth(null), this.hConstructionYardImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hConstructionYardBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hConstructionYardImage, 0, 0, null);

      //Hardkonnen Construction Yard 1
      this.hConstructionYardBI1 = new BufferedImage(this.hConstructionYardImage1.getWidth(null), this.hConstructionYardImage1.getHeight(null), Transparency.BITMASK);
      biContext = this.hConstructionYardBI1.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hConstructionYardImage1, 0, 0, null);

      //Hardkonnen Construction Yard 2
      this.hConstructionYardBI2 = new BufferedImage(this.hConstructionYardImage2.getWidth(null), this.hConstructionYardImage2.getHeight(null), Transparency.BITMASK);
      biContext = this.hConstructionYardBI2.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hConstructionYardImage2, 0, 0, null);

      //Atreides Single Slab
      this.aSingleSlabBI = new BufferedImage(this.aSingleSlabImage.getWidth(null), this.aSingleSlabImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aSingleSlabBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aSingleSlabImage, 0, 0, null);

      //Ordos Single Slab
      this.oSingleSlabBI = new BufferedImage(this.oSingleSlabImage.getWidth(null), this.oSingleSlabImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oSingleSlabBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oSingleSlabImage, 0, 0, null);

      //Harkonnen Single Slab
      this.hSingleSlabBI = new BufferedImage(this.hSingleSlabImage.getWidth(null), this.hSingleSlabImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hSingleSlabBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hSingleSlabImage, 0, 0, null);

      //Atreides Slab 4
      this.aSlab4BI = new BufferedImage(this.aSlab4Image.getWidth(null), this.aSlab4Image.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aSlab4BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aSlab4Image, 0, 0, null);

      //Harkonnen Slab 4
      this.hSlab4BI = new BufferedImage(this.hSlab4Image.getWidth(null), this.hSlab4Image.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hSlab4BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hSlab4Image, 0, 0, null);

      //Ordos Slab 4
      this.oSlab4BI = new BufferedImage(this.oSlab4Image.getWidth(null), this.oSlab4Image.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oSlab4BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oSlab4Image, 0, 0, null);

      //Atreides Light Factory
      this.aLightFactoryBI = new BufferedImage(this.aLightFactoryImage.getWidth(null), this.aLightFactoryImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aLightFactoryBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aLightFactoryImage, 0, 0, null);

      //Harkonnen Light Factory
      this.hLightFactoryBI = new BufferedImage(this.hLightFactoryImage.getWidth(null), this.hLightFactoryImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hLightFactoryBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hLightFactoryImage, 0, 0, null);

      //Ordos Light Factory
      this.oLightFactoryBI = new BufferedImage(this.oLightFactoryImage.getWidth(null), this.oLightFactoryImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oLightFactoryBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oLightFactoryImage, 0, 0, null);

      //Atreides Heavy Factory
      this.aHeavyFactoryBI = new BufferedImage(this.aHeavyFactoryImage.getWidth(null), this.aHeavyFactoryImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aHeavyFactoryBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aHeavyFactoryImage, 0, 0, null);

      //Harkonnen Heavy Factory
      this.hHeavyFactoryBI = new BufferedImage(this.hHeavyFactoryImage.getWidth(null), this.hHeavyFactoryImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hHeavyFactoryBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hHeavyFactoryImage, 0, 0, null);

      //Ordos Heavy Factory
      this.oHeavyFactoryBI = new BufferedImage(this.oHeavyFactoryImage.getWidth(null), this.oHeavyFactoryImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oHeavyFactoryBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oHeavyFactoryImage, 0, 0, null);

      //Atreides Radar
      this.aRadarBI = new BufferedImage(this.aRadarImage.getWidth(null), this.aRadarImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aRadarBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aRadarImage, 0, 0, null);

      //Harkonnen Radar
      this.hRadarBI = new BufferedImage(this.hRadarImage.getWidth(null), this.hRadarImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hRadarBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hRadarImage, 0, 0, null);

      //Ordos Radar
      this.oRadarBI = new BufferedImage(this.oRadarImage.getWidth(null), this.oRadarImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oRadarBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oRadarImage, 0, 0, null);

      //Atreides Refinery
      this.aRefineryBI = new BufferedImage(this.aRefineryImage.getWidth(null), this.aRefineryImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aRefineryBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aRefineryImage, 0, 0, null);

      //Harkonnen Refinery
      this.hRefineryBI = new BufferedImage(this.hRefineryImage.getWidth(null), this.hRefineryImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hRefineryBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hRefineryImage, 0, 0, null);

      //Ordos Refinery
      this.oRefineryBI = new BufferedImage(this.oRefineryImage.getWidth(null), this.oRefineryImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oRefineryBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oRefineryImage, 0, 0, null);

      //Atreides Harvestor Docked
      this.aHarvestorDockedBI = new BufferedImage(this.aHarvestorDockedImage.getWidth(null), this.aHarvestorDockedImage.getHeight(null), Transparency.BITMASK);
      biContext = this.aHarvestorDockedBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aHarvestorDockedImage, 0, 0, null);

      //Harkonnen Harvestor Docked
      this.hHarvestorDockedBI = new BufferedImage(this.hHarvestorDockedImage.getWidth(null), this.hHarvestorDockedImage.getHeight(null), Transparency.BITMASK);
      biContext = this.hHarvestorDockedBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hHarvestorDockedImage, 0, 0, null);

      //Ordos Harvestor Docked
      this.oHarvestorDockedBI = new BufferedImage(this.oHarvestorDockedImage.getWidth(null), this.oHarvestorDockedImage.getHeight(null), Transparency.BITMASK);
      biContext = this.oHarvestorDockedBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oHarvestorDockedImage, 0, 0, null);

      //Atreides Silo
      this.aSiloBI = new BufferedImage(this.aSiloImage.getWidth(null), this.aSiloImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aSiloBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aSiloImage, 0, 0, null);

      //Harkonnen Silo
      this.hSiloBI = new BufferedImage(this.hSiloImage.getWidth(null), this.hSiloImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hSiloBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hSiloImage, 0, 0, null);

      //Ordos Silo
      this.oSiloBI = new BufferedImage(this.oSiloImage.getWidth(null), this.oSiloImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oSiloBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oSiloImage, 0, 0, null);

      //Atreides Wall
      this.aWallBI = new BufferedImage(this.aWallImage.getWidth(null), this.aWallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aWallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aWallImage, 0, 0, null);

      //Harkonnen Wall
      this.hWallBI = new BufferedImage(this.hWallImage.getWidth(null), this.hWallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hWallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hWallImage, 0, 0, null);

      //Ordos Wall
      this.oWallBI = new BufferedImage(this.oWallImage.getWidth(null), this.oWallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oWallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oWallImage, 0, 0, null);

      //Atreides Windtrap
      this.aWindtrapBI = new BufferedImage(this.aWindtrapImage.getWidth(null), this.aWindtrapImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aWindtrapBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aWindtrapImage, 0, 0, null);

      //Harkonnen Windtrap
      this.hWindtrapBI = new BufferedImage(this.hWindtrapImage.getWidth(null), this.hWindtrapImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hWindtrapBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hWindtrapImage, 0, 0, null);

      //Ordos Windtrap
      this.oWindtrapBI = new BufferedImage(this.oWindtrapImage.getWidth(null), this.oWindtrapImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oWindtrapBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oWindtrapImage, 0, 0, null);

      //Atreides WOR
      this.aWORBI = new BufferedImage(this.aWORImage.getWidth(null), this.aWORImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.aWORBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aWORImage, 0, 0, null);

      //Harkonnen WOR
      this.hWORBI = new BufferedImage(this.hWORImage.getWidth(null), this.hWORImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.hWORBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hWORImage, 0, 0, null);

      //Ordos WOR
      this.oWORBI = new BufferedImage(this.oWORImage.getWidth(null), this.oWORImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.oWORBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oWORImage, 0, 0, null);

      //Harkonnen Combat Turret
      this.hCombatTurretBI = new BufferedImage(this.hCombatTurretImage.getWidth(null), this.hCombatTurretImage.getHeight(null),  Transparency.BITMASK);
      biContext = this.hCombatTurretBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.hCombatTurretImage, 0, 0, null);

      //Ordos Combat Turret
      this.oCombatTurretBI = new BufferedImage(this.oCombatTurretImage.getWidth(null), this.oCombatTurretImage.getHeight(null),  Transparency.BITMASK);
      biContext = this.oCombatTurretBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.oCombatTurretImage, 0, 0, null);

      //Atreides Combat Turret
      this.aCombatTurretBI = new BufferedImage(this.aCombatTurretImage.getWidth(null), this.aCombatTurretImage.getHeight(null),  Transparency.BITMASK);
      biContext = this.aCombatTurretBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.aCombatTurretImage, 0, 0, null);

      //Combat Turret Turret
      this.combatTurretTurretBI = new BufferedImage(this.combatTurretTurretImage.getWidth(null), this.combatTurretTurretImage.getHeight(null),  Transparency.BITMASK);
      biContext = this.combatTurretTurretBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.combatTurretTurretImage, 0, 0, null);


      //Construction Yard Large
      this.CYLargeBI = new BufferedImage(this.CYLargeImage.getWidth(null), this.CYLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.CYLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.CYLargeImage, 0, 0, null);

      //Construction Yard Small
      this.CYSmallBI = new BufferedImage(this.CYSmallImage.getWidth(null), this.CYSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.CYSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.CYSmallImage, 0, 0, null);

      //Single Slab Large
      this.SSLargeBI = new BufferedImage(this.SSLargeImage.getWidth(null), this.SSLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.SSLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.SSLargeImage, 0, 0, null);

      //SingleSlab Small
      this.SSSmallBI = new BufferedImage(this.SSSmallImage.getWidth(null), this.SSSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.SSSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.SSSmallImage, 0, 0, null);

      //4 Slab Large
      this.S4LargeBI = new BufferedImage(this.S4LargeImage.getWidth(null), this.S4LargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.S4LargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.S4LargeImage, 0, 0, null);

      //4 Slab Small
      this.S4SmallBI = new BufferedImage(this.S4SmallImage.getWidth(null), this.S4SmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.S4SmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.S4SmallImage, 0, 0, null);

      //Light Factory Large
      this.LFLargeBI = new BufferedImage(this.LFLargeImage.getWidth(null), this.LFLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.LFLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.LFLargeImage, 0, 0, null);

      //Light Factory Small
      this.LFSmallBI = new BufferedImage(this.LFSmallImage.getWidth(null), this.LFSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.LFSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.LFSmallImage, 0, 0, null);

      //Heavy Factory Large
      this.HFLargeBI = new BufferedImage(this.HFLargeImage.getWidth(null), this.HFLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.HFLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.HFLargeImage, 0, 0, null);

      //Heavy Factory Small
      this.HFSmallBI = new BufferedImage(this.HFSmallImage.getWidth(null), this.HFSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.HFSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.HFSmallImage, 0, 0, null);

      //Radar Large
      this.RALargeBI = new BufferedImage(this.RALargeImage.getWidth(null), this.RALargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.RALargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.RALargeImage, 0, 0, null);

      //Radar Small
      this.RASmallBI = new BufferedImage(this.RASmallImage.getWidth(null), this.RASmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.RASmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.RASmallImage, 0, 0, null);

      //Refinery Large
      this.RELargeBI = new BufferedImage(this.RELargeImage.getWidth(null), this.RELargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.RELargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.RELargeImage, 0, 0, null);

      //Refinery Small
      this.RESmallBI = new BufferedImage(this.RESmallImage.getWidth(null), this.RESmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.RESmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.RESmallImage, 0, 0, null);

      //Silo Large
      this.SLargeBI = new BufferedImage(this.SLargeImage.getWidth(null), this.SLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.SLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.SLargeImage, 0, 0, null);

      //Silo Small
      this.SSmallBI = new BufferedImage(this.SSmallImage.getWidth(null), this.SSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.SSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.SSmallImage, 0, 0, null);

      //Wall Large
      this.WALargeBI = new BufferedImage(this.WALargeImage.getWidth(null), this.WALargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.WALargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.WALargeImage, 0, 0, null);

      //Wall Small
      this.WASmallBI = new BufferedImage(this.WASmallImage.getWidth(null), this.WASmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.WASmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.WASmallImage, 0, 0, null);

      //Windtrap Large
      this.WILargeBI = new BufferedImage(this.WILargeImage.getWidth(null), this.WILargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.WILargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.WILargeImage, 0, 0, null);

      //Windtrap Small
      this.WISmallBI = new BufferedImage(this.WISmallImage.getWidth(null), this.WISmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.WISmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.WISmallImage, 0, 0, null);

      //WOR Large
      this.WOLargeBI = new BufferedImage(this.WOLargeImage.getWidth(null), this.WOLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.WOLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.WOLargeImage, 0, 0, null);

      //WOR Small
      this.WOSmallBI = new BufferedImage(this.WOSmallImage.getWidth(null), this.WOSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.WOSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.WOSmallImage, 0, 0, null);

      //Combat Turret Small
      this.CTTSmallBI = new BufferedImage(this.CTTSmallImage.getWidth(null), this.CTTSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.CTTSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.CTTSmallImage, 0, 0, null);

      //Combat Turret Large
      this.CTTLargeBI = new BufferedImage(this.CTTLargeImage.getWidth(null), this.CTTLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.CTTLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.CTTLargeImage, 0, 0, null);



      //Carryall Large
      this.CALargeBI = new BufferedImage(this.CALargeImage.getWidth(null), this.CALargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.CALargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.CALargeImage, 0, 0, null);

      //Carryall Small
      this.CASmallBI = new BufferedImage(this.CASmallImage.getWidth(null), this.CASmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.CASmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.CASmallImage, 0, 0, null);

      //Combat Tank Large
      this.CTLargeBI = new BufferedImage(this.CTLargeImage.getWidth(null), this.CTLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.CTLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.CTLargeImage, 0, 0, null);

      //Combat Tank Small
      this.CTSmallBI = new BufferedImage(this.CTSmallImage.getWidth(null), this.CTSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.CTSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.CTSmallImage, 0, 0, null);

      //Harvestor Large
      this.HALargeBI = new BufferedImage(this.HALargeImage.getWidth(null), this.HALargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.HALargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.HALargeImage, 0, 0, null);

      //Harvestor Small
      this.HASmallBI = new BufferedImage(this.HASmallImage.getWidth(null), this.HASmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.HASmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.HASmallImage, 0, 0, null);

      //Heavy Troopers Large
      this.HTSLargeBI = new BufferedImage(this.HTSLargeImage.getWidth(null), this.HTSLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.HTSLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.HTSLargeImage, 0, 0, null);

      //Heavy Troopers Small
      this.HTSSmallBI = new BufferedImage(this.HTSSmallImage.getWidth(null), this.HTSSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.HTSSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.HTSSmallImage, 0, 0, null);

      //Light Infantry Large
      this.LILargeBI = new BufferedImage(this.LILargeImage.getWidth(null), this.LILargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.LILargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.LILargeImage, 0, 0, null);

      //Light Infantry Small
      this.LISmallBI = new BufferedImage(this.LISmallImage.getWidth(null), this.LISmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.LISmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.LISmallImage, 0, 0, null);

      //MCV Large
      this.MCLargeBI = new BufferedImage(this.MCLargeImage.getWidth(null), this.MCLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.MCLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.MCLargeImage, 0, 0, null);

      //MCV Small
      this.MCSmallBI = new BufferedImage(this.MCSmallImage.getWidth(null), this.MCSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.MCSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.MCSmallImage, 0, 0, null);

      //Assault Buggy Large
      this.ABLargeBI = new BufferedImage(this.ABLargeImage.getWidth(null), this.ABLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.ABLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.ABLargeImage, 0, 0, null);

      //Assault Buggy Small
      this.ABSmallBI = new BufferedImage(this.ABSmallImage.getWidth(null), this.ABSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.ABSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.ABSmallImage, 0, 0, null);

      //Heavy Trooper Large
      this.HTLargeBI = new BufferedImage(this.HTLargeImage.getWidth(null), this.HTLargeImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.HTLargeBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.HTLargeImage, 0, 0, null);

      //Heavy Trooper Small
      this.HTSmallBI = new BufferedImage(this.HTSmallImage.getWidth(null), this.HTSmallImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.HTSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.HTSmallImage, 0, 0, null);

      //Moguls
      this.mogulsBI = new BufferedImage(this.mogulsImage.getWidth(null), this.mogulsImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.mogulsBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.mogulsImage, 0, 0, null);

      //Sand
      this.sandBI = new BufferedImage(this.sandImage.getWidth(null), this.sandImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.sandBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.sandImage, 0, 0, null);

      //Stone
      this.stoneBI = new BufferedImage(this.stoneImage.getWidth(null), this.stoneImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.stoneBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.stoneImage, 0, 0, null);

      //Spice
      this.spiceBI = new BufferedImage(this.spiceImage.getWidth(null), this.spiceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.spiceBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.spiceImage, 0, 0, null);

      //Rock
      this.rockBI = new BufferedImage(this.rockImage.getWidth(null), this.rockImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
      biContext = this.rockBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.rockImage, 0, 0, null);

      //Cannon Shell
      this.cannonShellBI = new BufferedImage(this.cannonShellImage.getWidth(null), this.cannonShellImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.cannonShellBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.cannonShellImage, 0, 0, null);

      //Machine Gun Bullets
      this.machineGunBI = new BufferedImage(this.machineGunImage.getWidth(null), this.machineGunImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.machineGunBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.machineGunImage, 0, 0, null);

      //Rocket Small
      this.rocketSmallBI = new BufferedImage(this.rocketSmallImage.getWidth(null), this.rocketSmallImage.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.rocketSmallBI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.rocketSmallImage, 0, 0, null);

      /***EXPLOSIONS***/
      //General Explosion (1 THROUGH 13)
      this.generalExplosion1BI = new BufferedImage(this.generalExplosion1Image.getWidth(null), this.generalExplosion1Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion1BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion1Image, 0, 0, null);

      this.generalExplosion2BI = new BufferedImage(this.generalExplosion2Image.getWidth(null), this.generalExplosion2Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion2BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion2Image, 0, 0, null);

      this.generalExplosion3BI = new BufferedImage(this.generalExplosion3Image.getWidth(null), this.generalExplosion3Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion3BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion3Image, 0, 0, null);

      this.generalExplosion4BI = new BufferedImage(this.generalExplosion4Image.getWidth(null), this.generalExplosion4Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion4BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion4Image, 0, 0, null);

      this.generalExplosion5BI = new BufferedImage(this.generalExplosion5Image.getWidth(null), this.generalExplosion5Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion5BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion5Image, 0, 0, null);

      this.generalExplosion6BI = new BufferedImage(this.generalExplosion6Image.getWidth(null), this.generalExplosion6Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion6BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion6Image, 0, 0, null);

      this.generalExplosion7BI = new BufferedImage(this.generalExplosion7Image.getWidth(null), this.generalExplosion7Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion7BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion7Image, 0, 0, null);

      this.generalExplosion8BI = new BufferedImage(this.generalExplosion8Image.getWidth(null), this.generalExplosion8Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion8BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion8Image, 0, 0, null);

      this.generalExplosion9BI = new BufferedImage(this.generalExplosion9Image.getWidth(null), this.generalExplosion9Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion9BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion9Image, 0, 0, null);

      this.generalExplosion10BI = new BufferedImage(this.generalExplosion10Image.getWidth(null), this.generalExplosion10Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion10BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion10Image, 0, 0, null);

      this.generalExplosion11BI = new BufferedImage(this.generalExplosion11Image.getWidth(null), this.generalExplosion11Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion11BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion11Image, 0, 0, null);

      this.generalExplosion12BI = new BufferedImage(this.generalExplosion12Image.getWidth(null), this.generalExplosion12Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion12BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion12Image, 0, 0, null);

      this.generalExplosion13BI = new BufferedImage(this.generalExplosion13Image.getWidth(null), this.generalExplosion13Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.generalExplosion13BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.generalExplosion13Image, 0, 0, null);

      //Machine Gun Hit (1 THROUGH 8)
      this.machineGunHit1BI = new BufferedImage(this.machineGunHit1Image.getWidth(null), this.machineGunHit1Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.machineGunHit1BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.machineGunHit1Image, 0, 0, null);

      this.machineGunHit2BI = new BufferedImage(this.machineGunHit2Image.getWidth(null), this.machineGunHit2Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.machineGunHit2BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.machineGunHit2Image, 0, 0, null);

      this.machineGunHit3BI = new BufferedImage(this.machineGunHit3Image.getWidth(null), this.machineGunHit3Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.machineGunHit3BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.machineGunHit3Image, 0, 0, null);

      this.machineGunHit4BI = new BufferedImage(this.machineGunHit4Image.getWidth(null), this.machineGunHit4Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.machineGunHit4BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.machineGunHit4Image, 0, 0, null);

      this.machineGunHit5BI = new BufferedImage(this.machineGunHit5Image.getWidth(null), this.machineGunHit5Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.machineGunHit5BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.machineGunHit5Image, 0, 0, null);

      this.machineGunHit6BI = new BufferedImage(this.machineGunHit6Image.getWidth(null), this.machineGunHit6Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.machineGunHit6BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.machineGunHit6Image, 0, 0, null);

      this.machineGunHit7BI = new BufferedImage(this.machineGunHit7Image.getWidth(null), this.machineGunHit7Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.machineGunHit7BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.machineGunHit7Image, 0, 0, null);

      this.machineGunHit8BI = new BufferedImage(this.machineGunHit8Image.getWidth(null), this.machineGunHit8Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.machineGunHit8BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.machineGunHit8Image, 0, 0, null);

      //Small Explosion (1 THROUGH 8)
      this.smallExplosion1BI = new BufferedImage(this.smallExplosion1Image.getWidth(null), this.smallExplosion1Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.smallExplosion1BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.smallExplosion1Image, 0, 0, null);

      this.smallExplosion2BI = new BufferedImage(this.smallExplosion2Image.getWidth(null), this.smallExplosion2Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.smallExplosion2BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.smallExplosion2Image, 0, 0, null);

      this.smallExplosion3BI = new BufferedImage(this.smallExplosion3Image.getWidth(null), this.smallExplosion3Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.smallExplosion3BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.smallExplosion3Image, 0, 0, null);

      this.smallExplosion4BI = new BufferedImage(this.smallExplosion4Image.getWidth(null), this.smallExplosion4Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.smallExplosion4BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.smallExplosion4Image, 0, 0, null);

      this.smallExplosion5BI = new BufferedImage(this.smallExplosion5Image.getWidth(null), this.smallExplosion5Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.smallExplosion5BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.smallExplosion5Image, 0, 0, null);

      this.smallExplosion6BI = new BufferedImage(this.smallExplosion6Image.getWidth(null), this.smallExplosion6Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.smallExplosion6BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.smallExplosion6Image, 0, 0, null);

      this.smallExplosion7BI = new BufferedImage(this.smallExplosion7Image.getWidth(null), this.smallExplosion7Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.smallExplosion7BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.smallExplosion7Image, 0, 0, null);

      this.smallExplosion8BI = new BufferedImage(this.smallExplosion8Image.getWidth(null), this.smallExplosion8Image.getHeight(null), Transparency.BITMASK);//BufferedImage.TYPE_INT_RGB);
      biContext = this.smallExplosion8BI.createGraphics();
      biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      biContext.drawImage(this.smallExplosion8Image, 0, 0, null);

      //this.bufferBlankImage();
      //this.bufferBigBlankImage();
  }

  public BufferedImage bigBlankBI(int x, int y)
  {  this.bufferBigBlankImage(x, y);
     return this.bigBlankBI;
  }

  private void bufferBigBlankImage(int x, int y)
  {  Graphics2D biContext;
     this.bigBlankBI = new BufferedImage(x * Constants.GRID_SIZE, y * Constants.GRID_SIZE, BufferedImage.TYPE_INT_RGB);
     biContext = this.bigBlankBI.createGraphics();
     biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
     biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
     biContext.fillRect(0, 0, x * Constants.GRID_SIZE, y * Constants.GRID_SIZE);
  }

  /*
  public BufferedImage blankBI()
  {
     this.bufferBlankImage();
     return this.blankBI;
  }

  public BufferedImage bigBlankBI()
  {  this.bufferBigBlankImage();
     return this.bigBlankBI;
  }

  private void bufferBlankImage()
  {  Graphics2D biContext;
     this.blankBI = new BufferedImage(Constants.GRID_SIZE, Constants.GRID_SIZE, BufferedImage.TYPE_INT_RGB);
     biContext = this.blankBI.createGraphics();
     biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
     biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
     biContext.drawImage(this.blankImage, 0, 0, null);
  }

  private void bufferBigBlankImage()
  {  Graphics2D biContext;
     this.bigBlankBI = new BufferedImage(Constants.GRID_SIZE, Constants.GRID_SIZE, BufferedImage.TYPE_INT_RGB);
     biContext = this.bigBlankBI.createGraphics();
     biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
     biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
     biContext.drawImage(this.bigBlankImage, 0, 0, null);
  }

  */
   public boolean prepareAllImages()
   {  this.toolkit.prepareImage(this.aAssaultBuggyImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oAssaultBuggyImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hAssaultBuggyImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aCombatTankImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oCombatTankImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hCombatTankImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.combatTurretImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hHarvestorImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aHarvestorImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oHarvestorImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hHarvestorHarvestingImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aHarvestorHarvestingImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oHarvestorHarvestingImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oHeavyTroopersImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hHeavyTroopersImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aHeavyTroopersImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aConstructionYardImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oConstructionYardImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hConstructionYardImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aConstructionYardImage1, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oConstructionYardImage1, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hConstructionYardImage1, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aConstructionYardImage2, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oConstructionYardImage2, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hConstructionYardImage2, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aSingleSlabImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hSingleSlabImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oSingleSlabImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aSlab4Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oSlab4Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hSlab4Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aLightFactoryImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oLightFactoryImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hLightFactoryImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aHeavyFactoryImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oHeavyFactoryImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hHeavyFactoryImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aRadarImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oRadarImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hRadarImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aRefineryImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oRefineryImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hRefineryImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aHarvestorDockedImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hHarvestorDockedImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oHarvestorDockedImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aSiloImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oSiloImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hSiloImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aWallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oWallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hWallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aWindtrapImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oWindtrapImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hWindtrapImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aWORImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oWORImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hWORImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.hCombatTurretImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.oCombatTurretImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.aCombatTurretImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.combatTurretTurretImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.CYLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.SSLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.S4LargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.LFLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.HFLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.RALargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.RELargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.SLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.WALargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.WILargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.WOLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.CALargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.CTLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.HALargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.HTSLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.LILargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.MCLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.ABLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.HTLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.CYSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.SSSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.S4SmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.LFSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.HFSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.RASmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.RESmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.SSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.WASmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.WISmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.WOSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.CASmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.CTSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.HASmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.HTSSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.LISmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.MCSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.ABSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.HTSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.CTTSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.CTTLargeImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.errorImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.sandImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.stoneImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.rockImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.spiceImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.mogulsImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.highlighterImage1, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.highlighterImage2, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.highlighterImage3, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.cannonShellImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.machineGunImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.rocketSmallImage, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion1Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion2Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion3Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion4Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion5Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion6Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion7Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion8Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion9Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion10Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion11Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion12Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.generalExplosion13Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.machineGunHit1Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.machineGunHit2Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.machineGunHit3Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.machineGunHit4Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.machineGunHit5Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.machineGunHit6Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.machineGunHit7Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.machineGunHit8Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.smallExplosion1Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.smallExplosion2Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.smallExplosion3Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.smallExplosion4Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.smallExplosion5Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.smallExplosion6Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.smallExplosion7Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);
      this.toolkit.prepareImage(this.smallExplosion8Image, Constants.GRID_SIZE, Constants.GRID_SIZE, null);

      return false;
   }
}


