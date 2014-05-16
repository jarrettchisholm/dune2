package Dune2.Events;
import Dune2.Model.*;

import becker.io.TextInput;

public class Location
{  private int startX, startY, endX, endY;
   private String name;

   public Location(String name, int x1, int y1, int x2, int y2)
   {  this.name = name;
      this.startX = x1;
      this.startY = y1;
      this.endX = x2;
      this.endY = y2;
   }

   public Location(TextInput in)
   {  this.name = in.readLine();
      this.startX = in.readInt();
      in.readLine();
      this.startY = in.readInt();
      in.readLine();
      this.endX = in.readInt();
      in.readLine();
      this.endY = in.readInt();
      in.readLine();
      in.readLine();
   }

   public String getName()
   {  return this.name;
   }

   public int getStartX()
   {  return this.startX;
   }

   public int getStartY()
   {  return this.startY;
   }

   public int getEndX()
   {  return this.endX;
   }

   public int getEndY()
   {  return this.endY;
   }
} 