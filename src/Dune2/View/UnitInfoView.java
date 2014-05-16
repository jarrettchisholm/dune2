package Dune2.View;
import Dune2.Model.*;
import Dune2.Units.*;

import becker.util.IView;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class UnitInfoView extends JPanel implements IView
{  private World model;
   private ImageInformation imageInfo;
   private JLabel name;
   private JLabel hp;
   private JLabel currentConstruction;
   private JLabel status;
   private JLabel progress;
   private JLabel cash;
   private JLabel holdingCredits;
   private JLabel time;

   public UnitInfoView(World theModel, ImageInformation iInfo)
   {  this.model = theModel;

      this.layoutView();
      this.registerListeners();

      this.model.addView(this);
   }

   private void layoutView()
   {  this.setPreferredSize(new Dimension(200,150));

      this.name = new JLabel("NONE");
      this.hp = new JLabel("HP: 0/0");
      this.currentConstruction = new JLabel("cc: NONE");
      this.status = new JLabel("Status: N/A");
      this.progress = new JLabel("Progress: 0%");
      this.cash = new JLabel("Credits: 0");
      this.holdingCredits = new JLabel("Held: 0");
      this.time = new JLabel("Time: 0.00");

      JPanel holder = new JPanel();
      holder.setPreferredSize(new Dimension(200,150));
      holder.setBackground(Color.lightGray);
      holder.add(this.name);
      holder.add(this.hp);
      holder.add(this.currentConstruction);
      holder.add(this.status);
      holder.add(this.progress);
      holder.add(this.cash);
      holder.add(this.holdingCredits);
      this.add(this.time);

      this.add(holder);
   }

   private void registerListeners()
   {
   }

   public void updateView()
   {  Unit[] unit = this.model.getSelectedUnits();
      Building building = this.model.getSelectedBuilding();
      if (unit != null)
      {  int numUnits = unit.length;
         if (numUnits < 2)
         {  this.name.setText(unit[0].getName());
            this.hp.setText("HP: " + unit[0].getCurrentHP() + "/" + unit[0].getTotalHP());
            this.currentConstruction.setText("cc: NONE");
            this.status.setText("Status: " + unit[0].getStatus());
            this.progress.setText("Progress: " + unit[0].getProgress() + "%");
            this.holdingCredits.setText("Held: " + unit[0].getHoldingCredits());
         } else
         {  this.hp.setText("NUM SELECTED" + numUnits);
         }
      } else if (building != null)
      {  this.name.setText(building.getName());
         this.hp.setText("HP: " + building.getCurrentHP() + "/" + building.getTotalHP());
         this.currentConstruction.setText("cc: " + building.getCurrentConstruction());
         this.status.setText("Status: " + building.getStatus());
         this.progress.setText("Progress: " + building.getProgress() + "%");
      } else
      {  this.name.setText("NONE");
         this.hp.setText("HP: 0/0");
      }

      this.cash.setText("Credits: " + this.model.getPlayerFunds(0));
      this.time.setText("Time: " + (int)this.model.getTime());
   }
}
