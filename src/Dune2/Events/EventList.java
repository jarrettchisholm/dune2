package Dune2.Events;
import Dune2.Model.*;

import becker.io.TextInput;

public class EventList
{  private World model;
   private int numEvents;
   private Event[] events;
   private int numEventsRead;
   private int numEventsToDoPerRound;

   public EventList(TextInput in, EventProfiles eProfiles, World theModel)
   {  this.model = theModel;

      this.numEvents = in.readInt();
      System.out.println(this.numEvents);
      in.readLine();
      in.readLine();

      this.events = new Event[this.numEvents];

      for (int i=0; i < this.numEvents; i++)
      {  this.events[i] = new Event(in, model, eProfiles);
      }

      this.refreshEventsToCompute();
      this.refreshNumEventsRead();
   }

   private void refreshEventsToCompute()
   {  this.numEventsToDoPerRound = this.numEvents / (Constants.GAME_SPEED * Constants.TIME_TO_COMPUTE_ALL_EVENTS);
      if (this.numEventsToDoPerRound < 1)
      {  this.numEventsToDoPerRound = 1;
      }
   }

   private void refreshNumEventsRead()
   {  this.numEventsRead = 0;
   }

   public void doEvents()
   {  if (numEventsRead >= this.events.length)
      {  this.refreshEventsToCompute();
         this.refreshNumEventsRead();
      }

      for (int i= this.numEventsRead; i < (this.numEventsToDoPerRound + this.numEventsRead); i++)
      {  this.events[i].checkEvent();
         //I may end up getting 'array index out of bounds' errors here.....
      }

      this.numEventsRead = this.numEventsRead + this.numEventsToDoPerRound;

   }

   /* may not need this method........*/
   public void refreshEventActivityAfterLoad(boolean[] active)
   {  for (int i=0; i < this.numEvents; i++)
      {  if (active[i])
         {  this.events[i].setActive();
         } else
         {  this.events[i].setInActive();
         }
      }
   }

   public Event[] getEvents()
   {  return this.events;
   }

   public int getNumEvents()
   {  return this.numEvents;
   }
} 