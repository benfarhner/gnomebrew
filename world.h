/*

world.h

Defines a game world that contains all world elements, characters, and objects.
All elements are stored in Tiles on a rendered map

Note that the Tiles are stored [row][col], or [y][x]

*/

#ifndef WORLD_H
#define WORLD_H

#include <curses.h>
#include <cstdlib>
#include <ctime>
#include <vector>
using namespace std;

#include "tile.h"
#include "dirt.h"
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
        
        Tile getTile(int, int);
        char getSymbol(int, int);
        
        int getCharX();
        int getCharY();
        
        void moveCharLeft();
        void moveCharRight();
        void moveCharUp();
        void moveCharDown();
        
        WINDOW* render();
    
    private:
        // Private Member Functions
        void generateWorld();
        
        int _width;
        int _height;
        
        vector< vector<Tile> >* _tiles;
        WINDOW* _map;
        
        Being* _player;
};

#endif
