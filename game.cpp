/*

game.cpp

Implementation of the Game class

*/

#include "game.h"

/*
 * Static Constants
 */

const int Game::SECONDS_PER_UPDATE = 3600;

/*
 * Constructors
 */

Game::Game()
{
    _time.setHours(6); // 6:00am
    
#ifndef DEBUG
    _description = newwin(LINES - 2, 20, 0, COLS - 20);
    _footer = newwin(2, COLS, LINES - 2, 0);
#endif
    
    _world = new World(200, 100);
    _player = new Being();
}

Game::~Game()
{
#ifndef DEBUG
    delwin(_description);
    delwin(_footer);
#endif
    
    delete _world;
    delete _player;
    
    for (list<Recipe*>::iterator it = _recipes.begin();
         it != _recipes.end();
         ++it)
    {
        delete *it;
    }
}

/*
 * Member Functions
 */

void Game::update()
{
    _world->update(SECONDS_PER_UPDATE);
    render();
    
    // Run at about 60 "frames" per second
    usleep(17000);
    _time.addSecond(SECONDS_PER_UPDATE);
}

/*
 * Private Member Functions
 */

void Game::handleInput(int input)
{
    if (input != ERR)
    {
        switch (input)
        {
            case KEY_UP:
                if (_player->getY() > 0)
                {
                    _player->move(GameObject::DirectionNorth);
                }
                break;
            case KEY_DOWN:
                if (_player->getY() < _world->getHeight() - 1)
                {
                    _player->move(GameObject::DirectionSouth);
                }
                break;
            case KEY_LEFT:
                if (_player->getX() > 0)
                {
                    _player->move(GameObject::DirectionWest);
                }
                break;
            case KEY_RIGHT:
                if (_player->getX() < _world->getWidth() - 1)
                {
                    _player->move(GameObject::DirectionEast);
                }
                break;
            case 'h': // Harvest
                GameObject* crop = _world->harvest(_player->getY(), _player->getX());
                
                if (crop != 0)
                {
                    _player->addObject(crop);
                }
        }
    }
}

void Game::render()
{
#ifndef DEBUG
    /// Render world tiles
    
    WINDOW* map = _world->render();
    int mapHeight = LINES - 3;
    int mapWidth = COLS - 23;
    int mapRow = _player->getY() - (mapHeight / 2);
    int mapCol = _player->getX() - (mapWidth / 2);
    
    if (mapRow < 0)
    {
        mapRow = 0;
    }
    if (mapRow + mapHeight >= _world->getHeight())
    {
        mapRow = _world->getHeight() - mapHeight - 1;
    }
    
    if (mapCol < 0)
    {
        mapCol = 0;
    }
    if (mapCol + mapWidth >= _world->getWidth())
    {
        mapCol = _world->getWidth() - mapWidth - 1;
    }
    
    // Render player on map
    mvwaddch(map, _player->getY(), _player->getX(), _player->getSymbol());
    
    pnoutrefresh(map, mapRow, mapCol, 0, 0, mapHeight, mapWidth);
    
    /// Render current tile descriptions
    
    Tile* currentTile = _world->getTile(_player->getY(), _player->getX());
    list<string> descriptions = currentTile->getDescriptions();
    list<string>::iterator it;
    int index = 0;
    
    werase(_description);
    
    for (it = descriptions.begin(); it != descriptions.end(); ++it)
    {
        mvwprintw(_description, index++, 0, it->c_str());
    }
    
    // Render player inventory
    mvwprintw(_description, ++index, 0, "Inventory");
    mvwprintw(_description, ++index, 0, "=========");
    
    list<GameObject*>* inventory = _player->getInventory();
    
    for (list<GameObject*>::iterator it = inventory->begin();
         it != inventory->end();
         ++it)
    {
        mvwprintw(_description, ++index, 0, (*it)->getDescription().c_str());
    }
    
    wnoutrefresh(_description);
    
    /// Render current game time
    
    string time = " " + GameTime::toPaddedString(_time.getHours()) + ":" +
                  GameTime::toPaddedString(_time.getMinutes()) + " " +
                  GameTime::toPaddedString(_time.getYear(), 4) + "-" +
                  GameTime::toPaddedString(_time.getMonth()) + "-" +
                  GameTime::toPaddedString(_time.getDay()) + " ";
    
    for (int i = 0; i < COLS; ++i)
    {
        mvwprintw(_footer, 0, i, "=");
        mvwprintw(_footer, 1, i, "=");
    }
    
    mvwprintw(_footer, 1, (COLS - time.length()) / 2, time.c_str());
    wnoutrefresh(_footer);
    
    // Push all updates to the screen
    doupdate();
#endif
}
