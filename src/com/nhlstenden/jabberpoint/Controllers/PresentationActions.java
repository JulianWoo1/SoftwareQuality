package com.nhlstenden.jabberpoint.Controllers;

import javax.swing.*;

public class PresentationActions {
  private final PresentationService service;

  public PresentationActions(PresentationService service) {
    this.service = service;
  }

  public void openPresentation() {
    service.open();
  }

  public void newPresentation() {
    service.clear();
  }

  public void savePresentation() {
    service.save();
  }

  public void nextSlide() {
    service.next();
  }

  public void previousSlide() {
    service.previous();
  }

  public void gotoSlide() {
    service.goTo();
  }

  public void showAbout() {
    service.about();
  }

  public void exitApplication() {
    service.exit();
  }
}
