/*

recipe.cpp

Implementation for the Recipe class

*/

#include "recipe.h"

/*
 * Constructors
 */

Recipe::Recipe()
{
}

Recipe::Recipe(const Recipe&)
{
}

Recipe::~Recipe()
{
    for (list<GameObject*>::iterator it = _ingredients.begin();
         it != _ingredients.end();
         ++it)
    {
        delete *it;
    }
}

/*
 * Operators
 */

Recipe& Recipe::operator=(const Recipe&)
{
}

bool Recipe::operator==(const Recipe&) const
{
}

/*
 * Member Functions
 */

string Recipe::toString()
{
}
