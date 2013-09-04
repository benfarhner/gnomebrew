/*

gametime.cpp

Implementation of the GameTime class

*/

#include "gametime.h"



/*
 * Static Constants
 */

const int GameTime::SEASON_MIN = 1;
const int GameTime::SEASON_MAX = 4;
const int GameTime::SEASONS_PER_YEAR = 4;
const int GameTime::MONTH_MIN = 1;
const int GameTime::MONTH_MAX = 12;
const int GameTime::MONTHS_PER_YEAR = 12;
const int GameTime::DAY_MIN = 1;
const int GameTime::DAY_MAX = 30;
const int GameTime::DAYS_PER_MONTH = 30;
const int GameTime::HOURS_MIN = 0;
const int GameTime::HOURS_MAX = 23;
const int GameTime::HOURS_PER_DAY = 24;
const int GameTime::MINUTES_MIN = 0;
const int GameTime::MINUTES_MAX = 59;
const int GameTime::MINUTES_PER_HOUR = 60;
const int GameTime::SECONDS_MIN = 0;
const int GameTime::SECONDS_MAX = 59;
const int GameTime::SECONDS_PER_MINUTE = 60;

/*
 * Static Functions
 */

string GameTime::toPaddedString(int i, int length)
{
    stringstream ss;
    ss << i;
    string s = ss.str();
    
    while (s.length() < length)
    {
        s = "0" + s;
    }
    
    return s;
}

/*
 * Constructors
 */

GameTime::GameTime()
{
	// Initialize default time to 6:00am on the first day of Year Zero
	_year = 0;
	_season = SEASON_MIN;
	_month = MONTH_MIN;
	_day = DAY_MIN;
	_hours = 6;
	_minutes = MINUTES_MIN;
	_seconds = SECONDS_MIN;
}

/*
 * Accessors
 */

int GameTime::getYear()
{
	return _year;
}

int GameTime::getSeason()
{
	return _season;
}

int GameTime::getMonth()
{
	return _month;
}

int GameTime::getDay()
{
	return _day;
}

int GameTime::getHours()
{
	return _hours;
}

int GameTime::getMinutes()
{
	return _minutes;
}

int GameTime::getSeconds()
{
	return _seconds;
}

/*
 * Mutators
 */

void GameTime::setYear(int year)
{
	year = _year;
}

void GameTime::setSeason(int season)
{
	if (season < SEASON_MIN)
	{
		_season = SEASON_MIN;
	}
	else if (season > SEASON_MAX)
	{
		_season = SEASON_MAX;
	}
	else
	{
		_season = season;
	}
}

void GameTime::setMonth(int month)
{
	if (month < MONTH_MIN)
	{
		_month = MONTH_MIN;
	}
	else if (month > MONTH_MAX)
	{
		_month = MONTH_MAX;
	}
	else
	{
		_month = month;
	}
}

void GameTime::setDay(int day)
{
	if (day < DAY_MIN)
	{
		_day = DAY_MIN;
	}
	else if (day > DAY_MAX)
	{
		_day = DAY_MAX;
	}
	else
	{
		_day = day;
	}
}

void GameTime::setHours(int hours)
{
	if (hours < HOURS_MIN)
	{
		_hours = HOURS_MIN;
	}
	else if (hours > HOURS_MAX)
	{
		_hours = HOURS_MAX;
	}
	else
	{
		_hours = hours;
	}
}

void GameTime::setMinutes(int minutes)
{
	if (minutes < MINUTES_MIN)
	{
		_minutes = MINUTES_MIN;
	}
	else if (minutes > MINUTES_MAX)
	{
		_minutes = MINUTES_MAX;
	}
	else
	{
		_minutes = minutes;
	}
}

void GameTime::setSeconds(int seconds)
{
	if (seconds < SECONDS_MIN)
	{
		_seconds = SECONDS_MIN;
	}
	else if (seconds > SECONDS_MAX)
	{
		_seconds = SECONDS_MAX;
	}
	else
	{
		_seconds = seconds;
	}
}

void GameTime::addYear(int year)
{
	_year += year;
}

void GameTime::addSeason(int season)
{
	_season += season;
	
	while (_season < SEASON_MIN)
	{
		addYear(-1);
		_season += SEASONS_PER_YEAR;
	}
	
	while (_season > SEASON_MAX)
	{
		addYear(1);
		_season -= SEASONS_PER_YEAR;
	}
}

void GameTime::addMonth(int month)
{
	_month += month;
	
	while (_month < MONTH_MIN)
	{
		addYear(-1);
		_month += MONTHS_PER_YEAR;
	}
	
	while (_month > MONTH_MAX)
	{
		addYear(1);
		_month -= MONTHS_PER_YEAR;
	}
	
	_season = (_month / MONTHS_PER_YEAR * SEASONS_PER_YEAR) + 1;
}

void GameTime::addDay(int day)
{
	_day += day;
	
	while (_day < DAY_MIN)
	{
		addMonth(-1);
		_day += DAYS_PER_MONTH;
	}
	
	while (_day > DAY_MAX)
	{
		addMonth(1);
		_day -= DAYS_PER_MONTH;
	}
}

void GameTime::addHour(int hour)
{
	_hours += hour;
	
	while (_hours < HOURS_MIN)
	{
		addDay(-1);
		_hours += HOURS_PER_DAY;
	}
	
	while (_hours > HOURS_MAX)
	{
		addDay(1);
		_hours -= HOURS_PER_DAY;
	}
}

void GameTime::addMinute(int minute)
{
	_minutes += minute;
	
	while (_minutes < MINUTES_MIN)
	{
		addHour(-1);
		_minutes += MINUTES_PER_HOUR;
	}
	
	while (_minutes > MINUTES_MAX)
	{
		addHour(1);
		_minutes -= MINUTES_PER_HOUR;
	}
}

void GameTime::addSecond(int second)
{
	_seconds += second;
	
	while (_seconds < SECONDS_MIN)
	{
		addMinute(-1);
		_seconds += SECONDS_PER_MINUTE;
	}
	
	while (_seconds > SECONDS_MAX)
	{
		addMinute(1);
		_seconds -= SECONDS_PER_MINUTE;
	}
}
