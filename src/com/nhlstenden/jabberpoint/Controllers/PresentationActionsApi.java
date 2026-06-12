package com.nhlstenden.jabberpoint.Controllers;

public interface PresentationActionsApi {
    void nextSlide();
    void previousSlide();
    void gotoSlide();
    void openPresentation();
    void newPresentation();
    void savePresentation();
    void showAbout();
    void exitApplication();
}