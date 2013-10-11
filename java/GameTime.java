/*

GameTime.java

Represents time within the game world, distinct from real world time.

60 seconds / minute
60 minutes / hour
24 hours / day
30 days / month
360 days / year
12 months / year
3 months / season
4 seasons / year

*/

public class GameTime implements Comparable<GameTime>
{
    /*
     * Properties
     */
    
    private long milliseconds;
    
    /*
     * Constructors
     */
    
    public GameTime()
    {
        milliseconds = 0;
    }
    
    public GameTime(long milliseconds)
    {
        this();
        
        if (milliseconds > 0)
        {
            this.milliseconds = milliseconds;
        }
    }
    
    public GameTime(GameTime other)
    {
        milliseconds = other.milliseconds;
    }
    
    /*
     * Accessors
     */
    
    public long getMilliseconds()
    {
        return milliseconds % 1000L;
    }
    
    public long getSeconds()
    {
        return milliseconds / 1000L % 60L;
    }
    
    public long getMinutes()
    {
        return milliseconds / 60000L % 60L;
    }
    
    public long getHours()
    {
        return milliseconds / 3600000L % 24L;
    }
    
    public long getDays()
    {
        return milliseconds / 86400000L % 30L;
    }
    
    public long getMonths()
    {
        return milliseconds / 2592000000L % 12L;
    }
    
    public long getSeasons()
    {
        return milliseconds / 7776000000L % 4L;
    }
    
    public long getYears()
    {
        return milliseconds / 31104000000L;
    }
    
    public long getTotalMilliseconds()
    {
        return milliseconds;
    }
    
    public long getTotalSeconds()
    {
        return milliseconds / 1000L;
    }
    
    public long getTotalMinutes()
    {
        return milliseconds / 60000L;
    }
    
    public long getTotalHours()
    {
        return milliseconds / 3600000L;
    }
    
    public long getTotalDays()
    {
        return milliseconds / 86400000L;
    }
    
    public long getTotalMonths()
    {
        return milliseconds / 2592000000L;
    }
    
    public long getTotalSeasons()
    {
        return milliseconds / 7776000000L;
    }
    
    public long getTotalYears()
    {
        return getYears();
    }
    
    /*
     * Mutators
     */
    
    public void addMillisecond()
    {
        addMilliseconds(1L);
    }
    
    public void addMilliseconds(long milliseconds)
    {
        this.milliseconds += milliseconds;
    }
    
    public void addSecond()
    {
        addSeconds(1L);
    }
    
    public void addSeconds(long seconds)
    {
        milliseconds += (seconds * 1000L);
    }
    
    public void addMinute()
    {
        addMinutes(1L);
    }
    
    public void addMinutes(long minutes)
    {
        milliseconds += (minutes * 60000L);
    }
    
    public void addHour()
    {
        addHours(1L);
    }
    
    public void addHours(long hours)
    {
        milliseconds += (hours * 3600000L);
    }
    
    public void addDay()
    {
        addDays(1L);
    }
    
    public void addDays(long days)
    {
        milliseconds += (days * 86400000L);
    }
    
    public void addMonth()
    {
        addMonths(1L);
    }
    
    public void addMonths(long months)
    {
        milliseconds += (months * 2592000000L);
    }
    
    public void addSeason()
    {
        addSeasons(1L);
    }
    
    public void addSeasons(long seasons)
    {
        milliseconds += (seasons * 7776000000L);
    }
    
    public void addYear()
    {
        addYears(1L);
    }
    
    public void addYears(long years)
    {
        milliseconds += (years * 31104000000L);
    }
    
    /*
     * Public Methods
     */
    
    public boolean equals(Object other)
    {
        if (other instanceof GameTime)
        {
            return milliseconds == ((GameTime) other).milliseconds;
        }
        
        return false;
    }
    
    public int hashCode()
    {
        return (int)milliseconds;
    }
    
    public int compareTo(GameTime other)
    {
        return (int)milliseconds - (int)other.milliseconds;
    }
    
    public String toString()
    {
        return String.format("%d %04d-%02d-%02d %02d:%02d:%02d.%04d",
                             getSeasons(),
                             getYears(),
                             getMonths(),
                             getDays(),
                             getHours(),
                             getMinutes(),
                             getSeconds(),
                             getMilliseconds());
    }
}
