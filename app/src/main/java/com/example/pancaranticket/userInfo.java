package com.example.pancaranticket;

public class userInfo {
    private static String fullname;
    private static String email;
    private static String username;
    private static String role;

    private static String destination;
    private static String from;
    private static String date;

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

}
