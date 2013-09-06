/*

dirt.h

Represents a basic dirt floor tile.

*/

#ifndef DIRT_H
#define DIRT_H

#include "gameobject.h"

class Dirt: public GameObject
{
    public:
        // Constructors
        Dirt();
        
        // Member Functions
        virtual void update(int seconds = 0);
    
    private:
};

#endif
