/*

menu.cpp

Implementation of the Menu class

*/

#include "menu.h"

/*
 * Constructors
 */

Menu::Menu(GameManager* owner)
{
    _owner = owner;
    selectFirst();
    
#ifndef DEBUG
    // Initialize menu
    _menu = newwin(_items.size() + 4, COLS / 4, 5, COLS / 8 * 3);
    keypad(_menu, true);
    box(_menu, 0, 0);
#endif
}

Menu::Menu(GameManager* owner, list<MenuItem> items)
{
    _owner = owner;
    
    _items = items;
    selectFirst();
    
#ifndef DEBUG
    // Initialize menu
    _menu = newwin(_items.size() + 4, COLS / 4, 5, COLS / 8 * 3);
    keypad(_menu, true);
    wattron(_menu, COLOR_PAIR(COLOR_PAIR_NORMAL));
    box(_menu, 0, 0);
#endif
}

Menu::Menu(const Menu& other)
{
    _owner = other._owner;
    
    _items = other._items;
    selectFirst();
    
#ifndef DEBUG
    // Initialize menu
    _menu = newwin(_items.size() + 4, COLS / 4, 5, COLS / 8 * 3);
    keypad(_menu, true);
    wattron(_menu, COLOR_PAIR(COLOR_PAIR_NORMAL));
    box(_menu, 0, 0);
#endif
}

Menu::~Menu()
{
#ifndef DEBUG
    delwin(_menu);
#endif
}

/*
 * Mutator Functions
 */

void Menu::setItems(list<MenuItem> items)
{
}

/*
 * Member Functions
 */

void Menu::update()
{
    render();
    handleInput();
}

/*
 * Private Member Functions
 */

void Menu::handleInput()
{
#ifndef DEBUG
    int input = wgetch(_menu);
    
    switch (input)
    {
        case KEY_DOWN:
        case KEY_RIGHT:
            selectNext();
            break;
        case KEY_UP:
        case KEY_LEFT:
            selectPrevious();
            break;
        case 10: // newline
        case 13: // carriage return
        case 32: // space
            _owner->handleMenuSelection(*_selected);
            break;
    }
#endif
}

void Menu::render()
{
#ifndef DEBUG
    int row = 2, y, col;
    
    wattron(_menu, A_BOLD);
    
    for (list<MenuItem>::iterator it = _items.begin();
         it != _items.end();
         ++it)
    {
        if (it == _selected)
        {
            wattron(_menu, A_REVERSE);
        }
        
        if (!(it->isEnabled()))
        {
            wattroff(_menu, A_BOLD);
        }
        
        getmaxyx(_menu, y, col);
        col = (col - it->getText().length()) / 2;
        mvwprintw(_menu, row++, col, it->getText().c_str());
        
        wattroff(_menu, A_REVERSE);
        wattron(_menu, A_BOLD);
    }
    
    wattroff(_menu, A_BOLD);
    
    touchwin(_menu);
    wrefresh(_menu);
#endif
}

void Menu::selectNext()
{
    do
    {
        if (_selected == _items.end())
        {
            _selected = _items.begin();
        }
        else
        {
            ++_selected;
        }
    }
    while (!(_selected->isEnabled()));
}

void Menu::selectPrevious()
{
    do
    {
        if (_selected == _items.begin())
        {
            _selected = _items.end();
        }
        else
        {
            --_selected;
        }
    }
    while (!(_selected->isEnabled()));
}

void Menu::selectFirst()
{
    // Clever!
    _selected = _items.end();
    selectNext();
}
