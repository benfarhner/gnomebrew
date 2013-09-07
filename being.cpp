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
 * Acccessors
 */

list<GameObject*>* Being::getInventory()
{
    return &_inventory;
}

/*
 * Mutators
 */

void Being::addObject(GameObject* object)
{
    if (object != 0)
    {
        _inventory.push_back(object);
    }
}

/*
 * Member Functions
 */

bool Being::brew(Recipe* recipe)
{
    // Check if the Being has the resources to brew this Recipe
}

void Being::move(Direction direction)
{
    switch (direction)
    {
        case DirectionNorth:
            --_y;
            break;
        case DirectionSouth:
            ++_y;
            break;
        case DirectionEast:
            ++_x;
            break;
        case DirectionWest:
            --_x;
            break;
    }
}

void Being::update(int seconds)
{
    GameObject::update(seconds);
}
