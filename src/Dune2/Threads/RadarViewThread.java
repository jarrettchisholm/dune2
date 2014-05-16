package Dune2.Threads;
import Dune2.View.RadarView;

import becker.util.Utilities;

public class RadarViewThread implements Runnable
{  private RadarView radar;

   public RadarViewThread(RadarView radar)
   {  this.radar = radar;
   }

   public void run()
   {  while(true)
      {  if (this.radar.isGameRunning())
         {  //System.out.println("Doing thread in radar");
            if (this.radar.hasViewInModelChanged())
            {  this.radar.updateValuesForViewable();
               this.radar.resetViewChangedInModel();
            } else if (this.radar.isChangingView())
            {  this.radar.updateValuesForRadarFromRadar();
            }
         }
      }
   }
}
