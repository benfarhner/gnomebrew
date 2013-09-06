/*

world.h

Defines a game world that contains all world elements, characters, and objects.
All elements are stored in Tiles on a rendered map

Note that the Tiles are stored [row][col], or [y][x]

*/

//#define DEBUG 1

#ifndef WORLD_H
#define WORLD_H

#include <curses.h>
#include <cstdlib>
#include <ctime>
#include <vector>
using namespace std;

#include "tile.h"
#include "dirt.h"
#include "grass.h"
#include "stone.h"
#include "being.h"

class World
{    
    public:
        // Constructors
        World();
        World(int, int);
        ~World();
        
        // Accessors
        int getWidth();
        int getHeight();
        char getSymbol(int, int);
        Tile* getTile(int, int);
        
        int getCharX();
        int getCharY();
        
        void moveCharLeft();
        void moveCharRight();
        void moveCharUp();
        void moveCharDown();
        
        // Member Functions
        void update(int seconds = 0);
        WINDOW* render();
    
    private:
        // Private Member Functions
        void generateWorld();
        
        // Properties
        int _width;
        int _height;
        
        vector< vector<Tile> >* _tiles;
        WINDOW* _map;
        
        Being* _player;
};

#endif
