package Dune2.TextDisplay;

import java.awt.Color;

public class TextDisplayer
{  private Message[] messages;
   private int numMessages;
   private double currentTime;

   public TextDisplayer()
   {  this.resetMessages();
   }

   /*  Call this method when a user wishes to 'remove' all text from his/her
       screen.
   */
   public void resetMessages()
   {  this.messages = new Message[0];
      this.numMessages = 0;
   }

   public void addMessage(String message, double duration)
   {  Message[] temp = new Message[this.numMessages+1];
      System.out.println(message + " : " + duration);
      for (int i=0; i < this.numMessages; i++)
      {  temp[i] = this.messages[i];
      }

      this.messages = temp;
      this.messages[this.numMessages] = new Message(message, duration);
      this.numMessages++;
   }

   public void addMessage(String message, double duration, Color color)
   {  Message[] temp = new Message[this.numMessages+1];
      System.out.println(message + " : " + duration);
      for (int i=0; i < this.numMessages; i++)
      {  temp[i] = this.messages[i];
      }

      this.messages = temp;
      this.messages[this.numMessages] = new Message(message, duration, color);
      this.numMessages++;
   }

   private void removeMessage(Message message)
   {  boolean found = false;
      for (int i=0; i < this.numMessages-1; i++)
      {  if (this.messages[i] == message)
         {  found = true;
         }

         if (found)
         {  this.messages[i] = messages[i + 1];
         }
      }

      this.numMessages--;
      this.messages[this.numMessages] = null;
   }

   public void doRound(double time)
   {  double elapsedTime = time - this.currentTime;
      this.currentTime = time;

      //do message rounds
      for (int i=0; i < this.numMessages; i++)
      {  this.messages[i].doRound(elapsedTime);
      }

      //remove messages that have completed their viewing cycle
      for (int i=0; i < this.numMessages; i++)
      {  if (this.messages[i].hasCompletedLifeCycle())
         {  this.removeMessage(this.messages[i]);
            if (i != 0)
            {  i--;
            }
         }
      }
   }

   public Message[] getMessages()
   {  return this.messages;
   }

   public int getNumMessages()
   {  return this.numMessages;
   }

   
} 