package Dune2.Events;
import Dune2.Model.*;
import Dune2.Units.*;

public class EventProfiles
{  private Locations locations;
   private World model;

   public EventProfiles(Locations locations, World theModel)
   {  this.locations = locations;
      this.model = theModel;
   }

   public void doEvent(Action action)
   {  if (action.getIdentifier().equals(Constants.DISPLAY_TEXT))
      {  //do a check for the modifier --> tells us the text color...
         //DISPLAY TEXT
         String[] arguments = action.getArguments();
         double[] numberArguments = action.getNumberArguments();
         this.model.addMessageToTextDisplayer(arguments[0], numberArguments[0]);
      } else if (action.getIdentifier().equals(Constants.CREATE_UNITS))
      {  //CREATE UNITS
         String[] arguments = action.getArguments();
         double[] numberArguments = action.getNumberArguments();
         Location loc = this.locations.getLocation(arguments[2]);

         this.model.createUnitsFromEvent(arguments[1], (int)numberArguments[0], arguments[0], loc.getStartX(), loc.getStartY(), loc.getEndX(), loc.getEndY());
      }  else if (action.getIdentifier().equals(Constants.ORDER))
      {  String[] arguments = action.getArguments();
         Location start = this.locations.getLocation(arguments[2]);
         Location end = this.locations.getLocation(arguments[3]);
         if (action.getModifier().equals(Constants.ATTACK))
         {  this.model.orderUnitsToAttackFromEvent(arguments[1], arguments[0] ,start.getStartX(), start.getStartY(), start.getEndX(), start.getEndY(), end.getStartX(), end.getStartY(), end.getEndX(), end.getEndY());
         } else if (action.getModifier().equals(Constants.MOVE))
         {  this.model.orderUnitsToMoveFromEvent(arguments[1], arguments[0], start.getStartX(), start.getStartY(), start.getEndX(), start.getEndY(), end.getStartX(), end.getStartY(), end.getEndX(), end.getEndY());
         }
      }
   }

   public boolean eventCheck(Condition condition)
   {  boolean flag = false;
      if (condition.getIdentifier().equals(Constants.IF))
      {  if (condition.getModifier().equals(Constants.BRING))
         {  flag = this.doIfPlayerBringsUnitsToLocationCheck(condition);
         }
      }

      return flag;
   }

   private boolean doIfPlayerBringsUnitsToLocationCheck(Condition condition)
   {  String[] arguments = condition.getArguments();
      double[] numberArguments = condition.getNumberArguments();
      Location location = this.locations.getLocation(arguments[3]);
      /* NOTE:  x1 < x2 AND y1 < y2 ALWAYS!
      */

      int x1 = location.getStartX();
      int y1 = location.getStartY();
      int x2 = location.getEndX();
      int y2 = location.getEndY();

      int loopX = x2 - x1 + 1;
      int loopY = y2 - y1 + 1;
      int numCounted = 0; //how many units meet the requirements

      for (int i=0; i < loopX; i++)
      {  for (int j=0; j < loopY; j++)
         {  if (arguments[0].equals(Constants.ALL))
            {  Unit unit = this.model.getUnitAt(x1+i, y1+j);
               if (unit != null)
               {  System.out.println(unit.getName());
                  if (arguments[2].equals(Constants.ANY))
                  {  numCounted++;
                  } else if (unit.getName().equals(arguments[2]))
                  {  numCounted++;
                  }
               }
            } else
            {  Unit unit = this.model.getUnitAt(x1+i, y1+j, arguments[0]);
               if (unit != null)
               {  System.out.println(unit.getName());
                  if (arguments[2].equals(Constants.ANY))
                  {  numCounted++;
                  } else if (unit.getName().equals(arguments[2]))
                  {  numCounted++;
                  }
               }
            }
         }
      }

      if (arguments[1].equals(Constants.EXACTLY))
      {  return ((double)numCounted == numberArguments[0]);
      } else if (arguments[1].equals(Constants.AT_LEAST))
      {  return ((double)numCounted >= numberArguments[0]);
      }

      /*
      else if (arguments[1].equals(Constants.AT_MOST))
      {  return (numCounted <= arguments[2]);
      }
      */
      //Dont need the last else if...
      return ((double)numCounted <= numberArguments[0]);
   }
}
