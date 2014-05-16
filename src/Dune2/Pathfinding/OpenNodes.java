package Dune2.Pathfinding;

public class OpenNodes
{  private Node[] nodes;
   private int[][] coordinates;
   private int numNodes;

   public OpenNodes()
   {  this.resetNodes();
   }

   public void resetNodes()
   {  //System.out.println("Reseting Open Nodes");
      this.numNodes = 0;
      this.nodes = new Node[1];
      this.coordinates = new int[0][0];
   }

   public Node[] getAllNodes()
   {  return this.nodes;
   }

   public void addNode(Node node)
   {  //System.out.println("Adding Open Node");
      if(this.nodes.length == this.numNodes)
      {  this.increaseNodeSize();
      }

      boolean replacingDuplicate = false;
      for (int i=0; i < this.numNodes; i++)
      {  if (this.nodes[i].getX() == node.getX() && this.nodes[i].getY() == node.getY())
         {  this.nodes[i] = node;
            replacingDuplicate = true;
         }
      }
      if (!replacingDuplicate)
      {  this.nodes[this.numNodes] = node;
         this.numNodes++;
      }
   }

   private void increaseNodeSize()
   {  //System.out.println("Increasing Open Node Size");
      Node[] temp = new Node[this.numNodes * 2];
      for (int i=0; i < this.numNodes; i++)
      {  temp[i] = this.nodes[i];
      }

      this.nodes = temp;
   }

   public void removeNode(Node node)
   {  boolean foundNode = false;
      for (int i=0; i < this.numNodes; i++)
      {  if (foundNode)
         {  this.nodes[i-1] = this.nodes[i];
         }
         if (this.nodes[i] == node)
         {  foundNode = true;
         }
      }

      this.nodes[this.numNodes-1] = null;
      this.numNodes--;
   }

   public boolean checkForNode(int x, int y, int f)
   {  //System.out.println("Checking for Open Node");
      boolean flag = false;
      for (int i=0; i < this.numNodes; i++)
      {  if (this.nodes[i].getX() == x && this.nodes[i].getY() == y && this.nodes[i].getF() < f)
         {  flag = true;
         }
      }

      return flag;
   }

   public Node getNextNode()
   {  //System.out.println("getting Next Open Node");
      int index=0;
      int compare = this.nodes[index].getF();
      for (int i=1; i < this.numNodes; i++)
      {  if (compare > this.nodes[i].getF())
         {  index = i;
            compare = this.nodes[i].getF();
         }
      }

      return this.nodes[index];
   }
   

   public int getNumOfNodes()
   {  //System.out.println("Getting number of Open Nodes");
      return this.numNodes;
   }
} 