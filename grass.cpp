/*

grass.cpp

Implementation of the Grass class

*/

#include "grass.h"

/*
 * Constructors
 */

Grass::Grass()
{
    _symbol = ',';
    _description = "Grass Seedling";
}

/*
 * Member Functions
 */

void Grass::update(int seconds)
{
    GameObject::update(seconds);
    
    if (_age.getMonth() >= 9)
    {
        _symbol = '_';
        _description = "Dead Grass";
    }
    else if (_age.getMonth() >= 6)
    {
        _symbol = 'W';
        _description = "Ripe Grass";
    }
    else if (_age.getMonth() >= 3)
    {
        _symbol = 'w';
        _description = "Young Grass";
    }
    else
    {
        _symbol = ',';
        _description = "Grass Seedling";
    }
}
