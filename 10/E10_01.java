/*
  Design a class named Time. The class contains:

  - The data fields hour, minute, and second that represent a time.
  - A no-arg constructor that creates a Time object for the current time.
    (The values of the data fields will represent the current time.)
  - A constructor that constructs a Time object with a specified elapsed time
    since midnight, January 1, 1970, in milliseconds. (The values of the data
    fields will represent this time.)
  - A constructor that constructs a Time object with the specified hour, minute,
    and second.
  - Three getter methods for the data fields hour, minute, and second,
    respectively.
  - A method named setTime(long elapseTime) that sets a new time for the object
    using the elapsed time. For example, if the elapsed time is 555550000
    milliseconds, the hour is 10, the minute is 19, and the second is 10.

  Write a test program that creates two Time objects (using new Time() and
  new Time(555550000)) and displays their hour, minute, and second in the format
  hour:minute:second.
*/

public class E10_01 {
  public static void main(String[] args) {
    Time a = new Time();
    Time b = new Time(555550000);

    System.out.println("a: " + a);
    System.out.println("b: " + b);
  }

  private static class Time {
    private int hour;
    private int minute;
    private int second;

    Time() {
      this(System.currentTimeMillis());
    }

    Time(long elapsedTime) {
      this((int)(elapsedTime / 3600000) % 24, (int)(elapsedTime / 60000) % 60,
        (int)(elapsedTime / 1000) % 60);
    }

    Time(int hour, int minute, int second) {
      this.hour = hour;
      this.minute = minute;
      this.second = second;
    }

    // getters
    int getHour() {
      return hour;
    }

    int getMinute() {
      return minute;
    }

    int getSecond() {
      return second;
    }

    // instance methods
    void setTime(long elapseTime) {
      hour = (int)((elapseTime / 3600000) % 24);
      minute = (int)((elapseTime / 60000) % 60);
      second = (int)((elapseTime / 1000) % 60);
    }

    @Override
    public String toString() {
      return hour + ":" + minute + ":" + second;
    }
  }
}
