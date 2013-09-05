/*

game.cpp

Implementation of the Game class

*/

#include "game.h"



/*
 * Static Constants
 */

const int Game::SECONDS_PER_UPDATE = 1;

/*
 * Constructors
 */

Game::Game()
{
    _description = newwin(LINES - 2, 20, 0, COLS - 20);
    _footer = newwin(2, COLS, LINES - 2, 0);
    
    _world = new World(200, 100);
}

Game::~Game()
{
    delwin(_description);
    delwin(_footer);
    
    delete _world;
}

/*
 * Member Functions
 */

void Game::update()
{
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
                _world->moveCharUp();
                break;
            case KEY_DOWN:
                _world->moveCharDown();
                break;
            case KEY_LEFT:
                _world->moveCharLeft();
                break;
            case KEY_RIGHT:
                _world->moveCharRight();
                break;
        }
    }
}

void Game::render()
{
    /* Render world tiles */
    
    WINDOW* map = _world->render();
    int mapHeight = LINES - 2;
    int mapWidth = COLS - 23;
    int mapRow = _world->getCharY() - (mapHeight / 2);
    int mapCol = _world->getCharX() - (mapWidth / 2);
    
    if (mapRow < 0)
    {
        mapRow = 0;
    }
    if (mapRow + mapHeight > _world->getHeight())
    {
        mapRow = _world->getHeight() - mapHeight;
    }
    
    if (mapCol < 0)
    {
        mapCol = 0;
    }
    if (mapCol + mapWidth > _world->getWidth())
    {
        mapCol = _world->getWidth() - mapWidth;
    }
    
    pnoutrefresh(map, mapRow, mapCol, 0, 0, mapHeight, mapWidth);
    
    /* Render current tile descriptions */
    
    Tile currentTile = _world->getTile(_world->getCharY(), _world->getCharX());
    list<string> descriptions(currentTile.getDescriptions());
    list<string>::iterator it;
    int index = 0;
    
    wclear(_description);
    
    for (it = descriptions.begin(); it != descriptions.end(); ++it)
    {
        mvwprintw(_description, index, 0, it->c_str());
        ++index;
    }
    
    wnoutrefresh(_description);
    
    /* Render current game time */
    
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
}
