package Dune2.Model;

import becker.util.Utilities;
import java.awt.event.*;

public class GameThread implements Runnable
{  private World model;
   private int x = 0;

   public GameThread(World theModel)
   {  this.model = theModel;
      this.model.addRunnable(this);
   }

   public void run()
   {
      this.x = 0;
      long start = 0;
      long end = 0;
      long finish = 0;

      long sleepTime = 0;

      while(true)
      {  if (!this.model.getAtMainMenu())
         {  start = System.currentTimeMillis();

            this.model.gameFlow();
            this.x++;
            this.model.setThreadNumber(this.x);

            end = System.currentTimeMillis();

            /* 1000 / gamespeed should be exactly fps...but end-start takes into
               account how long it takes for a frame to be processed
            */

            finish = end-start;

            sleepTime = 1000 / Constants.GAME_SPEED - finish;

            if (sleepTime < 0)
            {  //System.out.println("Frame rate has gone below " + Constants.GAME_SPEED + " FPS.  Sleep time is: " + sleepTime);
               sleepTime = 0;
            }
            Utilities.sleep(sleepTime);
         }
      }
   }
}
