/*

being.h

Represents a generic Being in the game

*/

#ifndef BEING_H
#define BEING_H

#include <list>
using namespace std;

#include "gameobject.h"
#include "recipe.h"

class Being: public GameObject
{
    public:
        // Constructors
        Being();
        
        // Accessors
        list<GameObject*>* getInventory();
        
        // Mutators
        void addObject(GameObject*);
        
        // Member Functions
        bool brew(Recipe*);
        void move(Direction);
        virtual void update(int seconds = 0);
    
    private:
        // Properties
        list<GameObject*> _inventory;
};

#endif
