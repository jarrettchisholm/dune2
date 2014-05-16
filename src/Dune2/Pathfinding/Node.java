package Dune2.Pathfinding;
import Dune2.Model.Constants;

public class Node
{  private Node parent;
   private int parentX;
   private int parentY;
   private int x;
   private int y;
   private int f;
   private int g;
   private int h;

   /* This constructor is used for pathfinding
   */
   public Node(int x, int y, int goalDistance, Node parent)
   {  this.parent = parent;
      this.parentX = parent.getX();
      this.parentY = parent.getY();
      this.x = x;
      this.y = y;
      if(x != this.parentX && y != this.parentY)
      {  this.g = parent.getG() + Constants.DIAGONAL_DISTANCE;
      } else
      {  this.g = parent.getG() + Constants.STRAIGHT_DISTANCE;
      }
      this.h = goalDistance;
      this.f = this.g + this.h;
   }

   /* This constructor is used for pathfinding
   */
   public Node(int x, int y, int goalDistance)
   {  this.parent = null;
      this.parentX = x;
      this.parentY = y;
      this.x = x;
      this.y = y;
      this.g = 0;
      this.h = goalDistance;
      this.f = h + g;
   }

   /* This constructor is used for finding 'free' coordinates
   */
   public Node(int x, int y)
   {  this.parent = null;
      this.parentX = x;
      this.parentY = y;
      this.x = x;
      this.y = y;
      this.g = 0;
      this.h = 0;
      this.f = h + g;
   }

   public Node getParentNode()
   {  return this.parent;
   }

   public void changeParentNode(Node newParent)
   {  this.parent = newParent;
      this.parentX = newParent.getX();
      this.parentY = newParent.getY();
   }

   public int getX()
   {  return this.x;
   }

   public int getY()
   {  return this.y;
   }

   public int getG()
   {  return this.g;
   }

   public int getH()
   {  return this.h;
   }

   public int getF()
   {  return this.f;
   }

   public int getParentX()
   {  return this.parentX;
   }

   public int getParentY()
   {  return this.parentY;
   }
} 