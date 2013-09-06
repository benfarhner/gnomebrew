/*

stone.cpp

Implementation of the Stone class

*/

#include "stone.h"

/*
 * Constructors
 */

Stone::Stone()
{
    _symbol = '.';
    _description = "Stone";
}

/*
 * Member Functions
 */

void Stone::update(int seconds)
{
    GameObject::update(seconds);
}
