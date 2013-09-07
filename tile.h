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
        // Constructors
        Tile();
        Tile(const Tile&);
        ~Tile();
        
        // Operators
        Tile& operator=(const Tile&);
        
        // Accessors
        char getSymbol();
        list<string> getDescriptions();
        
        // Mutators
        void addObject(GameObject*);
        
        // Member Functions
        GameObject* harvest();
        void update(int seconds = 0);

    private:
        list<GameObject*>* _objects;
};

#endif
