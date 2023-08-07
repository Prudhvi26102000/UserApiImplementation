package com.DPDzero.config;

public class AppConstants {

    public static final String status="error";
    public static final String INVALID_REQUEST="Invalid request. Please provide all required fields: username, email, password, full_name.";
    public static final String USERNAME_EXISTS="The provided username is already taken. Please choose a different username.";
    public static final String EMAIL_EXISTS="The provided email is already registered. Please use a different email address.";
    public static final String INVALID_PASSWORD="The provided password does not meet the requirements. Password must be at least 8 characters long and contain a mix of uppercase and lowercase letters, numbers, and special characters.";
    public static final String INVALID_AGE="Invalid age value. Age must be a positive integer.";
    public static final String GENDER_REQUIRED="Gender field is required. Please specify the gender (e.g., male, female, non-binary).";
    public static final String INTERNAL_SERVER_ERROR="An internal server error occurred. Please try again later.";
    public static final String INVALID_EMAIL="Invalid email address.Please check your email address.";
    public static final String INVALID_CREDENTIALS= "Invalid credentials. The provided username or password is incorrect.";
    public static final String MISSING_FIELDS= "Missing fields. Please provide both username and password.";
    public static final String INVALID_KEY= "The provided key is not valid or missing.";
    public static final String INVALID_VALUE= "The provided value is not valid or missing.";
    public static final String KEY_EXISTS="The provided key already exists in the database. To update an existing key, use the update API.";
    public static final String INVALID_TOKEN="Invalid access token provided";
    public static final String KEY_NOT_FOUND= "The provided key does not exist in the database.";
    public static final String AUTHORIZATION_ERROR="Its UnAuthorized. Please authorize with correct details.";
}
