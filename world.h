/*

world.h

Defines a game world that contains all world elements, characters, and objects.
All elements are stored in Tiles on a rendered map

Note that the Tiles are stored [row][col], or [y][x]

*/

#ifndef WORLD_H
#define WORLD_H

#include <vector>
using namespace std;

#include "tile.h"
#include "dirt.h"
#include "stone.h"
#include "being.h"

class World
{    
    public:
        World();
        ~World();
        
        int getWidth();
        int getHeight();
        
        int getCharX();
        int getCharY();
        
        void moveCharLeft();
        void moveCharRight();
        void moveCharUp();
        void moveCharDown();
        
        Tile getTile(int, int);
        char getSymbol(int, int);
    
    private:
        int width;
        int height;
        int charX;
        int charY;
        
        vector< vector<Tile> > * tiles;
        
        Being * player;
};

#endif
