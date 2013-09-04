/*

world.cpp

Implementation of the World class

*/

#include "world.h"

World::World()
{
    width = 20;
    height = 10;
    charX = 0;
    charY = 0;
    
    tiles = new vector< vector<Tile> >(height, vector<Tile>(width));
    
    for (vector< vector<Tile> >::iterator rowItr = tiles->begin();
         rowItr != tiles->end();
         ++rowItr)
    {
        for (vector<Tile>::iterator colItr = rowItr->begin();
             colItr != rowItr->end();
             ++colItr)
        {
            Dirt d;
            colItr->addObject(d);
            
            if (rowItr == tiles->begin() || rowItr == tiles->end() ||
                colItr == rowItr->begin() || colItr == rowItr->end())
            {
                Stone s;
                colItr->addObject(s);
            }
        }
    }
    
    player = new Being();
}

World::~World()
{
    delete tiles;
    delete player;
}

int World::getWidth()
{
    return width;
}

int World::getHeight()
{
    return height;
}

int World::getCharX()
{
    return charX;
}

int World::getCharY()
{
    return charY;
}

void World::moveCharLeft()
{
    if (charX > 0)
    {
        charX--;
    }
}

void World::moveCharRight()
{
    if (charX < width - 1)
    {
        charX++;
    }
}

void World::moveCharUp()
{
    if (charY > 0)
    {
        charY--;
    }
}

void World::moveCharDown()
{
    if (charY < height - 1)
    {
        charY++;
    }
}

Tile World::getTile(int row, int col)
{
    if (row >= 0 && row < height &&
        col >= 0 && col < width)
    {
        return tiles->at(row).at(col);
    }
}

char World::getSymbol(int row, int col)
{
    if (row >= 0 && row < height &&
        col >= 0 && col < width)
    {
        if (row == charY && col == charX)
        {
            return player->Symbol();
        }
        else
        {
            return tiles->at(row).at(col).getSymbol();
        }
    }
}
