package Dune2.View;

//import java.io.FileFilter;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Dune2FileFilter extends FileFilter
{  boolean loadGame = false;
   boolean newGame = false;
   boolean saveGame = false;

   public Dune2FileFilter(boolean loadGame, boolean newGame, boolean saveGame)
   {  this.loadGame = loadGame;
      this.newGame = newGame;
      this.saveGame = saveGame;
   }

   //Accept all directories and .map / .sav files.
    public boolean accept(File f)
    {
        if (f.isDirectory())
        {
            return true;
        }

        String extension = this.getExtension(f.getName());

        if (extension != null && (this.loadGame || this.saveGame))
        {   if (extension.equals("sav"))
            {  return true;
            } else
            {  //System.out.println("map: " + extension);
               return false;
            }
        } else if (extension != null && this.newGame)
        {  if (extension.equals("map"))
            {  return true;
            } else
            {  //System.out.println("map: " + extension);
               return false;
            }

         }

        return false;
    }

    //The description of this filter
    public String getDescription()
    {
        return ".sav and .map only";
    }

    //Get the extension of the fileName
    private String getExtension(String fileName)
    {  String returnExtension = "";
         for (int i=0; i < fileName.length(); i++)
         {  if (fileName.charAt(i) == '\\')
            {  returnExtension = "";
            } else if (fileName.charAt(i) == '.')
            {  returnExtension = "";
            } else
            {  returnExtension = returnExtension + fileName.charAt(i);
            }
         }

         return returnExtension;
    }


}
