/*

grass.h

Represents wild Grass.

*/

#ifndef GRASS_H
#define GRASS_H

#include <sstream>
using namespace std;

#include "gameobject.h"

class Grass: public GameObject
{
    public:
        Grass();
        
        void update(int seconds = 0);
    
    private:
};

#endif
