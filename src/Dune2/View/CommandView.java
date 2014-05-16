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

public class CommandView extends JPanel implements IView
{  private World model;
   private ImageInformation imageInfo;
  // private JButton start;
   private JButton pauseResume;
   private boolean pause = false;
   private JLabel blaH;
   private JLabel FPS;
   private JButton move;
   private JButton enter;
   private JButton placeBuilding;
   private JButton buildLast;
   private JButton attack;
   private JButton harvest;
   private JButton harvestReturn;
   private JButton stop;

   public CommandView(World theModel, ImageInformation iInfo)
   {  this.model = theModel;

      this.layoutView();
      this.registerListeners();

      this.model.addView(this);
   }

   private void layoutView()
   {  this.setPreferredSize(new Dimension(200,150));

      //this.start = new JButton("Start");
      this.pauseResume = new JButton("Pause");
      this.blaH = new JLabel("0000");
      this.FPS = new JLabel("FPS: " + Constants.GAME_SPEED);
      this.move = new JButton("Move");
      this.enter = new JButton("Enter Building");
      this.placeBuilding = new JButton("Place Building");
      this.buildLast = new JButton("Build Last");
      this.attack = new JButton("Attack");
      this.harvest = new JButton("Harvest");
      this.harvestReturn = new JButton("Return");
      this.stop = new JButton("Stop");
      this.placeBuilding.setBackground(Color.darkGray);
      this.buildLast.setVisible(false);
      this.placeBuilding.setVisible(false);


      JPanel holder = new JPanel();
      holder.setPreferredSize(new Dimension(200,100));
      holder.setBackground(Color.blue);
      //holder.add(this.start);
      holder.add(this.pauseResume);
      holder.add(this.attack);
      holder.add(this.harvest);
      holder.add(this.move);
      holder.add(this.stop);
      holder.add(this.harvestReturn);
      holder.add(this.blaH);
      this.add(this.FPS);
      this.add(this.enter);
      this.add(this.placeBuilding);
      this.add(this.buildLast);

      this.add(holder);
   }

   private void registerListeners()
   {  //this.start.addActionListener(new startActionListener());
      this.pauseResume.addActionListener(new pauseResumeActionListener());
      this.move.addActionListener(new moveActionListener());
      this.stop.addActionListener(new stopActionListener());
      this.enter.addActionListener(new enterActionListener());
      this.placeBuilding.addActionListener(new placeBuildingActionListener());
      this.buildLast.addActionListener(new buildLastActionListener());
      this.attack.addActionListener(new attackActionListener());
      this.harvest.addActionListener(new harvestActionListener());
      this.harvestReturn.addActionListener(new harvestReturnActionListener());
   }

   public void updateView()
   {  this.blaH.setText("Frame: " + this.model.getThreadNumber() + " ");
      if (this.model.getSelectedBuilding() != null && this.model.getSelectedBuilding().getProgress() == 100)
      {  this.placeBuilding.setVisible(true);
      } else
      {  this.placeBuilding.setVisible(false);
      }

      if (this.model.getSelectedUnits() != null)
      {  Unit[] units = this.model.getSelectedUnits();
         if (units[0].getName().equals(Constants.HARVESTOR))
         {  this.harvest.setVisible(true);
            this.harvestReturn.setVisible(true);
            this.attack.setVisible(false);
            this.move.setVisible(true);
         } else
         {  this.harvest.setVisible(false);
            this.harvestReturn.setVisible(false);
            this.attack.setVisible(true);
            this.move.setVisible(true);
         }
         this.buildLast.setVisible(false);
      } else if (this.model.getSelectedBuilding() != null && !this.model.getSelectedBuilding().getPreviousConstruction().equals(Constants.NONE))
      {  this.buildLast.setVisible(true);
      } else
      {  this.buildLast.setVisible(false);
      }

      if (this.model.getSelectedUnits() == null)
      {  this.harvest.setVisible(false);
         this.harvestReturn.setVisible(false);
         this.attack.setVisible(false);
         this.move.setVisible(false);
      }
   }

   private class startActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  //model.startGame();
         //model.updateAllViews();
         //start.setVisible(false);
      }
   }

   private class pauseResumeActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  if (pause)
         {  model.resumeGame();
            pauseResume.setText("Pause");
            pause = false;
         } else
         {  model.pauseGame();
            pauseResume.setText("Resume");
            pause = true;
         }
      }
   }

   private class resumeActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  model.resumeGame();
      }
   }

   private class stopActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  model.setSelectedUnitStop();
      }
   }

   private class moveActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  if (model.getSelectedUnits() != null)
         {  model.setSelectedUnitMoving();
            model.setSelectedUnitNotHarvest();
            model.setSelectedUnitNotAttack();
         }
      }
   }

   private class enterActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  if (model.getSelectedBuilding() != null && model.getSelectedBuilding().getEnterable())
         {  model.enterBuilding();
         }
      }
   }

   private class buildLastActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  if (model.getSelectedBuilding() != null && !model.getSelectedBuilding().getPreviousConstruction().equals(Constants.NONE))
         {  System.out.println("building last: " + model.getSelectedBuilding().getPreviousConstruction());
            model.canBuildBuilding(model.getSelectedBuilding().getPreviousConstruction());
         }
      }
   }

   private class placeBuildingActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  if (model.getSelectedBuilding() != null && !model.getPlacingBuilding())
         {  model.setPlacingBuilding(true);
         } else if (model.getSelectedBuilding() != null && model.getPlacingBuilding())
         {  model.setPlacingBuilding(false);
         }
      }
   }

   private class attackActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  model.setSelectedUnitAttack();
      }
   }

   private class harvestActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  model.setSelectedUnitHarvest();
      }
   }

   private class harvestReturnActionListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  model.unitReturnToRefinery(model.getSelectedUnits()[0], 0);
      }
   }
}
