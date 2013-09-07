/*

recipe.h

Represents a recipe for brewing a new object from existing objects.

*/

#ifndef RECIPE_H
#define RECIPE_H

#include <list>
#include <string>
using namespace std;

#include "gameobject.h"

class Recipe
{
    public:
        // Constructors
        Recipe();
        Recipe(const Recipe&);
        ~Recipe();
        
        // Operators
        Recipe& operator=(const Recipe&);
        bool operator==(const Recipe&) const;
        // TODO: Implement other operators
        
        // Member Functions
        string toString();
        
    private:
        // Properties
        list<GameObject*> _ingredients;
};

#endif
