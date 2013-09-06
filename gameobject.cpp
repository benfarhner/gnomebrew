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
    _x = 0;
    _y = 0;
    _symbol = ' ';
    _description = "";
    _age.reset();
}

GameObject::GameObject(const GameObject& other)
{
    _x = other._x;
    _y = other._y;
    _symbol = other._symbol;
    _description = other._description;
    _age = other._age;
}

/*
 * Operators
 */

GameObject& GameObject::operator=(const GameObject& other)
{
    _x = other._x;
    _y = other._y;
    _symbol = other._symbol;
    _description = other._description;
    _age = other._age;
}

/*
 * Accessors
 */

int GameObject::getX()
{
    return _x;
}

int GameObject::getY()
{
    return _y;
}

char GameObject::getSymbol()
{
    return _symbol;
}

string GameObject::getDescription()
{
    return _description;
}

GameTime GameObject::getAge()
{
    return _age;
}

/*
 * Mutators
 */

void GameObject::setX(int x)
{
    _x = x;
}

void GameObject::setY(int y)
{
    _y = y;
}

void GameObject::setSymbol(char symbol)
{
    _symbol = symbol;
}

void GameObject::setDescription(string description)
{
    _description = description;
}

void GameObject::setAge(GameTime age)
{
    _age = age;
}

/*
 * Member Functions
 */

void GameObject::update(int seconds)
{
    _age.addSecond(seconds);
}
