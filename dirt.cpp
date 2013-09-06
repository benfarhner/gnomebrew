/*

dirt.cpp

Implementation of the Dirt class

*/

#include "dirt.h"

/*
 * Constructors
 */

Dirt::Dirt()
{
    _symbol = ' ';
    _description = "Dirt";
}

/*
 * Member Functions
 */

void Dirt::update(int seconds)
{
    GameObject::update(seconds);
}
