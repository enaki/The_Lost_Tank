package State.Utils;

import static State.Utils.Levels.Level.*;

public class Levels {

   public enum Level{
      level_1, level_2, level_3, level_4
   }

   public static Level nextLevel(Level level){
      switch(level){
         case level_1:
            return level_2;
         case level_2:
            return level_3;
         case level_3:
         default:
            return level_4;
      }
   }

   public static Level previousLevel(Level level){
      switch(level){
         case level_3:
            return level_2;
         case level_4:
            return level_3;
         default:
            return level_1;
      }
   }

   public static String GetLevelName(Level level){
      switch(level){
         case level_1:
            return "Level 1";
         case level_2:
            return "Level 2";
         case level_3:
            return "Level 3";
         default:
            return "Final level";
      }
   }

   public static String GetLevelWorld(Level level){
      switch(level){
         case level_1:
            return "res/world/world_1.txt";
         case level_2:
            return "res/world/world_2.txt";
         case level_3:
            return "res/world/world_3.txt";
         case level_4:
         default:
            return "res/world/world_4.txt";
      }
   }

}
