/*

grass.h

Represents wild Grass.

*/

#ifndef GRASS_H
#define GRASS_H

#include <map>
using namespace std;

#include "gameobject.h"

class Grass: public GameObject
{
    public:
        // Constructors
        Grass();
        
        // Member Functions
        virtual GameObject* harvest();
        virtual void update(int seconds = 0);
    
    private:
};

#endif
