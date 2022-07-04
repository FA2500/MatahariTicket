package com.example.pancaranticket;

public class userInfo {
    private static String fullname;
    private static String email;
    private static String username;
    private static String role;

    private static String destination;
    private static String destinationState;
    private static String from;
    private static String fromState;
    private static String date;
    private static String rdate;

    public static String getFullName()
    {
        return fullname;
    }

    public static void setFullname(String user)
    {
        fullname = user;
    }

    public static String getEmail()
    {
        return email;
    }

    public static void setEmail(String user)
    {
        email = user;
    }

    public static String getUsername()
    {
        return username;
    }

    public static void setUsername(String user)
    {
        username = user;
    }

    public static String getRole()
    {
        return role;
    }

    public static void setRole(String user)
    {
        role = user;
    }

    public static String getDestination() { return destination; }

    public static void setDestination(String user) { destination = user; }

    public static String getFrom() { return from; }

    public static void setFrom(String user) { from = user ;}

    public static String getDate() { return date; }

    public static void setDate(String user) { date = user ;}

    public static String getDestinationState() { return destinationState ;}

    public static void setDestinationState(String user) { destinationState = user ;}

    public static String getFromState() { return fromState ;}

    public static void setFromState(String user) { fromState = user  ;}

    public static String getRdate() { return rdate ;}

    public static void setRdate(String user) { rdate = user ;}

}
