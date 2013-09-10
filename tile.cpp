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
    _objects = new list<GameObject*>;
}

Tile::Tile(const Tile& other)
{
    // Copy each object from the other list
    _objects = new list<GameObject*>;
    
    for (list<GameObject*>::iterator it = other._objects->begin();
         it != other._objects->end();
         ++it)
    {
        GameObject* object;
        *object = **it;
        _objects->push_front(object);
    }
}

Tile::~Tile()
{
    // Manually delete each object
    while (!_objects->empty())
    {
        delete _objects->front();
        _objects->pop_front();
    }
    
    delete _objects;
}

/*
 * Operators
 */

Tile& Tile::operator= (const Tile& other)
{
    if (this == &other)
    {
        return *this;
    }
    
    // Copy each object from the other list
    _objects->clear();
    
    for (list<GameObject*>::iterator it = other._objects->begin();
         it != other._objects->end();
         ++it)
    {
        GameObject* object;
        *object = **it;
        _objects->push_front(object);
    }
    
    return *this;
}

/*
 * Accessors
 */

char Tile::getSymbol()
{
    if (_objects->size() > 0)
    {
        return _objects->front()->getSymbol();
    }
    else
    {
        return ' ';
    }
}

list<string> Tile::getDescriptions()
{
    list<string> descriptions;
    
    if (_objects->size() > 0)
    {
        for (list<GameObject*>::iterator it = _objects->begin();
             it != _objects->end();
             ++it)
        {
            string description(1, (*it)->getSymbol());
            description += " " + (*it)->getDescription();
            descriptions.push_back(description);
        }
    }
    else
    {
        descriptions.push_back("Nothing here.");
    }
    
    return descriptions;
}

/*
 * Mutators
 */

void Tile::addObject(GameObject* object)
{
    _objects->push_front(object);
}

/*
 * Member Functions
 */

GameObject* Tile::harvest()
{
    GameObject* crop = _objects->front()->harvest();
    
    if (crop != 0)
    {
        delete _objects->front();
        _objects->pop_front();
        return crop;
    }
    
    return 0;
}

void Tile::update(int seconds)
{
    // Update all objects on the Tile
    for (list<GameObject*>::iterator it = _objects->begin();
         it != _objects->end();
         ++it)
    {
        (*it)->update(seconds);
    }
}
