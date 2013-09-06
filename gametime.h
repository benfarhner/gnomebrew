/*

gametime.h

Represents time within the game world, distinct from real world time.

*/

#ifndef GAMETIME_H
#define GAMETIME_H

#include <sstream>
#include <string>
using namespace std;

class GameTime
{
	public:
		// Constructors
		GameTime();
		GameTime(const GameTime&);
		
		// Operators
		GameTime& operator=(const GameTime&);
		
		// Accessors
		int getYear();
		int getSeason();
		int getMonth();
		int getDay();
		int getHours();
		int getMinutes();
		int getSeconds();
		
		// Mutators
		void setYear(int);
		void setSeason(int);
		void setMonth(int);
		void setDay(int);
		void setHours(int);
		void setMinutes(int);
		void setSeconds(int);
		void addYear(int year = 1);
		void addSeason(int season = 1);
		void addMonth(int month = 1);
		void addDay(int day = 1);
		void addHour(int hour = 1);
		void addMinute(int minute = 1);
		void addSecond(int second = 1);
		
		// Member Functions
		void reset();
    
        // Static Functions
        static string toPaddedString(int, int length = 2);
		
	private:
		// Private Properties
		int _year;
		int _season;
		int _month;
		int _day;
		int _hours;
		int _minutes;
		int _seconds;
		
		// Static Constants
		static const int SEASON_MIN;
		static const int SEASON_MAX;
		static const int SEASONS_PER_YEAR;
		static const int MONTH_MIN;
		static const int MONTH_MAX;
		static const int MONTHS_PER_YEAR;
		static const int DAY_MIN;
		static const int DAY_MAX;
		static const int DAYS_PER_MONTH;
		static const int HOURS_MIN;
		static const int HOURS_MAX;
		static const int HOURS_PER_DAY;
		static const int MINUTES_MIN;
		static const int MINUTES_MAX;
		static const int MINUTES_PER_HOUR;
		static const int SECONDS_MIN;
		static const int SECONDS_MAX;
		static const int SECONDS_PER_MINUTE;
};

#endif
