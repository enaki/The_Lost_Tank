# The Lost Tank

## 1. Introduction

![Game](https://github.com/enaki/The_Lost_Tank/master/blob/Documenation/screenshots/ss_1.png)


#### 1.1. Plot
You are an american tank lost in German territory during the WWII. 
Your goal is to defeat enemy tanks and get back to Allied lines. 
You also need to be careful as the Germans have a secret powerful weapon that has begun to 
be used on the front. Try to use german technology knowledge to upgrade as fast as you can, 
as more powerful enemies are waiting for you.

#### 1.2 Gameplay
The player must defeat enemies to progress through levels. 
You can use the trees and mountains to defend yourself from enemy projectiles. 
If you destroy trees and enemies there is a chance that one of the three types of cuffs 
will appear at their death.

* __Red__ – health + coins 
* __Yellow__ – coins 
* __Green__ – upgrade + coins

#### 1.3 Mechanics

The control keys:

| Action        | Player        |
| ------------- |:-------------:|
| Move Up       | W/ Up Arrow   |
| Move Down     | S/ Down Arrow |
| Move Left     | A/ Left Arrow |
| Move Right    | D/ Right Arrow|
| Shoot         | Space         |

## 2. Notes

#### 2.1 Levels
The game has 5 difficulty levels. A level is stored in a text file whose address 
is stored in the database. 
The level of difficulty is determined by the number of enemies and the type of 
enemies (in total there are 5 types of enemies). Each level has its own map.

#### 2.2 Gaming Points
The aim of the game is to collect as many coins as possible. 
They can be obtained from chests generated at the death of an enemy or a tree, 
each of which is generated with a different probability depending on the entity.

#### 2.3 Reading and writing data from / to a SQLite database
At the end of the game the score is saved in a table containing the history 
of all games and there is also a table containing the maximum score obtained so far and 
depending on the score obtained changes or not the maximum score which is also displayed 
on the screen.

#### 2.4 Implementing the notion of saving, loading, respectively new game
At the time of the game by pressing the Esc key, you are in the menu mode from where you 
can access the Help, then return to the game from the remaining state or start another game. 
The transition from menu to game and vice versa is based on a class of states.

#### 2.5 Game configuration
A layer is stored in a text file whose address is stored in the database. 
Each level also has a background soundtrack that is also stored in the database.

#### 2.6 The Method of Victory
The game continues as long as the player's life is greater than 0. 
If he dies, only the score with the message "You lose" is displayed. 
If he managed to pass all the levels then his score is recorded in the data history 
along with the specific date and if this is the maximum score obtained so far, 
it is also recorded in the maximum score table.

## 3. Database

There are 3 tables:
* Score
* BestScre
* LevelPath

![Database Tables](https://github.com/enaki/The_Lost_Tank/master/blob/Documenation/screenshots/db.png)

#### 3.1 Score Table
Here are kept the history of the games won. 
It is accessed in the WinState class where the insertion is made.
- `Date` column - game date
- `Score` column - the score obtained

#### 3.2 BestScore Table
The best accumulated score is kept here. 
It is accessed in the WinState class where the insertion is made if the extraction is needed.
- `Date` column - game date
- `Score` column - the score obtained

#### 3.3 LevelPath Table
Data specific to each level is stored here. It is accessed in the GameState class where the 
map and specific configurations such as background music are loaded.
- `Level` column - the respective level
- `Path` column - the address of the map file
- `Background` column - the address of the file containing the background music for
each level in wav format

## User Interface

![States](https://github.com/enaki/The_Lost_Tank/master/blob/Documenation/screenshots/ui.png)

There are 6 states:
* Menu State
* Help State
* IntermediateMenu State
* Game State
* Lose State
* Win State

![States](https://github.com/enaki/The_Lost_Tank/master/blob/Documenation/screenshots/ss_2.png)


## Class Diagram

![Class Diagram](https://github.com/enaki/The_Lost_Tank/master/blob/Documenation/diagrams/class_diagram.png)


## Run the Project

- Mark the `res` folder as the `resource folder`
- Include the `sqlite-jdbc-3.7.2.jar` in the game module as library
- Run the project