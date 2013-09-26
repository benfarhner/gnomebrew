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
    
    private long seconds;
    
    /*
     * Constructors
     */
    
    public GameTime()
    {
        seconds = 0;
    }
    
    public GameTime(GameTime other)
    {
        seconds = other.seconds;
    }
    
    /*
     * Accessors
     */
    
    public long getSeconds()
    {
        return seconds % 60L;
    }
    
    public long getMinutes()
    {
        return seconds / 60L % 60L;
    }
    
    public long getHours()
    {
        return seconds / 3600L % 24L;
    }
    
    public long getDays()
    {
        return seconds / 86400L % 30L;
    }
    
    public long getMonths()
    {
        return seconds / 2592000L % 12L;
    }
    
    public long getSeasons()
    {
        return seconds / 7776000L % 4L;
    }
    
    public long getYears()
    {
        return seconds / 31104000L;
    }
    
    public long getTotalSeconds()
    {
        return seconds;
    }
    
    public long getTotalMinutes()
    {
        return seconds / 60L;
    }
    
    public long getTotalHours()
    {
        return seconds / 3600L;
    }
    
    public long getTotalDays()
    {
        return seconds / 86400L;
    }
    
    public long getTotalMonths()
    {
        return seconds / 2592000L;
    }
    
    public long getTotalSeasons()
    {
        return seconds / 7776000L;
    }
    
    public long getTotalYears()
    {
        return getYears();
    }
    
    /*
     * Mutators
     */
    
    public void addSecond()
    {
        addSeconds(1L);
    }
    
    public void addSeconds(long seconds)
    {
        this.seconds += seconds;
    }
    
    public void addMinute()
    {
        addMinutes(1L);
    }
    
    public void addMinutes(long minutes)
    {
        seconds += (minutes * 60L);
    }
    
    public void addHour()
    {
        addHours(1L);
    }
    
    public void addHours(long hours)
    {
        seconds += (hours * 3600L);
    }
    
    public void addDay()
    {
        addDays(1L);
    }
    
    public void addDays(long days)
    {
        seconds += (days * 86400L);
    }
    
    public void addMonth()
    {
        addMonths(1L);
    }
    
    public void addMonths(long months)
    {
        seconds += (months * 2592000L);
    }
    
    public void addSeason()
    {
        addSeasons(1L);
    }
    
    public void addSeasons(long seasons)
    {
        seconds += (seasons * 7776000L);
    }
    
    public void addYear()
    {
        addYears(1L);
    }
    
    public void addYears(long years)
    {
        seconds += (years * 31104000L);
    }
    
    /*
     * Public Methods
     */
    
    public boolean equals(Object other)
    {
        if (other instanceof GameTime)
        {
            return seconds == ((GameTime) other).seconds;
        }
        
        return false;
    }
    
    public int hashCode()
    {
        return (int)seconds;
    }
    
    public int compareTo(GameTime other)
    {
        return (int)seconds - (int)other.seconds;
    }
    
    public String toString()
    {
        return String.format("%d %04d-%02d-%02d %02d:%02d:%02d",
                             getSeasons(),
                             getYears(),
                             getMonths(),
                             getDays(),
                             getHours(),
                             getMinutes(),
                             getSeconds());
    }
}
