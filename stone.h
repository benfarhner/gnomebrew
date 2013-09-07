/*

stone.h

Represents a stone tile.

*/

#ifndef STONE_H
#define STONE_H

#include "gameobject.h"

class Stone: public GameObject
{
    public:
        // Constructors
        Stone();
        
        // Member Functions
        virtual GameObject* harvest();
        virtual void update(int seconds = 0);
        
    private:
};

#endif
