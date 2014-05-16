package Dune2.Pathfinding;

public class ClosedNodes
{

   private Node[] nodes;
   private int[][] coordinates;
   private int numNodes;

   public ClosedNodes()
   {  this.resetNodes();
   }

   public void resetNodes()
   {  //Syste.out.println("Reseting Closed Nodes");
      this.numNodes = 0;
      this.nodes = new Node[1];
      this.coordinates = new int[0][0];
   }

   public Node[] getAllNodes()
   {  return this.nodes;
   }

   public void addNode(Node node)
   {  //System.out.println("Adding Closed Node");
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

   public void addNodes(Node[] nodes)
   {  for (int i=0; i < nodes.length; i++)
      {  if (nodes[i] != null)
         {  this.addNode(nodes[i]);
         }
      }
   }

   private void increaseNodeSize()
   {  //System.out.println("Increasing Closed Node Size");
      Node[] temp = new Node[this.numNodes * 2];
      for (int i=0; i < this.numNodes; i++)
      {  temp[i] = this.nodes[i];
      }

      this.nodes = temp;
   }

   public void removeNode(Node node)
   {  //System.out.println("Removing Closed Node");
      boolean foundNode = false;
      for (int i=0; i < this.numNodes; i++)
      {  if (foundNode)
         {  this.nodes[i-1] = this.nodes[i];
         }
         if (this.nodes[i] == node)
         {  foundNode = true;
         }
      }
      //System.out.println("numNodes: " + this.numNodes + ", this.nodes.length: " + this.nodes.length);
      this.nodes[this.numNodes-1] = null;
      this.numNodes--;
   }

   public Node[] getNodes()
   {  return this.nodes;
   }

   public boolean checkForNode(int x, int y)
   {  //System.out.println("Checking for Closed Node (no f)");
      boolean flag = false;
      for (int i=0; i < this.numNodes; i++)
      {  if (this.nodes[i].getX() == x && this.nodes[i].getY() == y)
         {  flag = true;
         }
      }

      return flag;
   }

   public boolean checkForNode(int x, int y, int f)
   {  //System.out.println("Checking for Closed Node (with f)");
      boolean flag = false;
      for (int i=0; i < this.numNodes; i++)
      {  if (this.nodes[i].getX() == x && this.nodes[i].getY() == y && this.nodes[i].getF() > f)
         {  flag = true;
         }
      }

      return flag;
   }

   public Node getNextNode()
   {  //System.out.println("Getting next Closed Node");
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
   {  //System.out.println("Getting Number of Closed Nodes");
      return this.numNodes;
   }
} 