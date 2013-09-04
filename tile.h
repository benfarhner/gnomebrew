/*

tile.h

Generic Tile class that represents any kind of tile on a map.
Holds a stack of game objects located on this tile.

*/

#ifndef TILE_H
#define TILE_H

#include <list>
#include <string>
using namespace std;

#include "gameobject.h"

class Tile
{
    public:
        Tile();
        Tile(const Tile&);
        ~Tile();
        
        Tile& operator= (const Tile&);
        
        void addObject(GameObject);
        
        char getSymbol();
        list<string> getDescriptions();

    private:
        list<GameObject> * objects;
};

#endif
