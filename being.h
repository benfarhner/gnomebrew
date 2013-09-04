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
        Being();
        
        void Update(int hours = 0);
    
    private:
};

#endif
