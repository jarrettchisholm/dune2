package Dune2.Pathfinding;

import becker.util.*;

public class CompiledList
{  private Node[] nodeList;
   private int[][] coordinates = new int[2][1];
   private int numNodes;
   private int numCoordinates;

   public CompiledList(Node[] nodes, int numNodes)
   {  this.nodeList = nodes;
      this.numNodes = numNodes;
      this.compileCoordinatesList();
   }

   private void compileCoordinatesList()
   {  Node previousNode = null;
      for (int i=0; i < this.numNodes; i++)
      {  if (this.nodeList[i].getH() == 0)
         {  previousNode = this.nodeList[i];
            this.addToCoordinates(previousNode.getX(), previousNode.getY());
         }
      }

      if(previousNode != null)
      {  int beginningDistance = previousNode.getG();
         while (beginningDistance != 0)
         {  for (int i=0; i < this.numNodes; i++)
            {  if (this.nodeList[i].getX() == previousNode.getParentX() && this.nodeList[i].getY() == previousNode.getParentY())
               {  previousNode = this.nodeList[i];
               }
            }
            beginningDistance = previousNode.getG();
            this.addToCoordinates(previousNode.getX(), previousNode.getY());
         }

         this.fixCoordinatesListDirection();
      } else
      {  this.coordinates = null;
      }
   }

   private void fixCoordinatesListDirection()
   {  int[][] temp = new int[2][this.numCoordinates];

      int index = 0;
      for (int i=this.numCoordinates-1; i >= 0; i--)
      {  temp[0][index] = this.coordinates[0][i];
         temp[1][index] = this.coordinates[1][i];
         index++;
      }

      this.coordinates = temp;
   }

   private void addToCoordinates(int x, int y)
   {  //System.out.println(x + "," + y);
      if (this.coordinates[0].length == this.numCoordinates)
      {  this.increaseCoordinatesListSize();
      }

      this.coordinates[0][this.numCoordinates] = x;
      this.coordinates[1][this.numCoordinates] = y;
      this.numCoordinates++;

   }

   private void increaseCoordinatesListSize()
   {  int[][] temp = new int[2][this.numCoordinates * 2];
      for (int i=0; i < this.numCoordinates; i++)
      {  temp[0][i] = this.coordinates[0][i];
         temp[1][i] = this.coordinates[1][i];
      }

      this.coordinates = temp;
   }

   private Node getNodeAt(int x, int y)
   {  for (int i=0; i < this.numNodes; i++)
      {  if (this.nodeList[i].getX() == x && this.nodeList[i].getY() == y)
         {  return this.nodeList[i];
         }
      }

      return null;
   }

   public int[][] getCoordinates()
   {  return this.coordinates;
   }

   public int getNumCoordinates()
   {  return this.numCoordinates;
   }

} 