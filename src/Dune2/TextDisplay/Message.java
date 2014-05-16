package Dune2.TextDisplay;
import Dune2.Model.Constants;

import java.awt.*;

public class Message
{  private String[] message;
   private int numLines;
   private double duration;
   private double timeElapsed;
   private Color color = Color.white;

   public Message(String newMessage, double duration, Color color)
   {  this.duration = duration;
      this.color = color;

      double numL = (double)newMessage.length() / (double)Constants.MAX_CHARS_FOR_DISPLAY_TEXT;
      this.numLines = (int)Math.round(numL);

      /* This will add an extra line at the end of the message to accomidate
         the end of the message if it is not the exact number of MAX_CHARS
         (the last line of the message will almost never be exactly MAX_CHARS long)
      */
      if (numL - (double)this.numLines > 0.0)
      {  this.numLines++;
      }

      this.message = new String[this.numLines];
      int index = 0;
      for (int i=0; i < this.numLines; i++)
      {  this.message[i] = ""; //initialize the string to empty (instead of "null")
         for (int j=0; j < Constants.MAX_CHARS_FOR_DISPLAY_TEXT; j++)
         {  if (index >= newMessage.length())
            {  break;
            }

            this.message[i] = this.message[i] + newMessage.charAt(index);
            index++;
         }
      }
   }

   public Message(String newMessage, double duration)
   {  this.duration = duration;

      double numL = (double)newMessage.length() / (double)Constants.MAX_CHARS_FOR_DISPLAY_TEXT;
      this.numLines = (int)Math.round(numL);

      /* This will add an extra line at the end of the message to accomidate
         the end of the message if it is not the exact number of MAX_CHARS
         (the last line of the message will almost never be exactly MAX_CHARS long)
      */
      if (numL - (double)this.numLines > 0.0)
      {  this.numLines++;
      }

      this.message = new String[this.numLines];
      int index = 0;
      for (int i=0; i < this.numLines; i++)
      {  this.message[i] = ""; //initialize the string to empty (instead of "null")
         for (int j=0; j < Constants.MAX_CHARS_FOR_DISPLAY_TEXT; j++)
         {  if (index >= newMessage.length())
            {  break;
            }

            this.message[i] = this.message[i] + newMessage.charAt(index);
            index++;
         }
      }
   }

   public Color getColor()
   {  return this.color;
   }

   public String[] getMessage()
   {  return this.message;
   }

   public int getNumLines()
   {  return this.numLines;
   }

   public boolean hasCompletedLifeCycle()
   {  return (this.timeElapsed >= this.duration);
   }

   public void doRound(double elapsed)
   {  this.timeElapsed = this.timeElapsed + elapsed;
   }
} 