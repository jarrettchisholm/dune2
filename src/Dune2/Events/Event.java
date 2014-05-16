package Dune2.Events;
import Dune2.Model.*;

import becker.io.TextInput;

public class Event
{  private Condition[] conditions;
   private Action[] actions;
   private boolean isActive;

   private int numConditions;
   private int numActions;
   private String name;

   private World model;
   private EventProfiles eProfiles;

   public Event(TextInput in, World theModel, EventProfiles eProfiles)
   {  this.model = theModel;
      this.eProfiles = eProfiles;

      this.name = in.readLine();
      this.numConditions = in.readInt();
      in.readLine();
      this.numActions = in.readInt();
      in.readLine();
      this.isActive = in.readBoolean();
      in.readLine();
      in.readLine();

      this.conditions = new Condition[this.numConditions];
      this.actions = new Action[this.numActions];

      for (int a=0; a < this.numConditions; a++)
      {  this.conditions[a] = new Condition(in);
      }

      for (int a=0; a < this.numActions; a++)
      {  this.actions[a] = new Action(in);
      }
   }

   public String getName()
   {  return this.name;
   }

   public int getNumCondition()
   {  return this.numConditions;
   }

   public int getNumAction()
   {  return this.numActions;
   }

   public boolean getIsActive()
   {  return this.isActive;
   }

   public void setActive()
   {  this.isActive = true;
   }

   public void setInActive()
   {  this.isActive = false;
   }

   public Action[] getActions()
   {  return this.actions;
   }

   public Condition[] getConditions()
   {  return this.conditions;
   }

   public void checkEvent()
   {  boolean flag = true;
      if (this.isActive)
      {  for (int i=0; i < this.numConditions; i++)
         {  if (!this.eProfiles.eventCheck(this.conditions[i]))
            {  flag = false;
               break;
            }
         }

         if (flag)
         {  this.doEvent();
            this.isActive = false;
         }
      }
   }

   private void doEvent()
   {
      System.out.println("Performing EVENT! --> " + this.name);

      for (int i=0; i < this.numActions; i++)
      {  this.eProfiles.doEvent(this.actions[i]);
      }
   }
}
