/*

world.cpp

Implementation of the World class

*/

#include "world.h"



/*
 * Constructors
 */

World::World()
{
    _width = 20;
    _height = 10;
    
    _map = newpad(_height, _width);
    
    _player = new Being();
    
    generateWorld();
}

World::World(int width, int height)
{
    _width = width > 0 ? width : 1;
    _height = height > 0 ? height : 1;
    
    _map = newpad(_height, _width);
    
    _player = new Being();
    
    generateWorld();
}

World::~World()
{
    delwin(_map);
    
    delete _tiles;
    delete _player;
}

/*
 * Accessors
 */

int World::getWidth()
{
    return _width;
}

int World::getHeight()
{
    return _height;
}

Tile World::getTile(int row, int col)
{
    if (row >= 0 && row < _height &&
        col >= 0 && col < _width)
    {
        return _tiles->at(row).at(col);
    }
}

char World::getSymbol(int row, int col)
{
    if (row >= 0 && row < _height &&
        col >= 0 && col < _width)
    {
        if (row == _player->Y() && col == _player->X())
        {
            return _player->Symbol();
        }
        else
        {
            return _tiles->at(row).at(col).getSymbol();
        }
    }
}

int World::getCharX()
{
    return _player->X();
}

int World::getCharY()
{
    return _player->Y();
}

/*
 * Mutators
 */

void World::moveCharLeft()
{
    if (_player->X() > 0)
    {
        _player->X(_player->X() - 1);
    }
}

void World::moveCharRight()
{
    if (_player->X() < _width - 1)
    {
        _player->X(_player->X() + 1);
    }
}

void World::moveCharUp()
{
    if (_player->Y() > 0)
    {
        _player->Y(_player->Y() - 1);
    }
}

void World::moveCharDown()
{
    if (_player->Y() < _height - 1)
    {
        _player->Y(_player->Y() + 1);
    }
}

/*
 * Member Functions
 */

WINDOW* World::render()
{
    for (int row = 0; row < _height; row++)
    {
        for (int col = 0; col < _width; col++)
        {
            mvwaddch(_map, row, col, getSymbol(row, col));
        }
    }
    
    return _map;
}

/*
 * Private Member Functions
 */

void World::generateWorld()
{
    // Seed random number generator with current timestamp
    srand(time(NULL));
    
    _tiles = new vector< vector<Tile> >(_height, vector<Tile>(_width));
    
    // Initialize entire map with Dirt
    for (vector< vector<Tile> >::iterator rowItr = _tiles->begin();
         rowItr != _tiles->end();
         ++rowItr)
    {
        for (vector<Tile>::iterator colItr = rowItr->begin();
             colItr != rowItr->end();
             ++colItr)
        {
            Dirt d;
            colItr->addObject(d);
            
            if (rand() % 100 > 33)
            {
                Stone s;
                colItr->addObject(s);
            }
        }
    }
}
