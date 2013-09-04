/*

gameobject.cpp

Implementation of the GameObject class

*/

#include "gameobject.h"

/*
 * Constructors
 */

GameObject::GameObject()
{
    symbol = ' ';
    description = "";
}

/*
 * Accessors
 */

int GameObject::X()
{
    return x;
}

int GameObject::Y()
{
    return y;
}

char GameObject::Symbol()
{
    return symbol;
}

string GameObject::Description()
{
    return description;
}

/*
 * Mutators
 */

void GameObject::X(int _x)
{
    x = _x;
}

void GameObject::Y(int _y)
{
    y = _y;
}

void GameObject::Symbol(char _symbol)
{
    symbol = _symbol;
}

void GameObject::Description(string _description)
{
    description = _description;
}



void GameObject::Update(int hours) { }
