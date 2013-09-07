/*

gameobject.h

Represents a generic game object.

*/

#ifndef GAMEOBJECT_H
#define GAMEOBJECT_H

#include <string>
using namespace std;

#include "updatable.h"
#include "gametime.h"

class GameObject: public Updatable
{
    public:
        // Constructors
        GameObject();
        GameObject(const GameObject&);
        
        // Operators
        GameObject& operator=(const GameObject&);
        
        // Accessors
        int getX();
        int getY();
        char getSymbol();
        string getDescription();
        GameTime getAge();
        
        // Mutators
        void setX(int);
        void setY(int);
        void setSymbol(char);
        void setDescription(string);
        void setAge(GameTime);
        
        // Member Functions
        virtual GameObject* harvest();
        virtual void update(int seconds = 0);
        
        enum Direction
        {
            DirectionNorth,
            DirectionSouth,
            DirectionEast,
            DirectionWest
        };
    
    protected:
        // Properties
        int _x;
        int _y;
        char _symbol;
        string _description;
        GameTime _age;
};

#endif
