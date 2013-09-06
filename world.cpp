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
    
#ifndef DEBUG
    _map = newpad(_height, _width);
#endif
    
    _player = new Being();
    
    generateWorld();
}

World::World(int width, int height)
{
    _width = width > 0 ? width : 1;
    _height = height > 0 ? height : 1;
    
#ifndef DEBUG
    _map = newpad(_height, _width);
#endif
    
    _player = new Being();
    
    generateWorld();
}

World::~World()
{
#ifndef DEBUG
    delwin(_map);
#endif

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

char World::getSymbol(int row, int col)
{
    if (row >= 0 && row < _height &&
        col >= 0 && col < _width)
    {
        if (row == _player->getY() && col == _player->getX())
        {
            return _player->getSymbol();
        }
        else
        {
            return _tiles->at(row).at(col).getSymbol();
        }
    }
}

Tile* World::getTile(int row, int col)
{
    if (row >= 0 && row < _height &&
        col >= 0 && col < _width)
    {
        return &(_tiles->at(row).at(col));
    }
}

int World::getCharX()
{
    return _player->getX();
}

int World::getCharY()
{
    return _player->getY();
}

/*
 * Mutators
 */

void World::moveCharLeft()
{
    if (_player->getX() > 0)
    {
        _player->setX(_player->getX() - 1);
    }
}

void World::moveCharRight()
{
    if (_player->getX() < _width - 1)
    {
        _player->setX(_player->getX() + 1);
    }
}

void World::moveCharUp()
{
    if (_player->getY() > 0)
    {
        _player->setY(_player->getY() - 1);
    }
}

void World::moveCharDown()
{
    if (_player->getY() < _height - 1)
    {
        _player->setY(_player->getY() + 1);
    }
}

/*
 * Member Functions
 */

void World::update(int seconds)
{
    // Update individual tiles
    for (vector< vector<Tile> >::iterator rowItr = _tiles->begin();
         rowItr != _tiles->end();
         ++rowItr)
    {
        for (vector<Tile>::iterator colItr = rowItr->begin();
             colItr != rowItr->end();
             ++colItr)
        {
            colItr->update(seconds);
        }
    }
}

WINDOW* World::render()
{
#ifndef DEBUG
    for (int row = 0; row < _height; row++)
    {
        for (int col = 0; col < _width; col++)
        {
            mvwaddch(_map, row, col, getSymbol(row, col));
        }
    }
    
    return _map;
#endif
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
            GameObject* object = new Dirt();
            colItr->addObject(object);
            
            int r = rand() % 100;
            
            if (r < 33)
            {
                object = new Stone();
                colItr->addObject(object);
            }
            
            if (r >= 66)
            {
                object = new Grass();
                colItr->addObject(object);
            }
        }
    }
}
