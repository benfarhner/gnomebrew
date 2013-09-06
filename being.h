/*

being.h

Represents a generic Being in the game

*/

#ifndef BEING_H
#define BEING_H

#include "gameobject.h"

class Being: public GameObject
{
    public:
        // Constructors
        Being();
        
        // Member Functions
        virtual void update(int seconds = 0);
    
    private:
};

#endif
