package Dune2.View;
import Dune2.Model.*;

import becker.util.IView;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class MenuView extends JPanel implements IView
{  private World model;
   private ImageInformation imageInfo;

   public MenuView(World theModel, ImageInformation iInfo)
   {  this.model = theModel;
      this.imageInfo = iInfo;
   }

   public void updateView()
   {
   }
} 