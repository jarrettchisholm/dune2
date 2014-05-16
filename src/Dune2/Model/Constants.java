package Dune2.Model;

public class Constants
{  public static final String PROGRAM_NAME = "Dune 2";
   public static final int MIN_VIEWS = 4;
   public static final double ALERT_COOLDOWN = 50.0;

   /************ATTACK TYPES***********************/
   public static final String SUICIDE = "Suicide";
   /***********************************************/

   /**********AI JOB TYPES*************************/
   public static final String ATTACK = "Attack";
   public static final String GUARD = "Guard";
   /***********************************************/

   //AI DIFFICULTY LEVELS
   public static final String EASY = "Easy";
   public static final String NORMAL = "Normal";
   public static final String HARD = "Hard";
   // DONE AI DIFFICULTY LEVELS

   public static final int STRAIGHT_DISTANCE = 10;
   public static final int DIAGONAL_DISTANCE = 14;

   public static final int RADAR_VIEW_X = 125;
   public static final int RADAR_VIEW_Y = 125;

   public static final int GAME_SPEED = 20;
   public static final int TIME_TO_COMPUTE_ALL_EVENTS = 2;
   public static final int GRID_SIZE = 40;
   public static final int DIAGONAL_MOVEMENT_SIZE = (int)(GRID_SIZE * 1.4142135);
   public static final int TURRET_SPEED = 19;
   public static final int MAX_HOLDING_CREDITS = 700;
   public static final int CREDIT_EXCHANGE_RATE = 7;
   public static final int SPICE_HARVEST_RATE = 2;
   public static final double HARVESTOR_HOLDING_TIME = 1.50;
   public static final double FIND_PATH_DELAY_TIME = 4.00;
   public static final int NUM_TIMES_FIND_PATH = 10;

   public static final String HIGHLIGHTER_UNIT = "Highlighter Unit";
   public static final String HIGHLIGHTER_BUILDING = "Highlighter Building";

   public static final int FIRST_STANDARD = 1;
   public static final int SECOND_STANDARD = 2;
   public static final int THIRD_STANDARD = 3;
   public static final int FOURTH_STANDARD = 4;

   public static final String ROCK = "R";
   public static final String SAND = "S";
   public static final String SPICE = "P";
   public static final String STONE = "T";
   public static final String MOGULS = "M";

   public static final String MACHINE_GUN = "Machine Gun";
   public static final String CANNON_SHELL = "Cannon Shell";
   public static final String MORTAR = "Mortar";
   public static final String LIGHT_ROCKET = "Light Rocket";
   public static final String HEAVY_ROCKET = "Heavy Rocket";

   public static final String LIGHT = "Light";
   public static final String MEDIUM = "Medium";
   public static final String HEAVY = "Heavy";
   public static final String FORTIFIED = "Fortified";
   public static final String PIERCING = "Piercing";
   public static final String BULGEONING = "Bulgeoning";
   public static final String EXPLOSIVE = "Explosive";
   public static final String NONE = "None";

   public static final String GENERAL_EXPLOSION = "General Explosion";
   public static final String SMALL_EXPLOSION = "Small Explosion";
   public static final String MACHINE_GUN_HIT = "Machine Gun Hit";

   /************************SOUND CONSTANTS*********************************/
   public static final String BUILDING_PLACED = "Building Placed";
   public static final String CANNOT_PLACE = "Cannot Place";
   public static final String UNIT_UNDER_ATTACK = "Unit Under Attack";
   public static final String BUILDING_UNDER_ATTACK = "Building Under Attack";
   /************************************************************************/

   public static final String ASSAULT_BUGGY = "Assault Buggy";
   public static final String CARRYALL = "Carryall";
   public static final String COMBAT_TANK = "Combat Tank";
   //public static final String COMBAT_TANK_TURRET = "Combat Tank Turret";
   public static final String HARVESTOR = "Harvestor";
   public static final String HARVESTOR_DOCKED = "Harvestor Docked";
   //public static final String HARVESTOR_HARVESTING = "Harvestor Harvesting";
   public static final String HEAVY_TROOPERS = "Heavy Troopers";
   public static final String HEAVY_TROOPER = "Heavy Trooper";
   public static final String LIGHT_INFANTRY = "Light Infantry";
   public static final String MCV = "MCV";
   public static final String ORDOS_RAIDER_TRIKE = "Ordoes Raider Trike";
   public static final String SARDAUKAR_TROOPERS = "Sardaukar Troopers";
   public static final String TRIKE = "Trike";
   public static final String SAND_WORM = "Sand Worm";

   public static final String CONSTRUCTION_YARD = "Construction Yard";
   public static final String SINGLE_SLAB = "Single Slab";
   public static final String SLAB_4 = "4 Slab";
   public static final String LIGHT_FACTORY = "Light Factory";
   public static final String HEAVY_FACTORY = "Heavy Factory";
   public static final String RADAR = "Radar";
   public static final String REFINERY = "Refinery";
   public static final String SILO = "Silo";
   public static final String WALL = "Wall";
   public static final String WINDTRAP = "Windtrap";
   public static final String WOR = "WOR";
   public static final String COMBAT_TURRET = "Combat Turret";

   public static final String MOVE = "Move";
   public static final String LEFT = "Left";
   public static final String RIGHT = "Right";
   public static final String LEFT_45 = "Left45";
   public static final String RIGHT_45 = "Right45";
   public static final String STOP = "Stop";
   public static final String PATROL = "Patrol";
   public static final String HARVESTING = "Harvesting";
   public static final String MOVE_AND_ATTACK = "Move And Attack";

   /************************** File Path Constants ************************/

   public static final String DEFAULT_FILES = System.getProperty("user.dir") + System.getProperty("file.separator") + "Default" + System.getProperty("file.separator");
   public static final String MAPS_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "Maps" + System.getProperty("file.separator");
   public static final String IMAGE_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "Images" + System.getProperty("file.separator");
   public static final String SAVED_GAME_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "Saved" + System.getProperty("file.separator");
   public static final String TEMP_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "Temp" + System.getProperty("file.separator");
   public static final String SOUND_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "Sounds" + System.getProperty("file.separator");

   /***********************************************************************/

   public static final String ATREIDES = "Atreides";
   public static final String ORDOS = "Ordos";
   public static final String HARKONNEN = "Harkonnen";

   /*********** EVENT CONSTANTS ***********************************************/
   public static final String ANY = "ANY";
   public static final String ALL = "ALL";
   public static final String EXACTLY = "Exactly";
   public static final String AT_LEAST = "At Least";
   public static final String AT_MOST = "At Most";

   //condition ID
   public static final String IF = "IF";
   public static final String BRING = "BRING";

   //action ID
   public static final String DISPLAY_TEXT = "DISPLAY TEXT";
   public static final String CREATE_UNITS = "CREATE UNITS";
   public static final String ORDER = "ORDER";
   /***************************************************************************/

   //MESSAGE CONSTANTS
   public static final int MAX_CHARS_FOR_DISPLAY_TEXT = 100;
   public static final int MAX_LINES_FOR_DISPLAY_TEXT = 12;
   public static final int MIN_DISTANCE_BETWEEN_LINES = 10;
}
