/*

menuitem.cpp

Implementation of the MenuItem class

*/

#include "menuitem.h"

/*
 * Constructors
 */

MenuItem::MenuItem()
{
	_text = "";
	_enabled = true;
}

MenuItem::MenuItem(string text, bool enabled)
{
	_text = text;
	_enabled = enabled;
}

MenuItem::MenuItem(const MenuItem& other)
{
	_text = other._text;
	_enabled = other._enabled;
}

MenuItem::~MenuItem()
{
}

/*
 * Accessors
 */

string MenuItem::getText()
{
	return _text;
}

bool MenuItem::isEnabled()
{
    return _enabled;
}

/*
 * Mutators
 */

void MenuItem::enable(bool enabled)
{
    _enabled = enabled;
}
