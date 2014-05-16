package Dune2.Model;

public class AIQueueList
{  private Job[] jobs;
   private int numQueue;

   public AIQueueList()
   {
   }

   public void addToNeedsQueue(Job job)
   {  if (this.jobs == null)
      {  this.increaseQueueSize();
      } else if (this.jobs.length == this.numQueue)
      {  this.increaseQueueSize();
      }

      this.jobs[this.numQueue] = job;
      this.numQueue++;
   }

   public void increaseQueueSize()
   {  Job[] temp1 = null;

      if (this.numQueue != 0)
      {  temp1 = new Job[this.numQueue * 2];
      } else
      {  temp1 = new Job[1];
      }

      for (int i=0; i < this.numQueue; i++)
      {  temp1[i] = this.jobs[i];
      }

      this.jobs = temp1;
   }

   public void removeMostImportantQueueJob()
   {  if (this.numQueue >= 1)
      {  int compare = 0;
         int check = this.jobs[compare].getImportance();
         for (int i=1; i < this.numQueue; i++)
         {  if (check > this.jobs[i].getImportance())
            {  compare = i;
               check = this.jobs[i].getImportance();
            }
         }

         boolean found = false;
         for (int i=0; i < this.numQueue-1; i++)
         {  if (compare == i)
            {  found = true;
            }

            if (found)
            {  this.jobs[i] = this.jobs[i+1];
            }
         }

         this.numQueue--;
      }
   }

   public Job getMostImportantQueueJob()
   {  if (this.numQueue >= 1)
      {  int compare = 0;
         int check = this.jobs[compare].getImportance();
         for (int i=1; i < this.numQueue; i++)
         {  if (check > this.jobs[i].getImportance())
            {  compare = i;
               check = this.jobs[i].getImportance();
            }
         }

         return this.jobs[compare];
      }

      return null;
   }

   public boolean isOnQueue(String name)
   {  for (int i=0; i < this.numQueue; i++)
      {  if (this.jobs[i].getUnitNeed() != null && this.jobs[i].getUnitNeed().getName().equals(name))
         {  return true;
         } else if (this.jobs[i].getBuildingNeed() != null && this.jobs[i].getBuildingNeed().getName().equals(name))
         {  return true;
         }
      }

      return false;
   }

   public int howManyOnQueue(String name)
   {  int num = 0;
      for (int i=0; i < this.numQueue; i++)
      {  if (this.jobs[i].getUnitNeed().getName().equals(name))
         {  num++;
         } else if (this.jobs[i].getBuildingNeed().getName().equals(name))
         {  num++;
         }
      }

      return num;
   }

   public int getQueueLength()
   {  return this.numQueue;
   }
} 