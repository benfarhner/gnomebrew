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
    _isRunning = true;
    
    _window = newwin(LINES, COLS, 0, 0);
    keypad(_window, TRUE);
    nodelay(_window, TRUE);
    
    // Initialize menu
    list<MenuItem> items;
    items.push_back(MenuItem("Resume"));
    items.push_back(MenuItem("Quit"));
	_menu = new Menu(this, items);
    _isMenuVisible = false;
}

Game::~Game()
{
    delwin(_window);
}

/*
 * Accessors
 */

bool Game::isRunning()
{
    return _isRunning;
}

/*
 * Member Functions
 */

void Game::update()
{
    if (!_isMenuVisible)
    {
        render();
        handleInput();
        _time.addSecond(SECONDS_PER_UPDATE);
    }
    else
    {
        _menu->update();
    }
}

/*
 * Private Member Functions
 */

void Game::handleInput()
{
    int input = wgetch(_window);
    
    if (input != ERR)
    {
        switch (input)
        {
            case KEY_UP:
                _world.moveCharUp();
                break;
            case KEY_DOWN:
                _world.moveCharDown();
                break;
            case KEY_LEFT:
                _world.moveCharLeft();
                break;
            case KEY_RIGHT:
                _world.moveCharRight();
                break;
            case KEY_F(1):
                showMenu();
                break;
        }
    }
}

void Game::handleMenuSelection(MenuItem selection)
{
	if (selection.getText() == "Resume")
	{
		_isMenuVisible = false;
		touchwin(_window);
	}
	else if (selection.getText() == "Quit")
	{
		_isRunning = false;
	}
}

void Game::render()
{
    /* Render world tiles */
    
    for (int row = 0; row < _world.getHeight(); row++)
    {
        for (int col = 0; col < _world.getWidth(); col++)
        {
            mvwaddch(_window, row, col, _world.getSymbol(row, col));
        }
    }
    
    /* Render current tile descriptions */
    
    Tile currentTile = _world.getTile(_world.getCharY(), _world.getCharX());
    list<string> descriptions(currentTile.getDescriptions());
    int index;
    list<string>::iterator it;
    
    for (index = 0; index < _world.getHeight(); ++index)
    {
        mvwprintw(_window, index, _world.getWidth() + 2, "          ");
    }
    
    index = 0;
    
    for (it = descriptions.begin(); it != descriptions.end(); ++it)
    {
        mvwprintw(_window, index, _world.getWidth() + 2, it->c_str());
        ++index;
    }
    
    /* Render current game time */
    
    string time = GameTime::toPaddedString(_time.getHours()) + ":" +
                  GameTime::toPaddedString(_time.getMinutes()) + " " +
                  GameTime::toPaddedString(_time.getYear(), 4) + "-" +
                  GameTime::toPaddedString(_time.getMonth()) + "-" +
                  GameTime::toPaddedString(_time.getDay());
    
    for (int i = 0; i < COLS; ++i)
    {
        mvwprintw(_window, LINES - 1, i, "=");
    }
    
    mvwprintw(_window, LINES - 1, (COLS - time.length()) / 2, time.c_str());
    
    wrefresh(_window);
}

void Game::showMenu()
{
    _isMenuVisible = true;
    _menu->update();
}
