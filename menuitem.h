/*

menuitem.h

Defines a MenuItem object used in Menus

*/

#ifndef MENUITEM_H
#define MENUITEM_H

#include <string>
using namespace std;

class MenuItem
{
	public:
		// Constructors
		MenuItem();
		MenuItem(string, bool enabled = true);
		MenuItem(const MenuItem&);
		~MenuItem();
		
		// Accessors
		string getText();
		bool isEnabled();
		
		// Mutators
		void enable(bool enabled = true);
		
	private:
		// Private Properties
		string _text;
		bool _enabled;
};

#endif
