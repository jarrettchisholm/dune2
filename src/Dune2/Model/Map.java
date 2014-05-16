package Dune2.Model;

import java.io.File;
import becker.io.TextInput;

public class Map
{  private String[][] baseLayer;
   private int sizeX;
   private int sizeY;
   private int[][] baseSpiceLayer;
   private Lands lands;

   public Map(TextInput in1, TextInput in2, Lands lands)
   {  this.lands = lands;
      //STRING BASE LAYER
      //TextInput in1 = new TextInput(Constants.MAPS_PATH + mapName);
      in1.readLine(); //passes by the map next "ie. Default.txt"
      this.sizeX = in1.readInt();
      in1.readLine();
      this.sizeY = in1.readInt();
      in1.readLine();

      this.baseLayer = new String[this.sizeX][this.sizeY];

      for (int j=0; j < this.sizeY; j++)
      {  for (int i=0; i < this.sizeX; i++)
         {  this.baseLayer[i][j] = in1.readToken();
         }
         in1.readLine();
      }
      in1.readLine();

      //SPICE BASE LAYER
      this.baseSpiceLayer = new int[this.sizeX][this.sizeY];
      //TextInput in2 = new TextInput(Constants.SAVED_GAME_PATH + savedMapName);
      for (int j=0; j < this.sizeY; j++)
      {  for (int i=0; i < this.sizeX; i++)
         {  this.baseSpiceLayer[i][j] = in2.readInt();
         }
         in2.readLine();
      }
      in2.readLine();
   }

   public int getBaseSpiceInfoAt(int x, int y)
   {  //System.out.println(x + ", " + y);
      return this.baseSpiceLayer[x][y];
   }

   public void setBaseSpiceInfoAt(int x, int y, int amount)
   {  this.baseSpiceLayer[x][y] = this.baseSpiceLayer[x][y] + amount;
   }

   public String getBaseLayerInfoAt(int x, int y)
   {  if(x >= 0 && x <= sizeX-1 && y >= 0 && y <= sizeY-1)
      {  return this.baseLayer[x][y];
      } else
      {  return null;
      }
   }

   public void setBaseLayerInfoAtXYToSand(int x, int y)
   {  this.baseLayer[x][y] = Constants.SAND;
   }

   public int getX()
   {  return this.sizeX;
   }

   public int getY()
   {  return this.sizeY;
   }

   public boolean areaClearAt(int x, int y)
   {
      if (x >= 0 && x < this.sizeX && y >= 0 && y < this.sizeY)
      {  return (!this.baseLayer[x][y].equals(Constants.STONE));
      } else
      {  System.out.println("Map location out of bounds: " + x + ", " + y);
         return false;
      }
   }
}
