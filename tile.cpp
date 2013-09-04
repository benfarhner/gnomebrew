/*

tile.cpp

Implementation of the Tile class

*/

#include "tile.h"

/*
 * Constructors
 */

Tile::Tile()
{
    objects = new list<GameObject>;
}

Tile::Tile(const Tile& other)
{
    objects = new list<GameObject>(*(other.objects));
}

Tile::~Tile()
{
    delete objects;
}

/*
 * Operators
 */

Tile& Tile::operator= (const Tile& other)
{
    delete objects;
    objects = new list<GameObject>(*(other.objects));
    return *this;
}

/*
 * Functions
 */

void Tile::addObject(GameObject object)
{
    objects->push_front(object);
}

char Tile::getSymbol()
{
    if (objects->size() > 0)
    {
        return objects->front().Symbol();
    }
    else
    {
        return ' ';
    }
}

list<string> Tile::getDescriptions()
{
    list<string> descriptions;
    
    if (objects->size() > 0)
    {
        list<GameObject>::iterator it;
        
        for(it = objects->begin(); it != objects->end(); ++it)
        {
            descriptions.push_back(it->Description());
        }
    }
    else
    {
        descriptions.push_back("Nothing here.");
    }
    
    return descriptions;
}
