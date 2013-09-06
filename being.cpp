/*

being.cpp

Implementation of the Being class

*/

#include "being.h"

/*
 * Constructors
 */

Being::Being()
{
    _symbol = '@';
    _description = "A living creature";
}

/*
 * Member Functions
 */

void Being::update(int seconds)
{
    GameObject::update(seconds);
}
