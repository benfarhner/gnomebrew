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
    symbol = 'w';
    description = "Grass";
}

/*
 * Member Functions
 */

void Grass::update(int seconds)
{
    if (seconds > 0)
    {
        _age += seconds;
        
        if (_age >= 60 * 60 * 24 * 10)
        {
            symbol = 'W';
        }
        
        if (_age >= 60 * 60 * 24 * 20)
        {
            symbol = '_';
        }
    }
}
